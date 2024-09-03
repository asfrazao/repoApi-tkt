package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.attendant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.attendant.gateways.AttendantGateway;
import br.com.citrus.ticket.domain.attendant.models.Solution;
import br.com.citrus.ticket.infraestructure.persistence.schemas.SolutionSchema;

@Component
public class AttendantJpaGateway implements AttendantGateway {

    @Autowired
    private AttendantJpaRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Solution> findSolutionsByOcurrencyId(UUID ocurrencyId) {
        List<Solution> solutions = new ArrayList<>();
        List<SolutionSchema> schema = repository.findByOcurrencyId(ocurrencyId);
        if(!schema.isEmpty()){
            solutions = schema.stream()
                        .map(solution -> mapper.map(solution, Solution.class))
                        .toList();
        }

        return solutions;
    }
    
}
