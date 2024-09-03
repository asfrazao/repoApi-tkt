package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketChannel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "canal_atendimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelTicketSchema {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String name;

    @Column(name = "codigo")
    private String code;

    @Column(name = "descricao")
    private String description;

    @Column(name = "icone")
    private String icon;

    @Column(name = "cor")
    private String color;

    @ManyToOne
    @JoinColumn(name = "formulario_id")
    private FormSchema form;

    @Column(name = "sistema")
    private boolean system;

    @Column(name = "codigo_protocolo_atendimento")
    private String ticketProtocolCode;


    public ChannelTicketSchema(TicketChannel ticketChannel) {
        this.id = ticketChannel.getId();
        this.name = ticketChannel.getName();
        this.ticketProtocolCode = ticketChannel.getTicketProtocolCode();
        this.code = ticketChannel.getCode();
        this.description = ticketChannel.getDescription();
        this.icon = ticketChannel.getIcon();
        this.color = ticketChannel.getColor();
        this.system = ticketChannel.isSystem();
    }

    public ChannelTicketSchema(UUID id) {
        this.id = id;
    }
}

