package com.lawencon.ticketjosep.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.FileTicketDao;
import com.lawencon.ticketjosep.model.File;
import com.lawencon.ticketjosep.model.FileTicket;

@Repository
@Profile("hql-query")
public class FileTicketDaoHQLImpl implements FileTicketDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public FileTicket insert(FileTicket fileTicket) {
		this.em.persist(fileTicket);
		return fileTicket;
	}

	@Override
	public List<FileTicket> getFileByTicketId(Long ticketId) {
		final List<FileTicket> fileTickets = new ArrayList<>();
		final String sql = "SELECT " 
						+ "ft.id, ft.file.files, ft.file.fileFormat " 
						+ "FROM " 
						+ "FileTicket ft " 
						+ "WHERE " 
						+ "ft.ticket.id = :ticketId";

		final List<?> fileTicketObjs = this.em.createQuery(sql)
				.setParameter("ticketId", ticketId)
				.getResultList();

		if (fileTicketObjs.size() > 0) {
			for (Object fileTicketObj : fileTicketObjs) {
				final Object[] fileTicketArr = (Object[]) fileTicketObj;
				final FileTicket fileTicket = new FileTicket();
				fileTicket.setId(Long.valueOf(fileTicketArr[0].toString()));

				final File file = new File();
				file.setFiles(fileTicketArr[1].toString());
				file.setFileFormat(fileTicketArr[2].toString());
				fileTicket.setFile(file);
				fileTickets.add(fileTicket);
			}
		}

		return fileTickets;
	}

}
