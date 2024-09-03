package br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketIssueReclameAqui;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "atendimento_reclame_aqui_issue")
public class TicketIssueReclameAquiSchema {

	@Id
	private UUID id;

	private String raId;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	private String message;

	private boolean response;

	@Column(name = "ticket_interaction_id")
	private String ticketInteractionId;

	@Column(name = "ticket_interaction_name")
	private String ticketInteractionName;

	@Column(name = "atendimento_id")
	private UUID ticketId;

	@Column(name = "client_id_worker")
	private String clientIdWorker;

	@Column(name = "user_id_worker")
	private String userIdWorker;

	public TicketIssueReclameAquiSchema(TicketIssueReclameAqui issue) {
		this.id = issue.getId();
		this.raId = issue.getRaId();
		this.creationDate = issue.getCreationDate();
		this.message = issue.getMessage();
		this.response = issue.isResponse();
		this.ticketInteractionId = issue.getTicketInteractionId();
		this.ticketInteractionName = issue.getTicketInteractionName();
		this.ticketId = issue.getTicketId();
		this.clientIdWorker = issue.getClientIdWorker().toString();
		this.userIdWorker = issue.getUserIdWorker().toString();
	}

	public TicketIssueReclameAqui toModel() {
		return new TicketIssueReclameAqui(this.id, this.raId, this.creationDate, this.message, this.response,
				this.ticketInteractionId, this.ticketInteractionName, this.ticketId,
				UUID.fromString(this.clientIdWorker), UUID.fromString(this.userIdWorker));
	}
}
