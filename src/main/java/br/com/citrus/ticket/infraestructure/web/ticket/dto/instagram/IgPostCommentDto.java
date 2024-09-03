package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPostComment;
import lombok.Data;

@Data
public class IgPostCommentDto {

  private UUID id;

  @JsonProperty("instagram_comment_id")
  private String igCommentId;

  @JsonProperty("instagram_message")
  private String igMessage;

  private IgCommentPayloadDto payload;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("deleted_at")
  private LocalDateTime deletedAt;

  private IgUserDto user;

  public IgPostCommentDto(UUID id, String igCommentId, String igMessage, IgCommentPayloadDto payload,
      LocalDateTime createdAt, LocalDateTime deletedAt, IgUserDto user) {
    this.id = id;
    this.igCommentId = igCommentId;
    this.igMessage = igMessage;
    this.payload = payload;
    this.createdAt = createdAt;
    this.deletedAt = deletedAt;
    this.user = user;
  }

  public IgPostComment toValueObject() {
    return new IgPostComment(id, igCommentId, igMessage, payload.toValueObject(), createdAt, deletedAt, user.toValueObject());
  }
  
}
