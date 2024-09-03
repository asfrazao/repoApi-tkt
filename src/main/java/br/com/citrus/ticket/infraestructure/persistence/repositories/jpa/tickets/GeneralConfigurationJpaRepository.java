package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.GeneralConfigurationSchema;

public interface GeneralConfigurationJpaRepository extends JpaRepository<GeneralConfigurationSchema, UUID> {

	@Query("SELECT g.protocolFormat FROM GeneralConfigurationSchema g")
	List<String> getProtocolFormat();

	@Query("SELECT g.TicketChannelId FROM GeneralConfigurationSchema g")
	List<UUID> getTicketChannelId();

	@Query("SELECT g.folder FROM GeneralConfigurationSchema g")
	List<String> getFolder();

	@Query("SELECT g.url FROM GeneralConfigurationSchema g")
	List<String> getSystemUrl();
	
}
