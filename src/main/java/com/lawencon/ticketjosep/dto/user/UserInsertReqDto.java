package com.lawencon.ticketjosep.dto.user;

import javax.validation.constraints.NotBlank;

public class UserInsertReqDto {
	
	@NotBlank(message = "Email cannot be empty!")
	private String email;
	private Long roleId;
	private Long companyId;
	private String profileName;
	private String profilePhone;
	private String profileAddress;
	private String file;
	private String fileFormat;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getProfilePhone() {
		return profilePhone;
	}

	public void setProfilePhone(String profilePhone) {
		this.profilePhone = profilePhone;
	}

	public String getProfileAddress() {
		return profileAddress;
	}

	public void setProfileAddress(String profileAddress) {
		this.profileAddress = profileAddress;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

}
