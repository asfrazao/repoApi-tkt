package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.facebook;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.facebook.TicketFacebookCommentSchema;

public interface TicketFacebookCommentRepository extends JpaRepository<TicketFacebookCommentSchema, UUID> {
     
    @Query("SELECT count(c) FROM TicketFacebookCommentSchema c WHERE c.fbCommentId = :fbCommentId")
	int getCountTicketPostByFbCommentId(String fbCommentId);
    
    @Query("SELECT c FROM TicketFacebookCommentSchema c WHERE c.fbCommentId = :fbCommentId")
	List<TicketFacebookCommentSchema> findTicketCommentByFbCommentId(String fbCommentId);
    
	@Query("SELECT c.ticketId FROM TicketFacebookCommentSchema c WHERE c.fbCommentId = :fbCommmetId")
	List<UUID> getTicketIdByCommentId(String fbCommmetId);
}
