package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui.TicketIssueReclameAquiSchema;

public interface TicketIssueReclameAquiRepository extends JpaRepository<TicketIssueReclameAquiSchema, UUID> {

	@Query(value = "SELECT count(*) FROM atendimento_reclame_aqui_issue WHERE ticket_interaction_id = :ticketInteractionId", nativeQuery = true)
	int interactionIdAlreadyExists(String ticketInteractionId);


}
