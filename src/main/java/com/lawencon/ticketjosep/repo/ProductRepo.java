package com.lawencon.ticketjosep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	
}
