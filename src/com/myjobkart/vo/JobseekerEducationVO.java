/**
 * 
 */
package com.myjobkart.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author User
 *
 */
@Entity
@Table(name = "jb_educational_details")
public class JobseekerEducationVO extends BasicEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long educationId;
	@Column(name="degree")
	private String degree;
	@Column(name="college")
	private String college;
	@Column(name="yearOfPassing")
	private String yearOfPassing;
	@Column(name="grade")
	private String grade;
	@Column(name="percentage")
	private String percentage;
	@Column(name="department")
	private String department;
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
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
	 * @return the college
	 */
	public String getCollege() {
		return college;
	}
	/**
	 * @param college the college to set
	 */
	public void setCollege(String college) {
		this.college = college;
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
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the educationId
	 */
	@Id
	@GeneratedValue
	public long getEducationId() {
		return educationId;
	}
	/**
	 * @param educationId the educationId to set
	 */
	public void setEducationId(long educationId) {
		this.educationId = educationId;
	}


}
