package br.com.citrus.ticket.domain.tickets.services.impl;

import br.com.citrus.ticket.domain.tickets.services.OpenTicketAttachment;
import br.com.citrus.ticket.domain.tickets.vo.attachment.AttachmentDto;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.AttachmentTicketJpaRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.AttachmentTicketSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OpenTicketAttachmentImpl implements OpenTicketAttachment {

    @Autowired private AttachmentTicketJpaRepository service;

    @Override
    public void save(AttachmentDto dto) {
        AttachmentTicketSchema attachment = new AttachmentTicketSchema();
        attachment.setAttachment(dto.getAttachment());
        attachment.setTicketId(dto.getTicketId());
        attachment.setCreatedAt(LocalDateTime.now());
        service.save(attachment);
    }

}
