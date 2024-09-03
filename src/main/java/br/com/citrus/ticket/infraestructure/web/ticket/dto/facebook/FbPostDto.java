package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPost;
import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPostItem;
import lombok.Data;

@Data
public class FbPostDto {

	private UUID id;

	@JsonProperty("fb_post_id")
	private String fbPostId;

	@JsonProperty("fb_post_link")
	private String fbPostLink;

	private String payload;

	@JsonProperty("fb_message")
	private String fbMessage;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;

	@JsonProperty("deleted_at")
	private LocalDateTime deletedAt;

	private List<FbPostItemDto> items;

	private FbUserDto user;

	public FbPostDto(UUID id, String fbPostId, String fbPostLink, String payload, String fbMessage,
			LocalDateTime createdAt, LocalDateTime deletedAt, List<FbPostItemDto> items, FbUserDto user) {
		this.id = id;
		this.fbPostId = fbPostId;
		this.fbPostLink = fbPostLink;
		this.payload = payload;
		this.fbMessage = fbMessage;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.items = items;
		this.user = user;
	}

	public FbPost toValueObject() {

		List<FbPostItem> itemsList = new ArrayList<>();
		items.forEach(i -> itemsList.add(i.toValueObject()));

		return new FbPost(id,
				fbPostId,
				fbPostLink,
				payload,
				fbMessage,
				createdAt,
				deletedAt,
				itemsList,
				user.toValueObject());
	}

}
