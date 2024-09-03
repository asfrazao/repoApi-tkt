package br.com.citrus.ticket.infraestructure.web.attendant.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.citrus.ticket.domain.attendant.models.Solution;
import br.com.citrus.ticket.domain.attendant.usecases.FindSolutionsByOcurrencyIdUseCase;
import br.com.citrus.ticket.infraestructure.web.attendant.dto.SolutionDTO;
import br.com.citrus.ticket.shared.constants.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(RestConstants.API_BASE_URI + RestConstants.TICKET_MODULE + RestConstants.ATTENDAT_ENDPOINT)
public class AttendantController {

	@Autowired
	private FindSolutionsByOcurrencyIdUseCase findSolutionsByOcurrencyIdUseCase;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/solutions/{ocurrencyId}")
	@Operation(summary = "Get solutions by an ocurrency id", tags = "Attendant")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SolutionDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	List<SolutionDTO> findSolutionsByOcurrencyId(@PathVariable String ocurrencyId) {
		List<SolutionDTO> dto = new ArrayList<>();
		List<Solution> solutions = findSolutionsByOcurrencyIdUseCase.execute(ocurrencyId);
		if (!solutions.isEmpty()) {
			dto = solutions.stream().map(solution -> {
				SolutionDTO sol = mapper.map(solution, SolutionDTO.class);
				sol.setFormattedEstimatedTime(solution.getEstimatedTime() + " " + solution.getTimeUnit() + "(s)");
				return sol;
			}).toList();
		}
		return dto;
	}
}
