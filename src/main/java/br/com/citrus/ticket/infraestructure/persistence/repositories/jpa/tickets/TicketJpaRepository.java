package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketSchema;

public interface TicketJpaRepository extends JpaRepository<TicketSchema, UUID> {

	Optional<TicketSchema> findByProtocol(String protocol);

	@Query(value = """
			SELECT RIGHT(protocolo, 6) AS sequence FROM atendimento \
			WHERE protocolo LIKE :protocol% \
			ORDER BY sequence DESC\
			""", nativeQuery = true)
	List<String> getLastSequenceFromProtocolFormat(@Param("protocol") String protocol);

	@Query(value = """
			SELECT * FROM atendimento a \
				WHERE EXISTS(SELECT * FROM atendimento_reclame_aqui_issue arai WHERE arai.atendimento_id = a.id AND arai.ra_id = :id_ra)
			""", nativeQuery = true)
	TicketSchema getTicketFromReclameAquiIssueIdRa(@Param("id_ra") String idRa);

	@Modifying
	@Transactional
	@Query(value = "UPDATE atendimento SET visualizado = false where id = :ticketId", nativeQuery = true)
	void setTicketToNotRead(UUID ticketId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE atendimento SET status = :status where id = :ticketId", nativeQuery = true)
	void setTicketResolvedByCustomer(String status, UUID ticketId);

}
