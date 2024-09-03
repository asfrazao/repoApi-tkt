package br.com.citrus.ticket.domain.tickets.services.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.tickets.enums.FacebookPostAction;
import br.com.citrus.ticket.domain.tickets.gateways.IntegrationGateway;
import br.com.citrus.ticket.domain.tickets.gateways.TicketFacebookGateway;
import br.com.citrus.ticket.domain.tickets.gateways.TicketGateway;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.models.TicketFacebookComment;
import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPost;
import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPostMedia;
import br.com.citrus.ticket.domain.tickets.services.OpenTicketFacebookPost;
import br.com.citrus.ticket.domain.tickets.usecases.GenerateProcotolNumberUseCase;
import br.com.citrus.ticket.domain.tickets.vo.FbPostClient;
import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPost;
import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPostComment;

@Service
public class OpenTicketFacebookPostImpl implements OpenTicketFacebookPost {

	@Autowired
	private GenerateProcotolNumberUseCase generateProcotolUseCase;
	@Autowired
	private TicketFacebookGateway ticketFacebookGateway;
	@Autowired
	private TicketGateway ticketGateway;
	@Autowired
	private IntegrationGateway integrationGateway;

	@Override
	public List<TicketFacebookPost> execute(FbPost post, FbPostClient client, FbPostComment comment) {

		List<TicketFacebookPost> result = new ArrayList<>();

		comment.getPayload().getChanges().forEach(action -> {
			String parentId = action.getValue().getParentId();
			String verb = action.getValue().getVerb();

			if (verb.equals(FacebookPostAction.ADD.getAction())) {
				createNewTicketOrAddComment(post, client, comment, parentId, result);
			}

			if (verb.equals(FacebookPostAction.EDITED.getAction())) {
				ticketFacebookGateway.commentEditedToTicket(comment.getFbCommmetId(), comment.getFbMessage());
			}

			if (verb.equals(FacebookPostAction.REMOVE.getAction())) {
				disabledTicket(post, comment, result);
			}
		});

		return result;
	}

	private void disabledTicket(FbPost post, FbPostComment comment, List<TicketFacebookPost> result) {
		var ticketPost = ticketFacebookGateway.findTicketByPostAndCommentId(post.getFbPostId(),
				comment.getFbCommmetId());

		if (ticketPost != null) {
			Ticket ticket = ticketGateway.getTicketById(ticketPost.getTicketId());
			ticket.cancel();

			ticketGateway.updateTicket(ticket);

			result.add(ticketPost);
		}
	}

	private void createNewTicketOrAddComment(FbPost post, FbPostClient client, FbPostComment comment, String parentId,
			List<TicketFacebookPost> result) {

		TicketFacebookPost ticketFb = null;

		if (parentId.equals(post.getFbPostId())) {
			ticketFb = openNewTicket(post, client, comment);
		} else {

			ticketFb = saveCommentFromPost(post, comment, parentId);

			if (ticketFb == null) {
				ticketFb = openNewTicket(post, client, comment);
			}
		}
		result.add(ticketFb);
	}

	private TicketFacebookPost saveCommentFromPost(FbPost post, FbPostComment comment, String parentId) {

		if (comment.getUser().getFbId().equals(post.getUser().getFbId())) {
			UUID ticketId = ticketFacebookGateway.getTicketIdByCommentId(parentId);

			TicketFacebookPost ticketFb = ticketFacebookGateway.getTicketByTicketId(ticketId);
			saveCommentToTicketFb(post, comment, ticketFb);
			return ticketFb;
		} else {
			TicketFacebookPost ticketFb = getTicketByPostAndUser(post, comment.getUser().getFbId());

			if (ticketFb != null) {
				saveCommentToTicketFb(post, comment, ticketFb);
				return ticketFb;
			} else {
				return null;
			}
		}
	}

	private TicketFacebookPost openNewTicket(FbPost post, FbPostClient client, FbPostComment comment) {

		TicketFacebookPost ticketFbPost = getTicketByPostAndUser(post, comment.getUser().getFbId());

		if (ticketFbPost == null) {
			UUID ticketChannelId = integrationGateway.getTicketChannelIdFromFbExternalIdClient(client.getExternalId());

			String protocol = generateProcotolUseCase.execute(ticketChannelId);

			Ticket ticketToSave = new Ticket(protocol);

			Ticket ticket = ticketGateway.saveTicket(ticketToSave);
			ticketFbPost = saveTicketFacebookPost(post, client, comment.getFbCommmetId(), ticket);

			savePostMedia(post, ticketFbPost.getId());
			saveCommentToTicketFb(post, comment, ticketFbPost);
		}

		return ticketFbPost;
	}

	private TicketFacebookPost getTicketByPostAndUser(FbPost post, String fbUserId) {
		return ticketFacebookGateway.findTicketByPostAndFbUser(post.getFbPostId(), fbUserId);
	}

	private void saveCommentToTicketFb(FbPost post, FbPostComment comment, TicketFacebookPost ticketFbPost) {

		if (!ticketFacebookGateway.alreadExitsTicketByComment(comment.getFbCommmetId())) {

			var model = new TicketFacebookComment(post, comment, ticketFbPost.getTicketId());

			ticketFacebookGateway.saveCommentToFbPost(model);
		}
	}

	private void savePostMedia(FbPost post, UUID ticketFbPostId) {
		post.getItems().forEach(item -> {
			// logica para carregar extensao??
			String extensao = ".jpg";

			String fileName = post.getFbPostId() + "_" + item.getFbItemId() + extensao;
			uploadMediaByPost(item.getFbMediaLink(), fileName);

			var media = TicketFacebookPostMedia.builder().fbMediaName(fileName).ticketFacebookPostId(ticketFbPostId)
					.build();

			ticketFacebookGateway.saveFacebookPostMedia(media);
		});
	}

	private TicketFacebookPost saveTicketFacebookPost(FbPost post, FbPostClient client, String fbCommentId,
			Ticket ticket) {
		TicketFacebookPost facebookPost = new TicketFacebookPost(post, client, fbCommentId, ticket.getId());
		return ticketFacebookGateway.saveFacebookPost(facebookPost);
	}

	private void uploadMediaByPost(String url, String filename) {

		File path = new File("/opt/digivox/citrus/attachments/socialMedia/" + filename);

		try {
			BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
			path.createNewFile();

			FileOutputStream output = new FileOutputStream(path);
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				output.write(dataBuffer, 0, bytesRead);
			}
			output.close();
		} catch (IOException e) {
			// logger
		}
	}
}
