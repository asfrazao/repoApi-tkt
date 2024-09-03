package br.com.citrus.ticket.domain.extraFields.gateway;

import br.com.citrus.ticket.domain.extraFields.models.TicketExtraField;

public interface TicketExtraFieldGateway {
    void save(TicketExtraField field);
    void update(TicketExtraField field);
}
