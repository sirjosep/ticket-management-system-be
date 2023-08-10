package com.lawencon.ticketjosep.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.TicketPriorityDao;
import com.lawencon.ticketjosep.model.TicketPriority;

@Repository
@Profile("native-query")
public class TicketPriorityDaoImpl implements TicketPriorityDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketPriority> getAll() {
		final String sql = "SELECT * FROM t_ticket_priority";
		
		final List<TicketPriority> priorityLists = this.em.createNativeQuery(sql, TicketPriority.class).getResultList();
		
		return priorityLists;
	}

	@Override
	public TicketPriority getById(Long priorityId) {
		final TicketPriority ticketPriority = this.em.find(TicketPriority.class, priorityId);
		return ticketPriority;
	}

}
