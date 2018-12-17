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

import org.hibernate.search.annotations.Indexed;

/**
 * @author Administrator
 * 
 */
@Entity
@Indexed
@Table(name = "saved_jobs")
public class SaveJobVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8522759741181090880L;

	private long applicationid;
	private EmployerLoginVO employerLogin;
	private JobseekerLoginVO jobseekerLogin;
	private EmployerVO employerRegistration;
	private JobPostVO jobpostVO;
	private String address;
	private String companyName;
	private String name;

	private String contactPerson;
	private Long contactNo;
	private String currencyType;
	private long deletedBy;
	private Date deletedDate;
	private String functionArea;
	private String industryType;
	private Boolean isDeleted;
	private String jobDescription;
	private String jobTitle;
	private String keywords;
	private String jobLocation;
	private String maxSalary;
	private String minSalary;
	private String pgQualification;
	private String otherSalaryDetails;
	private String ugQualification;
	private long noVacancies;
	private String maxExp;
	private String minExp;
	private String jobResponse;
	private Boolean isActive;

	private Boolean isApply;

	/**
	 * @return the applicationid
	 */
	@Id
	@GeneratedValue
	@Column(name = "application_id", unique = true, nullable = false)
	public long getApplicationid() {
		return this.applicationid;
	}

	/**
	 * @param applicationid
	 *            the applicationid to set
	 */
	public void setApplicationid(long applicationid) {
		this.applicationid = applicationid;
	}

	/**
	 * @return the employerLogin
	 */
	@ManyToOne
	@JoinColumn(name = "emp_id")
	public EmployerLoginVO getEmployerLogin() {
		return this.employerLogin;
	}

	/**
	 * @param employerLogin
	 *            the employerLogin to set
	 */
	public void setEmployerLogin(EmployerLoginVO employerLogin) {
		this.employerLogin = employerLogin;
	}

	/**
	 * @return the jobseekerLogin
	 */
	@ManyToOne
	@JoinColumn(name = "jb_id")
	public JobseekerLoginVO getJobseekerLogin() {
		return this.jobseekerLogin;
	}

	/**
	 * @param jobseekerLogin
	 *            the jobseekerLogin to set
	 */
	public void setJobseekerLogin(JobseekerLoginVO jobseekerLogin) {
		this.jobseekerLogin = jobseekerLogin;
	}

	/**
	 * @return the jobpostBO
	 */
	@ManyToOne
	@JoinColumn(name = "job_id")
	public JobPostVO getJobpostVO() {
		return this.jobpostVO;
	}

	/**
	 * @param jobpostBO
	 *            the jobpostBO to set
	 */
	public void setJobpostVO(JobPostVO jobpostVO) {
		this.jobpostVO = jobpostVO;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return this.contactPerson;
	}

	/**
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return this.currencyType;
	}

	/**
	 * @param currencyType
	 *            the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
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
	 * @return the functionArea
	 */
	public String getFunctionArea() {
		return this.functionArea;
	}

	/**
	 * @param functionArea
	 *            the functionArea to set
	 */
	public void setFunctionArea(String functionArea) {
		this.functionArea = functionArea;
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
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return this.jobDescription;
	}

	/**
	 * @param jobDescription
	 *            the jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return this.keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the jobLocation
	 */
	public String getJobLocation() {
		return this.jobLocation;
	}

	/**
	 * @param jobLocation
	 *            the jobLocation to set
	 */
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	/**
	 * @return the maxSalary
	 */
	public String getMaxSalary() {
		return this.maxSalary;
	}

	/**
	 * @param maxSalary
	 *            the maxSalary to set
	 */
	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}

	/**
	 * @return the minSalary
	 */
	public String getMinSalary() {
		return this.minSalary;
	}

	/**
	 * @param minSalary
	 *            the minSalary to set
	 */
	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	/**
	 * @return the pgQualification
	 */
	public String getPgQualification() {
		return this.pgQualification;
	}

	/**
	 * @param pgQualification
	 *            the pgQualification to set
	 */
	public void setPgQualification(String pgQualification) {
		this.pgQualification = pgQualification;
	}

	/**
	 * @return the otherSalaryDetails
	 */
	public String getOtherSalaryDetails() {
		return this.otherSalaryDetails;
	}

	/**
	 * @param otherSalaryDetails
	 *            the otherSalaryDetails to set
	 */
	public void setOtherSalaryDetails(String otherSalaryDetails) {
		this.otherSalaryDetails = otherSalaryDetails;
	}

	/**
	 * @return the ugQualification
	 */
	public String getUgQualification() {
		return this.ugQualification;
	}

	/**
	 * @param ugQualification
	 *            the ugQualification to set
	 */
	public void setUgQualification(String ugQualification) {
		this.ugQualification = ugQualification;
	}

	/**
	 * @return the noVacancies
	 */
	public long getNoVacancies() {
		return this.noVacancies;
	}

	/**
	 * @param noVacancies
	 *            the noVacancies to set
	 */
	public void setNoVacancies(long noVacancies) {
		this.noVacancies = noVacancies;
	}

	/**
	 * @return the maxExp
	 */
	public String getMaxExp() {
		return this.maxExp;
	}

	/**
	 * @param maxExp
	 *            the maxExp to set
	 */
	public void setMaxExp(String maxExp) {
		this.maxExp = maxExp;
	}

	/**
	 * @return the minExp
	 */
	public String getMinExp() {
		return this.minExp;
	}

	/**
	 * @param minExp
	 *            the minExp to set
	 */
	public void setMinExp(String minExp) {
		this.minExp = minExp;
	}

	/**
	 * @return the jobResponse
	 */
	public String getJobResponse() {
		return this.jobResponse;
	}

	/**
	 * @param jobResponse
	 *            the jobResponse to set
	 */
	public void setJobResponse(String jobResponse) {
		this.jobResponse = jobResponse;
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
	 * @return the postedBy
	 */
	public String getPostedBy() {
		return this.postedBy;
	}

	/**
	 * @param postedBy
	 *            the postedBy to set
	 */
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	/**
	 * @return the isApply
	 */
	public Boolean getIsApply() {
		return this.isApply;
	}

	/**
	 * @param isApply
	 *            the isApply to set
	 */
	public void setIsApply(Boolean isApply) {
		this.isApply = isApply;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	private String postedBy;

	/**
	 * @return the employerRegistration
	 */
	@ManyToOne
	@JoinColumn(name = "empReg_id")
	public EmployerVO getEmployerRegistration() {
		return employerRegistration;
	}

	/**
	 * @param employerRegistration the employerRegistration to set
	 */
	public void setEmployerRegistration(EmployerVO employerRegistration) {
		this.employerRegistration = employerRegistration;
	}
	
}
