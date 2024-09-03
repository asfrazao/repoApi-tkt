package br.com.citrus.ticket.domain.tickets.services.protocol;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.tickets.gateways.TicketGateway;

@Service
public class ProtocolChannelJulianDate implements Protocol {

	private TicketGateway gateway;

	public ProtocolChannelJulianDate(TicketGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public String create(String protocolCode, String instanceId) {
		var date = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
		var str = new StringBuilder();

		return str.append(protocolCode != null ? protocolCode : "").append(date).append(instanceId)
				.append(new DecimalFormat("000000").format(getNextSequence(protocolCode))).toString();
	}

	private int getNextSequence(String protocolCode) {
		return gateway.getNextSequenceFromProtocolFormat(protocolCode);
	}

}
