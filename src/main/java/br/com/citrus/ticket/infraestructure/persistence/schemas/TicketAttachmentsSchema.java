package br.com.citrus.ticket.infraestructure.persistence.schemas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "atendimento_anexos")
@Getter
@Setter
public class TicketAttachmentsSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "atendimento_id")
	private TicketSchema ticket;

	@Column(name = "anexo")
	private String attachment;

	@Column(name = "usuario_id")
	private UUID userId;

	@Column(name = "data")
	private LocalDateTime registerDate;

}