package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.assignment.DevAssignmentReqDto;
import com.lawencon.ticketjosep.dto.assignment.DevAssignmentResDto;

public interface DevAssignmentService {
	InsertResDto insert(DevAssignmentReqDto data);
	
	List<DevAssignmentResDto> getAll();
}
