package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.instagram.IgCommentPayloadChangeValue;
import lombok.Data;

@Data
public class IgCommentPayloadChangeValueDto {
  
  private IgCommentPayloadChangeValueFromDto from;
  // private IgCommentPayloadChangeValuePostDto post;
  private String message;

  @JsonProperty("post_id")
  private String postId;

  @JsonProperty("comment_id")
  private String commentId;

  @JsonProperty("created_time")
  private int createdTime;
  private String item;

  @JsonProperty("parent_id")
  private String parentId;

  private String verb;

  public IgCommentPayloadChangeValue toValueObject() {
    return new IgCommentPayloadChangeValue(from.toValueObject(),
        // post.toValueObject(),
        message,
        postId,
        commentId,
        createdTime,
        item,
        parentId,
        verb);
  }
}
