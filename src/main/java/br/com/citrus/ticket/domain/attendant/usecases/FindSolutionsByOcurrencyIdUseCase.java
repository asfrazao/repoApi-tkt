package br.com.citrus.ticket.domain.attendant.usecases;

import java.util.List;

import br.com.citrus.ticket.domain.attendant.models.Solution;
import br.com.citrus.ticket.shared.exception.InvalidUUIDFormatException;

public interface FindSolutionsByOcurrencyIdUseCase {
    List<Solution> execute(String ocurrencyId) throws InvalidUUIDFormatException;
}
