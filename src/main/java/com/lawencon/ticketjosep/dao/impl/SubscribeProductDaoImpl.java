package com.lawencon.ticketjosep.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.SubscribeProductDao;
import com.lawencon.ticketjosep.model.Product;
import com.lawencon.ticketjosep.model.SubscribedProductDetail;

@Repository
@Profile("native-query")
public class SubscribeProductDaoImpl implements SubscribeProductDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SubscribedProductDetail insert(SubscribedProductDetail subscribedProductDetail) {
		this.em.persist(subscribedProductDetail);
		return subscribedProductDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubscribedProductDetail> getSubscribedProductByUser(Long userId) {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_subscribed_product_detail tspd "
				+ "INNER JOIN "
				+ "t_product tp ON tspd.product_id = tp.id "
				+ "WHERE "
				+ "tspd.user_id = :userId";
		
		final List<SubscribedProductDetail> 
					subProductDetails = this.em.createNativeQuery(sql, SubscribedProductDetail.class)
					.setParameter("userId", userId).getResultList();
		
		return subProductDetails;
	}
	
	@Override
	public SubscribedProductDetail getSubscribedProductByUserAndProduct(Long userId, Long productId) {
		final String sql = "SELECT "
				+ "tspd.id, tp.product_name "
				+ "FROM "
				+ "t_product tp "
				+ "INNER JOIN "
				+ "t_subscribed_product_detail tspd ON tspd.product_id = tp.id "
				+ "WHERE "
				+ "tspd.user_id = :userId AND tspd.product_id = :productId";
		try {
			final Object subProdObj = this.em.createNativeQuery(sql)
					.setParameter("userId", userId)
					.setParameter("productId", productId)
					.getSingleResult();
			
			final Object[] subProdArr = (Object[])subProdObj;

			SubscribedProductDetail subscribedProductDetail = null;
			if(subProdArr.length > 0) {
				subscribedProductDetail = new SubscribedProductDetail();
				subscribedProductDetail.setId(Long.valueOf(subProdArr[0].toString()));
				
				final Product product = new Product();
				product.setProductName(subProdArr[1].toString());
				subscribedProductDetail.setProduct(product);
			}
			return subscribedProductDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubscribedProductDetail> getAll() {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_subscribed_product_detail tspd "
				+ "INNER JOIN "
				+ "t_product tpr ON tspd.product_id = tpr.id "
				+ "INNER JOIN "
				+ "t_user tu ON tspd.user_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id";
		
		final List<SubscribedProductDetail> subProductDetails = this.em.createNativeQuery(sql, SubscribedProductDetail.class).getResultList();
		
		return subProductDetails;
	}

}
