package com.lawencon.ticketjosep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.TicketPriority;

@Repository
public interface TicketPriorityRepo extends JpaRepository<TicketPriority, Long>{
	
}
