package br.com.citrus.ticket.domain.tickets.services.protocol;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.citrus.ticket.domain.tickets.gateways.TicketGateway;

public class ProtocolRecordDate implements Protocol {

	private TicketGateway gateway;

	public ProtocolRecordDate(TicketGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public String create(String protocolCode, String instanceId) {
		var protocol = new StringBuilder();

		var date = DateTimeFormatter.ofPattern("yyyyMMddmmSS").format(LocalDateTime.now());

		protocol.append(protocolCode == null ? "" : protocolCode);
		protocol.append(date);
		protocol.append(instanceId);
		protocol.append(new DecimalFormat("000000").format(getNextSequence(protocolCode)));

		return protocol.toString();
	}

	private int getNextSequence(String protocolCode) {
		return gateway.getNextSequenceFromProtocolFormat(protocolCode);
	}

}
