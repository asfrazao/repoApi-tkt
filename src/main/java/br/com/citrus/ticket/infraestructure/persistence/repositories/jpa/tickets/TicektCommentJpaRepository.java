package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketCommentSchema;

public interface TicektCommentJpaRepository extends JpaRepository<TicketCommentSchema, UUID> {

	@Query(value = "SELECT count(a.id) FROM atendimento_comentario a WHERE a.comentario_rede_social_id = :socialMediaCommentId", nativeQuery = true)
	int getCountTicketCommentBySocialMediaCommentId(String socialMediaCommentId);

}
