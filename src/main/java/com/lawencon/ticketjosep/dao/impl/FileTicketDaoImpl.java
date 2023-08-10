package com.lawencon.ticketjosep.dao.impl;

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
@Profile("native-query")
public class FileTicketDaoImpl implements FileTicketDao {

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
						+ "tft.id, tf.id as fileId, tf.files, tf.file_format " 
						+ "FROM " 
						+ "t_file_ticket tft " 
						+ "INNER JOIN "
						+ "t_file tf ON tft.file_id = tf.id " 
						+ "WHERE " 
						+ "tft.ticket_id = :ticketId";

		final List<?> fileTicketObjs = this.em.createNativeQuery(sql)
				.setParameter("ticketId", ticketId)
				.getResultList();

		if (fileTicketObjs.size() > 0) {
			for (Object fileTicketObj : fileTicketObjs) {
				final Object[] fileTicketArr = (Object[]) fileTicketObj;
				final FileTicket fileTicket = new FileTicket();
				fileTicket.setId(Long.valueOf(fileTicketArr[0].toString()));

				final File file = new File();
				if(fileTicketArr[1] != null) {
					file.setId(Long.valueOf(fileTicketArr[1].toString()));
				}
				file.setFiles(fileTicketArr[2].toString());
				file.setFileFormat(fileTicketArr[3].toString());
				fileTicket.setFile(file);
				fileTickets.add(fileTicket);
			}
		}

		return fileTickets;
	}

}
