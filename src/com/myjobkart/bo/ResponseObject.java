/**
 * 
 */
package com.myjobkart.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class ResponseObject<T> extends BaseBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<T> list;

	private int totalPages;
	private int currentPage;
	private int recordsPerPage;
	private int totalRecords;
	private JobPostBO jobPostBO;
	private JobseekerProfileBO jobseekerProfileBO;
	private JobAlertBO jobAlertBO;
	private int records;
	private int start;
	private int page;

	public JobPostBO getJobPostBO() {
		return this.jobPostBO;
	}

	public void setJobPostBO(JobPostBO jobPostBO) {
		if (null == jobPostBO) {
			this.jobPostBO = new JobPostBO();
		} else {
			this.jobPostBO = new JobPostBO();
		}
	}

	public List<T> getList() {
		return this.list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public int getTotalPages() {
		return this.totalPages;
	}

	@Override
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public int getCurrentPage() {
		return this.currentPage;
	}

	@Override
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public int getRecordsPerPage() {
		return this.recordsPerPage;
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getTotalRecords() {
		return this.totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the jobseekerProfileBO
	 */
	public JobseekerProfileBO getJobseekerProfileBO() {
		return this.jobseekerProfileBO;
	}

	/**
	 * @param jobseekerProfileBO
	 *            the jobseekerProfileBO to set
	 */
	public void setJobseekerProfileBO(JobseekerProfileBO jobseekerProfileBO) {
		this.jobseekerProfileBO = jobseekerProfileBO;
	}

	/**
	 * @return the records
	 */
	public int getRecords() {
		return this.records;
	}

	/**
	 * @param records
	 *            the records to set
	 */
	public void setRecords(int records) {
		this.records = records;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return this.start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return this.page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the jobAlertBO
	 */
	public JobAlertBO getJobAlertBO() {
		return jobAlertBO;
	}

	/**
	 * @param jobAlertBO the jobAlertBO to set
	 */
	public void setJobAlertBO(JobAlertBO jobAlertBO) {
		this.jobAlertBO = jobAlertBO;
	}

}
