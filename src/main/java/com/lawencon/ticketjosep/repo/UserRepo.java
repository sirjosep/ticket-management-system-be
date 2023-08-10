package com.lawencon.ticketjosep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	List<User> getUserByRoleRoleCode(String roleCode);
	
	User getByEmail(String email);
}
