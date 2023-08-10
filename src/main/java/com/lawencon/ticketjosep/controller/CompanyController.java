package com.lawencon.ticketjosep.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdatePhotoResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.company.CompanyInsertReqDto;
import com.lawencon.ticketjosep.dto.company.CompanyResDto;
import com.lawencon.ticketjosep.dto.company.CompanyUpdatePhotoReqDto;
import com.lawencon.ticketjosep.dto.company.CompanyUpdateReqDto;
import com.lawencon.ticketjosep.service.CompanyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("companies")
public class CompanyController {

	private final CompanyService companyService;

	CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> insert(@RequestBody CompanyInsertReqDto data) {
		final InsertResDto response = companyService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<List<CompanyResDto>> getAll(){
		final List<CompanyResDto> response = companyService.getAll();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<CompanyResDto> getCompanyDetail(Long id){
		final CompanyResDto response = companyService.getCompanyDetail(id);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping
	private ResponseEntity<UpdateResDto> updateCompany(@RequestBody CompanyUpdateReqDto data){
		final UpdateResDto response = companyService.update(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping
	private ResponseEntity<UpdatePhotoResDto> updateCompanyPhoto(@RequestBody CompanyUpdatePhotoReqDto data){
		final UpdatePhotoResDto response = companyService.updatePhoto(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
				
	}
}
