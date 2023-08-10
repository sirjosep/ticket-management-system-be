package com.lawencon.ticketjosep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.DeveloperAssignment;

@Repository
public interface DevAssignmentRepo extends JpaRepository<DeveloperAssignment, Long>{
	DeveloperAssignment getDeveloperAssignmentByTicketId(Long ticketId);
}
