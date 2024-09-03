package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import java.util.ArrayList;
import java.util.List;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbCommentPayload;
import br.com.citrus.ticket.domain.tickets.vo.facebook.FbCommentPayloadChange;
import lombok.Data;

@Data
public class FbCommentPayloadDto {

	private String id;
	private int time;
	private List<FbCommentPayloadChangeDto> changes;

	public FbCommentPayloadDto(String id, int time, List<FbCommentPayloadChangeDto> changes) {
		this.id = id;
		this.time = time;
		this.changes = changes;
	}

	public FbCommentPayload toValueObject() {

		List<FbCommentPayloadChange> changesList = new ArrayList<>();
		changes.forEach(c -> changesList.add(c.toValueObject()));
		
		return new FbCommentPayload(
				id,
				time,
				changesList);
	}

}
