package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.RoleDao;
import com.lawencon.ticketjosep.dto.role.RoleResDto;
import com.lawencon.ticketjosep.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	private final RoleDao roleDao;
	
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<RoleResDto> getAll() {
		final List<RoleResDto> roleResDtos = new ArrayList<>();
		roleDao.getAll().forEach(u -> {
			final RoleResDto roleResDto = new RoleResDto();
			roleResDto.setId(u.getId());
			roleResDto.setRoleCode(u.getRoleCode());
			roleResDto.setRoleName(u.getRoleName());
			
			roleResDtos.add(roleResDto);
		});
		return roleResDtos;
	}

}
