/**
 * 
 */
package com.myjobkart.bo;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.myjobkart.vo.EmployerLoginVO;

/**
 * @author win 7
 *
 */
public class EmployerAlertBO extends BaseBO
{
	private static final long serialVersionUID = 1L;
	private long Id;
	private EmployerLoginBO empLogin;
	private boolean isActive;
	@NotEmpty
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid Email Format")
	@Valid
	private String emailAddress;
	/**
	 * @return the id
	 */
	public long getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		Id = id;
	}
	/**
	 * @return the empLogin
	 */
	public EmployerLoginBO getEmpLogin() {
		return empLogin;
	}
	/**
	 * @param empLogin the empLogin to set
	 */
	public void setEmpLogin(EmployerLoginBO empLogin) {
		this.empLogin = empLogin;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 * @return 
	 */
	public String setActive(boolean isActive) {
		this.isActive = isActive;
		return emailAddress;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the keySkills
	 */
	public String getKeySkills() {
		return keySkills;
	}
	/**
	 * @param keySkills the keySkills to set
	 */
	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String keySkills;
}
