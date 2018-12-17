package com.myjobkart.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jobseekerproduct_service")
public class JobseekerProductServiceVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3310805853826686756L;

	private long productId;

	private BigDecimal productCost;

	private String productType;

	private Date gracePeriod;

	private JobseekerVO jobseeker;

	/**
	 * @return the productId
	 */
	@Id
	@GeneratedValue
	public long getProductId() {
		return this.productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * @return the productCost
	 */
	@Column(name = "product_cost")
	public BigDecimal getProductCost() {
		return this.productCost;
	}

	/**
	 * @param productCost
	 *            the productCost to set
	 */
	public void setProductCost(BigDecimal productCost) {
		this.productCost = productCost;
	}

	/**
	 * @return the productType
	 */
	@Column(name = "product_Type")
	public String getProductType() {
		return this.productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the gracePeriod
	 */
	@Column(name = "grace_Period")
	public Date getGracePeriod() {
		return this.gracePeriod;
	}

	/**
	 * @param gracePeriod
	 *            the gracePeriod to set
	 */
	public void setGracePeriod(Date gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	/**
	 * @return the jobseeker
	 */

	@ManyToOne
	@JoinColumn(name = "id")
	public JobseekerVO getJobseeker() {
		return this.jobseeker;
	}

	/**
	 * @param jobseeker
	 *            the jobseeker to set
	 */
	public void setJobseeker(JobseekerVO jobseeker) {
		this.jobseeker = jobseeker;
	}

}
