package br.com.citrus.ticket.domain.tickets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Ticket not found")
public class TicketNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TicketNotFoundException() {
		super("Ticket not found");
	}
}
