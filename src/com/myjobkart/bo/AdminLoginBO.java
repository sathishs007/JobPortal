package com.myjobkart.bo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.myjobkart.vo.AdminLoginVO;

/**
 * @author Administrator
 * 
 */
public class AdminLoginBO extends AdminLoginVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4049514026397242479L;



	private boolean rememberMe;

	private boolean isActive;



	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the rememberMe
	 */
	public boolean getRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe the rememberMe to set
	 */
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
