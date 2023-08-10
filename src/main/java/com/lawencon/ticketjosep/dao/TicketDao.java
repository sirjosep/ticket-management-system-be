package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.Ticket;

public interface TicketDao {
	Ticket insert(Ticket ticket);
	Ticket getById(Long ticketId);
	Ticket getByIdRef(Long id);
	Ticket update(Ticket ticket);
	
	List<Ticket> getByTicketPriorityAndUserId(Long priorityId, Long userId);
	List<Ticket> getTicketByUserId(Long userId);
	List<Ticket> getTicketByPicAndStatusCode(Long picId, String statusCode);
	List<Ticket> getAssignedTicketByDev(Long devId);
}
