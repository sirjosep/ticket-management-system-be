package com.lawencon.ticketjosep.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketCommentDao;
import com.lawencon.ticketjosep.model.TicketComment;

@Repository
@Profile("hql-query")
public class TicketCommentDaoHQLImpl implements TicketCommentDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public TicketComment insert(TicketComment ticketComment) {
		this.em.persist(ticketComment);
		return ticketComment;
	}

	@Override
	public List<TicketComment> getCommentByTicketId(Long ticketId) {
		final String sql = "SELECT "
				+ "tc "
				+ "FROM "
				+ "TicketComment tc "
				+ "WHERE "
				+ "tc.ticket.id = :ticketId "
				+ "ORDER BY "
				+ "tc.createdAt DESC";
		
		final List<TicketComment> ticketComments = this.em.createQuery(sql, TicketComment.class)
				.setParameter("ticketId", ticketId).getResultList();
		
		return ticketComments;
	}

}
