package com.lawencon.ticketjosep.dao;

import com.lawencon.ticketjosep.model.Profile;

public interface ProfileDao{
	Profile insertProfile(Profile profile);
	Profile update(Profile profile);
	Profile getById(Long id);
}
