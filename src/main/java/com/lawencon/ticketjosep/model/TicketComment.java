package com.lawencon.ticketjosep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_ticket_comment")
public class TicketComment extends Base{
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	@Column(name = "forum_comment", nullable = true)
	private String forumComment;

	@ManyToOne
	@JoinColumn(name = "ticket_id", nullable = true)
	private Ticket ticket;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getForumComment() {
		return forumComment;
	}

	public void setForumComment(String forumComment) {
		this.forumComment = forumComment;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
