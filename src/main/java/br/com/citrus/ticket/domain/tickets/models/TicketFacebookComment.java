package br.com.citrus.ticket.domain.tickets.models;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPost;
import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPostComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketFacebookComment {

	private UUID id;
	private String fbUserName;
	private String fbUserId;
	private String fbCommentId;
	private String fbMessage;
	private String fbPostId;
	private LocalDateTime createdAt;
	private UUID ticketId;

	public TicketFacebookComment(FbPost post, FbPostComment comment, UUID ticketId) {
		this.fbPostId = post.getFbPostId();

		this.fbUserName = comment.getUser().getFbName();
		this.fbUserId = comment.getUser().getFbId();

		this.fbMessage = comment.getFbMessage();
		this.fbCommentId = comment.getFbCommmetId();
		this.createdAt = comment.getCreatedAt();

		this.ticketId = ticketId;
	}

}
