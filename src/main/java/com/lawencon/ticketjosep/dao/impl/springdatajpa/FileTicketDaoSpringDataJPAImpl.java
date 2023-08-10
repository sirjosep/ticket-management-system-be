package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.FileTicketDao;
import com.lawencon.ticketjosep.model.FileTicket;
import com.lawencon.ticketjosep.repo.FileTicketRepo;

@Repository
@Profile("springdatajpa-query")
public class FileTicketDaoSpringDataJPAImpl implements FileTicketDao {

	private FileTicketRepo fileTicketRepo;
	
	FileTicketDaoSpringDataJPAImpl(FileTicketRepo fileTicketRepo) {
		this.fileTicketRepo = fileTicketRepo;
	}

	@Override
	public FileTicket insert(FileTicket fileTicket) {
		fileTicketRepo.save(fileTicket);
		return fileTicket;
	}

	@Override
	public List<FileTicket> getFileByTicketId(Long ticketId) {
		final List<FileTicket> fileTickets = fileTicketRepo.getFileTicketByTicketId(ticketId);
		return fileTickets;
	}

}
