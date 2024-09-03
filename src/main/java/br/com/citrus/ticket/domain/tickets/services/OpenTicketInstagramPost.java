package br.com.citrus.ticket.domain.tickets.services;

import java.util.List;

import br.com.citrus.ticket.domain.tickets.models.TicketInstagramPost;
import br.com.citrus.ticket.domain.tickets.vo.IgPostClient;
import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPost;
import br.com.citrus.ticket.domain.tickets.vo.instagram.IgPostComment;
public interface OpenTicketInstagramPost {

  List<TicketInstagramPost> execute(IgPost post, IgPostClient client, IgPostComment comment, String action);
}
