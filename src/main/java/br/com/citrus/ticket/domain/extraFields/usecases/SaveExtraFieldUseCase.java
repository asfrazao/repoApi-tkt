package br.com.citrus.ticket.domain.extraFields.usecases;

import br.com.citrus.ticket.domain.extraFields.models.ExtraField;

public interface SaveExtraFieldUseCase {
    public void execute(ExtraField extraField);
}
