package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "regiao")
public class RegionSchema {

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

	@NotNull
	@NotEmpty
	@NotBlank
	@Column(name = "codigo")
	private String code;

}