/**
 * 
 */
package com.myjobkart.service;

import java.util.List;
import java.util.Map;


import com.myjobkart.bo.AdminLoginBO;
import com.myjobkart.bo.ContactBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.FeedbackBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.NewsLetterBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.vo.EmployerProfileVO;

/**
 * @author Administrator
 * 
 */
public interface AdminService<E> extends GenericService<AdminLoginBO> {

	AdminLoginBO authendicate(AdminLoginBO adminLoginBO)
			throws MyJobKartException;

	JobPostBO reteriveAllJoBpost();
	

	/**
	 * @param productBO
	 * @return
	 */
	ProductBO createProduct(ProductBO productBO) throws MyJobKartException;

	/**
	 * @return
	 */
	ProductBO viewProduct();
	boolean productAuthentication(ProductBO productBO);

	/**
	 * @param productBO
	 * @return
	 */
	boolean productActivation(ProductBO productBO);

	/**
	 * @param productBO
	 * @return
	 */
	ProductBO productUpdate(ProductBO productBO)throws MyJobKartException;

	/**
	 * @param productBO
	 * @return
	 * @throws MyJobKartException 
	 */
	ProductBO deleteProduct(ProductBO productBO) throws MyJobKartException;
	
	ContactBO contactBo(ContactBO contact);
	List<ContactBO> retrieveUserConatctDetails(ContactBO contactBO);
	List<FeedbackBO> retriveFeedbackDetails(FeedbackBO feedbackBO);
	public ContactBO deleteContact(ContactBO contactBO);
	public FeedbackBO deleteFeedback(FeedbackBO feedbackBO);
	
	 List<EntityBO> retrieveIndustryList();
	
	
	Map<String, List<EmployerProfileVO>> retrieveCompanyName();
	
	Map<String, List<String>> retrieveEntityList(String Value);

	/**
	 * @return
	 */
	Map<String, List<String>> retrieveExperience();

	/**
	 * @param bo
	 * @return
	 */
	NewsLetterBO newsLetterCreate(NewsLetterBO bo);

	/**
	 * @param emailId
	 * @return
	 */
	boolean findByEmail(String emailId);

	/**
	 * @param uploadFiles
	 * @return
	 * @throws MyJobKartException 
	 */

	EntityBO createCompany(EntityBO uploadFiles) throws MyJobKartException;

	EntityBO createIndustry(EntityBO uploadFiles) throws MyJobKartException;
	
	long uploadEmployer(List<EmployerProfileBO> uploadEmployerList) throws MyJobKartException;
	
	public List<String> reteriveIndustry() throws MyJobKartException;
	
	public List<String> reteriveCompany() throws MyJobKartException;
	
	
	public List<EntityBO> adminCompanyEntityView() throws MyJobKartException;

	/**
	 * @param entityBO
	 * @return
	 * @throws MyJobKartException 
	 */
	EntityBO getCompanyEntity(EntityBO entityBO) throws MyJobKartException;

	/**
	 * @param updateCompany
	 * @return
	 * @throws MyJobKartException 
	 */
	boolean adminUpdateCompanyEntity(EntityBO updateCompany) throws MyJobKartException;

	/**
	 * @param entityBO
	 * @return
	 * @throws MyJobKartException 
	 */
	boolean adminDeleteCompanyEntity(EntityBO entityBO) throws MyJobKartException;

	/**
	 * @return
	 */
	List<EntityBO> adminIndustryEntityView();

	/**
	 * @param entityBO
	 * @return
	 */
	EntityBO getIndustryEntity(EntityBO entityBO);

	/**
	 * @param updateCompany
	 * @return
	 */
	boolean adminUpdateIndutryEntity(EntityBO updateCompany);

	/**
	 * @param entityBO
	 * @return
	 * @throws MyJobKartException 
	 */
	boolean adminDeleteIndustryEntity(EntityBO entityBO) throws MyJobKartException;
	
	public List<EntityBO> retrievemail();
	
	boolean setemployerinvitation(List<EntityBO> entityBOList);
	
	long addNewCompany(String companyName);
	
	List<WalkinBO> retriveAllwalkins(WalkinBO walkinBO) throws MyJobKartException;
	
}
