/**
 * 
 */
package com.myjobkart.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ULTIMATE
 * 
 */

@Entity
@Table(name = "em_subuser_creation")
public class EmployerSubuserVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	private EmployerLoginVO employerLogin;	

	private long id;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String confirmEmailAddress;

	private long contactNumber;

	private String password;

	private String confirmPassword;

	private Boolean isDeleted = false;
	
	private Boolean isActive = true;

	/**
	 * @return the employerLogin
	 */

	@ManyToOne
	@JoinColumn(name = "em_id")
	public EmployerLoginVO getEmployerLogin() {
		return employerLogin;
	}

	/**
	 * @param employerLogin
	 *            the employerLogin to set
	 */
	public void setEmployerLogin(EmployerLoginVO employerLogin) {
		this.employerLogin = employerLogin;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	@Column(name = "first_Name")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailAddress
	 */
	@Column(name = "email_Id")
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the confirmEmailAddress
	 */
	@Column(name = "con_Email_Id")
	public String getConfirmEmailAddress() {
		return confirmEmailAddress;
	}

	/**
	 * @param confirmEmailAddress
	 *            the confirmEmailAddress to set
	 */
	public void setConfirmEmailAddress(String confirmEmailAddress) {
		this.confirmEmailAddress = confirmEmailAddress;
	}

	/**
	 * @return the contactNumber
	 */
	@Column(name = "contact_Number")
	public long getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the password
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
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
	@Column(name = "con_Password")
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the isDeleted
	 */
	@Column(name = "is_deleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the isActive
	 */
	@Column(name = "is_activated")
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
}
