package com.lawencon.ticketjosep.dto.ticketpriority;

public class TicketPriorityResDto {
	private Long id;
	private String ticketPriorityCode;
	private String ticketPriorityName;
	private Integer ticketPriorityLimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
