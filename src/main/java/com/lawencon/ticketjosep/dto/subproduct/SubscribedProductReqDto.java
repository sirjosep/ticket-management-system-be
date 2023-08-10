package com.lawencon.ticketjosep.dto.subproduct;

import java.util.List;

public class SubscribedProductReqDto {
	private Long userId;
	private List<Long> productId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getProductId() {
		return productId;
	}

	public void setProductId(List<Long> productId) {
		this.productId = productId;
	}
	
	

}
