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
 * @author User
 *
 */
@Entity
@Table(name="feedback")
public class FeedbackVO extends BasicEntity{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="phoneno")
	private long phoneno;
	@Column(name="area")
	private String area;
	@Column(name="subject")
	private String subject;
	@Column(name="details")
	private String details;
	@Column(name="isDelete")
	private Boolean isDelete=false;
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return the phoneno
	 */
	public long getPhoneno() {
		return phoneno;
	}
	/**
	 * @param phoneno the phoneno to set
	 */
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the isDelete
	 */
	public Boolean getIsDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * @return the serialversionuid
	 */
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
