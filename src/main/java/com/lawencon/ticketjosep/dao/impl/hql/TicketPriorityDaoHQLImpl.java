package com.lawencon.ticketjosep.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketPriorityDao;
import com.lawencon.ticketjosep.model.TicketPriority;

@Repository
@Profile("hql-query")
public class TicketPriorityDaoHQLImpl implements TicketPriorityDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<TicketPriority> getAll() {
		final String sql = "SELECT tp FROM TicketPriority tp";
		
		final List<TicketPriority> priorityLists = this.em.createQuery(sql, TicketPriority.class).getResultList();
		
		return priorityLists;
	}

	@Override
	public TicketPriority getById(Long priorityId) {
		final TicketPriority ticketPriority = this.em.find(TicketPriority.class, priorityId);
		return ticketPriority;
	}

}
