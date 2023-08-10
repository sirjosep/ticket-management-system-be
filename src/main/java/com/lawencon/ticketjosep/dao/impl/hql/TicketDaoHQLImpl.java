package com.lawencon.ticketjosep.dao.impl.hql;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketDao;
import com.lawencon.ticketjosep.model.Ticket;

@Repository
@Profile("hql-query")
public class TicketDaoHQLImpl implements TicketDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Ticket insert(Ticket ticket) {
		this.em.persist(ticket);
		return ticket;
	}

	@Override
	public List<Ticket> getTicketByUserId(Long userId) {
		final String sql = "SELECT "
				+ "t "
				+ "FROM "
				+ "Ticket t "
				+ "WHERE "
				+ "t.user.id = :userId";
		
		final List<Ticket> tickets = this.em.createQuery(sql, Ticket.class)
				.setParameter("userId", userId).getResultList();
		
		return tickets;
	}

	@Override
	public Ticket getById(Long ticketId) {
		final Ticket ticket = this.em.find(Ticket.class, ticketId);
		return ticket;
	}
	
	@Override
	public Ticket getByIdRef(Long id) {
		final Ticket ticket = this.em.getReference(Ticket.class, id);
		return ticket;
	}

	@Override
	public List<Ticket> getTicketByPicAndStatusCode(Long picId , String statusCode) {
		final String sql = "SELECT "
				+ "t "
				+ "FROM "
				+ "Ticket t "
				+ "INNER JOIN "
				+ "PicAssignment pa ON t.user.id = pa.costumer.id "
				+ "INNER JOIN "
				+ "TicketStatus ts ON t.ticketStatus.id = ts.id "
				+ "WHERE "
				+ "pa.pic.id = :picId AND ts.ticketStatusCode = :statusCode";
		
		final List<Ticket> tickets = this.em.createQuery(sql, Ticket.class)
				.setParameter("picId", picId)
				.setParameter("statusCode", statusCode)
				.getResultList();
		
		return tickets;
	}

	@Override
	public List<Ticket> getAssignedTicketByDev(Long devId) {
		final String sql = "SELECT "
				+ "t "
				+ "FROM "
				+ "Ticket t "
				+ "INNER JOIN "
				+ "DeveloperAssignment da ON da.ticket.id = t.id "
				+ "INNER JOIN "
				+ "TicketStatus ts ON t.ticketStatus.id = ts.id "
				+ "WHERE "
				+ "da.developer.id = :devId "
				+ "AND "
				+ "t.ticketStatus.id "
				+ "IN "
				+ " ( "
				+ "		SELECT "
				+ "		ts2.id "
				+ "		FROM "
				+ "		TicketStatus ts2 "
				+ "		WHERE "
				+ "		ts2.ticketStatusCode LIKE 'PDA' "
				+ "		OR "
				+ "		ts2.ticketStatusCode LIKE 'ONP' "
				+ "		OR "
				+ "		ts2.ticketStatusCode LIKE 'PDC' "
				+ " ) ";

		final List<Ticket> tickets = this.em.createQuery(sql, Ticket.class)
				.setParameter("devId", devId).getResultList();
		
		return tickets;
	}


	@Override
	public List<Ticket> getByTicketPriorityAndUserId(Long priorityId, Long userId) {
		final String sql = "SELECT "
				+ "t "
				+ "FROM "
				+ "Ticket t "
				+ "WHERE "
				+ "t.ticketPriority.id = :priorityId "
				+ "AND "
				+ "t.user.id = :userId "
				+ "AND "
				+ "t.createdAt BETWEEN "
				+ ":dateStart AND :dateEnd";
		
		final LocalDateTime timeNow = LocalDateTime.now();
		final List<Ticket> tickets = this.em.createQuery(sql, Ticket.class)
				.setParameter("priorityId", priorityId)
				.setParameter("userId", userId)
				.setParameter("dateStart", timeNow.withDayOfMonth(1))
				.setParameter("dateEnd", timeNow.withDayOfMonth(1).plusMonths(1).minusDays(1))
				.getResultList();
		return tickets;
	}

	@Override
	public Ticket update(Ticket ticket) {
		final Ticket newTicket = this.em.merge(ticket);
		this.em.flush();
		return newTicket;
	}

}
