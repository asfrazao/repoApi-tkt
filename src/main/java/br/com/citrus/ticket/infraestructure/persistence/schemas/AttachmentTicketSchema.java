package br.com.citrus.ticket.infraestructure.persistence.schemas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "atendimento_anexos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentTicketSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "atendimento_id")
	private UUID ticketId;
	
	@Column(name = "anexo")
	private String attachment;

	@Column(name = "usuario_id")
	private UUID userId;

	@Column(name = "data")
	private LocalDateTime createdAt;

}
