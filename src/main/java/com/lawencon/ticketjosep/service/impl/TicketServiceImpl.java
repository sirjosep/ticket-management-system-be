package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.constant.RoleCode;
import com.lawencon.ticketjosep.constant.Status;
import com.lawencon.ticketjosep.dao.DevAssignmentDao;
import com.lawencon.ticketjosep.dao.FileDao;
import com.lawencon.ticketjosep.dao.FileTicketDao;
import com.lawencon.ticketjosep.dao.ProductDao;
import com.lawencon.ticketjosep.dao.TicketDao;
import com.lawencon.ticketjosep.dao.TicketPriorityDao;
import com.lawencon.ticketjosep.dao.TicketStatusDao;
import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.ticket.FileTicketResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketDetailResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketInsertReqDto;
import com.lawencon.ticketjosep.dto.ticket.TicketResDto;
import com.lawencon.ticketjosep.dto.ticket.TicketUpdateStatusReqDto;
import com.lawencon.ticketjosep.model.File;
import com.lawencon.ticketjosep.model.FileTicket;
import com.lawencon.ticketjosep.model.Product;
import com.lawencon.ticketjosep.model.Ticket;
import com.lawencon.ticketjosep.model.TicketPriority;
import com.lawencon.ticketjosep.model.TicketStatus;
import com.lawencon.ticketjosep.model.User;
import com.lawencon.ticketjosep.service.EmailService;
import com.lawencon.ticketjosep.service.PicAssignmentService;
import com.lawencon.ticketjosep.service.PrincipalService;
import com.lawencon.ticketjosep.service.TicketService;
import com.lawencon.ticketjosep.service.TicketStatusService;
import com.lawencon.ticketjosep.util.DateUtil;
import com.lawencon.ticketjosep.util.GeneratorUtil;
import com.lawencon.ticketjosep.validation.CustomException;

@Service
public class TicketServiceImpl implements TicketService {

	private final TicketStatusService ticketStatusService;
	private final UserDao userDao;
	private final ProductDao productDao;
	private final TicketDao ticketDao;
	private final TicketStatusDao ticketStatusDao;
	private final TicketPriorityDao ticketPriorityDao;
	private final PicAssignmentService picAssignmentService;
	private final FileDao fileDao;
	private final FileTicketDao fileTicketDao;
	private final EmailService emailService;
	private final PrincipalService principalService;
	private final DevAssignmentDao devAssignmentDao;

	@PersistenceContext
	private EntityManager em;

	public TicketServiceImpl(TicketStatusService ticketStatusService, TicketDao ticketDao, FileDao fileDao,
			FileTicketDao fileTicketDao, UserDao userDao, ProductDao productDao, TicketPriorityDao ticketPriorityDao,
			TicketStatusDao ticketStatusDao, EmailService emailService, PrincipalService principalService,
			PicAssignmentService picAssignmentService, DevAssignmentDao devAssignmentDao) {
		this.ticketStatusService = ticketStatusService;
		this.userDao = userDao;
		this.productDao = productDao;
		this.ticketDao = ticketDao;
		this.ticketStatusDao = ticketStatusDao;
		this.ticketPriorityDao = ticketPriorityDao;
		this.fileDao = fileDao;
		this.fileTicketDao = fileTicketDao;
		this.emailService = emailService;
		this.principalService = principalService;
		this.picAssignmentService = picAssignmentService;
		this.devAssignmentDao = devAssignmentDao;
	}

	@Transactional
	@Override
	public InsertResDto createTicket(TicketInsertReqDto data) {
		final InsertResDto insertResDto = new InsertResDto();

		final int currTicketPriorityQty = ticketDao
				.getByTicketPriorityAndUserId(data.getTicketPriorityId(), principalService.getId()).size();

		final int priorityLimit = ticketPriorityDao.getById(data.getTicketPriorityId()).getTicketPriorityLimit();

		if (currTicketPriorityQty < priorityLimit || priorityLimit == 0) {
			final User user = userDao.getById(principalService.getId());
			final Product product = productDao.getById(data.getProductId());

			final TicketStatus ticketStatus = ticketStatusDao
					.getByIdRef(ticketStatusService.getTicketByRoleAndStatus(null, null).getId());
			final TicketPriority ticketPriority = ticketPriorityDao.getById(data.getTicketPriorityId());

			final Ticket ticket = new Ticket();
			ticket.setTicketCode(GeneratorUtil.generateAlphaNum(5));
			ticket.setTicketTitle(data.getTicketTitle());
			ticket.setTicketBody(data.getTicketBody());
			ticket.setTicketPriority(ticketPriority);
			ticket.setTicketStatus(ticketStatus);
			ticket.setUser(user);
			ticket.setProduct(product);
			ticket.setCreatedBy((Long) principalService.getId());

			final Ticket insertedTicket = ticketDao.insert(ticket);

			final List<FileTicket> fileTickets = new ArrayList<>();
			if (data.getFileLists() != null) {
				if (data.getFileLists().get(0) != null) {
					for (int i = 0; i < data.getFileLists().size(); i++) {
						final File file = new File();
						file.setFiles(data.getFileLists().get(i).getFiles());
						file.setFileFormat(data.getFileLists().get(i).getFileFormat());
						file.setCreatedBy((Long) principalService.getId());

						fileDao.insertFile(file);

						final FileTicket fileTicket = new FileTicket();
						fileTicket.setTicket(ticket);
						fileTicket.setFile(file);
						fileTicket.setCreatedBy((Long) principalService.getId());

						fileTicketDao.insert(fileTicket);
						fileTickets.add(fileTicket);
					}	
				}
			}

			if (insertedTicket != null) {
				insertResDto.setId(insertedTicket.getId());
				insertResDto.setMsg("Ticket #" + insertedTicket.getTicketCode() + " Created Successfully");

				final String body = "Your customer, " + user.getProfile().getProfileName()
						+ " just created a new Ticket with code " + insertedTicket.getTicketCode() + " at "
						+ DateUtil.dateFormat(insertedTicket.getCreatedAt())
						+ ", please assign a developer as soon as possible.";

				final String email = userDao
						.getById(picAssignmentService.getPicAssignByCustId(principalService.getId()).getPicId())
						.getEmail();
				emailService.sendEmail("New Ticket Created", body, email);
			}
		} else {
			throw new CustomException("Error! Limit reached");
		}

		return insertResDto;
	}

