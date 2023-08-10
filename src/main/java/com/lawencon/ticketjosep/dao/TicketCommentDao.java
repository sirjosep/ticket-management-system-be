package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.TicketComment;

public interface TicketCommentDao {
	TicketComment insert(TicketComment ticketComment);
	
	List<TicketComment> getCommentByTicketId(Long ticketId);
}
