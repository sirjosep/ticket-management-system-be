package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketDao;
import com.lawencon.ticketjosep.model.Ticket;
import com.lawencon.ticketjosep.repo.TicketRepo;

@Repository
@Profile("springdatajpa-query")
public class TicketDaoSpringDataJPAImpl implements TicketDao {

	private TicketRepo ticketRepo;
	final LocalDateTime dateStart = LocalDateTime.now().withDayOfMonth(1);
	final LocalDateTime dateEnd = LocalDateTime.now().withDayOfMonth(1).plusMonths(1).minusDays(1);

	TicketDaoSpringDataJPAImpl(TicketRepo ticketRepo) {
		this.ticketRepo = ticketRepo;
	}

	@Override
	public Ticket insert(Ticket ticket) {
		ticketRepo.save(ticket);
		
		return ticket;
	}

	@Override
	public List<Ticket> getTicketByUserId(Long userId) {
		final List<Ticket> tickets = ticketRepo.getTicketByUserId(userId);

		return tickets;
	}

	@Override
	public Ticket getById(Long ticketId) {
		final Ticket ticket = ticketRepo.findById(ticketId).get();
		
		return ticket;
	}

	@Override
	public Ticket getByIdRef(Long id) {
		final Ticket ticket = ticketRepo.getReferenceById(id);
		
		return ticket;
	}

	@Override
	public List<Ticket> getTicketByPicAndStatusCode(Long picId, String statusCode) {
		final List<Ticket> tickets = ticketRepo.getTicketByPicAndStatusCode(picId, statusCode);

		return tickets;
	}

	@Override
	public List<Ticket> getAssignedTicketByDev(Long devId) {
		final List<Ticket> tickets = ticketRepo.getAssignedTicketByDev(devId);
		
		return tickets;
	}

	@Override
	public List<Ticket> getByTicketPriorityAndUserId(Long priorityId, Long userId) {
		final List<Ticket> tickets = ticketRepo.getByTicketPriorityIdAndUserId(priorityId, userId, dateStart, dateEnd);

		return tickets;
	}

	@Override
	public Ticket update(Ticket ticket) {
		final Ticket newTicket = ticketRepo.saveAndFlush(ticket);
		return newTicket;
	}

}
