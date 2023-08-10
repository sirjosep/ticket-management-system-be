package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketPriorityDao;
import com.lawencon.ticketjosep.model.TicketPriority;
import com.lawencon.ticketjosep.repo.TicketPriorityRepo;

@Repository
@Profile("springdatajpa-query")
public class TicketPriorityDaoSpringDataJPAImpl implements TicketPriorityDao{

	private TicketPriorityRepo ticketPriorityRepo;
	
	TicketPriorityDaoSpringDataJPAImpl(TicketPriorityRepo ticketPriorityRepo) {
		this.ticketPriorityRepo = ticketPriorityRepo;
	}

	@Override
	public List<TicketPriority> getAll() {
		final List<TicketPriority> priorityLists = ticketPriorityRepo.findAll();
		
		return priorityLists;
	}

	@Override
	public TicketPriority getById(Long priorityId) {
		final TicketPriority ticketPriority = ticketPriorityRepo.findById(priorityId).get();
		return ticketPriority;
	}

}
