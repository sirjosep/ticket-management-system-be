package com.lawencon.ticketjosep.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.CompanyDao;
import com.lawencon.ticketjosep.model.Company;

@Repository
@Profile("native-query")
public class CompanyDaoImpl implements CompanyDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getAll() {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_company tc "
				+ "INNER JOIN "
				+ "t_file tf ON tc.file_id = tf.id";
		
		final List<Company> companies = this.em.createNativeQuery(sql, Company.class).getResultList();
		return companies;
	}

	@Override
	public Company insertCompany(Company company) {
		em.persist(company);
		return company;
	}


	@Override
	public boolean deleteCompanyById(Long id) {
		final String sql = "DELETE FROM t_company WHERE id = ?";
		
		final int result = this.em.createNativeQuery(sql, Company.class).executeUpdate();
		
		return result != 0;
	}

	@Override
	public Company getById(Long id) {
		final Company company = this.em.find(Company.class, id);
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
