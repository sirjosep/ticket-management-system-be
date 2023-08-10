package com.lawencon.ticketjosep.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.DevAssignmentDao;
import com.lawencon.ticketjosep.model.DeveloperAssignment;
import com.lawencon.ticketjosep.model.Profile;
import com.lawencon.ticketjosep.model.User;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class DevAssignmentDaoImpl implements DevAssignmentDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public DeveloperAssignment insert(DeveloperAssignment developerAssignment) {
		em.persist(developerAssignment);
		return developerAssignment;
	}

	@Override
	public DeveloperAssignment getByTicketId(Long ticketId) {
		final String sql = "SELECT "
				+ "tda.id, tda.developer_id, tu.email, tp.profile_name " 
				+ "FROM "
				+ "t_developer_assignment tda "
				+ "INNER JOIN "
				+ "t_ticket t ON tda.ticket_id = t.id "
				+ "INNER JOIN "
				+ "t_user tu ON t.user_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "WHERE "
				+ "ticket_id = :ticketId";
		
		try {
			final Object devAssignObj = this.em.createNativeQuery(sql)
					.setParameter("ticketId", ticketId)
					.getSingleResult();
			
			final Object[] devAssignObjArr = (Object[]) devAssignObj;
			
			DeveloperAssignment developerAssignment = null;
			if (devAssignObjArr.length > 0) {
				developerAssignment = new DeveloperAssignment();
				developerAssignment.setId(Long.valueOf(devAssignObjArr[0].toString()));
				
				final User dev = new User();
				dev.setId(Long.valueOf(devAssignObjArr[1].toString()));
				dev.setEmail(devAssignObjArr[2].toString());
				
				final Profile profile = new Profile();
				profile.setProfileName(devAssignObjArr[3].toString());
				dev.setProfile(profile);
				
				developerAssignment.setDeveloper(dev);
				
			}
			return developerAssignment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeveloperAssignment> getAll() {
		final String sql = "SELECT "
				+ "* " 
				+ "FROM "
				+ "t_developer_assignment tda "
				+ "INNER JOIN "
				+ "t_ticket t ON tda.ticket_id = t.id "
				+ "INNER JOIN "
				+ "t_user tu ON t.user_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id ";
		
		final List<DeveloperAssignment> developerAssignments = this.em.createNativeQuery(sql, DeveloperAssignment.class)
				.getResultList();
		
		return developerAssignments;
	}

}
