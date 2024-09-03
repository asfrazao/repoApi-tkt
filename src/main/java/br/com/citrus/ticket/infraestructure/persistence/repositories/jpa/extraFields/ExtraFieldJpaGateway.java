package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.extraFields;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.extraFields.gateway.ExtraFieldGateway;
import br.com.citrus.ticket.domain.extraFields.models.ExtraField;
import br.com.citrus.ticket.infraestructure.persistence.dao.ExtraFieldDAO;

@Component
public class ExtraFieldJpaGateway implements ExtraFieldGateway {

    @Autowired
    private ExtraFieldJpaRepository repository;

    @Override
    public List<ExtraField> findByNameAndTicketProtocol(String name, String ticketProtocol) {
        
        List<ExtraFieldDAO> fields = repository.findByNameAndTicketProtocol(name, ticketProtocol);
        List<ExtraField> extraFields = new ArrayList<>();

        fields.forEach(field -> {
            extraFields.add(
                new ExtraField(UUID.fromString(field.getId()), UUID.fromString(field.getTicketId()), field.getName(), field.getValue())
            );
        });

        return extraFields;
    }

    @Override
    public ExtraField save(ExtraField extraField) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void runProcedureInsertValuesInExtraFields(String protocol, String values) {
        repository.runInsertExtraFieldsProcedure(protocol, values);
    }

    @Override
    public List<ExtraField> findByTicketProtocol(String ticketProtocol) {
        List<ExtraFieldDAO> dao = repository.findByTicketProtocol(ticketProtocol);

         List<ExtraField> extraFields = new ArrayList<>();

        dao.forEach(field -> {
            extraFields.add(
                new ExtraField(UUID.fromString(field.getId()), UUID.fromString(field.getTicketId()), field.getName(), field.getValue())
            );
        });

        return extraFields;
    }

    
}
