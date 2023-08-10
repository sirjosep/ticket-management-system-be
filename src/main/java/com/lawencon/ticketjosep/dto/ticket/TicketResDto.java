package com.lawencon.ticketjosep.dto.ticket;

import java.util.List;

public class TicketResDto {
	private Long id;
	private String ticketCode;
	private String ticketStatusCode;
	private String ticketStatus;
	private String ticketDate;
	private String priorityCode;
	private String priorityName;
	private List<FileTicketResDto> files;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getTicketStatusCode() {
		return ticketStatusCode;
	}
	public void setTicketStatusCode(String ticketStatusCode) {
		this.ticketStatusCode = ticketStatusCode;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}
	public String getPriorityName() {
		return priorityName;
	}
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	public List<FileTicketResDto> getFiles() {
		return files;
	}
	public void setFiles(List<FileTicketResDto> files) {
		this.files = files;
	}
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	
	
}
