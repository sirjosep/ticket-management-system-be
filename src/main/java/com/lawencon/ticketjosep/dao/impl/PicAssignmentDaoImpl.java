package com.lawencon.ticketjosep.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.PicAssignmentDao;
import com.lawencon.ticketjosep.model.PicAssignment;
import com.lawencon.ticketjosep.model.Profile;
import com.lawencon.ticketjosep.model.User;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class PicAssignmentDaoImpl implements PicAssignmentDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public PicAssignment insert(PicAssignment picAssignment) {
		this.em.persist(picAssignment);		
		return picAssignment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PicAssignment> getAll() {
		final String sql = "SELECT "
				+ "*, "
				+ "pic.id AS picId, "
				+ "picprof.profile_name AS picName, "
				+ "cust.id AS custId, "
				+ "custprof.profile_name AS custName "
				+ "FROM "
				+ "t_pic_assignment tpa "
				+ "INNER JOIN "
				+ "t_user pic ON tpa.pic_id = pic.id "
				+ "INNER JOIN "
				+ "t_profile picprof ON pic.profile_id = picprof.id "
				+ "INNER JOIN "
				+ "t_user cust ON tpa.costumer_id = cust.id "
				+ "INNER JOIN "
				+ "t_profile custprof ON cust.profile_id = custprof.id";
		
		final List<PicAssignment> picAssignments = this.em.createNativeQuery(sql, PicAssignment.class).getResultList();
		
		return picAssignments;
	}

	@Override
	public PicAssignment getPicAssignByCustId(Long custId) {
		final String sql = "SELECT "
				+ "tpa.id, pic.id AS picId, "
				+ "picprof.profile_name AS picName, "
				+ "cust.id AS custId, "
				+ "custprof.profile_name AS custName "
				+ "FROM "
				+ "t_pic_assignment tpa "
				+ "INNER JOIN "
				+ "t_user pic ON tpa.pic_id = pic.id "
				+ "INNER JOIN "
				+ "t_profile picprof ON pic.profile_id = picprof.id "
				+ "INNER JOIN "
				+ "t_user cust ON tpa.costumer_id = cust.id "
				+ "INNER JOIN "
				+ "t_profile custprof ON cust.profile_id = custprof.id "
				+ "WHERE "
				+ "tpa.costumer_id =  :custId";
		try {
			final Object picAssignObj = this.em.createNativeQuery(sql)
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
