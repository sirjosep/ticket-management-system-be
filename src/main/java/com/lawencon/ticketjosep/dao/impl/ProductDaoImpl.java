package com.lawencon.ticketjosep.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.ProductDao;
import com.lawencon.ticketjosep.model.Product;

@Repository
@Profile("native-query")
public class ProductDaoImpl implements ProductDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() {
		final String sql = "SELECT * FROM t_product";
		
		final List<Product> products = this.em.createNativeQuery(sql, Product.class).getResultList();
		
		return products;
	}

	@Override
	public Product insert(Product product) {
		em.persist(product);
		return product;
	}

	@Override
	public Product getById(Long ProductId) {
		final Product product = em.find(Product.class, ProductId);
		return product;
	}

	@Override
	public Product getByIdRef(Long id) {
		final Product product = em.getReference(Product.class, id);
		return product;
	}
}
