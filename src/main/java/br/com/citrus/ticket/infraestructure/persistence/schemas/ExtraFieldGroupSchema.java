package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.List;
import java.util.UUID;

import br.com.citrus.ticket.domain.extraFields.models.ExtraFieldGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grupo_campo_extra")
public class ExtraFieldGroupSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "nome")
	private String name;

	@Column(name = "tipo")
	private String type;

	@OneToMany(mappedBy = "extraFieldGroup")
	private List<ExtraFieldSchema> extraFields;

	public ExtraFieldGroup toModel() {
		return new ExtraFieldGroup(this.id, this.name, this.type, this.extraFields.stream().map(ExtraFieldSchema::toModel).toList());
	}
}
