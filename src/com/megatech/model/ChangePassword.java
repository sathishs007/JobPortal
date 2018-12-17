package com.megatech.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePassword extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2294128143764307923L;

	@NotEmpty
	@Size(min = 4, max = 8)
	private String password;

	@NotEmpty
	@Size(min = 4, max = 8)
	private String confirmPassword;

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
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
