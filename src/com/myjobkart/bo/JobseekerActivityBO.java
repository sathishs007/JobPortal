/**
 * 
 */
package com.myjobkart.bo;


import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import com.myjobkart.vo.EmployerVO;



/**
 * @author User
 *
 */
public class JobseekerActivityBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8828760417561301661L;

	private int SNo;
	
	private long activityId;
	private String activityDate;
	private String activityStatus;
	private JobSeekerLoginBO jobseekerLoginBO;
	private JobseekerBO jobseekerBO;
	private EmployerLoginBO employerLoginBO;
	private EmployerVO employerVO;
	private JobPostBO jobPostBO;
	private JobseekerProfileBO jobseekerProfileBO;
	
	
	
	private long adminId;
	private long DateandTime;
	private String jobseekerName;
	
	
	private String employerName;
	private String jobPostTitle;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;
	
	
	public long getActivityId() {
		return activityId;
	}
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	
	
	public String getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	
	public long getDateandTime() {
		return DateandTime;
	}
	public void setDateandTime(long dateandTime) {
		DateandTime = dateandTime;
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
	
	public long getAdminId() {
		return adminId;
	}
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	
	public JobSeekerLoginBO getJobseekerLoginBO() {
		return jobseekerLoginBO;
	}
	public void setJobseekerLoginBO(JobSeekerLoginBO jobseekerLoginBO) {
		this.jobseekerLoginBO = jobseekerLoginBO;
	}
	public JobseekerBO getJobseekerBO() {
		return jobseekerBO;
	}
	public void setJobseekerBO(JobseekerBO jobseekerBO) {
		this.jobseekerBO = jobseekerBO;
	}
	public EmployerLoginBO getEmployerLoginBO() {
		return employerLoginBO;
	}
	public void setEmployerLoginBO(EmployerLoginBO employerLoginBO) {
		this.employerLoginBO = employerLoginBO;
	}
	public EmployerVO getEmployerVO() {
		return employerVO;
	}
	public void setEmployerVO(EmployerVO employerVO) {
		this.employerVO = employerVO;
	}
	
	public String getJobseekerName() {
		return jobseekerName;
	}
	
	public void setJobseekerName(String jobseekerName) {
		this.jobseekerName = jobseekerName;
	}
	
	public String getEmployerName() {
		return employerName;
	}
	
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	public String getJobPostTitle() {
		return jobPostTitle;
	}
	
	public void setJobPostTitle(String jobPostTitle) {
		this.jobPostTitle = jobPostTitle;
	}
	public JobPostBO getJobPostBO() {
		return jobPostBO;
	}
	public void setJobPostBO(JobPostBO jobPostBO) {
		this.jobPostBO = jobPostBO;
	}
	public JobseekerProfileBO getJobseekerProfileBO() {
		return jobseekerProfileBO;
	}
	public void setJobseekerProfileBO(JobseekerProfileBO jobseekerProfileBO) {
		this.jobseekerProfileBO = jobseekerProfileBO;
	}
	
}
