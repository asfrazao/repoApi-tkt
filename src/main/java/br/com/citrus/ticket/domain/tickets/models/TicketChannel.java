package br.com.citrus.ticket.domain.tickets.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketChannel {

	private UUID id;
	private String name;
	private String code;
	private String description;
	private String icon;
	private String color;
	private boolean system;
	private String ticketProtocolCode;


	public TicketChannel(UUID ticketChannelId) {
		this.id = ticketChannelId;
	}
}
