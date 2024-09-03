package br.com.citrus.ticket.domain.tickets.exceptions;

public class TicketIsNotOpenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	TicketIsNotOpenException() {
		super("Este atendimento não está aberto");
	}
}
