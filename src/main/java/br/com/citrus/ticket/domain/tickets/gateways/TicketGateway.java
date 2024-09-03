package br.com.citrus.ticket.domain.tickets.gateways;

import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.models.TicketComment;
import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketSchema;

public interface TicketGateway {

	int getNextSequenceFromProtocolFormat(String protocolCode);

	String getGeneralConfigProtocolFormat();

	UUID getGeneralConfigTicketChannelId();

	String getTicketProtocolCodeById(UUID id);
	
	Ticket saveTicket(Ticket ticket);

	Ticket getTicketById(UUID ticketId);

	void updateTicket(Ticket ticket);

	Ticket getTicketByRaId(String idRa);

	void newCommentPreTicket(TicketComment ticketComment);

	void setTicketToNotRead(UUID ticketId);

	boolean validIfMessageAlreadyExists(String socialMediaCommentId);
	TicketSchema getTicketSchemaByRaId(String idRa);
	TicketSchema updateTicketByRaId(TicketSchema ticketSchema);
}
