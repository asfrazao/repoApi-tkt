package br.com.citrus.ticket.domain.attendant.usecases.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.attendant.gateways.AttendantGateway;
import br.com.citrus.ticket.domain.attendant.models.Solution;
import br.com.citrus.ticket.domain.attendant.usecases.FindSolutionsByOcurrencyIdUseCase;
import br.com.citrus.ticket.shared.exception.InvalidUUIDFormatException;
import br.com.citrus.ticket.shared.utils.Helper;

@Service
public class FindSolutionsByOcurrencyIdUseCaseImpl implements FindSolutionsByOcurrencyIdUseCase {
    private static Logger log = LoggerFactory.getLogger(FindSolutionsByOcurrencyIdUseCaseImpl.class);

    @Autowired
    private AttendantGateway gateway;

    public List<Solution> execute(String ocurrencyId) throws InvalidUUIDFormatException{
        log.info("ocurrencyId receveid: " + ocurrencyId);
        if(!Helper.isUUIDValid(ocurrencyId)){
            InvalidUUIDFormatException error = new InvalidUUIDFormatException();
            log.error(error.getMessage());  
            throw error;
        }

        List<Solution> solutions = new ArrayList<>();

        try {
            solutions = gateway.findSolutionsByOcurrencyId(UUID.fromString(ocurrencyId));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return solutions;
    }
}
