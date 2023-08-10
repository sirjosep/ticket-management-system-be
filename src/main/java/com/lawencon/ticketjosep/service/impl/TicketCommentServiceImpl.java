package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.constant.RoleCode;
import com.lawencon.ticketjosep.dao.DevAssignmentDao;
import com.lawencon.ticketjosep.dao.FileDao;
import com.lawencon.ticketjosep.dao.FileTicketCommentDao;
import com.lawencon.ticketjosep.dao.TicketCommentDao;
import com.lawencon.ticketjosep.dao.TicketDao;
import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.ticketcomment.FileTicketCommentResDto;
import com.lawencon.ticketjosep.dto.ticketcomment.TicketCommentInsertReqDto;
import com.lawencon.ticketjosep.dto.ticketcomment.TicketCommentResDto;
import com.lawencon.ticketjosep.model.File;
import com.lawencon.ticketjosep.model.FileTicketComment;
import com.lawencon.ticketjosep.model.Ticket;
import com.lawencon.ticketjosep.model.TicketComment;
import com.lawencon.ticketjosep.model.User;
import com.lawencon.ticketjosep.service.EmailService;
import com.lawencon.ticketjosep.service.PrincipalService;
import com.lawencon.ticketjosep.service.TicketCommentService;
import com.lawencon.ticketjosep.util.DateUtil;

@Service
public class TicketCommentServiceImpl implements TicketCommentService {

	private final FileDao fileDao;
	private final UserDao userDao;
	private final TicketDao ticketDao;
	private final TicketCommentDao ticketCommentDao;
	private final FileTicketCommentDao fileTicketCommentDao;
	private final PrincipalService principalService;
	private final EmailService emailService;
	private final DevAssignmentDao devAssignmentDao;

	@PersistenceContext
	private EntityManager em;

	public TicketCommentServiceImpl(FileDao fileDao, TicketCommentDao ticketCommentDao,
			FileTicketCommentDao fileTicketCommentDao, UserDao userDao, TicketDao ticketDao, 
			PrincipalService principalService, EmailService emailService, DevAssignmentDao devAssignmentDao) {
		this.fileDao = fileDao;
		this.userDao = userDao;
		this.ticketDao = ticketDao;
		this.ticketCommentDao = ticketCommentDao;
		this.principalService = principalService;
		this.emailService = emailService;
		this.fileTicketCommentDao = fileTicketCommentDao;
		this.devAssignmentDao = devAssignmentDao;
	}

	@Transactional
	@Override
	public InsertResDto addComment(TicketCommentInsertReqDto data) {
		final InsertResDto insertResDto = new InsertResDto();
			
		final User user = userDao.getByIdRef(principalService.getId());
		final Ticket ticket = ticketDao.getById(data.getTicketId());

		final TicketComment ticketComment = new TicketComment();
		ticketComment.setUser(user);
		ticketComment.setForumComment(data.getTicketCommentBody());
		ticketComment.setTicket(ticket);
		ticketComment.setCreatedBy(principalService.getId());

		final TicketComment insertedComment = ticketCommentDao.insert(ticketComment);
		
		final List<FileTicketComment> fileTicketComments = new ArrayList<>();
		if (data.getFiles() != null){
			if(data.getFiles().get(0) != null) {
				for (int i = 0; i < data.getFiles().size(); i++) {
					final File file = new File();
					file.setFiles(data.getFiles().get(i).getFiles());
					file.setFileFormat(data.getFiles().get(i).getFileFormat());
					file.setCreatedBy(principalService.getId());
					
					fileDao.insertFile(file);
					
					final FileTicketComment fileTicketComment = new FileTicketComment();
					fileTicketComment.setTicketComment(insertedComment);
					fileTicketComment.setFile(file);
					fileTicketComment.setCreatedBy(principalService.getId());
					
					fileTicketCommentDao.insert(fileTicketComment);
					fileTicketComments.add(fileTicketComment);
				}	
			}
		}
		if (data.getFiles() != null) {
			if (insertedComment != null) {
				insertResDto.setId(insertedComment.getId());
				insertResDto.setMsg("Comment added Successfully");
				

				final String devName = devAssignmentDao.getByTicketId(data.getTicketId()).getDeveloper().getProfile().getProfileName();
				final String devEmail = devAssignmentDao.getByTicketId(data.getTicketId()).getDeveloper().getEmail();
				
				final String custName = ticket.getUser().getProfile().getProfileName();
				final String custEmail = ticket.getUser().getEmail();
				
				final String subject = "New Comment Added for Ticket  #" + ticket.getTicketCode();
				
				if (RoleCode.CUSTOMER.roleCode.equals(principalService.getRoleCode())) {
					final String body = "Hello, " + devName + "! "
							+ "We like to informed you that your ticket have a new comment. you can check the comment from link below. "
							+ "Thank you.";
					
					emailService.sendEmail(subject, body, devEmail);
				} else if (RoleCode.DEVELOPER.roleCode.equals(principalService.getRoleCode())) {
					final String body = "Hello, " + custName + "! "
							+ "We like to informed you that your ticket have a new comment. you can check the comment from link below. "
							+ "Thank you.";
					
					emailService.sendEmail(subject, body, custEmail);
				}
			}
		}
		
		return insertResDto;
	}

	@Override
	public List<TicketCommentResDto> getCommentByTicketId(Long ticketId) {
		final List<TicketCommentResDto> responses = new ArrayList<>();
		
		ticketCommentDao.getCommentByTicketId(ticketId).forEach(tc -> {
			final TicketCommentResDto response = new TicketCommentResDto();
			response.setId(tc.getId());
			if(tc.getUser().getProfile().getFile() != null) {
				response.setFileId(tc.getUser().getProfile().getFile().getId());
			}
			response.setUserId(tc.getUser().getId());
			response.setProfileName(tc.getUser().getProfile().getProfileName());
			response.setRoleName(tc.getUser().getRole().getRoleName());
			response.setTicketCommentBody(tc.getForumComment());
			response.setCreatedAt(DateUtil.dateFormat(tc.getCreatedAt()));
			
				final List<FileTicketComment> files = fileTicketCommentDao.getFileByTicketCommentId(tc.getId());
				if (files != null) {
					final List<FileTicketCommentResDto> fileResponses = new ArrayList<>();
					files.forEach(ftc -> {
						final FileTicketCommentResDto fileResponse = new FileTicketCommentResDto();
						fileResponse.setId(ftc.getFile().getId());
						fileResponse.setFileName(ftc.getFile().getFiles());
						fileResponse.setFileFormat(ftc.getFile().getFileFormat());
						
						fileResponses.add(fileResponse);
						response.setFiles(fileResponses);
				});
			}
			responses.add(response);
		});
		
		return responses;
	}

}
