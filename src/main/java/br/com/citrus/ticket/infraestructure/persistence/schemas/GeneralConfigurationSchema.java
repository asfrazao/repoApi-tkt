package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "configuracoes_gerais")
public class GeneralConfigurationSchema {

	@Id
	private UUID id;

	@Column(name = "nome")
	private String name;

	@Column(name = "pasta")
	private String folder;

	@Column(name = "url")
	private String url;

	@Column(name = "formato_protocolo")
	private String protocolFormat;

	@Column(name = "canal_atendimento_id")
	private String TicketChannelId;

}
