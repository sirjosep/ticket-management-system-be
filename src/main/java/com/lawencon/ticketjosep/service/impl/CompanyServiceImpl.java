package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.CompanyDao;
import com.lawencon.ticketjosep.dao.FileDao;
import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdatePhotoResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.company.CompanyInsertReqDto;
import com.lawencon.ticketjosep.dto.company.CompanyResDto;
import com.lawencon.ticketjosep.dto.company.CompanyUpdatePhotoReqDto;
import com.lawencon.ticketjosep.dto.company.CompanyUpdateReqDto;
import com.lawencon.ticketjosep.model.Company;
import com.lawencon.ticketjosep.model.File;
import com.lawencon.ticketjosep.service.CompanyService;
import com.lawencon.ticketjosep.service.PrincipalService;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	private final CompanyDao companyDao;
	private final FileDao fileDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;

	CompanyServiceImpl(CompanyDao companyDao, FileDao fileDao, PrincipalService principalService) {
		this.companyDao = companyDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}

	@Override
	public List<CompanyResDto> getAll() {
		final List<CompanyResDto> companyResDtos = new ArrayList<>();
		
		companyDao.getAll().forEach(c -> {
			final CompanyResDto companyResDto = new CompanyResDto();
			companyResDto.setId(c.getId());
			companyResDto.setCompanyCode(c.getCompanyCode());
			companyResDto.setCompanyName(c.getCompanyName());
			companyResDto.setCompanyPhone(c.getCompanyPhone());
			companyResDto.setCompanyAddress(c.getCompanyAddress());
			companyResDto.setFileId(c.getFile().getId());
			
			companyResDtos.add(companyResDto);
		});
		return companyResDtos;
	}

	@Override
	public boolean deleteCompanyById(Long id) {
		return companyDao.deleteCompanyById(id);
	}

	@Transactional
	@Override
	public InsertResDto insert(CompanyInsertReqDto data) {
		final InsertResDto insertResDto = new InsertResDto();
		
		final Company company = new Company();
		company.setCompanyCode(data.getCompanyCode().toUpperCase());
		company.setCompanyName(data.getCompanyName());
		company.setCompanyPhone(data.getCompanyPhone());
		company.setCompanyAddress(data.getCompanyAddress());
		company.setCreatedBy(principalService.getId());
		
		final File newFile = new File();
		newFile.setFiles(data.getFiles());
		newFile.setFileFormat(data.getFileFormat());
		newFile.setCreatedBy(principalService.getId());
		fileDao.insertFile(newFile);
		
		company.setFile(newFile);
		
		final Company newCompany = companyDao.insertCompany(company);
		
		if(newCompany != null) {
			insertResDto.setId(newCompany.getId());
			insertResDto.setMsg("Company created Successfully");
		}
		return insertResDto;
	}

	@Transactional
	@Override
	public UpdateResDto update(CompanyUpdateReqDto data) {
		final UpdateResDto updateResDto = new UpdateResDto();
		final Company company = companyDao.getById(data.getCompanyId());
		company.setCompanyCode(data.getCompanyCode());
		company.setCompanyName(data.getCompanyName());
		company.setCompanyPhone(data.getCompanyPhone());
		company.setCompanyAddress(data.getCompanyAddress());
		company.setUpdatedBy(principalService.getId());
			
		companyDao.update(company);
			
		updateResDto.setVer(company.getVer());
		updateResDto.setMsg("Company updated successfully");
		
		return updateResDto;
	}

	@Transactional
	@Override
	public UpdatePhotoResDto updatePhoto(CompanyUpdatePhotoReqDto data) {
		final UpdatePhotoResDto response = new UpdatePhotoResDto();
		
		Company company = companyDao.getById(data.getCompanyId());
		final Long fileId = company.getFile().getId();
			
		final File newFile = new File();
		newFile.setFiles(data.getFile());
		newFile.setFileFormat(data.getFileFormat());
		newFile.setCreatedBy(principalService.getId());
		fileDao.insertFile(newFile);

		company.setFile(newFile);
		company.setUpdatedBy(principalService.getId());
			
		companyDao.update(company);
		fileDao.deleteFileById(fileId);
		
		response.setVer(company.getVer());
		response.setMsg("Company photo updated successfully");
		response.setFileId(company.getFile().getId());
		return response;
	}

	@Override
	public CompanyResDto getCompanyDetail(Long id) {
		final Company c = companyDao.getById(id);
		
		final CompanyResDto companyResDto = new CompanyResDto();
		companyResDto.setId(c.getId());
		companyResDto.setCompanyCode(c.getCompanyCode());
		companyResDto.setCompanyName(c.getCompanyName());
		companyResDto.setCompanyPhone(c.getCompanyPhone());
		companyResDto.setCompanyAddress(c.getCompanyAddress());
		companyResDto.setFileId(c.getFile().getId());
		
		return companyResDto;
	}

}
