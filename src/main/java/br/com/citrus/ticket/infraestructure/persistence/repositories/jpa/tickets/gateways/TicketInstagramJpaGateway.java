package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.gateways;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.tickets.gateways.TicketInstagramGateway;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramComment;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPost;
import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPostMedia;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.instagram.TicketInstagramCommentRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.instagram.TicketInstagramPostMediaRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.instagram.TicketInstagramPostRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.instagram.TicketInstagramCommentSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.instagram.TicketInstagramPostMediaSchema;
import br.com.citrus.ticket.infraestructure.persistence.schemas.instagram.TicketInstagramPostSchema;

@Component
public class TicketInstagramJpaGateway implements TicketInstagramGateway {

  @Autowired
  private TicketInstagramPostRepository ticketInstagramPostRepository;
  @Autowired
	private TicketInstagramPostMediaRepository ticketIgPostMediaRepository;
  @Autowired
  private TicketInstagramCommentRepository ticketIgCommentRepository;

  private static final Logger logger = LoggerFactory.getLogger(TicketInstagramJpaGateway.class);
  private static final String SAVE_INFO_TO_TICKET_INSTAGRAM_POST = "saved instagram post information to new ticket with id: {}";
  private static final String SAVE_INFO_TO_TICKET_INSTAGRAM_COMM = "saved instagram comment, ticket id: {}, instagram user: {}";

  @Override
  public TicketInstagramPost findTicketByPostAndIgUser(String igPostId, String igUserId) {
    if (igPostId == null) {
      return null;
    }
    List<TicketInstagramPostSchema> result = ticketInstagramPostRepository.findTicketIgPostByIgUser(igPostId, igUserId);
    if (result.size() > 0) {
      return result.get(0).toModel();
    }
    return null;
  }

  @Override
  public TicketInstagramPost findTicketByComment(String igCommentId) {
    if (igCommentId == null) {
      return null;
    }
    List<TicketInstagramPostSchema> result = ticketInstagramPostRepository.findTicketIgCommentId(igCommentId);
    if (result.size() > 0) {
      return result.get(0).toModel();
    }
    return null;
  }

  @Override
  public TicketInstagramPost findTicketByCommentAndIgUser(String igCommentId, String igUserId){
    if (igCommentId == null) {
      return null;
    }
    List<TicketInstagramPostSchema> result = ticketInstagramPostRepository.findTicketIgCommentIdByIgUser(igCommentId, igUserId);
    if (result.size() > 0) {
      return result.get(0).toModel();
    }
    return null;
  }

  @Override
  public TicketInstagramPost saveInstagramPost(TicketInstagramPost ticketIgPost) {
    try {
      TicketInstagramPostSchema ticketInstagramPostSchema = new TicketInstagramPostSchema(ticketIgPost);
      var schema = ticketInstagramPostRepository.save(ticketInstagramPostSchema);
      logger.info(SAVE_INFO_TO_TICKET_INSTAGRAM_POST, schema.getTicketId(), schema.getIgUserUsername());
			return schema.toModel();
    } catch (Exception e) {
      logger.error("Error on save post instagram");
			throw e;
    }
  }

  @Override
  public UUID saveInstagramPostMedia(TicketInstagramPostMedia media) {
    var schema = new TicketInstagramPostMediaSchema();

    schema.setIgMediaName(media.getIgMediaName());
    schema.setTicketInstagramPostId(media.getTicketInstagramPostId());

    var persist = ticketIgPostMediaRepository.save(schema);
    return persist.getId();
  }

  @Override
  public boolean alreadExitsTicketByComment(String igCommentId) {
    var result = ticketIgCommentRepository.getCountTicketPostByIgCommentId(igCommentId);
		return result > 0;
  }

  @Override
  public void saveCommentToIgPost(TicketInstagramComment comment) {
    var schema = new TicketInstagramCommentSchema(comment);

    ticketIgCommentRepository.save(schema);

    logger.info(SAVE_INFO_TO_TICKET_INSTAGRAM_COMM, schema.getTicketId(), schema.getIgUserName());
  }

  @Override
  public TicketInstagramPost findTicketByPostAndCommentId(String igPostId, String igCommmetId) {
    List<TicketInstagramPostSchema> result = ticketInstagramPostRepository.findTicketByPostAndCommentId(igPostId, igCommmetId);

    if (result.size() > 0) {
      return result.get(0).toModel();
    }
    return null;
  }
  
}
