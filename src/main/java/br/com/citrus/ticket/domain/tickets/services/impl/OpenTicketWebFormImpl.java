package br.com.citrus.ticket.domain.tickets.services.impl;

import br.com.citrus.ticket.domain.tickets.models.*;
import br.com.citrus.ticket.domain.tickets.services.OpenTicketWebForm;
import br.com.citrus.ticket.domain.tickets.usecases.GenerateProcotolNumberUseCase;
import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiInteraction;
import br.com.citrus.ticket.domain.tickets.vo.webForm.WebFormTicketDTO;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.ClientRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.GeneralConfigurationJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicketAttachmentsJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicketJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.webForm.WebFormRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.*;
import br.com.citrus.ticket.infraestructure.web.ticket.dto.FileUploadDTO;
import br.com.citrus.ticket.infraestructure.web.ticket.CitrusGateway;
import br.com.citrus.ticket.infraestructure.web.ticket.enums.IdentifyClient;
import br.com.citrus.ticket.shared.constants.Constantes;
import br.com.citrus.ticket.shared.exception.RequiredFieldException;
import br.com.citrus.ticket.shared.utils.Helper;
import br.com.citrus.ticket.shared.utils.TimeUnitCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OpenTicketWebFormImpl implements OpenTicketWebForm {

	@Autowired
	private GenerateProcotolNumberUseCase generateProcotolUseCase;
	@Autowired
	private WebFormRepository webFormRepository;
	@Autowired
	private TicketJpaRepository ticketJpaRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private TicketAttachmentsJpaRepository ticketAttachmentsRepository;
	@Autowired
	private GeneralConfigurationJpaRepository generalConfigurationRepository;
	@Autowired
	private CitrusGateway citrusGateway;



	private static final Logger logger = LoggerFactory.getLogger(OpenTicketWebFormImpl.class);

//	ReclameAquiInteraction reclameAquiInteraction = new ReclameAquiInteraction();

	public Ticket execute(UUID webFormId, WebFormTicketDTO dto) {
		var webForm = webFormRepository.findById(webFormId).orElseThrow(() -> new RuntimeException("Web form not found"));

		validateRequiredFields(dto, webForm);

		var client = getClient(dto, webForm);
		var ticket = createTicket(dto, webForm, client);

		return saveTicket(ticket, dto, webForm);
	}

	private ClientSchema getClient(WebFormTicketDTO dto, WebFormSchema webForm) {
		if (shouldUseAnonymousClient(webForm, dto)) {
			return clientRepository.findClientByCode(webForm.getAnonymousClientCode());
		}
		return createOrUpdateClient(dto, webForm);
	}

	private boolean shouldUseAnonymousClient(WebFormSchema webForm, WebFormTicketDTO dto) {
		if (IdentifyClient.NO.name().equalsIgnoreCase(dto.getIdentify()) && webForm.getAnonymousClientCode() == null) {
			throw new RequiredFieldException(" Para o atendimento anônimo, o código do cliente no cadastro do Formulário Web é obrigatório");
		}
		var anonymousClient = clientRepository.findClientByCode(webForm.getAnonymousClientCode());
		if (IdentifyClient.NO.name().equalsIgnoreCase(dto.getIdentify()) && Objects.isNull(anonymousClient)) {
			throw new RequiredFieldException("Não existe um cliente com o código informado para o atendimento anônimo");
		}
		return IdentifyClient.NO.name().equalsIgnoreCase(dto.getIdentify());
	}

	private ClientSchema createOrUpdateClient(WebFormTicketDTO dto, WebFormSchema webForm) {
		var client = clientRepository.findClientByCode(dto.getCode());
		if (Objects.nonNull(client)) {
			return client;
		}
		return createClient(dto, webForm);
	}

	private ClientSchema createClient(WebFormTicketDTO dto, WebFormSchema webForm) {
		var clientSchema = new ClientSchema();
		clientSchema.setEmail(dto.getEmail());
		clientSchema.setName(dto.getName());
		clientSchema.setPhone(dto.getPhone());
		clientSchema.setCode(getClientCode(dto));
		clientSchema.setClientTypeId(webForm.getClientType().getId());
		clientSchema.setActive(true);
		clientSchema.setRegionId(webForm.getRegion().getId());
//		clientSchema.setRegisterDate(reclameAquiInteraction.getCreationDate());
		clientSchema.setRegisterDate(LocalDateTime.now());
		clientSchema.setSfaCode(dto.getSfaCode());

		var clientExtraFields = mapToClientExtraFields(dto.getAdditionalFields(), webForm);
		clientExtraFields.forEach(field -> field.setClient(clientSchema));
		clientSchema.setExtraFields(clientExtraFields);

		return clientRepository.save(clientSchema);
	}

	private String getClientCode(WebFormTicketDTO dto) {
		return Objects.nonNull(dto.getCode()) ? dto.getCode() : Helper.generatesNumber(Constantes.TAMANHO_CODIGO);
	}

	private List<ClientExtraFieldSchema> mapToClientExtraFields(Map<String, Object> additionalFields, WebFormSchema webForm) {
		List<ClientExtraFieldSchema> clientExtraFields = new ArrayList<>();
		if (Objects.nonNull(webForm.getClientType()) && Objects.nonNull(webForm.getClientType().getExtraFieldGroup())) {
			additionalFields.forEach((key, value) -> webForm.getClientType().getExtraFieldGroup().getExtraFields().stream()
					.map(extraField -> {
						if(extraField.isRequired() && ((Objects.isNull(value) || value.toString().isBlank()) || !additionalFields.containsKey(extraField.getAttributeReturnJson()))) {
							throw new RequiredFieldException("O campo " + extraField.getAttributeReturnJson() + " é obrigatório");
						}
						return extraField;
					})
					.filter(extraField -> key.equals(extraField.getAttributeReturnJson()))
					.findFirst()
					.ifPresent(extraField -> clientExtraFields.add(
                            new ClientExtraFieldSchema(null, value.toString(), null, null, extraField, null)
                    )));
		}
		return clientExtraFields;
	}

	private TicketSchema createTicket(WebFormTicketDTO dto, WebFormSchema webForm, ClientSchema client) {
		String protocol = generateProcotolUseCase.execute(webForm.getChannelTicket().getId());
		TicketSchema ticket = new TicketSchema(protocol);

		ticket.setDestinationSectorId(webForm.getSectorId());
		ticket.setTicketChannel(new TicketChannelSchema(webForm.getChannelTicket().getId()));
		ticket.setPreTicket(false);
		ticket.setOcurrency(webForm.getOcurrency());
		ticket.setSla(calculateSla(webForm.getOcurrency()));
		ticket.setCreatorId(webForm.getCreatorId());
		ticket.setClient(client);
		ticket.setDescription(dto.getMessage());
		ticket.setSolution(webForm.getSolution());

		return ticket;
	}

	private int calculateSla(OcurrencySchema ocurrency) {
		if (Objects.isNull(ocurrency) || Objects.isNull(ocurrency.getSla()) || Objects.isNull(ocurrency.getSlaTimeUnit())) {
			return 0;
		}
		Long minuteSla = TimeUnitCustom.valueOf(ocurrency.getSlaTimeUnit())
				.converterTempoMinutos(ocurrency.getSla());
		return Math.toIntExact(TimeUnitCustom.converterSegundos(minuteSla));
	}

	private Ticket saveTicket(TicketSchema ticket, WebFormTicketDTO dto, WebFormSchema webForm) {
		List<TicketExtraFieldSchema> extraFields = mapToTicketExtraFields(dto.getAdditionalFields(), webForm, ticket);
		ticket.setExtraFields(extraFields);

		var savedTicket = ticketJpaRepository.save(ticket);
		List<TicketAttachmentsSchema> attachments = mapToTicketAttachments(dto.getAttachment(), webForm, savedTicket);
		ticketAttachmentsRepository.saveAll(attachments);


		try {
			citrusGateway.SendEmailNewTicket(savedTicket.getId());
		} catch (Exception e) {
			logger.error("Falha ao enviar email de notificação de novo Atendimento", e);
		}

		return savedTicket.toModel();
	}

	private List<TicketExtraFieldSchema> mapToTicketExtraFields(Map<String, Object> additionalFields, WebFormSchema webForm, TicketSchema ticket) {
		List<TicketExtraFieldSchema> ticketExtraFields = new ArrayList<>();
		if (webForm.getExtraFieldGroup() != null) {
			additionalFields.forEach((key, value) -> webForm.getExtraFieldGroup().getExtraFields().stream()
					.map(extraField -> {
						if(extraField.isRequired() && ((Objects.isNull(value) || value.toString().isBlank()) || !additionalFields.containsKey(extraField.getAttributeReturnJson()))) {
							throw new RequiredFieldException("O campo " + extraField.getAttributeReturnJson() + " é obrigatório");
						}
						return extraField;
					})
					.filter(extraField -> key.equals(extraField.getAttributeReturnJson()))
					.findFirst()
					.ifPresent(extraField -> {
						TicketExtraFieldSchema extraFieldEntity = new TicketExtraFieldSchema(null, value.toString(), null, null, extraField, ticket);
						ticketExtraFields.add(extraFieldEntity);
					}));
		}
		return ticketExtraFields;
	}

	private List<TicketAttachmentsSchema> mapToTicketAttachments(List<FileUploadDTO> files, WebFormSchema webForm, TicketSchema ticket) {
		List<TicketAttachmentsSchema> attachments = new ArrayList<>();

		if (files != null) {
			for (FileUploadDTO fileUploadDTO : files) {
				if (fileUploadDTO.getBase64Content() == null || fileUploadDTO.getFileName() == null) {
					logger.warn("Arquivo com dados incompletos ignorado.");
					continue;
				}

				byte[] fileContent;
				try {
					fileContent = Base64.getDecoder().decode(fileUploadDTO.getBase64Content());
				} catch (IllegalArgumentException e) {
					logger.error("Conteúdo base64 inválido para o arquivo: {}", fileUploadDTO.getFileName(), e);
					continue;
				}

				File savedFile = saveFileToFolder(fileUploadDTO.getFileName(), fileContent, ticket.getId());
				if (savedFile == null) {
					logger.error("Erro ao salvar o arquivo: {}", fileUploadDTO.getFileName());
					continue;
				}

				TicketAttachmentsSchema attachment = new TicketAttachmentsSchema();
				attachment.setAttachment(fileUploadDTO.getFileName());
				attachment.setTicket(ticket);
				attachment.setUserId(webForm.getCreatorId());
				attachment.setRegisterDate(LocalDateTime.now());
//				attachment.setRegisterDate(reclameAquiInteraction.getCreationDate());


				attachments.add(attachment);
			}
		}

		return attachments;
	}

	private File saveFileToFolder(String fileName, byte[] fileContent, UUID ticketId) {
		String folderPath = generalConfigurationRepository.getFolder().get(0);

		Path ticketFolderPath = Paths.get(folderPath, "atendimentos", ticketId.toString());

		try {
			if (Files.notExists(ticketFolderPath)) {
				Files.createDirectories(ticketFolderPath);
			}
		} catch (IOException e) {
			logger.error("Erro ao criar a pasta: {}", ticketFolderPath, e);
			return null;
		}

		Path filePath = ticketFolderPath.resolve(fileName);

		try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
			fos.write(fileContent);
			fos.flush();
		} catch (IOException e) {
			logger.error("Erro ao salvar o arquivo: {}", fileName, e);
			return null;
		}

		return filePath.toFile();
	}

	private void validateRequiredFields(WebFormTicketDTO dto, WebFormSchema webForm) {
		if (webForm.getChannelTicket() == null) {
			throw new RequiredFieldException("O canal de atendimento no cadastro do Formulário Web é obrigatório");
		}
		if (webForm.getSectorId() == null) {
			throw new RequiredFieldException("O setor no cadastro do Formulário Web é obrigatório");
		}
		if (webForm.getOcurrency() == null) {
			throw new RequiredFieldException("A Ocorrência no cadastro do Formulário Web é obrigatório");
		}
		if (!IdentifyClient.NO.name().equalsIgnoreCase(dto.getIdentify())) {
			if (dto.getName() == null || dto.getName().isEmpty()) {
				throw new RequiredFieldException("O campo name é obrigatório");
			}
			if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
				throw new RequiredFieldException("O campo email é obrigatório");
			}
			if (dto.getPhone() == null || dto.getPhone().isEmpty()) {
				throw new RequiredFieldException("O campo phone é obrigatório");
			}
			if (dto.getMessage() == null || dto.getMessage().isEmpty()) {
				throw new RequiredFieldException("O campo message é obrigatório");
			}
		}
		if (webForm.getClientType() == null) {
			throw new RequiredFieldException("Tipo de cliente no cadastro do Formulário Web é obrigatório");
		}
	}

}
