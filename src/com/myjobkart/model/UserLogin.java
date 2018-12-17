package com.myjobkart.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserLogin {

	@NotEmpty
	@Size(min = 4, max = 20)
	private String userName;

	@NotEmpty
	@Size(min = 4, max = 8)
	private String password;

	public String getPassword() {
		return this.password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
