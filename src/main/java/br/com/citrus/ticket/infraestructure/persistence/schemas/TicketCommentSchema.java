package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketComment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "atendimento_comentario")
@Getter
@Setter
public class TicketCommentSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "comentario")
	private String comment;

	@Column(name = "anexo")
	private String attachment;

	@Column(name = "data")
	private LocalDateTime date;

	@Column(name = "sistema")
	private boolean system;

	@Column(name = "enviar_sms")
	private boolean sendSMS;

	@Column(name = "codigo")
	private String code;

	@Column(name = "visivel_cliente")
	private boolean visibleCustomer;

	@Column(name = "comentario_rede_social_id")
	private String socialMediaCommentId;

	@Column(name = "enviado_rede_social")
	private boolean sentSocialMedia;

	@Column(name = "aguardando_retorno_cliente")
	private boolean waitingCustomerFeedback;

	@Column(name = "aguardando_autorizacao_cliente_finalizar")
	private boolean waitingCustomerAuthFinish;

	@Column(name = "resolvido")
	private boolean resolved;

	@Column(name = "usuario_id")
	private UUID userId;

	@Column(name = "atendimento_id")
	private UUID ticketId;

	@Column(name = "copia")
	private String copy;

	@Column(name = "midia_notificacao")
	private String notificationMedia;

	@Column(name = "sucesso")
	private boolean success;

	public TicketCommentSchema(TicketComment model) {
		this.comment = model.getComment();
		this.attachment = model.getAttachment();
		this.date = model.getDate();
		this.system = model.isSystem();
		this.sendSMS = model.isSendSMS();
		this.code = model.getCode();
		this.visibleCustomer = model.isVisibleCustomer();
		this.socialMediaCommentId = model.getSocialMediaCommentId();
		this.sentSocialMedia = model.isSentSocialMedia();
		this.waitingCustomerFeedback = model.isWaitingCustomerFeedback();
		this.waitingCustomerAuthFinish = model.isWaitingCustomerAuthFinish();
		this.resolved = model.isResolved();
		this.userId = model.getUserId();
		this.ticketId = model.getTicketId();
		this.copy = model.getCopy();
		this.notificationMedia = model.getNotificationMedia();
		this.success = model.isSuccess();
	}

}