package com.megatech.model;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class AddressInfo extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6727515253148723662L;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Incharge Should be a Character only")
	@Size(min = 3, max = 40)
	private String labIncharge;

	@NotNull(message = "Phone No is Required")
	@Digits(integer = 10, fraction = 0, message = "Phone No accept only 10 digits")
	@Range(min = 777777777, message = "Phone No is not Valid")
	private Long contactNumber;

	@NotNull(message = "Phone No is Required")
	@Digits(integer = 10, fraction = 0, message = "Phone No accept only 10 digits")
	@Range(min = 777777777, message = "Phone No is not Valid")
	private Long landLineNumber;
	@NotEmpty
	private String workingDay;
	@NotEmpty
	private String workingHourse;
	@NotEmpty
	private String city;
	private long cityId;
	private long locationId;
	@NotEmpty
	private String location;
	private long labId;
	@NotEmpty
	private String labName;

	/*
	 * @Digits(integer = 8, fraction = 0, message =
	 * "PinCode Accept only 8 digits ")
	 * 
	 * @Range(min=100000 ,message="PinCode is Not valid")
	 */
	private long pinCode;

	private List<AddressInfo> allAddressInfo;

	@NotEmpty
	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email is not correct format")
	private String emailAddress;
	@NotEmpty
	/*@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Invalid Format")*/
	private String address;

	/**
	 * @return the labIncharge
	 */
	public String getLabIncharge() {
		return labIncharge;
	}

	/**
	 * @param labIncharge
	 *            the labIncharge to set
	 */
	public void setLabIncharge(String labIncharge) {
		this.labIncharge = labIncharge;
	}

	/**
	 * @return the contactNumber
	 */
	public Long getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the landLineNumber
	 */
	public Long getLandLineNumber() {
		return landLineNumber;
	}

	/**
	 * @param landLineNumber
	 *            the landLineNumber to set
	 */
	public void setLandLineNumber(Long landLineNumber) {
		this.landLineNumber = landLineNumber;
	}

	/**
	 * @return the workingDay
	 */
	public String getWorkingDay() {
		return workingDay;
	}

	/**
	 * @param workingDay
	 *            the workingDay to set
	 */
	public void setWorkingDay(String workingDay) {
		this.workingDay = workingDay;
	}

	/**
	 * @return the workingHourse
	 */
	public String getWorkingHourse() {
		return workingHourse;
	}

	/**
	 * @param workingHourse
	 *            the workingHourse to set
	 */
	public void setWorkingHourse(String workingHourse) {
		this.workingHourse = workingHourse;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the cityId
	 */
	public long getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the locationId
	 */
	public long getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *            the locationId to set
	 */
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the pinCode
	 */
	public long getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode
	 *            the pinCode to set
	 */
	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the labId
	 */
	public long getLabId() {
		return labId;
	}

	/**
	 * @param labId
	 *            the labId to set
	 */
	public void setLabId(long labId) {
		this.labId = labId;
	}

	/**
	 * @return the labName
	 */
	public String getLabName() {
		return labName;
	}

	/**
	 * @param labName
	 *            the labName to set
	 */
	public void setLabName(String labName) {
		this.labName = labName;
	}

	/**
	 * @return the allAddressInfo
	 */
	public List<AddressInfo> getAllAddressInfo() {
		return allAddressInfo;
	}

	/**
	 * @param allAddressInfo
	 *            the allAddressInfo to set
	 */
	public void setAllAddressInfo(List<AddressInfo> allAddressInfo) {
		this.allAddressInfo = allAddressInfo;
	}

}
