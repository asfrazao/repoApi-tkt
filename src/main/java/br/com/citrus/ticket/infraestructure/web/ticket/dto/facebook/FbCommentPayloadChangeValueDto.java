package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbCommentPayloadChangeValue;
import lombok.Data;

@Data
public class FbCommentPayloadChangeValueDto {

	private FbCommentPayloadChangeValueFromDto from;
	private FbCommentPayloadChangeValuePostDto post;
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

	public FbCommentPayloadChangeValue toValueObject() {
		return new FbCommentPayloadChangeValue(from.toValueObject(),
				post.toValueObject(),
				message,
				postId,
				commentId,
				createdTime,
				item,
				parentId,
				verb);
	}

}
