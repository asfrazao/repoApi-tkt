package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;

import br.com.citrus.ticket.infraestructure.web.ticket.dto.IgPostClientDto;
import lombok.Data;

@Data
public class IgActionsRegisterDto {
  
  private IgPostDto post;
  private IgPostCommentDto comment;
  private IgPostClientDto client;
  private String action;
}
