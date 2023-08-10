package com.lawencon.ticketjosep.dto.ticket;

import java.util.List;

public class TicketDetailResDto {
	private Long id;
	private String ticketCode;
	private String ticketTitle;
	private String ticketBody;
	private String ticketDate;
	private String profileName;
	private String productName;
	private String statusCode;
	private String statusName;
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
	public String getTicketTitle() {
		return ticketTitle;
	}
	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}
	public String getTicketBody() {
		return ticketBody;
	}
	public void setTicketBody(String ticketBody) {
		this.ticketBody = ticketBody;
	}
	public String getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
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


}
