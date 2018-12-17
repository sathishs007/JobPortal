/**
 * 
 */
package com.myjobkart.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;




import java.sql.Blob;
import java.util.List;


/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Aravindhan
 * Description : Backing Controller class for EmployerBO Reviewed by :
 * 
 */

@Entity
@Table(name = "employerjob_alerts")
public class EmployerjobAlertsVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	

	private String jobId;

	private String jobName;

	private String lastRun;

	private String nextRun;

	

	@Id
	@GeneratedValue
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the jobName
	 */
	@Column(name = "job_Name")
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	/**
	 * @return the lastRun
	 */
	@Column(name = "last_Run")
	public String getLastRun() {
		return lastRun;
	}

	/**
	 * @param lastRun the lastRun to set
	 */
	public void setLastRun(String lastRun) {
		this.lastRun = lastRun;
	}

	/**
	 * @return the nextRun
	 */
	@Column(name = "next_Run")
	public String getNextRun() {
		return nextRun;
	}

	/**
	 * @param nextRun the nextRun to set
	 */
	public void setNextRun(String nextRun) {
		this.nextRun = nextRun;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the jobId
	 */
	@Column(name = "job_Id")
	public String getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	

	
	
	

	
}















