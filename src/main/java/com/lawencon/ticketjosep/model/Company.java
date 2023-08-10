package com.lawencon.ticketjosep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_company")
public class Company extends Base{
	@Column(name = "company_code", length = 5, unique = true, nullable = false)
	private String companyCode;

	@Column(name = "company_name", length = 50, nullable = false)
	private String companyName;

	@Column(name = "company_phone", length = 13, nullable = false)
	private String companyPhone;

	@Column(name = "company_address", nullable = false)
	private String companyAddress;
	
	@OneToOne
	@JoinColumn(name = "file_id", unique = true)
	private File file;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
