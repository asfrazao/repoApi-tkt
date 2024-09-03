package br.com.citrus.ticket.domain.attendant.gateways;

import java.util.List;
import java.util.UUID;

import br.com.citrus.ticket.domain.attendant.models.Solution;

public interface AttendantGateway {
    List<Solution> findSolutionsByOcurrencyId(UUID ocurrencyId);
}
