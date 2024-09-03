package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.gateways;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.tickets.gateways.ClientGateway;
import br.com.citrus.ticket.domain.tickets.models.Client;
import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.vo.reclameAqui.ReclameAquiUserDto;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.ClientRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.ClientTypeRepository;
import br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.tickets.RegionRepository;
import br.com.citrus.ticket.infraestructure.persistence.schemas.ClientSchema;

@Component
public class ClientJpaGateway implements ClientGateway {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientTypeRepository clientTypeRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Override
	public Client searchOrCreateUserToSaveTicket(ReclameAquiUserDto user, Ticket ticket, String clientTypeCode,
			String regionName) {
		Client client = findClientByEmail(user.getEmail());

		if (client == null) {
			UUID clientTypeId = getClientTypeIdByCode(clientTypeCode);
			UUID regionId = getRegionIdToReclameAquiClient(regionName);

			var model = new Client(user.getName(), user.getEmail(), generateClientCode(), clientTypeId, regionId);
			client = createClient(model);
		}

		return client;
	}

	private Client findClientByEmail(String email) {
		var schema = clientRepository.findClientByEmail(email);
		if (schema != null) {
			return schema.toModel();
		}
		return null;
	}

	private String generateClientCode() {
		Random rnd = new Random();
		var code = String.format("%06d", rnd.nextInt(999999));

		if (alreadyExistsClientWithThisCode(code)) {
			generateClientCode();
		}
		return code;
	}

	private Client createClient(Client model) {
		var client = clientRepository.save(new ClientSchema(model));
		return client.toModel();
	}

	private UUID getRegionIdToReclameAquiClient(String regionName) {
		return regionRepository.getRegionIdToReclameAquiClient(regionName);
	}

	private UUID getClientTypeIdByCode(String code) {
		return clientTypeRepository.getClientTypeIdByCode(code);
	}

	private boolean alreadyExistsClientWithThisCode(String code) {
		return clientRepository.alreadyExistsClientWithThisCode(code) > 0;
	}

}
