package br.com.citrus.ticket.domain.extraFields.usecases.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.extraFields.gateway.ExtraFieldGateway;
import br.com.citrus.ticket.domain.extraFields.models.ExtraField;
import br.com.citrus.ticket.domain.extraFields.usecases.SaveExtraFieldUseCase;
import br.com.citrus.ticket.shared.exception.InvalidUUIDFormatException;
import br.com.citrus.ticket.shared.utils.Helper;

@Service
public class SaveExtraFieldUseCaseUseCaseImpl implements SaveExtraFieldUseCase {

    @Autowired
    private ExtraFieldGateway gateway;
    private static Logger log = LoggerFactory.getLogger(SaveExtraFieldUseCaseUseCaseImpl.class);

    public void execute(ExtraField extraField){
        if (!Helper.isUUIDValid(extraField.getId().toString())
            || !Helper.isUUIDValid(extraField.getTicketId().toString())) {
            try {
                throw new InvalidUUIDFormatException();
            } catch (InvalidUUIDFormatException e) {
                log.error(e.getMessage());
            }
        }

        try {
            gateway.save(extraField);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }
}
