package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.ProductDao;
import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.product.ProductInsertReqDto;
import com.lawencon.ticketjosep.dto.product.ProductUpdateReqDto;
import com.lawencon.ticketjosep.model.Product;
import com.lawencon.ticketjosep.service.PrincipalService;
import com.lawencon.ticketjosep.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductDao productDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;

	public ProductServiceImpl(ProductDao productDao, PrincipalService principalService) {
		this.productDao = productDao;
		this.principalService = principalService;
	}

	@Override
	public List<ProductUpdateReqDto> getAll() {
		final List<ProductUpdateReqDto> responses = new ArrayList<>();
		
		productDao.getAll().forEach(p -> {
			final ProductUpdateReqDto response = new ProductUpdateReqDto();
			response.setProductId(p.getId());
			response.setProductCode(p.getProductCode());
			response.setProductName(p.getProductName());
			
			responses.add(response);
		});
		
		return responses;
	}

	@Transactional
	@Override
	public InsertResDto insert(ProductInsertReqDto data) {
		final InsertResDto insertResDto = new InsertResDto();
		
		final Product product = new Product();
		product.setProductCode(data.getProductCode().toUpperCase());
		product.setProductName(data.getProductName());
		product.setCreatedBy(principalService.getId());
		product.setIsActive(true);
		
		final Product newProduct = productDao.insert(product);
		
		if(newProduct != null) {
			insertResDto.setId(newProduct.getId());
			insertResDto.setMsg("Product inserted successfully");
		}
		
		return insertResDto;
	}

	@Transactional
	@Override
	public UpdateResDto update(ProductUpdateReqDto data) {
		final UpdateResDto updateResDto = new UpdateResDto();
		
		final Product currProduct = productDao.getById(data.getProductId());
		currProduct.setProductCode(data.getProductCode());
		currProduct.setProductName(data.getProductName());
		currProduct.setUpdatedBy(principalService.getId());
		
		updateResDto.setMsg("Product updated successfully");
		
		return updateResDto;
	}

}
