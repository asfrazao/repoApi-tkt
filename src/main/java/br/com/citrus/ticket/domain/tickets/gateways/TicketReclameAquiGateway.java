package br.com.citrus.ticket.domain.tickets.gateways;

import br.com.citrus.ticket.domain.tickets.models.TicketIssueReclameAqui;

public interface TicketReclameAquiGateway {

	TicketIssueReclameAqui saveIssueReclameAqui(TicketIssueReclameAqui issue);

	boolean interactionIdAlreadyExists(String ticketInteractionId);

}
