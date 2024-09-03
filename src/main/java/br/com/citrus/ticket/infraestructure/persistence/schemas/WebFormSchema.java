package br.com.citrus.ticket.infraestructure.persistence.schemas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "formulario_web")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebFormSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	@Column(name = "nome")
	private String name;

	@Column(name = "descricao")
	private String description;

	@Column(name = "chave")
	private String key;

	@OneToOne
	@JoinColumn(name = "ocorrencia_id")
	private OcurrencySchema ocurrency;

	@OneToOne
	@JoinColumn(name = "tipo_ocorrencia_id")
	private OcurrencyTypeSchema ocurrencyType;

	@Column(name = "validar_assunto")
	private boolean validateSubject;

	@Column(name = "validar_tempo")
	private boolean validateTime;

	@Column(name = "validar_tempo_horas")
	private Integer validateTimeHours;

	@Column(name = "validar_campo_indexador")
	private boolean validateIndexerField;

	@Column(name = "label_campo_protocolo")
	private String protocolFieldLabel;

	@Column(name = "label_campo_codigo")
	private String codeFieldLabel;

	@Column(name = "setor_id")
	private UUID sectorId;

	@Column(name = "campo_indexador")
	private String indexerField;

	@Column(name = "solicitar_campo_codigo")
	private boolean requestCodeField;

	@Column(name = "label_campo_nome")
	private String nameFieldLabel;

	@Column(name = "label_campo_email")
	private String emailFieldLabel;

	@Column(name = "label_campo_telefone")
	private String phoneFieldLabel;

	@Column(name = "solicitar_campo_protocolo")
	private boolean requestProtocolField;

	@Column(name = "validar_status_atendimento")
	private boolean validateTicketStatus;

	@Column(name = "status_atendimento")
	private String ticketStatus;

	@ManyToOne
	@JoinColumn(name = "grupo_campo_extra_id")
	private ExtraFieldGroupSchema extraFieldGroup;

	@ManyToOne
	@JoinColumn(name = "canal_atendimento_id")
	private ChannelTicketSchema channelTicket;

	@Column(name = "campo_codigo_obrigatorio")
	private boolean codeFieldRequired;

	@Column(name = "tipo")
	private String type;

	@Column(name = "lead_fonte")
	private String leadSource;

	@Column(name = "lead_situacao_id")
	private UUID leadSituationId;

	@Column(name = "lead_usuario_id")
	private UUID leadUser;

	@Column(name = "tem_anexo")
	private boolean hasAttachment;

	@Column(name = "cor_botao_principal")
	private String mainButtonColor;

	@Column(name = "label_campo_tipo_cliente")
	private String clientTypeFieldLabel;

	@Column(name = "label_campo_regiao")
	private String regionFieldLabel;

	@Column(name = "habilitar_conversao_atendimento")
	private boolean enableServiceConversion;

	@Column(name = "habilitar_label_campo_tipo_cliente_regiao")
	private boolean enableClientTypeRegionFieldLabel;

	@ManyToOne
	@JoinColumn(name = "tipo_cliente_id")
	private ClientTypeSchema clientType;

	@ManyToOne
	@JoinColumn(name = "regiao_id")
	private RegionSchema region;

	@ManyToOne
	@JoinColumn(name = "solucao_id")
	private SolutionSchema solution;

	@Column(name = "criador_id")
	private UUID creatorId;

	@Column(name = "habilitar_distribuicao_formulario")
	private boolean enableFormDistribution;

	@Column(name = "tipo_distribuicao_formulario")
	private String formDistributionType;

	@Column(name = "cliente_anonimo_codigo")
	private String anonymousClientCode;
}
