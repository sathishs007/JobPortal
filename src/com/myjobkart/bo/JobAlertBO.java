/**
 * 
 */
package com.myjobkart.bo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Column;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ultimate
 *
 */
public class JobAlertBO extends BaseBO{
	
	private static final long serialVersionUID = -8297084534459011594L;

	private JobSeekerLoginBO jobseekerLogin;
	
	private long jobSeekerId;
	
	/*private long jobSeekerAlert;*/
	
	private String keySkills;
	
	private String alertDate;
	
	private String preferredLocation;
	
	private String preferredIndustry;
	
	private String jobType;
	
	private String alertName;
	
	private String role;
	
	private Blob profileImage;
	
	private String firstName;
	
	private String password;
	
	private String degree;
	
	private String yearOfPassing;
	
	private long Sno;
	
	private String searchElement;

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getYearOfPassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(String yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	@NotEmpty
	@Email
	private String emailId;
	
	@Pattern(regexp = "^[0-9\\s]*$", message = "Experience Should be a Number")
	private String experienceInYear;
	
	@Pattern(regexp = "^[0-9\\s]*$", message = "Salary Should be a Number")
	private String salary;
	
	private Boolean isDeleted = false;
	
	
	private Boolean isActive = false;
	
	/*private String searchSkills;
	
	private String searchLocation;
*/
	private List<JobAlertBO> jobAlertList;
	
	
	/**
	 * @return the jobseekerLogin
	 */
	public JobSeekerLoginBO getJobseekerLogin() {
		return jobseekerLogin;
	}

	/**
	 * @param jobseekerLogin the jobseekerLogin to set
	 */
	public void setJobseekerLogin(JobSeekerLoginBO jobseekerLogin) {
		this.jobseekerLogin = jobseekerLogin;
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

	/**
	 * @return the preferredLocation
	 */
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Location Should be a Character")
	public String getPreferredLocation() {
		return preferredLocation;
	}

	/**
	 * @param preferredLocation the preferredLocation to set
	 */
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	/**
	 * @return the preferredIndustry
	 */
	public String getPreferredIndustry() {
		return preferredIndustry;
	}

	/**
	 * @param preferredIndustry the preferredIndustry to set
	 */
	public void setPreferredIndustry(String preferredIndustry) {
		this.preferredIndustry = preferredIndustry;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the experienceInYear
	 */
	public String getExperienceInYear() {
		return experienceInYear;
	}

	/**
	 * @param experienceInYear the experienceInYear to set
	 */
	public void setExperienceInYear(String experienceInYear) {
		this.experienceInYear = experienceInYear;
	}

	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * @return the isDelete
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDelete the isDelete to set
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

	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the nameOfTheAlert
	 */
	@Column(name = "Name Of Alert")
	public String getAlertName() {
		return alertName;
	}

	/**
	 * @param nameOfTheAlert the nameOfTheAlert to set
	 */
	public void setAlertName(String alertName) {
		this.alertName = alertName;
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
	 * @return the jobSeekerId
	 */
	public long getJobSeekerId() {
		return jobSeekerId;
	}

	/**
	 * @param jobSeekerId the jobSeekerId to set
	 */
	public void setJobSeekerId(long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	/**
	 * @return the searchSkills
	 */
/*	public String getSearchSkills() {
		return searchSkills;
	}

	*//**
	 * @param searchSkills the searchSkills to set
	 *//*
	public void setSearchSkills(String searchSkills) {
		this.searchSkills = searchSkills;
	}

	*//**
	 * @return the searchLocation
	 *//*
	public String getSearchLocation() {
		return searchLocation;
	}

	*//**
	 * @param searchLocation the searchLocation to set
	 *//*
	public void setSearchLocation(String searchLocation) {
		this.searchLocation = searchLocation;
	}*/

	/**
	 * @return
	 */
	public List<JobAlertBO> getJobAlertList() {
		return jobAlertList;
	}
	
	public void setJobAlertList(List<JobAlertBO> jobAlertList){
		this.jobAlertList = jobAlertList;
	}
	
	
	public Blob getProfileImage() {
		return profileImage;
	}

	/**
	 * @param bs the profileImage to set
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

	public long getSno() {
		return Sno;
	}

	public void setSno(long sno) {
		Sno = sno;
	}

	public String getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
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