
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import com.myjobkart.bo.EntityBO;

/**
 * @author User
 *
 */
@Entity
@Table( name = "walkin_jobs")

public class WalkinVO   extends BasicEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 415715966740768178L;
	
	@Id
	@GeneratedValue
	@Column(name = "job_id")
	private long jobId;
	
	
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private EmployerLoginVO employerLogin;
	
	//public EntityBO companyId;
	@ManyToOne
	@JoinColumn(name="companyid")
	private CompanyVO companyID;
	
	@ManyToOne
	@JoinColumn(name = "empReg_id")
	private EmployerVO employerRegistionVO;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "contact_no")
	private Long contactNo;
	@Column(name = "job_title")
	private String jobTitle;
	@Column(name = "job_type")
	private String jobType;
	@Column(name = "job_location")
	private String jobLocation;
	@Column(name = "address")
	private String address;
	@Column(name = "max_exp", nullable = false)
	private String maxExp;
	@Column(name = "min_exp", nullable = false)
	private String minExp;
	@Column(name = "job_description")
	private String jobDescription;
	@Column(name = "max_salary")
	private String maxSalary;
	@Column(name = "min_salary")
	private String minSalary;
	@Column(name = "function_area")
	private String functionArea;
	@Column(name = "currency_type")
	private String currencyType;
	@Column(name = "industry_type")
	private String industryType;
	@Column(name = "role_category")
	private String roleCategory;
	@Column(name = "role")
	private String role;
	@Column(name = "keywords")
	private String keywords;
	@Column(name = "ug_qualification")
	private String ugQualification;
	@Column(name = "pg_qualification")
	private String pgQualification;
	@Column (name = "company_profile")
	private String companyProfile;
	@Column(name = "posted_by")
	private String postedBy;
	@Column(name = "contact_person")
	private String contactPerson;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column(name = "job_seeker_response")
	private String jobResponse;
	@Column(name = "no_vacanvies")
	private Long noVacancies;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@DateBridge(resolution = Resolution.DAY)
	private Date startDate;
	@DateBridge(resolution = Resolution.DAY)
	private Date endDate;
	@Column(name = "doctorate")
	private String doctorate;
	@Column(name = "deleted_by", nullable = false)
	private long deletedBy;
	@Column(name = "deleted_date")
	private Date deletedDate;
	@Column(name = "is_deleted")
	private Boolean isDeleted ;
	
	private Boolean isActive;


	
	/*public long getWalkinId() {
		return walkinId;
	}
	public void setWalkinId(long walkinId) {
		this.walkinId = walkinId;
	}*/
	

	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}


	
	public EmployerLoginVO getEmployerLogin() {
		return employerLogin;
	}
	public void setEmployerLogin(EmployerLoginVO employerLogin) {
		this.employerLogin = employerLogin;
	}

	
	public EmployerVO getEmployerRegistionVO() {
		return employerRegistionVO;
	}
	public void setEmployerRegistionVO(EmployerVO employerRegistionVO) {
		this.employerRegistionVO = employerRegistionVO;
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


	
	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}


	
	public String getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(String maxExp) {
		this.maxExp = maxExp;
	}


	
	public String getMinExp() {
		return minExp;
	}
	public void setMinExp(String minExp) {
		this.minExp = minExp;
	}


	
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}


	
	public String getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}


	
	public String getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	

	public String getFunctionArea() {
		return functionArea;
	}
	public void setFunctionArea(String functionArea) {
		this.functionArea = functionArea;
	}


	
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	
	public String getRoleCategory() {
		return roleCategory;
	}
	public void setRoleCategory(String roleCategory) {
		this.roleCategory = roleCategory;
	}

	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	
	public String getUgQualification() {
		return ugQualification;
	}
	public void setUgQualification(String ugQualification) {
		this.ugQualification = ugQualification;
	}


	
	public String getPgQualification() {
		return pgQualification;
	}
	public void setPgQualification(String pgQualification) {
		this.pgQualification = pgQualification;
	}

	
	public String getCompanyProfile() {
		return companyProfile;
	}
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}


	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public String getDoctorate() {
		return doctorate;
	}
	public void setDoctorate(String doctorate) {
		this.doctorate = doctorate;
	}


	
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	
	public Long getNoVacancies() {
		return noVacancies;
	}
	public void setNoVacancies(Long noVacancies) {
		this.noVacancies = noVacancies;
	}

	
	public long getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(long deletedBy) {
		this.deletedBy = deletedBy;
	}


	
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}


	
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	
	
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getJobResponse() {
		return jobResponse;
	}
	public void setJobResponse(String jobResponse) {
		this.jobResponse = jobResponse;
	}

	
	/*public EntityBO getCompanyId() {
		return companyId;
	}
	public void setCompanyId(EntityBO companyId) {
		this.companyId = companyId;
	}*/
	public CompanyVO getCompanyID() {
		return companyID;
	}
	public void setCompanyID(CompanyVO companyID) {
		this.companyID = companyID;
	}
	
}