	@Transactional
	@Override
	public UpdateResDto changeTicketStatus(TicketUpdateStatusReqDto data) {
		final UpdateResDto updateResDto = new UpdateResDto();

		final Ticket ticket = ticketDao.getById(data.getTicketId());
		final String currTicketStatus = ticket.getTicketStatus().getTicketStatusName();

		if (ticketStatusService.getTicketByRoleAndStatus(principalService.getRoleCode(),
				ticket.getTicketStatus().getTicketStatusCode()) != null) {

			final TicketStatus ticketStatus = ticketStatusDao
					.getByIdRef(ticketStatusService.getTicketByRoleAndStatus(principalService.getRoleCode(),
							ticket.getTicketStatus().getTicketStatusCode()).getId());

			ticket.setTicketStatus(ticketStatus);
			ticket.setUpdatedBy(principalService.getId());

			ticketDao.update(ticket);

			updateResDto.setVer(ticket.getVer());
			updateResDto.setMsg("Ticket Status changed successfully");

			final String subject = "Ticket Status Changed";
			String body = "";

			final String toEmailCust = ticketDao.getById(data.getTicketId()).getUser().getEmail();
			final String toEmailDev = devAssignmentDao.getByTicketId(data.getTicketId()).getDeveloper().getEmail();
			final String toEmailPic = userDao
					.getById(picAssignmentService.getPicAssignByCustId(ticket.getUser().getId()).getPicId()).getEmail();

			if (RoleCode.DEVELOPER.roleCode.equals(principalService.getRoleCode())
					&& Status.ON_PROGRESS.statusCode.equals(ticketStatus.getTicketStatusCode())) {
				body = "Your ticket with code #" + ticket.getTicketCode() + " has changed status to "
						+ ticketStatus.getTicketStatusName()
						+ ", please wait until our developer take care of your problem and kindly "
						+ "check the forum in case our developer tried to contact you. Thank you.";

				emailService.sendEmail(subject, body, toEmailCust);
			} else if (RoleCode.DEVELOPER.roleCode.equals(principalService.getRoleCode())
					&& Status.PENDING_CUSTOMER.statusCode.equals(ticketStatus.getTicketStatusCode())) {
				body = "Your ticket with code #" + ticket.getTicketCode() + " has changed status to "
						+ ticketStatus.getTicketStatusName()
						+ ", please kindly check again if there is still error and you can "
						+ "close the ticket if there are no problem anymore. Thank you.";

				emailService.sendEmail(subject, body, toEmailCust);
			} else if (RoleCode.PIC.roleCode.equals(principalService.getRoleCode())
					&& Status.RE_OPEN.statusCode.equals(currTicketStatus)) {
				body = "Your Assigned ticket with code #" + ticket.getTicketCode() + " has changed status to "
						+ ticketStatus.getTicketStatusName()
						+ ", please check the ticket and take the customer's problem. Thank you.";

				emailService.sendEmail(subject, body, toEmailDev);
			} else if (RoleCode.CUSTOMER.roleCode.equals(principalService.getRoleCode())
					&& Status.RE_OPEN.statusCode.equals(ticketStatus.getTicketStatusCode())) {
				body = "Your Assigned ticket with code #" + ticket.getTicketCode() + " has changed status to "
						+ ticketStatus.getTicketStatusName()
						+ ", please check the problem as soon as possible or you can contact the customer on the forum";

				emailService.sendEmail(subject, body, toEmailDev);
			} else if (RoleCode.CUSTOMER.roleCode.equals(principalService.getRoleCode())
					&& Status.CLOSED.statusCode.equals(ticketStatus.getTicketStatusCode())) {
				body = "Your Assigned customer with name : " + ticket.getUser().getProfile().getProfileName()
						+ " with ticket code #" + ticket.getTicketCode() + " has changed status to "
						+ ticketStatus.getTicketStatusName()
						+ ", please kindly check the status update in case the customer re opening the ticket. Thank you.";

				emailService.sendEmail(subject, body, toEmailPic);

				final String bodyToDeveloper = "Your Assigned customer with name : "
						+ ticket.getUser().getProfile().getProfileName() + " with ticket code #"
						+ ticket.getTicketCode() + " has changed status to " + ticketStatus.getTicketStatusName()
						+ ", thanks for your hard work!";

				emailService.sendEmail(subject, bodyToDeveloper, toEmailDev);
			}
		} else {
			throw new CustomException("Access forbidden! You don't have access to that!");
		}

		return updateResDto;
	}

