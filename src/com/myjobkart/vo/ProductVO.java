/**
 * 
 */
package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "product")
public class ProductVO extends BasicEntity {

	
	private long productId;
	private String productType;
	private String services;
	private Long productPrice;
	private Boolean isActive;
	private Boolean isDeleted;
	private Long durationDate;
	
	
	
	/**
	 * @return the productId
	 */
	@Id
	@GeneratedValue
	@Column(name="product_id")
	public long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}
	/**
	 * @return the productType
	 */
	@Column(name = "product_type")
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return the services
	 */
	@Column(name = "services")
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
	@Column(name ="product_price")
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
	 * @return the isActive
	 */
	@Column(name="is_active")
	public Boolean getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the isDeleted
	 */
	@Column(name="is_deleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return the durationDate
	 */
	@Column(name="duration_date")
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
