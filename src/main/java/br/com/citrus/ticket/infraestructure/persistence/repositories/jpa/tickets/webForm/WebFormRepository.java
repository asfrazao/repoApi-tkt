package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.webForm;

import br.com.citrus.ticket.infraestructure.persistence.schemas.WebFormSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface WebFormRepository extends JpaRepository<WebFormSchema, UUID> {

}
