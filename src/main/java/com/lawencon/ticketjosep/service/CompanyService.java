package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdatePhotoResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.company.CompanyInsertReqDto;
import com.lawencon.ticketjosep.dto.company.CompanyResDto;
import com.lawencon.ticketjosep.dto.company.CompanyUpdatePhotoReqDto;
import com.lawencon.ticketjosep.dto.company.CompanyUpdateReqDto;

public interface CompanyService {
	List<CompanyResDto> getAll();
	CompanyResDto getCompanyDetail(Long id);
	
	UpdateResDto update(CompanyUpdateReqDto data);
	UpdatePhotoResDto updatePhoto(CompanyUpdatePhotoReqDto data);
	InsertResDto insert(CompanyInsertReqDto data); 
	boolean deleteCompanyById(Long id);
}
