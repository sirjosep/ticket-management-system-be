package com.lawencon.ticketjosep.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.model.Company;
import com.lawencon.ticketjosep.model.File;
import com.lawencon.ticketjosep.model.Profile;
import com.lawencon.ticketjosep.model.Role;
import com.lawencon.ticketjosep.model.User;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		final String sql = "SELECT * FROM t_user";
		
		final List<User> users = this.em.createNativeQuery(sql, User.class).getResultList();
		return users;
	}

	@Override
	public List<User> getUserByRoleCode(String roleCode) {
		final List<User> users = new ArrayList<>();
		final String sql = "SELECT "
				+ "tu.id AS user_id, email, password, profile_name, role_code, company_name "
				+ "FROM "
				+ "t_user tu "
				+ "INNER JOIN "
				+ "t_role tr ON tu.role_id = tr.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "INNER JOIN "
				+ "t_company tc ON tu.company_id = tc.id "
				+ "WHERE tr.role_code = :roleCode";
		
		final List<?> userObjs = this.em.createNativeQuery(sql)
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
				+ "tu.id, tu.password, tp.profile_name, tf.id as fileId, tr.role_code " 
				+ "FROM " 
				+ "t_user tu " 
				+ "INNER JOIN "
				+ "t_role tr ON tu.role_id = tr.id " 
				+ "INNER JOIN " 
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "LEFT JOIN "
				+ "t_file tf ON tp.file_id = tf.id "
				+ "WHERE "
				+ "tu.email = :email";
		try {
			final Object userObj = this.em.createNativeQuery(sql)
					.setParameter("email", email)
					.getSingleResult();

			final Object[] userArr = (Object[]) userObj;

			User user = null;
			if (userArr.length > 0) {
				user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));
				user.setPassword(userArr[1].toString());

				final Profile profile = new Profile();
				profile.setProfileName(userArr[2].toString());
				
				if(userArr[3] != null) {
					final File file = new File();
					file.setId(Long.valueOf(userArr[3].toString()));
					
					profile.setFile(file);
				}
				user.setProfile(profile);

				final Role role = new Role();
				role.setRoleCode(userArr[4].toString());
				user.setRole(role);
			}

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public User getById(Long id) {
		final User user = this.em.find(User.class, id);
		return user;
	}

	@Override
	public User getByIdRef(Long id) {
		final User user = this.em.getReference(User.class, id);
		return user;
	}
}
