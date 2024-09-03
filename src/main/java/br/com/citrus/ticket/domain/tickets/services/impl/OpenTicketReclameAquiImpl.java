package br.com.citrus.ticket.domain.tickets.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.enums.TicketStatus;
import br.com.citrus.ticket.domain.tickets.services.OpenTicketAttachment;
import br.com.citrus.ticket.domain.tickets.vo.attachment.AttachmentDto;
import br.com.citrus.ticket.infraestructure.persistence.schemas.SolutionSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui.IntegrationReclameAquiSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.tickets.gateways.IntegrationGateway;
import br.com.citrus.ticket.domain.tickets.gateways.TicketGateway;
import br.com.citrus.ticket.domain.tickets.gateways.TicketReclameAquiGateway;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.models.TicketComment;
import br.com.citrus.ticket.domain.tickets.models.TicketIssueReclameAqui;
import br.com.citrus.ticket.domain.tickets.services.OpenTicketReclameAqui;
import br.com.citrus.ticket.domain.tickets.usecases.GenerateProcotolNumberUseCase;
import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiInteraction;
import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiPayload;
import br.com.citrus.ticket.infraestructure.web.ticket.RaWorkerGateway;

import static br.com.citrus.ticket.domain.tickets.enums.TicketStatus.FINISHED;
import static br.com.citrus.ticket.domain.tickets.enums.TicketStatus.RESOLVED;

@Service
public class OpenTicketReclameAquiImpl implements OpenTicketReclameAqui {

	private static final Logger logger = LoggerFactory.getLogger(OpenTicketReclameAquiImpl.class);
	private static final String CANAL_ATENDIMENTO_NOT_FOUND = "not found a ticket channel integration for reclame aqui ticket channel";

	@Autowired
	private IntegrationGateway integrationService;

	@Autowired
	private TicketReclameAquiGateway ticketReclameAquiService;

	@Autowired
	private TicketGateway ticketService;

	@Autowired
	private GenerateProcotolNumberUseCase generateProcotol;

	@Autowired
	private RaWorkerGateway raWorkerGateway;

	@Autowired
	private OpenTicketAttachment openTicketAttachment;

	@Override
	public void execute(ReclameAquiPayload dto) throws Exception {

		for (ReclameAquiInteraction interaction : dto.getInteractions()) {
			try {
				Ticket ticket = ticketService.getTicketByRaId(dto.getIdRa());

				if (ticket == null) {
					newTicket(dto, interaction);
				} else {
					newComment(dto, ticket, interaction);
				}
			} catch (Exception e) {
				logger.error("FALHA NO ATENDIMENTO {}", e.getMessage());
				throw new Exception(e);
			}
		}
	}

	@Override
	public void update(ReclameAquiPayload dto) throws Exception {
		TicketSchema ticketSchema = ticketService.getTicketSchemaByRaId(dto.getIdRa());
		logger.info(dto.toString());
		if(ticketSchema != null
				&& (!ticketSchema.getStatus().equalsIgnoreCase(FINISHED.getLabel()) && !ticketSchema.getStatus().equalsIgnoreCase(RESOLVED.getLabel()))
				&& dto.isClosed()
		) {
			IntegrationReclameAquiSchema raScheme = integrationService
					.getIntegrationReclameAquiSchemaByClientIdWorker(dto.getIdClient());
			ticketSchema.setStatus(FINISHED.getLabel());
			ticketSchema.setSolution(new SolutionSchema(raScheme.getSolutionId()));
			ticketSchema.setResolved(LocalDateTime.now());
			ticketSchema.setClosure(LocalDateTime.now());
			ticketService.updateTicketByRaId(ticketSchema);
		} else if(ticketSchema != null && ticketSchema.getStatus().equalsIgnoreCase(TicketStatus.WAIT_RETURN.getLabel())) {
			ticketSchema.setStatus(TicketStatus.IN_PROGRESS.getLabel());
			ticketService.updateTicketByRaId(ticketSchema);
		}
	}

