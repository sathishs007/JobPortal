package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jb_login")
public class JobseekerLoginVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	private long id;

	private String emailAddress;

	private String password;

	private String confirmPassword;

	private boolean isActive;

	private JobseekerVO jobseekerRegistration;

	/**
	 * @return the jobseekerRegistration
	 */
	@OneToOne
	@JoinColumn(name = "jb_Id")
	public JobseekerVO getJobseekerRegistration() {
		return this.jobseekerRegistration;
	}

	/**
	 * @param jobseekerRegistration
	 *            the jobseekerRegistration to set
	 */
	public void setJobseekerRegistration(JobseekerVO jobseekerRegistration) {
		this.jobseekerRegistration = jobseekerRegistration;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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
	 * @return the isActive
	 */
	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

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

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
