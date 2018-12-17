/**
 * 
 */
package com.myjobkart.bo;


import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * @author User
 *
 */
public class EmployerActivityBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8828760417561301661L;

	private int SNo;
	
	private long activityId;
	private String activityDate;
	private String activityStatus;
	
	private long empId;
	private String empName;
	
	private long jbId;
	private String jbpostName;
	private String jbpostTitle;
	
	private long empprfId;
	private long jbprfId;
	
	private long jbseekerId;
	private String jbseekerName;
	private String jbseekerEmail;
	
	private long adminId;
	private long DateandTime;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;
	
	private List<EmployerActivityBO> allRetriveRecords;
	

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

	
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
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
	
	
	public long getEmpprfId() {
		return empprfId;
	}
	public void setEmpprfId(long empprfId) {
		this.empprfId = empprfId;
	}
	
	
	
	public long getJbprfId() {
		return jbprfId;
	}
	public void setJbprfId(long jbprfId) {
		this.jbprfId = jbprfId;
	}
	
	
	
	
	public long getJbseekerId() {
		return jbseekerId;
	}
	
	public void setJbseekerId(long jbseekerId) {
		this.jbseekerId = jbseekerId;
	}
	
	
	
	public long getAdminId() {
		return adminId;
	}
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	
	
	
	public long getJbId() {
		return jbId;
	}
	public void setJbId(long jbId) {
		this.jbId = jbId;
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
	public String getJbseekerName() {
		return jbseekerName;
	}
	public void setJbseekerName(String jbseekerName) {
		this.jbseekerName = jbseekerName;
	}
	
	


	
	
	public String getJbpostName() {
		return jbpostName;
	}
	public void setJbpostName(String jbpostName) {
		this.jbpostName = jbpostName;
	}
	
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
	public String getJbseekerEmail() {
		return jbseekerEmail;
	}
	public void setJbseekerEmail(String jbseekerEmail) {
		this.jbseekerEmail = jbseekerEmail;
	}
	
	
	public String getJbpostTitle() {
		return jbpostTitle;
	}
	public void setJbpostTitle(String jbpostTitle) {
		this.jbpostTitle = jbpostTitle;
	}
	
	
	public List<EmployerActivityBO> getAllRetriveRecords() {
		return allRetriveRecords;
	}
	public void setAllRetriveRecords(List<EmployerActivityBO> allRetriveRecords) {
		this.allRetriveRecords = allRetriveRecords;
	}
}
