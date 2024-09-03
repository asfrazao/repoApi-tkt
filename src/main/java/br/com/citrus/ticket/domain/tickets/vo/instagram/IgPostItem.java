package br.com.citrus.ticket.domain.tickets.vo.instagram;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IgPostItem {

  private UUID id;
  // private String igMessage;
  private String igLink;
  // private String igMediaLink;
  private String igItem;
  private String igItemId;
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;
}
