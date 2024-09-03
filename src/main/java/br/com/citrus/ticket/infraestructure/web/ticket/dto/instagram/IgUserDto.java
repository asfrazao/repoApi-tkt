package br.com.citrus.ticket.infraestructure.web.ticket.dto.instagram;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.citrus.ticket.domain.tickets.vo.instagram.IgUser;
import lombok.Data;

@Data
public class IgUserDto {
  
  private UUID id;

  @JsonProperty("instagram_id")
  private String igId;

  @JsonProperty("instagram_name")
  private String igName;

  @JsonProperty("instagram_username")
  private String igUsername;

  public IgUserDto(UUID id, String igId, String igName, String igUsername) {
    this.id = id;
    this.igId = igId;
    this.igName = igName;
    this.igUsername = igUsername;
  }

  public IgUser toValueObject() {
    return new IgUser(this.id, this.igId, this.igName, this.igUsername);
  }
}
