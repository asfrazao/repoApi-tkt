package br.com.citrus.ticket.domain.tickets.services;

import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiPayload;

public interface OpenTicketReclameAqui {

	void execute(ReclameAquiPayload dto) throws Exception;

	void update(ReclameAquiPayload dto) throws Exception;
}
