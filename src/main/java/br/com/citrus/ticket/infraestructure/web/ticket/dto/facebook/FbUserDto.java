package br.com.citrus.ticket.infraestructure.web.ticket.dto.facebook;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.facebook.FbUser;
import lombok.Data;

@Data
public class FbUserDto {

	private UUID id;

	@JsonProperty("fb_id")
	private String fbId;

	@JsonProperty("fb_name")
	private String fbName;

	public FbUserDto(UUID id, String fbId, String fbName) {
		this.id = id;
		this.fbId = fbId;
		this.fbName = fbName;
	}

	public FbUser toValueObject() {
		return new FbUser(this.id, this.fbId, this.fbName);
	}

}
