package br.com.citrus.ticket.domain.tickets.usecases;

import java.util.Optional;

import br.com.citrus.ticket.domain.tickets.models.Ticket;

public interface FindTicketByProtocolUseCase {

	Optional<Ticket> execute(String protocol);

}
