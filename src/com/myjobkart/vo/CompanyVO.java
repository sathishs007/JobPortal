package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "company")
public class CompanyVO extends BasicEntity {

	private static final long serialVersionUID = 1L;
	private long companiesId;
	private String companiesName;
	private boolean isActiveInvitation = false;
	private String email;
	private boolean isDeleted = false;
	

	/**
	 * @return the email
	 */
	@Column(name = "email_address")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the companiesId
	 */
	@Id
	@GeneratedValue
	public long getCompaniesId() {
		return companiesId;
	}

	/**
	 * @param companiesId
	 *            the companiesId to set
	 */
	public void setCompaniesId(long companiesId) {
		this.companiesId = companiesId;
	}

	/**
	 * @return the companiesName
	 */
	/**
	 * @return the companiesName
	 */
	@Column(name = "companies_name")
	public String getCompaniesName() {
		return companiesName;
	}

	/**
	 * @param companiesName
	 *            the companiesName to set
	 */
	public void setCompaniesName(String companiesName) {
		this.companiesName = companiesName;
	}

	@Column(name = "is_active_invitation")
	public boolean getIsActiveInvitation() {
		return isActiveInvitation;
	}

	/**
	 * @param isActiveInvitation
	 *            the isActiveInvitation to set
	 */
	public void setIsActiveInvitation(boolean isActiveInvitation) {
		this.isActiveInvitation = isActiveInvitation;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
}
