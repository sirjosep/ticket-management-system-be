package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.FileTicketComment;

public interface FileTicketCommentDao {
	FileTicketComment insert(FileTicketComment fileTicketComment);
	
	List<FileTicketComment> getFileByTicketCommentId(Long ticketCommentId);
}
