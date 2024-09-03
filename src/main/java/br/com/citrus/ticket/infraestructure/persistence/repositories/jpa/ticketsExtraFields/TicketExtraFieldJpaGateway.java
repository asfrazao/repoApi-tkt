package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.ticketsExtraFields;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.extraFields.gateway.TicketExtraFieldGateway;
import br.com.citrus.ticket.domain.extraFields.models.TicketExtraField;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.extraFields.ExtraFieldJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicketJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.ExtraFieldSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketExtraFieldSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketSchema;
import jakarta.transaction.Transactional;

@Component
public class TicketExtraFieldJpaGateway implements TicketExtraFieldGateway {

	@Autowired
	private TicketExtraFieldJpaRepository repository;

	@Autowired
	private ExtraFieldJpaRepository extraFieldJpaRepository;

	@Autowired
	private TicketJpaRepository ticketJpaRepository;

	@Override
	public void save(TicketExtraField field) {
		TicketExtraFieldSchema ticketExtraFieldSchema = new TicketExtraFieldSchema();
		UUID id = UUID.randomUUID();
		ticketExtraFieldSchema.setId(id);
		ticketExtraFieldSchema.setValue(field.getValue());

		UUID extraFieldId = field.getExtraFieldId();
		UUID ticketId = field.getTicketId();

		if (extraFieldId != null && ticketId != null) {

			Optional<ExtraFieldSchema> extraFieldSchema = extraFieldJpaRepository.findById(extraFieldId);
			ticketExtraFieldSchema.setExtraField(extraFieldSchema.get());
			Optional<TicketSchema> ticketSchema = ticketJpaRepository.findById(ticketId);
			ticketExtraFieldSchema.setTicket(ticketSchema.get());
			repository.save(ticketExtraFieldSchema);
		}
	}

	@Override
	@Transactional
	public void update(TicketExtraField field) {
		repository.updateTicketExtraField(field.getValue(), field.getTicketId(), field.getExtraFieldId());
	}

}
