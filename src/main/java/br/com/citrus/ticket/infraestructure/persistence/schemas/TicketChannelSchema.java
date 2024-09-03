package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketChannel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "canal_atendimento")
public class TicketChannelSchema {

	@Id
	private UUID id;
	
	@Column(name = "nome")
	private String name;

	@Column(name = "codigo")
	private String code;

	@Column(name = "icone")
	private String icon;

	@Column(name = "cor")
	private String color;

	@Column(name = "descricao")
	private String description;

	@Column(name = "sistema")
	private boolean system;
	
	@Column(name = "codigo_protocolo_atendimento")
	private String ticketProtocolCode;


	public TicketChannelSchema(UUID ticketChannelId) {
		this.id = ticketChannelId;
	}
	
	public TicketChannel toModel() {
		return new TicketChannel(this.id, this.name, this.code, this.description, this.icon, this.color, this.system, this.ticketProtocolCode);
	}
}
