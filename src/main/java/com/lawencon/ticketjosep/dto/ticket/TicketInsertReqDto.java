package com.lawencon.ticketjosep.dto.ticket;

import java.util.List;

import com.lawencon.ticketjosep.dto.file.FileDto;

public class TicketInsertReqDto {
	private String ticketTitle;
	private String ticketBody;
	private Long productId;
	private Long ticketPriorityId;
	private List<FileDto> fileLists;

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getTicketPriorityId() {
		return ticketPriorityId;
	}

	public void setTicketPriorityId(Long ticketPriorityId) {
		this.ticketPriorityId = ticketPriorityId;
	}

	public List<FileDto>  getFileLists() {
		return fileLists;
	}

	public void setFileLists(List<FileDto>  fileLists) {
		this.fileLists = fileLists;
	}

}
