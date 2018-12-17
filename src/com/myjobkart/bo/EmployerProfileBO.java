package com.myjobkart.bo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * 
 * @author Administrator
 * 
 */
public class EmployerProfileBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8297084534459011594L;

	private EmployerLoginBO employerLogin;
	private EmployerBO employerRegistion;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First Name Should be a Character")
	private String firstName;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Last Name Should be a Character")
	private String lastName;
	
	
	private String emailId;
	@NotNull
	@Range(min = 1111111111,message = "Phone Number must be a 10 Digits")
	private Long contactNo;
	@NotNull
	@Range(min = 1111111111,message = "Mobile Number must be a 10 Digits")
	private Long mobileNo;
	
	private String companyName;
	
	private String otherCompany;
	
	@NotEmpty
	private String companyType;
	@NotEmpty
	private String industryType;
	@NotEmpty
	@Pattern(regexp = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]", message = "Invalid Website Format")
	private String companyWebsite;
	@NotEmpty
	private String addressLine1;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Country Name Should be a Character")
	private String country;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s\\&]*$", message = "State Name Should be a Character")
	private String state;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "City Name Should be a Character")
	private String city;
	
	/*@NotEmpty
	@Pattern(regexp="^[0-9]*$",message= "Pincode should be a number")
	//@Range(min=66666, message="Pincode Should be a 6 digits ")
	 * 
	 * 
	 */
	@NotNull
	@Range(min = 111111,message = "Pincode Code must be a 6 Digits")
	private Long pinCode;
	
	private String currentDesignation;
	@NotEmpty
	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid Email Format")
	private String secondaryEmail;
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Responsibilities Should be Character")
	private String currentRolesResponsibilities;

	private Blob uploadPhoto;
	private Blob companyLogo;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Hiring Should be a Character")
	private String hiringPurpose;

	private Boolean termsConditionsAgreed;
	@NotEmpty(message="Should be select")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name Should be a Character")
	private String productEnrolled;
	@Size(min = 0, max = 50000)
	private String companyProfile;

	private Boolean isDelete;

	private Date deletedDate;

	private long deleteBy;

	private Boolean isActive;

	private String status;

	private Boolean resumeVisibility = true;

	private String searchElement;

	private List<EmployerProfileBO> employerProfileList;
	
	private long empRegId;
	
	private String password;

	private String confirmPassword;
	
	private long sNo;
	private String confirmEmail;
	private long companyId;
	
	

	/**
	 * @return the employerLogin
	 */
	public EmployerLoginBO getEmployerLogin() {
		return this.employerLogin;
	}

	/**
	 * @param employerLogin
	 *            the employerLogin to set
	 */
	public void setEmployerLogin(EmployerLoginBO employerLogin) {
		this.employerLogin = employerLogin;
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
	 * @return the emailId
	 */
	public String getEmailId() {
		return this.emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the contactNo
	 */
	public Long getContactNo() {
		return this.contactNo;
	}

	/**
	 * @param contactNo
	 *            the contactNo to set
	 */
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
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
	 * @return the companyWebsite
	 */
	public String getCompanyWebsite() {
		return this.companyWebsite;
	}

	/**
	 * @param companyWebsite
	 *            the companyWebsite to set
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return this.addressLine1;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	


	

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the pinCode
	 */
	public Long getPinCode() {
		return this.pinCode;
	}

	/**
	 * @param pinCode
	 *            the pinCode to set
	 */
	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	/**
	 * @return the currentDesignation
	 */
	public String getCurrentDesignation() {
		return this.currentDesignation;
	}

	/**
	 * @param currentDesignation
	 *            the currentDesignation to set
	 */
	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	/**
	 * @return the secondaryEmail
	 */
	public String getSecondaryEmail() {
		return this.secondaryEmail;
	}

	/**
	 * @param secondaryEmail
	 *            the secondaryEmail to set
	 */
	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	/**
	 * @return the currentRolesResponsibilities
	 */
	public String getCurrentRolesResponsibilities() {
		return this.currentRolesResponsibilities;
	}

	/**
	 * @param currentRolesResponsibilities
	 *            the currentRolesResponsibilities to set
	 */
	public void setCurrentRolesResponsibilities(
			String currentRolesResponsibilities) {
		this.currentRolesResponsibilities = currentRolesResponsibilities;
	}

	/**
	 * @return the uploadPhoto
	 */
	public Blob getUploadPhoto() {
		return this.uploadPhoto;
	}

	/**
	 * @param profileImage
	 *            the profileImage to set
	 * @throws SQLException
	 * @throws SerialException
	 */
	public void setUploadPhoto(byte[] uploadPhoto) throws SerialException,
			SQLException {
		if (null != uploadPhoto) {
			this.uploadPhoto = new SerialBlob(uploadPhoto);
		} else {
			this.uploadPhoto = null;
		}

	}

	/**
	 * @return the hiringPurpose
	 */
	public String getHiringPurpose() {
		return this.hiringPurpose;
	}

	/**
	 * @param hiringPurpose
	 *            the hiringPurpose to set
	 */
	public void setHiringPurpose(String hiringPurpose) {
		this.hiringPurpose = hiringPurpose;
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
	 * @return the productEnrolled
	 */
	public String getProductEnrolled() {
		return this.productEnrolled;
	}

	/**
	 * @param productEnrolled
	 *            the productEnrolled to set
	 */
	public void setProductEnrolled(String productEnrolled) {
		this.productEnrolled = productEnrolled;
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
	 * @return the companyProfile
	 */
	public String getCompanyProfile() {
		return this.companyProfile;
	}

	/**
	 * @param companyProfile
	 *            the companyProfile to set
	 */
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	/**
	 * @return the employerProfileList
	 */
	public List<EmployerProfileBO> getEmployerProfileList() {
		return this.employerProfileList;
	}

	/**
	 * @param employerProfileList
	 *            the employerProfileList to set
	 */
	public void setEmployerProfileList(
			List<EmployerProfileBO> employerProfileList) {
		this.employerProfileList = employerProfileList;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the resumeVisibility
	 */
	public Boolean getResumeVisibility() {
		return this.resumeVisibility;
	}

	/**
	 * @param resumeVisibility
	 *            the resumeVisibility to set
	 */
	public void setResumeVisibility(Boolean resumeVisibility) {
		this.resumeVisibility = resumeVisibility;
	}

	public String getSearchElement() {
		return this.searchElement;
	}

	public void setSearchElement(String searchElement) {
		this.searchElement = searchElement;
	}

	public long getEmpRegId() {
		return empRegId;
	}

	public void setEmpRegId(long empRegId) {
		this.empRegId = empRegId;
	}

	public String getOtherCompany() {
		return otherCompany;
	}

	public void setOtherCompany(String otherCompany) {
		this.otherCompany = otherCompany;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public EmployerBO getEmployerRegistion() {
		return employerRegistion;
	}

	public void setEmployerRegistion(EmployerBO employerRegistion) {
		this.employerRegistion = employerRegistion;
	}

	public Blob getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(byte[] companyLogo)throws SerialException,
	SQLException {
		if(null != companyLogo){
			this.companyLogo = new SerialBlob(companyLogo);
		}else{
		this.companyLogo = null;
		}
	}

}
