package br.com.citrus.ticket.domain.tickets.models;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPost;
import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPostComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketInstagramComment {

  private UUID id;
	private String igUserName;
	private String igUserId;
	private String igCommentId;
	private String igMessage;
	private String igPostId;
	private LocalDateTime createdAt;
	private UUID ticketId;

  public TicketInstagramComment(IgPost post, IgPostComment comment, UUID ticketId) {
		this.igPostId = post.getIgPostId();

		this.igUserName = comment.getUser().getIgName();
		this.igUserId = comment.getUser().getIgId();

		this.igMessage = comment.getIgMessage();
		this.igCommentId = comment.getIgCommentId();
		this.createdAt = comment.getCreatedAt();

		this.ticketId = ticketId;
	}
  
}
