package br.com.citrus.ticket.domain.extraFields.usecases;

import br.com.citrus.ticket.domain.extraFields.models.TicketExtraField;

public interface UpdateTicketExtraFieldUseCase {
    void execute(TicketExtraField ticketExtraField);
}
