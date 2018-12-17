/**
 * 
 */
package com.myjobkart.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * @author ULTIMATE
 *
 */
public class EmployerSubuserBO extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	private long employerId;
	private long empRegid;
	private long sNo;
	
	
	private EmployerLoginBO emploginBO;
	
		
	@NotEmpty
	//@Size(min = 3, max = 20)
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First Name Should be a Character")
	private String firstName;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Last Name Should be a Character")
	private String lastName;
	
	@NotEmpty
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid Email Format")
	@Email
	private String emailAddress;

	@NotEmpty
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid Email Format")
	@Valid
	private String confirmEmailAddress;

	//@Pattern(regexp="^[0-9]*$", message="Contact Number should be a Number")
	@NotNull
	@Range(min = 1111111111, message = "Contact Number must be a 10 Digits")
	private Long contactNumber;
	
	@NotEmpty
	@Size(min = 3,max=8, message = "Password minimum 3 value")
	private String password;
	
	@NotEmpty
	@Valid
	@Size(min = 3,max=8, message = "Confirm Password minimum 3 value")
	private String confirmPassword;
	
	private long deletedBy;

	private Date deletedDate;

	private Boolean isDeletedVal;
	
	private Boolean isActivedVal;
			

	/**
	 * @return the firstName
	 */
	
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailAddress
	 */
	
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the confirmEmailAddress
	 */
	
	public String getConfirmEmailAddress() {
		return confirmEmailAddress;
	}

	/**
	 * @param confirmEmailAddress the confirmEmailAddress to set
	 */
	public void setConfirmEmailAddress(String confirmEmailAddress) {
		this.confirmEmailAddress = confirmEmailAddress;
	}

	/**
	 * @return the contactNumber
	 */

	public Long getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the password
	 */
	
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the deletedBy
	 */
	public long getDeletedBy() {
		return deletedBy;
	}

	/**
	 * @param deletedBy the deletedBy to set
	 */
	public void setDeletedBy(long deletedBy) {
		this.deletedBy = deletedBy;
	}

	/**
	 * @return the deletedDate
	 */
	public Date getDeletedDate() {
		return deletedDate;
	}

	/**
	 * @param deletedDate the deletedDate to set
	 */
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	/**
	 * @return the employerId
	 */
	public long getEmployerId() {
		return employerId;
	}

	/**
	 * @param employerId the employerId to set
	 */
	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}

	/**
	 * @return the isDeletedVal
	 */
	public Boolean getIsDeletedVal() {
		return isDeletedVal;
	}

	/**
	 * @param isDeletedVal the isDeletedVal to set
	 */
	public void setIsDeletedVal(Boolean isDeletedVal) {
		this.isDeletedVal = isDeletedVal;
	}

	/**
	 * @return the isActivedVal
	 */
	public Boolean getIsActivedVal() {
		return isActivedVal;
	}

	/**
	 * @param isActivedVal the isActivedVal to set
	 */
	public void setIsActivedVal(Boolean isActivedVal) {
		this.isActivedVal = isActivedVal;
	}

	

	public EmployerLoginBO getEmploginBO() {
		return emploginBO;
	}

	public void setEmploginBO(EmployerLoginBO emploginBO) {
		this.emploginBO = emploginBO;
	}

	public long getEmpRegid() {
		return empRegid;
	}

	public void setEmpRegid(long empRegid) {
		this.empRegid = empRegid;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}

}
