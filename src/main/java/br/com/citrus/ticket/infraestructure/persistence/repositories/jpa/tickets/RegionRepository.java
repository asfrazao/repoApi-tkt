package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.RegionSchema;

public interface RegionRepository extends JpaRepository<RegionSchema, UUID> {

	@Query(value = "SELECT r.id FROM regiao r WHERE r.nome = :regionName", nativeQuery = true)
	UUID getRegionIdToReclameAquiClient(String regionName);

}
