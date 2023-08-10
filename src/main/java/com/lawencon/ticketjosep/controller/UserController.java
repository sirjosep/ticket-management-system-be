package com.lawencon.ticketjosep.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdatePhotoResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.profile.ProfilePhotoUpdateReqDto;
import com.lawencon.ticketjosep.dto.profile.ProfileResDto;
import com.lawencon.ticketjosep.dto.profile.ProfileUpdateReqDto;
import com.lawencon.ticketjosep.dto.user.ChangePasswordReqDto;
import com.lawencon.ticketjosep.dto.user.UserInsertReqDto;
import com.lawencon.ticketjosep.dto.user.UsersResDto;
import com.lawencon.ticketjosep.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("users")
public class UserController {

	private final UserService userService;
	
	UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UsersResDto>> getAll(
			@RequestParam(value = "roleCode", required = false) String roleCode) {
		final List<UsersResDto> data = userService.getUserByRoleCode(roleCode);
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<ProfileResDto> getProfile(){
		final ProfileResDto response = userService.getUserDetail();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@Valid @RequestBody UserInsertReqDto data){
		final InsertResDto response = userService.insert(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	private ResponseEntity<UpdateResDto> updateProfile(@RequestBody ProfileUpdateReqDto data){
		final UpdateResDto response = userService.update(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping
	private ResponseEntity<UpdatePhotoResDto> updateProfilePhoto(@RequestBody ProfilePhotoUpdateReqDto data){
		final UpdatePhotoResDto response = userService.updatePhoto(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping("/change-password")
	private ResponseEntity<UpdateResDto> changePassword(@RequestBody ChangePasswordReqDto data) {
		final UpdateResDto response = userService.changePassword(data);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
