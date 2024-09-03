package br.com.citrus.ticket.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid UUID format")
public class InvalidUUIDFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidUUIDFormatException() {
		super("Invalid UUID format");
	}
}
