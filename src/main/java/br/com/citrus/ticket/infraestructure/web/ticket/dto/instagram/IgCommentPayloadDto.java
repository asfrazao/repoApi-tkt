package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;

import java.util.ArrayList;
import java.util.List;

import br.com.citrus.ticket.domain.tickets.vo.instagram.IgCommentPayload;
import br.com.citrus.ticket.domain.tickets.vo.instagram.IgCommentPayloadChange;
import lombok.Data;

@Data
public class IgCommentPayloadDto {

  private String id;
  private int time;
  private List<IgCommentPayloadChangeDto> changes;

  public IgCommentPayloadDto(String id, int time, List<IgCommentPayloadChangeDto> changes) {
    this.id = id;
    this.time = time;
    this.changes = changes;
  }

  public IgCommentPayload toValueObject() {

    List<IgCommentPayloadChange> changesList = new ArrayList<>();
    changes.forEach(c -> changesList.add(c.toValueObject()));
    
    return new IgCommentPayload(
        id,
        time,
        changesList);
  }

  
}
