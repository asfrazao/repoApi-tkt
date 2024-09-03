package br.com.citrus.ticket.domain.extraFields.gateway;

import java.util.List;

import br.com.citrus.ticket.domain.extraFields.models.ExtraField;

public interface ExtraFieldGateway {
    List<ExtraField> findByNameAndTicketProtocol(String name, String ticketProtocol);
    List<ExtraField> findByTicketProtocol(String ticketProtocol);
    ExtraField save(ExtraField extraField);
    void runProcedureInsertValuesInExtraFields(String protocol, String values);
}
