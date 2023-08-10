package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.ticket.FileTicketResDto;

public interface FileTicketService {
	List<FileTicketResDto> getFileByTicketId(Long ticketId);
}
