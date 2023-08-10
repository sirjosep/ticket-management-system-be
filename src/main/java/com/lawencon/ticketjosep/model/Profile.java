package com.lawencon.ticketjosep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_profile")
public class Profile extends Base{

	@Column(name = "profile_name", length = 50, nullable = false)
	private String profileName;

	@Column(name = "profile_phone", length = 13, nullable = false)
	private String profilePhone;

	@Column(name = "profile_address", nullable = false)
	private String profileAddress;
	
	@OneToOne
	@JoinColumn(name = "file_id", nullable = true)
	private File file;

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
