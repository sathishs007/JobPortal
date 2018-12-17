package com.myjobkart.bo;

import java.util.Date;
import java.util.List;

public class PaymentBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String selectProduct;
	private String productType;
	private int totalMonth;
	private EmployerBO employerRegistration;
	private String paymentDate;
	private String name;
	private Date deletedDate;
	private Boolean isDeleted;
	private long totalcost;
	private long payId;
	private Date endDate;
	private String paymentMode;
	private String startDate;
	private String dateEnd;
	private long empId;
	
	
	private String services;
	private Long productPrice;
	private Long durationDate;
	
	
	private Date validFrom;
	
	private List<PaymentBO> enrolledList;
	private List<PaymentBO> jobseekerPaymentList;
	private List<PaymentBO> enrolledList1;

	/**
	 * @return the selectProduct
	 */
	public String getSelectProduct() {
		return this.selectProduct;
	}

	/**
	 * @param selectProduct
	 *            the selectProduct to set
	 */
	public void setSelectProduct(String selectProduct) {
		this.selectProduct = selectProduct;
	}

	/**
	 * @return the totalMonth
	 */
	public int getTotalMonth() {
		return this.totalMonth;
	}

	/**
	 * @param totalMonth
	 *            the totalMonth to set
	 */
	public void setTotalMonth(int totalMonth) {
		this.totalMonth = totalMonth;
	}

	/**
	 * @return the totalcost
	 */
	public long getTotalcost() {
		return this.totalcost;
	}

	/**
	 * @param totalcost
	 *            the totalcost to set
	 */
	public void setTotalcost(long totalcost) {
		this.totalcost = totalcost;
	}

	/**
	 * @return the payId
	 */
	public long getPayId() {
		return this.payId;
	}

	/**
	 * @param payId
	 *            the payId to set
	 */
	public void setPayId(long payId) {
		this.payId = payId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<PaymentBO> getEnrolledList() {
		return this.enrolledList;
	}

	public void setEnrolledList(List<PaymentBO> enrolledList) {
		this.enrolledList = enrolledList;
	}

	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<PaymentBO> getJobseekerPaymentList() {
		return this.jobseekerPaymentList;
	}

	public void setJobseekerPaymentList(List<PaymentBO> jobseekerPaymentList) {
		this.jobseekerPaymentList = jobseekerPaymentList;
	}

	/**
	 * @return the validFrom
	 */
	public Date getValidFrom() {
		return this.validFrom;
	}

	/**
	 * @param validFrom
	 *            the validFrom to set
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the enrolledList1
	 */
	public List<PaymentBO> getEnrolledList1() {
		return this.enrolledList1;
	}

	/**
	 * @param enrolledList1
	 *            the enrolledList1 to set
	 */
	public void setEnrolledList1(List<PaymentBO> enrolledList1) {
		this.enrolledList1 = enrolledList1;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the dateEnd
	 */
	public String getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd the dateEnd to set
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public EmployerBO getEmployerRegistration() {
		return employerRegistration;
	}

	public void setEmployerRegistration(EmployerBO employerRegistration) {
		this.employerRegistration = employerRegistration;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	/**
	 * @return the services
	 */
	public String getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(String services) {
		this.services = services;
	}

	/**
	 * @return the productPrice
	 */
	public Long getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the durationDate
	 */
	public Long getDurationDate() {
		return durationDate;
	}

	/**
	 * @param durationDate the durationDate to set
	 */
	public void setDurationDate(Long durationDate) {
		this.durationDate = durationDate;
	}

}
