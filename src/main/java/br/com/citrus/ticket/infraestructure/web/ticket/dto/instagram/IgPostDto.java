package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPost;
import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPostItem;

public class IgPostDto {

  private UUID id;

  @JsonProperty("instagram_post_id")
  private String igPostId;

  @JsonProperty("instagram_post_link")
  private String igPostLink;

  // private String payload;

  @JsonProperty("instagram_message")
  private String igMessage;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("deleted_at")
  private LocalDateTime deletedAt;

  private List<IgPostItemDto> items;

  private IgUserDto user;

  public IgPostDto(UUID id, String igPostId, String igPostLink, String igMessage,
      LocalDateTime createdAt, LocalDateTime deletedAt, List<IgPostItemDto> items, IgUserDto user) {
    this.id = id;
    this.igPostId = igPostId;
    this.igPostLink = igPostLink;
    // this.payload = payload;
    this.igMessage = igMessage;
    this.createdAt = createdAt;
    this.deletedAt = deletedAt;
    this.items = items;
    this.user = user;
  }

  public IgPost toValueObject() {

    List<IgPostItem> itemsList = new ArrayList<>();
    items.forEach(i -> itemsList.add(i.toValueObject()));

    return new IgPost(id,
        igPostId,
        igPostLink,
        // payload,
        igMessage,
        createdAt,
        deletedAt,
        itemsList,
        user.toValueObject());
  }
}
