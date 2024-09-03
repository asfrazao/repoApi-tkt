package br.com.citrus.ticket.infraestructure.persistence.schemas.instagram;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPost;
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
@Table(name = "atendimento_instagram_post")
public class TicketInstagramPostSchema {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

  @Column(name = "ig_user_name")
  private String igUserName;

  @Column(name = "ig_user_id")
  private String igUserId;

  @Column(name = "ig_user_username")
  private String igUserUsername;

	@Column(name = "client_name")
	private String clientName;

	@Column(name = "client_external_id")
	private String clientExternalId;

  @Column(name = "ig_comment_id")
  private String igCommentId;

  @Column(name = "ig_post_id")
  private String igPostId;

  @Column(name = "ig_post_link")
  private String igPostLink;

  @Column(name = "ig_message")
  private String igMessage;

  @Column(name = "ig_created_at")
  private LocalDateTime igCreatedAt;

  @Column(name = "atendimento_id")
  private UUID ticketId;


  public TicketInstagramPostSchema(TicketInstagramPost post) {
    id = post.getId();
    igUserId = post.getIgUserId();
    igUserName = post.getIgUserName();
    igUserUsername = post.getIgUserUsername();
    clientName = post.getClientName();
    clientExternalId = post.getClientExternalId();
    igCommentId = post.getIgCommentId();
    igPostId = post.getIgPostId();
    igPostLink = post.getIgPostLink();
    igMessage = post.getIgMessage();
    igCreatedAt = post.getIgCreatedAt();
    ticketId = post.getTicketId();
  }


  public TicketInstagramPost toModel() {
    return new TicketInstagramPost(this.id, this.igUserName, this.igUserId, this.igUserUsername, this.clientName, this.clientExternalId, this.igCommentId, this.igPostId, this.igPostLink, this.igMessage, this.igCreatedAt, this.ticketId);
  }

}
