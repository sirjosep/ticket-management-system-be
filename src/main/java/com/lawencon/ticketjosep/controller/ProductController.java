package com.lawencon.ticketjosep.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.product.ProductInsertReqDto;
import com.lawencon.ticketjosep.dto.product.ProductUpdateReqDto;
import com.lawencon.ticketjosep.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("products")
public class ProductController {

	private final ProductService productService;

	ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody ProductInsertReqDto data){
		final InsertResDto response = productService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<List<ProductUpdateReqDto>> getAll(){
		final List<ProductUpdateReqDto> response = productService.getAll();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping
	private ResponseEntity<UpdateResDto> updateProduct(@RequestBody ProductUpdateReqDto data){
		final UpdateResDto response = productService.update(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
