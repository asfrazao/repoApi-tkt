package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "procedimento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "nome")
	private String name;

	@Column(name = "observacao")
	private String note;

	@Column(name = "descricao")
	private String description;

	@Column(name = "categoria_id")
	private String idCategory;

	@Column(name = "visivel_usuario_cliente")
	private boolean isVisibleToClientUser;

	private Integer status;

	@ManyToAny
	@JoinTable(name = "ocorrencia_procedimento_artigos", joinColumns = @JoinColumn(name = "procedimento_id"), inverseJoinColumns = @JoinColumn(name = "ocorrencia_id"))
	private List<OcurrencySchema> ocurrencySchema;

}
