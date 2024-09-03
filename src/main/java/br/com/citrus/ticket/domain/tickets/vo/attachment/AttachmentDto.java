package br.com.citrus.ticket.domain.tickets.vo.attachment;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttachmentDto {
    private UUID ticketId;
    private String attachment;
}
