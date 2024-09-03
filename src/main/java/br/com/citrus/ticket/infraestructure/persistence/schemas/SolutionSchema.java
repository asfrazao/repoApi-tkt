package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "solucao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionSchema {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "nome")
	private String name;

	@Column(name = "observacao")
	private String note;

	@Column(name = "descricao")
	private String description;

	@Column(name = "tempo_estimado")
	private int estimatedTime;

	@Column(name = "unidade_tempo_tempo_estimado")
	private String timeUnit;

	@Column(name = "fluxo_alternativo")
	private boolean alternativeFlow;

	@Column(name = "ativo")
	private boolean active;

	@Column(name = "tipo")
	private String type;

	@Column(name = "data_criacao")
	private Date createdAt;

	@Column(name = "criador_id")
	private UUID creatorId;

	@Column(name = "cliente_obrigatorio")
	private boolean mandatoryClient;

	@Column(name = "grupo_campo_extra_id")
	private UUID extraFieldGroupId;

	@Column(name = "formulario_id")
	private UUID formId;

	@Column(name = "desabilitar_acoes_objeto_fluxo")
	private boolean desableFlowObjectActions;

	@Column(name = "setor_id")
	private UUID sectorId;

	@Column(name = "atualiza_objeto_processo")
	private boolean updateProcessObject;

	@Column(name = "processo_finalizado_fase_proposta_id")
	private UUID endedProcessInOfferFaseId;

	@Column(name = "processo_cancelado_fase_proposta_id")
	private UUID canceledProcessInOfferFaseId;

	@OneToOne
	@JoinColumn(name = "ocorrencia_id")
	private OcurrencySchema ocurrency;

	public SolutionSchema(UUID id) {
		this.id = id;
	}
}
