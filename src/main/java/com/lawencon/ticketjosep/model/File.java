package com.lawencon.ticketjosep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_file")
public class File extends Base{
	@Column(name = "files", nullable = false)
	private String files;
	
	@Column(name = "file_format", nullable = false)
	private String fileFormat;

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

}
