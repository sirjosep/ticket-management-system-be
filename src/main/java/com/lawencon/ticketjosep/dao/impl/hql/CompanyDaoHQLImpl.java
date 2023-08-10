package com.lawencon.ticketjosep.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.CompanyDao;
import com.lawencon.ticketjosep.model.Company;

@Repository
@Profile("hql-query")
public class CompanyDaoHQLImpl implements CompanyDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Company> getAll() {
		final String sql = "SELECT c FROM Company c ";
		
		final List<Company> companies = this.em.createQuery(sql, Company.class).getResultList();
		return companies;
	}

	@Override
	public Company insertCompany(Company company) {
		em.persist(company);
		return company;
	}

	@Override
	public boolean deleteCompanyById(Long id) {
		final String sql = "DELETE FROM Company WHERE id = ?";
		
		final int result = this.em.createNativeQuery(sql, Company.class).executeUpdate();
		
		return result > 0;
	}

	@Override
	public Company getById(Long companyId) {
		final Company company = this.em.find(Company.class, companyId);
		return company;
	}

	@Override
	public Company update(Company company) {
		final Company updatedCompany = this.em.merge(company);
		this.em.flush();
		return updatedCompany;
	}

	@Override
	public Company getByIdRef(Long id) {
		final Company company = this.em.getReference(Company.class, id);
		return company;
	}

}
