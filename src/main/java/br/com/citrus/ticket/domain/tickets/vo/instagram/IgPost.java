package br.com.citrus.ticket.domain.tickets.vo.instagram;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IgPost {

  private UUID id;
  private String igPostId;
  private String igPostLink;
  // private String payload;
  private String igMessage;
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;
  private List<IgPostItem> items;
  private IgUser user;
  
}
