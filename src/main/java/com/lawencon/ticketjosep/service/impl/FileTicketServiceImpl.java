package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.FileTicketDao;
import com.lawencon.ticketjosep.dto.ticket.FileTicketResDto;
import com.lawencon.ticketjosep.service.FileTicketService;

@Service
public class FileTicketServiceImpl implements FileTicketService{
	
	private final FileTicketDao fileTicketDao;
	
	public FileTicketServiceImpl(FileTicketDao fileTicketDao) {
		this.fileTicketDao = fileTicketDao;
	}

	@Override
	public List<FileTicketResDto> getFileByTicketId(Long ticketId) {
		final List<FileTicketResDto> responses = new ArrayList<>();
		fileTicketDao.getFileByTicketId(ticketId).forEach(ft ->{
			final FileTicketResDto response = new FileTicketResDto();
			response.setId(ft.getId());
			response.setFileName(ft.getFile().getFiles());
			response.setFileFormat(ft.getFile().getFileFormat());
			
			responses.add(response);
		});
		
		return responses;
	}

}
