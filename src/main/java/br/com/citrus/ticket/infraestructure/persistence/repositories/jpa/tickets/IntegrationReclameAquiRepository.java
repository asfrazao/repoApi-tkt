package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui.IntegrationReclameAquiSchema;

public interface IntegrationReclameAquiRepository extends JpaRepository<IntegrationReclameAquiSchema, UUID> {

	@Query(value = "SELECT i.integracao_canal_atendimento_id " +
			"FROM integracao_reclame_aqui i " +
			"where client_id_worker = :clientIdWorker and " +
			"company_id = :companyId " +
			"LIMIT 1", nativeQuery = true)
	UUID getIntegrationTicketChannelIdByIntegrationReclameAqui(String clientIdWorker, String companyId);

	@Query(value = "SELECT * FROM integracao_reclame_aqui i where client_id_worker = :clientIdWorker", nativeQuery = true)
	IntegrationReclameAquiSchema getIntegrationReclameAquiByClientIdWorker(String clientIdWorker);

}
