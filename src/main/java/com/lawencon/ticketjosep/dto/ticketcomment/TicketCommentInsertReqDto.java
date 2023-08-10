package com.lawencon.ticketjosep.dto.ticketcomment;

import java.util.List;

import com.lawencon.ticketjosep.dto.file.FileDto;

public class TicketCommentInsertReqDto {
	private Long ticketId;
	private String ticketCommentBody;
	private List<FileDto>  files;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketCommentBody() {
		return ticketCommentBody;
	}

	public void setTicketCommentBody(String ticketCommentBody) {
		this.ticketCommentBody = ticketCommentBody;
	}

	public List<FileDto> getFiles() {
		return files;
	}

	public void setFiles(List<FileDto>  files) {
		this.files = files;
	}

}
