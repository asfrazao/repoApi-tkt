package br.com.citrus.ticket.domain.tickets.vo.instagram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IgCommentPayloadChangeValuePost {

  private String id;
  private String statusType;
  private String isPublished;
  private String updatedTime;
  private String permalinkUrl;
  private String promotionStatus;
}
