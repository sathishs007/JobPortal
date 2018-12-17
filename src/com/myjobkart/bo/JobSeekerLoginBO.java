package com.myjobkart.bo;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Administrator
 * 
 */
public class JobSeekerLoginBO  extends BaseBO implements Serializable{ 
//extends BaseLoginBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2292474627861167736L;
	
	
	
	@NotEmpty
	@Email
	private String emailAddress;
	private JobseekerBO jobseekerBO;
	private JobseekerProfileBO jobseekerProfileBO;
	
	
	private String name;

	/**
	 * Password Variable Declaration
	 */

	@NotEmpty
	@Size(min = 4, max = 8)
	private String password;

	/**
	 * ConfirmPassword Variable Declaration
	 */

	@NotEmpty
	@Size(min = 4, max = 8)
	private String confirmPassword;
	
	private boolean rememberMe;

	/**
	 * IsActive Variable Declaration
	 */
	private Boolean isActive;

	/**
	 * Register ID Variable Declaration
	 */
	private long registerId;

	/**
	 * @return the emailAddress
	 */

	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return this.isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the registerId
	 */
	public long getRegisterId() {
		return this.registerId;
	}

	/**
	 * @param registerId
	 *            the registerId to set
	 */
	public void setRegisterId(long registerId) {
		this.registerId = registerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rememberMe
	 */
	public boolean getRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe the rememberMe to set
	 */
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public JobseekerBO getJobseekerBO() {
		return jobseekerBO;
	}

	public void setJobseekerBO(JobseekerBO jobseekerBO) {
		this.jobseekerBO = jobseekerBO;
	}

	public JobseekerProfileBO getJobseekerProfileBO() {
		return jobseekerProfileBO;
	}

	public void setJobseekerProfileBO(JobseekerProfileBO jobseekerProfileBO) {
		this.jobseekerProfileBO = jobseekerProfileBO;
	}

	

	

}
