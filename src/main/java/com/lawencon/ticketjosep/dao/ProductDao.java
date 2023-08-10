package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.Product;

public interface ProductDao {
	Product insert(Product product);
	Product getById(Long ProductId);
	Product getByIdRef(Long id);
	List<Product> getAll();
}
