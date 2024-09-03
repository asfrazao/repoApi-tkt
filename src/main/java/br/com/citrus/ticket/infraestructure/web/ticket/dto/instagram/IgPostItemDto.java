package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPostItem;
import lombok.Data;

@Data
public class IgPostItemDto {
  
  private UUID id;

  // @JsonProperty("instagram_message")
  // private String igMessage;

  @JsonProperty("instagram_link")
  private String igLink;

  // @JsonProperty("instagram_media_link")
  // private String igMediaLink;

  @JsonProperty("instagram_item")
  private String igItem;

  @JsonProperty("instagram_item_id")
  private String igItemId;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("deleted_at")
  private LocalDateTime deletedAt;

  public IgPostItemDto(UUID id, String igMessage, String igLink, String igMediaLink, String igItem, String igItemId,
      LocalDateTime createdAt, LocalDateTime deletedAt) {
    this.id = id;
    // this.igMessage = igMessage;
    this.igLink = igLink;
    // this.igMediaLink = igMediaLink;
    this.igItem = igItem;
    this.igItemId = igItemId;
    this.createdAt = createdAt;
    this.deletedAt = deletedAt;
  }

  public IgPostItem toValueObject() {
    return new IgPostItem(this.id,
        // this.igMessage,
        this.igLink,
        // this.igMediaLink,
        this.igItem,
        this.igItemId,
        this.createdAt,
        this.deletedAt);
  }
}
