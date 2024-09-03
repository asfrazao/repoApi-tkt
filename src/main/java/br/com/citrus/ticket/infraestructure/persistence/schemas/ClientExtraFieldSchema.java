package br.com.citrus.ticket.infraestructure.persistence.schemas;

import br.com.citrus.ticket.domain.extraFields.models.ClientExtraField;
import br.com.citrus.ticket.domain.extraFields.models.TicketExtraField;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cliente_campo_extra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientExtraFieldSchema {

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

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private ClientSchema client;

	TicketExtraField toModel() {
		return new TicketExtraField(
				this.id,
				this.value,
				this.note,
				this.optionLabel,
				this.extraField != null ? this.extraField.getId() : null,
				this.client != null ? this.client.getId() : null
		);
	}

	static ClientExtraFieldSchema fromSchema(ClientExtraField clientExtraField) {
		return new ClientExtraFieldSchema(
				clientExtraField.getId(),
				clientExtraField.getValue(),
				clientExtraField.getNote(),
				clientExtraField.getOptionLabel(),
				clientExtraField.getExtraFieldId() != null ? new ExtraFieldSchema(clientExtraField.getExtraFieldId()) : null,
				clientExtraField.getClientId() != null ? new ClientSchema(clientExtraField.getClientId()) : null
		);
	}
}
