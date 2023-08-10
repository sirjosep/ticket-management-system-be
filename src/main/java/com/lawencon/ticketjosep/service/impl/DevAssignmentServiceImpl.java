package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.DevAssignmentDao;
import com.lawencon.ticketjosep.dao.TicketDao;
import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.assignment.DevAssignmentReqDto;
import com.lawencon.ticketjosep.dto.assignment.DevAssignmentResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketUpdateStatusReqDto;
import com.lawencon.ticketjosep.model.DeveloperAssignment;
import com.lawencon.ticketjosep.model.Ticket;
import com.lawencon.ticketjosep.model.User;
import com.lawencon.ticketjosep.service.DevAssignmentService;
import com.lawencon.ticketjosep.service.EmailService;
import com.lawencon.ticketjosep.service.PrincipalService;
import com.lawencon.ticketjosep.service.TicketService;

@Service
public class DevAssignmentServiceImpl implements DevAssignmentService {

	private final UserDao userDao;
	private final TicketDao ticketDao;
	private final TicketService ticketService;
	private final EmailService emailService;
	private final DevAssignmentDao devAssignmentDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public DevAssignmentServiceImpl(DevAssignmentDao devAssignmentDao, UserDao userDao, 
			TicketService ticketService, TicketDao ticketDao, 
			EmailService emailService, PrincipalService principalService) {
		this.devAssignmentDao = devAssignmentDao;
		this.userDao = userDao;
		this.ticketDao = ticketDao;
		this.ticketService = ticketService;
		this.emailService = emailService;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(DevAssignmentReqDto data) {
		final InsertResDto insertResDto = new InsertResDto();
		
		final User dev = userDao.getById(data.getDevId());
		final User pic = userDao.getById(principalService.getId());
		final Ticket ticket = ticketDao.getById(data.getTicketId());

		if (devAssignmentDao.getByTicketId(data.getTicketId()) == null) {
			final DeveloperAssignment developerAssignment = new DeveloperAssignment();
			developerAssignment.setDeveloper(dev);
			developerAssignment.setTicket(ticket);
			developerAssignment.setCreatedBy(principalService.getId());

			final DeveloperAssignment insertedAssign = devAssignmentDao.insert(developerAssignment);
			
			final TicketUpdateStatusReqDto statusReqDto = new TicketUpdateStatusReqDto();
			statusReqDto.setTicketId(data.getTicketId());
			
			ticketService.changeTicketStatus(statusReqDto);
			if (insertedAssign != null) {
				insertResDto.setId(developerAssignment.getId());
				insertResDto.setMsg("Developer assigned successfully");
				
				final String body = "Hello, " + dev.getProfile().getProfileName() 
						+ " We informed you that you have assigned to take ticket with code " 
						+ ticket.getTicketCode() + "by " + pic.getProfile().getProfileName() 
						+ ". Please take handle this problem as soon as possible. Thank you.";
						
				emailService.sendEmail("New Assigned Ticket", body, dev.getEmail());
				
				final String bodyToCust = "Hello, " + ticket.getUser().getProfile().getProfileName() 
						+ ". We like to inform you that we found you a developer for ticket with code #" 
						+ ticket.getTicketCode() + ", please wait until our developer check the take the problem and change the status to on progres. Thank you.";
				
				emailService.sendEmail("Ticket Status Change", bodyToCust, ticket.getUser().getEmail());
			}
		} else {
			insertResDto.setMsg("Error! ticket with code #" + ticket.getTicketCode() + " is already have a developer!");
		}
		
		return insertResDto;
	}

	@Override
	public List<DevAssignmentResDto> getAll() {
		final List<DevAssignmentResDto> responses = new ArrayList<>();
		
		devAssignmentDao.getAll().forEach(da -> {
			final DevAssignmentResDto response = new DevAssignmentResDto();
			response.setDevId(da.getDeveloper().getId());
			response.setDevName(da.getDeveloper().getProfile().getProfileName());
			response.setTicketId(da.getTicket().getId());
			response.setTicketCode(da.getTicket().getTicketCode());
			
			responses.add(response);
		});
		return responses;
	}

}
