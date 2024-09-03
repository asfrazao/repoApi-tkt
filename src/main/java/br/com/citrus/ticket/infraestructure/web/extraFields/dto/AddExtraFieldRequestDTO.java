package br.com.citrus.ticket.infraestructure.web.extraFields.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddExtraFieldRequestDTO {

    @NotNull
    private String ticket;

    @NotEmpty
    private List<ExtraFieldsDTO> fields;
}
