package com.lawencon.ticketjosep.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_file_ticket_comment")
public class FileTicketComment extends Base{
	@ManyToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "ticket_comment_id", nullable = false)
	private TicketComment ticketComment;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public TicketComment getTicketComment() {
		return ticketComment;
	}

	public void setTicketComment(TicketComment ticketComment) {
		this.ticketComment = ticketComment;
	}

}
