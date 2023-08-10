package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.SubscribeProductDao;
import com.lawencon.ticketjosep.model.Product;
import com.lawencon.ticketjosep.model.SubscribedProductDetail;
import com.lawencon.ticketjosep.repo.SubscribeProductRepo;

@Repository
@Profile("springdatajpa-query")
public class SubscribeProductDaoSpringDataJPAImpl implements SubscribeProductDao {

	private SubscribeProductRepo subscribeProductRepo;

	SubscribeProductDaoSpringDataJPAImpl(SubscribeProductRepo subscribeProductRepo) {
		this.subscribeProductRepo = subscribeProductRepo;
	}

	@Override
	public SubscribedProductDetail insert(SubscribedProductDetail subscribedProductDetail) {
		subscribeProductRepo.save(subscribedProductDetail);
		return subscribedProductDetail;
	}

	@Override
	public List<SubscribedProductDetail> getSubscribedProductByUser(Long userId) {
		final List<SubscribedProductDetail> subProductDetails = subscribeProductRepo
				.getSubscribedProductDetailByUserId(userId);

		return subProductDetails;
	}

	@Override
	public SubscribedProductDetail getSubscribedProductByUserAndProduct(Long userId, Long productId) {
		try {
			final Object subProdObj = subscribeProductRepo.getSubscribedProductDetailByUserIdAndProductId(userId,
					productId);

			final Object[] subProdArr = (Object[]) subProdObj;

			SubscribedProductDetail subscribedProductDetail = null;
			if (subProdArr.length > 0) {
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
		final List<SubscribedProductDetail> subProductDetails = subscribeProductRepo.findAll();

		return subProductDetails;
	}

}
