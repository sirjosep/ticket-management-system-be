package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.ProductDao;
import com.lawencon.ticketjosep.dao.SubscribeProductDao;
import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.subproduct.SubscribedProductReqDto;
import com.lawencon.ticketjosep.dto.subproduct.SubscribedProductResDto;
import com.lawencon.ticketjosep.model.Product;
import com.lawencon.ticketjosep.model.SubscribedProductDetail;
import com.lawencon.ticketjosep.model.User;
import com.lawencon.ticketjosep.service.PrincipalService;
import com.lawencon.ticketjosep.service.SubscribedProductService;

@Service
public class SubscribedProductServiceImpl implements SubscribedProductService {

	private final UserDao userDao;
	private final ProductDao productDao;
	private final SubscribeProductDao subscribeProductDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public SubscribedProductServiceImpl(SubscribeProductDao subscribeProductDao, UserDao userDao, ProductDao productDao,
			PrincipalService principalService) {
		this.userDao = userDao;
		this.productDao = productDao;
		this.subscribeProductDao = subscribeProductDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(SubscribedProductReqDto data) {
		final InsertResDto insertResDto = new InsertResDto();

		for (int i = 0; i < data.getProductId().size(); i++) {
			final User user = userDao.getById(data.getUserId());
			final Product product = productDao.getById(data.getProductId().get(i));

			final SubscribedProductDetail subDetail = subscribeProductDao
					.getSubscribedProductByUserAndProduct(data.getUserId(), data.getProductId().get(i));

			if (subDetail == null) {
				final SubscribedProductDetail subscribedProductDetail = new SubscribedProductDetail();
				subscribedProductDetail.setUser(user);
				subscribedProductDetail.setProduct(product);
				subscribedProductDetail.setCreatedBy(principalService.getId());
				subscribedProductDetail.setIsActive(true);

				final SubscribedProductDetail spd = subscribeProductDao.insert(subscribedProductDetail);
				if(spd != null) {
					insertResDto.setMsg("Product subscribed successfully");
				}
			} else if (subDetail != null){
				insertResDto.setMsg("Failed! customer name " + user.getProfile().getProfileName()
						+ " is already subscribed with product " + product.getProductName());
				break;
			}
		}

		return insertResDto;
	}

	@Override
	public List<SubscribedProductResDto> getAll(Long userId) {
		final List<SubscribedProductResDto> responses = new ArrayList<>();

		List<SubscribedProductDetail> subscribedProductDetails = null;
		if (userId == null) {
			subscribedProductDetails = subscribeProductDao.getAll();
		} else {
			subscribedProductDetails = subscribeProductDao.getSubscribedProductByUser(userId);
		}

		subscribedProductDetails.forEach(sp -> {
			final SubscribedProductResDto subscribedProductResDto = new SubscribedProductResDto();
			subscribedProductResDto.setId(sp.getId());
			subscribedProductResDto.setProfileId(sp.getUser().getId());
			subscribedProductResDto.setProductName(sp.getProduct().getProductName());
			subscribedProductResDto.setProductId(sp.getProduct().getId());
			subscribedProductResDto.setProfileName(sp.getUser().getProfile().getProfileName());

			responses.add(subscribedProductResDto);
		});
		return responses;
	}
}
