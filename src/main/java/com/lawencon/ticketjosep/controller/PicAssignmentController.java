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
import com.lawencon.ticketjosep.dto.assignment.PicAssignmentReqDto;
import com.lawencon.ticketjosep.dto.assignment.PicAssignmentResDto;
import com.lawencon.ticketjosep.service.PicAssignmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("pic-assignments")
public class PicAssignmentController {
	
	private final PicAssignmentService picAssignmentService;

	PicAssignmentController(PicAssignmentService picAssignmentService) {
		this.picAssignmentService = picAssignmentService;
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody PicAssignmentReqDto data){
		final InsertResDto response = picAssignmentService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<List<PicAssignmentResDto>> getAll(){
		final List<PicAssignmentResDto> response = picAssignmentService.getAll();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/find")
	private ResponseEntity<PicAssignmentResDto> getByCustId(@RequestParam("custId") Long custId){
		final PicAssignmentResDto response = picAssignmentService.getPicAssignByCustId(custId);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
