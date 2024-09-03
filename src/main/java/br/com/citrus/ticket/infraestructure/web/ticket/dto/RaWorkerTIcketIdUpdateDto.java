package br.com.citrus.ticket.infraestructure.web.ticket.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class RaWorkerTIcketIdUpdateDto implements Serializable {

	private static final long serialVersionUID = -2084191826221849201L;

	private static final String PROTOCOL_IS_REQUIRED = "protocolo é obrigatório";

	@JsonProperty(value = "protocol")
	private String protocol;

	public RaWorkerTIcketIdUpdateDto(String protocol) throws Exception {
		if (protocol == null || protocol.isEmpty() || protocol.isBlank()) {
			throw new Exception(PROTOCOL_IS_REQUIRED);
		}
		this.protocol = protocol;
	}

}
