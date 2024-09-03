package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.attendant;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.citrus.ticket.infraestructure.persistence.schemas.SolutionSchema;


public interface AttendantJpaRepository extends JpaRepository<SolutionSchema, UUID>{

    @Query(value = """
            SELECT sc \
            FROM SolutionSchema AS sc JOIN sc.ocurrency AS o \
            WHERE sc.ocurrency.id = :ocurrencyId \
            AND sc.alternativeFlow = false \
            AND sc.active = true \
            ORDER BY sc.name \
            """)
    List<SolutionSchema> findByOcurrencyId(@Param("ocurrencyId") UUID ocurrencyId);
}
