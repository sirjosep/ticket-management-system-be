package com.lawencon.ticketjosep.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.RoleDao;
import com.lawencon.ticketjosep.model.Role;

@Repository
@Profile("native-query")
public class RoleDaoImpl implements RoleDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_role tr ";
		
		final List<Role> roles = this.em.createNativeQuery(sql, Role.class).getResultList();
		
		return roles;
	}

	@Override
	public Role getById(Long id) {
		final Role role = this.em.find(Role.class, id);
		return role;
	}

	@Override
	public Role getByIdRef(Long id) {
		final Role role = this.em.getReference(Role.class, id);
		return role;
	}
}
