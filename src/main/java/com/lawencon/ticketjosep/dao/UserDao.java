package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.User;

public interface UserDao{
	List<User> getAll();
	
	List<User> getUserByRoleCode(String roleCode);
	
	User insert(User user);
	
	User getByEmail(String email);
	
	User getById(Long id);
	
	User getByIdRef(Long id);
}
