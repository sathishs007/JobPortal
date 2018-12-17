package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "view_jobseeker_profile")
public class ViewJobSeekerVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 773336551693413083L;
	private long historyId;
	private EmployerVO employerRegistration;
	private int days;

	private JobseekerLoginVO jobseekerLoginVO;

	private JobseekerProfileVO jobseekerProfileVO;

	@ManyToOne
	@JoinColumn(name = "em_Id")
	public EmployerVO getEmployerRegistration() {
		return this.employerRegistration;
	}

	public void setEmployerRegistration(EmployerVO employerRegistration) {
		this.employerRegistration = employerRegistration;
	}

	@Id
	@GeneratedValue
	public long getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}

	@ManyToOne
	@JoinColumn(name = "id")
	public JobseekerLoginVO getJobseekerLoginVO() {
		return this.jobseekerLoginVO;
	}

	public void setJobseekerLoginVO(JobseekerLoginVO jobseekerLoginVO) {
		this.jobseekerLoginVO = jobseekerLoginVO;
	}

	@Column(name = "days")
	public int getDays() {
		return this.days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@ManyToOne
	@JoinColumn(name = "profileId")
	public JobseekerProfileVO getJobseekerProfileVO() {
		return this.jobseekerProfileVO;
	}

	public void setJobseekerProfileVO(JobseekerProfileVO jobseekerProfileVO) {
		this.jobseekerProfileVO = jobseekerProfileVO;
	}

}
