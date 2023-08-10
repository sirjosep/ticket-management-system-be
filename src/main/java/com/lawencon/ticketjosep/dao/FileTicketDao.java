package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.FileTicket;

public interface FileTicketDao {
	FileTicket insert(FileTicket fileTicket);
	
	List<FileTicket> getFileByTicketId(Long ticketId);
}
