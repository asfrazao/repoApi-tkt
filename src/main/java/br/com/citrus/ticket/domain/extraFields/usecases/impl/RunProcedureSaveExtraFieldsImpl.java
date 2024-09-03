package br.com.citrus.ticket.domain.extraFields.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.citrus.ticket.domain.extraFields.gateway.ExtraFieldGateway;
import br.com.citrus.ticket.domain.extraFields.usecases.RunProcedureSaveExtraFields;

public class RunProcedureSaveExtraFieldsImpl implements RunProcedureSaveExtraFields{

    @Autowired
    private ExtraFieldGateway gateway;

    @Override
    public void execute(String protocol, String values) {
        gateway.runProcedureInsertValuesInExtraFields(protocol, values);
    }
    
}
