package com.lawencon.ticketjosep.dao;

import com.lawencon.ticketjosep.model.File;

public interface FileDao {
	
	File getById(Long id);
	
	File insertFile(File file);
	
	boolean deleteFileById(Long id);
}
