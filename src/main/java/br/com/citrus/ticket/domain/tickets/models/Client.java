package br.com.citrus.ticket.domain.tickets.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Client implements Serializable {

	private static final long serialVersionUID = 6112462753826539273L;

	private UUID id;
	private String name;

	@NotNull
	@NotEmpty
	@NotBlank
	private String code;
	private String email;
	private String phone;
	private String UF;
	private String observation;
	private LocalDateTime birthday;
	private String phone2;
	private String cellphone;

	@NotNull
	private UUID clientTypeId;

	@NotNull
	private UUID regionId;

	private UUID extraFieldGroupId;
	private UUID extraFieldGroupByTicketId;
	private boolean deleted;
	private String avatar;
	private UUID customerPortfolioId;
	private UUID partnershipId;
	private boolean active;
	private String whatsapp;
	private String formContact;
	private String sfaCode;
	private String twitter;
	private String facebook;
	private String token;
	private LocalDateTime registerDate;
	private String degreeOfkinship;
	private String geneticConnection;
	private LocalDateTime activationDate;
	private boolean publicFigure;
//	private String language;

	public Client(String name, String email, String code, UUID clientTypeId, UUID regionId) {
		this.name = name;
		this.email = email;
		this.code = code;
		this.registerDate = LocalDateTime.now();
		this.clientTypeId = clientTypeId;
		this.regionId = regionId;
	}

}
