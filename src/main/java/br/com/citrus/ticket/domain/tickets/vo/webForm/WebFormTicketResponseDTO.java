package br.com.citrus.ticket.domain.tickets.vo.webForm;

import br.com.citrus.ticket.domain.tickets.models.Ticket;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebFormTicketResponseDTO {

    private static final String IDENTIFIER = "CO";


    String protocol;

    public WebFormTicketResponseDTO mapTicketToResponse(Ticket ticket) {
        return WebFormTicketResponseDTO.builder()
                .protocol(ticket.getProtocol())
                .build();
    }
}
