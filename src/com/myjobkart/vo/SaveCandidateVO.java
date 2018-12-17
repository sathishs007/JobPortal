package com.myjobkart.vo;

// Generated 5 Feb, 2015 10:16:28 AM by Hibernate Tools 4.0.0

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

/**
 * JobSeekerProfile generated by hbm2java
 */
@Entity
@Indexed
@Table(name = "save_candidate")
public class SaveCandidateVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long savecandidateid;
	private EmployerLoginVO employerLoginVO;
	private String firstName;
	private String lastName;
	private Blob profileImage;
	private String maritalStatus;
	private String gender;
	private String location;
	private String languagesKnown;
	private Long phone;
	private String nationality;
	private String jobType;
	private String currentIndustry;
	private String function;
	private String keySkills;
	private String domainSkills;
	private String specialisation;
	private long currentSalary;
	private String degree;
	private String college;
	private String yearOfPassing;
	private String grade;
	private String companyName;
	private String companyType;
	private String designation;
	private int experienceInMonth;
	private Date expStartDate;
	private Date expEndDate;
	private String resumeTitle;
	private Blob uploadResume;
	private String preferredIndustry;
	private String preferredLocation;
	private long expectedCtc;
	private String getPrivilege;
	private Boolean termsConditionsAgreed;

	private String emailId;

	private Boolean isDelete;

	private Date deletedDate;

	private long deleteBy;
	private Boolean isActive;
	private String currentSalaryPer;
	private String expectedCtcPer;
	private int experienceInYear;
	private String profiledescription;
	private JobseekerProfileVO jobseekerProfileVO;
	private Boolean isShortListed;

	/**
	 * @return the profileId
	 */

	/**
	 * @return the jobSeekerLogin
	 */

	/**
	 * @return the firstName
	 */
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}

	/**
	 * @return the maritalStatus
	 */
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	 * @return the languagesKYESwn
	 */
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	//@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	//@DateBridge(resolution = Resolution.DAY)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@DateBridge(resolution = Resolution.DAY)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@DateBridge(resolution = Resolution.DAY)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column(name = "profileTitle")
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
	public void setUploadResume(Blob uploadResume) {
		this.uploadResume = uploadResume;
	}

	/**
	 * @return the preferredIndustry
	 */
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(impl = WhitespaceAnalyzer.class)
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

	/**
	 * @return the isActive
	 */
	/*
	 * @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO) public
	 * Boolean getIsActive() { return isActive; }
	 *//**
	 * @param isActive
	 *            the isActive to set
	 */
	/*
	 * 
	 * public void setIsActive(Boolean isActive) { isActive = isActive; }
	 */

	/**
	 * @return the currentSalaryPer
	 */
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	 * @return the profiledescription
	 */
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	 * @return the isActive
	 */
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	 * @return the employerLoginVO
	 */
	@ManyToOne
	@JoinColumn(name = "emp_id")
	public EmployerLoginVO getEmployerLoginVO() {
		return this.employerLoginVO;
	}

	/**
	 * @param employerLoginVO
	 *            the employerLoginVO to set
	 */
	public void setEmployerLoginVO(EmployerLoginVO employerLoginVO) {
		this.employerLoginVO = employerLoginVO;
	}

	/**
	 * @return the savecandidateid
	 */
	@Id
	@GeneratedValue
	@Column(name = "selected_Id", unique = true, nullable = false)
	public long getSavecandidateid() {
		return this.savecandidateid;
	}

	/**
	 * @param savecandidateid
	 *            the savecandidateid to set
	 */
	public void setSavecandidateid(long savecandidateid) {
		this.savecandidateid = savecandidateid;
	}

	/**
	 * @return the jobseekerProfileVO
	 */
	@ManyToOne
	@JoinColumn(name = "profileId")
	public JobseekerProfileVO getJobseekerProfileVO() {
		return this.jobseekerProfileVO;
	}

	/**
	 * @param jobseekerProfileVO
	 *            the jobseekerProfileVO to set
	 */
	public void setJobseekerProfileVO(JobseekerProfileVO jobseekerProfileVO) {
		this.jobseekerProfileVO = jobseekerProfileVO;
	}

	/**
	 * @return the isShortListed
	 */
	@Column(name = "isShortListed")
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

}