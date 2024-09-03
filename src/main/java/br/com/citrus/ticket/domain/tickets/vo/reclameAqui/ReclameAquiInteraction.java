package br.com.citrus.ticket.domain.tickets.vo.reclameAqui;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReclameAquiInteraction {

	@JsonProperty("creation_date")
	private LocalDateTime creationDate;

	private String message;

	private boolean response;

	@JsonProperty("ticket_interaction_id")
	private String ticketInteractionId;

	@JsonProperty("ticket_interaction_name")
	private String ticketInteractionName;
}
