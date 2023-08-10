package com.lawencon.ticketjosep.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.subproduct.SubscribedProductReqDto;
import com.lawencon.ticketjosep.dto.subproduct.SubscribedProductResDto;
import com.lawencon.ticketjosep.service.SubscribedProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("subs-product")
public class SubscribedProductController {

	private final SubscribedProductService subscribedProductService;

	SubscribedProductController(SubscribedProductService subscribedProductService) {
		this.subscribedProductService = subscribedProductService;
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> insert(@RequestBody SubscribedProductReqDto data){
		final InsertResDto response = subscribedProductService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<List<SubscribedProductResDto>> getAll(@RequestParam(value = "userId", required = false) Long userId){
		final List<SubscribedProductResDto> responses = subscribedProductService.getAll(userId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
