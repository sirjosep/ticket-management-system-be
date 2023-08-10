package com.lawencon.ticketjosep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ticket_status")
public class TicketStatus extends Base{
	@Column(name = "ticket_status_code", length = 5, nullable = false)
	private String ticketStatusCode;

	@Column(name = "ticket_status_name", nullable = false)
	private String ticketStatusName;
	
	public String getTicketStatusCode() {
		return ticketStatusCode;
	}

	public void setTicketStatusCode(String ticketStatusCode) {
		this.ticketStatusCode = ticketStatusCode;
	}

	public String getTicketStatusName() {
		return ticketStatusName;
	}

	public void setTicketStatusName(String ticketStatusName) {
		this.ticketStatusName = ticketStatusName;
	}
}
