package br.com.citrus.ticket.domain.tickets.services;

import br.com.citrus.ticket.domain.tickets.vo.attachment.AttachmentDto;

public interface OpenTicketAttachment {

	void save(AttachmentDto dto);
}
