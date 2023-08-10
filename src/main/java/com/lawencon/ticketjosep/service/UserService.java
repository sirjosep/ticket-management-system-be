package com.lawencon.ticketjosep.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdatePhotoResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.account.LoginReqDto;
import com.lawencon.ticketjosep.dto.account.LoginResDto;
import com.lawencon.ticketjosep.dto.profile.ProfilePhotoUpdateReqDto;
import com.lawencon.ticketjosep.dto.profile.ProfileResDto;
import com.lawencon.ticketjosep.dto.profile.ProfileUpdateReqDto;
import com.lawencon.ticketjosep.dto.user.ChangePasswordReqDto;
import com.lawencon.ticketjosep.dto.user.UserInsertReqDto;
import com.lawencon.ticketjosep.dto.user.UsersResDto;

public interface UserService extends UserDetailsService{
	List<UsersResDto> getUserByRoleCode(String roleCode);
	
	InsertResDto insert(UserInsertReqDto data);
	UpdateResDto update(ProfileUpdateReqDto data);
	UpdateResDto changePassword(ChangePasswordReqDto data);
	
	UpdatePhotoResDto updatePhoto(ProfilePhotoUpdateReqDto data);
	
	LoginResDto login(LoginReqDto data);
	
	ProfileResDto getUserDetail();
}
