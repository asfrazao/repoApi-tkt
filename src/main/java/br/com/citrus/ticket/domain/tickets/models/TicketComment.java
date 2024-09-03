package br.com.citrus.ticket.domain.tickets.models;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketComment {

	private UUID id;
	private String comment;
	private String attachment;
	private LocalDateTime date;
	private boolean system;
	private boolean sendSMS;
	private String code;
	private boolean visibleCustomer;
	private String socialMediaCommentId;
	private boolean sentSocialMedia;
	private boolean waitingCustomerFeedback;
	private boolean waitingCustomerAuthFinish;
	private boolean resolved;
	private UUID userId;
	private UUID ticketId;
	private String copy;
	private String notificationMedia;
	private boolean success;

	public TicketComment(String comment, String socialMediaCommentId, UUID ticketId) {
		this.comment = comment;
		this.date = LocalDateTime.now();
		this.socialMediaCommentId = socialMediaCommentId;
		this.ticketId = ticketId;
	}

}