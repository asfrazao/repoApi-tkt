package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.gateways;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import br.com.citrus.ticket.domain.tickets.gateways.TicketGateway;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.models.TicketComment;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.GeneralConfigurationJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicektCommentJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicketChannelJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.TicketJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketCommentSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketSchema;

@Component
public class TicketGatewayImpl implements TicketGateway {

	private static final String TICKET_CHANNEL_ID_NOT_FOUND = "ticket channel id not found";
	private static final String TICKET_PROTOCOL_CODE_NOT_FOUND = "ticket protocol code not found";
	private static final String PROTOCOL_FORMAT_NOT_FOUND = "protocol format not found";
	private static final String NEW_TICKET_OPEN = "new ticket open with id: {}";
	private static final String TICKET_CANCELED = "ticket with id: {} canceled";

	private static final int INT_ONE = 1;

	private static final Logger logger = LoggerFactory.getLogger(TicketGatewayImpl.class);

	@Autowired
	private GeneralConfigurationJpaRepository generalConfigRepository;

	@Autowired
	private TicketChannelJpaRepository ticketChannelRepository;

	@Autowired
	private TicektCommentJpaRepository ticketCommentRepository;

	@Autowired
	private TicketJpaRepository ticketRepository;

	@Override
	public int getNextSequenceFromProtocolFormat(String protocolCode) {
		String date = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now());
		var protocol = protocolCode + date;
		var sequence = ticketRepository.getLastSequenceFromProtocolFormat(protocol);
		if (sequence.size() == 0) {
			return INT_ONE;
		}

		var seq = sequence.get(0).replaceAll("^0*", "");
		var curr = Integer.parseInt(seq);
		return curr + INT_ONE;
	}

	@Override
	public String getGeneralConfigProtocolFormat() {
		var result = generalConfigRepository.getProtocolFormat();
		if (result.isEmpty()) {
			throw new NotFoundException(PROTOCOL_FORMAT_NOT_FOUND);
		}
		return result.get(0);
	}

	@Override
	public String getTicketProtocolCodeById(UUID id) {
		var result = ticketChannelRepository.getTicketProtocolCodeById(id);
		if (result.isEmpty()) {
			throw new NotFoundException(TICKET_PROTOCOL_CODE_NOT_FOUND);
		}
		return result.get(0);
	}

	@Override
	public UUID getGeneralConfigTicketChannelId() {
		var result = generalConfigRepository.getTicketChannelId();
		if (result.isEmpty()) {
			throw new NotFoundException(TICKET_CHANNEL_ID_NOT_FOUND);
		}
		return result.get(0);
	}

	@Override
	public Ticket saveTicket(Ticket ticket) {
		TicketSchema ticketSchema = new TicketSchema(ticket);
		TicketSchema schema = ticketRepository.save(ticketSchema);

		logger.info(NEW_TICKET_OPEN, schema.getId());
		return schema.toModel();
	}

	@Override
	public Ticket getTicketById(UUID ticketId) {
		if (ticketId != null) {
			Optional<TicketSchema> findById = ticketRepository.findById(ticketId);
			if (findById.isPresent()) {
				TicketSchema ticketSchema = findById.get();
				return ticketSchema.toModel();
			}
		}
		return null;
	}

	@Override
	public void updateTicket(Ticket ticket) {
		ticketRepository.save(new TicketSchema(ticket));
		logger.info(TICKET_CANCELED, ticket.getId());
	}

	@Override
	public void newCommentPreTicket(TicketComment ticketComment) {
		TicketCommentSchema comment = new TicketCommentSchema(ticketComment);
		ticketCommentRepository.save(comment);
	}

	@Override
	public Ticket getTicketByRaId(String idRa) {
		TicketSchema schema = ticketRepository.getTicketFromReclameAquiIssueIdRa(idRa);
		if (schema != null) {
			return schema.toModel();
		}
		return null;
	}

	@Override
	public void setTicketToNotRead(UUID ticketId) {
		ticketRepository.setTicketToNotRead(ticketId);
	}

	@Override
	public boolean validIfMessageAlreadyExists(String socialMediaCommentId) {
		return ticketCommentRepository.getCountTicketCommentBySocialMediaCommentId(socialMediaCommentId) > 0;
	}

	@Override
	public TicketSchema getTicketSchemaByRaId(String idRa) {
		TicketSchema schema = ticketRepository.getTicketFromReclameAquiIssueIdRa(idRa);
		if (schema != null) {
			return schema;
		}
		return null;
	}

	@Override
	public TicketSchema updateTicketByRaId(TicketSchema ticket) {
		//ticketRepository.setTicketResolvedByCustomer(ticket.getStatus(), ticket.getId());
		//return ticket;
		return ticketRepository.save(ticket);
	}
}
