package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.ticketcomment.TicketCommentInsertReqDto;
import com.lawencon.ticketjosep.dto.ticketcomment.TicketCommentResDto;

public interface TicketCommentService {
	InsertResDto addComment(TicketCommentInsertReqDto data);
	
	List<TicketCommentResDto> getCommentByTicketId(Long ticketId);
}
