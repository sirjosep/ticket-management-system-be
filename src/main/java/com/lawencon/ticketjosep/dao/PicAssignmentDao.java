package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.PicAssignment;

public interface PicAssignmentDao {
	List<PicAssignment> getAll();
	PicAssignment getPicAssignByCustId(Long custId);
	
	PicAssignment insert(PicAssignment picAssignment);
}
