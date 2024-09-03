package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;


import br.com.citrus.ticket.domain.tickets.vo.instagram.IgCommentPayloadChangeValueFrom;
import lombok.Data;

@Data
public class IgCommentPayloadChangeValueFromDto {
  
  private String id;
  private String name;
  private String username;

public IgCommentPayloadChangeValueFrom toValueObject() {
  return new IgCommentPayloadChangeValueFrom(id, name, username);
}
}
