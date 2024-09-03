package br.com.citrus.ticket.infraestructure.persistence.schemas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "grupo_campo_extra_resposta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraFieldResponseSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "valor")
	private String value;

	@Column(name = "observacao")
	private String observation;

	@Column(name = "grupo_campo_extra_campo_id")
	private UUID extraFieldGroupFieldId;

	@Column(name = "grupo_campo_extra_campo_link_id")
	private UUID extraFieldGroupFieldLinkId;

	@Column(name = "dono_id")
	private UUID ownerId;

	@Column(name = "tipo")
	private String type;

	@Column(name = "label_option")
	private String labelOption;

	@Column(name= "codigo")
	private String code;
}
