package br.com.citrus.ticket.infraestructure.web.procedures.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDTO {

    private String id;
    
    @JsonProperty("nome")
    private String name;

    private boolean selected;
}
