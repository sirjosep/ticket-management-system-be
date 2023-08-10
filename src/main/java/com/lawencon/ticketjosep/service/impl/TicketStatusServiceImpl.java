package com.lawencon.ticketjosep.service.impl;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.constant.RoleCode;
import com.lawencon.ticketjosep.constant.Status;
import com.lawencon.ticketjosep.dao.TicketStatusDao;
import com.lawencon.ticketjosep.model.TicketStatus;
import com.lawencon.ticketjosep.service.TicketStatusService;

@Service
public class TicketStatusServiceImpl implements TicketStatusService {

	private final TicketStatusDao ticketStatusDao;
	
	public TicketStatusServiceImpl(TicketStatusDao ticketStatusDaoImpl) {
		this.ticketStatusDao = ticketStatusDaoImpl;
	}

	@Override
	public TicketStatus getTicketByRoleAndStatus(String roleCode, String statusCode) {
		TicketStatus ticketStatus = null;
		if (statusCode != null && roleCode != null) {
			if (roleCode.equals(RoleCode.CUSTOMER.roleCode) && statusCode.equals(Status.OPEN.statusCode)) {
				ticketStatus = ticketStatusDao.getStatusByStatusCode(Status.CLOSED.statusCode);
			} else if (roleCode.equals(RoleCode.CUSTOMER.roleCode)
					&& statusCode.equals(Status.PENDING_CUSTOMER.statusCode)) {
				ticketStatus = ticketStatusDao.getStatusByStatusCode(Status.CLOSED.statusCode);
			} else if (roleCode.equals(RoleCode.CUSTOMER.roleCode) && statusCode.equals(Status.CLOSED.statusCode)) {
				ticketStatus = ticketStatusDao.getStatusByStatusCode(Status.RE_OPEN.statusCode);
			} else if (roleCode.equals(RoleCode.PIC.roleCode)
					&& (statusCode.equals(Status.OPEN.statusCode) || statusCode.equals(Status.RE_OPEN.statusCode))) {
				ticketStatus = ticketStatusDao.getStatusByStatusCode(Status.PENDING_AGENT.statusCode);
			} else if (roleCode.equals(RoleCode.DEVELOPER.roleCode) && statusCode.equals(Status.PENDING_AGENT.statusCode)) {
				ticketStatus = ticketStatusDao.getStatusByStatusCode(Status.ON_PROGRESS.statusCode);
			} else if (roleCode.equals(RoleCode.DEVELOPER.roleCode) && statusCode.equals(Status.ON_PROGRESS.statusCode)) {
				ticketStatus = ticketStatusDao.getStatusByStatusCode(Status.PENDING_CUSTOMER.statusCode);
			}
		} else if (statusCode == null & roleCode == null){
			ticketStatus = ticketStatusDao.getStatusByStatusCode(Status.OPEN.statusCode);
		} 

		return ticketStatus;
	}

}
