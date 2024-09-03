package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.citrus.ticket.domain.extraFields.models.FieldExtraField;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grupo_campo_extra_campo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraFieldSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "posicao")
	private int position;

	@Column(name = "label")
	private String label;

	@Column(name = "dica")
	private String tip;

	@Column(name = "opcional")
	private boolean optional;

	@Column(name = "obrigatorio")
	private boolean required;

	@Column(name = "modifica_sla_atendimento")
	private boolean modifySLATicket;

	@Column(name = "tipo_campo")
	private String fieldType;

	@Column(name = "nome_variavel")
	private String nameField;

	@Column(name = "habilitado")
	private boolean allowed;

	@Column(name = "excluido")
	private boolean excluded;

	@Column(name = "atributo_retorno_json")
	private String attributeReturnJson;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "grupo_campo_extra_id")
	private ExtraFieldGroupSchema extraFieldGroup;

	@ToString.Exclude
	@OneToMany(mappedBy = "extraField", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketExtraFieldSchema> ticketExtraFields = new ArrayList<>();

	@ToString.Exclude
	@OneToMany(mappedBy = "extraField", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClientExtraFieldSchema> clientExtraFields = new ArrayList<>();

	public ExtraFieldSchema(UUID id) {
		this.id = id;
	}

	public FieldExtraField toModel() {
		return new FieldExtraField(
			this.id,
			this.position,
			this.label,
			this.tip,
			this.optional,
			this.required,
			this.modifySLATicket,
			this.fieldType,
			this.nameField,
			this.allowed,
			this.excluded,
			this.extraFieldGroup != null ? this.extraFieldGroup.toModel() : null, this.ticketExtraFields.stream().map(TicketExtraFieldSchema::toModel).collect(Collectors.toList())  // Use collect(Collectors.toList())
		);
	}
}
