package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.FileTicketCommentDao;
import com.lawencon.ticketjosep.dto.ticketcomment.FileTicketCommentResDto;
import com.lawencon.ticketjosep.service.FileTicketCommentService;

@Service
public class FileTicketCommentServiceImpl implements FileTicketCommentService{
	
	private final FileTicketCommentDao fileTicketCommentDao;
	
	public FileTicketCommentServiceImpl(FileTicketCommentDao fileTicketCommentDao) {
		this.fileTicketCommentDao = fileTicketCommentDao;
	}

	@Override
	public List<FileTicketCommentResDto> getFileByTicketCommentId(Long ticketCommentId) {
		final List<FileTicketCommentResDto> responses = new ArrayList<>();
		fileTicketCommentDao.getFileByTicketCommentId(ticketCommentId).forEach(ftc -> {
			final FileTicketCommentResDto response = new FileTicketCommentResDto();
			response.setId(ftc.getId());
			response.setFileName(ftc.getFile().getFiles());
			response.setFileFormat(ftc.getFile().getFileFormat());
			
			responses.add(response);
		});
		
		return responses;
	}

}
