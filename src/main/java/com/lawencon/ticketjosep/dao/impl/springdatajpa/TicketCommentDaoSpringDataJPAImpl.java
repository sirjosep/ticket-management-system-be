package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketCommentDao;
import com.lawencon.ticketjosep.model.TicketComment;
import com.lawencon.ticketjosep.repo.TicketCommentRepo;

@Repository
@Profile("springdatajpa-query")
public class TicketCommentDaoSpringDataJPAImpl implements TicketCommentDao{

	private final TicketCommentRepo ticketCommentRepo;
	
	TicketCommentDaoSpringDataJPAImpl(TicketCommentRepo ticketCommentRepo) {
		this.ticketCommentRepo = ticketCommentRepo;
	}

	@Override
	public TicketComment insert(TicketComment ticketComment) {
		ticketCommentRepo.save(ticketComment);
		return ticketComment;
	}

	@Override
	public List<TicketComment> getCommentByTicketId(Long ticketId) {
		final List<TicketComment> ticketComments = ticketCommentRepo.getTicketCommentByTicketId(ticketId);
		
		return ticketComments;
	}

}
