package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Aravindhan
 * Description : Backing Controller class for EmployerBO Reviewed by :
 * 
 */

@Entity
@Table(name = "em_login")
public class EmployerLoginVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "email_id")
	private String emailAddress;

	@Column(name = "password")
	private String password;

	@Column(name = "confirmPassword")
	private String confirmPassword;

	@Column(name = "active")
	private boolean isActive;

	@ManyToOne
	@JoinColumn(name = "em_Id")
	private EmployerVO employerRegistration;

	@OneToOne
	@JoinColumn(name = "subuser_id")
	private EmployerSubuserVO employerSubuserVO;

	/**
	 * @return the employerRegistration
	 */

	public EmployerVO getEmployerRegistration() {
		return this.employerRegistration;
	}

	/**
	 * @param employerRegistration
	 *            the employerRegistration to set
	 */
	public void setEmployerRegistration(EmployerVO employerRegistration) {
		this.employerRegistration = employerRegistration;
	}

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

	/**
	 * @return the employerSubuserVO
	 */
	public EmployerSubuserVO getEmployerSubuserVO() {
		return employerSubuserVO;
	}

	/**
	 * @param employerSubuserVO
	 *            the employerSubuserVO to set
	 */
	public void setEmployerSubuserVO(EmployerSubuserVO employerSubuserVO) {
		this.employerSubuserVO = employerSubuserVO;
	}
}
