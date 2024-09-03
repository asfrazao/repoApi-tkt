package br.com.citrus.ticket.infraestructure.web.attendant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionDTO {

	@JsonProperty("nome")
	private String name;

	@JsonProperty("observacao")
	private String note;

	@JsonProperty("tempoEstimado")
	private int estimatedTime;

	@JsonProperty("tempoEstimadoFormatado")
	private String formattedEstimatedTime;

	@JsonProperty("ocorrenciaNome")
	private String ocurrencyName;

	private boolean selected;
}
