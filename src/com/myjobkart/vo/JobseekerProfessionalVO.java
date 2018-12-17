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
 * @author User
 *
 */
@Entity
@Indexed
@Table(name = "jobseeker_professional")
public class JobseekerProfessionalVO extends BasicEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long professionalId;
	@Column(name="companyName")
	private String companyName;
	@Column(name="companyType")
	private String companyType;
	@Column(name="expStatus")
	private boolean expStatus;
	/**
	 * @return the expStatus
	 */
	public boolean getExpStatus() {
		return expStatus;
	}
	/**
	 * @param expStatus the expStatus to set
	 */
	public void setExpStatus(boolean expStatus) {
		this.expStatus = expStatus;
	}
	@Column(name="designation")
	private String designation;
	@Column(name="experienceInMonth")
	private int experienceInMonth;
	@Column(name="expStartDate")
	private Date expStartDate;
	@Column(name="expEndDate")
	private Date expEndDate;
	@Column(name="lastSalary")
	private String lastSalary;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private CompanyVO companyVO;
	/**
	 * @return the lastSalary
	 */
	
	public String getLastSalary() {
		return lastSalary;
	}
	/**
	 * @param lastSalary the lastSalary to set
	 */
	public void setLastSalary(String lastSalary) {
		this.lastSalary = lastSalary;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}
	/**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the experienceInMonth
	 */
	public int getExperienceInMonth() {
		return experienceInMonth;
	}
	/**
	 * @param experienceInMonth the experienceInMonth to set
	 */
	public void setExperienceInMonth(int experienceInMonth) {
		this.experienceInMonth = experienceInMonth;
	}
	/**
	 * @return the expStartDate
	 */
	public Date getExpStartDate() {
		return expStartDate;
	}
	/**
	 * @param expStartDate the expStartDate to set
	 */
	public void setExpStartDate(Date expStartDate) {
		this.expStartDate = expStartDate;
	}
	/**
	 * @return the expEndDate
	 */
	public Date getExpEndDate() {
		return expEndDate;
	}
	/**
	 * @param expEndDate the expEndDate to set
	 */
	public void setExpEndDate(Date expEndDate) {
		this.expEndDate = expEndDate;
	}
	/**
	 * @return the professionalId
	 */
	@Id
	@GeneratedValue
	public long getProfessionalId() {
		return professionalId;
	}
	/**
	 * @param professionalId the professionalId to set
	 */
	public void setProfessionalId(long professionalId) {
		this.professionalId = professionalId;
	}
	/**
	 * @return the companyVO
	 */
	@ManyToOne
	@JoinColumn(name = "company_id")
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	/**
	 * @param companyVO the companyVO to set
	 */
	
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}

}
