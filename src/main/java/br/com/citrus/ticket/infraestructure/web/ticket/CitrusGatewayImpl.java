package br.com.citrus.ticket.infraestructure.web.ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class CitrusGatewayImpl implements CitrusGateway {

	private static final Logger logger = LoggerFactory.getLogger(CitrusGatewayImpl.class);

	@Value("${citrus.cx.api}")
	private String citrusUrl;

	@Override
	public void SendEmailNewTicket(UUID ticketId){

		RestTemplate restTemplate = RestTemplateBuilder();

		String url = citrusUrl + "/atendimento-aberto/enviar-email/" + ticketId;
		final HttpMethod post = HttpMethod.POST;

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, post, new HttpEntity<>(""), String.class);
		logger.info("Status Code do envio do E-mail: {}", responseEntity.getStatusCode());
	}

	private RestTemplate RestTemplateBuilder() {
		var factory = new SimpleClientHttpRequestFactory();

		factory.setConnectTimeout(30000);
		factory.setReadTimeout(30000);

		return new RestTemplate(factory);
	}

}
