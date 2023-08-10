package com.lawencon.ticketjosep.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketDetailResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketInsertReqDto;
import com.lawencon.ticketjosep.dto.ticket.TicketResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketUpdateStatusReqDto;
import com.lawencon.ticketjosep.service.TicketService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("tickets")
public class TicketController {

	private final TicketService ticketService;

	TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping
	private ResponseEntity<InsertResDto> insert(@RequestBody TicketInsertReqDto data) {
		final InsertResDto response = ticketService.createTicket(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PatchMapping
	private ResponseEntity<UpdateResDto> changeStatus(@RequestBody TicketUpdateStatusReqDto data) {
		final UpdateResDto response = ticketService.changeTicketStatus(data);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	private ResponseEntity<List<TicketResDto>> getAll(Long priorityId, String statusCode) {
		final List<TicketResDto> response = ticketService.getAll(priorityId, statusCode);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	private ResponseEntity<TicketDetailResDto> getTicketById(Long ticketId){
		final TicketDetailResDto response = ticketService.getTicketById(ticketId);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
