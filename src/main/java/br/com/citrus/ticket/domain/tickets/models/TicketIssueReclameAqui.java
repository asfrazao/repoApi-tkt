package br.com.citrus.ticket.domain.tickets.models;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketIssueReclameAqui {

	private UUID id;
	private String raId;
	private LocalDateTime creationDate;
	private String message;
	private boolean response;
	private String ticketInteractionId;
	private String ticketInteractionName;
	private UUID ticketId;
	private UUID clientIdWorker;
	private UUID userIdWorker;

	public TicketIssueReclameAqui(UUID id, String raId, LocalDateTime creationDate, String message, boolean response,
			String ticketInteractionId, String ticketInteractionName, UUID ticketId, UUID clientIdWorker,
								  UUID userIdWorker) {
		this.id = id;
		this.raId = raId;
		this.creationDate = creationDate;
		this.message = message;
		this.response = response;
		this.ticketInteractionId = ticketInteractionId;
		this.ticketInteractionName = ticketInteractionName;
		this.ticketId = ticketId;
		this.clientIdWorker = clientIdWorker;
		this.userIdWorker = userIdWorker;
	}
}
