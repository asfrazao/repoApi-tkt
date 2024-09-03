package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.instagram;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.instagram.TicketInstagramPostSchema;

public interface TicketInstagramPostRepository extends JpaRepository<TicketInstagramPostSchema, UUID> {

  @Query("SELECT t FROM TicketInstagramPostSchema t WHERE t.igPostId = :igPostId AND t.igUserId = :igUserId")
  List<TicketInstagramPostSchema> findTicketIgPostByIgUser(String igPostId, String igUserId);

  @Query("SELECT t FROM TicketInstagramPostSchema t WHERE t.igCommentId = :igCommentId AND t.igUserId = :igUserId")
  List<TicketInstagramPostSchema> findTicketIgCommentIdByIgUser(String igCommentId, String igUserId);

  @Query("SELECT t FROM TicketInstagramPostSchema t WHERE t.igCommentId = :igCommentId")
  List<TicketInstagramPostSchema> findTicketIgCommentId(String igCommentId);

  @Query("""
			SELECT t FROM TicketInstagramPostSchema t\
			 WHERE t.igPostId = :igPostId AND t.igCommentId = :igCommmetId\
			""")
	List<TicketInstagramPostSchema> findTicketByPostAndCommentId(String igPostId, String igCommmetId);

}
