/**
 * 
 */
package com.myjobkart.bo;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.myjobkart.bo.BaseBO;

/**
 * @author Administrator
 *
 */
public class ProductBO extends BaseBO{
	
	private long productId;
	private String productType;
	@NotEmpty
	@Pattern(regexp = "^[a-z,A-Z,0-9,\\s]*$", message = "Name Should be a Character")
	private String services;
	@NotNull
	@Range(min = 7, message = "Enter Number must be above  2 Digits")
	private Long productPrice;
	private Boolean isActive;
	private Boolean isDeleted;
	@NotNull
	@Range(min =1, message = "Enter Number must be above  2 Digits")
	private Long durationDate;
	private String active;
	private String registerDate;
	private int sno;
	
	
	/**
	 * @return the sno
	 */
	public int getSno() {
		return sno;
	}
	/**
	 * @param sno the sno to set
	 */
	public void setSno(int sno) {
		this.sno = sno;
	}
	private List<ProductBO> registeredList;
	
	

	
	/**
	 * @return the registeredList
	 */
	public List<ProductBO> getRegisteredList() {
		return registeredList;
	}
	/**
	 * @param registeredList the registeredList to set
	 */
	public void setRegisteredList(List<ProductBO> registeredList) {
		this.registeredList = registeredList;
	}
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	private int entityLock;
	
	/**
	 * @return the productId
	 */
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
	 * @return the productServices
	 */
	public String getServices() {
		return services;
	}
	/**
	 * @param productServices the productServices to set
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
	 * @return the isActive
	 */
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
	public Long getdurationDate() {
		return durationDate;
	}
	/**
	 * @param durationDate the durationDate to set
	 */
	public void setdurationDate(Long durationDate) {
		this.durationDate = durationDate;
	}
	
	/**
	 * @return the entityLock
	 */
	public int getEntityLock() {
		return entityLock;
	}
	/**
	 * @param entityLock the entityLock to set
	 */
	public void setEntityLock(int entityLock) {
		this.entityLock = entityLock;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	
	

}
