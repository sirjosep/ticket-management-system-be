package com.lawencon.ticketjosep.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketjosep.dto.role.RoleResDto;
import com.lawencon.ticketjosep.service.RoleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("roles")
public class RoleController {

	private RoleService roleService;

	RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	private ResponseEntity<List<RoleResDto>> getAll(){
		final List<RoleResDto> responses = roleService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
