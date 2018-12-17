package com.megatech.vo;

import java.math.BigDecimal;
import java.sql.Blob;
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

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.megatech.model.CustomerBO;

@Indexed
@Entity
@Table(name = "supplier_invoice")
public class SupplierInvoiceVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1979278046758760722L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private long id;

	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "invoice_number")
	private String invoiceNumber;

	@Column(name = "invoice_amount")
	private BigDecimal invoiceAmount;

	@JoinColumn(name = "supplier_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private SupplierVO supplierVO;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "description")
	private String description;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the supplierVO
	 */
	public SupplierVO getSupplierVO() {
		return supplierVO;
	}

	/**
	 * @param supplierVO
	 *            the supplierVO to set
	 */
	public void setSupplierVO(SupplierVO supplierVO) {
		this.supplierVO = supplierVO;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	

}
