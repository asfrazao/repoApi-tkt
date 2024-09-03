package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.ticketsExtraFields;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.citrus.ticket.infraestructure.persistence.schemas.TicketExtraFieldSchema;

import java.util.UUID;

public interface TicketExtraFieldJpaRepository  extends JpaRepository<TicketExtraFieldSchema, UUID>{

    @Query(value="""
                    INSERT INTO atendimento_campo_extra (id, valor, grupo_campo_extra_campo_id, atendimento_id) \
                    VALUES (:id, :valor, :grupo_campo_extra_campo_id, :atendimento_id)\
                    """, 
            nativeQuery = true)
    void saveTicketExtraField(@Param("id") UUID id, 
                                @Param("valor") String value, 
                                @Param("grupo_campo_extra_campo_id") UUID extraFieldId,
                                @Param("atendimento_id") UUID ticketId
                                );

    @Modifying
    @Query(value = """
                    UPDATE TicketExtraFieldSchema t SET t.value = :value \
                    WHERE t.extraField.id = :extraFieldId \
                    AND t.ticket.id = :ticketId\
                    """)
    void updateTicketExtraField(@Param("value") String value, 
                                @Param("ticketId") UUID ticketId, 
                                @Param("extraFieldId") UUID extraFieldId);
    
}
