package com.megatech.model;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AdminLoginBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5075544932678455420L;
	@NotEmpty
	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email is not correct format")
	private String emailAddress;
	@NotEmpty
	@Size(min = 3, max = 8)
	private String password;

	private List<AdminLoginBO> allProfileList;

	private List<AdminLoginBO> adminUserList;
    private String userType;
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
	 * @return the allProfileList
	 */
	public List<AdminLoginBO> getAllProfileList() {
		return allProfileList;
	}

	/**
	 * @param allProfileList
	 *            the allProfileList to set
	 */
	public void setAllProfileList(List<AdminLoginBO> allProfileList) {
		this.allProfileList = allProfileList;
	}

	/**
	 * @return the adminUserList
	 */
	public List<AdminLoginBO> getAdminUserList() {
		return adminUserList;
	}

	/**
	 * @param adminUserList
	 *            the adminUserList to set
	 */
	public void setAdminUserList(List<AdminLoginBO> adminUserList) {
		this.adminUserList = adminUserList;
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
