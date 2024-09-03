package br.com.citrus.ticket.domain.tickets.models;

import br.com.citrus.ticket.infraestructure.persistence.schemas.OcurrencyTypeSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Ocurrency {

	private UUID id;
	private String name;
	private OcurrencyTypeSchema ocurrencyType;

	public Ocurrency(UUID ticketChannelId) {
		this.id = ticketChannelId;
	}
}
