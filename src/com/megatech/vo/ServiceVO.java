package com.megatech.vo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.annotations.Cascade;
import org.hibernate.metamodel.binding.CascadeType;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Indexed
@Entity
@Table(name = "service")
public class ServiceVO extends BasicEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2639761267265134367L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private int serviceId;
	@JoinColumn(name = "customer_id" )
	@ManyToOne(fetch = FetchType.LAZY)
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private CustomerVO customerVO;
	@Column(name = "customer_name")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String customerName;
	@Column(name = "job_no")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String jobNo;
	@Column(name = "technician_name")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String technician;
	@Column(name = "location")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String location;
	@Column(name = "date_of_servics")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private Date dateOfServics;
	@Column(name = "description")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String description;
	@Column(name = "ref_no")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String refNo;
	@Column(name = "file_upload")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private Blob fileupload;
	@Column(name = "special_remarks")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String specialRemarks;
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
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	
	
	
	
}
