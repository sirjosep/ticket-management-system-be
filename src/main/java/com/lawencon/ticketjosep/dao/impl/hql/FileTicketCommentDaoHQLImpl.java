package com.lawencon.ticketjosep.dao.impl.hql;

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
@Profile("hql-query")
public class FileTicketCommentDaoHQLImpl implements FileTicketCommentDao{

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
				+ "ftc.id, ftc.file.files, ftc.file.fileFormat "
				+ "FROM "
				+ "FileTicketComment ftc "
				+ "WHERE "
				+ "ftc.ticketComment.id = :ticketCommentId";
		
		final List<?> fileCommentObjs = this.em.createQuery(sql)
								.setParameter("ticketCommentId", ticketCommentId)
								.getResultList();
		
		if(fileCommentObjs.size() > 0) {
			for(Object fileCommObj : fileCommentObjs) {
				final Object[] fileCommentArr = (Object[])fileCommObj;
				final FileTicketComment fileTicketComment = new FileTicketComment();
				fileTicketComment.setId(Long.valueOf(fileCommentArr[0].toString()));
				
				final File file = new File();
				file.setFiles(fileCommentArr[1].toString());
				file.setFileFormat(fileCommentArr[2].toString());
				fileTicketComment.setFile(file);
				fileTicketComments.add(fileTicketComment);
			}
		}
		return fileTicketComments;
	}

}
