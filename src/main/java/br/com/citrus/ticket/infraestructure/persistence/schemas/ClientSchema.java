package br.com.citrus.ticket.infraestructure.persistence.schemas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.citrus.ticket.domain.tickets.models.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cliente")
public class ClientSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "nome")
	private String name;

	@Column(name = "codigo")
	private String code;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String phone;

	@Column(name = "uf")
	private String UF;

	@Column(name = "observacao")
	private String observation;

	@Column(name = "nascimento")
	private LocalDateTime birthday;

	@Column(name = "telefone2")
	private String phone2;

	@Column(name = "celular")
	private String cellphone;

	@Column(name = "tipo_cliente_id")
	private UUID clientTypeId;

	@Column(name = "regiao_id")
	private UUID regionId;

	@Column(name = "grupo_campo_extra_id")
	private UUID extraFieldGroupId;

	@Column(name = "grupo_campo_extra_por_atendimento_id")
	private UUID extraFieldGroupByTicketId;

	@Column(name = "excluido")
	private boolean deleted;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "carteira_cliente_id")
	private UUID customerPortfolioId;

	@Column(name = "parceria_id")
	private UUID partnershipId;

	@Column(name = "ativo")
	private boolean active;

	@Column(name = "whatsapp")
	private String whatsapp;

	@Column(name = "forma_contato")
	private String formContact;

	@Column(name = "sfa_codigo")
	private String sfaCode;

	@Column(name = "twitter")
	private String twitter;

	@Column(name = "facebook")
	private String facebook;

	@Column(name = "token")
	private String token;

	@Column(name = "data_cadastro")
	private LocalDateTime registerDate;

	@Column(name = "grau_parentesco")
	private String degreeOfkinship;

	@Column(name = "conexao_genetica")
	private String geneticConnection;

	@Column(name = "data_ativacao")
	private LocalDateTime activationDate;

	@Column(name = "figura_publica")
	private boolean publicFigure;

//	@Column(name = "idioma")
//	private String language;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClientExtraFieldSchema> extraFields = new ArrayList<>();


	public ClientSchema(UUID id) {
		this.id = id;
	}

	public ClientSchema(Client model) {
		this.id = model.getId();
		this.name = model.getName();
		this.code = model.getCode();
		this.email = model.getEmail();
		this.phone = model.getPhone();
		this.UF = model.getUF();
		this.observation = model.getObservation();
		this.birthday = model.getBirthday();
		this.phone2 = model.getPhone2();
		this.cellphone = model.getCellphone();
		this.clientTypeId = model.getClientTypeId();
		this.regionId = model.getRegionId();
		this.extraFieldGroupId = model.getExtraFieldGroupId();
		this.extraFieldGroupByTicketId = model.getExtraFieldGroupByTicketId();
		this.deleted = model.isDeleted();
		this.avatar = model.getAvatar();
		this.customerPortfolioId = model.getCustomerPortfolioId();
		this.partnershipId = model.getPartnershipId();
		this.active = model.isActive();
		this.whatsapp = model.getWhatsapp();
		this.formContact = model.getFormContact();
		this.sfaCode = model.getSfaCode();
		this.twitter = model.getTwitter();
		this.facebook = model.getFacebook();
		this.token = model.getToken();
		this.registerDate = model.getRegisterDate();
		this.degreeOfkinship = model.getDegreeOfkinship();
		this.geneticConnection = model.getGeneticConnection();
		this.activationDate = model.getActivationDate();
		this.publicFigure = model.isPublicFigure();
//		this.language = model.getLanguage();
	}

	public Client toModel() {

		return new Client(this.id,
				this.name,
				this.code,
				this.email,
				this.phone,
				this.UF,
				this.observation,
				this.birthday,
				this.phone2,
				this.cellphone,
				this.clientTypeId,
				this.regionId,
				this.extraFieldGroupId,
				this.extraFieldGroupByTicketId,
				this.deleted,
				this.avatar,
				this.customerPortfolioId,
				this.partnershipId,
				this.active,
				this.whatsapp,
				this.formContact,
				this.sfaCode,
				this.twitter,
				this.facebook,
				this.token,
				this.registerDate,
				this.degreeOfkinship,
				this.geneticConnection,
				this.activationDate,
				this.publicFigure/*,
				this.language*/
				);
	}
}
