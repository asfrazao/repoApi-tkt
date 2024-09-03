package br.com.citrus.ticket.domain.tickets.enums;

import java.util.Arrays;

import org.webjars.NotFoundException;

import lombok.Getter;

public enum FacebookPostAction {

	ADD("add"), EDITED("edited"), REMOVE("remove");

	private static final String ACTION_NOT_FOUND = "ACTION NOT FOUND";
	@Getter
	private String action;

	private FacebookPostAction(String action) {
		this.action = action;
	}

	public static FacebookPostAction getByValue(String value) {
		return Arrays.stream(FacebookPostAction.values())
				.filter(a -> a.action.equals(value))
				.findFirst()
				.orElseThrow(() -> new NotFoundException(ACTION_NOT_FOUND));
		
	}
}
