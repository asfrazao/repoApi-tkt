package br.com.citrus.ticket.domain.tickets.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.tickets.enums.InstagramPostAction;
import br.com.citrus.ticket.domain.tickets.gateways.IntegrationGateway;
import br.com.citrus.ticket.domain.tickets.gateways.TicketGateway;
import br.com.citrus.ticket.domain.tickets.gateways.TicketInstagramGateway;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramComment;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPost;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPostMedia;
import br.com.citrus.ticket.domain.tickets.services.OpenTicketInstagramPost;
import br.com.citrus.ticket.domain.tickets.usecases.GenerateProcotolNumberUseCase;
import br.com.citrus.ticket.domain.tickets.vo.IgPostClient;
import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPost;
import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPostComment;

@Service
public class OpenTicketInstagramPostImpl implements OpenTicketInstagramPost {

  @Autowired
  private TicketInstagramGateway ticketInstagramGateway;
  @Autowired
	private IntegrationGateway integrationGateway;
  @Autowired
	private GenerateProcotolNumberUseCase generateProcotolUseCase;
  @Autowired
	private TicketGateway ticketGateway;

  @Override
  public List<TicketInstagramPost> execute(IgPost post, IgPostClient client, IgPostComment comment, String action ) {
    List<TicketInstagramPost> result = new ArrayList<>();
    if(action.equals(InstagramPostAction.ADD.getAction())){
      comment.getPayload().getChanges().forEach(change -> {
        String parentId = change.getValue().getParentId();
        createNewTicketOrAddComment(post, client, comment, parentId, result);
      });
    }
    if(action.equals(InstagramPostAction.REMOVE.getAction())){
      disabledTicket(post, comment, result);
    }
   
    return result;
  }

  	private void disabledTicket(IgPost post, IgPostComment comment, List<TicketInstagramPost> result) {
		var ticketPost = ticketInstagramGateway.findTicketByPostAndCommentId(post.getIgPostId(),
				comment.getIgCommentId());

		if (ticketPost != null) {
			Ticket ticket = ticketGateway.getTicketById(ticketPost.getTicketId());
			ticket.cancel();

			ticketGateway.updateTicket(ticket);

			result.add(ticketPost);
		}
	}

  private void createNewTicketOrAddComment(IgPost post, IgPostClient client, IgPostComment comment, String parentId,  List<TicketInstagramPost> result) {
    TicketInstagramPost ticketIg = null;
    String ownerId = comment.getUser().getIgId();
    String postOwnerId  = post.getUser().getIgId();

    if(ownerId.equals(postOwnerId)){
      TicketInstagramPost ticketExist = ticketInstagramGateway.findTicketByComment(parentId); 

      if(ticketExist != null){
        ticketIg = saveCommentFromPost(post, comment, ticketExist);
      }
    } else {
      TicketInstagramPost ticketIgExistByParent = ticketInstagramGateway.findTicketByCommentAndIgUser(parentId,comment.getUser().getIgId());

      if (ticketIgExistByParent != null) {

        ticketIg = saveCommentFromPost(post, comment, ticketIgExistByParent);
      } else {
        ticketIg = openNewTicket(post, client, comment);
      }
    }
    result.add(ticketIg);
  }

  private TicketInstagramPost saveCommentFromPost(IgPost post, IgPostComment comment, TicketInstagramPost ticketIgPost) {
    saveCommentToTicketIg(post, comment, ticketIgPost);
    return ticketIgPost;
  }

  private TicketInstagramPost openNewTicket(IgPost post, IgPostClient client, IgPostComment comment) {
    TicketInstagramPost ticketIgPost = null;
    UUID ticketChannelId = integrationGateway.getTicketChannelIdFromIgExternalIdClient(client.getExternalId());
    String protocol = generateProcotolUseCase.execute(ticketChannelId);

    Ticket ticketToSave = new Ticket(protocol);

    Ticket ticket = ticketGateway.saveTicket(ticketToSave);

    ticketIgPost = saveTicketInstagramPost(post, client, comment.getIgCommentId(), ticket);

    savePostMedia(post, ticketIgPost.getId());

    saveCommentToTicketIg(post, comment, ticketIgPost);

    return ticketIgPost;
  }

  private TicketInstagramPost saveTicketInstagramPost(IgPost post, IgPostClient client, String igCommentId, Ticket ticket) {
    TicketInstagramPost instagramPost = new TicketInstagramPost(post, client, igCommentId, ticket.getId());
    return ticketInstagramGateway.saveInstagramPost(instagramPost);
  }

  private String getFileExtension(String url) {
    String extensao = ".jpeg";
    Pattern pattern = Pattern.compile("\\.(\\w+)(?:\\?|$)");
    Matcher matcher = pattern.matcher(url);
    if (matcher.find()) {
        extensao = "." + matcher.group(1);
    }
    return extensao;
}

  private void savePostMedia(IgPost post, UUID ticketIgPostId) {
    post.getItems().forEach(item -> {
			String extensao = getFileExtension(item.getIgLink());

			String fileName = post.getIgPostId() + "_" + item.getIgItemId() + extensao;
			uploadMediaByPost(item.getIgLink(), fileName);

      var media = TicketInstagramPostMedia.builder().igMediaName(fileName).ticketInstagramPostId(ticketIgPostId)
          .build();

      ticketInstagramGateway.saveInstagramPostMedia(media);
    });
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

  private void saveCommentToTicketIg(IgPost post, IgPostComment comment, TicketInstagramPost ticketIgPost) {

    if (!ticketInstagramGateway.alreadExitsTicketByComment(comment.getIgCommentId())) {

			var model = new TicketInstagramComment(post, comment, ticketIgPost.getTicketId());

			ticketInstagramGateway.saveCommentToIgPost(model);
		}
  }
  
}
