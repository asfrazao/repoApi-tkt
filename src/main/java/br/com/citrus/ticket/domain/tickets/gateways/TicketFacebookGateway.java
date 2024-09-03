package br.com.citrus.ticket.domain.tickets.gateways;

import java.util.List;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.TicketFacebookComment;
import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPost;
import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPostMedia;
import jakarta.transaction.Transactional;

@Transactional
public interface TicketFacebookGateway {

	List<TicketFacebookPost> listFacebookPost();

	TicketFacebookPost saveFacebookPost(TicketFacebookPost ticketFbPost);

	UUID saveFacebookPostMedia(TicketFacebookPostMedia media);

	boolean alreadExitsTicketByComment(String fbCommentId);

	TicketFacebookPost findTicketByPostAndFbUser(String fbPostId, String fbUserId);

	void saveCommentToFbPost(TicketFacebookComment comment);

	TicketFacebookPost findTicketByPostAndCommentId(String fbPostId, String fbCommmetId);

	void commentEditedToTicket(String fbCommmetId, String fbMessage);

	TicketFacebookPost getTicketByTicketId(UUID ticketId);

	UUID getTicketIdByCommentId(String fbCommmetId);
}
