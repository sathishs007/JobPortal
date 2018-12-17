package com.myjobkart.vo;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "industry")
public class IndustryVO  extends BasicEntity{
	
	
	private String industryName;
	private long industryId;
	private boolean isActiveInvitation;
	private String email;
	private boolean isDeleted;
	/**
	 * @return the industryId
	 */
	@Id
	@GeneratedValue
	public long getIndustryId() {
		return industryId;
	}
	/**
	 * @param industryId the industryId to set
	 */
	public void setIndustryId(long industryId) {
		this.industryId = industryId;
	}
	/**
	 * @return the industryName
	 */@Column(name="Industry_name")
	public String getIndustryName() {
		return industryName;
	}
	/**
	 * @param industryName the industryName to set
	 */
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	/**
	 * @return the isActiveInvitation
	 */
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
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
