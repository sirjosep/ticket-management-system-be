package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.SubscribedProductDetail;

public interface SubscribeProductDao {
	SubscribedProductDetail insert(SubscribedProductDetail subscribedProductDetail);
	
	List<SubscribedProductDetail> getAll();
	List<SubscribedProductDetail> getSubscribedProductByUser(Long userId);
	SubscribedProductDetail getSubscribedProductByUserAndProduct(Long userId, Long productId);
}
