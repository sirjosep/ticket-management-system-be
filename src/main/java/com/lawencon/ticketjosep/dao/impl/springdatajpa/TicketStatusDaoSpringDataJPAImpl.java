package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketStatusDao;
import com.lawencon.ticketjosep.model.TicketStatus;
import com.lawencon.ticketjosep.repo.TicketStatusRepo;

@Repository
@Profile("springdatajpa-query")
public class TicketStatusDaoSpringDataJPAImpl implements TicketStatusDao{

	private TicketStatusRepo ticketStatusRepo;
	
	TicketStatusDaoSpringDataJPAImpl(TicketStatusRepo ticketStatusRepo) {
		this.ticketStatusRepo = ticketStatusRepo;
	}


	@Override
	public TicketStatus getStatusByStatusCode(String statusCode) {
		final Object statusObj = ticketStatusRepo.getTicketStatusByTicketStatusCode(statusCode);
		
		final Object[] statusArr = (Object[])statusObj;

		TicketStatus ticketStatus = null;
		if(statusArr.length > 0) {
			ticketStatus = new TicketStatus();
			ticketStatus.setId(Long.valueOf(statusArr[0].toString()));
			ticketStatus.setTicketStatusCode(statusArr[1].toString());
			ticketStatus.setTicketStatusName(statusArr[2].toString());
		}
		
		return ticketStatus;
	}

	
	public TicketStatus getByIdRef(Long id) {
		final TicketStatus ticketStatus = ticketStatusRepo.findById(id).get();
		return ticketStatus;
	}

}
