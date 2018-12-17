package com.megatech.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.sun.istack.internal.NotNull;

public class InvoiceCustomerBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1979278046758760722L;
	@NotEmpty
	private String customerName;
	private String companyName;
	private String email;
	private String address;
	private String customerId;
	private Long contactNo;
	@NotNull
	@Range(min = 50, message = "enter amount min 50 rupees")
	private BigDecimal amount;
	@NotEmpty
	private String description;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
