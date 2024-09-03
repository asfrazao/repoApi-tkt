package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.gateways;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.tickets.gateways.TicketFacebookGateway;
import br.com.citrus.ticket.domain.tickets.models.TicketFacebookComment;
import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPost;
import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPostMedia;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.facebook.TicketFacebookCommentRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.facebook.TicketFacebookPostMediaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.facebook.TicketFacebookPostRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.facebook.TicketFacebookCommentSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.facebook.TicketFacebookPostMediaSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.facebook.TicketFacebookPostSchema;

@Component
public class TicketFacebookJpaGateway implements TicketFacebookGateway {

	@Autowired
	private TicketFacebookPostRepository ticketFbPostrepository;
	@Autowired
	private TicketFacebookPostMediaRepository ticketFbPostMediaRepository;
	@Autowired
	private TicketFacebookCommentRepository ticketFbCommentRepository;

	private static final Logger logger = LoggerFactory.getLogger(TicketFacebookJpaGateway.class);
	private static final String SAVE_INFO_TO_TICKET_FACEBOOK_POST = "saved facebook post information to new ticket with id: {}";
	private static final String SAVE_INFO_TO_TICKET_FACEBOOK_COMM = "saved facebook comment, ticket id: {}, facebook user: {}";
	private static final String COMMENT_TICKET_FACEBOOK_EDITED = "comment ticket {} edited successfully";

	private static final String MSG_ERROR_SAVE_POST = "";

	@Override
	public List<TicketFacebookPost> listFacebookPost() {
		List<TicketFacebookPost> result = new ArrayList<>();

		List<TicketFacebookPostSchema> list = ticketFbPostrepository.findAll();

		list.forEach(t -> result.add(t.toModel()));
		return result;
	}

	@Override
	public TicketFacebookPost saveFacebookPost(TicketFacebookPost ticketFbPost) {
		try {
			TicketFacebookPostSchema ticketFacebookPostSchema = new TicketFacebookPostSchema(ticketFbPost);

			var schema = ticketFbPostrepository.save(ticketFacebookPostSchema);

			logger.info(SAVE_INFO_TO_TICKET_FACEBOOK_POST, schema.getTicketId(), schema.getFbUserName());
			return schema.toModel();
		} catch (Exception e) {
			logger.error(MSG_ERROR_SAVE_POST);
			throw e;
		}
	}

	@Override
	public boolean alreadExitsTicketByComment(String fbCommentId) {
		var result = ticketFbCommentRepository.getCountTicketPostByFbCommentId(fbCommentId);
		return result > 0;
	}

	@Override
	public UUID saveFacebookPostMedia(TicketFacebookPostMedia media) {
		var schema = new TicketFacebookPostMediaSchema();

		schema.setFbMediaName(media.getFbMediaName());
		schema.setTicketFacebookPostId(media.getTicketFacebookPostId());

		var persist = ticketFbPostMediaRepository.save(schema);
		return persist.getId();
	}

	@Override
	public TicketFacebookPost findTicketByPostAndFbUser(String fbPostId, String fbUserId) {
		List<TicketFacebookPostSchema> result = ticketFbPostrepository.findTicketFbPostByFbUser(fbPostId, fbUserId);

		if (result.size() > 0) {
			return result.get(0).toModel();
		}
		return null;
	}

	@Override
	public void saveCommentToFbPost(TicketFacebookComment comment) {
		var schema = new TicketFacebookCommentSchema(comment);

		ticketFbCommentRepository.save(schema);

		logger.info(SAVE_INFO_TO_TICKET_FACEBOOK_COMM, schema.getTicketId(), schema.getFbUserName());
	}

	@Override
	public TicketFacebookPost findTicketByPostAndCommentId(String fbPostId, String fbCommmetId) {
		List<TicketFacebookPostSchema> result = ticketFbPostrepository.findTicketByPostAndCommentId(fbPostId,
				fbCommmetId);

		if (result.size() > 0) {
			return result.get(0).toModel();
		}
		return null;
	}

	@Override
	public void commentEditedToTicket(String fbCommmetId, String fbMessage) {
		List<TicketFacebookCommentSchema> commentSchemaList = ticketFbCommentRepository
				.findTicketCommentByFbCommentId(fbCommmetId);

		if (commentSchemaList.size() == 1) {
			var commentSchema = commentSchemaList.get(0);

			commentSchema.setFbMessage(fbMessage);
			ticketFbCommentRepository.save(commentSchema);

			logger.info(COMMENT_TICKET_FACEBOOK_EDITED, commentSchema.getTicketId());
		}
	}

	@Override
	public TicketFacebookPost getTicketByTicketId(UUID ticketId) {
		List<TicketFacebookPostSchema> result = ticketFbPostrepository.getTicketByTicketId(ticketId);

		if (result.size() > 0) {
			return result.get(0).toModel();
		}
		return null;
	}

	@Override
	public UUID getTicketIdByCommentId(String fbCommmetId) {
		List<UUID> result = ticketFbCommentRepository.getTicketIdByCommentId(fbCommmetId);

		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
}
