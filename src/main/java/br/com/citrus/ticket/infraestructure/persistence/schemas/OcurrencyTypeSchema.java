package br.com.citrus.ticket.infraestructure.persistence.schemas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tipo_ocorrencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcurrencyTypeSchema {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;

	@Column(name = "nome")
	private String name;

	@Column(name = "descricao")
	private String description;

	@Column(name = "detalhar_apenas_setor")
	private boolean detailOnlySector;

	@Column(name = "visivel_apenas_setor")
	private boolean visibleOnlySector;

	@Column(name = "ativo")
	private boolean active;

	@Column(name = "setor_id")
	private UUID sectorId;

	@Column(name = "classificacao_id")
	private String classificationId;

	@Column(name = "notificar_atendimento")
	private boolean notifyAttendance;

	@Column(name = "equipe_id")
	private UUID teamId;

	@Column(name = "alerta_id")
	private UUID alertId;

	@Column(name = "formulario_id")
	private UUID formId;

	@Column(name = "enviar_nome_usuario_email")
	private boolean sendNameUserEmail;

	@Column(name = "notificar_responsaveis_aceita_atn")
	private boolean notifyResponsiblesAccepted;

	@Column(name = "solucao_obrigatorio")
	private boolean solutionMandatory;

	@Column(name = "classificacao_atendimento_obrigatorio")
	private boolean classificationAttendanceMandatory;

	@Column(name = "enviar_sms")
	private boolean sendSMS;

	@Column(name = "enviar_sms_link_formulario")
	private boolean sendSMSFormLink;

	@Column(name = "mensagem_sms")
	private String smsMessage;

	@Column(name = "enviar_pergunta_unica_nps")
	private boolean sendUniqueQuestionNPS;

	@Column(name = "mensagem_nps_id")
	private UUID npsMessageId;

	@Column(name = "anonimo")
	private boolean anonymous;

	@Column(name = "confidencial")
	private boolean confidential;
}
