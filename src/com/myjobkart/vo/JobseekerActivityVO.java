package com.myjobkart.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "jobseeker_activity")
public class JobseekerActivityVO extends BasicEntity {

	private static final long serialVersionUID = -1999952737844997692L;

	@Id
	@GeneratedValue
	@Column(name = "activity_id")
	private long id;

	@Column(name = "activity_date")
	private String activityDate;
	
	@Column(name = "Activity_status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	@Cascade(CascadeType.PERSIST)
	private JobPostVO jobPostVO;

	@JoinColumn(name = "job_prf_id")
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private JobseekerProfileVO jobseekerProfileVO;

	

	@JoinColumn(name = "jobseeker_id")
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private JobseekerVO jobseekerVO;
	
	@JoinColumn(name = "joblogin_id")
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private JobseekerLoginVO jobseekerLoginVO;
	
	

	@JoinColumn(name = "emplogin_id")
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private EmployerLoginVO employerLoginVO;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JobPostVO getJobPostVO() {
		return jobPostVO;
	}

	public void setJobPostVO(JobPostVO jobPostVO) {
		this.jobPostVO = jobPostVO;
	}


	public JobseekerVO getJobseekerVO() {
		return jobseekerVO;
	}

	public void setJobseekerVO(JobseekerVO jobseekerVO) {
		this.jobseekerVO = jobseekerVO;
	}

	public JobseekerProfileVO getJobseekerProfileVO() {
		return jobseekerProfileVO;
	}

	public void setJobseekerProfileVO(JobseekerProfileVO jobseekerProfileVO) {
		this.jobseekerProfileVO = jobseekerProfileVO;
	}

	public JobseekerLoginVO getJobseekerLoginVO() {
		return jobseekerLoginVO;
	}

	public void setJobseekerLoginVO(JobseekerLoginVO jobseekerLoginVO) {
		this.jobseekerLoginVO = jobseekerLoginVO;
	}
	
	public EmployerLoginVO getEmployerLoginVO() {
		return employerLoginVO;
	}

	public void setEmployerLoginVO(EmployerLoginVO employerLoginVO) {
		this.employerLoginVO = employerLoginVO;
	}

}
