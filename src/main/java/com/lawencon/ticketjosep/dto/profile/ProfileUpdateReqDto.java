package com.lawencon.ticketjosep.dto.profile;

public class ProfileUpdateReqDto {
	private Long profileId;
	private String profileName;
	private String profilePhone;
	private String profileAddress;
	private Integer version;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	

}
