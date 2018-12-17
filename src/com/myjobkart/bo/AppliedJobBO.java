package com.myjobkart.bo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Administrator
 * 
 */
public class AppliedJobBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2208311019213571984L;

	private EmployerLoginBO employerLogin;
	private JobSeekerLoginBO jobseekerLogin;
	private JobseekerBO jobseekerRegistration;
	private EmployerBO employerRegistration;
	private JobPostBO jobpostBO;
	private long jobId;
	private String JobseekerEmail;
	private String EmployeeEmail;
	private long Sno;
	private String appliedDate;
	

	private String dateandYear;
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
	private String name;
	private long loginId;
	private List<AppliedJobBO> jobPostList;
	private List<AppliedJobBO> adminAppliedJobList;
	private List<AppliedJobBO> jobPostListSearch;
	private List<AppliedJobBO> jobPostSearchList;
	private List<AppliedJobBO> appliedJobList;
	private String firstName;
	private String profiledescription;
	private int experienceInYear;
	private String preferredLocation;
	private long expectedCtc;
	private String resumeTitle;
	private Blob uploadResume;
	private String keySkills;
	private String degree;
	private String currentIndustry;
	private Long phone;
	private String emailId;
	private int experienceInMonth;
	private String preferredIndustry;
	private String expectedCtcPer;
	private Blob profileImage;

	private Boolean isShortlisted;
	private Boolean resumevisibility;
	private Boolean resumevisibile = true;
	private long empId;

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
	 * @return the profiledescription
	 */
	public String getProfiledescription() {
		return this.profiledescription;
	}

	/**
	 * @param profiledescription
	 *            the profiledescription to set
	 */
	public void setProfiledescription(String profiledescription) {
		this.profiledescription = profiledescription;
	}

	/**
	 * @return the experienceInYear
	 */
	public int getExperienceInYear() {
		return this.experienceInYear;
	}

	/**
	 * @param experienceInYear
	 *            the experienceInYear to set
	 */
	public void setExperienceInYear(int experienceInYear) {
		this.experienceInYear = experienceInYear;
	}

	/**
	 * @return the preferredLocation
	 */
	public String getPreferredLocation() {
		return this.preferredLocation;
	}

	/**
	 * @param preferredLocation
	 *            the preferredLocation to set
	 */
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	/**
	 * @return the expectedCtc
	 */
	public long getExpectedCtc() {
		return this.expectedCtc;
	}

	/**
	 * @param expectedCtc
	 *            the expectedCtc to set
	 */
	public void setExpectedCtc(long expectedCtc) {
		this.expectedCtc = expectedCtc;
	}

	/**
	 * @return the resumeTitle
	 */
	public String getResumeTitle() {
		return this.resumeTitle;
	}

	/**
	 * @param resumeTitle
	 *            the resumeTitle to set
	 */
	public void setResumeTitle(String resumeTitle) {
		this.resumeTitle = resumeTitle;
	}

	/**
	 * @return the uploadResume
	 */
	public Blob getUploadResume() {
		return this.uploadResume;
	}

	/**
	 * @param uploadResume
	 *            the uploadResume to set
	 */
	public void setUploadResume(byte[] uploadResume) throws SerialException,
			SQLException {
		if (null != uploadResume) {
			this.uploadResume = new SerialBlob(uploadResume);
		} else {
			this.uploadResume = null;
		}
	}

	/**
	 * @return the keySkills
	 */
	public String getKeySkills() {
		return this.keySkills;
	}

	/**
	 * @param keySkills
	 *            the keySkills to set
	 */
	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return this.degree;
	}

	/**
	 * @param degree
	 *            the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the currentIndustry
	 */
	public String getCurrentIndustry() {
		return this.currentIndustry;
	}

	/**
	 * @param currentIndustry
	 *            the currentIndustry to set
	 */
	public void setCurrentIndustry(String currentIndustry) {
		this.currentIndustry = currentIndustry;
	}

	/**
	 * @return the phone
	 */
	public Long getPhone() {
		return this.phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(Long phone) {
		this.phone = phone;
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
	 * @return the experienceInMonth
	 */
	public int getExperienceInMonth() {
		return this.experienceInMonth;
	}

	/**
	 * @param experienceInMonth
	 *            the experienceInMonth to set
	 */
	public void setExperienceInMonth(int experienceInMonth) {
		this.experienceInMonth = experienceInMonth;
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
	public List<AppliedJobBO> getJobPostList() {
		return this.jobPostList;
	}

	/**
	 * @param jobPostList
	 *            the jobPostList to set
	 */
	public void setJobPostList(List<AppliedJobBO> jobPostList) {
		this.jobPostList = jobPostList;
	}

	/**
	 * @return the jobPostListSearch
	 */
	public List<AppliedJobBO> getJobPostListSearch() {
		return this.jobPostListSearch;
	}

	/**
	 * @param jobPostListSearch
	 *            the jobPostListSearch to set
	 */
	public void setJobPostListSearch(List<AppliedJobBO> jobPostListSearch) {
		this.jobPostListSearch = jobPostListSearch;
	}

	/**
	 * @return the jobPostSearchList
	 */
	public List<AppliedJobBO> getJobPostSearchList() {
		return this.jobPostSearchList;
	}

	/**
	 * @param jobPostSearchList
	 *            the jobPostSearchList to set
	 */
	public void setJobPostSearchList(List<AppliedJobBO> jobPostSearchList) {
		this.jobPostSearchList = jobPostSearchList;
	}

	/**
	 * @return the preferredIndustry
	 */
	public String getPreferredIndustry() {
		return this.preferredIndustry;
	}

	/**
	 * @param preferredIndustry
	 *            the preferredIndustry to set
	 */
	public void setPreferredIndustry(String preferredIndustry) {
		this.preferredIndustry = preferredIndustry;
	}

	/**
	 * @return the expectedCtcPer
	 */
	public String getExpectedCtcPer() {
		return this.expectedCtcPer;
	}

	/**
	 * @param expectedCtcPer
	 *            the expectedCtcPer to set
	 */
	public void setExpectedCtcPer(String expectedCtcPer) {
		this.expectedCtcPer = expectedCtcPer;
	}

	/**
	 * @return the loginId
	 */
	public long getLoginId() {
		return this.loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the appliedJobList
	 */
	public List<AppliedJobBO> getAppliedJobList() {
		return this.appliedJobList;
	}

	/**
	 * @param appliedJobList
	 *            the appliedJobList to set
	 */
	public void setAppliedJobList(List<AppliedJobBO> appliedJobList) {
		this.appliedJobList = appliedJobList;
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
	 * 
	 * @param profileImage
	 * @throws SerialException
	 * @throws SQLException
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
	 * @return the profileImage
	 */
	public Blob getProfileImage() {
		return this.profileImage;
	}

	/**
	 * @return the isShortlisted
	 */
	public Boolean getIsShortlisted() {
		return this.isShortlisted;
	}

	/**
	 * @param isShortlisted
	 *            the isShortlisted to set
	 */
	public void setIsShortlisted(Boolean isShortlisted) {
		this.isShortlisted = isShortlisted;
	}

	/**
	 * @return the resumevisibility
	 */
	public Boolean getResumevisibility() {
		return this.resumevisibility;
	}

	/**
	 * @param resumevisibility
	 *            the resumevisibility to set
	 */
	public void setResumevisibility(Boolean resumevisibility) {
		this.resumevisibility = resumevisibility;
	}

	/**
	 * @return the resumevisibile
	 */
	public Boolean getResumevisibile() {
		return this.resumevisibile;
	}

	/**
	 * @param resumevisibile
	 *            the resumevisibile to set
	 */
	public void setResumevisibile(Boolean resumevisibile) {
		this.resumevisibile = resumevisibile;
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
	 * @return the adminAppliedJobList
	 */
	public List<AppliedJobBO> getAdminAppliedJobList() {
		return this.adminAppliedJobList;
	}

	/**
	 * @param adminAppliedJobList
	 *            the adminAppliedJobList to set
	 */
	public void setAdminAppliedJobList(List<AppliedJobBO> adminAppliedJobList) {
		this.adminAppliedJobList = adminAppliedJobList;
	}

	/**
	 * @return the jobseekerEmail
	 */
	public String getJobseekerEmail() {
		return JobseekerEmail;
	}

	/**
	 * @param jobseekerEmail the jobseekerEmail to set
	 */
	public void setJobseekerEmail(String jobseekerEmail) {
		JobseekerEmail = jobseekerEmail;
	}

	/**
	 * @return the employeeEmail
	 */
	public String getEmployeeEmail() {
		return EmployeeEmail;
	}

	/**
	 * @param employeeEmail the employeeEmail to set
	 */
	public void setEmployeeEmail(String employeeEmail) {
		EmployeeEmail = employeeEmail;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public EmployerBO getEmployerRegistration() {
		return employerRegistration;
	}

	public void setEmployerRegistration(EmployerBO employerRegistration) {
		this.employerRegistration = employerRegistration;
	}

	public long getSno() {
		return Sno;
	}

	public void setSno(long sno) {
		Sno = sno;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getDateandYear() {
		return dateandYear;
	}

	public void setDateandYear(String dateandYear) {
		this.dateandYear = dateandYear;
	}

	public JobseekerBO getJobseekerRegistration() {
		return jobseekerRegistration;
	}

	public void setJobseekerRegistration(JobseekerBO jobseekerRegistration) {
		this.jobseekerRegistration = jobseekerRegistration;
	}

}
