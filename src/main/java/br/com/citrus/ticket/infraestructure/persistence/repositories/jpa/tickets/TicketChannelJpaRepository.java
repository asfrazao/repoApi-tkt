package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketChannelSchema;

public interface TicketChannelJpaRepository extends JpaRepository<TicketChannelSchema, UUID> {

	@Query("SELECT t.ticketProtocolCode FROM TicketChannelSchema t WHERE t.id = :id")
	List<String> getTicketProtocolCodeById(@Param("id") UUID id);

	@Query(value = "SELECT ica.canal_atendimento_id FROM integracao_canal_atendimento ica WHERE ica.id = :ticketChannelIntegrationId LIMIT 1", nativeQuery = true)
	UUID getTicketChannelIdFromTicketChannelIntegrationId(UUID ticketChannelIntegrationId);

	@Query(value = """
			SELECT ica.canal_atendimento_id
				FROM integracao_api_facebook_comentario_pagina iafcp
				JOIN integracao_facebook_comentario ifc ON iafcp.id = ifc.integracao_api_facebook_comentario_pagina_id
				JOIN integracao_canal_atendimento ica ON ifc.integracao_canal_atendimento_id = ica.id
				WHERE iafcp.dcs_integration_id = :externalId
			""", nativeQuery = true)
	UUID getTicketChannelIdFromFbExternalIdClient(String externalId);

	@Query(value = """
			SELECT ica.canal_atendimento_id
				FROM integracao_api_instagram_comentario_pagina iaicp
				JOIN integracao_instagram_comentario igc ON iaicp.id = igc.integracao_api_instagram_comentario_pagina_id
				JOIN integracao_canal_atendimento ica ON igc.integracao_canal_atendimento_id = ica.id
				WHERE iaicp.dcs_integration_id = :externalId
			""", nativeQuery = true)
	UUID getTicketChannelIdFromIgExternalIdClient(String externalId);

}
