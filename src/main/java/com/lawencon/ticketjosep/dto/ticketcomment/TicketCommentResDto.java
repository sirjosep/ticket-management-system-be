package com.lawencon.ticketjosep.dto.ticketcomment;

import java.util.List;

public class TicketCommentResDto {
	private Long id;
	private Long fileId;
	private Long userId;
	private String profileName;
	private String roleName;
	private String ticketCommentBody;
	private String createdAt;
	private List<FileTicketCommentResDto> files;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getTicketCommentBody() {
		return ticketCommentBody;
	}
	public void setTicketCommentBody(String ticketCommentBody) {
		this.ticketCommentBody = ticketCommentBody;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public List<FileTicketCommentResDto> getFiles() {
		return files;
	}
	public void setFiles(List<FileTicketCommentResDto> files) {
		this.files = files;
	}
	
}
