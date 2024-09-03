package br.com.citrus.ticket.domain.tickets.models;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.vo.FbPostClient;
import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketFacebookPost {

	private UUID id;
	private String fbUserName;
	private String fbUserId;
	private String clientName;
	private String clientExternalId;
	private String fbCommentId;
	private String fbPostId;
	private String fbPostLink;
	private String fbMessage;
	private LocalDateTime fbCreatedAt;
	private UUID ticketId;


	public TicketFacebookPost(FbPost post, FbPostClient client, String fbCommentId, UUID ticketId) {
		this.fbUserName = post.getUser().getFbName();
		this.fbUserId = post.getUser().getFbId();

		this.fbCommentId = fbCommentId;
		this.fbPostId = post.getFbPostId();
		this.fbPostLink = post.getFbPostLink();
		this.fbMessage = post.getFbMessage();
		
		this.clientName = client.getName();
		this.clientExternalId = client.getExternalId();

		this.fbCreatedAt = post.getCreatedAt();
		this.ticketId = ticketId;
	}
}
