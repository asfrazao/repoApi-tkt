package br.com.citrus.ticket.domain.tickets.gateways;

import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketInstagramComment;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPost;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPostMedia;
import jakarta.transaction.Transactional;

@Transactional
public interface TicketInstagramGateway {
  TicketInstagramPost findTicketByPostAndIgUser(String igPostId, String igUserId);
  TicketInstagramPost findTicketByCommentAndIgUser(String igCommentId, String igUserId);
  TicketInstagramPost findTicketByComment(String igCommentId);
  TicketInstagramPost findTicketByPostAndCommentId(String fbPostId, String fbCommmetId);
  TicketInstagramPost saveInstagramPost(TicketInstagramPost ticketIgPost);
  boolean alreadExitsTicketByComment(String igCommentId);
  void saveCommentToIgPost(TicketInstagramComment comment);
  UUID saveInstagramPostMedia(TicketInstagramPostMedia media);
}
