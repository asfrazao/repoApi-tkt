package br.com.citrus.ticket.domain.tickets.vo.instagram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IgCommentPayloadChange {
  
  private IgCommentPayloadChangeValue value;
  private String field;
}