package com.myjobkart.bo;

import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class NewsLetterBO extends BaseBO

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6613456902953287609L;
	
	@NotEmpty
	@Email
	private String emailId;
	
	private Boolean isdeleted;
	
	
	private List<NewsLetterBO> newsLetterList;
	
	

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	/**
	 * @return the newsLetterList
	 */
	public List<NewsLetterBO> getNewsLetterList() {
		return newsLetterList;
	}

	/**
	 * @param newsLetterList the newsLetterList to set
	 */
	public void setNewsLetterList(List<NewsLetterBO> newsLetterList) {
		this.newsLetterList = newsLetterList;
	}
	
	

}


