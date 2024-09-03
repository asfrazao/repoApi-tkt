package br.com.citrus.ticket.domain.tickets.gateways;

import br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui.IntegrationReclameAquiSchema;

import java.util.UUID;

public interface IntegrationGateway {

	UUID getIntegrationTicketChannelIdByIntegrationReclameAqui(String clientIdWorker, String companyId);

	UUID getTicketChannelIdFromTicketChannelIntegrationId(UUID ticketChannelIntegrationId);

	UUID getTicketChannelIdFromFbExternalIdClient(String externalId);

	UUID getTicketChannelIdFromIgExternalIdClient(String externalId);

	IntegrationReclameAquiSchema getIntegrationReclameAquiSchemaByClientIdWorker(String clientIdWorker);

}
