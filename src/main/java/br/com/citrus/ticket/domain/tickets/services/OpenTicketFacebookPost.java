package br.com.citrus.ticket.domain.tickets.services;

import java.util.List;

import br.com.citrus.ticket.domain.tickets.models.TicketFacebookPost;
import br.com.citrus.ticket.domain.tickets.vo.FbPostClient;
import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPost;
import br.com.citrus.ticket.domain.tickets.vo.facebook.FbPostComment;

public interface OpenTicketFacebookPost {

	List<TicketFacebookPost> execute(FbPost post, FbPostClient client, FbPostComment comment);
}
