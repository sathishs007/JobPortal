/**
 * 
 */
package com.myjobkart.bo;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class ShortListCandidate extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2208311019213571984L;

	private AppliedJobBO appliedJobBO;
	private SaveCandidateBO candidateBO;
	private String jobTitle;
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
	private String companyName;
	private String contactMail;
	private String emailId;
	private int experienceInMonth;
	private String preferredIndustry;
	private String expectedCtcPer;
	private long shortlistId;
	private String jobpostTittle;
	private String shortListCompany;
	private Boolean isDeleted;
	private long deletedBy;
	private Date deletedDate;
	private long profileId;
	private long emploginId;
	private long empRegId;
	private long jobId;
	private long jobseekerId;
	private String searchElement;
	
	/**
	 * @return the deletedDate
	 */
	public Date getDeletedDate() {
		return deletedDate;
	}

	/**
	 * @param deletedDate the deletedDate to set
	 */
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	/**
	 * @return the deletedBy
	 */
	public long getDeletedBy() {
		return deletedBy;
	}

	/**
	 * @param deletedBy the deletedBy to set
	 */
	public void setDeletedBy(long deletedBy) {
		this.deletedBy = deletedBy;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	/**
	 * @return the shortListCompany
	 */
	public String getShortListCompany() {
		return shortListCompany;
	}

	/**
	 * @param shortListCompany the shortListCompany to set
	 */
	public void setShortListCompany(String shortListCompany) {
		this.shortListCompany = shortListCompany;
	}

	/**
	 * @return the jobpostTittle
	 */
	public String getJobpostTittle() {
		return jobpostTittle;
	}

	/**
	 * @param jobpostTittle the jobpostTittle to set
	 */
	public void setJobpostTittle(String jobpostTittle) {
		this.jobpostTittle = jobpostTittle;
	}

	private List<ShortListCandidate> candidateList;

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
	public void setUploadResume(Blob uploadResume) {
		this.uploadResume = uploadResume;
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
	 * @return the appliedJobBO
	 */
	public AppliedJobBO getAppliedJobBO() {
		return this.appliedJobBO;
	}

	/**
	 * @param appliedJobBO
	 *            the appliedJobBO to set
	 */
	public void setAppliedJobBO(AppliedJobBO appliedJobBO) {
		this.appliedJobBO = appliedJobBO;
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
	 * @return the contactMail
	 */
	public String getContactMail() {
		return this.contactMail;
	}

	/**
	 * @param contactMail
	 *            the contactMail to set
	 */
	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	/**
	 * @return the candidateBO
	 */
	public SaveCandidateBO getCandidateBO() {
		return this.candidateBO;
	}

	/**
	 * @param candidateBO
	 *            the candidateBO to set
	 */
	public void setCandidateBO(SaveCandidateBO candidateBO) {
		this.candidateBO = candidateBO;
	}

	/**
	 * @return the candidateList
	 */
	public List<ShortListCandidate> getCandidateList() {
		return this.candidateList;
	}

	/**
	 * @param candidateList
	 *            the candidateList to set
	 */
	public void setCandidateList(List<ShortListCandidate> candidateList) {
		this.candidateList = candidateList;
	}

	/**
	 * @return the shortlistId
	 */
	public long getShortlistId() {
		return this.shortlistId;
	}

	/**
	 * @param shortlistId
	 *            the shortlistId to set
	 */
	public void setShortlistId(long shortlistId) {
		this.shortlistId = shortlistId;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public long getEmploginId() {
		return emploginId;
	}

	public void setEmploginId(long emploginId) {
		this.emploginId = emploginId;
	}

	public long getEmpRegId() {
		return empRegId;
	}

	public void setEmpRegId(long empRegId) {
		this.empRegId = empRegId;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public long getJobseekerId() {
		return jobseekerId;
	}

	public void setJobseekerId(long jobseekerId) {
		this.jobseekerId = jobseekerId;
	}

	/**
	 * @return the searchElement
	 */
	public String getSearchElement() {
		return searchElement;
	}

	/**
	 * @param searchElement the searchElement to set
	 */
	public void setSearchElement(String searchElement) {
		this.searchElement = searchElement;
	}

	/**
	 * @param deletedDate2
	 */
	

}
