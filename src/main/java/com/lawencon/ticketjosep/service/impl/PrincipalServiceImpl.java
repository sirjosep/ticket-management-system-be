package com.lawencon.ticketjosep.service.impl;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.service.PrincipalService;

@Service
public class PrincipalServiceImpl implements PrincipalService{
	
	@SuppressWarnings("unchecked")
	@Override
	public String getRoleCode() {
		final Map<String, Object> principals = (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final String roleCode = (String) principals.get("roleCode");
		
		return roleCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getId() {
		final Map<String, Object> principals = (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final int idInt = (int) principals.get("id");
		final long id = (long) idInt;
		
		return id;
	}

}
