package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.CompanyDao;
import com.lawencon.ticketjosep.model.Company;
import com.lawencon.ticketjosep.repo.CompanyRepo;

@Repository
@Profile("springdatajpa-query")
public class CompanyDaoSpringDataJPAImpl implements CompanyDao{

	private CompanyRepo companyRepo;
	
	CompanyDaoSpringDataJPAImpl(CompanyRepo companyRepo) {
		this.companyRepo = companyRepo;
	}

	@Override
	public List<Company> getAll() {
		final List<Company> companies = companyRepo.findAll();
		return companies;
	}

	@Override
	public Company insertCompany(Company company) {
		companyRepo.save(company);
		return company;
	}


	@Override
	public boolean deleteCompanyById(Long id) {
		companyRepo.deleteById(id);
		return true;
	}

	@Override
	public Company getById(Long id) {
		final Company company = companyRepo.findById(id).get();
		return company;
	}

	@Override
	public Company update(Company company) {
		final Company updatedCompany = companyRepo.saveAndFlush(company);
		return updatedCompany;
	}

	@Override
	public Company getByIdRef(Long id) {
		final Company company = companyRepo.getReferenceById(id);
		return company;
	}

}
