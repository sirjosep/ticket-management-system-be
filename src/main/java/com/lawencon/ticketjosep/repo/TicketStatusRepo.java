package com.lawencon.ticketjosep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.TicketStatus;

@Repository
public interface TicketStatusRepo extends JpaRepository<TicketStatus, Long>{
	@Query(value = "SELECT "
				+ "tts.id, tts.ticket_status_code, tts.ticket_status_name "
				+ "FROM "
				+ "t_ticket_status tts "
				+ "WHERE "
				+ "tts.ticket_status_code = :statusCode", nativeQuery = true)
	Object getTicketStatusByTicketStatusCode(String statusCode);
}
