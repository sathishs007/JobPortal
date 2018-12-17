
package com.myjobkart.bo;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerVO;

/**
 * @author User
 *
 */
public class WalkinBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5476392267374256942L;

	private long jobId;
	//	private long walkinId;

	private long employerId;
	private long empRegId;
	private long SNo;

	private EmployerLoginBO employerLogin;
	private EmployerBO employerRegistion;

	private String companyName;
	private long companyId;
	@NotEmpty
	private String jobTitle;
	private String jobLocation;
	private String jobType;
	private String otherCompany;
	@NotEmpty
	private String address;
	@NotEmpty
	private String maxExp;
	@NotEmpty
	private String minExp;

	private String experiance;

	@NotEmpty
	@Size(max = 50000)
	private String jobDescription;
	@NotEmpty
	private String maxSalary;
	@NotEmpty
	private String minSalary;

	private String salary;
	@NotEmpty
	private String functionArea;
	@NotEmpty
	private String currencyType;
	@NotEmpty	
	private String industryType;
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "RoleCategory Should be Character")
	private String roleCategory;
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Role Should be Character")
	private String role;
	@NotEmpty
	private String keywords;
	@NotEmpty
	private String ugQualification;
	private String pgQualification;
	@Size(min = 0, max = 50000)
	private String companyProfile;
	@NotEmpty
	private String postedBy;
	@NotEmpty
	private String contactPerson;
	@Range(min = 1111111111, message = "Contact Number must be a 10 Digits")
	private Long contactNo;
	@NotEmpty
	private String jobResponse;

	@NotNull
	@Digits(fraction = 0, integer = 4)
	@Range(min =1, message="Minimum value is 1" )
	private Long noVacancies;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;

	private String doctorate;

	private long deletedBy;
	private Date deletedDate;
	private Boolean isDeleted;
	private Boolean isActive;
	private String status;
	
	private String searchElement;


	private long companyCount;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/*public long getWalkinId() {
		return walkinId;
	}
	public void setWalkinId(long walkinId) {
		this.walkinId = walkinId;
	}
	 */

	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return the jobLocation
	 */
	public String getJobLocation() {
		return jobLocation;
	}
	/**
	 * @param jobLocation the jobLocation to set
	 */
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}
	/**
	 * @return the maxExp
	 */
	public String getMaxExp() {
		return maxExp;
	}
	/**
	 * @param maxExp the maxExp to set
	 */
	public void setMaxExp(String maxExp) {
		this.maxExp = maxExp;
	}
	/**
	 * @return the minExp
	 */
	public String getMinExp() {
		return minExp;
	}
	/**
	 * @param minExp the minExp to set
	 */
	public void setMinExp(String minExp) {
		this.minExp = minExp;
	}
	/**
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription the jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	/**
	 * @return the maxSalary
	 */
	public String getMaxSalary() {
		return maxSalary;
	}
	/**
	 * @param maxSalary the maxSalary to set
	 */
	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}
	/**
	 * @return the minSalary
	 */
	public String getMinSalary() {
		return minSalary;
	}
	/**
	 * @param minSalary the minSalary to set
	 */
	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}
	/**
	 * @return the functionArea
	 */
	public String getFunctionArea() {
		return functionArea;
	}
	/**
	 * @param functionArea the functionArea to set
	 */
	public void setFunctionArea(String functionArea) {
		this.functionArea = functionArea;
	}
	/**
	 * @return the industryType
	 */
	public String getIndustryType() {
		return industryType;
	}
	/**
	 * @param industryType the industryType to set
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	/**
	 * @return the roleCategory
	 */
	public String getRoleCategory() {
		return roleCategory;
	}
	/**
	 * @param roleCategory the roleCategory to set
	 */
	public void setRoleCategory(String roleCategory) {
		this.roleCategory = roleCategory;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * @return the ugQualification
	 */
	public String getUgQualification() {
		return ugQualification;
	}
	/**
	 * @param ugQualification the ugQualification to set
	 */
	public void setUgQualification(String ugQualification) {
		this.ugQualification = ugQualification;
	}
	/**
	 * @return the pgQualification
	 */
	public String getPgQualification() {
		return pgQualification;
	}
	/**
	 * @param pgQualification the pgQualification to set
	 */
	public void setPgQualification(String pgQualification) {
		this.pgQualification = pgQualification;
	}
	/**
	 * @return the companyProfile
	 */
	public String getCompanyProfile() {
		return companyProfile;
	}
	/**
	 * @param companyProfile the companyProfile to set
	 */
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}
	/**
	 * @return the postedBy
	 */
	public String getPostedBy() {
		return postedBy;
	}
	/**
	 * @param postedBy the postedBy to set
	 */
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}
	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the doctorate
	 */
	public String getDoctorate() {
		return doctorate;
	}
	/**
	 * @param doctorate the doctorate to set
	 */
	public void setDoctorate(String doctorate) {
		this.doctorate = doctorate;
	}
	/**
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
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
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public long getEmployerId() {
		return employerId;
	}
	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}


	public long getEmpRegId() {
		return empRegId;
	}
	public void setEmpRegId(long empRegId) {
		this.empRegId = empRegId;
	}


	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}


	public String getJobResponse() {
		return jobResponse;
	}
	public void setJobResponse(String jobResponse) {
		this.jobResponse = jobResponse;
	}


	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getOtherCompany() {
		return otherCompany;
	}
	public void setOtherCompany(String otherCompany) {
		this.otherCompany = otherCompany;
	}

	public Long getNoVacancies() {
		return noVacancies;
	}
	public void setNoVacancies(Long noVacancies) {
		this.noVacancies = noVacancies;
	}
	/*public List<WalkinBO> getWalkinBOList() {
		return walkinBOList;
	}
	public void setWalkinBOList(List<WalkinBO> walkinBOList) {
		this.walkinBOList = walkinBOList;
	}*/


	public EmployerLoginBO getEmployerLogin() {
		return employerLogin;
	}
	public void setEmployerLogin(EmployerLoginBO employerLogin) {
		this.employerLogin = employerLogin;
	}



	public EmployerBO getEmployerRegistion() {
		return employerRegistion;
	}
	public void setEmployerRegistion(EmployerBO employerRegistion) {
		this.employerRegistion = employerRegistion;
	}


	public String getExperiance() {
		return experiance;
	}
	public void setExperiance(String experiance) {
		this.experiance = experiance;
	}


	/*public int getEmpRegId() {
		return empRegId;
	}
	public void setEmpRegId(int empRegId) {
		this.empRegId = empRegId;
	}


	public int getEmployerId() {
		return employerId;
	}
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}*/



	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}


	public long getCompanyCount() {
		return companyCount;
	}
	public void setCompanyCount(long companyCount) {
		this.companyCount = companyCount;
	}


	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getSNo() {
		return SNo;
	}
	public void setSNo(long sNo) {
		SNo = sNo;
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




}