	private void newTicket(ReclameAquiPayload dto, ReclameAquiInteraction interaction) throws Exception {
		try {

			UUID ticketChannelIntegrationId = integrationService
					.getIntegrationTicketChannelIdByIntegrationReclameAqui(dto.getIdClient(), dto.getCompanyId());

			UUID ticketChannelId = integrationService
					.getTicketChannelIdFromTicketChannelIntegrationId(ticketChannelIntegrationId);

			String protocol = generateProcotol.execute(ticketChannelId);

/*			LocalDateTime creationDate = dto.getCreationDate();
			Ticket ticket = new Ticket(protocol, creationDate);*/
			Ticket ticket = new Ticket(protocol);

			logger.info("idClient:{} companyId:{}", dto.getIdClient(), dto.getCompanyId());
			fillTicketChannel(ticket, dto.getIdClient(), dto.getCompanyId());

			ticket.fillAttributesFromReclameAqui(dto, interaction);

			logger.info("****creationDate:{}", ticket.getOpening());

			Ticket model = ticketService.saveTicket(ticket);

			var issueRa = fillTicketIssueReclameAqui(dto, interaction, model);

			syncTicketIdRaWorker(model, issueRa);

			if(!dto.getAttached().isEmpty()) {
				dto.getAttached().forEach(attach -> {
					logger.info(attach.getOriginalLink());
					AttachmentDto attachment = new AttachmentDto();
					attachment.setTicketId(model.getId());
					attachment.setAttachment(attach.getOriginalLink());
					openTicketAttachment.save(attachment);
				});
			}

			logger.info("ticket reclame aqui aberto, protocolo: {}, id: {}", protocol, model.getId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		}
	}

	private void syncTicketIdRaWorker(Ticket ticket, TicketIssueReclameAqui issueRa) throws Exception {
		raWorkerGateway.syncTicketRaWorker(ticket, issueRa);
	}

	private TicketIssueReclameAqui fillTicketIssueReclameAqui(ReclameAquiPayload dto,
			ReclameAquiInteraction interaction, Ticket model) {

		String raId = dto.getIdRa();
		LocalDateTime creationDate = interaction.getCreationDate();
		String message = interaction.getMessage();
		boolean response = interaction.isResponse();
		String ticketInteractionId = interaction.getTicketInteractionId();
		String ticketInteractionName = interaction.getTicketInteractionName();

		var issue = new TicketIssueReclameAqui(
				UUID.fromString(dto.getId()), raId, creationDate, message, response,
				ticketInteractionId, ticketInteractionName, model.getId(), UUID.fromString(dto.getIdClient()),
				UUID.fromString(dto.getIdUser()));

		return ticketReclameAquiService.saveIssueReclameAqui(issue);
	}

	private void fillTicketChannel(Ticket ticket, String clientIdWorker, String companyId) throws Exception {

		UUID canalAtendimento = integrationService
				.getIntegrationTicketChannelIdByIntegrationReclameAqui(clientIdWorker, companyId);

		if (canalAtendimento == null) {
			throw new Exception(CANAL_ATENDIMENTO_NOT_FOUND);
		}
		ticket.ticketChannel(canalAtendimento);
	}

	private void newComment(ReclameAquiPayload dto, Ticket ticket, ReclameAquiInteraction interaction)
			throws Exception {
		try {
			if (!ticketReclameAquiService.interactionIdAlreadyExists(interaction.getTicketInteractionId())) {
				TicketComment comment = new TicketComment(interaction.getMessage(),
						interaction.getTicketInteractionId(), ticket.getId());

				if(!dto.getAttached().isEmpty()) {
					comment.setAttachment(dto.getAttached().get(0).getOriginalLink());
				}

				if (!interaction.isResponse() && !validIfMessageAlreadyExists(comment.getSocialMediaCommentId())) {
					ticketService.newCommentPreTicket(comment);
					ticketService.setTicketToNotRead(comment.getTicketId());
					fillTicketIssueReclameAqui(dto, interaction, ticket);

					logger.info("novo comentario no ticket: {}, interactionId: {}", comment.getTicketId(),
							interaction.getTicketInteractionId());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		}
	}

	private boolean validIfMessageAlreadyExists(String socialMediaCommentId) {
		return ticketService.validIfMessageAlreadyExists(socialMediaCommentId);
	}

}
