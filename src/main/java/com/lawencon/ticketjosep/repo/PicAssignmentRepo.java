package com.lawencon.ticketjosep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.PicAssignment;

@Repository
public interface PicAssignmentRepo extends JpaRepository<PicAssignment, Long>{
	PicAssignment getPicAssignByCostumerId(Long custId);
}
