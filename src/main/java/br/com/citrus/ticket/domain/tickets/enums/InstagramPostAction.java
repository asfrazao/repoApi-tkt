package br.com.citrus.ticket.domain.tickets.enums;

import java.util.Arrays;

import org.webjars.NotFoundException;

import lombok.Getter;

public enum InstagramPostAction {

	ADD("create"), REMOVE("delete");

	private static final String ACTION_NOT_FOUND = "ACTION NOT FOUND";
	@Getter
	private String action;

	private InstagramPostAction(String action) {
		this.action = action;
	}

	public static InstagramPostAction getByValue(String value) {
		return Arrays.stream(InstagramPostAction.values())
				.filter(a -> a.action.equals(value))
				.findFirst()
				.orElseThrow(() -> new NotFoundException(ACTION_NOT_FOUND));
		
	}
}
