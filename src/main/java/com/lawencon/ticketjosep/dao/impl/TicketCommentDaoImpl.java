package com.lawencon.ticketjosep.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketCommentDao;
import com.lawencon.ticketjosep.model.TicketComment;

@Repository
@Profile("native-query")
public class TicketCommentDaoImpl implements TicketCommentDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public TicketComment insert(TicketComment ticketComment) {
		this.em.persist(ticketComment);
		return ticketComment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketComment> getCommentByTicketId(Long ticketId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_ticket_comment ttc "
				+ "INNER JOIN "
				+ "t_user tu ON ttc.user_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "INNER JOIN "
				+ "t_role tr ON tu.role_id = tr.id "
				+ "WHERE "
				+ "ttc.ticket_id = :ticketId "
				+ "ORDER BY "
				+ "ttc.created_at DESC";
		
		final List<TicketComment> ticketComments = this.em.createNativeQuery(sql, TicketComment.class)
				.setParameter("ticketId", ticketId).getResultList();
		
		return ticketComments;
	}

}
