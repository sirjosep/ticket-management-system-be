package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.PicAssignmentDao;
import com.lawencon.ticketjosep.model.PicAssignment;
import com.lawencon.ticketjosep.repo.PicAssignmentRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class PicAssignmentDaoSpringDataJPAImpl implements PicAssignmentDao {

	private PicAssignmentRepo picAssignmentRepo;

	PicAssignmentDaoSpringDataJPAImpl(PicAssignmentRepo picAssignmentRepo) {
		this.picAssignmentRepo = picAssignmentRepo;
	}

	@Override
	public PicAssignment insert(PicAssignment picAssignment) {
		picAssignmentRepo.save(picAssignment);
		return picAssignment;
	}

	@Override
	public List<PicAssignment> getAll() {
		final List<PicAssignment> picAssignments = picAssignmentRepo.findAll();

		return picAssignments;
	}

	@Override
	public PicAssignment getPicAssignByCustId(Long custId) {
		final PicAssignment picAssignment = picAssignmentRepo.getPicAssignByCostumerId(custId);
		return picAssignment;
	}
}
