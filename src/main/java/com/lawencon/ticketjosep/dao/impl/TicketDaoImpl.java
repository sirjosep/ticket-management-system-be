package com.lawencon.ticketjosep.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketDao;
import com.lawencon.ticketjosep.model.Ticket;

@Repository
@Profile("native-query")
public class TicketDaoImpl implements TicketDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Ticket insert(Ticket ticket) {
		this.em.persist(ticket);
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getTicketByUserId(Long userId) {
		final String sql = "SELECT "
				+ " * "
				+ "FROM "
				+ "t_ticket tti "
				+ "INNER JOIN "
				+ "t_product tp ON tti.product_id = tp.id "
				+ "INNER JOIN "
				+ "t_ticket_status tts ON tti.ticket_status_id = tts.id "
				+ "WHERE "
				+ "tti.user_id = :userId";
		
		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class)
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getTicketByPicAndStatusCode(Long picId , String statusCode) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_ticket t "
				+ "INNER JOIN "
				+ "t_pic_assignment tpa ON t.user_id = tpa.costumer_id "
				+ "INNER JOIN "
				+ "t_user tu ON t.user_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "INNER JOIN "
				+ "t_ticket_status tts ON t.ticket_status_id = tts.id "
				+ "WHERE "
				+ "tpa.pic_id = :picId AND tts.ticket_status_code = :statusCode";
		
		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class)
				.setParameter("picId", picId)
				.setParameter("statusCode", statusCode)
				.getResultList();
		
		return tickets;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAssignedTicketByDev(Long devId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_ticket t "
				+ "INNER JOIN "
				+ "t_developer_assignment tda ON tda.ticket_id = t.id "
				+ "INNER JOIN "
				+ "t_ticket_status tts ON t.ticket_status_id = tts.id "
				+ "WHERE "
				+ "tda.developer_id = :devId "
				+ "AND "
				+ "t.ticket_status_id "
				+ "IN "
				+ " ( "
				+ "		SELECT "
				+ "		tts2.id "
				+ "		FROM "
				+ "		t_ticket_status tts2 "
				+ "		WHERE "
				+ "		tts2.ticket_status_code LIKE 'PDA' "
				+ "		OR "
				+ "		tts2.ticket_status_code LIKE 'ONP' "
				+ "		OR "
				+ "		tts2.ticket_status_code LIKE 'PDC' "
				+ " ) ";

		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class)
				.setParameter("devId", devId).getResultList();
		
		return tickets;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getByTicketPriorityAndUserId(Long priorityId, Long userId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_ticket t "
				+ "INNER JOIN "
				+ "t_ticket_priority ttp ON t.ticket_priority_id = ttp.id "
				+ "WHERE "
				+ "t.ticket_priority_id = :priorityId "
				+ "AND t.user_id = :userId "
				+ "AND "
				+ "t.created_at BETWEEN "
				+ ":dateStart AND :dateEnd";

		final LocalDateTime timeNow = LocalDateTime.now();
		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class)
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
