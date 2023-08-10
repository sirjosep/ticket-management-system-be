package com.lawencon.ticketjosep.service;

import com.lawencon.ticketjosep.model.TicketStatus;

public interface TicketStatusService {
	TicketStatus getTicketByRoleAndStatus(String roleCode, String statusCode);
}
