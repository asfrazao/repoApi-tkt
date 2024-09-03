package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbCommentPayloadChangeValueFrom;
import lombok.Data;

@Data
public class FbCommentPayloadChangeValueFromDto {

	private String id;
	private String name;

	public FbCommentPayloadChangeValueFrom toValueObject() {
		return new FbCommentPayloadChangeValueFrom(id, name);
	}
}
