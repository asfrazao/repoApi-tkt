package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_cliente")
public class ClientTypeSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "nome")
	private String name;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "codigo")
	private String code;

	@Column(name = "integracao_cliente_id")
	private UUID integrationClientId;

	@ManyToOne
	@JoinColumn(name = "grupo_campo_extra_id")
	private ExtraFieldGroupSchema extraFieldGroup;

	@Column(name = "grupo_campo_extra_por_atendimento_id")
	private UUID groupExtraFieldByTicketId;

	@Column(name = "mascara_codigo")
	private String codeMask;

	@Column(name = "dados_integracao_prioritarios")
	private boolean priorityIntegrationData;

	@Column(name = "integracao_importacao_produto_servico")
	private UUID integrationImportProductService;

	@Column(name = "remover_pontuacao")
	private boolean removePonctuation;

	@Column(name = "abertura_atendimento_integracao")
	private boolean openingTicketIntegration;

}