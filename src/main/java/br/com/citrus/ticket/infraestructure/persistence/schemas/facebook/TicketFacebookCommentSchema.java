package br.com.citrus.ticket.infraestructure.persistence.schemas.facebook;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketFacebookComment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "atendimento_facebook_comentario")
public class TicketFacebookCommentSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "fb_user_name")
	private String fbUserName;

	@Column(name = "fb_user_id")
	private String fbUserId;

	@Column(name = "fb_comment_id")
	private String fbCommentId;

	@Column(name = "fb_message")
	private String fbMessage;

	@Column(name = "fb_post_id")
	private String fbPostId;

	@Column(name = "fb_created_at")
	private LocalDateTime fbCreatedAt;

	@Column(name = "atendimento_id")
	private UUID ticketId;

	public TicketFacebookCommentSchema(TicketFacebookComment comment) {
		id = comment.getId();
		fbPostId = comment.getFbPostId();
		fbUserId = comment.getFbUserId();
		fbUserName = comment.getFbUserName();
		fbMessage = comment.getFbMessage();
		fbCommentId = comment.getFbCommentId();
		fbCreatedAt = comment.getCreatedAt();
		ticketId = comment.getTicketId();
	}

}
