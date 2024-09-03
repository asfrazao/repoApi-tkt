package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.List;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.Ocurrency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ocorrencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcurrencySchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "nome")
	private String name;

	@Column(name = "observacao")
	private String note;

	@Column(name = "codigo")
	private String code;

	@Column(name = "sla")
	private Integer sla;

	@Column(name = "unidade_tempo_sla")
	private String slaTimeUnit;

	@Column(name = "ativo")
	private String active;

	@ManyToOne
	@JoinColumn(name = "grupo_campos_extra_id")
	@ToString.Exclude private ExtraFieldGroupSchema extraFieldGroup;

	@OneToMany(mappedBy = "ocurrency")
	@ToString.Exclude private List<TicketSchema> tickets;

	@ManyToOne
	@JoinColumn(name = "tipo_ocorrencia_id")
	private OcurrencyTypeSchema ocurrencyType;


	public Ocurrency toModel() {
		return new Ocurrency(this.id, this.name, this.ocurrencyType);
	}
}
