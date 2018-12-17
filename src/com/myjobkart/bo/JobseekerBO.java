package com.myjobkart.bo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Vinoth
 * Description : Backing Controller class for JobseekerVO Reviewed by:
 * 
 */
public class JobseekerBO extends BaseBO {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First Name Should be a Character")
	@Size(min = 3, max = 20)
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
	@Email
	@Valid
	private String confirmEmailAddress;
	@NotEmpty
	@Size(min = 4, max = 8,message = "Password Size Must be 4 to 8")
	private String password;
	@NotEmpty
	@Size(min = 4, max = 8,message = "Confirm Password Size Must be 4 to 8")
	@Valid
	private String confirmPassword;
	@NotNull
	@Range(min = 1111111111,message = "Mobile Number must be a 10 Digits")
	private Long mobileNo;
	
    @AssertTrue(message = "Please accept the terms and conditions")
	private Boolean termsConditionsAgreed;

	private String termsConditions;
	private String createDate;

	private Boolean isActive;

	private String jobseeker;

	private Blob profileImage;

	private Boolean isDeleted;

	private long deletedBy;

	private String searchElement;

	private Date deletedDate;
	private String active;

	private int totalDays;
	private long SNo;

	private List<JobseekerBO> registeredList;

	/**
	 * @return the jobseeker
	 */
	public String getJobseeker() {
		return this.jobseeker;
	}

	/**
	 * @param jobseeker
	 *            the jobseeker to set
	 */
	public void setJobseeker(String jobseeker) {
		this.jobseeker = jobseeker;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
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
	public String getLastName() {
		return this.lastName;
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
	 * @return the confirmEmailAddress
	 */
	public String getConfirmEmailAddress() {
		return this.confirmEmailAddress;
	}

	/**
	 * @param confirmEmailAddress
	 *            the confirmEmailAddress to set
	 */
	public void setConfirmEmailAddress(String confirmEmailAddress) {
		this.confirmEmailAddress = confirmEmailAddress;
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
	 * @return the mobileNo
	 */
	public Long getMobileNo() {
		return this.mobileNo;
	}

	/**
	 * @param mobileNo
	 *            the mobileNo to set
	 */
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the termsConditionsAgreed
	 */
	public Boolean getTermsConditionsAgreed() {
		return this.termsConditionsAgreed;
	}

	/**
	 * @param termsConditionsAgreed
	 *            the termsConditionsAgreed to set
	 */
	public void setTermsConditionsAgreed(Boolean termsConditionsAgreed) {
		this.termsConditionsAgreed = termsConditionsAgreed;
	}

	/**
	 * @return the profileImage
	 */
	public Blob getProfileImage() {
		return this.profileImage;
	}

	/**
	 * @param profileImage
	 *            the profileImage to set
	 * @throws SQLException
	 * @throws SerialException
	 */
	public void setProfileImage(byte[] profileImage) throws SerialException,
			SQLException {
		if (null != profileImage) {
			this.profileImage = new SerialBlob(profileImage);
		} else {
			this.profileImage = null;
		}

	}

	/**
	 * @return the registeredList
	 */
	public List<JobseekerBO> getRegisteredList() {
		return this.registeredList;
	}

	/**
	 * @param registeredList
	 *            the registeredList to set
	 */
	public void setRegisteredList(List<JobseekerBO> registeredList) {
		this.registeredList = registeredList;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the deletedBy
	 */
	public long getDeletedBy() {
		return this.deletedBy;
	}

	/**
	 * @param deletedBy
	 *            the deletedBy to set
	 */
	public void setDeletedBy(long deletedBy) {
		this.deletedBy = deletedBy;
	}

	/**
	 * @return the deletedDate
	 */
	public Date getDeletedDate() {
		return this.deletedDate;
	}

	/**
	 * @param deletedDate
	 *            the deletedDate to set
	 */
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	/**
	 * @return the active
	 */
	public String getActive() {
		return this.active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * @return the termsConditions
	 */
	public String getTermsConditions() {
		return this.termsConditions;
	}

	/**
	 * @param termsConditions
	 *            the termsConditions to set
	 */
	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

	/**
	 * @return the searchElement
	 */
	public String getSearchElement() {
		return this.searchElement;
	}

	/**
	 * @param searchElement
	 *            the searchElement to set
	 */
	public void setSearchElement(String searchElement) {
		this.searchElement = searchElement;
	}

	/**
	 * @return the totalDays
	 */
	public int getTotalDays() {
		return this.totalDays;
	}

	/**
	 * @param totalDays
	 *            the totalDays to set
	 */
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public long getSNo() {
		return SNo;
	}

	public void setSNo(long sNo) {
		SNo = sNo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
