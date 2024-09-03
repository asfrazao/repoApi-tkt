package br.com.citrus.ticket.domain.tickets.gateways;

import br.com.citrus.ticket.domain.tickets.models.Client;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiUserDto;

public interface ClientGateway {

	Client searchOrCreateUserToSaveTicket(ReclameAquiUserDto user, Ticket ticket, String clientTypeCode, String regionName);

}
