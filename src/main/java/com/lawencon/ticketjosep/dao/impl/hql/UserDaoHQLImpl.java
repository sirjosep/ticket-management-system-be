package com.lawencon.ticketjosep.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.model.Company;
import com.lawencon.ticketjosep.model.Profile;
import com.lawencon.ticketjosep.model.Role;
import com.lawencon.ticketjosep.model.User;

@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class UserDaoHQLImpl implements UserDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> getAll() {
		final String sql = "SELECT u FROM User AS u";
		
		final List<User> users = this.em.createQuery(sql, User.class).getResultList();
		return users;
	}

	@Override
	public List<User> getUserByRoleCode(String roleCode) {
		final List<User> users = new ArrayList<>();
		
		final String sql = "SELECT "
				+ "u.id, u.email, u.password, u.profile.profileName, u.role.roleCode, u.company.companyName "
				+ "FROM "
				+ "User u "
				+ "INNER JOIN "
				+ "u.role AS r "
				+ "WHERE "
				+ "u.role.roleCode = :roleCode";
		
		final List<?> userObjs = this.em.createQuery(sql)
								.setParameter("roleCode", roleCode)
								.getResultList();
		
		if(userObjs.size() > 0) {
			for(Object userObj : userObjs) {
				final Object[] userArr = (Object[])userObj;
				
				final User user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));
				user.setEmail(userArr[1].toString());
				user.setPassword(userArr[2].toString());
				
				final Profile profile = new Profile();
				profile.setProfileName(userArr[3].toString());
				user.setProfile(profile);
				
				final Role role = new Role();
				role.setRoleCode(userArr[4].toString());
				user.setRole(role);
				
				final Company company = new Company();
				company.setCompanyName(userArr[5].toString());
				user.setCompany(company);
				
				users.add(user);
			}
		}
		return users;
	}

	@Override
	public User insert(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User getByEmail(String email) {
		final String sql = "SELECT "
				+ "u.id, u.password, u.profile.profileName, u.role.roleCode "
				+ "FROM "
				+ "User u "
				+ "WHERE u.email = :email";
		try {
			final Object userObj = this.em.createQuery(sql)
					.setParameter("email", email)
					.getSingleResult();
			
			final Object[] userArr = (Object[])userObj;
			
			User user = null;
			if(userArr.length > 0) {
				user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));
				user.setPassword(userArr[1].toString());
				
				final Profile profile = new Profile();
				profile.setProfileName(userArr[2].toString());
				user.setProfile(profile);
				
				final Role role = new Role();
				role.setRoleCode(userArr[3].toString());
				user.setRole(role);
			}
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public User getById(Long userId) {
		final User user = this.em.find(User.class, userId);
		return user;
	}

	@Override
	public User getByIdRef(Long id) {
		final User user = this.em.getReference(User.class, id);
		return user;
	}
}
