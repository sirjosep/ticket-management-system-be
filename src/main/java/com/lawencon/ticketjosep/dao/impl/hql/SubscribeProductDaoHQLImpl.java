package com.lawencon.ticketjosep.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.SubscribeProductDao;
import com.lawencon.ticketjosep.model.Product;
import com.lawencon.ticketjosep.model.SubscribedProductDetail;

@Repository
@Profile("hql-query")
public class SubscribeProductDaoHQLImpl implements SubscribeProductDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SubscribedProductDetail insert(SubscribedProductDetail subscribedProductDetail) {
		this.em.persist(subscribedProductDetail);
		return subscribedProductDetail;
	}

	@Override
	public List<SubscribedProductDetail> getSubscribedProductByUser(Long userId) {
		final String sql = "SELECT "
				+ "spd "
				+ "FROM "
				+ "SubscribedProductDetail spd "
				+ "WHERE "
				+ "spd.user.id = :userId";
		
		final List<SubscribedProductDetail> 
					subProductDetails = this.em.createQuery(sql, SubscribedProductDetail.class)
					.setParameter("userId", userId).getResultList();
		
		return subProductDetails;
	}
	
	@Override
	public SubscribedProductDetail getSubscribedProductByUserAndProduct(Long userId, Long productId) {
		final String sql = "SELECT "
				+ "spd.id, spd.product.productName "
				+ "FROM "
				+ "SubscribedProductDetail spd "
				+ "WHERE "
				+ "spd.user.id = :userId AND spd.product.id = :productId";
		try {
			final Object subProdObj = this.em.createQuery(sql)
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

	@Override
	public List<SubscribedProductDetail> getAll() {
		final String sql = "SELECT "
				+ "spd "
				+ "FROM "
				+ "SubscribedProductDetail spd ";
		
		final List<SubscribedProductDetail> subProductDetails = this.em.createQuery(sql, SubscribedProductDetail.class).getResultList();
		
		return subProductDetails;
	}

}
