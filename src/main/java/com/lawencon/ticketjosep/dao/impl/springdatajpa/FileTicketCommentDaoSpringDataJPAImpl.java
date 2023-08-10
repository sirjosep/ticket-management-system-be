package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.FileTicketCommentDao;
import com.lawencon.ticketjosep.model.FileTicketComment;
import com.lawencon.ticketjosep.repo.FileTicketCommentRepo;

@Repository
@Profile("springdatajpa-query")
public class FileTicketCommentDaoSpringDataJPAImpl implements FileTicketCommentDao{

	private FileTicketCommentRepo fileTicketCommentRepo;
	
	FileTicketCommentDaoSpringDataJPAImpl(FileTicketCommentRepo fileTicketCommentRepo) {
		this.fileTicketCommentRepo = fileTicketCommentRepo;
	}

	@Override
	public FileTicketComment insert(FileTicketComment fileTicketComment) {
		fileTicketCommentRepo.save(fileTicketComment);
		return fileTicketComment;
	}

	@Override
	public List<FileTicketComment> getFileByTicketCommentId(Long ticketCommentId) {
		final List<FileTicketComment> fileTicketComments = fileTicketCommentRepo.getFileByTicketCommentId(ticketCommentId);
		return fileTicketComments;
	}

}
