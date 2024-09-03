package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbCommentPayloadChangeValuePost;
import lombok.Data;

@Data
public class FbCommentPayloadChangeValuePostDto {

	private String id;

	@JsonProperty("status_type")
	private String statusType;

	@JsonProperty("is_published")
	private String isPublished;

	@JsonProperty("updated_time")
	private String updatedTime;

	@JsonProperty("permalink_url")
	private String permalinkUrl;

	@JsonProperty("promotion_status")
	private String promotionStatus;

	public FbCommentPayloadChangeValuePost toValueObject() {
		return new FbCommentPayloadChangeValuePost(id,
				statusType,
				isPublished,
				updatedTime,
				permalinkUrl,
				promotionStatus);
	}

}