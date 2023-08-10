package com.lawencon.ticketjosep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.FileTicket;

@Repository
public interface FileTicketRepo extends JpaRepository<FileTicket, Long>{
	List<FileTicket> getFileTicketByTicketId(Long ticketId);
}
