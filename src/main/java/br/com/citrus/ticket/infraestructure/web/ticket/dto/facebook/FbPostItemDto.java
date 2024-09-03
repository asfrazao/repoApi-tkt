package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPostItem;
import lombok.Data;

@Data
public class FbPostItemDto {

	private UUID id;

	@JsonProperty("fb_message")
	private String fbMessage;

	@JsonProperty("fb_link")
	private String fbLink;

	@JsonProperty("fb_media_link")
	private String fbMediaLink;

	@JsonProperty("fb_item")
	private String fbItem;

	@JsonProperty("fb_item_id")
	private String fbItemId;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;

	@JsonProperty("deleted_at")
	private LocalDateTime deletedAt;

	public FbPostItemDto(UUID id, String fbMessage, String fbLink, String fbMediaLink, String fbItem, String fbItemId,
			LocalDateTime createdAt, LocalDateTime deletedAt) {
		this.id = id;
		this.fbMessage = fbMessage;
		this.fbLink = fbLink;
		this.fbMediaLink = fbMediaLink;
		this.fbItem = fbItem;
		this.fbItemId = fbItemId;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
	}

	public FbPostItem toValueObject() {
		return new FbPostItem(this.id,
				this.fbMessage,
				this.fbLink,
				this.fbMediaLink,
				this.fbItem,
				this.fbItemId,
				this.createdAt,
				this.deletedAt);
	}

}
