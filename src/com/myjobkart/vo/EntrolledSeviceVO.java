package com.myjobkart.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entrolled_service")
public class EntrolledSeviceVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	private long entrolledid;

	private String selectProduct;

	private int totalMonth;

	private long totalcost;

	private Date validFrom;

	private Date validEnd;
	
	private String producType;

	private long payId;

	private Date deletedDate;

	private Boolean isDeleted;

	/**
	 * @return the entrolledid
	 */
	@Id
	@GeneratedValue
	public long getEntrolledid() {
		return this.entrolledid;
	}

	/**
	 * @param entrolledid
	 *            the entrolledid to set
	 */
	public void setEntrolledid(long entrolledid) {
		this.entrolledid = entrolledid;
	}

	/**
	 * @return the totalMonth
	 */
	@Column(name = "total_month")
	public int getTotalMonth() {
		return this.totalMonth;
	}

	/**
	 * @param selectProduct
	 *            the selectProduct to set
	 */
	public void setSelectProduct(String selectProduct) {
		this.selectProduct = selectProduct;
	}

	/**
	 * @return the productName
	 */
	@Column(name = "product_name")
	public String getSelectProduct() {
		return this.selectProduct;
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
	@Column(name = "total_cost")
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
	 * @return the validFrom
	 */
	@Column(name = "valid_from")
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
	 * @return the validEnd
	 */
	@Column(name = "valid_end")
	public Date getValidEnd() {
		return this.validEnd;
	}

	/**
	 * @param validEnd
	 *            the validEnd to set
	 */
	public void setValidEnd(Date validEnd) {
		this.validEnd = validEnd;
	}

	/**
	 * @return the producType
	 */
	@Column(name = "product_type")
	public String getProducType() {
		return this.producType;
	}

	/**
	 * @param producType
	 *            the producType to set
	 */
	public void setProducType(String producType) {
		this.producType = producType;
	}

	/**
	 * @return the payId
	 */
	@Column(name = "pay_id")
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

	@Column(name = "deleted_date")
	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	@Column(name = "is_deleted")
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	

}
