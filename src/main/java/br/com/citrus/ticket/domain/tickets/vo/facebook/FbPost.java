package br.com.citrus.ticket.domain.tickets.vo.facebook;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FbPost {

    private UUID id;
    private String fbPostId;
    private String fbPostLink;
    private String payload;
    private String fbMessage;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private List<FbPostItem> items;
    private FbUser user;

}
