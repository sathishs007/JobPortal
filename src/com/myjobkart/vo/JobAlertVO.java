/**
 * 
 */
package com.myjobkart.vo;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

/**
 * @author ultimate
 * 
 */
@Entity
@Table(name = "jobseeker_jobalert")
public class JobAlertVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	private long id;
    private String firstName;
	private String password;
	private String keySkills;
	private String preferredLocation;
	private String jobType;
	private String salary;
	private String alertName;
	private String role;
	private String preferredIndustry;
	private String emailId;
	private Boolean isDeleted = false;
	private Boolean isActived = false;
	private String experienceInYear;
	private JobseekerLoginVO jobSeekerLogin;
	private JobseekerVO jobSeeker;
	private String degree;
	private String yearOfPassing;
	private byte[] profileImage;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name = "alert_id")
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the keySkills
	 */
	@Column(name = "key_skill")
	public String getKeySkills() {
		return keySkills;
	}

	/**
	 * @param keySkills
	 *            the keySkills to set
	 */
	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}

	/**
	 * @return the preferredLocation
	 */
	@Column(name = "preferred_location")
	public String getPreferredLocation() {
		return preferredLocation;
	}

	/**
	 * @param preferredLocation
	 *            the preferredLocation to set
	 */
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	/**
	 * @return the jobType
	 */
	@Column(name = "job_type")
	public String getJobType() {
		return jobType;
	}

	/**
	 * @param jobType
	 *            the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the salary
	 */
	@Column(name = "salary")
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * @return the alertName
	 */
	@Column(name = "alert_name")
	public String getAlertName() {
		return alertName;
	}

	/**
	 * @param alertName
	 *            the alertName to set
	 */
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	/**
	 * @return the role
	 */
	@Column(name = "role")
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the preferredIndustry
	 */
	@Column(name = "preferred_industry")
	public String getPreferredIndustry() {
		return preferredIndustry;
	}

	/**
	 * @param preferredIndustry
	 *            the preferredIndustry to set
	 */
	public void setPreferredIndustry(String preferredIndustry) {
		this.preferredIndustry = preferredIndustry;
	}

	/**
	 * @return the emailId
	 */
	@Column(name = "email_id")
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the isDeleted
	 */
	@Column(name = "is_deleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the isActived
	 */
	@Column(name = "is_activated")
	public Boolean getIsActived() {
		return isActived;
	}

	/**
	 * @param isActived
	 *            the isActived to set
	 */
	public void setIsActived(Boolean isActived) {
		this.isActived = isActived;
	}

	/**
	 * @return the experienceInYear
	 */
	@Column(name = "experience_year")
	public String getExperienceInYear() {
		return experienceInYear;
	}

	/**
	 * @param experienceInYear
	 *            the experienceInYear to set
	 */
	public void setExperienceInYear(String experienceInYear) {
		this.experienceInYear = experienceInYear;
	}

	/**
	 * @return the jobSeekerLogin
	 */
	@ManyToOne
	@JoinColumn(name = "jobseeker_Login_id")
	public JobseekerLoginVO getJobSeekerLogin() {
		return jobSeekerLogin;
	}

	/**
	 * @param jobSeekerLogin
	 *            the jobSeekerLogin to set
	 */
	public void setJobSeekerLogin(JobseekerLoginVO jobSeekerLogin) {
		this.jobSeekerLogin = jobSeekerLogin;
	}

	/**
	 * @param jobSeekerId
	 */
	public void setJobSeekerId(long jobSeekerId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the yearOfPassing
	 */
	public String getYearOfPassing() {
		return yearOfPassing;
	}

	/**
	 * @param yearOfPassing the yearOfPassing to set
	 */
	public void setYearOfPassing(String yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	/**
	 * @return the profileImage
	 */
	public byte[] getProfileImage() {
		return profileImage;
	}

	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(byte[] bs) {
		this.profileImage = bs;
	}

	/**
	 * @return the jobSeeker
	 */
	@ManyToOne
	@JoinColumn(name = "jobseeker_Reg_id")
	public JobseekerVO getJobSeeker() {
		return jobSeeker;
	}

	/**
	 * @param jobSeeker the jobSeeker to set
	 */
	public void setJobSeeker(JobseekerVO jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

}
