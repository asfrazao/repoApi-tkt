package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.UUID;

import br.com.citrus.ticket.domain.extraFields.models.TicketExtraField;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "atendimento_campo_extra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketExtraFieldSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "valor")
	private String value;

	@Column(name = "observacao")
	private String note;

	@Column(name = "label_option")
	private String optionLabel;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "grupo_campo_extra_campo_id")
	private ExtraFieldSchema extraField;

	@ManyToOne
	@JoinColumn(name = "atendimento_id")
	private TicketSchema ticket;

	TicketExtraField toModel() {
		return new TicketExtraField(
				this.id,
				this.value,
				this.note,
				this.optionLabel,
				this.extraField != null ? this.extraField.getId() : null,
				this.ticket != null ? this.ticket.getId() : null
		);
	}

	static TicketExtraFieldSchema fromSchema(TicketExtraField ticketExtraField) {
		return new TicketExtraFieldSchema(
				ticketExtraField.getId(),
				ticketExtraField.getValue(),
				ticketExtraField.getNote(),
				ticketExtraField.getOptionLabel(),
				ticketExtraField.getExtraFieldId() != null ? new ExtraFieldSchema(ticketExtraField.getExtraFieldId()) : null,
				ticketExtraField.getTicketId() != null ? new TicketSchema(ticketExtraField.getTicketId()) : null
		);
	}
}
