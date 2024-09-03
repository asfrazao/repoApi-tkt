package br.com.citrus.ticket.infraestructure.web.ticket;

import java.util.UUID;

public interface CitrusGateway {

	 void SendEmailNewTicket(UUID ticketId) throws Exception;
}
