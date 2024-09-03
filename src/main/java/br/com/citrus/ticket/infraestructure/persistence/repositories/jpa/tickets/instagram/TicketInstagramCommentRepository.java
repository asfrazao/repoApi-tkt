package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.instagram;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.instagram.TicketInstagramCommentSchema;

public interface TicketInstagramCommentRepository extends JpaRepository<TicketInstagramCommentSchema, UUID> {
  
  @Query("SELECT count(c) FROM TicketInstagramCommentSchema c WHERE c.igCommentId = :igCommentId")
  int getCountTicketPostByIgCommentId(String igCommentId);
}
