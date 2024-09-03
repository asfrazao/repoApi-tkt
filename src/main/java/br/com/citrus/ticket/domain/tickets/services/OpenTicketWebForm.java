package br.com.citrus.ticket.domain.tickets.services;

import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.vo.webForm.WebFormTicketDTO;

import java.util.UUID;

public interface OpenTicketWebForm {

	Ticket execute(UUID webFormId, WebFormTicketDTO payload);
}
