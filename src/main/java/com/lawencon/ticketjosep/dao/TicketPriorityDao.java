package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.TicketPriority;

public interface TicketPriorityDao {
	TicketPriority getById(Long priorityId);
	List<TicketPriority> getAll();
}
