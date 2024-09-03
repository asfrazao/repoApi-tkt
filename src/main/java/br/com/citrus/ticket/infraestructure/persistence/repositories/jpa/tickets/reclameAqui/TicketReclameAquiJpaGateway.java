package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.reclameAqui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.tickets.gateways.TicketReclameAquiGateway;
import br.com.citrus.ticket.domain.tickets.models.TicketIssueReclameAqui;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicketIssueReclameAquiRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui.TicketIssueReclameAquiSchema;

@Component
public class TicketReclameAquiJpaGateway implements TicketReclameAquiGateway {

	@Autowired
	private TicketIssueReclameAquiRepository repository;

	@Override
	public TicketIssueReclameAqui saveIssueReclameAqui(TicketIssueReclameAqui issue) {
		var schema = new TicketIssueReclameAquiSchema(issue);
		TicketIssueReclameAquiSchema persist = repository.save(schema);
		return persist.toModel();
	}

	@Override
	public boolean interactionIdAlreadyExists(String ticketInteractionId) {
		return repository.interactionIdAlreadyExists(ticketInteractionId) > 0;
	}

}
