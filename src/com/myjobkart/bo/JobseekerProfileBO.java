package com.myjobkart.bo;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.myjobkart.vo.JobseekerEducationVO;

public class JobseekerProfileBO extends BaseBO {

	private static final long serialVersionUID = -1858124723519342841L;

	private JobSeekerLoginBO jobSeekerLogin;

	@Size(min = 0, max = 20)
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First name Should be a Character")
	private String firstName;
	@Size(min = 0, max = 20)
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Last name Should be a Character")
	private String lastName;
	private String startDate;
	private String endDate;
	@Pattern(regexp = "^[0-9\\s]*$", message = "Salary Should be a Number")
	private String lastSalary;
	private long profesId;
	private boolean resumevisibile;
	private InputStream profilePicture;
	private boolean expStatus;
	private long companyId;
	private String addCompany;
	private Long noOfExperience;
	private String yearOfPassing;
	private String createdDate;
	private long SNo;
	private long jobseekerRegId;
	private long titleCount;

	/**
	 * @return the resumevisibile
	 */
	public boolean isResumevisibile() {
		return resumevisibile;
	}

	/**
	 * @param resumevisibile the resumevisibile to set
	 */
	public void setResumevisibile(boolean resumevisibile) {
		this.resumevisibile = resumevisibile;
	}

	private long jobTittleCount;
	private List<JobseekerProfileBO> jobTittleCountList;
	
	private List<JobseekerProfileBO> resumeList;
	
	private List<JobseekerProfileBO> titleList;
	

	/**
	 * @return the jobTittleCountList
	 */
	public List<JobseekerProfileBO> getJobTittleCountList() {
		return jobTittleCountList;
	}

	/**
	 * @param jobTittleCountList
	 *            the jobTittleCountList to set
	 */
	public void setJobTittleCountList(
			List<JobseekerProfileBO> jobTittleCountList) {
		this.jobTittleCountList = jobTittleCountList;
	}

	/**
	 * @return the jobTittleCount
	 */
	public long getJobTittleCount() {
		return jobTittleCount;
	}

	/**
	 * @param jobTittleCount
	 *            the jobTittleCount to set
	 */
	public void setJobTittleCount(long jobTittleCount) {
		this.jobTittleCount = jobTittleCount;
	}

	/**
	 * @return the lastSalary
	 */
	public String getLastSalary() {
		return lastSalary;
	}

	/**
	 * @param lastSalary
	 *            the lastSalary to set
	 */
	public void setLastSalary(String lastSalary) {
		this.lastSalary = lastSalary;
	}

	private Blob profileImage;
	private String maritalStatus;

	private String gender;
	@Size(min = 0, max = 20)
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Location Should be a Character")
	private String location;

	/*
	 * @Size(min = 0, max = 100)
	 * 
	 * @Pattern(regexp = "^[a-zA-Z,.\\s]*$", message =
	 * "Language should be a Character")
	 */
	private String languagesKnown;

	@Range(min = 1111111111, message = "Phone Number Should be a 10 digits")
	private Long phone;
	@Size(min = 0, max = 20)
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Nationality Should be a Character")
	private String nationality;
	private String jobType;
	private String currentIndustry;
	@Size(min = 0, max = 500)
	@Pattern(regexp = "^[a-zA-Z,.\\s]*$", message = "Function should be a Character")
	private String function;
	// @Pattern(regexp =
	// "^[a-zA-Z,0-9,.\\-\\:\\(\\)\\s]*$",message="Accept special character ,.")

	private String address;

	/**
	 * @return the pincode
	 */
	public Long getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	@Pattern(regexp = "^[a-zA-Z,0-9,.\\s]*$", message = "Enter Percentage is Numeric or Grade is character")
	private String percentage;

	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage
	 *            the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	/*
	 * @Size(min = 6, max = 6 ,message="Pincode Should be a 6 digits")
	 * 
	 * @Pattern(regexp = "^[0-9]*$", message = "Pincode should be a number")
	 */
	@Range(min = 111111, message = "Pincode Should be a 6 digits")
	private Long pincode;
	private String state;
	private String keySkills;
	private String domainSkills;
	@Size(min = 0, max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9,.\\s]*$", message = "Specialisation should be a Character and Number")
	private String specialisation;
	private Long currentSalary;
	private String currentSalaryPer;
	private String degree;
	@Size(min = 0, max = 50)
	@Pattern(regexp = "^[a-zA-Z,.&@'-()\\s]*$", message = "College name Should be a Character and accept ,.&@'-()")
	private String college;
	@Size(min = 0, max = 100)
	@Pattern(regexp = "^[a-zA-Z,.'\\s]*$", message = "Department Should be a Character")
	private String department;
	
