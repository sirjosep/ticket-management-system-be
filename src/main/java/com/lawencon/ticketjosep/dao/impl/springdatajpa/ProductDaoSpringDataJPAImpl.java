package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.ProductDao;
import com.lawencon.ticketjosep.model.Product;
import com.lawencon.ticketjosep.repo.ProductRepo;

@Repository
@Profile("springdatajpa-query")
public class ProductDaoSpringDataJPAImpl implements ProductDao{

	private ProductRepo productRepo;

	ProductDaoSpringDataJPAImpl(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public List<Product> getAll() {
		final List<Product> products = productRepo.findAll();
		
		return products;
	}

	@Override
	public Product insert(Product product) {
		productRepo.save(product);
		return product;
	}

	@Override
	public Product getById(Long productId) {
		final Product product = productRepo.findById(productId).get();
		return product;
	}

	@Override
	public Product getByIdRef(Long id) {
		final Product product = productRepo.getReferenceById(id);
		return product;
	}
}
