package com.myjobkart.bo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author Administrator
 * 
 */
public class SaveCandidateBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1858124723519342841L;

	private EmployerLoginBO employerLoginBO;
	private JobseekerProfileBO jobseekerProfileBO;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	private long sno;
	private String dateandYear;
	private Blob profileImage;
	@NotEmpty
	private String maritalStatus;
	@NotEmpty
	private String gender;
	@NotEmpty
	private String location;
	@NotEmpty
	private String languagesKnown;
	@NotEmpty
	private Long phone;
	@NotEmpty
	private String nationality;
	private String jobType;
	private String currentIndustry;
	private String function;
	@NotEmpty
	private String keySkills;
	private String domainSkills;
	private String specialisation;
	private long currentSalary;
	private String currentSalaryPer;
	@NotEmpty
	private String degree;
	private String college;
	private String expYearandMonth;
	@NotNull
	//@DateTimeFormat(pattern = "MM/dd/yyyy")
	private String yearOfPassing;
	private String grade;
	private String companyName;
	private String companyType;
	private String designation;
	private int experienceInMonth;
	@NotNull
	@Past
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date expStartDate;
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date expEndDate;
	private String resumeTitle;
	private Blob uploadResume;

	private String preferredIndustry;
	private String preferredLocation;
	private long expectedCtc;
	private String expectedCtcPer;
	private String getPrivilege;
	private Boolean termsConditionsAgreed;
	private List<SaveCandidateBO> jobseekerProfileList;
	private String emailId;
	private Boolean isDelete;
	private Date deletedDate;
	private long deleteBy;
	private Boolean isActive;
	private int experienceInYear;
	private String status;
	private String profiledescription;
	private String searchElement;

	private List<SaveCandidateBO> jobProfileList;
	private Blob uploadResumes;
	private Blob profileImages;

	private long profileId;
	private long loginId;
	private long saveId;
	private List<SaveCandidateBO> saveCandidateBOList;
	private Boolean isShortListed;
	private Boolean resumeVisibility = true;
	private long empId;
	
	private String jobTitle;



	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the uploadResumes
	 */
	public Blob getUploadResumes() {
		return this.uploadResumes;
	}

	/**
	 * @param uploadResumes
	 *            the uploadResumes to set
	 */
	public void setUploadResumes(Blob uploadResumes) {
		this.uploadResumes = uploadResumes;
	}

	/**
	 * @return the profileImages
	 */
	public Blob getProfileImages() {
		return this.profileImages;
	}

	/**
	 * @param profileImages
	 *            the profileImages to set
	 */
	public void setProfileImages(Blob profileImages) {
		this.profileImages = profileImages;
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
	 * @return the currentSalary
	 */
	public long getCurrentSalary() {
		return this.currentSalary;
	}

	/**
	 * @param currentSalary
	 *            the currentSalary to set
	 */
	public void setCurrentSalary(long currentSalary) {
		this.currentSalary = currentSalary;
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
	public List<SaveCandidateBO> getJobseekerProfileList() {
		return this.jobseekerProfileList;
	}

	/**
	 * @param jobseekerProfileList
	 *            the jobseekerProfileList to set
	 */
	public void setJobseekerProfileList(
			List<SaveCandidateBO> jobseekerProfileList) {
		this.jobseekerProfileList = jobseekerProfileList;
	}

	/**
	 * @return the jobProfileList
	 */
	public List<SaveCandidateBO> getJobProfileList() {
		return this.jobProfileList;
	}

	/**
	 * @param jobProfileList
	 *            the jobProfileList to set
	 */
	public void setJobProfileList(List<SaveCandidateBO> jobProfileList) {
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
	 * @return the employerLoginBO
	 */
	public EmployerLoginBO getEmployerLoginBO() {
		return this.employerLoginBO;
	}

	/**
	 * @param employerLoginBO
	 *            the employerLoginBO to set
	 */
	public void setEmployerLoginBO(EmployerLoginBO employerLoginBO) {
		this.employerLoginBO = employerLoginBO;
	}

	/**
	 * @return the jobseekerProfileBO
	 */
	public JobseekerProfileBO getJobseekerProfileBO() {
		return this.jobseekerProfileBO;
	}

	/**
	 * @param jobseekerProfileBO
	 *            the jobseekerProfileBO to set
	 */
	public void setJobseekerProfileBO(JobseekerProfileBO jobseekerProfileBO) {
		this.jobseekerProfileBO = jobseekerProfileBO;
	}

	/**
	 * @return the profileId
	 */
	public long getProfileId() {
		return this.profileId;
	}

	/**
	 * @param profileId
	 *            the profileId to set
	 */
	public void setProfileId(long profileId) {
		this.profileId = profileId;
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
	 * @return the saveCandidateBOList
	 */
	public List<SaveCandidateBO> getSaveCandidateBOList() {
		return this.saveCandidateBOList;
	}

	/**
	 * @param saveCandidateBOList
	 *            the saveCandidateBOList to set
	 */
	public void setSaveCandidateBOList(List<SaveCandidateBO> saveCandidateBOList) {
		this.saveCandidateBOList = saveCandidateBOList;
	}

	/**
	 * @return the isShortListed
	 */
	public Boolean getIsShortListed() {
		return this.isShortListed;
	}

	/**
	 * @param isShortListed
	 *            the isShortListed to set
	 */
	public void setIsShortListed(Boolean isShortListed) {
		this.isShortListed = isShortListed;
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
	 * @return the saveId
	 */
	public long getSaveId() {
		return this.saveId;
	}

	/**
	 * @param saveId
	 *            the saveId to set
	 */
	public void setSaveId(long saveId) {
		this.saveId = saveId;
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

	public long getEmpId() {
		return this.empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getDateandYear() {
		return dateandYear;
	}

	public void setDateandYear(String dateandYear) {
		this.dateandYear = dateandYear;
	}

	public long getSno() {
		return sno;
	}

	public void setSno(long sno) {
		this.sno = sno;
	}

	public String getExpYearandMonth() {
		return expYearandMonth;
	}

	public void setExpYearandMonth(String expYearandMonth) {
		this.expYearandMonth = expYearandMonth;
	}

}
