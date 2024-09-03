package br.com.citrus.ticket.domain.tickets.vo.facebook;

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
public class FbPostComment {

    private UUID id;
    private String fbCommmetId;
    private String fbMessage;
    private FbCommentPayload payload;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private FbUser user;
}
