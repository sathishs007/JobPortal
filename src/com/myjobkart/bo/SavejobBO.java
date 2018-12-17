/**
 * 
 */
package com.myjobkart.bo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Administrator
 * 
 */
public class SavejobBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2208311019213571984L;

	private EmployerLoginBO employerLogin;
	private JobSeekerLoginBO jobseekerLogin;
	private EmployerBO employerRegistration;
	private JobseekerBO jobseekerRegistration;
	private JobPostBO jobpostBO;
	private String emailId;
	private int sno;
	private String saveDate;
	
	@NotEmpty
	private String address;
	@NotEmpty
	private String companyName;
  
	@NotEmpty
	private String contactPerson;
	@NotEmpty
	private Long contactNo;
	@NotEmpty
	private String currencyType;

	private long deletedBy;
	private long jobpostId;

	private Date deletedDate;
	@NotEmpty
	private String functionArea;
	@NotEmpty
	private String industryType;

	private Boolean isDeleted;
	@NotEmpty
	@Size(max = 50000)
	private String jobDescription;
	@NotEmpty
	private String jobTitle;
	@NotEmpty
	private String keywords;
	@NotEmpty
	private String jobLocation;
	@NotEmpty
	private String maxSalary;
	@NotEmpty
	private String minSalary;
	@NotEmpty
	private String pgQualification;
	@NotEmpty
	private String otherSalaryDetails;
	@NotEmpty
	private String ugQualification;
	@NotNull
	private long noVacancies;
	@NotEmpty
	private String maxExp;
	@NotEmpty
	private String minExp;
	@NotEmpty
	private String jobResponse;
	private String searchElement;
	private Boolean isActive;
	private byte[] photo;
	private byte[] presentation;
	private String experiance;
	private String salary;
	@NotEmpty
	private String postedBy;
	private long jobId;
	private List<SavejobBO> jobPostList;
	private List<SavejobBO> adminSaveJobList;
	private List<SavejobBO> jobPostListSearch;
	private List<SavejobBO> jobPostSearchList;
	private List<SavejobBO> savejobList;
	private Boolean isApply;
	long totalsavedjob;
	long totalactivesavedjob;
	long totalapplaysavedjob;
	long deactive;
	private String name;
	private long empRegId;

	private Set<SavejobBO> savejobBOSet;

	/**
	 * @return the deactive
	 */
	public long getDeactive() {
		return this.deactive;
	}

	/**
	 * @param deactive
	 *            the deactive to set
	 */
	public void setDeactive(long deactive) {
		this.deactive = deactive;
	}

	/**
	 * @return the totalsavedjob
	 */
	public long getTotalsavedjob() {
		return this.totalsavedjob;
	}

	/**
	 * @param totalsavedjob
	 *            the totalsavedjob to set
	 */
	public void setTotalsavedjob(long totalsavedjob) {
		this.totalsavedjob = totalsavedjob;
	}

	/**
	 * @return the totalactivesavedjob
	 */
	public long getTotalactivesavedjob() {
		return this.totalactivesavedjob;
	}

	/**
	 * @param totalactivesavedjob
	 *            the totalactivesavedjob to set
	 */
	public void setTotalactivesavedjob(long totalactivesavedjob) {
		this.totalactivesavedjob = totalactivesavedjob;
	}

	/**
	 * @return the totalapplaysavedjob
	 */
	public long getTotalapplaysavedjob() {
		return this.totalapplaysavedjob;
	}

	/**
	 * @param totalapplaysavedjob
	 *            the totalapplaysavedjob to set
	 */
	public void setTotalapplaysavedjob(long totalapplaysavedjob) {
		this.totalapplaysavedjob = totalapplaysavedjob;
	}

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
	 * @return the jobseekerLogin
	 */
	public JobSeekerLoginBO getJobseekerLogin() {
		return this.jobseekerLogin;
	}

	/**
	 * @param jobseekerLogin
	 *            the jobseekerLogin to set
	 */
	public void setJobseekerLogin(JobSeekerLoginBO jobseekerLogin) {
		this.jobseekerLogin = jobseekerLogin;
	}

	/**
	 * @return the jobpostBO
	 */
	public JobPostBO getJobpostBO() {
		return this.jobpostBO;
	}

	/**
	 * @param jobpostBO
	 *            the jobpostBO to set
	 */
	public void setJobpostBO(JobPostBO jobpostBO) {
		this.jobpostBO = jobpostBO;
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
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return this.photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the presentation
	 */
	public byte[] getPresentation() {
		return this.presentation;
	}

	/**
	 * @param presentation
	 *            the presentation to set
	 */
	public void setPresentation(byte[] presentation) {
		this.presentation = presentation;
	}

	/**
	 * @return the experiance
	 */
	public String getExperiance() {
		return this.experiance;
	}

	/**
	 * @param experiance
	 *            the experiance to set
	 */
	public void setExperiance(String experiance) {
		this.experiance = experiance;
	}

	/**
	 * @return the salary
	 */
	public String getSalary() {
		return this.salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
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
	 * @return the jobPostList
	 */
	public List<SavejobBO> getJobPostList() {
		return this.jobPostList;
	}

	/**
	 * @param jobPostList
	 *            the jobPostList to set
	 */
	public void setJobPostList(List<SavejobBO> jobPostList) {
		this.jobPostList = jobPostList;
	}

	/**
	 * @return the jobPostListSearch
	 */
	public List<SavejobBO> getJobPostListSearch() {
		return this.jobPostListSearch;
	}

	/**
	 * @param jobPostListSearch
	 *            the jobPostListSearch to set
	 */
	public void setJobPostListSearch(List<SavejobBO> jobPostListSearch) {
		this.jobPostListSearch = jobPostListSearch;
	}

	/**
	 * @return the jobPostSearchList
	 */
	public List<SavejobBO> getJobPostSearchList() {
		return this.jobPostSearchList;
	}

	/**
	 * @param jobPostSearchList
	 *            the jobPostSearchList to set
	 */
	public void setJobPostSearchList(List<SavejobBO> jobPostSearchList) {
		this.jobPostSearchList = jobPostSearchList;
	}

	/**
	 * @return the savejobList
	 */
	public List<SavejobBO> getSavejobList() {
		return this.savejobList;
	}

	/**
	 * @param savejobList
	 *            the savejobList to set
	 */
	public void setSavejobList(List<SavejobBO> savejobList) {
		this.savejobList = savejobList;
	}

	/**
	 * @return the jobId
	 */
	public long getJobId() {
		return this.jobId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(long jobId) {
		this.jobId = jobId;
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
	 * @return the adminSaveJobList
	 */
	public List<SavejobBO> getAdminSaveJobList() {
		return this.adminSaveJobList;
	}

	/**
	 * @param adminSaveJobList
	 *            the adminSaveJobList to set
	 */
	public void setAdminSaveJobList(List<SavejobBO> adminSaveJobList) {
		this.adminSaveJobList = adminSaveJobList;
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

	/**
	 * @return the savejobBOSet
	 */
	public Set<SavejobBO> getSavejobBOSet() {
		return this.savejobBOSet;
	}

	/**
	 * @param savejobBOSet
	 *            the savejobBOSet to set
	 */
	public void setSavejobBOSet(Set<SavejobBO> savejobBOSet) {
		this.savejobBOSet = savejobBOSet;
	}

	public long getJobpostId() {
		return jobpostId;
	}

	public void setJobpostId(long jobpostId) {
		this.jobpostId = jobpostId;
	}

	public long getEmpRegId() {
		return empRegId;
	}

	public void setEmpRegId(long empRegId) {
		this.empRegId = empRegId;
	}

	public EmployerBO getEmployerRegistration() {
		return employerRegistration;
	}

	public void setEmployerRegistration(EmployerBO employerRegistration) {
		this.employerRegistration = employerRegistration;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String EmailId) {
		this.emailId = EmailId;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(String saveDate) {
		this.saveDate = saveDate;
	}

	public JobseekerBO getJobseekerRegistration() {
		return jobseekerRegistration;
	}

	public void setJobseekerRegistration(JobseekerBO jobseekerRegistration) {
		this.jobseekerRegistration = jobseekerRegistration;
	}

}
