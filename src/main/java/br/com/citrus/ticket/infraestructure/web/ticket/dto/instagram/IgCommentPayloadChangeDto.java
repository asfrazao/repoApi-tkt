package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;


import br.com.citrus.ticket.domain.tickets.vo.instagram.IgCommentPayloadChange;
import lombok.Data;

@Data
public class IgCommentPayloadChangeDto {
  
  private IgCommentPayloadChangeValueDto value;
  private String field;

  public IgCommentPayloadChangeDto(IgCommentPayloadChangeValueDto value, String field) {
    this.field = field;
    this.value = value;
  }

  public IgCommentPayloadChange toValueObject() {
    return new IgCommentPayloadChange(value.toValueObject(), field);
  }

}
