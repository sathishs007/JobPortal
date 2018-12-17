package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Aravindhan
 * Description : Backing Controller class for EmployerBO Reviewed by :
 * 
 */

@Entity
@Table(name = "admin_login")
public class AdminLoginVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	private long id;

	private String emailAddress;

	private String password;

	private String userType;

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
	@Column(name = "password")
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
	 * @return the emailAddress
	 */
	@Column(name = "email_id")
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

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
