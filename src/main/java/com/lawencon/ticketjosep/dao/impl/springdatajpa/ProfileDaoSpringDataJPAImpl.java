package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.ProfileDao;
import com.lawencon.ticketjosep.model.Profile;
import com.lawencon.ticketjosep.repo.ProfileRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class ProfileDaoSpringDataJPAImpl implements ProfileDao{
	
	private ProfileRepo profileRepo;
	
	ProfileDaoSpringDataJPAImpl(ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
	}

	@Override
	public Profile getById(Long id) {
		final Profile profile = profileRepo.findById(id).get();
		return profile;
	}

	@Override
	public Profile insertProfile(Profile profile) {
		profileRepo.save(profile);
		return profile;
	}

	@Override
	public Profile update(Profile profile) {
		final Profile updatedProfile = profileRepo.saveAndFlush(profile);
		return updatedProfile;
	}

}
