package br.com.citrus.ticket.domain.tickets.vo.reclameAqui;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReclameAquiUserDto {

	private String email;

	@JsonProperty("external_id")
	private String externalId;

	private String name;

	private String type;
}
