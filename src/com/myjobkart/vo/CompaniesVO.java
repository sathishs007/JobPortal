package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "companies_table")
public class CompaniesVO  extends BasicEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long companiesId;
	private String companiesName;
	private boolean isActiveInvitation;
	
	/**
	 * @return the companiesId
	 */
	@Id
	@GeneratedValue
	public long getCompaniesId() {
		return companiesId;
	}
	/**
	 * @param companiesId the companiesId to set
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
	@Column(name="companies_name")
	public String getCompaniesName() {
		return companiesName;
	}
	/**
	 * @param companiesName the companiesName to set
	 */
	public void setCompaniesName(String companiesName) {
		this.companiesName = companiesName;
	}
	@Column(name="is_active_invitation")
	public boolean getIsActiveInvitation() {
		return isActiveInvitation;
	}
	/**
	 * @param isActiveInvitation the isActiveInvitation to set
	 */
	public void setIsActiveInvitation(boolean isActiveInvitation) {
		this.isActiveInvitation = isActiveInvitation;
	}
	
	
}