	private String profileDescriptions;

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	
	private String grade;
	// @Pattern(regexp = "^[a-zA-Z,0-9,.@&*#$\\s]*$", message =
	// "Company Name should be a Character and accept ,.@&*#$")
	private String companyName;
	// @Pattern(regexp = "^[a-zA-Z,.\\s]*$", message =
	// "Company Type should be a Character")
	private String companyType;
	@Size(min = 0, max = 100)
	private String designation;
	private int experienceInMonth;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date expStartDate;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date expEndDate;

	// @Pattern(regexp = "^[a-zA-Z,.'\\s]*$", message =
	// "Description Should be a Character")
	private String resumeTitle;
	private Blob uploadResume;
	// @Pattern(regexp = "^[a-zA-Z,.\\s]*$", message =
	// "Preferred Industry should be a Character")
	// @Size(min = 0, max = 20)
	private String preferredIndustry;
	private String preferredLocation;
	private Long expectedCtc;
	private String expectedCtcPer;
	@Size(min = 0, max = 100)
	@Pattern(regexp = "^[a-zA-Z,.'\\s]*$", message = "Privilege should be a Character")
	private String getPrivilege;
	private Boolean termsConditionsAgreed;
	private List<JobseekerProfileBO> jobseekerProfileList;
	private String emailId;
	private Boolean isDelete;
	private Date deletedDate;
	private long deleteBy;
	private Boolean isActive;
	private int experienceInYear;
	private String status;
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Job Title should be a Character")
	@Size(min = 0, max = 3000)
	private String profiledescription;
	private String searchElement;
	private String searchSkills;
	private String searchLocation;

	private Boolean isVisiable = true;

	private Boolean resumeVisibility = true;

	private List<JobseekerProfileBO> jobProfileList;
	private List<JobseekerProfileBO> jobEductionProfileList;
	private List<JobseekerProfileBO> jobprofessionalList;
	
	private List<JobseekerProfileBO> jobseekerResumeList;
	
	private long educationId;

	/**
	 * @return the educationId
	 */
	public long getEducationId() {
		return educationId;
	}

