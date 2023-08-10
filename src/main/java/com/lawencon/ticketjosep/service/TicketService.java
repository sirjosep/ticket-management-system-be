package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketDetailResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketInsertReqDto;
import com.lawencon.ticketjosep.dto.ticket.TicketResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketUpdateStatusReqDto;

public interface TicketService {
	InsertResDto createTicket(TicketInsertReqDto data);
	UpdateResDto changeTicketStatus(TicketUpdateStatusReqDto data);
	TicketDetailResDto getTicketById(Long ticketId);
	
	List<TicketResDto> getAll(Long priorityId, String statusCode);
}
