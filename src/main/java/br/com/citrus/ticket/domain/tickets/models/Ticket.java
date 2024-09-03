package br.com.citrus.ticket.domain.tickets.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.citrus.ticket.domain.extraFields.models.TicketExtraField;
import br.com.citrus.ticket.domain.tickets.enums.TicketStatus;
import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiInteraction;
import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

	private static final String RECLAME_AQUI = "RECLAME_AQUI";

	private UUID id;
	private String protocol;
	private String status;
	private LocalDateTime opening;
	private LocalDateTime startOpening;
	private LocalDateTime closure;
	private boolean automaticallyFinished;
	private boolean preTicket;
	private boolean viewed;
	private boolean visible;
	private String description;
	private TicketChannel ticketChannel;
	private UUID responsibleId;
	private String origin;
	private String subject;
	private String media;
	private String clientNameFw;
	private String clientEmailFw;
	private UUID destinationSectorId;
	private Ocurrency ocurrency;
	private List<TicketExtraField> extraFields = new ArrayList<>();

	public Ticket(String protocol) {
		this.protocol = protocol;
		this.opening = LocalDateTime.now();
		this.startOpening = this.opening;
		this.status = TicketStatus.OPEN.getLabel();
		this.preTicket = true;
		this.visible = false;
		this.closure = null;
	}

/*	public Ticket(String protocol, LocalDateTime creationDate) {
		this.protocol = protocol;
		this.opening = creationDate;  // Usando a data do payload
		this.startOpening = this.opening;
		this.status = TicketStatus.OPEN.getLabel();
		this.preTicket = true;
		this.visible = false;
		this.closure = null;
	}*/

	public void cancel() {
		this.visible = true;
		this.status = TicketStatus.CANCEL.getLabel();
		this.closure = LocalDateTime.now();
	}

	public void fillAttributesFromReclameAqui(ReclameAquiPayload dto, ReclameAquiInteraction interaction) {
		this.origin = dto.getUser().getEmail();
		this.subject = dto.getComplaintTitle();
		this.description = interaction.getMessage();
		this.clientNameFw = dto.getUser().getName();
		this.clientEmailFw = dto.getUser().getEmail();
		this.media = RECLAME_AQUI;
	}

/*	public void fillAttributesFromReclameAqui(ReclameAquiPayload dto, ReclameAquiInteraction interaction) {
		this.origin = dto.getUser().getEmail();
		this.subject = dto.getComplaintTitle();
		this.description = interaction.getMessage();
		this.clientNameFw = dto.getUser().getName();
		this.clientEmailFw = dto.getUser().getEmail();
		this.media = RECLAME_AQUI;
		this.opening = dto.getCreationDate(); // Usando a data do payload
	}*/

	public void ticketChannel(UUID canalAtendimento) {
		this.ticketChannel = new TicketChannel(canalAtendimento);
	}

}
