package br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "integracao_reclame_aqui")
public class IntegrationReclameAquiSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String password;

	@Column(name = "integracao_canal_atendimento_id")
	private UUID ticketChannelIntegrationId;

	@Column(name = "nome")
	private String name;

	@Column(name = "descricao")
	private String description;

	@Column(name = "setor_id")
	private UUID sectorId;

	@Column(name = "habilitado")
	private Boolean enabled;

	@Column(name = "resposta_automatica")
	private String automaticAnswer;

	@Column(name = "habilitar_conversao_atendimento")
	private Boolean enabledTicketConversion;

	@Column(name = "tipo_cliente_id")
	private UUID clientTypeId;

	@Column(name = "regiao_id")
	private UUID regionId;

	@Column(name = "tipo_ocorrencia_id")
	private UUID occurrenceTypeId;

	@Column(name = "ocorrencia_id")
	private UUID occurrenceId;

	@Column(name = "solucao_id")
	private UUID solutionId;

	@Column(name = "criador_id")
	private UUID creatorId;

	@Column(name = "habilitar_auto_distribuicao")
	private Boolean enabledAutoDistribution;

	@Column(name = "client_id_worker")
	private String clientId;
}
