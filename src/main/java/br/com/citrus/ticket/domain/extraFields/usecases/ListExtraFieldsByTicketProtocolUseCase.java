package br.com.citrus.ticket.domain.extraFields.usecases;

import java.util.List;

import br.com.citrus.ticket.domain.extraFields.models.ExtraField;

public interface ListExtraFieldsByTicketProtocolUseCase {
    List<ExtraField> execute(String protocol);
}
