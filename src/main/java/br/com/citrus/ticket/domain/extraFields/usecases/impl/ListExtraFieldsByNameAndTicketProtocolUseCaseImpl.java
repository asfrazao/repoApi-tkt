package br.com.citrus.ticket.domain.extraFields.usecases.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.extraFields.gateway.ExtraFieldGateway;
import br.com.citrus.ticket.domain.extraFields.models.ExtraField;
import br.com.citrus.ticket.domain.extraFields.usecases.ListExtraFieldsByNameAndTicketProtocolUseCase;

@Service
public class ListExtraFieldsByNameAndTicketProtocolUseCaseImpl implements ListExtraFieldsByNameAndTicketProtocolUseCase {

    @Autowired
    private ExtraFieldGateway gateway;

    @Override
    public List<ExtraField> execute(String extraFieldName, String protocol) {
        return gateway.findByNameAndTicketProtocol(extraFieldName, protocol);
    }
    
}
