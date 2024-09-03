package br.com.citrus.ticket.infraestructure.persistence.schemas.facebook;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "atendimento_facebook_post_media")
public class TicketFacebookPostMediaSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "fb_media_nome")
	private String fbMediaName;

	@Column(name = "atendimento_facebook_post_id")
	private UUID ticketFacebookPostId;

}
