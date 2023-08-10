package com.lawencon.ticketjosep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_ticket")
public class Ticket extends Base {
	@Column(name = "ticket_code", length = 5, nullable = true)
	private String ticketCode;

	@Column(name = "ticket_title", nullable = true)
	private String ticketTitle;

	@Column(name = "ticket_body", nullable = true)
	private String ticketBody;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = true)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "ticket_status_id", nullable = true)
	private TicketStatus ticketStatus;

	@ManyToOne
	@JoinColumn(name = "ticket_priority_id", nullable = true)
	private TicketPriority ticketPriority;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public TicketPriority getTicketPriority() {
		return ticketPriority;
	}

	public void setTicketPriority(TicketPriority ticketPriority) {
		this.ticketPriority = ticketPriority;
	}

}
