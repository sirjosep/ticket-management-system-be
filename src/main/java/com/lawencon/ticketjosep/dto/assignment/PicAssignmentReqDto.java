package com.lawencon.ticketjosep.dto.assignment;

import java.util.List;

public class PicAssignmentReqDto {
	private Long picId;
	private List<Long> custId;

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public List<Long> getCustId() {
		return custId;
	}

	public void setCustId(List<Long> custId) {
		this.custId = custId;
	}

}
