package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.facebook;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.facebook.TicketFacebookPostSchema;

public interface TicketFacebookPostRepository extends JpaRepository<TicketFacebookPostSchema, UUID> {

	@Query("""
			SELECT t FROM TicketFacebookPostSchema t\
			 LEFT JOIN TicketFacebookCommentSchema c ON c.ticketId = t.ticketId\
			 WHERE t.fbPostId = :fbPostId AND c.fbUserId = :fbUserId\
			""")
	List<TicketFacebookPostSchema> findTicketFbPostByFbUser(String fbPostId, String fbUserId);

	@Query("""
			SELECT t FROM TicketFacebookPostSchema t\
			 WHERE t.fbPostId = :fbPostId AND t.fbCommentId = :fbCommmetId\
			""")
	List<TicketFacebookPostSchema> findTicketByPostAndCommentId(String fbPostId, String fbCommmetId);

	@Query("SELECT t FROM TicketFacebookPostSchema t WHERE t.ticketId = :ticketId")
	List<TicketFacebookPostSchema> getTicketByTicketId(UUID ticketId);

}
