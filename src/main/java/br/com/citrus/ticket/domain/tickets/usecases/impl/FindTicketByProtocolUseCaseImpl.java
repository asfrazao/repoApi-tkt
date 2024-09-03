package br.com.citrus.ticket.domain.tickets.usecases.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.tickets.exceptions.TicketNotFoundException;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.usecases.FindTicketByProtocolUseCase;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicketJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketSchema;

@Service
public class FindTicketByProtocolUseCaseImpl implements FindTicketByProtocolUseCase {

	@Autowired
	private TicketJpaRepository repository;

	@Override
	public Optional<Ticket> execute(String protocol) {
		Optional<TicketSchema> schema = repository.findByProtocol(protocol);
		if (schema.isEmpty()) {
			throw new TicketNotFoundException();
		}
		TicketSchema founded = schema.get();
		
		return Optional.of(founded.toModel());
	}

}
