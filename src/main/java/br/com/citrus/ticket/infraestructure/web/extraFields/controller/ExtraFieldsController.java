package br.com.citrus.ticket.infraestructure.web.extraFields.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.citrus.ticket.domain.extraFields.models.ExtraField;
import br.com.citrus.ticket.domain.extraFields.usecases.AddValuesInExtraFieldsUseCase;
import br.com.citrus.ticket.infraestructure.web.extraFields.dto.AddExtraFieldRequestDTO;
import br.com.citrus.ticket.shared.constants.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = RestConstants.API_BASE_URI
        + RestConstants.TICKET_MODULE
        + RestConstants.EXFIELDS_ENDPOINT,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ExtraFieldsController {
    @Autowired
    private AddValuesInExtraFieldsUseCase addValuesInExtraFieldsUseCase;

    @Autowired
    private ModelMapper mapper;

    @PutMapping
    @Operation(summary = "Update extra fields in a ticket by passing it's name(s) and value(s)", tags="ExtraFields")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),            
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })
    public void addValuesInExtraFields(@Valid @RequestBody AddExtraFieldRequestDTO request){
        List<ExtraField> fields = request.getFields().stream().map(field -> mapper.map(field, ExtraField.class)).toList();
        addValuesInExtraFieldsUseCase.execute(request.getTicket(), fields);
    }
}
