package com.lawencon.ticketjosep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.SubscribedProductDetail;

@Repository
public interface SubscribeProductRepo extends JpaRepository<SubscribedProductDetail, Long>{
	List<SubscribedProductDetail> getSubscribedProductDetailByUserId(Long userId);
	
	@Query(value = "SELECT "
				+ "tspd.id, tp.product_name "
				+ "FROM "
				+ "t_product tp "
				+ "INNER JOIN "
				+ "t_subscribed_product_detail tspd ON tspd.product_id = tp.id "
				+ "WHERE "
				+ "tspd.user_id = ?1 AND tspd.product_id = ?2", nativeQuery = true)
	Object getSubscribedProductDetailByUserIdAndProductId(Long userId, Long productId);
}
