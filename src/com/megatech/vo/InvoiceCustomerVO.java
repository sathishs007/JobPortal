package com.megatech.vo;

import java.math.BigDecimal;

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
@Table(name = "invoice_customer")
public class InvoiceCustomerVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1979278046758760722L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private long invoiceCustomer;
	
	private BigDecimal amount;
	
	private String description;
	
	@JoinColumn(name = "cust_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private CustomerVO customerVO;

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

	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}

	public long getInvoiceCustomer() {
		return invoiceCustomer;
	}

	public void setInvoiceCustomer(long invoiceCustomer) {
		this.invoiceCustomer = invoiceCustomer;
	}

}
