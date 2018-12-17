/**
 * 
 */
package com.myjobkart.bo;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * @author User
 *
 */
public class ContactBO extends BaseBO{

	 private static final long serialVersionUID = 1L;
	 @NotEmpty
	 @Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid Email Format")
     private String email;
	 @NotEmpty
	 @Pattern(regexp = "^[a-zA-Z,0-9,.\\s]*$", message = "Subject should be a Character and Number")
     private String subject;
	 @NotEmpty
     private String message;
	 @NotEmpty
	 @Pattern(regexp = "^[a-zA-Z\\s]*$", message = " Name Should be a Character")
	 @Size(min = 3, max = 20)
	 private String name;
	 @NotNull
	 @Range(min = 1111111111,message = "Mobile Number must be a 10 Digits")
	 private Long phoneno;
	 private long id;
	 private Boolean isDelete;
     private List<ContactBO> userList;
     private String contactCreatedDate;
     private int sno;
     private String searchElement;
     
     
     /**
	 * @return the contactCreatedDate
	 */
	public String getContactCreatedDate() {
		return contactCreatedDate;
	}
	/**
	 * @param contactCreatedDate the contactCreatedDate to set
	 */
	public void setContactCreatedDate(String contactCreatedDate) {
		this.contactCreatedDate = contactCreatedDate;
	}
	/**
	 * @return the sno
	 */
	public int getSno() {
		return sno;
	}
	/**
	 * @param sno the sno to set
	 */
	public void setSno(int sno) {
		this.sno = sno;
	}
	/**
	 * @return the userList
	 */
	public List<ContactBO> getUserList() {
		return userList;
	}
	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<ContactBO> userList) {
		this.userList = userList;
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
     
     public String getname()
     {
    	 return this.name;
     }
     public void setname(String name)
     {
    	 this.name=name;
     }
     
     public Long getphoneno()
     {
    	 return this.phoneno;
     }
     public void setphoneno(Long phoneno)
     {
    	 this.phoneno=phoneno;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	/**
	 * @return the searchElement
	 */
	public String getSearchElement() {
		return searchElement;
	}
	/**
	 * @param searchElement the searchElement to set
	 */
	public void setSearchElement(String searchElement) {
		this.searchElement = searchElement;
	}
}
