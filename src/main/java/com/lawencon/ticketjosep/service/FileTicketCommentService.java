package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.ticketcomment.FileTicketCommentResDto;

public interface FileTicketCommentService {
	 List<FileTicketCommentResDto> getFileByTicketCommentId(Long ticketCommentId);
}
