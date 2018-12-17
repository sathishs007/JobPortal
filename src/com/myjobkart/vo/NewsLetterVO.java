package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "news_letter")
public class NewsLetterVO extends BasicEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285822948752591333L;
	
	
	private long newsLetterId;
	
	
	private String emailId;
	
	
	private Boolean isdeleted;
	
	

	@Column(name = "email_id")
	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	
	
   
	@Column(name = "is_deleted")
	public Boolean getIsdeleted() {
		return isdeleted;
	}


	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}


	/**
	 * @return the newsLetterId
	 */
	
	@Id
	@GeneratedValue
	@Column(name = "news_letter_id", unique = true, nullable = false)
	public long getNewsLetterId() {
		return newsLetterId;
	}


	/**
	 * @param newsLetterId the newsLetterId to set
	 */
	public void setNewsLetterId(long newsLetterId) {
		this.newsLetterId = newsLetterId;
	}
	
	
	
	

}
