package com.myjobkart.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by :
 * sathishkumar.s Description : Backing Controller class for All BO class
 * Reviewed by :
 */
public class BaseBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2398118119617999503L;

	private String errorCode;
	private String errorMessage;
	private String response;
	private long id;
	private String status;
	private String channel;
	private long createdBy;
	private Date created = new Date();
	private Date modified = new Date();
	private int version;
	private int totalPages;
	private int currentPage;
	private int recordsPerPage;
private boolean infoStatus; 
	

	/**
	 * @return the infoStatus
	 */
	public boolean isInfoStatus() {
		return infoStatus;
	}

	/**
	 * @param infoStatus the infoStatus to set
	 */
	public void setInfoStatus(boolean infoStatus) {
		this.infoStatus = infoStatus;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return this.totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the recordsPerPage
	 */
	public int getRecordsPerPage() {
		return this.recordsPerPage;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @param recordsPerPage
	 *            the recordsPerPage to set
	 */
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	private long modifiedBy;

	/**
	 * @return the response
	 */
	public String getResponse() {
		return this.response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return this.created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return this.modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the modifiedBy
	 */
	public long getModifiedBy() {
		return this.modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the createdBy
	 */
	public long getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

}
