package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.citrus.ticket.infraestructure.persistence.schemas.ClientSchema;

public interface ClientRepository extends JpaRepository<ClientSchema, UUID> {

	@Query(value = "SELECT c.* FROM cliente c WHERE c.email = :email LIMIT 1", nativeQuery = true)
	ClientSchema findClientByEmail(String email);

	@Query(value = "SELECT count(id) FROM cliente c WHERE c.codigo = :code", nativeQuery = true)
	int alreadyExistsClientWithThisCode(String code);

	ClientSchema findClientByCode(String code);

}
