package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.citrus.ticket.domain.tickets.enums.TicketStatus;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "atendimento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "protocolo")
	private String protocol;

	private String status;

	@Column(name = "visivel")
	private boolean visible;

	@ManyToOne
	@JoinColumn(name = "ocorrencia_id")
	private OcurrencySchema ocurrency;

	@ManyToOne
	@JoinColumn(name = "canal_atendimento_id")
	private TicketChannelSchema ticketChannel;

	@Column(name = "setor_destino_id")
	private UUID destinationSectorId;

	@Column(name = "setor_origem_id")
	private UUID originSectorId;

	@Column(name = "abertura")
	private LocalDateTime opening;

	@Column(name = "inicio_abertura")
	private LocalDateTime startOpening;

	@Column(name = "fechamento")
	private LocalDateTime closure;

	@Column(name = "finalizado_automatico")
	private boolean automaticallyFinished;

	@Column(name = "pre_atendimento")
	private boolean preTicket;

	@Column(name = "visualizado")
	private boolean viewed;

	@Column(name = "descricao")
	private String description;

	@Column(name = "responsavel_id")
	private UUID responsibleId;

	@Column(name = "criador_id")
	private UUID creatorId;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private ClientSchema client;

	@Column(name = "conta_origem_pre_atendimento")
	private String origin;

	@Column(name = "assunto_email_pre_atendimento")
	private String subject;

	@Column(name = "midia")
	private String media;

	@Column(name = "cliente_nome_fw")
	private String clientNameFw;

	@Column(name = "cliente_email_fw")
	private String clientEmailFw;

	@Column(name = "resolvido")
	private LocalDateTime resolved;

	@Column(name = "sla")
	private Integer sla;

	@Column(name = "sla_passado")
	private Integer lastSla;

	@ManyToOne
	@JoinColumn(name = "solucao_id")
	private SolutionSchema solution;

	@ToString.Exclude
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketExtraFieldSchema> extraFields = new ArrayList<>();

	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketAttachmentsSchema> attachments = new ArrayList<>();


	public TicketSchema(UUID id) {
		this.id = id;
	}

	public TicketSchema(Ticket ticket) {
		if (ticket.getId() != null) {
			id = ticket.getId();
		}
		protocol = ticket.getProtocol();
		status = ticket.getStatus();
		automaticallyFinished = ticket.isAutomaticallyFinished();
		opening = ticket.getOpening();
		startOpening = ticket.getStartOpening();
		preTicket = ticket.isPreTicket();
		viewed = ticket.isViewed();
		visible = ticket.isVisible();
		closure = ticket.getClosure();
		description = ticket.getDescription();
		responsibleId = ticket.getResponsibleId();
		origin = ticket.getOrigin();
		subject = ticket.getSubject();
		media = ticket.getMedia();
		clientNameFw = ticket.getClientNameFw();
		clientEmailFw = ticket.getClientEmailFw();
		extraFields = ticket.getExtraFields().stream()
				.map(TicketExtraFieldSchema::fromSchema)
				.collect(Collectors.toList());
	}

	// Método que converte TicketSchema para Ticket
	public Ticket toModel() {
		var ticketChannel = this.ticketChannel != null ? this.ticketChannel.toModel() : null;
		return new Ticket(this.id, this.protocol, this.status, this.opening, this.startOpening, this.closure,
				this.automaticallyFinished, this.preTicket, this.viewed, this.visible, this.description, ticketChannel,
				this.responsibleId, this.origin, this.subject, this.media, this.clientNameFw, this.clientEmailFw, this.destinationSectorId,
				this.ocurrency != null ? this.ocurrency.toModel() : null,
				this.extraFields.stream()
						.map(TicketExtraFieldSchema::toModel)
						.collect(Collectors.toList()));
	}

	public TicketSchema(String protocol) {
		this.protocol = protocol;
		this.opening = LocalDateTime.now();
		this.startOpening = this.opening;
		this.status = TicketStatus.OPEN.getLabel();
		this.preTicket = false;
		this.visible = true;
		this.closure = null;
	}

}