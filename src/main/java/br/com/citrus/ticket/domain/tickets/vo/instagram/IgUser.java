package br.com.citrus.ticket.domain.tickets.vo.instagram;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IgUser {

  private UUID id;
  private String igId;
  private String igName;
  private String igUsername;
}
