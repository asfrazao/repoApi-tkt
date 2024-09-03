package br.com.citrus.ticket.domain.tickets.models;

import java.io.Serializable;
import java.util.UUID;

import br.com.citrus.ticket.infraestructure.persistence.schemas.reclameAqui.IntegrationReclameAquiSchema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegrationReclameAqui implements Serializable {

	private static final long serialVersionUID = 4197518930343022522L;

	private UUID id;
	private String email;
	private String password;
	private UUID ticketChannelIntegrationId;
	private String name;
	private String description;
	private UUID sectorId;
	private Boolean enabled;
	private String automaticAnswer;
	private Boolean enabledTicketConversion;
	private UUID clientTypeId;
	private UUID regionId;
	private UUID occurrenceTypeId;
	private UUID occurrenceId;
	private UUID solutionId;
	private UUID creatorId;
	private Boolean enabledAutoDistribution;

	public IntegrationReclameAqui(IntegrationReclameAquiSchema schema) {
		this.id = schema.getId();
		this.email = schema.getEmail();
		this.password = schema.getPassword();
		this.ticketChannelIntegrationId = schema.getTicketChannelIntegrationId();
		this.name = schema.getName();
		this.description = schema.getDescription();
		this.sectorId = schema.getSectorId();
		this.enabled = schema.getEnabled();
		this.automaticAnswer = schema.getAutomaticAnswer();
		this.enabledTicketConversion = schema.getEnabledTicketConversion();
		this.clientTypeId = schema.getClientTypeId();
		this.regionId = schema.getRegionId();
		this.occurrenceTypeId = schema.getOccurrenceTypeId();
		this.occurrenceId = schema.getOccurrenceId();
		this.solutionId = schema.getSolutionId();
		this.creatorId = schema.getCreatorId();
		this.enabledAutoDistribution = schema.getEnabledAutoDistribution();
	}

}
