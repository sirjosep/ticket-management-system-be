package com.lawencon.ticketjosep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long>{

}
