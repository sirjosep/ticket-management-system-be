package com.lawencon.ticketjosep.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.assignment.DevAssignmentReqDto;
import com.lawencon.ticketjosep.dto.assignment.DevAssignmentResDto;
import com.lawencon.ticketjosep.service.DevAssignmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("assign-devs")
public class DevAssignmentController {

	private final DevAssignmentService devAssignmentService;

	DevAssignmentController(DevAssignmentService devAssignmentService) {
		this.devAssignmentService = devAssignmentService;
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> insert(@RequestBody DevAssignmentReqDto data){
		final InsertResDto response = devAssignmentService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<List<DevAssignmentResDto>> getAll(){
		final List<DevAssignmentResDto> responses = devAssignmentService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
