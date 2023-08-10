package com.lawencon.ticketjosep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.FileTicketComment;

@Repository
public interface FileTicketCommentRepo extends JpaRepository<FileTicketComment, Long> {
	List<FileTicketComment> getFileByTicketCommentId(Long ticketCommentId);
}
