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
import com.lawencon.ticketjosep.dto.ticketcomment.TicketCommentInsertReqDto;
import com.lawencon.ticketjosep.dto.ticketcomment.TicketCommentResDto;
import com.lawencon.ticketjosep.service.TicketCommentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("ticket-comments")
public class TicketCommentController {

	private final TicketCommentService ticketCommentService;

	TicketCommentController(TicketCommentService ticketCommentService) {
		this.ticketCommentService = ticketCommentService;
	}
	
	@PostMapping
	private ResponseEntity<InsertResDto> insert(@RequestBody TicketCommentInsertReqDto data){
		final InsertResDto response = ticketCommentService.addComment(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<List<TicketCommentResDto>> getCommentByTicketId(Long ticketId) {
		final List<TicketCommentResDto> responses = ticketCommentService.getCommentByTicketId(ticketId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