	/**
	 * @param educationId
	 *            the educationId to set
	 */
	public void setEducationId(long educationId) {
		this.educationId = educationId;
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
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return this.gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the languagesKnown
	 */
	public String getLanguagesKnown() {
		return this.languagesKnown;
	}

	/**
	 * @param languagesKnown
	 *            the languagesKnown to set
	 */
	public void setLanguagesKnown(String languagesKnown) {
		this.languagesKnown = languagesKnown;
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
	 * @return the nationality
	 */
	public String getNationality() {
		return this.nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return this.jobType;
	}

	/**
	 * @param jobType
	 *            the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
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
	 * @return the function
	 */
	public String getFunction() {
		return this.function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
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
	 * @return the domainSkills
	 */
	public String getDomainSkills() {
		return this.domainSkills;
	}

	/**
	 * @param domainSkills
	 *            the domainSkills to set
	 */
	public void setDomainSkills(String domainSkills) {
		this.domainSkills = domainSkills;
	}

	/**
	 * @return the specialisation
	 */
	public String getSpecialisation() {
		return this.specialisation;
	}

	/**
	 * @param specialisation
	 *            the specialisation to set
	 */
	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
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
	 * @return the college
	 */
	public String getCollege() {
		return this.college;
	}

	/**
	 * @param college
	 *            the college to set
	 */
	public void setCollege(String college) {
		this.college = college;
	}

	/**
	 * @return the yearOfPassing
	 */
	public String getYearOfPassing() {
		return this.yearOfPassing;
	}

	/**
	 * @param yearOfPassing
	 *            the yearOfPassing to set
	 */
	public void setYearOfPassing(String yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return this.grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
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
	 * @return the designation
	 */
	public String getDesignation() {
		return this.designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
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
	 * @return the expStartDate
	 */
	public Date getExpStartDate() {
		return this.expStartDate;
	}

	/**
	 * @param expStartDate
	 *            the expStartDate to set
	 */
	public void setExpStartDate(Date expStartDate) {
		this.expStartDate = expStartDate;
	}

	/**
	 * 
	 * @return the expEndDate
	 */
	public Date getExpEndDate() {
		return this.expEndDate;
	}

	/**
	 * @param expEndDate
	 *            the expEndDate to set
	 */
	public void setExpEndDate(Date expEndDate) {
		this.expEndDate = expEndDate;
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
	 * @throws SQLException
	 * @throws SerialException
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
	 * @return the getPrivilege
	 */
	public String getGetPrivilege() {
		return this.getPrivilege;
	}

	/**
	 * @param getPrivilege
	 *            the getPrivilege to set
	 */
	public void setGetPrivilege(String getPrivilege) {
		this.getPrivilege = getPrivilege;
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
	 * @return the jobSeekerLogin
	 */
	public JobSeekerLoginBO getJobSeekerLogin() {
		return this.jobSeekerLogin;
	}

	/**
	 * @param jobSeekerLogin
	 *            the jobSeekerLogin to set
	 */
	public void setJobSeekerLogin(JobSeekerLoginBO jobSeekerLogin) {
		this.jobSeekerLogin = jobSeekerLogin;
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
	 * @return the jobseekerProfileList
	 */
	public List<JobseekerProfileBO> getJobseekerProfileList() {
		return this.jobseekerProfileList;
	}

	/**
	 * @param jobseekerProfileList
	 *            the jobseekerProfileList to set
	 */
	public void setJobseekerProfileList(
			List<JobseekerProfileBO> jobseekerProfileList) {
		this.jobseekerProfileList = jobseekerProfileList;
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
	 * @return the jobProfileList
	 */
	public List<JobseekerProfileBO> getJobProfileList() {
		return this.jobProfileList;
	}

	/**
	 * @param jobProfileList
	 *            the jobProfileList to set
	 */
	public void setJobProfileList(List<JobseekerProfileBO> jobProfileList) {
		this.jobProfileList = jobProfileList;
	}

	/**
	 * @return the currentSalaryPer
	 */
	public String getCurrentSalaryPer() {
		return this.currentSalaryPer;
	}

	/**
	 * @param currentSalaryPer
	 *            the currentSalaryPer to set
	 */
	public void setCurrentSalaryPer(String currentSalaryPer) {
		this.currentSalaryPer = currentSalaryPer;
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
	 * @return the isVisiable
	 */
	public Boolean getIsVisiable() {
		return this.isVisiable;
	}

	/**
	 * @param isVisiable
	 *            the isVisiable to set
	 */
	public void setIsVisiable(Boolean isVisiable) {
		this.isVisiable = isVisiable;
	}

	/**
	 * @return the searchLocation
	 */
	public String getSearchLocation() {
		return searchLocation;
	}

	/**
	 * @param searchLocation
	 *            the searchLocation to set
	 */
	public void setSearchLocation(String searchLocation) {
		this.searchLocation = searchLocation;
	}

	/**
	 * @return the jobEductionProfileList
	 */
	public List<JobseekerProfileBO> getJobEductionProfileList() {
		return jobEductionProfileList;
	}

	/**
	 * @param jobEductionProfileList
	 *            the jobEductionProfileList to set
	 */
	public void setJobEductionProfileList(
			List<JobseekerProfileBO> jobEductionProfileList) {
		this.jobEductionProfileList = jobEductionProfileList;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getProfesId() {
		return profesId;
	}

	public void setProfesId(long profesId) {
		this.profesId = profesId;
	}

	public List<JobseekerProfileBO> getJobprofessionalList() {
		return jobprofessionalList;
	}

	public void setJobprofessionalList(
			List<JobseekerProfileBO> jobprofessionalList) {
		this.jobprofessionalList = jobprofessionalList;
	}


	

	public String getSearchSkills() {
		return searchSkills;
	}

	public void setSearchSkills(String searchSkills) {
		this.searchSkills = searchSkills;
	}

	/**
	 * 
	 * @return
	 */

	public Boolean getResumeVisibility() {
		return this.resumeVisibility;
	}

	public void setResumeVisibility(Boolean resumeVisibility) {
		this.resumeVisibility = resumeVisibility;
	}

	/**
	 * @return the currentSalary
	 */
	public Long getCurrentSalary() {
		return currentSalary;
	}

	/**
	 * @param currentSalary the currentSalary to set
	 */
	public void setCurrentSalary(Long currentSalary) {
		this.currentSalary = currentSalary;
	}

	/**
	 * @return the expectedCtc
	 */
	public Long getExpectedCtc() {
		return expectedCtc;
	}

	/**
	 * @param expectedCtc the expectedCtc to set
	 */
	public void setExpectedCtc(Long expectedCtc) {
		this.expectedCtc = expectedCtc;
	}

	/**
	 * @return the jobseekerResumeList
	 */
	public List<JobseekerProfileBO> getJobseekerResumeList() {
		return jobseekerResumeList;
	}

	/**
	 * @param jobseekerResumeList the jobseekerResumeList to set
	 */
	public void setJobseekerResumeList(List<JobseekerProfileBO> jobseekerResumeList) {
		this.jobseekerResumeList = jobseekerResumeList;
	}

	/**
	 * @return the profileDescriptions
	 */
	public String getProfileDescriptions() {
		return profileDescriptions;
	}

	/**
	 * @param profileDescriptions the profileDescriptions to set
	 */
	public void setProfileDescriptions(String profileDescriptions) {
		this.profileDescriptions = profileDescriptions;
	}

	
	
	

	/**
		 * @return the profilePicture
		 */
		public InputStream getProfilePicture() {
			return profilePicture;
		}

		/**
		 * @param profilePicture the profilePicture to set
		 */
		public void setProfilePicture(InputStream profilePicture) {
			this.profilePicture = profilePicture;
		}

		public boolean getExpStatus() {
			return expStatus;
		}

		public void setExpStatus(boolean expStatus) {
			this.expStatus = expStatus;
		}

		public long getCompanyId() {
			return companyId;
		}

		public void setCompanyId(long companyId) {
			this.companyId = companyId;
		}

		public String getAddCompany() {
			return addCompany;
		}

		public void setAddCompany(String addCompany) {
			this.addCompany = addCompany;
		}

		public Long getNoOfExperience() {
			return noOfExperience;
		}

		public void setNoOfExperience(Long noOfExperience) {
			this.noOfExperience = noOfExperience;
		}

		public String getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}

		public long getSNo() {
			return SNo;
		}

		public void setSNo(long sNo) {
			SNo = sNo;
		}

		public long getJobseekerRegId() {
			return jobseekerRegId;
		}

		public void setJobseekerRegId(long jobseekerRegId) {
			this.jobseekerRegId = jobseekerRegId;
		}

		/**
		 * @return the titleCount
		 */
		public long getTitleCount() {
			return titleCount;
		}

		/**
		 * @param titleCount the titleCount to set
		 */
		public void setTitleCount(long titleCount) {
			this.titleCount = titleCount;
		}

		/**
		 * @return the titleList
		 */
		public List<JobseekerProfileBO> getTitleList() {
			return titleList;
		}

		/**
		 * @param titleList the titleList to set
		 */
		public void setTitleList(List<JobseekerProfileBO> titleList) {
			this.titleList = titleList;
		}

		/**
		 * @return the resumeList
		 */
		public List<JobseekerProfileBO> getResumeList() {
			return resumeList;
		}

		/**
		 * @param resumeList the resumeList to set
		 */
		public void setResumeList(List<JobseekerProfileBO> resumeList) {
			this.resumeList = resumeList;
		}

		
	
}
