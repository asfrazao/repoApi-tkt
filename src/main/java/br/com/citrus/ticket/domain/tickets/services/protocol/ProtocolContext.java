package br.com.citrus.ticket.domain.tickets.services.protocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.tickets.enums.ProtocolFormat;
import br.com.citrus.ticket.domain.tickets.gateways.TicketGateway;

@Service
public class ProtocolContext {

	@Autowired
	private TicketGateway gateway;

	private Protocol protocol;

	public String create(String protocolFormat, String protocolCode, String instanceId) {

		if (protocolFormat.equals(ProtocolFormat.CANAL_DATA_JULIANA.getLabel())) {
			protocol = new ProtocolChannelJulianDate(gateway);
		} else if (protocolFormat.equals(ProtocolFormat.DATA_JULIANA.getLabel())) {
			protocol = new ProtocolJulianDate();
		} else if (protocolFormat.equals(ProtocolFormat.REGISTRO_DATA.getLabel())) {
			protocol = new ProtocolRecordDate(gateway);
		}

		return protocol.create(protocolCode, instanceId);
	}

}