	@Override
	public TicketDetailResDto getTicketById(Long ticketId) {
		final TicketDetailResDto response = new TicketDetailResDto();

		final Ticket ticket = ticketDao.getById(ticketId);

		response.setId(ticketId);
		response.setTicketCode(ticket.getTicketCode());
		response.setStatusCode(ticket.getTicketStatus().getTicketStatusCode());
		response.setStatusName(ticket.getTicketStatus().getTicketStatusName());
		response.setPriorityCode(ticket.getTicketPriority().getTicketPriorityCode());
		response.setPriorityName(ticket.getTicketPriority().getTicketPriorityName());
		response.setProductName(ticket.getProduct().getProductName());
		response.setProfileName(ticket.getUser().getProfile().getProfileName());
		response.setTicketTitle(ticket.getTicketTitle());
		response.setTicketBody(ticket.getTicketBody());
		response.setTicketDate(DateUtil.dateFormat(ticket.getCreatedAt()));

		final List<FileTicketResDto> fileResponses = new ArrayList<>();

		final List<FileTicket> files = fileTicketDao.getFileByTicketId(ticketId);
		if (files.size() > 0) {
			files.forEach(f -> {
				final FileTicketResDto fileTicketResDto = new FileTicketResDto();
				fileTicketResDto.setId(f.getFile().getId());
				fileTicketResDto.setFileName(f.getFile().getFiles());
				fileTicketResDto.setFileFormat(f.getFile().getFileFormat());

				fileResponses.add(fileTicketResDto);
			});

			response.setFiles(fileResponses);
		}

		return response;
	}

	@Override
	public List<TicketResDto> getAll(Long priorityId, String statusCode) {
		final List<TicketResDto> responses = new ArrayList<>();

		List<Ticket> tickets = null;

		Long idAcc = principalService.getId();
		if (RoleCode.DEVELOPER.roleCode.equals(principalService.getRoleCode())) {
			tickets = ticketDao.getAssignedTicketByDev(idAcc);
		} else if (RoleCode.CUSTOMER.roleCode.equals(principalService.getRoleCode())) {
			tickets = ticketDao.getTicketByUserId(idAcc);
		} else if (priorityId != null && RoleCode.CUSTOMER.roleCode.equals(principalService.getRoleCode())) {
			tickets = ticketDao.getByTicketPriorityAndUserId(priorityId, idAcc);
		} else if (statusCode != null && RoleCode.PIC.roleCode.equals(principalService.getRoleCode())) {
			tickets = ticketDao.getTicketByPicAndStatusCode(idAcc, statusCode);
		}

		for (int i = 0; i < tickets.size(); i++) {
			final TicketResDto response = new TicketResDto();
			response.setId(tickets.get(i).getId());
			response.setTicketCode(tickets.get(i).getTicketCode());
			response.setTicketStatusCode(tickets.get(i).getTicketStatus().getTicketStatusCode());
			response.setPriorityName(tickets.get(i).getTicketPriority().getTicketPriorityName());
			response.setPriorityCode(tickets.get(i).getTicketPriority().getTicketPriorityCode());
			response.setTicketStatus(tickets.get(i).getTicketStatus().getTicketStatusName());
			response.setTicketDate(DateUtil.dateFormat(tickets.get(i).getCreatedAt()));

			final List<FileTicket> files = fileTicketDao.getFileByTicketId(tickets.get(i).getId());

			if (files.size() > 0) {
				final List<FileTicketResDto> fileResponses = new ArrayList<>();
				for (int j = 0; j < files.size(); j++) {
					final FileTicketResDto fileResponse = new FileTicketResDto();
					fileResponse.setId(files.get(j).getId());
					fileResponse.setFileName(files.get(j).getFile().getFiles());
					fileResponse.setFileFormat(files.get(j).getFile().getFileFormat());

					fileResponses.add(fileResponse);
				}

				response.setFiles(fileResponses);
			}
			responses.add(response);
		}

		return responses;
	}
}
