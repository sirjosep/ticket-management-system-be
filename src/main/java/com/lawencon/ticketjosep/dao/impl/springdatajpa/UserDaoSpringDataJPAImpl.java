package com.lawencon.ticketjosep.dao.impl.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.model.User;
import com.lawencon.ticketjosep.repo.UserRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class UserDaoSpringDataJPAImpl implements UserDao{

	@PersistenceContext
	private EntityManager em;

	private final UserRepo userRepo;
	
	UserDaoSpringDataJPAImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public List<User> getAll() {
		final List<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public List<User> getUserByRoleCode(String roleCode) {
		final List<User> users = userRepo.getUserByRoleRoleCode(roleCode);
		return users;
	}

	@Override
	public User insert(User user) {
		userRepo.save(user);
		return user;
	}

	@Override
	public User getByEmail(String email) {
		final User user = userRepo.getByEmail(email);
		return user;
	}

	@Override
	public User getById(Long id) {
		final User user = userRepo.findById(id).get();
		return user;
	}

	@Override
	public User getByIdRef(Long id) {
		final User user = this.em.getReference(User.class, id);
		return user;
	}
}
