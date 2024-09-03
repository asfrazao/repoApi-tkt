package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPostComment;
import lombok.Data;

@Data
public class FbPostCommentDto {

	private UUID id;

	@JsonProperty("fb_comment_id")
	private String fbCommmetId;

	@JsonProperty("fb_message")
	private String fbMessage;

	private FbCommentPayloadDto payload;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;

	@JsonProperty("deleted_at")
	private LocalDateTime deletedAt;

	private FbUserDto user;

	public FbPostCommentDto(UUID id, String fbCommmetId, String fbMessage, FbCommentPayloadDto payload,
			LocalDateTime createdAt, LocalDateTime deletedAt, FbUserDto user) {
		this.id = id;
		this.fbCommmetId = fbCommmetId;
		this.fbMessage = fbMessage;
		this.payload = payload;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.user = user;
	}

	public FbPostComment toValueObject() {
		return new FbPostComment(id,
				fbCommmetId,
				fbMessage,
				payload.toValueObject(),
				createdAt,
				deletedAt,
				user.toValueObject());
	}

}