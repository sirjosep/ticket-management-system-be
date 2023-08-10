package com.lawencon.ticketjosep.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{
	List<Ticket> getTicketByUserId(Long userId);
	
	@Query(value = "SELECT "
			+ "* "
			+ "FROM "
			+ "t_ticket t "
			+ "INNER JOIN "
			+ "t_pic_assignment tpa ON t.user_id = tpa.costumer_id "
			+ "INNER JOIN "
			+ "t_user tu ON t.user_id = tu.id "
			+ "INNER JOIN "
			+ "t_profile tp ON tu.profile_id = tp.id "
			+ "INNER JOIN "
			+ "t_ticket_status tts ON t.ticket_status_id = tts.id "
			+ "WHERE "
			+ "tpa.pic_id = :picId AND tts.ticket_status_code = :statusCode", nativeQuery = true)
	List<Ticket> getTicketByPicAndStatusCode(@Param("picId") Long picId, @Param("statusCode") String statusCode);
	
	@Query(value = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_ticket t "
				+ "INNER JOIN "
				+ "t_developer_assignment tda ON tda.ticket_id = t.id "
				+ "INNER JOIN "
				+ "t_ticket_status tts ON t.ticket_status_id = tts.id "
				+ "WHERE "
				+ "tda.developer_id = :devId "
				+ "AND "
				+ "t.ticket_status_id "
				+ "IN "
				+ " ( "
				+ "		SELECT "
				+ "		tts2.id "
				+ "		FROM "
				+ "		t_ticket_status tts2 "
				+ "		WHERE "
				+ "		tts2.ticket_status_code LIKE 'PDA' "
				+ "		OR "
				+ "		tts2.ticket_status_code LIKE 'ONP' "
				+ "		OR "
				+ "		tts2.ticket_status_code LIKE 'PDC' "
				+ " ) ", nativeQuery = true)
	List<Ticket> getAssignedTicketByDev(@Param("devId") Long devId);
	
	@Query(value = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_ticket t "
				+ "INNER JOIN "
				+ "t_ticket_priority ttp ON t.ticket_priority_id = ttp.id "
				+ "WHERE "
				+ "t.ticket_priority_id = ?1 "
				+ "AND t.user_id = ?2 "
				+ "AND "
				+ "t.created_at BETWEEN "
				+ "?3 AND ?4", nativeQuery = true)
	List<Ticket> getByTicketPriorityIdAndUserId(Long priorityId, Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
