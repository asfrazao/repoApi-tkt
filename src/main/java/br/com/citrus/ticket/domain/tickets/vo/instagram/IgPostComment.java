package br.com.citrus.ticket.domain.tickets.vo.instagram;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IgPostComment {
  
  private UUID id;
  private String igCommentId;
  private String igMessage;
  private IgCommentPayload payload;
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;
  private IgUser user;
}
