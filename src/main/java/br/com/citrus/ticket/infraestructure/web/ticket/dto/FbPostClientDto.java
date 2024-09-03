package br.com.citrus.ticket.infraestructure.web.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.FbPostClient;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FbPostClientDto {

	private String name;
	
	@JsonProperty(value = "external_id")
	private String externalId;

	public FbPostClient toValueObject() {
		return new FbPostClient(this.name, this.externalId);
	}
}
