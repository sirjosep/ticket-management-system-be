package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.DevAssignmentDao;
import com.lawencon.ticketjosep.model.DeveloperAssignment;
import com.lawencon.ticketjosep.repo.DevAssignmentRepo;

@Repository
@Profile("springdatajpa-query")
public class DevAssignmentDaoSpringDataJPAImpl implements DevAssignmentDao{

	private DevAssignmentRepo devAssignmentRepo;

	DevAssignmentDaoSpringDataJPAImpl(DevAssignmentRepo devAssignmentRepo) {
		this.devAssignmentRepo = devAssignmentRepo;
	}

	@Override
	public DeveloperAssignment insert(DeveloperAssignment developerAssignment) {
		devAssignmentRepo.save(developerAssignment);
		return developerAssignment;
	}

	@Override
	public DeveloperAssignment getByTicketId(Long ticketId) {
		final DeveloperAssignment developerAssignment = devAssignmentRepo.getDeveloperAssignmentByTicketId(ticketId);
		
		return developerAssignment;
	}

	@Override
	public List<DeveloperAssignment> getAll() {
		final List<DeveloperAssignment> developerAssignments = devAssignmentRepo.findAll();
		return developerAssignments;
	}

}
