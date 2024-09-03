package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.facebook;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.citrus.ticket.infraestructure.persistence.schemas.facebook.TicketFacebookPostMediaSchema;

public interface TicketFacebookPostMediaRepository extends JpaRepository<TicketFacebookPostMediaSchema, UUID> {
    
}
