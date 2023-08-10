package com.lawencon.ticketjosep.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketjosep.dto.ticketpriority.TicketPriorityResDto;
import com.lawencon.ticketjosep.service.TicketPriorityService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("priorities")
public class TicketPriorityController {
	private final TicketPriorityService ticketPriorityService;
	
	TicketPriorityController(TicketPriorityService ticketPriorityService) {
		this.ticketPriorityService = ticketPriorityService;
	}


	@GetMapping
	private ResponseEntity<List<TicketPriorityResDto>> getAll(){
		final List<TicketPriorityResDto> responses = ticketPriorityService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
