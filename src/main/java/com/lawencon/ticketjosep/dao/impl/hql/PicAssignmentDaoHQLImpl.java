package com.lawencon.ticketjosep.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.PicAssignmentDao;
import com.lawencon.ticketjosep.model.PicAssignment;
import com.lawencon.ticketjosep.model.Profile;
import com.lawencon.ticketjosep.model.User;

@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class PicAssignmentDaoHQLImpl implements PicAssignmentDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PicAssignment insert(PicAssignment picAssignment) {
		this.em.persist(picAssignment);		
		return picAssignment;
	}

	@Override
	public List<PicAssignment> getAll() {
		final String sql = "SELECT pa FROM PicAssignment pa";
		
		final List<PicAssignment> picAssignments = this.em.createQuery(sql, PicAssignment.class).getResultList();
		
		return picAssignments;
	}

	@Override
	public PicAssignment getPicAssignByCustId(Long custId) {
		final String sql = "SELECT "
				+ "pa.id, pa.pic.id, "
				+ "pa.pic.profile.profileName, "
				+ "pa.costumer.id, "
				+ "pa.costumer.profile.profileName "
				+ "FROM "
				+ "PicAssignment pa "
				+ "WHERE "
				+ "pa.costumer.id =  :custId";
		try {
			final Object picAssignObj = this.em.createQuery(sql)
					.setParameter("custId", custId)
					.getSingleResult();
			
			final Object[] picAssignArr = (Object[])picAssignObj;
			
			PicAssignment picAssignment = null;
			if(picAssignArr.length > 0) {
				picAssignment = new PicAssignment();
				picAssignment.setId(Long.valueOf(picAssignArr[0].toString()));
				
				final User pic = new User();
				pic.setId(Long.valueOf(picAssignArr[1].toString()));
				
				final Profile picProfile = new Profile();
				picProfile.setProfileName(picAssignArr[2].toString());
				
				pic.setProfile(picProfile);
				picAssignment.setPic(pic);
				
				final User customer = new User();
				customer.setId(Long.valueOf(picAssignArr[3].toString()));
				
				final Profile custProfile = new Profile();
				custProfile.setProfileName(picAssignArr[4].toString());
				
				customer.setProfile(custProfile);
				picAssignment.setCostumer(customer);
			}
			return picAssignment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
