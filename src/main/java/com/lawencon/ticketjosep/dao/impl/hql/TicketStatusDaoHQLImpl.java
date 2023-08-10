package com.lawencon.ticketjosep.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketStatusDao;
import com.lawencon.ticketjosep.model.TicketStatus;

@Repository
@Profile("hql-query")
public class TicketStatusDaoHQLImpl implements TicketStatusDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public TicketStatus getStatusByStatusCode(String statusCode) {
		final String sql = "SELECT "
				+ "ts.id, ts.ticketStatusCode, ts.ticketStatusName "
				+ "FROM "
				+ "TicketStatus ts "
				+ "WHERE "
				+ "ts.ticketStatusCode = :statusCode";
		
		final Object statusObj = this.em.createQuery(sql)
				.setParameter("statusCode", statusCode)
				.getSingleResult();
		
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
	
	@Override
	public TicketStatus getByIdRef(Long id) {
		final TicketStatus ticketStatus = this.em.getReference(TicketStatus.class, id);
		return ticketStatus;
	}

}
