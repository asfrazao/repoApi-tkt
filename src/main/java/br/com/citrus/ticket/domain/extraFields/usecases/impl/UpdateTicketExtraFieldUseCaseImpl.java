package br.com.citrus.ticket.domain.extraFields.usecases.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.extraFields.gateway.TicketExtraFieldGateway;
import br.com.citrus.ticket.domain.extraFields.models.TicketExtraField;
import br.com.citrus.ticket.domain.extraFields.usecases.UpdateTicketExtraFieldUseCase;
import br.com.citrus.ticket.shared.exception.InvalidUUIDFormatException;
import br.com.citrus.ticket.shared.utils.Helper;

@Service
public class UpdateTicketExtraFieldUseCaseImpl implements UpdateTicketExtraFieldUseCase {

    private static Logger log = LoggerFactory.getLogger(UpdateTicketExtraFieldUseCaseImpl.class);

    @Autowired
    private TicketExtraFieldGateway gateway;

    @Override
    public void execute(TicketExtraField ticketExtraField){
        if (!Helper.isUUIDValid(ticketExtraField.getExtraFieldId().toString())
            || !Helper.isUUIDValid(ticketExtraField.getTicketId().toString())) {
            try {
                throw new InvalidUUIDFormatException();
            } catch (InvalidUUIDFormatException e) {
                log.error(e.getMessage());
            }
        }

        try {
            gateway.update(ticketExtraField);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
