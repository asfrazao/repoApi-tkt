package br.com.citrus.ticket.infraestructure.persistence.schemas.facebook;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPost;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atendimento_facebook_post")
public class TicketFacebookPostSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "fb_user_name")
	private String fbUserName;

	@Column(name = "fb_user_id")
	private String fbUserId;

	@Column(name = "client_name")
	private String clientName;

	@Column(name = "client_external_id")
	private String clientExternalId;

	@Column(name = "fb_comment_id")
	private String fbCommentId;

	@Column(name = "fb_post_id")
	private String fbPostId;

	@Column(name = "fb_post_link")
	private String fbPostLink;

	@Column(name = "fb_message")
	private String fbMessage;

	@Column(name = "fb_created_at")
	private LocalDateTime fbCreatedAt;

	@Column(name = "atendimento_id")
	private UUID ticketId;

	public TicketFacebookPostSchema(TicketFacebookPost post) {
		id = post.getId();
		fbPostId = post.getFbPostId();
		fbCommentId = post.getFbCommentId();
		fbPostLink = post.getFbPostLink();
		fbUserId = post.getFbUserId();
		clientName = post.getClientName();
		clientExternalId = post.getClientExternalId();
		fbUserName = post.getFbUserName();
		fbCreatedAt = post.getFbCreatedAt();
		fbMessage = post.getFbMessage();
		ticketId = post.getTicketId();
	}

	public TicketFacebookPost toModel() {
		return new TicketFacebookPost(this.id, this.fbUserName, this.fbUserId, this.clientName, this.clientExternalId,
				this.fbCommentId, this.fbPostId, this.fbPostLink, this.fbMessage, this.fbCreatedAt, this.ticketId);
	}
}
