/**
 * 
 */
package com.myjobkart.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author User
 *
 */
@Entity
@Table(name="contact")
public class ContactVO  extends BasicEntity{
	
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="contact_id")
	private int contactId;
	@Column(name="email")
	private String email;
	@Column(name="subject")
	private String subject;
	@Column(name="message")
	private String message;
	@Column(name="name")
	private String name;
	@Column(name="phoneno")
	private long phoneno;
	@Column(name="isDelete")
	private Boolean isDelete=false;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phoneno
	 */
	public long getPhoneno() {
		return phoneno;
	}
	/**
	 * @param phoneno the phoneno to set
	 */
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	public int getcontactId()
	{
		return this.contactId;
	}
	public void setid(int contactId)
	{
		this.contactId=contactId;
	}
	
	public String getemail()
	{
		return this.email;
	}
	public void setemail(String email)
	{
		this.email=email;
	}
	
	
	public String getsubject()
	{
		return this.subject;
	}
   public void setsubject(String subject)
   {
	   this.subject=subject;
   }
   
   
   public String getmessage()
   {
	  return this.message;
   }
   public void setmessage(String message)
   {
	   this.message=message;
   }
/**
 * @return the isDelete
 */
public Boolean getIsDelete() {
	return isDelete;
}
/**
 * @param isDelete the isDelete to set
 */
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
/**
 * @return the serialversionuid
 */
public static long getSerialversionuid() {
	return serialVersionUID;
}
}
