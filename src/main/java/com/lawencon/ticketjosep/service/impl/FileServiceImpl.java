package com.lawencon.ticketjosep.service.impl;

import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.dao.FileDao;
import com.lawencon.ticketjosep.model.File;
import com.lawencon.ticketjosep.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	private final FileDao fileDao;
	
	FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public File getById(Long id) {
		return fileDao.getById(id);
	}

}
