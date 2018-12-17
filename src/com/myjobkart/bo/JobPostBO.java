package com.myjobkart.bo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class JobPostBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8297084534459011594L;

	private EmployerLoginBO employerLogin;
	private EmployerProfileBO employerProfile;
	private EntityBO companyBO;;
	private long companyId;
	private long sNo;

	@NotNull
	private String address;
	//@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Invalid Format")
	private String companyName;
	private String otherCompany;
	
	private long companyCount;
	private long titleCount;
	private long companiesId;
	
	private long countlocation;

	@NotEmpty
	/*@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name Should be a Character")*/
	private String contactPerson;
	
	@Range(min = 1111111111, message = "Contact Number must be a 10 Digits")
	private Long contactNo;
	@NotEmpty
	private String currencyType;

	private long deletedBy;

	private Date deletedDate;
	@NotEmpty
	private String functionArea;
	private String industryType;

	private Boolean isDeleted;
	@NotEmpty
	@Size(max = 50000)
	private String jobDescription;
	@NotNull
	private String jobTitle;
	
	
	
	
	private String searchKeywords;
	/**
	 * @return the searchKeywords
	 */
	public String getSearchKeywords() {
		return searchKeywords;
	}

	/**
	 * @param searchKeywords the searchKeywords to set
	 */
	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}

	@NotEmpty
	private String keywords;
	@NotEmpty
	private String jobLocation;
	@NotEmpty
	private String maxSalary;
	@NotEmpty
	private String minSalary;

	private String pgQualification;
	@NotEmpty
	private String otherSalaryDetails;
	@NotEmpty
	private String ugQualification;
	@NotNull
	@Digits(fraction = 0, integer = 4)
	@Range(min =1, message="Minimum value is 1" )
	private Long noVacancies;
	@NotEmpty
	private String maxExp;
	@NotEmpty
	private String minExp;
	@NotEmpty
	private String jobResponse;
	private String searchElement;
	private Boolean isActive;
	private Blob photo;
	private Blob presentation;
	private String experiance;
	private String salary;
	private long empId;
	private long jobId;
	private String sDate;
	private String eDate;
	
	private long uploadEmpId;
	private String uploadEmailId;
	
	private String searchType;
	
	
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;

	@NotEmpty
	private String postedBy;

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	private String status;
	private String emailId;
	private List<JobPostBO> jobPostList;
	private List<JobPostBO> catagoriesList;
	private List<JobPostBO> searchjobPostList;
	private List<JobPostBO> companyList;
	private List<JobPostBO> countLocationList;
	
	private List<JobPostBO> countTitleList ;
	private List<JobPostBO> jobPostListSearch;
	private List<JobPostBO> jobPostSearchList;
	private List<JobPostBO> saveListCandidates;
	private Boolean isVisiable;
	private Boolean isdisable;
	
	private long employerRegisterID;

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
	public Long getNoVacancies() {
		return this.noVacancies;
	}

	/**
	 * @param noVacancies
	 *            the noVacancies to set
	 */
	public void setNoVacancies(Long noVacancies) {
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
	 * @return the photo
	 */
	public Blob getPhoto() {
		return this.photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 * @throws SQLException
	 * @throws SerialException
	 */
	public void setPhoto(byte[] photo) throws SerialException, SQLException {
		if (null != photo) {
			this.photo = new SerialBlob(photo);
		} else {
			this.photo = null;
		}
	}

	/**
	 * @return the presentation
	 */
	public Blob getPresentation() {
		return this.presentation;
	}

	/**
	 * @param presentation
	 *            the presentation to set
	 * @throws SQLException
	 * @throws SerialException
	 */
	public void setPresentation(byte[] presentation) throws SerialException,
			SQLException {
		if (null != presentation) {
			this.presentation = new SerialBlob(presentation);
		} else {
			this.presentation = null;
		}
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
	 * @return the jobPostList
	 */
	public List<JobPostBO> getJobPostList() {
		return this.jobPostList;
	}

	/**
	 * @param jobPostList
	 *            the jobPostList to set
	 */
	public void setJobPostList(List<JobPostBO> jobPostList) {
		this.jobPostList = jobPostList;
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
	 * @return the jobPostSearchList
	 */
	public List<JobPostBO> getJobPostSearchList() {
		return this.jobPostSearchList;
	}

	/**
	 * @param jobPostSearchList
	 *            the jobPostSearchList to set
	 */
	public void setJobPostSearchList(List<JobPostBO> jobPostSearchList) {
		this.jobPostSearchList = jobPostSearchList;
	}

	/**
	 * @return the jobPostListSearch
	 */
	public List<JobPostBO> getJobPostListSearch() {
		return this.jobPostListSearch;
	}

	/**
	 * @param jobPostListSearch
	 *            the jobPostListSearch to set
	 */
	public void setJobPostListSearch(List<JobPostBO> jobPostListSearch) {
		this.jobPostListSearch = jobPostListSearch;
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
	 * @return the isdisable
	 */
	public Boolean getIsdisable() {
		return this.isdisable;
	}

	/**
	 * @param isdisable
	 *            the isdisable to set
	 */
	public void setIsdisable(Boolean isdisable) {
		this.isdisable = isdisable;
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

	public List<JobPostBO> getSaveListCandidates() {
		return this.saveListCandidates;
	}

	public void setSaveListCandidates(List<JobPostBO> saveListCandidates) {
		this.saveListCandidates = saveListCandidates;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the empId
	 */
	public long getEmpId() {
		return this.empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(long empId) {
		this.empId = empId;
	}

	/**
	 * @return the companyCount
	 */
	public long getCompanyCount() {
		return companyCount;
	}

	/**
	 * @param companyCount the companyCount to set
	 */
	public void setCompanyCount(long companyCount) {
		this.companyCount = companyCount;
	}

	/**
	 * @return the companyList
	 */
	public List<JobPostBO> getCompanyList() {
		return companyList;
	}

	/**
	 * @param companyList the companyList to set
	 */
	public void setCompanyList(List<JobPostBO> companyList) {
		this.companyList = companyList;
	}

	/**
	 * @return the searchjobPostList
	 */
	public List<JobPostBO> getSearchjobPostList() {
		return searchjobPostList;
	}

	/**
	 * @param searchjobPostList the searchjobPostList to set
	 */
	public void setSearchjobPostList(List<JobPostBO> searchjobPostList) {
		this.searchjobPostList = searchjobPostList;
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
	 * @return the countlocation
	 */
	public long getCountlocation() {
		return countlocation;
	}

	/**
	 * @param countlocation the countlocation to set
	 */
	public void setCountlocation(long countlocation) {
		this.countlocation = countlocation;
	}

	/**
	 * @return the countLocationList
	 */
	public List<JobPostBO> getCountLocationList() {
		return countLocationList;
	}

	/**
	 * @param countLocationList the countLocationList to set
	 */
	public void setCountLocationList(List<JobPostBO> countLocationList) {
		this.countLocationList = countLocationList;
	}

	/**
	 * @return the countTitleList
	 */
	public List<JobPostBO> getCountTitleList() {
		return countTitleList;
	}

	/**
	 * @param countTitleList the countTitleList to set
	 */
	public void setCountTitleList(List<JobPostBO> countTitleList) {
		this.countTitleList = countTitleList;
	}

	public List<JobPostBO> getCatagoriesList() {
		return catagoriesList;
	}

	public void setCatagoriesList(List<JobPostBO> catagoriesList) {
		this.catagoriesList = catagoriesList;
	}

	/**
	 * @return the employerRegisterID
	 */
	public long getEmployerRegisterID() {
		return employerRegisterID;
	}

	/**
	 * @param employerRegisterID the employerRegisterID to set
	 */
	public void setEmployerRegisterID(long employerRegisterID) {
		this.employerRegisterID = employerRegisterID;
	}

	public long getUploadEmpId() {
		return uploadEmpId;
	}

	public void setUploadEmpId(long uploadEmpId) {
		this.uploadEmpId = uploadEmpId;
	}

	public String getUploadEmailId() {
		return uploadEmailId;
	}

	public void setUploadEmailId(String uploadEmailId) {
		this.uploadEmailId = uploadEmailId;
	}

	public String getOtherCompany() {
		return otherCompany;
	}

	public void setOtherCompany(String otherCompany) {
		this.otherCompany = otherCompany;
	}

	public EmployerProfileBO getEmployerProfile() {
		return employerProfile;
	}

	public void setEmployerRegistion(EmployerProfileBO employerProfile) {
		this.employerProfile = employerProfile;
	}

	

	public EntityBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(EntityBO companyBO) {
		this.companyBO = companyBO;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	

	public long getCompaniesId() {
		return companiesId;
	}

	public void setCompaniesId(long companiesId) {
		this.companiesId = companiesId;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}

	

	

}