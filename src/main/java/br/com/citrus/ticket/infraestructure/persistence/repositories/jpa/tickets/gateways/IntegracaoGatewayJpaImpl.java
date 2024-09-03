package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.gateways;

import java.util.UUID;

import br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui.IntegrationReclameAquiSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.tickets.gateways.IntegrationGateway;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.IntegrationReclameAquiRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicketChannelJpaRepository;

@Component
public class IntegracaoGatewayJpaImpl implements IntegrationGateway {

	@Autowired
	private IntegrationReclameAquiRepository integrationReclameAquiRepository;

	@Autowired
	private TicketChannelJpaRepository ticketChannelRepository;

	@Override
	public UUID getIntegrationTicketChannelIdByIntegrationReclameAqui(String clientIdWorker, String companyId) {
		return integrationReclameAquiRepository
				.getIntegrationTicketChannelIdByIntegrationReclameAqui(clientIdWorker, companyId);
	}

	@Override
	public UUID getTicketChannelIdFromTicketChannelIntegrationId(UUID ticketChannelIntegrationId) {
		return ticketChannelRepository
				.getTicketChannelIdFromTicketChannelIntegrationId(ticketChannelIntegrationId);
	}

	@Override
	public UUID getTicketChannelIdFromFbExternalIdClient(String externalId) {
		return ticketChannelRepository.getTicketChannelIdFromFbExternalIdClient(externalId);
	}

	@Override
	public UUID getTicketChannelIdFromIgExternalIdClient(String externalId) {
		return ticketChannelRepository.getTicketChannelIdFromIgExternalIdClient(externalId);
	}

	@Override
	public IntegrationReclameAquiSchema getIntegrationReclameAquiSchemaByClientIdWorker(String clientIdWorker) {
		return integrationReclameAquiRepository
				.getIntegrationReclameAquiByClientIdWorker(clientIdWorker);
	}

}
