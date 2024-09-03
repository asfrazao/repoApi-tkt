package br.com.citrus.ticket.domain.tickets.models;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TicketInstagramPostMedia {
  
	private UUID id;
	private String igMediaName;
	private UUID ticketInstagramPostId;
}
