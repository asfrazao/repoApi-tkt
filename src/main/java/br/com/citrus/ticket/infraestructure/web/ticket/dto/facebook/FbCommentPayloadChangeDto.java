package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbCommentPayloadChange;
import lombok.Data;

@Data
public class FbCommentPayloadChangeDto {

	private FbCommentPayloadChangeValueDto value;
	private String field;

	public FbCommentPayloadChangeDto(FbCommentPayloadChangeValueDto value, String field) {
		this.field = field;
		this.value = value;
	}

	public FbCommentPayloadChange toValueObject() {
		return new FbCommentPayloadChange(value.toValueObject(), field);
	}

}
