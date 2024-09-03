package br.com.citrus.ticket.domain.tickets.enums;

import lombok.Getter;

public enum ProtocolFormat {

	DATA_JULIANA("DATA_JULIANA"),
	REGISTRO_DATA("REGISTRO_DATA"),
	CANAL_DATA_JULIANA("CANAL_DATA_JULIANA");

	@Getter
	private String label;
	
	ProtocolFormat(String label) {
		this.label = label;
	}
	
}
