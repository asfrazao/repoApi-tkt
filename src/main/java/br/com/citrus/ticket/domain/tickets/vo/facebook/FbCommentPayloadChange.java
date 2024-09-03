package br.com.citrus.ticket.domain.tickets.vo.facebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FbCommentPayloadChange {

    private FbCommentPayloadChangeValue value;
    private String field;
}
