package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import br.com.citrus.ticket.infraestructure.persistence.schemas.AttachmentTicketSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentTicketJpaRepository extends JpaRepository<AttachmentTicketSchema, UUID> {

}
