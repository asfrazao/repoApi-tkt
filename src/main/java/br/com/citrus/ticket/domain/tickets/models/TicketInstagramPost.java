package br.com.citrus.ticket.domain.tickets.models;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.vo.IgPostClient;
import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketInstagramPost {
  
  private UUID id;
	private String igUserName;
	private String igUserId;
	private String igUserUsername;
	private String clientName;
	private String clientExternalId;
	private String igCommentId;
	private String igPostId;
	private String igPostLink;
	private String igMessage;
	private LocalDateTime igCreatedAt;
	private UUID ticketId;

  public TicketInstagramPost(IgPost post, IgPostClient client, String igCommentId, UUID ticketId) {
    this.igUserName = post.getUser().getIgName();
    this.igUserId = post.getUser().getIgId();
    this.igUserUsername = post.getUser().getIgUsername();

    this.igCommentId = igCommentId;
    this.igPostId = post.getIgPostId();
    this.igPostLink = post.getIgPostLink();
    this.igMessage = post.getIgMessage();

    this.clientName = client.getName();
    this.clientExternalId = client.getExternalId();

    this.igCreatedAt = post.getCreatedAt();
    this.ticketId = ticketId;

  }
}
