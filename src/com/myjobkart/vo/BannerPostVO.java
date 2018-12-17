package com.myjobkart.vo;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "banner_post")
public class BannerPostVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3587964319323412118L;

	private long bannerId;
	private String bannerName;
	private String postPage;
	private Blob bannerImage;
	private long totalcost;
	private Date gracePeriod;
	private int totalMonth;
	private Date deletedDate;
	private long deleteBy;

	private Boolean isDelete;

	private EmployerLoginVO employerRegister;
	private Boolean isActive;
	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Id
	@GeneratedValue
	public long getBannerId() {
		return this.bannerId;
	}

	public void setBannerId(long bannerId) {
		this.bannerId = bannerId;
	}

	@Column(name = "deleted_date")
	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	@Column(name = "banner_name")
	public String getBannerName() {
		return this.bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	@Column(name = "post_page")
	public String getPostPage() {
		return this.postPage;
	}

	public void setPostPage(String postPage) {
		this.postPage = postPage;
	}

	@Column(name = "banner_image")
	public Blob getBannerImage() {
		return this.bannerImage;
	}

	public void setBannerImage(Blob bannerImage) {
		this.bannerImage = bannerImage;
	}

	@ManyToOne
	@JoinColumn(name = "id")
	public EmployerLoginVO getEmployerRegister() {
		return this.employerRegister;
	}

	public void setEmployerRegister(EmployerLoginVO employerRegister) {
		this.employerRegister = employerRegister;
	}

	@Column(name = "total_cost")
	public long getTotalcost() {
		return this.totalcost;
	}

	public void setTotalcost(long totalcost) {
		this.totalcost = totalcost;
	}

	@Column(name = "end_date")
	public Date getGracePeriod() {
		return this.gracePeriod;
	}

	public void setGracePeriod(Date gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	@Column(name = "total_month")
	public int getTotalMonth() {
		return this.totalMonth;
	}

	public void setTotalMonth(int totalMonth) {
		this.totalMonth = totalMonth;
	}

	/**
	 * @return the deleteBy
	 */
	public long getDeleteBy() {
		return this.deleteBy;
	}

	/**
	 * @param deleteBy
	 *            the deleteBy to set
	 */
	public void setDeleteBy(long deleteBy) {
		this.deleteBy = deleteBy;
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
