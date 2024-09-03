package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.instagram;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.citrus.ticket.infraestructure.persistence.schemas.instagram.TicketInstagramPostMediaSchema;



public interface TicketInstagramPostMediaRepository extends JpaRepository<TicketInstagramPostMediaSchema, UUID> {
  
}
