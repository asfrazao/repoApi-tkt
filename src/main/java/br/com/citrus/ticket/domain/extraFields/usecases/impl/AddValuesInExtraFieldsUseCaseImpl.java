package br.com.citrus.ticket.domain.extraFields.usecases.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.extraFields.models.ExtraField;
import br.com.citrus.ticket.domain.extraFields.models.TicketExtraField;
import br.com.citrus.ticket.domain.extraFields.usecases.AddValuesInExtraFieldsUseCase;
import br.com.citrus.ticket.domain.extraFields.usecases.ListExtraFieldsByNameAndTicketProtocolUseCase;
import br.com.citrus.ticket.domain.extraFields.usecases.ListExtraFieldsByTicketProtocolUseCase;
import br.com.citrus.ticket.domain.extraFields.usecases.SaveTicketExtraFieldsUseCase;
import br.com.citrus.ticket.domain.extraFields.usecases.UpdateTicketExtraFieldUseCase;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.usecases.FindTicketByProtocolUseCase;

@Service
public class AddValuesInExtraFieldsUseCaseImpl implements AddValuesInExtraFieldsUseCase {

    @Autowired
    private ListExtraFieldsByNameAndTicketProtocolUseCase listExtraFieldsByNameAndTicketProtocolUseCase;

    @Autowired
    private ListExtraFieldsByTicketProtocolUseCase listExtraFieldsByTicketProtocolUseCase;

    @Autowired
    private UpdateTicketExtraFieldUseCase updateExtraFieldsUseCase;

    @Autowired
    private  FindTicketByProtocolUseCase findTicketByProtocolUseCase;

    @Autowired
    private SaveTicketExtraFieldsUseCase saveTicketExtraFieldsUseCase;

    @Override
    public void execute(String protocol, List<ExtraField> receivedExtraFields) {

        List<ExtraField> existentsExtraFields = new ArrayList<>();

        Optional<Ticket> ticket = findTicketByProtocolUseCase.execute(protocol);
        Ticket ticketFounded = ticket.get();

        List<ExtraField> ticketsExtraFields = listExtraFieldsByTicketProtocolUseCase.execute(ticketFounded.getProtocol());

        if(!ticketsExtraFields.isEmpty()){
            ticketsExtraFields.forEach(extraField -> {
                TicketExtraField ticketExtraField = new TicketExtraField();
                ticketExtraField.setExtraFieldId(extraField.getId());
                ticketExtraField.setTicketId(extraField.getTicketId());
                ticketExtraField.setValue(extraField.getValue());

                saveTicketExtraFieldsUseCase.execute(ticketExtraField);
            });
        }

        receivedExtraFields.forEach(extraField -> {
            extraField.setTicketId(ticketFounded.getId());
            List<ExtraField> fields = listExtraFieldsByNameAndTicketProtocolUseCase.execute(extraField.getName(), ticketFounded.getProtocol());
            fields.stream().forEach(f -> f.setValue(extraField.getValue()));
            existentsExtraFields.addAll(fields);
        });

        if (!existentsExtraFields.isEmpty()) {
            existentsExtraFields.forEach(extraField -> {
                TicketExtraField ticketExtraField = new TicketExtraField();
                ticketExtraField.setExtraFieldId(extraField.getId());
                ticketExtraField.setTicketId(extraField.getTicketId());
                ticketExtraField.setValue(extraField.getValue());

                updateExtraFieldsUseCase.execute(ticketExtraField);
            });
        }

    }
}
