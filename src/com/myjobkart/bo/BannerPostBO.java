package com.myjobkart.bo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class BannerPostBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long bannerId;
	@NotEmpty
	private String bannerName;
	@NotEmpty
	private String postPage;
	private Blob bannerImage;
	@NotNull(message = "Field Cannot be empty")
	private int totalMonth;
	private long totalcost;
	private Date gracePeriod;
	private int totalDays;
	private Boolean isDelete;
	private EmployerLoginBO employerRegister;
	private Date deletedDate;
	private String status;
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the deletedDate
	 */
	public Date getDeletedDate() {
		return deletedDate;
	}

	/**
	 * @param deletedDate the deletedDate to set
	 */
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	/**
	 * @return the deleteBy
	 */
	public long getDeleteBy() {
		return deleteBy;
	}

	/**
	 * @param deleteBy the deleteBy to set
	 */
	public void setDeleteBy(long deleteBy) {
		this.deleteBy = deleteBy;
	}

	private long deleteBy;

	public long getBannerId() {
		return this.bannerId;
	}

	public void setBannerId(long bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerName() {
		return this.bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public String getPostPage() {
		return this.postPage;
	}

	public void setPostPage(String postPage) {
		this.postPage = postPage;
	}

	public Blob getBannerImage() {
		return this.bannerImage;
	}

	public void setBannerImage(byte[] bannerImage) throws SerialException,
			SQLException {
		if (null != bannerImage) {
			this.bannerImage = new SerialBlob(bannerImage);
		} else {
			this.bannerImage = null;
		}

	}

	public EmployerLoginBO getEmployerRegister() {
		return this.employerRegister;
	}

	public void setEmployerRegister(EmployerLoginBO employerRegister) {
		this.employerRegister = employerRegister;
	}

	public int getTotalMonth() {
		return this.totalMonth;
	}

	public void setTotalMonth(int totalMonth) {
		this.totalMonth = totalMonth;
	}

	public long getTotalcost() {
		return this.totalcost;
	}

	public void setTotalcost(long totalcost) {
		this.totalcost = totalcost;
	}

	public Date getGracePeriod() {
		return this.gracePeriod;
	}

	public void setGracePeriod(Date gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public int getTotalDays() {
		return this.totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * @return the isDelete
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}

	/**
	 * @param isDelete
	 *            the isDelete to set
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

}
