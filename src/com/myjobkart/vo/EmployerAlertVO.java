/**
 * 
 */
package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * @author win 7
 * 
 */
@Entity
@Table(name = "Employer_alert")
public class EmployerAlertVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long Id;
	private EmployerLoginVO empLogin;
	private JobPostVO jobpost;
	private boolean isActive;
	
	private String emailAddress;
	private String keySkills;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public long getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		Id = id;
	}
	/**
	 * @return the empLogin
	 */

	@ManyToOne
	@JoinColumn(name = "em_Id")
	public EmployerLoginVO getEmpLogin() {
		return empLogin;
	}
	/**
	 * @param empLogin the empLogin to set
	 */
	public void setEmpLogin(EmployerLoginVO empLogin) {
		this.empLogin = empLogin;
	}
	/**
	 * @return the isActive
	 */
	@Column(name="isActive")
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the emailAddress
	 */
	@Column(name="emailaddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@ManyToOne
	@JoinColumn(name = "jobPost_id")
	public JobPostVO getJobpost() {
		return jobpost;
	}
	/**
	 * @param jobpost the jobpost to set
	 */
	public void setJobpost(JobPostVO jobpost) {
		this.jobpost = jobpost;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the keySkills
	 */
	public String getKeySkills() {
		return keySkills;
	}
	/**
	 * @param keySkills the keySkills to set
	 */
	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}
	
	
}
