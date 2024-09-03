package br.com.citrus.ticket.infraestructure.web.ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.citrus.ticket.domain.tickets.models.Ticket;
import br.com.citrus.ticket.domain.tickets.models.TicketIssueReclameAqui;
import br.com.citrus.ticket.infraestructure.web.ticket.dto.RaWorkerTIcketIdUpdateDto;

@Component
public class RaWorkerGatewayImpl implements RaWorkerGateway {

	private static final Logger logger = LoggerFactory.getLogger(RaWorkerGatewayImpl.class);

	@Value("${worker.ra.api}")
	private String raWorkerUrl;

	@Override
	public void syncTicketRaWorker(Ticket ticket, TicketIssueReclameAqui issueRa) throws Exception {
		var protocol = new RaWorkerTIcketIdUpdateDto(ticket.getProtocol());

		RestTemplate restTemplate = RestTemplateBuilder();

		HttpEntity<RaWorkerTIcketIdUpdateDto> payload = new HttpEntity<>(protocol);

		String fooResourceUrl = raWorkerUrl + "/ticket/" + issueRa.getId();
		final HttpMethod put = HttpMethod.PUT;
		if (put != null) {
			ResponseEntity<String> responseEntity = restTemplate.exchange(fooResourceUrl, put, payload, String.class);
			logger.info("response RA-WORKER UPDATE TICKET PROTOCOL: {}", responseEntity.getStatusCode());
		} else {
			throw new Exception();
		}
	}

	private RestTemplate RestTemplateBuilder() {
		var factory = new SimpleClientHttpRequestFactory();

		factory.setConnectTimeout(3000);
		factory.setReadTimeout(3000);

		return new RestTemplate(factory);
	}

}
