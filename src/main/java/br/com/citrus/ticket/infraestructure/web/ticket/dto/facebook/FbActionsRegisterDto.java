package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import br.com.citrus.ticket.infraestructure.web.ticket.dto.FbPostClientDto;
import lombok.Data;

@Data
public class FbActionsRegisterDto {

	private FbPostDto post;
	private FbPostCommentDto comment;
	private FbPostClientDto client;
	private String action;

}
