package br.com.citrus.ticket.infraestructure.web.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.IgPostClient;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IgPostClientDto {

	private String name;
	
	@JsonProperty(value = "external_id")
	private String externalId;

	public IgPostClient toValueObject() {
		return new IgPostClient(this.name, this.externalId);
	}
}
