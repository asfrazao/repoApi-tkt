package br.com.citrus.ticket.domain.tickets.usecases;

import java.util.UUID;

public interface GenerateProcotolNumberUseCase {

	String execute(UUID ticketChannelId);

}
