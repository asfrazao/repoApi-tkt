package br.com.citrus.ticket.infraestructure.web.ticket;

import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.models.TicketIssueReclameAqui;

public interface RaWorkerGateway {

	 void syncTicketRaWorker(Ticket ticket, TicketIssueReclameAqui issueRa) throws Exception;
}
