package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.assignment.PicAssignmentReqDto;
import com.lawencon.ticketjosep.dto.assignment.PicAssignmentResDto;

public interface PicAssignmentService {
	InsertResDto insert(PicAssignmentReqDto data);
	
	List<PicAssignmentResDto> getAll();
	PicAssignmentResDto getPicAssignByCustId(Long custId);
}
