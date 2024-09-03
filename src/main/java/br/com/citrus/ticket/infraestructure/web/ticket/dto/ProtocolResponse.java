package br.com.citrus.ticket.infraestructure.web.ticket.dto;

import lombok.Getter;

public class ProtocolResponse {

	@Getter
	private String protocol;

	public ProtocolResponse(String protocol) {
		super();
		this.protocol = protocol;
	}

}
