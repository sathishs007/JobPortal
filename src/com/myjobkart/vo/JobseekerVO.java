package com.myjobkart.vo;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Vinoth
 * Description : Backing Controller class for JobseekerVO Reviewed by :
 */

@Entity
@Table(name = "jb_registration")
public class JobseekerVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	private long id;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String confirmEmailAddress;

	private String password;

	private Blob profileImage;

	private String confirmPassword;

	private long mobileNo;

	private Boolean termsConditionsAgreed;

	private Boolean isDelete;

	private Date deletedDate;

	private long deleteBy;

	@Id
	@GeneratedValue
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the mobileNo
	 */
	@Column(name = "mobile_NO")
	public long getMobileNo() {
		return this.mobileNo;
	}

	/**
	 * @param mobileNo
	 *            the mobileNo to set
	 */
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	private Boolean isActive;

	/**
	 * @return the firestName
	 */
	@Column(name = "first_Name")
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
	@Column(name = "last_Name")
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
	@Column(name = "email_Id")
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
	@Column(name = "con_Email_Id")
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
	@Column(name = "confirm_Password")
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
	 */
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}

	/**
	 * @return the isDelete
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}

	/**
	 * @param isDelete
	 *            the isDelete to set
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the deletedDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
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
	 * @return the deleteBy
	 */
	public long getDeleteBy() {
		return this.deleteBy;
	}

	/**
	 * @param deleteBy
	 *            the deleteBy to set
	 */
	public void setDeleteBy(long deleteBy) {
		this.deleteBy = deleteBy;
	}

}
