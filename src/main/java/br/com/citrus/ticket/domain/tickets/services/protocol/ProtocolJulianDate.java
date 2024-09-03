package br.com.citrus.ticket.domain.tickets.services.protocol;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

@Service
public class ProtocolJulianDate implements Protocol {

	@Override
	public String create(String protocolCode, String instanceId) {
		DateFormat ano = new SimpleDateFormat("yyyy");
		DateFormat dia = new SimpleDateFormat("D");

		DecimalFormat df = new DecimalFormat("000");
		String dataJuliana = ano.format(new Date()) + df.format(Integer.parseInt(dia.format(new Date())));
		DecimalFormat dfProtocolo = new DecimalFormat("000000000000");
		String times = String.valueOf(GregorianCalendar.getInstance().getTimeInMillis());
		return dfProtocolo.format(
				Long.parseLong(dataJuliana + times.substring(8, times.length()) + instanceId + getSequencial()));
	}

	private int getSequencial() {
		return 1;
	}

}
