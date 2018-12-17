package com.myjobkart.bo;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

public class ViewJobseekerBO extends BaseBO {

	private long empId;
	private long jId;
	private int days;
	private String name;
	private String empName;
	private String profileName;
	private long id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String confirmEmailAddress;
	private Long contactNumber;
	private Long mobileNumber;
	private String password;
	private String confirmPassword;
	private String companyName;
	private String companyType;
	private String industryType;
	private long jobseekerRegId;
	private long Sno;
	private String viewDate;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getConfirmEmailAddress() {
		return this.confirmEmailAddress;
	}

	public void setConfirmEmailAddress(String confirmEmailAddress) {
		this.confirmEmailAddress = confirmEmailAddress;
	}

	public Long getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Long getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getIndustryType() {
		return this.industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getWebSite() {
		return this.webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getTermsConditionsAgreed() {
		return this.termsConditionsAgreed;
	}

	public void setTermsConditionsAgreed(Boolean termsConditionsAgreed) {
		this.termsConditionsAgreed = termsConditionsAgreed;
	}

	public Blob getCompanyLogo() {
		return this.companyLogo;
	}

	public void setCompanyLogo(Blob companyLogo) {
		this.companyLogo = companyLogo;
	}

	public List<EmployerBO> getRegisteredList() {
		return this.registeredList;
	}

	public void setRegisteredList(List<EmployerBO> registeredList) {
		this.registeredList = registeredList;
	}

	public long getDeletedBy() {
		return this.deletedBy;
	}

	public void setDeletedBy(long deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getTermsConditions() {
		return this.termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

	public String getActive() {
		return this.active;
	}

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

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	private String webSite;

	private Boolean isActive;

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

	private String total;

	public long getEmpId() {
		return this.empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public long getjId() {
		return this.jId;
	}

	public void setjId(long jId) {
		this.jId = jId;
	}

	public int getDays() {
		return this.days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getProfileName() {
		return this.profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getJobseekerRegId() {
		return jobseekerRegId;
	}

	public void setJobseekerRegId(long jobseekerRegId) {
		this.jobseekerRegId = jobseekerRegId;
	}

	public long getSno() {
		return Sno;
	}

	public void setSno(long sno) {
		Sno = sno;
	}

	public String getViewDate() {
		return viewDate;
	}

	public void setViewDate(String viewDate) {
		this.viewDate = viewDate;
	}

}
