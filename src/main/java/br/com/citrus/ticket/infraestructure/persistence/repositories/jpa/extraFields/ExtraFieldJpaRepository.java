package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.extraFields;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import br.com.citrus.ticket.infraestructure.persistence.dao.ExtraFieldDAO;
import br.com.citrus.ticket.infraestructure.persistence.schemas.ExtraFieldSchema;
import feign.Param;

public interface ExtraFieldJpaRepository extends JpaRepository<ExtraFieldSchema, UUID> {
    
    @Query(value = """
                    SELECT new br.com.citrus.ticket.infraestructure.persistence.dao.ExtraFieldDAO(f.id, tt.id, f.nameField, t.value) \
                    FROM TicketExtraFieldSchema t \
                    LEFT JOIN t.extraField f \
                    LEFT JOIN t.ticket tt \
                    WHERE tt.protocol = :protocol \
                    AND tt.status = 'ABERTO' \
                    AND f.nameField = :name \
                    AND f.allowed = true \
                    AND f.excluded = false \
                    """
          )
    List<ExtraFieldDAO> findByNameAndTicketProtocol(@Param("name") String name, @Param("protocol") String protocol);

@Query(value =
        """
        SELECT 
        new br.com.citrus.ticket.infraestructure.persistence.dao.ExtraFieldDAO(
        MAX(CAST(ef.id AS string)), 
        CAST(t.id AS string), 
        ef.nameField, 
        '') 
        FROM TicketSchema t
        JOIN t.ocurrency o 
        JOIN o.extraFieldGroup efg 
        JOIN efg.extraFields ef 
        WHERE t.protocol = :protocol 
        AND ef.nameField is not NULL 
        AND ef.allowed = TRUE 
        AND ef.excluded = FALSE 
        GROUP BY t.id, ef.nameField
        """)
List<ExtraFieldDAO> findByTicketProtocol(String protocol);

    @Procedure("protocolo_insere_campos_extras")
    void runInsertExtraFieldsProcedure(String protocol, String values);

}
