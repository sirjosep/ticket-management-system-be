package com.lawencon.ticketjosep.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.dao.FileTicketCommentDao;
import com.lawencon.ticketjosep.model.File;
import com.lawencon.ticketjosep.model.FileTicketComment;

@Repository
@Profile("native-query")
public class FileTicketCommentDaoImpl implements FileTicketCommentDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public FileTicketComment insert(FileTicketComment fileTicketComment) {
		this.em.persist(fileTicketComment);
		return fileTicketComment;
	}

	@Override
	public List<FileTicketComment> getFileByTicketCommentId(Long ticketCommentId) {
		final List<FileTicketComment> fileTicketComments = new ArrayList<>();
		final String sql = "SELECT "
				+ "tftc.id, tf.id as fileId, tf.files, tf.file_format "
				+ "FROM "
				+ "t_file_ticket_comment tftc "
				+ "INNER JOIN "
				+ "t_file tf ON tftc.file_id = tf.id "
				+ "WHERE "
				+ "tftc.ticket_comment_id = :ticketCommentId";
		
		final List<?> fileCommentObjs = this.em.createNativeQuery(sql)
								.setParameter("ticketCommentId", ticketCommentId)
								.getResultList();
		
		if(fileCommentObjs.size() > 0) {
			for(Object fileCommObj : fileCommentObjs) {
				final Object[] fileCommentArr = (Object[])fileCommObj;
				final FileTicketComment fileTicketComment = new FileTicketComment();
				fileTicketComment.setId(Long.valueOf(fileCommentArr[0].toString()));
				
				final File file = new File();
				if(fileCommentArr[1] != null) {
					file.setId(Long.valueOf(fileCommentArr[1].toString()));
				}
				file.setFiles(fileCommentArr[2].toString());
				file.setFileFormat(fileCommentArr[3].toString());
				fileTicketComment.setFile(file);
				fileTicketComments.add(fileTicketComment);
			}
		}
		return fileTicketComments;
	}

}
