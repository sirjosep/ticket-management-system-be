package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.Role;

public interface RoleDao {
	List<Role> getAll();
	Role getById(Long id);
	Role getByIdRef(Long id);
}
