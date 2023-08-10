package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.RoleDao;
import com.lawencon.ticketjosep.model.Role;
import com.lawencon.ticketjosep.repo.RoleRepo;

@Repository
@Profile("springdatajpa-query")
public class RoleDaoSpringDataJPAImpl implements RoleDao{

	private RoleRepo roleRepo;

	RoleDaoSpringDataJPAImpl(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	public List<Role> getAll() {
		final List<Role> roles = roleRepo.findAll();
		
		return roles;
	}

	@Override
	public Role getById(Long id) {
		final Role role = roleRepo.findById(id).get();
		return role;
	}

	@Override
	public Role getByIdRef(Long id) {
		final Role role = roleRepo.getReferenceById(id);
		return role;
	}
}
