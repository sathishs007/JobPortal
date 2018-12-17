/**
 * 
 */
package com.myjobkart.bo;

import java.sql.Date;

/**
 * @author win 7
 *
 */
public class EmployerjobAlertsBO extends BaseBO {
	private long id;
	private long jobid;
	private String jobname;
	private Date lastrun;
	private Date nextrun;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the jobid
	 */
	public long getJobid() {
		return jobid;
	}
	/**
	 * @param jobid the jobid to set
	 */
	public void setJobid(long jobid) {
		this.jobid = jobid;
	}
	/**
	 * @return the jobname
	 */
	public String getJobname() {
		return jobname;
	}
	/**
	 * @param jobname the jobname to set
	 */
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	/**
	 * @return the lastrun
	 */
	public Date getLastrun() {
		return lastrun;
	}
	/**
	 * @param lastrun the lastrun to set
	 */
	public void setLastrun(Date lastrun) {
		this.lastrun = lastrun;
	}
	/**
	 * @return the nextrun
	 */
	public Date getNextrun() {
		return nextrun;
	}
	/**
	 * @param nextrun the nextrun to set
	 */
	public void setNextrun(Date nextrun) {
		this.nextrun = nextrun;
	}
}