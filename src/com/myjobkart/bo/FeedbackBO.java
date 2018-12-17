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

/**
 * @author User
 *
 */
public class FeedbackBO extends BaseBO {
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = " Name Should be a Character")
	@Size(min = 3, max = 20)
	private String name;
	@NotEmpty
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid Email Format")
	private String email;
	@NotNull
	@Range(min = 1111111111,message = "Mobile Number must be a 10 Digits")
	private Long phoneno;
	@NotEmpty
	private String area;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z,0-9,.\\s]*$", message = "Subject should be a Character and Number")
	private String subject;
	@NotEmpty
	private String details;
	private long id;
	private Boolean isDelete;
	private List<FeedbackBO> feedbackList;
	
	private int sno;
	private String feedbackCreateDate;
	private String searchElement;
	

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
	/**
	 * @return the feedbackCreateDate
	 */
	public String getFeedbackCreateDate() {
		return feedbackCreateDate;
	}
	/**
	 * @param feedbackCreateDate the feedbackCreateDate to set
	 */
	public void setFeedbackCreateDate(String feedbackCreateDate) {
		this.feedbackCreateDate = feedbackCreateDate;
	}
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
	public Long getPhoneno() {
		return phoneno;
	}
	/**
	 * @param phoneno the phoneno to set
	 */
	public void setPhoneno(Long phoneno) {
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
	 * @return the feedbackList
	 */
	public List<FeedbackBO> getFeedbackList() {
		return feedbackList;
	}
	/**
	 * @param feedbackList the feedbackList to set
	 */
	public void setFeedbackList(List<FeedbackBO> feedbackList) {
		this.feedbackList = feedbackList;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the searchElement
	 */
	public String getSearchElement() {
		return searchElement;
	}
	/**
	 * @param searchElement the searchElement to set
	 */
	public void setSearchElement(String searchElement) {
		this.searchElement = searchElement;
	}
	
}
