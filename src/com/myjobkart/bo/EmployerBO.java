package com.myjobkart.bo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Aravindhan
 * Description : Backing Controller class for EmployerBO Reviewed by :
 * 
 */

public class EmployerBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	@NotEmpty
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

	@NotNull
	@Range(min = 1111111111, message = "Contact Number must be a 10 Digits")
	private Long contactNumber;


	@NotNull
	@Range(min = 1111111111, message = "Mobile Number must be a 10 Digits")
	private Long mobileNumber;
	@NotEmpty
	@Size(min = 4,max=8, message = "Password minimum 4 value")
	private String password;
	@NotEmpty
	@Valid

	@Size(min = 4,max=8, message = "Confirm Password minimum 4 value")
	private String confirmPassword;

	
	private String companyName;
	
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Company Type Should be a Character")
	private String companyType;

	
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Industry Type Should be a Character")
	private String industryType;
	@NotEmpty
	@Pattern(regexp = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]", message = "Invalid Website Format")
	private String webSite;

	private Boolean isActive;
	
	@AssertTrue(message="Please accept the terms and conditions")
	private Boolean termsConditionsAgreed;

	private Blob companyLogo;

	private List<EmployerBO> registeredList;

	private long deletedBy;

	private Date deletedDate;

	private Boolean isDeleted;
	
	private String termsConditions;
	private String active;
	private String searchElement;
	private int totalDate;
	private Date sdate;
	private Date edate;
	private String total;
	private String otherCompany;
	private long companyId;
	private long sNo;
	private String companyAddress;
	
	
	private List<EmployerBO> topCompanyList;
	
	private int jobCount=0;


	public long getId() {
		return this.id;
	}

	
	public void setId(long id) {
		this.id = id;
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
	 * @return the contactNumber
	 */
	public Long getContactNumber() {
		return this.contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the mobileNumber
	 */
	public Long getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	 * @return the companyName
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return this.companyType;
	}

	/**
	 * @param companyType
	 *            the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	/**
	 * @return the industryType
	 */
	public String getIndustryType() {
		return this.industryType;
	}

	/**
	 * @param industryType
	 *            the industryType to set
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	/**
	 * @return the webSite
	 */
	@Column(name = "website")
	public String getWebSite() {
		return this.webSite;
	}

	/**
	 * @param webSite
	 *            the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

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
	 * @return the companyLogo
	 */
	public Blob getCompanyLogo() {
		return this.companyLogo;
	}

	/**
	 * @param companyLogo
	 *            the companyLogo to set
	 * @throws SQLException
	 * @throws SerialException
	 */
	public void setCompanyLogo(byte[] companyLogo) throws SerialException,
			SQLException {
		if (null != companyLogo) {
			this.companyLogo = new SerialBlob(companyLogo);
		} else {
			this.companyLogo = null;
		}
	}

	/**
	 * @return the registeredList
	 */
	public List<EmployerBO> getRegisteredList() {
		return this.registeredList;
	}

	/**
	 * @param registeredList
	 *            the registeredList to set
	 */
	public void setRegisteredList(List<EmployerBO> registeredList) {
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

	public String getSearchElement() {
		return this.searchElement;
	}

	public void setSearchElement(String searchElement) {
		this.searchElement = searchElement;
	}

	public int getTotalDate() {
		return this.totalDate;
	}

	public void setTotalDate(int totalDate) {
		this.totalDate = totalDate;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return this.total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	public Date getSdate() {
		return this.sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return this.edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public int getJobCount() {
		return jobCount;
	}

	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}

	/**
	 * @return the topCompanyList
	 */
	public List<EmployerBO> getTopCompanyList() {
		return topCompanyList;
	}

	/**
	 * @param topCompanyList the topCompanyList to set
	 */
	public void setTopCompanyList(List<EmployerBO> topCompanyList) {
		this.topCompanyList = topCompanyList;
	}

	public String getOtherCompany() {
		return otherCompany;
	}

	public void setOtherCompany(String otherCompany) {
		this.otherCompany = otherCompany;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}


	public String getCompanyAddress() {
		return companyAddress;
	}


	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

}
