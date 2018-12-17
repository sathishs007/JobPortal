package com.megatech.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
@Indexed
@Entity
@Table(name = "admin_login")
public class AdminLoginVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4880895938205493271L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private long id;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column(name = "email_id")
	private String emailAddress;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column(name = "password")
	private String password;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	private String userType;
	/**
	 * @return the id
	 */

	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the emailAddress
	 */

	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the password
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

}
