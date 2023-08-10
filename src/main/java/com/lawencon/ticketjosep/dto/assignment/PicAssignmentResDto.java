package com.lawencon.ticketjosep.dto.assignment;

public class PicAssignmentResDto {
	private Long picId;
	private String picName;
	private Long costumerId;
	private String costumerName;

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Long getCostumerId() {
		return costumerId;
	}

	public void setCostumerId(Long costumerId) {
		this.costumerId = costumerId;
	}

	public String getCostumerName() {
		return costumerName;
	}

	public void setCostumerName(String costumerName) {
		this.costumerName = costumerName;
	}

}
