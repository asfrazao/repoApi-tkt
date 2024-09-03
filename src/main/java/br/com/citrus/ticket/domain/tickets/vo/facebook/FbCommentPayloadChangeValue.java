package br.com.citrus.ticket.domain.tickets.vo.facebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FbCommentPayloadChangeValue {

    private FbCommentPayloadChangeValueFrom from;
    private FbCommentPayloadChangeValuePost post;
    private String message;
    private String postId;
    private String commentId;
    private int createdTime;
    private String item;
    private String parentId;
    private String verb;
}