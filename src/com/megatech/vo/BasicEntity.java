package com.megatech.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@MappedSuperclass
public class BasicEntity implements Serializable {

	private static final long serialVersionUID = -6850552199656968022L;

	@Column(name = "created_date")
	private Date created = new Date();

	@Column(name = "modified_date")
	private Date modified = new Date();

	@Column(name = "created_by")
	private long createdBy;

	@Column(name = "modified_by")
	private long modifiedBy;

	/**
	 * @return the created
	 */

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	public Date getModified() {
		return modified;
	}

	@PrePersist
	@PreUpdate
	protected void updateModifiedDate() {
		modified = new Date();
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the created_By
	 */
	public long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param created_By
	 *            the created_By to set
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modified_By
	 */
	public long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modified_By
	 *            the modified_By to set
	 */
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * 
	 */

}
