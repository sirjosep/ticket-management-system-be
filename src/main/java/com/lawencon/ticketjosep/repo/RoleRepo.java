package com.lawencon.ticketjosep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	
}
