package com.lawencon.ticketjosep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.TicketComment;

@Repository
public interface TicketCommentRepo extends JpaRepository<TicketComment, Long>{
	List<TicketComment> getTicketCommentByTicketId(Long ticketId);
}
