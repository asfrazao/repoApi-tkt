package br.com.citrus.ticket.domain.tickets.vo.facebook;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FbCommentPayload {
    
    private String id;
    private int time;
    private List<FbCommentPayloadChange> changes;
}
