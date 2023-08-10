package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.PicAssignmentDao;
import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.assignment.PicAssignmentReqDto;
import com.lawencon.ticketjosep.dto.assignment.PicAssignmentResDto;
import com.lawencon.ticketjosep.model.PicAssignment;
import com.lawencon.ticketjosep.model.User;
import com.lawencon.ticketjosep.service.PicAssignmentService;
import com.lawencon.ticketjosep.service.PrincipalService;
import com.lawencon.ticketjosep.validation.CustomException;

@Service
public class PicAssignmentServiceImpl implements PicAssignmentService {

	private final UserDao userDao;
	private final PicAssignmentDao picAssignmentDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public PicAssignmentServiceImpl(PicAssignmentDao picAssignmentDao, UserDao userDao,
			PrincipalService principalService) {
		this.picAssignmentDao = picAssignmentDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insert(PicAssignmentReqDto data) {
		final InsertResDto insertResDto = new InsertResDto();

		for (int i = 0; i < data.getCustId().size(); i++) {
			final User pic = userDao.getByIdRef(data.getPicId());
			final User customer = userDao.getById(data.getCustId().get(i));

			if (picAssignmentDao.getPicAssignByCustId(data.getCustId().get(i)) == null) {
				final PicAssignment picAssignment = new PicAssignment();
				picAssignment.setPic(pic);
				picAssignment.setCostumer(customer);
				picAssignment.setCreatedBy(principalService.getId());

				final PicAssignment insertedAssign = picAssignmentDao.insert(picAssignment);
				if (insertedAssign != null) {
					insertResDto.setMsg("Success assign PIC");
				}
			} else {
				throw new CustomException("Customer dengan nama " + customer.getProfile().getProfileName()
						+ " sudah memiliki PIC, silahkan pilih customer yang lain");
			}
		}

		return insertResDto;
	}

	@Override
	public List<PicAssignmentResDto> getAll() {
		final List<PicAssignmentResDto> responses = new ArrayList<>();

		picAssignmentDao.getAll().forEach(u -> {
			final PicAssignmentResDto response = new PicAssignmentResDto();
			response.setCostumerId(u.getCostumer().getId());
			response.setCostumerName(u.getCostumer().getProfile().getProfileName());
			response.setPicId(u.getPic().getId());
			response.setPicName(u.getPic().getProfile().getProfileName());

			responses.add(response);
		});

		return responses;
	}

	@Override
	public PicAssignmentResDto getPicAssignByCustId(Long custId) {
		final PicAssignmentResDto response = new PicAssignmentResDto();

		final PicAssignment picAssignment = picAssignmentDao.getPicAssignByCustId(custId);
		response.setPicId(picAssignment.getPic().getId());
		response.setPicName(picAssignment.getPic().getProfile().getProfileName());
		response.setCostumerId(picAssignment.getCostumer().getId());
		response.setCostumerName(picAssignment.getCostumer().getProfile().getProfileName());

		return response;
	}

}
