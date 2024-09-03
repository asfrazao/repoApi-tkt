package br.com.citrus.ticket.infraestructure.persistence.schemas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "setor")
public class SectorSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "nome")
	private String name;

	@Column(name = "descricao")
	private String description;

	@Column(name = "assinatura")
	private String signature;

	@Column(name = "enviar_nome_usuario_email_cliente")
	private boolean sendNameUserEmailClient;

	@Column(name = "ativo")
	private boolean active;

}