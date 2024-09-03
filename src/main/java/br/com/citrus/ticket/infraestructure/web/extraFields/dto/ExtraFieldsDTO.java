package br.com.citrus.ticket.infraestructure.web.extraFields.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraFieldsDTO {
    private String name;
    private String value;
}
