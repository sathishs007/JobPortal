package com.megatech.model;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.internal.NotNull;

public class SupplierInvoiceBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1979278046758760722L;

	@NotEmpty
	private String supplierName;

	@NotNull
	@Future
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date invoiceDate;

	@NotEmpty
	private String invoiceNumber;

	@NotNull
	private BigDecimal invoiceAmount;

	private Blob invoiceImage;

	private String supplierId;

	private Long mobileNumber;

	private Long officeNumber;

	private String supplierCompanyName;

	private String email;

	private String address;

	private Integer quantity;

	private String description;

	private boolean isDeleted;

	private BigDecimal total;

	private List<SupplierInvoiceBO> allSupplierInvoiceBOList;

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName
	 *            the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate
	 *            the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber
	 *            the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the invoiceAmount
	 */
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	/**
	 * @param invoiceAmount
	 *            the invoiceAmount to set
	 */
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	/**
	 * @return the invoiceImage
	 */
	public Blob getInvoiceImage() {
		return invoiceImage;
	}

	public void setInvoiceImage(byte[] uploadRecord) throws SerialException,
			SQLException {
		if (null != uploadRecord) {
			this.invoiceImage = new SerialBlob(uploadRecord);
		} else {
			this.invoiceImage = null;
		}

	}

	/**
	 * @return the supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the supplierCompanyName
	 */
	public String getSupplierCompanyName() {
		return supplierCompanyName;
	}

	/**
	 * @param supplierCompanyName
	 *            the supplierCompanyName to set
	 */
	public void setSupplierCompanyName(String supplierCompanyName) {
		this.supplierCompanyName = supplierCompanyName;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the mobileNumber
	 */
	public Long getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
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
	 * @param officeNumber
	 *            the officeNumber to set
	 */
	public void setOfficeNumber(Long officeNumber) {
		this.officeNumber = officeNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the allSupplierInvoiceBOList
	 */
	public List<SupplierInvoiceBO> getAllSupplierInvoiceBOList() {
		return allSupplierInvoiceBOList;
	}

	/**
	 * @param allSupplierInvoiceBOList
	 *            the allSupplierInvoiceBOList to set
	 */
	public void setAllSupplierInvoiceBOList(
			List<SupplierInvoiceBO> allSupplierInvoiceBOList) {
		this.allSupplierInvoiceBOList = allSupplierInvoiceBOList;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
