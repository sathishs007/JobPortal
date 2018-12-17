package com.myjobkart.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateError;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myjobkart.bo.AdminLoginBO;
import com.myjobkart.bo.ContactBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.FeedbackBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.NewsLetterBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.dao.AdminDAO;
import com.myjobkart.dao.EmployerDAO;
import com.myjobkart.dao.JobSeekerDAO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.model.EmailModel;
import com.myjobkart.utils.ErrorCodes;
import com.myjobkart.utils.SendEmailServiceImpl;
import com.myjobkart.utils.SendMail;
import com.myjobkart.utils.SuccessMsg;
import com.myjobkart.vo.AdminLoginVO;
import com.myjobkart.vo.BODTO;
import com.myjobkart.vo.CompaniesVO;
import com.myjobkart.vo.CompanyVO;
import com.myjobkart.vo.ContactVO;
import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerProductServiceVO;
import com.myjobkart.vo.EmployerProfileVO;
import com.myjobkart.vo.EmployerVO;
import com.myjobkart.vo.EntrolledSeviceVO;
import com.myjobkart.vo.FeedbackVO;
import com.myjobkart.vo.IndustryVO;
import com.myjobkart.vo.JobseekerEducationVO;
import com.myjobkart.vo.JobseekerProfessionalVO;
import com.myjobkart.vo.JobseekerProfileVO;
import com.myjobkart.vo.NewsLetterVO;
import com.myjobkart.vo.ProductVO;
import com.paypal.api.payments.Payment;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by :
 * Sathishkumar.s Description : JobSeekerServiceImpl Class is a Class which is
 * responsible for access the data from Controller then convert it into
 * persistent Object then sent it into DAO class. Reviewed by :
 * 
 * 
 */

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService<AdminLoginBO> {

	static final JLogger LOGGER = JLogger.getLogger(AdminServiceImpl.class);
	// DAo layer annotations
	@Autowired
	private AdminDAO adminDAO;
	static boolean isApproval = true;
	EmailModel model = new EmailModel();

	@Autowired
	private SendEmailServiceImpl emailManager;

	@Autowired
	private JobSeekerDAO jobSeekerDAO;
	
	@Autowired
	private EmployerDAO employerDAO;

	@Override
	public AdminLoginBO authendicate(AdminLoginBO adminLoginBO)
			throws MyJobKartException {
		AdminServiceImpl.LOGGER.entry();

		final AdminLoginBO adminLogin = new AdminLoginBO();

		final AdminLoginVO adminLoginVO = this.adminDAO.authendicate(
				"emailAddress", adminLoginBO.getEmailAddress());

		if (null != adminLoginVO
				&& adminLoginVO.getPassword()
				.equals(adminLoginBO.getPassword())) {

			adminLogin.setActive(true);
			BeanUtils.copyProperties(adminLoginVO, adminLogin);

		} else {
			adminLogin.setActive(false);
		}
		return adminLogin;
	}

	@Override
	public AdminLoginBO create(AdminLoginBO entity) throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(List<BODTO<AdminLoginBO>> list)
			throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<AdminLoginBO> entityList)
			throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AdminLoginBO entity) throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(List<BODTO<AdminLoginBO>> list)
			throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public AdminLoginBO findByCriteria(AdminLoginBO entity)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminLoginBO findByParam(String entityParam, String entityParamValue)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminLoginBO> findByDate(Date fDate, Date tDate)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminLoginBO findById(Long long1) throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobPostBO reteriveAllJoBpost() {
		AdminServiceImpl.LOGGER.entry();
		List<JobPostBO> jobPostList = new ArrayList<JobPostBO>();
		final JobPostBO jobPostBO = new JobPostBO();
		try {
			jobPostList = this.adminDAO.reteriveAllJoBpost();

			if (null != jobPostList && !jobPostList.isEmpty()) {

				jobPostBO.setJobPostList(jobPostList);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return jobPostBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.service.AdminService#createProduct(com.jobtrolley.controller
	 * .ProductBO)
	 */
	@Override
	public ProductBO createProduct(ProductBO productBO)
			throws MyJobKartException {
		AdminServiceImpl.LOGGER.entry();

		ProductVO productVO = new ProductVO();

		try {

			BeanUtils.copyProperties(productBO, productVO);
			productVO = adminDAO.createProduct(productVO);

			BeanUtils.copyProperties(productVO, productBO);
			productBO.setResponse(SuccessMsg.REG_SUCCESS);
		} catch (final MyJobKartException jbe) {
			productBO.setErrorCode(ErrorCodes.JB_REG_FAIL);
			productBO.setErrorMessage(ErrorCodes.JB_REG_FAIL_MSG);
			if (AdminServiceImpl.LOGGER.isDebugEnabled()) {
				AdminServiceImpl.LOGGER.debug(ErrorCodes.JB_REG_FAIL_MSG + jbe);
			}
		}
		AdminServiceImpl.LOGGER.exit();
		return productBO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jobtrolley.service.AdminService#viewProduct()
	 */
	@Override
	public ProductBO viewProduct() {
		// TODO Auto-generated method stub

		ProductBO productBO = new ProductBO();
		try {
			List<ProductBO> registeredList = adminDAO.viewProduct();

			if (null != registeredList && !registeredList.isEmpty()) {

				productBO.setRegisteredList(registeredList);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productBO;
	}
	
	@Override
	public boolean productAuthentication(ProductBO productBO) {
		// TODO Auto-generated method stub
       boolean productStatus = false;
		try {
			productStatus = adminDAO.procutAuthentication(productBO);

			if (productStatus) {

				productStatus = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productStatus;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.service.AdminService#productActivation(com.jobtrolley.
	 * bo.ProductBO)
	 */
	@Override
	public boolean productActivation(ProductBO productBO) {
		// TODO Auto-generated method stub
		ProductVO productVO = new ProductVO();
		boolean result = false;
		try {
			if (0 != productBO.getProductId()) {
				productVO.setProductId(productBO.getProductId());
				productVO.setIsActive(productBO.getIsActive());
				result = adminDAO.procutActivation(productVO);
				if (result) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.service.AdminService#productUpdate(com.jobtrolley.bo.ProductBO
	 * )
	 */
	@Override
	public ProductBO productUpdate(ProductBO productBO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		AdminServiceImpl.LOGGER.entry();
		ProductVO productVO = new ProductVO();
		try {
			BeanUtils.copyProperties(productBO, productVO);
			int result = adminDAO.productUpdate(productVO);
			if (0 != result) {
				productBO.setResponse(SuccessMsg.UPDATE_SUCCESS);
			}
		} catch (MyJobKartException je) {
			je.printStackTrace();
			if (AdminServiceImpl.LOGGER.isDebugEnabled()) {
				AdminServiceImpl.LOGGER.debug(ErrorCodes.UPDATE_FAIL, je);
			}
			productBO.setErrorCode(je.getErrorCode());
			productBO.setErrorMessage(je.getErrorMessage());
		} finally {
			AdminServiceImpl.LOGGER.exit();
		}
		return productBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.service.AdminService#deleteProduct(com.jobtrolley.bo.ProductBO
	 * )
	 */
	@Override
	public ProductBO deleteProduct(ProductBO productBO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		AdminServiceImpl.LOGGER.entry();
		try {
			ProductVO productVO = new ProductVO();
			productVO.setIsDeleted(productBO.getIsDeleted());
			productVO.setProductId(productBO.getProductId());
			productVO.setModifiedBy(productBO.getModifiedBy());
			int reuslt = adminDAO.deleteProduct(productVO);
			if (0 != reuslt) {
				productBO.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (MyJobKartException je) {
			je.printStackTrace();
			if (AdminServiceImpl.LOGGER.isDebugEnabled()) {
				AdminServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL, je);
			}
			productBO.setErrorCode(je.getErrorCode());
			productBO.setErrorMessage(je.getErrorMessage());

		}
		return productBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.AdminService#contactBo(com.myjobkart.bo.ContactBO)
	 */
	@Override
	public ContactBO contactBo(ContactBO contact) {
		// TODO Auto-generated method stub
		boolean loginValidated = false;
		ContactVO contactVO = new ContactVO();
		contactVO = AdminServiceImpl.preparableBOtoVO(contact);
		contactVO = adminDAO.contactVO(contactVO);

		if (null != contactVO) {

			if (AdminServiceImpl.isApproval) {
				this.sendContactEmail(contactVO);
			}
		}

		return contact;

	}

	private void sendContactEmail(ContactVO contactVO) {
		try {
			final SendMail sendMail = new SendMail();

			final String toaddress = contactVO.getemail();
			final String subject = "Myjobkart:Contact Details!";
			String bodycontent = "Contact";
			model.setFirstname(contactVO.getName());
			model.setEmail(contactVO.getemail());
			model.setPhoneno(contactVO.getPhoneno());
			model.setSubject(contactVO.getsubject());
			model.setMessage(contactVO.getmessage());
			emailManager.sendEmail(toaddress, subject, bodycontent, model);
		} catch (final Exception ex) {
			AdminServiceImpl.LOGGER.debug(ex,
					"Client Email to the Customer Failed!");
		}
	}

	private static ContactVO preparableBOtoVO(ContactBO contact) {
		// TODO Auto-generated method stub
		ContactVO contactVO = new ContactVO();
		contactVO.setemail(contact.getemail());
		contactVO.setsubject(contact.getsubject());
		contactVO.setmessage(contact.getmessage());
		contactVO.setName(contact.getname());
		contactVO.setPhoneno(contact.getphoneno());
		contactVO.setIsDelete(false);
		return contactVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.AdminService#retrieveUserConatctDetails()
	 */
	@Override
	public List<ContactBO> retrieveUserConatctDetails(ContactBO contact) {
		// TODO Auto-generated method stub
		List<ContactBO> contactList = new ArrayList<ContactBO>();
		ContactBO contactBO = new ContactBO();
		contactList = adminDAO.retrieveUserConatctDetails(contact);

		if (null != contactList && !contactList.isEmpty()) {

			contactBO.setUserList(contactList);
		}
		return contactList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.AdminService#deleteContact(com.myjobkart.bo.ContactBO
	 * )
	 */
	@SuppressWarnings("unused")
	@Override
	public ContactBO deleteContact(ContactBO contactBO) {
		// TODO Auto-generated method stub
		try {
			ContactVO contactVO = new ContactVO();
			contactVO.setid((int) contactBO.getId());
			contactVO.setIsDelete(contactBO.getIsDelete());
			final int result = adminDAO.deleteContact(contactVO);

		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return contactBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.AdminService#retriveFeedbackDetails()
	 */
	@Override
	public List<FeedbackBO> retriveFeedbackDetails(FeedbackBO feedback) {
		// TODO Auto-generated method stub
		List<FeedbackBO> feedbackList = new ArrayList<FeedbackBO>();
		FeedbackBO feedbackBO = new FeedbackBO();
		feedbackList = adminDAO.retrievefeedbackDetails(feedback);

		if (null != feedbackList && !feedbackList.isEmpty()) {

			feedbackBO.setFeedbackList(feedbackList);
		}
		return feedbackList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.AdminService#deleteFeedback(com.myjobkart.bo.FeedbackBO
	 * )
	 */
	@SuppressWarnings("unused")
	@Override
	public FeedbackBO deleteFeedback(FeedbackBO feedbackBO) {
		// TODO Auto-generated method stub
		try {
			FeedbackVO feedbackVO = new FeedbackVO();
			feedbackVO.setId(feedbackBO.getId());
			feedbackVO.setIsDelete(feedbackBO.getIsDelete());
			final int result = adminDAO.deleteFeedback(feedbackVO);

		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return feedbackBO;
	}

	@Override
	public Map<String, List<EmployerProfileVO>> retrieveCompanyName() {
		return adminDAO.retrieveCompanyName();
	}
	
	@Override
	public Map<String, List<String>> retrieveEntityList(String Value) {
		return adminDAO.retrieveEntityList(Value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.AdminService#retrieveExperience()
	 */
	@Override
	public Map<String, List<String>> retrieveExperience() {
		// TODO Auto-generated method stub
		return adminDAO.retrieveExperience();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.AdminService#newsLetterCreate(com.myjobkart.bo.
	 * NewsLetterBO)
	 */
	@Override
	public NewsLetterBO newsLetterCreate(NewsLetterBO bo) {

		NewsLetterVO vo = new NewsLetterVO();
		try {
			vo.setEmailId(bo.getEmailId());
			vo.setIsdeleted(true);
			NewsLetterVO newsVO = (NewsLetterVO) adminDAO.newsLetterCreate(vo);

		} catch (Exception jbe) {

		}

		return bo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.AdminService#findByEmail(java.lang.String)
	 */
	@Override
	public boolean findByEmail(String emailId) {
		try {
			// TODO Auto-generated method stub
			LOGGER.entry();
			NewsLetterVO newsLetterRegister = new NewsLetterVO();
			newsLetterRegister = adminDAO.findByParams("emailId", emailId);
			if (newsLetterRegister != null) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exit();
		return false;
	}

	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#uploadFiles(com.myjobkart.bo.UploadFile)
	 */
	@Override
	public EntityBO createCompany(EntityBO uploadFiles) throws MyJobKartException {
		// TODO Auto-generated method stub

		LOGGER.entry();
		boolean uploadCompanies = false;

		EntityBO companyEntity = new EntityBO(); 

		try{

			ArrayList<EntityBO> newCompantList = new ArrayList<EntityBO>();
			ArrayList<EntityBO> duplicateCompanyList = new ArrayList<EntityBO>();

			List<CompanyVO> duplicateList = new ArrayList<CompanyVO>(); 	

			//Retrieved all CompanyList
			List<String> existingCompanyList = adminDAO.reteriveCompany();

			List<String> companyList = uploadFiles.getEntityName();

			//Validate the companies list whether already exist in the database orr not.
			for(String uCompany:companyList){

				String[] emailsplit=uCompany.split(":");
				String companyName =emailsplit[0];
				String email = null;
				if(emailsplit.length >1){
					email=emailsplit[1];
				}
				//find duplicate company to the List	
				boolean exist=existingCompanyList.contains(companyName);

				CompanyVO companiesVO=new CompanyVO();
				EntityBO entity = new EntityBO();
				if(!exist && !emailsplit[0].isEmpty() ){

					companiesVO.setCompaniesName(companyName);
					companiesVO.setEmail(email);
					companyName = companiesVO.getCompaniesName();
					duplicateList.add(companiesVO);
					entity.setCompanyName(companyName);
					entity.setEmail(email);
					newCompantList.add(entity);
					companyEntity.setNewEntityList(newCompantList);		
				}
				else{
					// duplicate Company set the EntityBO
					entity.setCompanyName(companyName);
					entity.setEmail(email);
					duplicateCompanyList.add(entity);
					companyEntity.setExistingEntityList(duplicateCompanyList);		

					companyEntity.setDuplicateCompanySize(duplicateCompanyList.size());

				}

			}

			//list send to adminDAO to store in company table
			if(duplicateList.size()>0){
				uploadCompanies = this.adminDAO.createCompany(duplicateList);	
			}


			if(uploadCompanies){

				companyEntity.setInfoStatus(uploadCompanies);
				companyEntity.setResponse("Successfully Created");
			}else{
				companyEntity.setInfoStatus(uploadCompanies);
				companyEntity.setResponse("Record is not Updated");
			}
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Company Entity Failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Service Company Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return companyEntity;

	}


	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#reteriveIndustry()
	 */
	@Override
	public List<String> reteriveIndustry() throws MyJobKartException {

		LOGGER.entry();
		// reterive data from Industry table 
		List<String> uploadFileList = new ArrayList<String>();
		try{
			uploadFileList=this.adminDAO.reteriveIndustry();
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Reterive Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Reterive Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return uploadFileList;
	}



	@Override
	public List<String> reteriveCompany() throws MyJobKartException {
		// TODO Auto-generated method stub
		// reterive data from Company table
		LOGGER.entry();

		List<String> uploadFileList = new ArrayList<String>();
		try{
			uploadFileList=this.adminDAO.reteriveCompany();
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Reterive Company Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Reterive Company Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return uploadFileList;
	}


	@Override
	public EntityBO createIndustry(EntityBO uploadFiles)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		LOGGER.entry();
		boolean uploadCompanies = false;
		EntityBO companyEntity = new EntityBO(); 
		try{
			ArrayList<EntityBO> newCompantList = new ArrayList<EntityBO>();
			ArrayList<EntityBO> duplicateCompanyList = new ArrayList<EntityBO>();

			List<IndustryVO> duplicateList = new ArrayList<IndustryVO>(); 	
			List<String> existingCompanyList=this.adminDAO.reteriveIndustry();
			List<String> companyList =uploadFiles.getEntityName();
			//Validate the companies list whether already exist in the database orr not.
			for(String uCompany:companyList){
				String[] emailsplit=uCompany.split(":");
				String companyName =emailsplit[0];
				String email = null;
				if(emailsplit.length >1){

					email=emailsplit[1];

				}


				EntityBO entity = new EntityBO();
				//find duplicate company to the List	
				boolean exist=existingCompanyList.contains(companyName);
				IndustryVO industryVO=new IndustryVO(); 
				if(!exist && !emailsplit[0].isEmpty()){
					industryVO.setIndustryName(companyName);
					industryVO.setEmail(email);
					duplicateList.add(industryVO);

					entity.setCompanyName(companyName);
					entity.setEmail(email);
					newCompantList.add(entity);
					companyEntity.setNewEntityList(newCompantList);
				}
				else{

					entity.setCompanyName(companyName);
					entity.setEmail(email);
					duplicateCompanyList.add(entity);
					companyEntity.setExistingEntityList(duplicateCompanyList);		
					companyEntity.setDuplicateCompanySize(duplicateCompanyList.size());

				}
			}

			if(duplicateList.size()>0){

				uploadCompanies = this.adminDAO.createIndustry(duplicateList);	
			}

			if(uploadCompanies){

				companyEntity.setInfoStatus(uploadCompanies);
				companyEntity.setResponse("Successfully Created");
			}else{
				companyEntity.setInfoStatus(uploadCompanies);
				companyEntity.setResponse("Record is not Updated");
			}

		}catch(Exception exception ){

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Create Indusrty  Entity Failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Service Create Industry Entity Failed" + exception.getMessage());	
		}	LOGGER.exit();
		return companyEntity;


	}

	@Override
	public List<EntityBO> adminCompanyEntityView() throws MyJobKartException {
		// TODO Auto-generated method stub
		LOGGER.entry();
		List<EntityBO> adminCompanyList = null;
		try{
			adminCompanyList = adminDAO.adminCompanyEntityView();
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Reterive Company  Entity View Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Reterive Company Entity View Failed" + exception.getMessage());
		}
		LOGGER.exit();	
		return adminCompanyList;
	}

	@Override
	public long uploadEmployer(List<EmployerProfileBO> uploadEmployerList)throws MyJobKartException {

		long uploadEmployerId = 0;
		long employerLoginId = 0;
		try {
			EmployerProfileVO employerProfileVO;
			EmployerLoginVO employerLoginVO;
			EmployerVO employerVO = null; 
			CompanyVO companyVO = new CompanyVO();
			if (null != uploadEmployerList && !uploadEmployerList.isEmpty()) { 

				//Employer Register
				for (EmployerProfileBO employerBO : uploadEmployerList) {
					uploadEmployerId = 0;
					employerVO = new EmployerVO();
					employerLoginVO = new EmployerLoginVO();
					employerProfileVO = new EmployerProfileVO();
					employerVO.setFirstName(employerBO.getFirstName());
					employerVO.setLastName(employerBO.getLastName());
					employerVO.setEmailAddress(employerBO.getEmailId());
					employerVO.setConfirmEmailAddress(employerBO.getConfirmEmail());
					employerVO.setContactNumber(employerBO.getContactNo());
					employerVO.setMobileNumber(employerBO.getMobileNo());
					employerVO.setCompanyName(employerBO.getCompanyName());
					employerVO.setWebSite(employerBO.getCompanyWebsite());
					employerVO.setCreatedBy(employerBO.getCreatedBy());
					employerVO.setPassword(employerBO.getPassword());
					employerVO.setConfirmPassword(employerBO.getConfirmPassword());
					employerVO.setTermsConditionsAgreed(true);
					employerVO.setIsActive(true);
					employerVO.setIsDeleted(true);
					companyVO.setCompaniesId(employerBO.getCompanyId());
					employerVO.setCompanyVO(companyVO);
					employerVO.setCompanyLogo(employerBO.getCompanyLogo());
					employerVO = adminDAO.uploadRegesitraion(employerVO);

					if(employerVO.getId() !=0){
						employerLoginVO.setEmployerRegistration(employerVO);	
						employerLoginVO.setEmailAddress(employerBO.getEmailId());
						employerLoginVO.setPassword(employerBO.getPassword());
						employerLoginVO.setConfirmPassword(employerBO.getConfirmPassword());
						employerLoginVO.setCreatedBy(employerBO.getCreatedBy());
						employerLoginVO.setActive(true);
						employerLoginVO = adminDAO.uploadLogin(employerLoginVO);
					}

					if(employerLoginVO.getId() != 0){
						employerProfileVO.setEmployerLogin(employerLoginVO);
						employerProfileVO.setEmployerRegistion(employerVO);
						employerProfileVO.setFirstName(employerBO.getFirstName());
						employerProfileVO.setLastName(employerBO.getLastName());
						employerProfileVO.setEmailId(employerBO.getEmailId());
						employerProfileVO.setContactNo(employerBO.getContactNo());
						employerProfileVO.setMobileNo(employerBO.getMobileNo());
						employerProfileVO.setCompanyName(employerBO.getCompanyName());
						employerProfileVO.setCompanyWebsite(employerBO.getCompanyWebsite());
						employerProfileVO.setAddressLine1(employerBO.getAddressLine1());
						employerProfileVO.setCreatedBy(employerBO.getCreatedBy());
						employerProfileVO.setIsActive(true);
						employerProfileVO.setIsDelete(true);
						companyVO.setCompaniesId(employerBO.getCompanyId());
						employerProfileVO.setCompanyID(companyVO);
						employerProfileVO.setCompanyType(employerBO.getIndustryType());
						employerProfileVO.setIndustryType("company");
						employerProfileVO.setCompanyLogo(employerBO.getCompanyLogo());
						employerProfileVO.setProductEnrolled("trail");
						
						employerProfileVO = adminDAO.uploadProfile(employerProfileVO);
						if(employerProfileVO.getProfileId() !=0){

                           // Product Trail Version Updated
							if (employerProfileVO.getProductEnrolled().equals("trail")) {
								final Calendar cal1 = new GregorianCalendar();
								final Calendar cal2 = new GregorianCalendar();
								PaymentBO payments = new PaymentBO();
								payments.setTotalMonth(1);
								payments.setTotalcost(0);
								payments.setProductType("trail");
								payments.setName("trail");
								payments.setPayId(employerProfileVO.getEmployerLogin().getId());
								payments.setIsDeleted(true);

								final int month = payments.getTotalMonth();
								final EntrolledSeviceVO entrolledSevice = new EntrolledSeviceVO();
								BeanUtils.copyProperties(payments, entrolledSevice);
								final Date validfrom = new Date();
								final Calendar cal = Calendar.getInstance();
								cal.add(Calendar.MONTH, month);
								final Date date = cal.getTime();

								cal1.setTime(date);
								cal2.setTime(validfrom);
								final Date ed = cal1.getTime();
								final Date sd = cal2.getTime();
								final long diff = ed.getTime() - sd.getTime();
								final int totalDays = (int) (diff / (1000 * 24 * 60 * 60));

								entrolledSevice.setValidFrom(validfrom);
								entrolledSevice.setValidEnd(date);
								entrolledSevice.setProducType("Employer");
								entrolledSevice.setSelectProduct("trail");
								long entollId = employerDAO.addPayments(entrolledSevice);
								if(entollId != 0 ){
									final EmployerProductServiceVO employeerProductServiceVO = new EmployerProductServiceVO();
									employeerProductServiceVO.setProductType("employeer");
									employeerProductServiceVO.setProductCost(new BigDecimal(0));

									cal.add(Calendar.MONTH, 1);
									final Date cdate = cal.getTime();

									employeerProductServiceVO.setGracePeriod(cdate);
									EmployerLoginVO loginVO  = new EmployerLoginVO();
									loginVO.setEmailAddress(employerProfileVO.getEmailId());
									loginVO.setPassword(employerProfileVO.getEmployerLogin().getPassword());
									loginVO.setCreatedBy(employerProfileVO.getEmployerLogin().getId());
									loginVO.setModifiedBy(employerProfileVO.getEmployerLogin().getId());
									loginVO.setActive(false);
									EmployerVO register = new EmployerVO();
									register.setId(employerProfileVO.getEmployerRegistion().getId());
									loginVO.setEmployerRegistration(register);

									employerLoginId = this.employerDAO.employerActivation(
											loginVO, employeerProductServiceVO);
									
									
								}if(0 != employerLoginId){
									uploadEmployerId = 1;
								}else{
									uploadEmployerId = 0;
								}
							}
						}
					}

				}
			}

		} catch (final Exception jb) {
          jb.printStackTrace();
		}

		return uploadEmployerId;
	}


	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#getCompanyEntity(com.myjobkart.bo.EntityBO)
	 */
	@Override
	public EntityBO getCompanyEntity(EntityBO entityBO) throws MyJobKartException {
		// TODO Auto-generated method stub
		LOGGER.entry();

		EntityBO entity = new EntityBO();
		try{
			CompanyVO companyVO = new CompanyVO();
			companyVO.setCompaniesId(entityBO.getCompaniesId());
			companyVO= this.adminDAO.getCompanyEntity(companyVO);
			entity.setCompaniesId(companyVO.getCompaniesId());
			entity.setCompanyName(companyVO.getCompaniesName());
			entity.setEmail(companyVO.getEmail());
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Get Company Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Get Company Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#adminUpdateCompanyEntity(boolean)
	 */
	@Override
	public boolean adminUpdateCompanyEntity(EntityBO updateCompany) throws MyJobKartException {
		// TODO Auto-generated method stub
		LOGGER.entry();

		CompanyVO companyVO= new CompanyVO();
		try{

			companyVO.setCompaniesId(updateCompany.getCompaniesId());
			// get the Company Entity single Record object    
			companyVO= this.adminDAO.getCompanyEntity(companyVO);
			companyVO.setCompaniesName(updateCompany.getCompanyName());
			companyVO.setCompaniesId(updateCompany.getCompaniesId());
			companyVO.setEmail(updateCompany.getEmail());
			companyVO.setModified(updateCompany.getModified());
			companyVO.setModifiedBy(updateCompany.getModifiedBy());
			// update the Company Entity Record 
			long updateId =this.adminDAO.adminUpdateCompanyEntity(companyVO);

			if(updateId!=0){

				return true;

			}

		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Update Company Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Update Company Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return false;
	}

	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#adminDeleteCompanyEntity(com.myjobkart.bo.EntityBO)
	 */
	@Override
	public boolean adminDeleteCompanyEntity(EntityBO entityBO) throws MyJobKartException {
		LOGGER.entry();
		try{

			CompanyVO companyVO = new CompanyVO();
			companyVO.setCompaniesId(entityBO.getCompaniesId());
			companyVO.setModifiedBy(entityBO.getModifiedBy());
			companyVO.setModified(entityBO.getModified());
			companyVO.setIsDeleted(entityBO.getIsDeleted());
			// update Delete is set to Active 
			int updateToDelete = this.adminDAO.adminDeleteCompanyEntity(companyVO);

			if(updateToDelete!=0){
				entityBO.setResponse(SuccessMsg.DELETE_SUCCESS);
				return true;
			}
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Delete Company Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Delete Company Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return false;
	}


	@Override
	public List<EntityBO> adminIndustryEntityView() {

		List<EntityBO> industryList = null;
		try{
			// pull the all Industry Entity table record  	

			industryList = this.adminDAO.adminIndustryEntityView();
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Industry Entity View  Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Industry Entity View Failed" + exception.getMessage());
		}
		return industryList;
	}

	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#getIndustryEntity(com.myjobkart.bo.EntityBO)
	 */
	@Override
	public EntityBO getIndustryEntity(EntityBO entityBO) {
		LOGGER.entry();

		EntityBO entity = new EntityBO();
		try{
			IndustryVO industryVO = new IndustryVO();
			industryVO.setIndustryId(entityBO.getCompaniesId());
			industryVO= this.adminDAO.getIndustryEntity(industryVO);
			entity.setCompaniesId(industryVO.getIndustryId());
			entity.setCompanyName(industryVO.getIndustryName());
			entity.setEmail(industryVO.getEmail());
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service get Industry Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service  get Industry Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return entity;


	}

	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#adminUpdateIndutryEntity(com.myjobkart.bo.EntityBO)
	 */
	@Override
	public boolean adminUpdateIndutryEntity(EntityBO updateCompany) {

		LOGGER.entry();
		try{
			IndustryVO industryVO = new IndustryVO(); 

			industryVO.setIndustryId(updateCompany.getCompaniesId());
			//get the Single Record from IndustryVO
			industryVO= this.adminDAO.getIndustryEntity(industryVO);
			industryVO.setIndustryName(updateCompany.getCompanyName());
			industryVO.setIndustryId(updateCompany.getCompaniesId());
			industryVO.setEmail(updateCompany.getEmail());
			industryVO.setModified(updateCompany.getModified());
			industryVO.setModifiedBy(updateCompany.getModifiedBy());
			// update the IndustryVO 
			long updateId =this.adminDAO.adminUpdateIndustryEntity(industryVO);

			if(updateId!=0){

				return true;

			}
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service  Update Industry Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Update Industry Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return false;



	}

	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#adminDeleteIndustryEntity(com.myjobkart.bo.EntityBO)
	 */
	@Override
	public boolean adminDeleteIndustryEntity(EntityBO entityBO) throws MyJobKartException {
		LOGGER.entry();
		try{
			IndustryVO industryVO = new IndustryVO();
			industryVO.setIndustryId(entityBO.getCompaniesId());
			industryVO.setModifiedBy(entityBO.getModifiedBy());
			industryVO.setModified(entityBO.getModified());
			industryVO.setIsDeleted(entityBO.getIsDeleted());
			// update IndustryVO Record isDelete column to active
			int updateToDelete = this.adminDAO.adminDeleteIndustryEntity(industryVO);

			if(updateToDelete!=0){
				entityBO.setResponse(SuccessMsg.DELETE_SUCCESS);
				return true;
			}
		}catch(Exception exception){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Delete Industry Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("Admin Service Delete Industry Entity Failed" + exception.getMessage());
		}
		LOGGER.exit();
		return false;
	}

	/* (non-Javadoc)
	 * @see com.myjobkart.service.AdminService#addNewCompany(java.lang.String)
	 */
	@Override
	public long addNewCompany(String companyName) {
		try{
			CompanyVO companyVO = new CompanyVO();
			companyVO.setCompaniesName(companyName);
			long id = adminDAO.addNewCompany(companyVO);
			return id;
		}catch(Exception ex){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Service Add New Company has Failed:"
						+ ex.getMessage());

			}
			LOGGER.info("Admin Service Add New Company has Failed" + ex.getMessage());
		}
		return 0;
	}

	@Override
	public List<EntityBO> retrievemail() {
		return adminDAO.retrievemail();
	}

	@Override
	public boolean setemployerinvitation(List<EntityBO> entityBOList) {
		boolean status = false;

		try {
			if (null != entityBOList && !entityBOList.isEmpty()) {
				for (EntityBO bolist : entityBOList) {
					String toaddress = bolist.getEmail();
					String bodyContent = "Empoyer_invitation";
					String subject = "Myjobkart: Employer Invitation!";

					status = emailManager.sendMailid(toaddress, subject,
							bodyContent, bolist);
					if (status) {
						adminDAO.setemployerinvitation(bolist.getId());

					}
				}
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();

		}
		return status;
	}
	
	@Override
	public List<WalkinBO> retriveAllwalkins(WalkinBO walkinBO) throws MyJobKartException {
		return adminDAO.retriveAllWalkin(walkinBO);
	}
	
	@Override
	public List<EntityBO> retrieveIndustryList() {
		JobSeekerServiceImpl.LOGGER.entry();
		List<EntityBO> entityBO = new ArrayList<EntityBO>();
		try {
			entityBO = adminDAO.retrieveIndustryList();
		} catch (final Exception e) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return entityBO;
	}

}
