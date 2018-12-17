package com.megatech.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

public class ServiceBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2867690511697844943L;
	
	private int serviceId;
	private CustomerBO customerBO;
	private String customerName;
	private String jobNo;
	private String technician;
	private String location;
	private Date dateOfServics;
	private String description;
	private String refNo;
	private Blob fileupload;
	private String specialRemarks;
	private String invoiceNo;
	private String imag;
	private List<ServiceBO>allServiceBOList;
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDateOfServics() {
		return dateOfServics;
	}
	public void setDateOfServics(Date dateOfServics) {
		this.dateOfServics = dateOfServics;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public Blob getFileupload() {
		return fileupload;
	}
	
	
	
	
	public void setFileupload(byte[] fileupload) throws SerialException,
	SQLException {
if (null != fileupload) {
	this.fileupload = new SerialBlob(fileupload);
} else {
	this.fileupload = null;
}
}
	
	
	public String getSpecialRemarks() {
		return specialRemarks;
	}
	public void setSpecialRemarks(String specialRemarks) {
		this.specialRemarks = specialRemarks;
	}
	/**
	 * @return the allServiceBOList
	 */
	public List<ServiceBO> getAllServiceBOList() {
		return allServiceBOList;
	}
	/**
	 * @param allServiceBOList the allServiceBOList to set
	 */
	public void setAllServiceBOList(List<ServiceBO> allServiceBOList) {
		this.allServiceBOList = allServiceBOList;
	}
	/**
	 * @return the customerBO
	 */
	public CustomerBO getCustomerBO() {
		return customerBO;
	}
	/**
	 * @param customerBO the customerBO to set
	 */
	public void setCustomerBO(CustomerBO customerBO) {
		this.customerBO = customerBO;
	}
	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}
	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	/**
	 * @return the imag
	 */
	public String getImag() {
		return imag;
	}
	/**
	 * @param imag the imag to set
	 */
	public void setImag(String imag) {
		this.imag = imag;
	}
	

}
