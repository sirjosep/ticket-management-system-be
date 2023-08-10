package com.lawencon.ticketjosep.dao;

import java.util.List;

import com.lawencon.ticketjosep.model.Company;

public interface CompanyDao {
	List<Company> getAll();
	Company getById(Long id);
	Company getByIdRef (Long id);
	
	Company insertCompany(Company company);
	Company update(Company company);
	boolean deleteCompanyById(Long id);
}
