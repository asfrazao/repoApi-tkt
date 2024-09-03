package br.com.citrus.ticket.domain.tickets.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IgPostClient {

	private String name;
	private String externalId;

	public IgPostClient(String name, String externalId) {
		this.name = name;
		this.externalId = externalId;
	}

}
