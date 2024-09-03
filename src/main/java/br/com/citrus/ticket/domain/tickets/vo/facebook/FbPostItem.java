package br.com.citrus.ticket.domain.tickets.vo.facebook;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FbPostItem {

    private UUID id;
    private String fbMessage;
    private String fbLink;
    private String fbMediaLink;
    private String fbItem;
    private String fbItemId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
