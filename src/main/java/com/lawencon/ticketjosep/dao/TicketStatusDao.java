package com.lawencon.ticketjosep.dao;

import com.lawencon.ticketjosep.model.TicketStatus;

public interface TicketStatusDao {
	TicketStatus getStatusByStatusCode(String statusCode);
	
	TicketStatus getByIdRef(Long id);
}
