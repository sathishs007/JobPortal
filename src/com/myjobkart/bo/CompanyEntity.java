package com.myjobkart.bo;

import java.util.ArrayList;

public class CompanyEntity  extends BaseBO{
	
	
	private long companiesId;
	private ArrayList<String> entityName;
	private String entity;
	private String companyName;
	
	/**
	 * @return the companyName
	 */
	

	private String industryType;
	
	
	/**
	 * @return the companiesId
	 */
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
	 * @return the industryType
	 */
	public String getIndustryType() {
		return industryType;
	}

	/**
	 * @param industryType the industryType to set
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
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
	 * @return the company
	 */
	

}
