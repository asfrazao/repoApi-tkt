package br.com.citrus.ticket.infraestructure.persistence.schemas.instagram;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketInstagramComment;
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
@Table(name = "atendimento_instagram_comentario")
public class TicketInstagramCommentSchema {
  
  @Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "ig_user_name")
	private String igUserName;

	@Column(name = "ig_user_id")
	private String igUserId;

	@Column(name = "ig_comment_id")
	private String igCommentId;

	@Column(name = "ig_message")
	private String igMessage;

	@Column(name = "ig_post_id")
	private String igPostId;

	@Column(name = "ig_created_at")
	private LocalDateTime igCreatedAt;

	@Column(name = "atendimento_id")
	private UUID ticketId;

	public TicketInstagramCommentSchema(TicketInstagramComment comment) {
		id = comment.getId();
		igPostId = comment.getIgPostId();
		igUserId = comment.getIgUserId();
		igUserName = comment.getIgUserName();
		igMessage = comment.getIgMessage();
		igCommentId = comment.getIgCommentId();
		igCreatedAt = comment.getCreatedAt();
		ticketId = comment.getTicketId();
	}

}
