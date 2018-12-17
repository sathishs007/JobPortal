package com.megatech.model;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class CustomerBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3794449461414183610L;

	private String customerId;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "CustomerName name should be a character")
	@Size(min = 3, max = 40)
	private String customerName;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Company name should be a character")
	@Size(min = 3, max = 40)
	private String companyName;

	@NotNull(message = "Mobile No is Required")
	@Digits(integer = 10, fraction = 0, message = "Mobile No accept only 10 digits")
	@Range(min = 777777777, message = "Mobile No is not Valid")
	private Long mobileNumber;

	@Digits(integer = 10, fraction = 0, message = "Office No accept only 10 digits")
	@Range(min = 222222222, message = "Office No is not Valid")
	private Long officeNumber;

	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email is not correct format")
	private String emailAddress;

	private String address;
	
	private boolean isDeleted;

	private List<CustomerBO> allCustomerBOList;

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the mobileNumber
	 */
	public Long getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the officeNumber
	 */
	public Long getOfficeNumber() {
		return officeNumber;
	}

	/**
	 * @param officeNumber the officeNumber to set
	 */
	public void setOfficeNumber(Long officeNumber) {
		this.officeNumber = officeNumber;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the allCustomerBOList
	 */
	public List<CustomerBO> getAllCustomerBOList() {
		return allCustomerBOList;
	}

	/**
	 * @param allCustomerBOList the allCustomerBOList to set
	 */
	public void setAllCustomerBOList(List<CustomerBO> allCustomerBOList) {
		this.allCustomerBOList = allCustomerBOList;
	}

	
	
}
