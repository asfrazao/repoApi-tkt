package br.com.citrus.ticket.domain.tickets.usecases.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.tickets.gateways.TicketGateway;
import br.com.citrus.ticket.domain.tickets.services.protocol.ProtocolContext;
import br.com.citrus.ticket.domain.tickets.usecases.GenerateProcotolNumberUseCase;

@Service
public class GenerateProcotolNumberUseCaseImpl implements GenerateProcotolNumberUseCase {

	private static final String ERRO_PROTOCOLO = "Erro ao criar protocolo";

	private static final Logger logger = LoggerFactory.getLogger(GenerateProcotolNumberUseCase.class);

	@Value("${app.instance.id}")
	private String instanceId;

	@Autowired
	private ProtocolContext protocol;

	@Autowired
	private TicketGateway gateway;

	@Override
	public String execute(UUID ticketChannelId) {
		try {
			if (ticketChannelId == null) {
				ticketChannelId = gateway.getGeneralConfigTicketChannelId();
			}

			String protocolCode = gateway.getTicketProtocolCodeById(ticketChannelId);

			var protocolFormat = gateway.getGeneralConfigProtocolFormat();

			return protocol.create(protocolFormat, protocolCode, instanceId);
		} catch (Exception e) {
			logger.error(ERRO_PROTOCOLO);
			throw e;
		}
	}

}
