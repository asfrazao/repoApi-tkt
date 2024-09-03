package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.ClientTypeSchema;

public interface ClientTypeRepository extends JpaRepository<ClientTypeSchema, UUID> {

	@Query(value = "SELECT tc.id FROM tipo_cliente tc WHERE tc.codigo = :code LIMIT 1", nativeQuery = true)
	public UUID getClientTypeIdByCode(String code);

}
