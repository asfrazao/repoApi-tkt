package br.com.citrus.ticket.infraestructure.persistence.schemas.instagram;

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
@Table(name = "atendimento_instagram_post_media")
public class TicketInstagramPostMediaSchema {
  @Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "ig_media_nome")
	private String igMediaName;

	@Column(name = "atendimento_instagram_post_id")
	private UUID ticketInstagramPostId;
}
