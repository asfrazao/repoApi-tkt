package br.com.citrus.ticket.domain.tickets.vo.reclameAqui;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReclameAquiAttached {

	@JsonProperty("original_link")
	private String originalLink;

	@JsonProperty("id_ra")
	private String idRa;
}
