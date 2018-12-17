package com.myjobkart.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import com.myjobkart.bo.ContactBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.FeedbackBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.vo.AdminLoginVO;
import com.myjobkart.vo.CompaniesVO;
import com.myjobkart.vo.CompanyVO;
import com.myjobkart.vo.ContactVO;
import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerProfileVO;
import com.myjobkart.vo.EmployerVO;
import com.myjobkart.vo.FeedbackVO;
import com.myjobkart.vo.IndustryVO;
import com.myjobkart.vo.NewsLetterVO;
import com.myjobkart.vo.ProductVO;

/**
 * @author Administrator
 * @param <T>
 * 
 */
public interface AdminDAO extends GenericDAO<AdminLoginVO> {

	AdminLoginVO authendicate(String string, String emailAddress)
			throws MyJobKartException;

	List<JobPostBO> reteriveAllJoBpost() throws SerialException, SQLException;
	
	
	boolean setemployerinvitation(long companySequenceNo);
	/**
	 * @param productVO
	 * @return
	 */
	ProductVO createProduct(ProductVO productVO)throws MyJobKartException ;

	/**
	 * @return
	 */
	List<ProductBO> viewProduct();
	boolean procutAuthentication(ProductBO productBO);

	/**
	 * @param productVO
	 * @return
	 */
	boolean procutActivation(ProductVO productVO);

	/**
	 * @param productVO
	 */
	int productUpdate(ProductVO productVO)throws MyJobKartException;

	/**
	 * @param productVO
	 * @return
	 * @throws MyJobKartException 
	 */
	int deleteProduct(ProductVO productVO) throws MyJobKartException;

	ContactVO contactVO(ContactVO contactVO);
	List<ContactBO> retrieveUserConatctDetails(ContactBO contact);
	List<FeedbackBO> retrievefeedbackDetails(FeedbackBO feedback);
	public int deleteContact(ContactVO contactVO);
	public int deleteFeedback(FeedbackVO feedbackVO);
	
	List<EntityBO> retrieveIndustryList() throws MyJobKartException, 
    SerialException, SQLException;
	
	
	Map<String, List<EmployerProfileVO>> retrieveCompanyName();
	
	Map<String, List<String>> retrieveEntityList(String Value);

	/**
	 * @return
	 */
	Map<String, List<String>> retrieveExperience();

	/**
	 * @param vo
	 * @return
	 */
	NewsLetterVO newsLetterCreate(NewsLetterVO vo);

	/**
	 * @param string
	 * @param emailId
	 * @return
	 */
	NewsLetterVO findByParams(String string, String emailId);

	/**
	 * @param companiesVO
	 * @return
	 * @throws MyJobKartException 
	 */
	boolean createCompany(List<CompanyVO> companiesVO) throws MyJobKartException;

	/**
	 * @param companiesVO
	 * @return
	 * @throws MyJobKartException 
	 */
	boolean createIndustry(List<IndustryVO> industryVO) throws MyJobKartException;
	
	List<String> reteriveIndustry()throws MyJobKartException;
	
	List<String> reteriveCompany()throws MyJobKartException;

	/**
	 * @return
	 * @throws MyJobKartException 
	 */
	List<EntityBO> adminCompanyEntityView() throws MyJobKartException;

	/**
	 * @param industryVO
	 * @return
	 * @throws MyJobKartException 
	 */
	CompanyVO getCompanyEntity(CompanyVO companyVO) throws MyJobKartException;

	/**
	 * @param companyVO
	 * @return
	 */
	long adminUpdateCompanyEntity(CompanyVO companyVO);

	/**
	 * @param companyVO
	 * @return
	 * @throws MyJobKartException 
	 */
	int adminDeleteCompanyEntity(CompanyVO companyVO) throws MyJobKartException;

	/**
	 * @return
	 */
	List<EntityBO> adminIndustryEntityView();

	/**
	 * @param industryVO
	 * @return
	 */
	IndustryVO getIndustryEntity(IndustryVO industryVO);

	/**
	 * @param industryVO
	 * @return
	 */
	long adminUpdateIndustryEntity(IndustryVO industryVO);

	/**
	 * @param industryVO
	 * @return
	 * @throws MyJobKartException 
	 */
	int adminDeleteIndustryEntity(IndustryVO industryVO) throws MyJobKartException;
	
	long uploadEmployer(List<EmployerProfileVO> employerVO)throws MyJobKartException;
	
	EmployerVO uploadRegesitraion(EmployerVO employerVO);
	
	EmployerLoginVO uploadLogin(EmployerLoginVO employerloginVO);
	
	EmployerProfileVO uploadProfile(EmployerProfileVO employerProfileVO);
	
	long addNewCompany(CompanyVO companyVO);
	
	List<EntityBO> retrievemail();
	
	List<WalkinBO> retriveAllWalkin(WalkinBO walkinBO) throws MyJobKartException;
	
}
