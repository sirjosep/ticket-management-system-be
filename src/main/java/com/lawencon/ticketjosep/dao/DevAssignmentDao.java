package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.DeveloperAssignment;

public interface DevAssignmentDao {
	DeveloperAssignment insert(DeveloperAssignment developerAssignment);
	
	DeveloperAssignment getByTicketId(Long ticketId);
	
	List<DeveloperAssignment> getAll();
}
