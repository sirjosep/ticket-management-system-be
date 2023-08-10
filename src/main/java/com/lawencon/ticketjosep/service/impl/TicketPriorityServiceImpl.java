package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.TicketPriorityDao;
import com.lawencon.ticketjosep.dto.ticketpriority.TicketPriorityResDto;
import com.lawencon.ticketjosep.service.TicketPriorityService;

@Service
public class TicketPriorityServiceImpl implements TicketPriorityService{

	private final TicketPriorityDao ticketPriorityDao;
	
	public TicketPriorityServiceImpl(TicketPriorityDao ticketPriorityDao) {
		this.ticketPriorityDao = ticketPriorityDao;
	}
	
	@Override
	public List<TicketPriorityResDto> getAll() {
		final List<TicketPriorityResDto> responses = new ArrayList<>();
		
		ticketPriorityDao.getAll().forEach(tp -> {
			final TicketPriorityResDto response = new TicketPriorityResDto();
			response.setId(tp.getId());
			response.setTicketPriorityCode(tp.getTicketPriorityCode());
			response.setTicketPriorityName(tp.getTicketPriorityName());
			response.setTicketPriorityLimit(tp.getTicketPriorityLimit());
			
			responses.add(response);
		});
		
		return responses;
	}

}
