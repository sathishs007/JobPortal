package com.myjobkart.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myjobkart.bo.EmployerBO;

@Entity
@Table(name = "booking_order")
public class BookingVO extends BasicEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private long bookingId;
	
	@Column(name = "customer_id")
	private long custId;
	
	@Column(name = "transaction_id")	
	private String transactionId;
	
	@Column(name = "transaction_date")	
	private String transactionDate;
	
	@Column(name = "payment_status")	
	private String paymentStatus;
	
	@Column(name = "payment_mode")	
	private String paymentMode;
	
	@Column(name = "order_amount")
	private String orderAmount;	

	@Column(name = "is_deleted")
	private boolean isDeleted = false;	
	
	@Column(name = "booking_status")
	private String bookingStatus;
	
	@Column(name = "product_type")
    private String productType;
	
	@Column(name = "total_month")
	private int totalMonth;
	
	@OneToOne
	@JoinColumn(name = "emReg_Id")
	private EmployerVO employerRegistration;
	
	

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(int totalMonth) {
		this.totalMonth = totalMonth;
	}

	public EmployerVO getEmployerRegistration() {
		return employerRegistration;
	}

	public void setEmployerRegistration(EmployerVO employerRegistration) {
		this.employerRegistration = employerRegistration;
	}

	/**
	 * @return the bookingId
	 */
	public long getBookingId() {
		return bookingId;
	}

	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the orderAmount
	 */
	public String getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the bookingStatus
	 */
	public String getBookingStatus() {
		return bookingStatus;
	}

	/**
	 * @param bookingStatus the bookingStatus to set
	 */
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
}
