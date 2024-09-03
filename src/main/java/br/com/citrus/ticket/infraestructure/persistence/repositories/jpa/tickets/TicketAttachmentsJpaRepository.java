package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketAttachmentsSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketAttachmentsJpaRepository extends JpaRepository<TicketAttachmentsSchema, UUID> {

	TicketAttachmentsSchema findByAttachmentAndTicket_Id(String anexo,UUID ticketId);
	
}
