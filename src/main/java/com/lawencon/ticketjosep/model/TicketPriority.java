package com.lawencon.ticketjosep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ticket_priority")
public class TicketPriority extends Base {
	@Column(name = "ticket_priority_code", length = 5, nullable = false)
	private String ticketPriorityCode;

	@Column(name = "ticket_priority_name", length = 20, nullable = false)
	private String ticketPriorityName;

	@Column(name = "ticket_priority_limit", nullable = false)
	private Integer ticketPriorityLimit;

	public String getTicketPriorityName() {
		return ticketPriorityName;
	}

	public void setTicketPriorityName(String ticketPriorityName) {
		this.ticketPriorityName = ticketPriorityName;
	}

	public Integer getTicketPriorityLimit() {
		return ticketPriorityLimit;
	}

	public void setTicketPriorityLimit(Integer ticketPriorityLimit) {
		this.ticketPriorityLimit = ticketPriorityLimit;
	}

	public String getTicketPriorityCode() {
		return ticketPriorityCode;
	}

	public void setTicketPriorityCode(String ticketPriorityCode) {
		this.ticketPriorityCode = ticketPriorityCode;
	}

}
