package br.com.citrus.ticket.domain.tickets.vo.reclameAqui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Data
public class ReclameAquiPayload {

	private String id;

	@JsonProperty("id_ra")
	private String idRa;

	@JsonProperty("creation_date")
	private LocalDateTime creationDate;

	@JsonProperty("last_modification_date")
	private LocalDateTime lastModificationDate;

	@JsonProperty("complaint_title")
	private String complaintTitle;

	@JsonProperty("resolved_issue")
	private boolean resolvedIssue;

	private boolean closed;

	private boolean sync;

	@JsonProperty("id_user")
	private String idUser;

	@JsonProperty("id_client")
	private String idClient;

	private String protocol;

	@JsonProperty("id_company")
	private String companyId;

	@ToString.Exclude private List<ReclameAquiInteraction> interactions = new ArrayList<>();

	@ToString.Exclude private ReclameAquiUserDto user;

	@ToString.Exclude private List<ReclameAquiAttached> attached = new ArrayList<>();
}
