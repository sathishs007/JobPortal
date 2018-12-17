package com.myjobkart.bo;

import java.util.ArrayList;

import javax.validation.constraints.Pattern;

public class EntityBO  extends BaseBO{
	
	
	private long companiesId;
	private ArrayList<String> entityName;
	private String entity;
	private String companyName;
	private String email;
	private ArrayList<EntityBO> newEntityList;
	private String entityType;
	private ArrayList<EntityBO> existingEntityList;
	private long duplicateCompanySize;
	private boolean isDeleted;
	private String IndustryName;
	private long IndustryId;
	private long sNo;
	
	/**
	 * @return the newEntityList
	 */
	public ArrayList<EntityBO> getNewEntityList() {
		return newEntityList;
	}

	/**
	 * @param newEntityList the newEntityList to set
	 */
	public void setNewEntityList(ArrayList<EntityBO> newEntityList) {
		this.newEntityList = newEntityList;
	}

	/**
	 * @return the existingEntityList
	 */
	public ArrayList<EntityBO> getExistingEntityList() {
		return existingEntityList;
	}

	/**
	 * @param existingEntityList the existingEntityList to set
	 */
	public void setExistingEntityList(ArrayList<EntityBO> existingEntityList) {
		this.existingEntityList = existingEntityList;
	}

	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getCompaniesId() {
		return companiesId;
	}

	/**
	 * @param companiesId the companiesId to set
	 */
	public void setCompaniesId(long companiesId) {
		this.companiesId = companiesId;
	}

	

	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * @return the entityName
	 */
	public ArrayList<String> getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(ArrayList<String> entityName) {
		this.entityName = entityName;
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
	 * @return the duplicateCompanySize
	 */
	public long getDuplicateCompanySize() {
		return duplicateCompanySize;
	}

	/**
	 * @param duplicateCompanySize the duplicateCompanySize to set
	 */
	public void setDuplicateCompanySize(long duplicateCompanySize) {
		this.duplicateCompanySize = duplicateCompanySize;
	}

	/**
	 * @return the entityType
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getIndustryId() {
		return IndustryId;
	}

	public void setIndustryId(long industryId) {
		IndustryId = industryId;
	}

	public String getIndustryName() {
		return IndustryName;
	}

	public void setIndustryName(String industryName) {
		IndustryName = industryName;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}

	

	
	
	

}
