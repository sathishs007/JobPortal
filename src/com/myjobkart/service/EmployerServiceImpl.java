package com.myjobkart.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjobkart.bo.BookingBO;
import com.myjobkart.vo.BookingVO;
import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.BannerPostBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.ContactBO;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EmployerSubuserBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.FeedbackBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobseekerActivityBO;
import com.myjobkart.bo.JobseekerBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.SaveCandidateBO;
import com.myjobkart.bo.ShortListCandidate;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.controller.EmployerController;
import com.myjobkart.dao.EmployerDAO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.model.EmailModel;
import com.myjobkart.service.JobtrolleyResourceBundle;
import com.myjobkart.utils.ErrorCodes;
import com.myjobkart.utils.MyjobkartResourceBundle;
import com.myjobkart.utils.SendEmailServiceImpl;
import com.myjobkart.utils.SendMail;
import com.myjobkart.utils.SuccessMsg;
import com.myjobkart.vo.AppliedJobVO;
import com.myjobkart.vo.BODTO;
import com.myjobkart.vo.BannerPostVO;
import com.myjobkart.vo.CompaniesVO;
import com.myjobkart.vo.CompanyVO;
import com.myjobkart.vo.ContactVO;
import com.myjobkart.vo.EmployerActivityVO;
import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerProductServiceVO;
import com.myjobkart.vo.EmployerProfileVO;
import com.myjobkart.vo.EmployerSubuserVO;
import com.myjobkart.vo.EmployerVO;
import com.myjobkart.vo.EmployerjobAlertsVO;
import com.myjobkart.vo.EntrolledSeviceVO;
import com.myjobkart.vo.FeedbackVO;
import com.myjobkart.vo.JobPostVO;
import com.myjobkart.vo.JobseekerProfileVO;
import com.myjobkart.vo.JobseekerVO;
import com.myjobkart.vo.ProductVO;
import com.myjobkart.vo.SaveCandidateVO;
import com.myjobkart.vo.ShortListVO;
import com.myjobkart.vo.WalkinVO;
import com.paypal.api.payments.Payment;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Aravindhan
 * Description : This is the service layer which is used to do the business
 * logics and pass the correspondent objects to the DAO layer. Reviewed by :
 * 
 */

@Service("employerService")
public class EmployerServiceImpl implements EmployerService<EmployerBO> {

	private static final JLogger LOGGER = JLogger
			.getLogger(EmployerServiceImpl.class);

	@Autowired
	private EmployerDAO employerDAO;
	EmailModel model = new EmailModel();
	private static boolean isApproval = true;
	JobtrolleyResourceBundle jobtrolleyResourceBundle;
	@Autowired
	private SendEmailServiceImpl emailManager;

	private void sendActivationEmail(EmployerBO employerBO) {
		try {
			final SendMail sendMail = new SendMail();

			final String toaddress = employerBO.getEmailAddress();
			final String subject = "Myjobkart:Registration Confirmation!";
			// final String website=JobtrolleyResourceBundle.bundle("website");
			String url = JobtrolleyResourceBundle
					.getValue("EmployerActivation");
			String website = JobtrolleyResourceBundle.getValue("website");
			String bodycontent = "accountActivationSuccess";
			// model.setWebSite(website);
			model.setUrl(url);
			model.setWebSite(website);
			model.setFirstname(employerBO.getFirstName());
			model.setEmail(employerBO.getEmailAddress());
			emailManager.sendEmail(toaddress, subject, bodycontent, model);

		} catch (final Exception ex) {
			EmployerServiceImpl.LOGGER.debug(ex,
					"Registeration Email to the Customer Failed!");
		}
	}

	/**
	 * This method is used to do the corresponding business logic and for
	 * Employer Registration and pass the respective object to DAO layer to
	 * persist the Data in the database.
	 * 
	 * @param registrationBO
	 * @return registrationBO
	 * @throws MyJobKartException
	 */
	@Transactional
	@Override
	public EmployerBO create(EmployerBO employerBO) throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		EmployerVO employerVO = new EmployerVO();
		CompanyVO companyVO = new CompanyVO();
		EmployerActivityVO activityVO = new EmployerActivityVO();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			BeanUtils.copyProperties(employerBO, employerVO);
			employerVO.setIsActive(false);
			employerVO.setIsDeleted(true);
			companyVO.setCompaniesId(employerBO.getCompanyId());
			employerVO.setCompanyVO(companyVO);
			employerVO = employerDAO.create(employerVO);

			if (null != employerVO) {
				if (EmployerServiceImpl.isApproval) {
					activityVO.setActivityDate(format.format(new Date()));
					activityVO.setEmployerVO(employerVO);
					activityVO.setCreated(new Date());
					activityVO.setCreatedBy(employerVO.getId());
					activityVO.setStatus("New Employer");

					long activityId = employerDAO.activity(activityVO);

					try {

						String str = JobtrolleyResourceBundle
								.getValue("EmployerChangePassword");
						String str1 = JobtrolleyResourceBundle
								.getValue("EmployerActivation");
						SendMail sendMail = new SendMail();
						String toaddress = employerBO.getEmailAddress();
						String subject = "Jobtrolley:Registration Confirmation!";

						if (null != employerVO) {
							if (EmployerServiceImpl.isApproval) {
								this.sendActivationEmail(employerBO);
							}
						}
					} catch (final Exception ex) {
						EmployerServiceImpl.LOGGER.debug(ex,
								"Registeration Email to the Customer Failed!");
					}

				}

			}

			BeanUtils.copyProperties(employerVO, employerBO);
			employerBO.setResponse(SuccessMsg.REG_SUCCESS);
		} catch (final MyJobKartException je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.EM_REG_FAIL + je);
			}
			employerBO.setErrorCode(je.getErrorCode());
			employerBO.setErrorMessage(je.getErrorMessage());
		}
		EmployerServiceImpl.LOGGER.exit();
		return employerBO;
	}

	@Override
	public EmployerBO updateEmployer(EmployerBO updateProfile)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();

		try {
			EmployerVO employerupdate = new EmployerVO();
			employerupdate.setId(updateProfile.getId());
			// BeanUtils.copyProperties(updateProfile, employerupdateProfile);
			EmployerVO employerUpdate = this.employerDAO
					.getEmployerReg(employerupdate);
			if (employerUpdate.getId() == updateProfile.getId()) {
				employerUpdate.setId(updateProfile.getId());
				employerUpdate.setEmailAddress(updateProfile.getEmailAddress());
				employerUpdate.setCompanyName(updateProfile.getCompanyName());
				employerUpdate.setFirstName(updateProfile.getFirstName());
				employerUpdate.setIsActive(updateProfile.getIsActive());
				employerUpdate.setCompanyType(updateProfile.getCompanyType());
				employerUpdate.setCreated(updateProfile.getCreated());
				employerUpdate.setCreatedBy(updateProfile.getCreatedBy());
				employerUpdate.setModifiedBy(updateProfile.getModifiedBy());
				employerUpdate.setContactNumber(updateProfile
						.getContactNumber());
				employerUpdate.setIndustryType(updateProfile.getIndustryType());
				employerUpdate.setLastName(updateProfile.getLastName());
				employerUpdate.setCompanyLogo(updateProfile.getCompanyLogo());
				employerUpdate.setMobileNumber(updateProfile.getMobileNumber());
				employerUpdate.setConfirmEmailAddress(updateProfile
						.getConfirmEmailAddress());
				CompanyVO companyVO = new CompanyVO();
				companyVO.setCompaniesId(updateProfile.getCompanyId());
				employerUpdate.setCompanyVO(companyVO);
				employerUpdate.setTermsConditionsAgreed(updateProfile
						.getTermsConditionsAgreed());
				employerUpdate.setWebSite(updateProfile.getWebSite());
				employerUpdate.setPassword(updateProfile.getPassword());
				employerUpdate.setConfirmPassword(updateProfile
						.getConfirmPassword());
				employerUpdate.setIsDeleted(updateProfile.getIsDeleted());
				employerUpdate.setVersion(updateProfile.getVersion());
				this.employerDAO.updateEmployer(employerUpdate);
			}

			updateProfile.setResponse(SuccessMsg.UPDATE_SUCCESS);
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.UPDATE_FAIL + je);
			}
			updateProfile.setErrorCode(je.getErrorCode());
			updateProfile.setErrorMessage(je.getErrorMessage());
		}

		return updateProfile;

	}

	public EmployerVO preparableupdateBOtoVO(EmployerBO updateProfile) {
		EmployerServiceImpl.LOGGER.entry();
		final EmployerVO employerupdate = new EmployerVO();
		try {
			employerupdate.setId(updateProfile.getId());
			employerupdate.setEmailAddress(updateProfile.getEmailAddress());
			employerupdate.setCompanyName(updateProfile.getCompanyName());
			employerupdate.setFirstName(updateProfile.getFirstName());
			employerupdate.setIsActive(updateProfile.getIsActive());
			employerupdate.setCompanyType(updateProfile.getCompanyType());
			employerupdate.setCreated(updateProfile.getCreated());
			employerupdate.setCreatedBy(updateProfile.getCreatedBy());
			employerupdate.setModifiedBy(updateProfile.getModifiedBy());
			employerupdate.setContactNumber(updateProfile.getContactNumber());
			employerupdate.setIndustryType(updateProfile.getIndustryType());
			employerupdate.setLastName(updateProfile.getLastName());
			employerupdate.setCompanyLogo(updateProfile.getCompanyLogo());
			employerupdate.setMobileNumber(updateProfile.getMobileNumber());
			employerupdate.setConfirmEmailAddress(updateProfile
					.getConfirmEmailAddress());
			CompanyVO companyVO = new CompanyVO();
			companyVO.setCompaniesId(updateProfile.getCompanyId());
			employerupdate.setCompanyVO(companyVO);
			employerupdate.setTermsConditionsAgreed(updateProfile
					.getTermsConditionsAgreed());
			employerupdate.setWebSite(updateProfile.getWebSite());
			employerupdate.setPassword(updateProfile.getPassword());
			employerupdate.setConfirmPassword(updateProfile
					.getConfirmPassword());
			employerupdate.setIsDeleted(updateProfile.getIsDeleted());
			employerupdate.setVersion(updateProfile.getVersion());
		} catch (final NullPointerException e) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return employerupdate;

	}

	@Override
	public void delete(Long id) throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	/**
	 * This method used to search and find mail id.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws MyJobKartException
	 */
	@Override
	public boolean findByEmail(String emailAddress) throws MyJobKartException {

		try {
			
			
			final EmployerVO employerVO = this.employerDAO.findByEmail("emailAddress", emailAddress);
					
			if (employerVO != null) {
				return true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean employerActivation(String inputParam)
			throws MyJobKartException {
		boolean isActivation = false;
		try {
			/*final EmployerVO employerVO = this.employerDAO.findByParam(
					"emailAddress", inputParam);*/
					
					final EmployerVO employerVO = this.employerDAO.getByEmployerEmail(inputParam);

			if (employerVO != null) {
				EmployerLoginVO	employerLoginVO = new EmployerLoginVO();
				employerLoginVO.setActive(true);
				employerLoginVO.setConfirmPassword(employerVO
						.getConfirmPassword());
				employerLoginVO.setPassword(employerVO.getPassword());
				employerLoginVO.setEmailAddress(employerVO.getEmailAddress());

				final EmployerVO employervo = new EmployerVO();
				employervo.setId(employerVO.getId());
				employerLoginVO.setCreatedBy(employerVO.getId());
				employerLoginVO.setModifiedBy(employerVO.getId());
				employerLoginVO.setEmployerRegistration(employervo);

				final EmployerProductServiceVO employeerProductServiceVO = new EmployerProductServiceVO();
				employeerProductServiceVO.setProductType("employeer");
				employeerProductServiceVO.setProductCost(new BigDecimal(0));

				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, 1);
				final Date date = cal.getTime();

				employeerProductServiceVO.setGracePeriod(date);

				final long employerLoginId = this.employerDAO
						.employerActivation(employerLoginVO,
								employeerProductServiceVO);
				if (0 != employerLoginId) {
					isActivation = true;
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return isActivation;

	}

	@Override
	public List<EmployerBO> getAllEmployers() {
		EmployerServiceImpl.LOGGER.entry();
		EmployerBO registrationBO = null;
		List<EmployerVO> empList = null;
		final List<EmployerBO> empBOList = new ArrayList<EmployerBO>();
		try {
			empList = this.employerDAO.retrieveAll();
		} catch (final MyJobKartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (final EmployerVO employerRegistration : empList) {
			registrationBO = new EmployerBO();
			BeanUtils.copyProperties(employerRegistration, registrationBO);
			empBOList.add(registrationBO);
		}
		EmployerServiceImpl.LOGGER.exit();
		return empBOList;
	}

	@Override
	public List<JobPostBO> getAllJobPost() {
		EmployerServiceImpl.LOGGER.entry();
		List<JobPostBO> jobPostBOList = null;
		try {
			jobPostBOList = employerDAO.retrieveJobPosting();
		} catch (final MyJobKartException e) {
			e.printStackTrace();
		}
		EmployerServiceImpl.LOGGER.exit();
		return jobPostBOList;
	}

	@Override
	public List<EmployerProfileBO> retriveEmployerProfile() {
		EmployerServiceImpl.LOGGER.entry();
		List<EmployerProfileBO> profileList = null;
		try {
			profileList = this.employerDAO.retriveEmployerProfile();

		} catch (Exception je) {

			profileList = new ArrayList<EmployerProfileBO>();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}

		}

		EmployerServiceImpl.LOGGER.exit();
		return profileList;
	}

	@Override
	public List<String> getAllEmailList() {
		List<String> getAllEmailList = null;
		try {
			getAllEmailList = this.employerDAO.getAllEmailList();
		} catch (final Exception jb) {

			getAllEmailList = new ArrayList<String>();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}

		}
		return getAllEmailList;
	}

	@Override
	public void create(List<BODTO<EmployerBO>> list) throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<EmployerBO> entityList)
			throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(EmployerBO entity) throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(List<BODTO<EmployerBO>> list) throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public EmployerBO findByCriteria(EmployerBO entity)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployerBO findByParam(String entityParam, String entityParamValue)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployerBO> findByDate(Date fDate, Date tDate)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployerBO findById(Long long1) throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findByLogin(String emailAddress, String password) {
		return null;

	}

	@Override
	public EmployerLoginBO authendicate(EmployerLoginBO employerLoginBO)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		EmployerLoginBO employerLogin = null;

		try {
			employerLogin = new EmployerLoginBO();
			final EmployerLoginVO employerLoginVO = this.employerDAO
					.authendicate("emailAddress",
							employerLoginBO.getEmailAddress());

			if (null != employerLoginVO
					&& employerLoginVO.getPassword().equals(
							employerLoginBO.getPassword())) {

				BeanUtils.copyProperties(employerLoginVO, employerLogin);
				employerLogin.setIsActive(employerLoginVO.isActive());
				employerLogin.setRegisterId(employerLoginVO
						.getEmployerRegistration().getId());
				if(null != employerLoginVO.getEmployerSubuserVO() &&0 != employerLoginVO.getEmployerSubuserVO().getId()){
						
				employerLogin.setSubuserId(employerLoginVO
						.getEmployerSubuserVO().getId());
				}
				employerLogin.setPassword(employerLoginVO.getPassword());

			} else {
				employerLogin.setIsActive(false);
				BeanUtils.copyProperties(employerLoginVO, employerLogin);
			}
		} catch (final Exception je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return employerLogin;
	}

	@Override
	public boolean forgetAuthentication(EmployerLoginBO employerLoginBO)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		boolean loginReset = false;

		final EmployerLoginVO employerLoginVO = this.employerDAO.authendicate(
				"emailAddress", employerLoginBO.getEmailAddress());

		if (null != employerLoginVO) {

			try {
				@SuppressWarnings("static-access")
				String str = JobtrolleyResourceBundle
						.getValue("EmployerChangePassword");
				String website = JobtrolleyResourceBundle.getValue("website");
				SendMail sendMail = new SendMail();
				String toaddress = employerLoginBO.getEmailAddress();
				String subject = "MyJobkart:Change Your Password for MyJobkart";
				String bodycontent = "changePassword";
				model.setWebSite(website);
				model.setUrl(str);
				model.setEmail(employerLoginBO.getEmailAddress());
				emailManager.sendEmail(toaddress, subject, bodycontent, model);

				loginReset = true;
			} catch (Exception ex) {
				LOGGER.debug(ex,
						"Change Password Email to the Customer Failed!");
			}

		}

		return loginReset;
	}

	/**
	 * 
	 */
	@Override
	public boolean changeAuthendication(EmployerLoginBO changePassword) {
		EmployerServiceImpl.LOGGER.entry();
		boolean loginChanged = false;

		EmployerLoginVO employerLoginVO = new EmployerLoginVO();

		if (null != changePassword.getEmailAddress()
				&& null != changePassword.getPassword()
				&& null != changePassword.getConfirmPassword()) {
			BeanUtils.copyProperties(changePassword, employerLoginVO);
			employerLoginVO = this.employerDAO
					.changeAuthendication(employerLoginVO);
			if (null != employerLoginVO.getPassword()
					&& null != changePassword.getPassword()) {
				loginChanged = true;
			}
		}
		return loginChanged;
	}

	/**
	 * @throws SQLException
	 * 
	 */
	@Override
	public boolean createProfile(EmployerProfileBO profile) throws SQLException {
		EmployerServiceImpl.LOGGER.entry();
		boolean isProfile = false;

		try {
			EmployerProfileVO employerProfileVO = new EmployerProfileVO();
			EmployerVO employerVO = new EmployerVO();

			employerProfileVO = EmployerServiceImpl.preparableBOtoVO(profile);
			employerVO.setId(profile.getEmployerRegistion().getId());
			employerProfileVO.setEmployerRegistion(employerVO);
			CompanyVO companyVO = new CompanyVO();
			companyVO.setCompaniesId(profile.getCompanyId());
			employerProfileVO.setCompanyID(companyVO);

			final long profileId = this.employerDAO
					.createProfile(employerProfileVO);

			if (0 != profileId) {
				isProfile = true;
			}

		} catch (final BeansException jb) {
			jb.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return isProfile;
	}

	@Override
	public boolean saveProfile(SaveCandidateBO saveCandidateBO) {
		EmployerServiceImpl.LOGGER.entry();
		boolean isProfile = false;

		try {
			SaveCandidateVO saveCandidateVO = new SaveCandidateVO();

			saveCandidateVO = EmployerServiceImpl
					.savepreparableBOtoVO(saveCandidateBO);
			saveCandidateVO.setExperienceInYear(saveCandidateBO
					.getExperienceInYear());
			saveCandidateVO.setEmailId(saveCandidateBO.getEmailId());
			final long profileId = this.employerDAO
					.saveProfile(saveCandidateVO);
			if (0 != profileId) {
				isProfile = true;
			}

		} catch (final BeansException jb) {
			jb.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return isProfile;
	}

	/*
	 * (non-Javadoc) the Employer Registration table (em_registration) get one
	 * row object from EmployerVO class
	 * 
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#getEmployerRegistration(com.myjobkart
	 * .bo.EmployerLoginBO)
	 */

	@Override
	public EmployerBO getEmployerRegistration(EmployerLoginBO employerLogin) {
		// TODO Auto-generated method stub
		EmployerBO employerBO = new EmployerBO();
		EmployerVO employerVO = new EmployerVO();
		employerVO.setEmailAddress(employerLogin.getEmailAddress());
		employerVO.setId(employerLogin.getRegisterId());

		employerVO = employerDAO.getEmployerRegistraion(employerVO);
		employerBO.setId(employerVO.getId());
		employerBO.setFirstName(employerVO.getFirstName());
		employerBO.setLastName(employerVO.getLastName());
		employerBO.setEmailAddress(employerVO.getEmailAddress());
		employerBO.setMobileNumber(employerVO.getMobileNumber());
		employerBO.setContactNumber(employerVO.getContactNumber());
		employerBO.setPassword(employerVO.getPassword());
		employerBO.setConfirmPassword(employerVO.getConfirmPassword());
		employerBO.setConfirmEmailAddress(employerVO.getConfirmEmailAddress());
		employerBO.setIsActive(employerVO.getIsActive());
		employerBO.setIsDeleted(employerVO.getIsDeleted());
		employerBO.setCompanyName(employerVO.getCompanyName());
		employerBO.setWebSite(employerVO.getWebSite());
		employerBO.setCompanyId(employerVO.getCompanyVO().getCompaniesId());
		try {
			employerBO.setCompanyLogo(employerVO.getCompanyLogo().getBytes(1,
					(int) employerVO.getCompanyLogo().length()));
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BeanUtils.copyProperties(employerVO, employerBO);

		return employerBO;
	}

	@Override
	public EmployerProfileBO retriveEmployer(EmployerProfileBO reteriveprofile) {
		EmployerServiceImpl.LOGGER.entry();
		try {
			final EmployerProfileVO employerProfileVO = new EmployerProfileVO();
			EmployerVO employerVO = new EmployerVO();
			EmployerBO employerBO = new EmployerBO();
			if (null != reteriveprofile.getEmailId()) {
				employerProfileVO.setEmailId(reteriveprofile.getEmailId());
			} 
			if (0 != reteriveprofile.getCompanyId()) {
				CompanyVO companyVO = new CompanyVO();
				companyVO.setCompaniesId(reteriveprofile.getCompanyId());
				employerProfileVO.setCompanyID(companyVO);
			} 
			if (null != reteriveprofile.getEmployerRegistion()) {
				employerVO
						.setId(reteriveprofile.getEmployerRegistion().getId());
				employerProfileVO.setEmployerRegistion(employerVO);
			} 
			if (0 != reteriveprofile.getId()) {
				employerProfileVO.setProfileId(reteriveprofile.getId());
			}

			final EmployerProfileVO employerProfile = this.employerDAO
					.retriveEmployer(employerProfileVO);
			if (null != employerProfile) {
				reteriveprofile = EmployerServiceImpl
						.preparableVOtoBO(employerProfile);
				employerBO
						.setId(employerProfile.getEmployerRegistion().getId());
				reteriveprofile.setEmployerRegistion(employerBO);
				reteriveprofile.setCompanyId(employerProfile.getCompanyID()
						.getCompaniesId());
				if (null != employerProfile.getCompanyWebsite()) {
					reteriveprofile.setCompanyWebsite(employerProfile
							.getCompanyWebsite());
				}
				reteriveprofile.setIsActive(employerProfile.getIsActive());
				reteriveprofile.setId(employerProfile.getProfileId());
			} else {
				reteriveprofile = null;

			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return reteriveprofile;

	}

	@Override
	@Transactional
	public JobPostBO retriveEmployer(JobPostBO jobPostBO)
			throws SerialException, SQLException {
		EmployerServiceImpl.LOGGER.entry();


		// JobPostBO retriveProfileList = new JobPostBO();
		JobPostBO retriveProfileList = null;

		try {
			retriveProfileList = this.employerDAO.retriveEmployer(jobPostBO);

		} catch (final MyJobKartException e) {

			retriveProfileList = new JobPostBO();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}

			retriveProfileList.setErrorCode(e.getErrorCode());
			retriveProfileList.setErrorMessage(e.getErrorMessage());

		}
		EmployerServiceImpl.LOGGER.exit();
		return retriveProfileList;

	}

	@Transactional
	@Override
	public List<CompanyEntity> retrieveCompanyList() {
		EmployerServiceImpl.LOGGER.entry();
		// List<CompanyEntity> entityBO = new ArrayList<CompanyEntity>();
		List<CompanyEntity> entityBO = null;
		try {

			entityBO = this.employerDAO.retriveCompanyList();

		} catch (final Exception e) {

			entityBO = new ArrayList<CompanyEntity>();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return entityBO;

	}

	@Override
	public JobPostBO reteriveJobPost(JobPostBO appliedJobBO) {
		EmployerServiceImpl.LOGGER.entry();

		JobPostBO appliedBO = null;
		try {
			appliedBO = employerDAO.reteriveJobPost(appliedJobBO);

		} catch (Exception e) {

			appliedBO = new JobPostBO();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return appliedBO;

	}

	@Override
	public JobPostBO retrieveJobPost(JobPostBO jobPostBO) {
		EmployerServiceImpl.LOGGER.entry();
		try {
			final JobPostVO jobPostVO = new JobPostVO();
			if (0 != jobPostBO.getJobId()) {
				jobPostVO.setJobId(jobPostBO.getJobId());
			} else {
				jobPostVO.setCreatedBy(jobPostBO.getId());
			}
			if (null != jobPostBO.getSearchElement()) {
				jobPostVO.setJobTitle(jobPostBO.getSearchElement());
			}
			final List<JobPostBO> jobPostBOList = this.employerDAO
					.retrieveJobPosts(jobPostVO);
			if (jobPostBOList != null) {
				jobPostBO.setJobPostList(jobPostBOList);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return jobPostBO;
	}

	/**
	 * @throws SQLException
	 * 
	 */
	@Override
	public EmployerProfileBO updateProfile(EmployerProfileBO updateProfile)
			throws MyJobKartException, SQLException {
		EmployerServiceImpl.LOGGER.entry();

		try {
			EmployerProfileVO employerProfileVO = new EmployerProfileVO();
			employerProfileVO.setProfileId(updateProfile.getId());
			
			EmployerProfileVO employerProfile = this.employerDAO
					.retriveEmployer(employerProfileVO);
			if (null != employerProfile) {
				if(null != updateProfile.getAddressLine1()){
				employerProfile.setAddressLine1(updateProfile.getAddressLine1());
				}
				if(null != updateProfile.getCity()){
				employerProfile.setCity(updateProfile.getCity());
				}
				if(null != updateProfile.getCompanyName()){
				employerProfile.setCompanyName(updateProfile.getCompanyName());
				}
				if(null != updateProfile.getCompanyWebsite()){
				employerProfile.setCompanyWebsite(updateProfile.getCompanyWebsite());
				}
				if(null != updateProfile.getCompanyType()){
				employerProfile.setCompanyType(updateProfile.getCompanyType());
				}
				if(null != updateProfile.getCountry()){
				employerProfile.setContactNo(updateProfile.getContactNo());
				}
				if(null != updateProfile.getCountry()){
				employerProfile.setCountry(updateProfile.getCountry());
				}
				if(0 != updateProfile.getCreatedBy()){
				employerProfile.setCreatedBy(updateProfile.getCreatedBy());
				}
				if(null != updateProfile.getCurrentDesignation()){
				employerProfile.setCurrentDesignation(updateProfile.getCurrentDesignation());
				}
				if(0 != updateProfile.getDeleteBy()){
				employerProfile.setDeleteBy(updateProfile.getDeleteBy());
				}
				if(null != updateProfile.getCurrentRolesResponsibilities()){
				employerProfile.setCurrentRolesResponsibilities(updateProfile.getCurrentRolesResponsibilities());
				}
				if(null != updateProfile.getDeletedDate()){
				employerProfile.setDeletedDate(updateProfile.getDeletedDate());
				}if(null != updateProfile.getEmailId()){
				employerProfile.setEmailId(updateProfile.getEmailId());
				}
				if(null != updateProfile.getFirstName()){
				employerProfile.setFirstName(updateProfile.getFirstName());
				}
				if(null != updateProfile.getHiringPurpose()){
				employerProfile.setHiringPurpose(updateProfile.getHiringPurpose());
				}
				if(null != updateProfile.getIndustryType()){
				employerProfile.setIndustryType(updateProfile.getIndustryType());
				}
				if(null != updateProfile.getLastName()){
				employerProfile.setLastName(updateProfile.getLastName());
				}
				if(null != updateProfile.getMobileNo()){
				employerProfile.setMobileNo(updateProfile.getMobileNo());
				}
				if(0 != updateProfile.getModifiedBy()){
				employerProfile.setModifiedBy(updateProfile.getModifiedBy());
				}
				if(null != updateProfile.getPinCode()){
				employerProfile.setPinCode(updateProfile.getPinCode());
				}
				
				if(null != updateProfile.getProductEnrolled()){
				employerProfile.setProductEnrolled(updateProfile.getProductEnrolled());
				}
				if(null != updateProfile.getSecondaryEmail()){
				employerProfile.setSecondaryEmail(updateProfile.getSecondaryEmail());
				}
				if(null != updateProfile.getState()){
				employerProfile.setState(updateProfile.getState());
				}
						
				if (null != updateProfile.getCompanyLogo()) {
					employerProfile.setCompanyLogo(updateProfile
							.getCompanyLogo());
				}
				if(null != updateProfile.getCompanyProfile()){
				employerProfile.setCompanyProfile(updateProfile.getCompanyProfile());
				}
				if( 0 != updateProfile.getCompanyId()){
				CompanyVO companyVO = new CompanyVO();
				companyVO.setCompaniesId(updateProfile.getCompanyId());
				employerProfile.setCompanyID(companyVO);
				}
				this.employerDAO.updateProfile(employerProfile);
				updateProfile.setResponse(SuccessMsg.UPDATE_SUCCESS);

			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.UPDATE_FAIL + je);
			}
			updateProfile.setErrorCode(je.getErrorCode());
			updateProfile.setErrorMessage(je.getErrorMessage());
		}

		return updateProfile;

	}

	@Override
	public JobPostBO updateJobPost(JobPostBO jobPostBO)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		try {
			JobPostVO jobpost = new JobPostVO();
			jobpost.setJobId(jobPostBO.getId());

			JobPostVO jobPostVO = this.employerDAO.getJoppost(jobpost);
			if (jobPostVO.getJobId() == jobPostBO.getId()) {
				// EmployerVO employerVO =new EmployerVO();
				// employerVO.setId(jobPostBO.getEmployerRegisterID());
				// jobPostVO.setEmployerRegistionVO(employerVO);
				jobPostVO.setAddress(jobPostBO.getAddress());
				// jobPostVO.setCompanyName(jobPostBO.getCompanyName());
				jobPostVO.setContactNo(jobPostBO.getContactNo());
				jobPostVO.setContactPerson(jobPostBO.getContactPerson());
				jobPostVO.setCreatedBy(jobPostBO.getCreatedBy());
				jobPostVO.setCurrencyType(jobPostBO.getCurrencyType());
				// jobPostVO.setDeletedBy(jobPostBO.getDeletedBy());
				// jobPostVO.setDeletedDate(jobPostBO.getDeletedDate());
				// jobPostVO.setCompanyId(jobPostBO.getCompanyId());
				// jobPostVO.getEmployerLogin().setId(jobPostBO.getEmpId());
				jobPostVO.setFunctionArea(jobPostBO.getFunctionArea());
				// jobPostVO.setIndustryType(jobPostBO.getIndustryType());
				// jobPostVO.setIsActive(jobPostBO.getIsActive());
				// jobPostVO.setIsDeleted(jobPostBO.getIsDeleted());
				jobPostVO.setJobDescription(jobPostBO.getJobDescription());
				jobPostVO.setJobLocation(jobPostBO.getJobLocation());
				jobPostVO.setJobResponse(jobPostBO.getJobResponse());
				jobPostVO.setJobTitle(jobPostBO.getJobTitle());
				jobPostVO.setKeywords(jobPostBO.getKeywords());
				jobPostVO.setMaxExp(jobPostBO.getMaxExp());
				jobPostVO.setModifiedBy(jobPostBO.getModifiedBy());
				jobPostVO.setMaxSalary(jobPostBO.getMaxSalary());
				jobPostVO.setMinExp(jobPostBO.getMinExp());
				jobPostVO.setMinSalary(jobPostBO.getMinSalary());
				jobPostVO.setNoVacancies(jobPostBO.getNoVacancies());
				jobPostVO.setOtherSalaryDetails(jobPostBO
						.getOtherSalaryDetails());
				jobPostVO.setPgQualification(jobPostBO.getPgQualification());
				jobPostVO.setUgQualification(jobPostBO.getUgQualification());
				// jobPostVO.setVersion(jobPostBO.getVersion());
				jobPostVO.setPostedBy(jobPostBO.getPostedBy());
				jobPostVO.setStartDate(jobPostBO.getStartDate());
				jobPostVO.setEndDate(jobPostBO.getEndDate());

				this.employerDAO.updateJobPost(jobPostVO);
				jobPostBO.setResponse(SuccessMsg.UPDATE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.UPDATE_FAIL_MSG
						+ je);
			}
			jobPostBO.setErrorCode(je.getErrorCode());
			jobPostBO.setErrorMessage(je.getErrorMessage());
		}

		return jobPostBO;

	}

	@Override
	public EmployerProfileBO deleteProfile(EmployerProfileBO deleteProfile)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();

		try {
			final EmployerProfileVO employerProfileVO = new EmployerProfileVO();
			employerProfileVO.setIsDelete(deleteProfile.getIsDelete());
			employerProfileVO.setProfileId(deleteProfile.getId());
			employerProfileVO.setDeleteBy(deleteProfile.getDeleteBy());
			employerProfileVO.setDeletedDate(deleteProfile.getDeletedDate());
			employerProfileVO.setModifiedBy(deleteProfile.getModifiedBy());
			final int result = this.employerDAO
					.deleteProfile(employerProfileVO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteProfile.setErrorCode(je.getErrorCode());
			deleteProfile.setErrorMessage(je.getErrorMessage());
		}

		return deleteProfile;

	}

	@Override
	public SaveCandidateBO deleteProfile(SaveCandidateBO deleteProfile)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();

		try {
			final SaveCandidateVO saveCandidateVO = new SaveCandidateVO();
			saveCandidateVO.setIsDelete(deleteProfile.getIsDelete());
			saveCandidateVO.setSavecandidateid(deleteProfile.getId());
			saveCandidateVO.setDeleteBy(deleteProfile.getDeleteBy());
			saveCandidateVO.setDeletedDate(deleteProfile.getDeletedDate());
			saveCandidateVO.setModifiedBy(deleteProfile.getModifiedBy());
			final int result = this.employerDAO.deleteProfile(saveCandidateVO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteProfile.setErrorCode(je.getErrorCode());
			deleteProfile.setErrorMessage(je.getErrorMessage());
		}

		return deleteProfile;

	}

	@Override
	public AppliedJobBO deleteAppliedCandidate(AppliedJobBO deleteCandidate) {

		EmployerServiceImpl.LOGGER.entry();
		try {
			AppliedJobVO appliedJobVO = new AppliedJobVO();
			appliedJobVO.setApplicationid(deleteCandidate.getId());
			appliedJobVO.setModifiedBy(deleteCandidate.getModifiedBy());
			appliedJobVO.setDeletedBy(deleteCandidate.getDeletedBy());
			appliedJobVO.setDeletedDate(deleteCandidate.getDeletedDate());
			appliedJobVO.setIsDeleted(deleteCandidate.getIsDeleted());
			final int result = this.employerDAO
					.deleteAppliedCandidate(appliedJobVO);
			if (result != 0) {
				deleteCandidate.setJobResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteCandidate.setErrorCode(je.getErrorCode());
			deleteCandidate.setErrorMessage(je.getErrorMessage());
		}
		return deleteCandidate;

	}

	@Override
	public EmployerBO deleteEmployer(EmployerBO deleteProfile)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();

		try {
			final EmployerVO employerProfileVO = new EmployerVO();
			employerProfileVO.setIsDeleted(deleteProfile.getIsDeleted());
			employerProfileVO.setId(deleteProfile.getId());
			employerProfileVO.setDeletedBy(deleteProfile.getDeletedBy());
			employerProfileVO.setDeletedDate(deleteProfile.getDeletedDate());
			employerProfileVO.setModifiedBy(deleteProfile.getModifiedBy());
			final int result = this.employerDAO
					.deleteEmployer(employerProfileVO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteProfile.setErrorCode(je.getErrorCode());
			deleteProfile.setErrorMessage(je.getErrorMessage());
		}

		return deleteProfile;

	}

	@Override
	public JobPostBO deleteJobPost(JobPostBO deleteProfile)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();

		try {
			final JobPostVO jobPostVO = new JobPostVO();
			jobPostVO.setIsDeleted(deleteProfile.getIsDeleted());
			jobPostVO.setJobId(deleteProfile.getId());
			jobPostVO.setDeletedBy(deleteProfile.getDeletedBy());
			jobPostVO.setDeletedDate(deleteProfile.getDeletedDate());
			jobPostVO.setModifiedBy(deleteProfile.getModifiedBy());
			final int result = this.employerDAO.deleteJobPost(jobPostVO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteProfile.setErrorCode(je.getErrorCode());
			deleteProfile.setErrorMessage(je.getErrorMessage());
		}

		return deleteProfile;
	}

	@Override
	public long jobPost(List<JobPostBO> uploadJobPostBO)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		JobPostBO jobPostBO = new JobPostBO();
		long jobPostId = 0;
		try {
			List<JobPostVO> uploadJobPostVO = new ArrayList<JobPostVO>();
			JobPostVO jobpost = new JobPostVO();

			if (null != uploadJobPostBO && uploadJobPostBO.size() > 0) {
				for (JobPostBO jobPost : uploadJobPostBO) {
					
					jobpost = EmployerServiceImpl.preparable(jobPost);
					jobpost.setJobResponse(jobPost.getJobResponse());

					EmployerProfileVO employerVO = new EmployerProfileVO();
					if (null != jobPost.getEmployerProfile()) {
						employerVO.setProfileId(jobPost.getEmployerProfile()
								.getId());
						jobpost.setEmployerProfileVO(employerVO);
						
					}
					if (0 != jobPost.getCompanyId()) {
						jobpost.setCompanyId(jobPost.getCompanyId());
					}
					uploadJobPostVO.add(jobpost);

				}
			}

			jobPostId = employerDAO.jobPost(uploadJobPostVO);
			jobPostBO.setResponse(SuccessMsg.JOB_POST_SUCCESS);
		} catch (final MyJobKartException je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.JOB_POST_FAIL + je);
			}
			jobPostBO.setErrorCode(je.getErrorCode());
			jobPostBO.setErrorMessage(je.getErrorMessage());
		}

		return jobPostId;

	}

	@Override
	public boolean employeerProfileCheck(EmployerProfileBO profileCheck) {
		EmployerServiceImpl.LOGGER.entry();
		boolean isProfile = false;
		try {
			final EmployerProfileVO employerProfileVO = new EmployerProfileVO();
			employerProfileVO.setProfileId(profileCheck.getId());
			final EmployerProfileBO employerProfileBO = this.employerDAO
					.employeerProfileCheck(employerProfileVO);

			if (0 != employerProfileBO.getId()) {
				isProfile = true;

			}

		} catch (final Exception e) {

		}
		return isProfile;
	}

	/*@Override
	public JobseekerProfileBO searchJobseeker(
			JobseekerProfileBO jobseekerProfile) throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		try {
			final List<JobseekerProfileBO> jobseekerProfileList = this.employerDAO
					.searchJobseeker(jobseekerProfile);

			jobseekerProfile.setJobProfileList(jobseekerProfileList);
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			jobseekerProfile.setErrorCode(je.getErrorCode());
			jobseekerProfile.setErrorMessage(je.getErrorMessage());
		}
		return jobseekerProfile;

	}*/
	
	@Override
	public JobseekerProfileBO searchJobseeker(
			JobseekerProfileBO jobseekerProfile) throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		try {
			JobseekerProfileBO jobseekerbo = this.employerDAO
					.searchJobseeker(jobseekerProfile);
			if (null != jobseekerbo.getResumeList()) {
				jobseekerProfile.setJobProfileList(jobseekerbo.getResumeList());
				jobseekerProfile.setJobTittleCountList(jobseekerbo
						.getTitleList());
			}

		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			jobseekerProfile.setErrorCode(je.getErrorCode());
			jobseekerProfile.setErrorMessage(je.getErrorMessage());
		}
		return jobseekerProfile;

	}

	

	private static JobPostVO preparable(JobPostBO jobPostBO) {
		EmployerServiceImpl.LOGGER.entry();
		JobPostVO jobPostVO = null;

		try {
			jobPostVO = new JobPostVO();
			// jobPostVO.setJobId(jobPostBO.getJobId());
			jobPostVO.setAddress(jobPostBO.getAddress());
			jobPostVO.setCompanyName(jobPostBO.getCompanyName());
			jobPostVO.setContactNo(jobPostBO.getContactNo());
			jobPostVO.setContactPerson(jobPostBO.getContactPerson());
			jobPostVO.setCreatedBy(jobPostBO.getCreatedBy());
			jobPostVO.setCurrencyType(jobPostBO.getCurrencyType());
			jobPostVO.setDeletedBy(jobPostBO.getDeletedBy());
			jobPostVO.setDeletedDate(jobPostBO.getDeletedDate());
			jobPostVO.setFunctionArea(jobPostBO.getFunctionArea());
			jobPostVO.setIndustryType(jobPostBO.getIndustryType());
			jobPostVO.setIsActive(jobPostBO.getIsActive());
			jobPostVO.setIsDeleted(jobPostBO.getIsDeleted());
			jobPostVO.setJobDescription(jobPostBO.getJobDescription());
			jobPostVO.setJobLocation(jobPostBO.getJobLocation());
			jobPostVO.setJobResponse(jobPostBO.getJobResponse());
			jobPostVO.setJobTitle(jobPostBO.getJobTitle());
			jobPostVO.setKeywords(jobPostBO.getKeywords());
			jobPostVO.setMaxExp(jobPostBO.getMaxExp());
			jobPostVO.setModifiedBy(jobPostBO.getModifiedBy());
			jobPostVO.setMaxSalary(jobPostBO.getMaxSalary());
			jobPostVO.setMinExp(jobPostBO.getMinExp());
			jobPostVO.setMinSalary(jobPostBO.getMinSalary());
			jobPostVO.setNoVacancies(jobPostBO.getNoVacancies());
			jobPostVO.setOtherSalaryDetails(jobPostBO.getOtherSalaryDetails());
			jobPostVO.setPgQualification(jobPostBO.getPgQualification());
			jobPostVO.setUgQualification(jobPostBO.getUgQualification());
			jobPostVO.setVersion(jobPostBO.getVersion());
			// jobPostVO.setPhoto(jobPostBO.getPhoto());
			// jobPostVO.setPresentation(jobPostBO.getPresentation());
			jobPostVO.setPostedBy(jobPostBO.getPostedBy());
			jobPostVO.setStartDate(jobPostBO.getStartDate());
			jobPostVO.setEndDate(jobPostBO.getEndDate());
			EmployerLoginVO employerLoginVO = new EmployerLoginVO();
			employerLoginVO.setId(jobPostBO.getEmployerLogin().getId());
			jobPostVO.setEmployerLogin(employerLoginVO);
			// jobPostVO.getEmployerLogin().setId(jobPostBO.getEmpId());

		} catch (final NullPointerException ne) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return jobPostVO;

	}

	private static EmployerProfileVO preparableBOtoVO(EmployerProfileBO profile)
			throws SQLException {
		EmployerServiceImpl.LOGGER.entry();
		EmployerProfileVO profileVO = null;

		try {
			profileVO = new EmployerProfileVO();
			profileVO.setAddressLine1(profile.getAddressLine1());
			profileVO.setCity(profile.getCity());
			profileVO.setCompanyName(profile.getCompanyName());
			profileVO.setCompanyWebsite(profile.getCompanyWebsite());
			profileVO.setCompanyType(profile.getCompanyType());
			profileVO.setContactNo(profile.getContactNo());
			profileVO.setCountry(profile.getCountry());
			profileVO.setCreatedBy(profile.getCreatedBy());
			profileVO.setCurrentDesignation(profile.getCurrentDesignation());
			profileVO.setDeleteBy(profile.getDeleteBy());
			profileVO.setCurrentRolesResponsibilities(profile
					.getCurrentRolesResponsibilities());
			profileVO.setDeletedDate(profile.getDeletedDate());
			profileVO.setEmailId(profile.getEmailId());
			final EmployerLoginVO employerLogin = new EmployerLoginVO();
			employerLogin.setId(profile.getEmployerLogin().getId());
			profileVO.setEmployerLogin(employerLogin);
			profileVO.setFirstName(profile.getFirstName());
			profileVO.setHiringPurpose(profile.getHiringPurpose());
			profileVO.setIndustryType(profile.getIndustryType());
			profileVO.setIsActive(profile.getIsActive());
			profileVO.setIsDelete(profile.getIsDelete());
			profileVO.setLastName(profile.getLastName());
			profileVO.setMobileNo(profile.getMobileNo());
			profileVO.setModifiedBy(profile.getModifiedBy());
			profileVO.setPinCode(profile.getPinCode());
			profileVO.setProductEnrolled(profile.getProductEnrolled());
			profileVO.setSecondaryEmail(profile.getSecondaryEmail());
			profileVO.setState(profile.getState());
			profileVO.setTermsConditionsAgreed(profile
					.getTermsConditionsAgreed());
			if (null != profile.getCompanyLogo()) {
				profileVO.setCompanyLogo(profile.getCompanyLogo());
			}
			profileVO.setCompanyProfile(profile.getCompanyProfile());

		} catch (final NullPointerException ne) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return profileVO;

	}

	private static EmployerProfileBO preparableVOtoBO(EmployerProfileVO profile)
			throws SerialException, SQLException {
		EmployerServiceImpl.LOGGER.entry();
		EmployerProfileBO profileBO = null;

		try {
			profileBO = new EmployerProfileBO();
			if (null != profile.getAddressLine1()) {
				profileBO.setAddressLine1(profile.getAddressLine1());
			}
			if (null != profile.getCity()) {
				profileBO.setCity(profile.getCity());
			}

			profileBO.setCompanyName(profile.getCompanyName());
			profileBO.setCompanyId(profile.getCompanyID().getCompaniesId());
			if (null != profile.getCompanyProfile()) {
				profileBO.setCompanyProfile(profile.getCompanyProfile());
			}
			if (null != profile.getCompanyWebsite()) {
				profileBO.setCompanyWebsite(profile.getCompanyWebsite());
			}
			if (null != profile.getCompanyType()) {
				profileBO.setCompanyType(profile.getCompanyType());
			}
			if (null != profile.getContactNo()) {
				profileBO.setContactNo(profile.getContactNo());
			}
			if (null != profile.getCountry()) {
				profileBO.setCountry(profile.getCountry());
			}
			profileBO.setCreatedBy(profile.getCreatedBy());
			if (null != profile.getCurrentDesignation()) {
				profileBO
						.setCurrentDesignation(profile.getCurrentDesignation());
			}
			profileBO.setDeleteBy(profile.getDeleteBy());
			if (null != profile.getCurrentRolesResponsibilities()) {

				profileBO.setCurrentRolesResponsibilities(profile
						.getCurrentRolesResponsibilities());
			}
			profileBO.setDeletedDate(profile.getDeletedDate());
			profileBO.setEmailId(profile.getEmailId());
			// final EmployerLoginBO employerLogin = new EmployerLoginBO();
			// employerLogin.setId(profile.getEmployerLogin().getId());
			// profileBO.setEmployerLogin(employerLogin);
			profileBO.setFirstName(profile.getFirstName());
			if (null != profile.getHiringPurpose()) {
				profileBO.setHiringPurpose(profile.getHiringPurpose());
			}
			profileBO.setIndustryType(profile.getIndustryType());
			profileBO.setIsActive(profile.getIsActive());
			profileBO.setIsDelete(profile.getIsDelete());
			profileBO.setLastName(profile.getLastName());
			profileBO.setMobileNo(profile.getMobileNo());
			profileBO.setModifiedBy(profile.getModifiedBy());
			if (null != profile.getPinCode()) {
				profileBO.setPinCode(profile.getPinCode());
			}
			profileBO.setProductEnrolled(profile.getProductEnrolled());
			if (null != profile.getSecondaryEmail()) {
				profileBO.setSecondaryEmail(profile.getSecondaryEmail());
			}
			if (null != profile.getState()) {
				profileBO.setState(profile.getState());
			}

			if (null != profile.getCompanyLogo()) {
				profileBO.setCompanyLogo(profile.getCompanyLogo().getBytes(1,
						(int) profile.getCompanyLogo().length()));
			}
			profileBO.setId(profile.getProfileId());

		} catch (final NullPointerException ne) {
			ne.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return profileBO;

	}

	@Override
	public boolean profileStatus(EmployerProfileBO employerProfileBO) {
		EmployerServiceImpl.LOGGER.entry();
		boolean loginChanged = false;

		EmployerProfileVO employerProfileVO = new EmployerProfileVO();

		if (0 != employerProfileBO.getId()) {
			BeanUtils.copyProperties(employerProfileBO, employerProfileVO);
			employerProfileVO.setProfileId(employerProfileBO.getId());
			employerProfileVO = this.employerDAO
					.profileStatus(employerProfileVO);
			if (0 != employerProfileVO.getProfileId()) {
				loginChanged = true;
			}
		}
		return loginChanged;
	}

	@Override
	public boolean updateSubUser(EmployerSubuserBO subUser) {
		EmployerServiceImpl.LOGGER.entry();
		boolean loginChanged = false;

		EmployerSubuserVO subUserVO = new EmployerSubuserVO();

		if (null != subUser) {
			subUserVO.setId(subUser.getId());
			subUserVO.setIsActive(subUser.getIsActivedVal());
			subUserVO = this.employerDAO.updateSubUserActive(subUserVO);
			if (0 != subUserVO.getId()) {
				loginChanged = true;
			}
		}
		return loginChanged;
	}

	@Override
	public AppliedJobBO reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException, SerialException, SQLException {
		EmployerServiceImpl.LOGGER.entry();
		final AppliedJobBO appliedjobBO = new AppliedJobBO();
		try {
			final List<AppliedJobBO> retriveProfileList = this.employerDAO
					.reteriveAppliedJobs(appliedJobBO);
			if (0 != retriveProfileList.size()) {
				appliedjobBO.setJobPostList(retriveProfileList);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			appliedjobBO.setErrorCode(je.getErrorCode());
			appliedjobBO.setErrorMessage(je.getErrorMessage());
		}
		EmployerServiceImpl.LOGGER.exit();
		return appliedjobBO;
	}

	@Override
	public SaveCandidateBO reteriveCandidate(SaveCandidateBO saveCandidateBO)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		final SaveCandidateBO sBo = new SaveCandidateBO();
		try {
			final List<SaveCandidateBO> retriveProfileList = this.employerDAO
					.reteriveCandidate(saveCandidateBO);
			if (0 != retriveProfileList.size()) {
				sBo.setJobProfileList(retriveProfileList);

			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			sBo.setErrorCode(je.getErrorCode());
			sBo.setErrorMessage(je.getErrorMessage());
		}
		EmployerServiceImpl.LOGGER.exit();
		return sBo;

	}

	@Override
	public boolean shortlistCandidate(ShortListCandidate shortListCandidate)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		ShortListVO shortListVO = new ShortListVO();
		SaveCandidateVO candidateVO = new SaveCandidateVO();
		boolean shortList = false;
		boolean loginReset = false;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			shortListVO = EmployerServiceImpl
					.shortlistBOtoVO(shortListCandidate);
			candidateVO.setSavecandidateid(shortListCandidate.getCandidateBO().getId());
			shortListVO.setCandidateVO(candidateVO);
			shortListVO = this.employerDAO.shortlistCandidate(shortListVO);

			if (0 != shortListVO.getShortlistId()) {

				EmployerActivityVO emActivityVO = new EmployerActivityVO();
				JobseekerProfileVO jobseekerProfile = new JobseekerProfileVO();
				JobPostVO jobPostVO = new JobPostVO();
				EmployerVO employerVO = new EmployerVO();

				// Employer RegId
				employerVO.setId(shortListCandidate.getEmpRegId());
				emActivityVO.setEmployerVO(employerVO);

				// Employer ShortListed Job Id
				jobPostVO.setJobId(shortListCandidate.getJobId());
				emActivityVO.setJobPostVO(jobPostVO);

				// job seeker Profile Id for saved Candidate
				if (0 != shortListCandidate.getProfileId()) {
					jobseekerProfile.setprofileId(shortListCandidate
							.getProfileId());
					emActivityVO.setJobseekerProfileVO(jobseekerProfile);
				}

				// Job seeker Profile Id for Applied Candidate
				JobseekerProfileVO jobseekerProfileVO = new JobseekerProfileVO();
				if (0 != shortListCandidate.getJobseekerId()) {
					long jobprofileId = this.employerDAO
							.getJobseekerRegId(shortListCandidate);
					if (jobprofileId != 0) {
						jobseekerProfileVO.setprofileId(jobprofileId);
						emActivityVO.setJobseekerProfileVO(jobseekerProfileVO);

					}
				}

				emActivityVO.setCreated(shortListVO.getCreated());
				emActivityVO.setCreatedBy(shortListVO.getCreatedBy());
				emActivityVO.setActivityDate(format.format(shortListVO
						.getCreated()));
				emActivityVO.setStatus("ShortListed Candidate");
				emActivityVO = employerDAO
						.shortlistCandidateActivity(emActivityVO);
				if (null != emActivityVO && emActivityVO.getId() != 0) {
					if (EmployerServiceImpl.isApproval) {
						try {

							String toaddress = shortListCandidate.getEmailId();
							String subject = "Myjobkart: Job ShortList Confirmation !";
							String website = JobtrolleyResourceBundle
									.getValue("website");
							String bodycontent = "ShortList";
							model.setWebSite(website);

							model.setFirstname(shortListCandidate
									.getFirstName());
							model.setCompanyName(shortListCandidate
									.getShortListCompany());
							model.setJobtitle(shortListCandidate.getJobTitle());
							model.setContactEmail(shortListCandidate
									.getContactMail());
							model.setSalary(shortListCandidate.getExpectedCtc());
							model.setPreferedlocation(shortListCandidate
									.getPreferredLocation());
							emailManager.sendEmail(toaddress, subject,
									bodycontent, model);
							loginReset = true;
							shortList = true;
						} catch (final Exception ex) {
							EmployerServiceImpl.LOGGER
									.debug(ex,
											"Registeration Email to the Customer Failed!");
						}
					}
				}
			}

			// BeanUtils.copyProperties(employerVO, employerBO);
			shortListCandidate.setResponse(SuccessMsg.REG_SUCCESS);
		} catch (final Exception je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.EM_REG_FAIL + je);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return shortList;
	}

	public static ShortListVO shortlistBOtoVO(
			ShortListCandidate shortListCandidate) {
		EmployerServiceImpl.LOGGER.entry();
		ShortListVO shortListVO = null;
		try {
			shortListVO = new ShortListVO();
			shortListVO.setIsDeleted(true);
			shortListVO.setShortListCompany(shortListCandidate
					.getShortListCompany());
			shortListVO.setJobpostTittle(shortListCandidate.getJobpostTittle());
			shortListVO.setKeySkills(shortListCandidate.getKeySkills());
			shortListVO.setCreatedBy(shortListCandidate.getCreatedBy());
			shortListVO.setModifiedBy(shortListCandidate.getModifiedBy());
			shortListVO.setEmailId(shortListCandidate.getEmailId());
			shortListVO.setCurrentIndustry(shortListCandidate
					.getCurrentIndustry());
			shortListVO.setDegree(shortListCandidate.getDegree());
			shortListVO.setFirstName(shortListCandidate.getFirstName());
			shortListVO.setPhone(shortListCandidate.getPhone());
			shortListVO.setUploadResume(shortListCandidate.getUploadResume());
			shortListVO.setExperienceInMonth(shortListCandidate
					.getExperienceInMonth());
			shortListVO.setExperienceInYear(shortListCandidate
					.getExperienceInYear());
			shortListVO.setExpectedCtc(shortListCandidate.getExpectedCtc());
			shortListVO.setExpectedCtcPer(shortListCandidate
					.getExpectedCtcPer());
			shortListVO.setProfiledescription(shortListCandidate
					.getProfiledescription());
			shortListVO.setPreferredLocation(shortListCandidate
					.getPreferredLocation());
			shortListVO.setPreferredIndustry(shortListCandidate
					.getPreferredIndustry());
			shortListVO.setCompanyName(shortListCandidate.getCompanyName());
			shortListVO.setResumeTitle(shortListCandidate.getResumeTitle());
			shortListVO.setJobTitle(shortListCandidate.getJobTitle());
			if (null != shortListCandidate.getAppliedJobBO()) {
				final AppliedJobVO appliedJobVO = new AppliedJobVO();
				appliedJobVO.setApplicationid(shortListCandidate
						.getAppliedJobBO().getId());
				shortListVO.setAppliedJobVO(appliedJobVO);
			} else {
				final SaveCandidateVO candidateVO = new SaveCandidateVO();
				candidateVO.setSavecandidateid(shortListCandidate
						.getCandidateBO().getId());
				shortListVO.setCandidateVO(candidateVO);
			}

		} catch (final NullPointerException ne) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return shortListVO;

	}

	public static SaveCandidateVO savepreparableBOtoVO(
			SaveCandidateBO saveCandidateBO) {
		EmployerServiceImpl.LOGGER.entry();
		SaveCandidateVO saveCandidateVO = null;
		try {
			saveCandidateVO = new SaveCandidateVO();

			saveCandidateVO.setCollege(saveCandidateBO.getCollege());
			saveCandidateVO.setCompanyName(saveCandidateBO.getCompanyName());
			saveCandidateVO.setCompanyType(saveCandidateBO.getCompanyType());
			saveCandidateVO.setCreatedBy(saveCandidateBO.getCreatedBy());
			saveCandidateVO.setCurrentIndustry(saveCandidateBO
					.getCurrentIndustry());
			saveCandidateVO
					.setCurrentSalary(saveCandidateBO.getCurrentSalary());
			saveCandidateVO.setDegree(saveCandidateBO.getDegree());
			saveCandidateVO.setDeleteBy(saveCandidateBO.getDeleteBy());
			saveCandidateVO.setDeletedDate(saveCandidateBO.getDeletedDate());
			saveCandidateVO.setDesignation(saveCandidateBO.getDesignation());
			saveCandidateVO.setDomainSkills(saveCandidateBO.getDomainSkills());
			saveCandidateVO.setEmailId(saveCandidateBO.getEmailId());
			saveCandidateVO.setExpectedCtc(saveCandidateBO.getExpectedCtc());
			saveCandidateVO.setExpEndDate(saveCandidateBO.getExpEndDate());
			saveCandidateVO.setExperienceInMonth(saveCandidateBO
					.getExperienceInMonth());
			saveCandidateVO.setExpStartDate(saveCandidateBO.getExpStartDate());
			saveCandidateVO.setFirstName(saveCandidateBO.getFirstName());
			saveCandidateVO.setFunction(saveCandidateBO.getFunction());
			saveCandidateVO.setGender(saveCandidateBO.getGender());
			saveCandidateVO.setIsActive(saveCandidateBO.getIsActive());
			saveCandidateVO.setGetPrivilege(saveCandidateBO.getGetPrivilege());
			saveCandidateVO.setGrade(saveCandidateBO.getGrade());
			saveCandidateVO.setIsDelete(saveCandidateBO.getIsDelete());
			final EmployerLoginVO employerLoginVO = new EmployerLoginVO();
			employerLoginVO.setId(saveCandidateBO.getEmployerLoginBO().getId());
			saveCandidateVO.setEmployerLoginVO(employerLoginVO);
			saveCandidateVO.setJobType(saveCandidateBO.getJobType());
			saveCandidateVO.setKeySkills(saveCandidateBO.getKeySkills());
			saveCandidateVO.setLanguagesKnown(saveCandidateBO
					.getLanguagesKnown());
			saveCandidateVO.setLastName(saveCandidateBO.getLastName());
			saveCandidateVO.setLocation(saveCandidateBO.getLocation());
			saveCandidateVO
					.setMaritalStatus(saveCandidateBO.getMaritalStatus());
			saveCandidateVO.setModifiedBy(saveCandidateBO.getModifiedBy());
			saveCandidateVO.setNationality(saveCandidateBO.getNationality());
			saveCandidateVO.setPhone(saveCandidateBO.getPhone());
			saveCandidateVO.setPreferredIndustry(saveCandidateBO
					.getPreferredIndustry());
			saveCandidateVO.setPreferredLocation(saveCandidateBO
					.getPreferredLocation());
			saveCandidateVO.setProfileImage(saveCandidateBO.getProfileImages());
			saveCandidateVO.setResumeTitle(saveCandidateBO.getResumeTitle());
			saveCandidateVO.setSpecialisation(saveCandidateBO
					.getSpecialisation());
			saveCandidateVO.setTermsConditionsAgreed(saveCandidateBO
					.getTermsConditionsAgreed());
			saveCandidateVO.setUploadResume(saveCandidateBO.getUploadResumes());
			saveCandidateVO
					.setYearOfPassing(saveCandidateBO.getYearOfPassing());
			saveCandidateVO.setCurrentSalaryPer(saveCandidateBO
					.getCurrentSalaryPer());
			saveCandidateVO.setExpectedCtcPer(saveCandidateBO
					.getExpectedCtcPer());
			saveCandidateVO.setExperienceInYear(saveCandidateBO
					.getExperienceInYear());
			saveCandidateVO.setProfiledescription(saveCandidateBO
					.getProfiledescription());
			final JobseekerProfileVO vo = new JobseekerProfileVO();
			vo.setprofileId(saveCandidateBO.getJobseekerProfileBO().getId());
			saveCandidateVO.setJobseekerProfileVO(vo);
			saveCandidateVO.setIsShortListed(false);
		} catch (final NullPointerException ne) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return saveCandidateVO;

	}

	@Override
	public EmployerLoginBO approveEmployer(EmployerLoginBO loginBO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployerBO> getemployers(EmployerBO employerRegistrationBO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobseekerProfileBO refineResults(JobseekerProfileBO profileBO) {
		EmployerServiceImpl.LOGGER.entry();
		try {
			final List<JobseekerProfileBO> jobseekerProfileBOList = this.employerDAO
					.refineResult(profileBO);
			profileBO.setJobProfileList(jobseekerProfileBOList);
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			profileBO.setErrorCode(je.getErrorCode());
			profileBO.setErrorMessage(je.getErrorMessage());
		}
		return profileBO;
	}

	@Override
	public EmployerBO retrieveRegisteredEmployer() throws MyJobKartException,
			SerialException, SQLException {
		EmployerServiceImpl.LOGGER.entry();
		final EmployerBO employerBO = new EmployerBO();
		try {

			final List<EmployerBO> registeredEmployerList = this.employerDAO
					.retrieveRegisteredList();

			if (0 != registeredEmployerList.size()) {
				employerBO.setRegisteredList(registeredEmployerList);

			}

		} catch (final MyJobKartException e) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}

			employerBO.setErrorCode(e.getErrorCode());
			employerBO.setErrorMessage(e.getErrorMessage());

		}
		EmployerServiceImpl.LOGGER.exit();
		return employerBO;
	}

	@Override
	public EmployerBO retrieveRegisteredEmployer(long employerRegisterId)
			throws MyJobKartException, SerialException, SQLException {
		EmployerServiceImpl.LOGGER.entry();
		final EmployerBO employerBO = new EmployerBO();
		try {

			final List<EmployerBO> registeredEmployerList = this.employerDAO
					.retrieveRegisteredList(employerRegisterId);

			if (0 != registeredEmployerList.size()) {
				employerBO.setRegisteredList(registeredEmployerList);

			}

		} catch (final MyJobKartException e) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}

			employerBO.setErrorCode(e.getErrorCode());
			employerBO.setErrorMessage(e.getErrorMessage());

		}
		EmployerServiceImpl.LOGGER.exit();
		return employerBO;
	}

	@Override
	public SaveCandidateBO employeerSaveResume(SaveCandidateBO saveCandidateBO) {
		final SaveCandidateBO candidateBO = new SaveCandidateBO();
		// List<SaveCandidateBO> saveCandidateBOList = new
		// ArrayList<SaveCandidateBO>();

		try {
			List<SaveCandidateBO> saveCandidateBOList = this.employerDAO
					.employeerSaveResume(saveCandidateBO);

			if (saveCandidateBOList.size() > 0) {
				candidateBO.setSaveCandidateBOList(saveCandidateBOList);
			}
		} catch (final MyJobKartException je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			saveCandidateBO.setErrorCode(je.getErrorCode());
			saveCandidateBO.setErrorMessage(je.getErrorMessage());
		}
		return candidateBO;
	}

	@Override
	public boolean profileStatus1(EmployerProfileBO employerProfileBO) {
		boolean loginChanged = false;

		EmployerVO employerLoginVO = new EmployerVO();

		if (null != employerProfileBO.getIsActive()) {
			BeanUtils.copyProperties(employerProfileBO, employerLoginVO);
			employerLoginVO.setIsActive(employerProfileBO.getIsActive());
			employerLoginVO.setId(employerProfileBO.getId());
			employerLoginVO = this.employerDAO.profileStatus1(employerLoginVO);
			if (null != employerProfileBO.getIsActive()) {
				loginChanged = true;
			}
		}
		return loginChanged;
	}

	@Override
	public void saveProfileView(ViewJobseekerBO viewBo) {
		this.employerDAO.saveProfileView(viewBo);

	}

	@Override
	public EmployerBO renewelRegisteredEmployer() throws MyJobKartException,
			SerialException, SQLException {
		EmployerServiceImpl.LOGGER.entry();
		final EmployerBO employerBO = new EmployerBO();
		try {
			final List<EmployerBO> registeredEmployerList = this.employerDAO
					.renewelRegisteredList();

			if (0 != registeredEmployerList.size()) {
				employerBO.setRegisteredList(registeredEmployerList);

			}
		} catch (final MyJobKartException e) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
			employerBO.setErrorCode(e.getErrorCode());
			employerBO.setErrorMessage(e.getErrorMessage());

		}
		EmployerServiceImpl.LOGGER.exit();
		return employerBO;
	}

	@Override
	public PaymentBO addPayments(PaymentBO paymentBO) {
		EmployerServiceImpl.LOGGER.entry();
		final int month = paymentBO.getTotalMonth();

		final EntrolledSeviceVO entrolledSevice = new EntrolledSeviceVO();
		BeanUtils.copyProperties(paymentBO, entrolledSevice);
		final Date validfrom = new Date();
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		final Date date = cal.getTime();
		entrolledSevice.setValidFrom(validfrom);
		entrolledSevice.setValidEnd(date);
		entrolledSevice.setProducType("employeer");
		this.employerDAO.addPayments(entrolledSevice);
		paymentBO.setResponse(SuccessMsg.REG_SUCCESS);
		EmployerServiceImpl.LOGGER.exit();
		return paymentBO;

	}

	@Override
	public BookingBO createOrder(BookingBO orderBO) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

			BookingVO bookingVO = new BookingVO();
			EmployerVO employerVO = new EmployerVO();
			BeanUtils.copyProperties(orderBO, bookingVO);
			bookingVO.setCreatedBy(orderBO.getCustId());
			bookingVO.setModifiedBy(orderBO.getCustId());
			employerVO.setId(orderBO.getEmployerRegistration().getId());
			bookingVO.setEmployerRegistration(employerVO);
			bookingVO.setBookingStatus(orderBO.getBookingStatus());
			orderBO = employerDAO.createOrder(bookingVO);

		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Craete order has failed:" + ex.getMessage());
			}
			LOGGER.info("Craete order has failed:" + ex.getMessage());
		}
		return orderBO;
	}

	@Override
	public SaveCandidateBO getJobseekerId(SaveCandidateBO candidateBO) {
		// TODO Auto-generated method stub
		// List<SaveCandidateBO> jobseekeridBos = new
		// ArrayList<SaveCandidateBO>();
		List<SaveCandidateBO> jobseekeridBos = this.employerDAO
				.getJobseekerId(candidateBO);
		if (0 != jobseekeridBos.size()) {
			candidateBO.setSaveCandidateBOList(jobseekeridBos);
		}
		return candidateBO;
	}

	@Override
	public JobPostBO getShortlistId(JobPostBO candidate) {
		// TODO Auto-generated method stub
		// List<JobPostBO> shortListCandidates = new ArrayList<JobPostBO>();
		List<JobPostBO> shortListCandidates = this.employerDAO
				.getShortlistId(candidate);
		if (0 != shortListCandidates.size()) {
			candidate.setSaveListCandidates(shortListCandidates);
		}

		return candidate;

	}

	@Override
	public JobPostBO getJobposting(JobPostBO jobPostBO) {
		JobPostVO jobpost = new JobPostVO();
		jobpost.setJobId(jobPostBO.getJobId());
		JobPostVO jobpostVO = employerDAO.getJobpost(jobpost);
		JobPostBO jobPost = new JobPostBO();
		jobPost.setCompanyName(jobpostVO.getCompanyName());
		jobPost.setCompanyId(jobpostVO.getCompanyId());
		jobPost.setCompanyName(jobpostVO.getCompanyName());
		EmployerProfileBO employer = new EmployerProfileBO();
		employer.setCompanyName(jobpostVO.getEmployerProfileVO()
				.getCompanyName());
		employer.setCompanyId(jobpostVO.getCompanyId());
		employer.setCompanyProfile(jobpostVO.getEmployerProfileVO()
				.getCompanyProfile());
		jobPost.setEmployerRegistion(employer);

		return jobPost;

	}

	@Override
	public boolean saveBannerPost(BannerPostBO bannerpost) {
		// TODO Auto-generated method stub
		boolean saveBanner = false;
		BannerPostVO bannerPostVO = new BannerPostVO();
		try {

			int month = bannerpost.getTotalMonth();
			if ("NEW".equalsIgnoreCase(bannerpost.getStatus())) {
				bannerPostVO.setBannerName(bannerpost.getBannerName());
				// bannerPostVO.setBannerId(bannerpost.getBannerId());
				bannerPostVO.setPostPage(bannerpost.getPostPage());
				bannerPostVO.setBannerImage(bannerpost.getBannerImage());
				bannerPostVO.setTotalcost(bannerpost.getTotalcost());
				bannerPostVO.setTotalMonth(bannerpost.getTotalMonth());
				bannerPostVO.setIsDelete(bannerpost.getIsDelete());
				bannerPostVO.setIsActive(true);
				final EmployerLoginVO bo = new EmployerLoginVO();
				bo.setId(bannerpost.getEmployerRegister().getId());
				bannerPostVO.setEmployerRegister(bo);
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, month);
				final Date date = cal.getTime();
				bannerPostVO.setGracePeriod(date);
				final long boolean1 = this.employerDAO
						.saveBannerPost(bannerPostVO);
				if (boolean1 != 0) {
					saveBanner = true;
				}
			} else {

				/*
				 * if("RENEWAL".equalsIgnoreCase(new
				 * String(bannerpost.getBannerId())){
				 */
				bannerPostVO.setBannerId(bannerpost.getBannerId());
				bannerPostVO = employerDAO.getBanner(bannerPostVO);
				bannerPostVO.setBannerId(bannerpost.getBannerId());
				bannerPostVO.setIsActive(false);
				// To inactive Existing record
				this.employerDAO.updateBanner(bannerPostVO);
				BannerPostVO bannerPost = new BannerPostVO();

				// To save the renewal record.
				bannerPost.setCreated(new Date());
				bannerPost.setBannerId(0);
				bannerPost.setBannerName(bannerpost.getBannerName());
				bannerPost.setPostPage(bannerpost.getPostPage());
				bannerPost.setBannerImage(bannerpost.getBannerImage());
				bannerPost.setTotalcost(bannerpost.getTotalcost());
				bannerPost.setTotalMonth(bannerpost.getTotalMonth());
				bannerPost.setIsDelete(bannerpost.getIsDelete());
				final EmployerLoginVO bo = new EmployerLoginVO();
				bo.setId(bannerpost.getEmployerRegister().getId());
				bannerPost.setEmployerRegister(bo);
				bannerPost.setIsActive(true);
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, month);
				final Date date = cal.getTime();
				bannerPost.setGracePeriod(date);
				bannerPost = this.employerDAO.updateBanner(bannerPost);

				/*
				 * long boolean1 =
				 * this.employerDAO.saveBannerPost(bannerPostVO);
				 */
				if (bannerPost.getIsActive()) {
					saveBanner = true;
				}
			}
			/* } */

		}

		catch (final Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return saveBanner;
	}

	@Override
	public List<BannerPostBO> retrieveBannerList(String fileName)
			throws SerialException, SQLException {
		// TODO Auto-generated method stub

		final List<BannerPostBO> bannerPostBOs = this.employerDAO
				.getBannerList(fileName);
		return bannerPostBOs;

	}

	@Override
	public List<BannerPostBO> renewelBannerList(long id)
			throws SerialException, SQLException {
		// TODO Auto-generated method stub
		final List<BannerPostBO> bannerPostBOs = this.employerDAO
				.getBannerList(id);

		return bannerPostBOs;
	}

	@Override
	public List<BannerPostBO> renewelBannarList() throws SerialException,
			SQLException {
		// TODO Auto-generated method stub
		final List<BannerPostBO> bannerPostBOs = this.employerDAO
				.getBannarList();
		return bannerPostBOs;
	}

	@Override
	public EmployerBO employerRenewalAlert(String email) {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		final EmployerBO employerBO = new EmployerBO();
		try {

			final List<EmployerBO> registeredEmployerList = this.employerDAO
					.employerRenewalAlert(email);

			if (0 != registeredEmployerList.size()) {
				employerBO.setRegisteredList(registeredEmployerList);

			}
		} catch (final Exception e) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return employerBO;
	}

	@Override
	public BannerPostBO deleteBannerList(BannerPostBO deleteProfile) {
		// TODO Auto-generated method stub
		try {
			final BannerPostVO jobPostVO = new BannerPostVO();
			jobPostVO.setBannerId(deleteProfile.getBannerId());
			jobPostVO.setIsDelete(deleteProfile.getIsDelete());
			jobPostVO.setDeleteBy(deleteProfile.getDeleteBy());
			jobPostVO.setDeletedDate(deleteProfile.getDeletedDate());
			final int result = this.employerDAO.deleteBannerList(jobPostVO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteProfile.setErrorCode(je.getErrorCode());
			deleteProfile.setErrorMessage(je.getErrorMessage());
		}

		return deleteProfile;
	}

	@Override
	public boolean bannerName(BannerPostBO bannerName) {
		// TODO Auto-generated method stub

		EmployerServiceImpl.LOGGER.entry();
		boolean isProfile = false;

		try {
			final long profileId = this.employerDAO.bannerName(bannerName);
			if (0 != profileId) {
				isProfile = true;
			}

		} catch (final BeansException jb) {
			jb.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return isProfile;
	}

	@Override
	public BannerPostBO updateBanner(BannerPostBO bannerPostBO) {
		try {

			BannerPostVO bannerPostVO = new BannerPostVO();
			bannerPostVO.setBannerId(bannerPostBO.getBannerId());
			bannerPostVO = employerDAO.getBanner(bannerPostVO);

			int month = bannerPostVO.getTotalMonth();
			bannerPostVO.setBannerName(bannerPostBO.getBannerName());
			bannerPostVO.setPostPage(bannerPostBO.getPostPage());
			bannerPostVO.setBannerImage(bannerPostBO.getBannerImage());
			bannerPostVO.setIsDelete(bannerPostBO.getIsDelete());
			bannerPostVO.setBannerId(bannerPostBO.getBannerId());
			bannerPostVO.setModifiedBy(bannerPostBO.getModifiedBy());
			EmployerLoginVO bo = new EmployerLoginVO();
			bo.setId(bannerPostBO.getEmployerRegister().getId());
			bannerPostVO.setEmployerRegister(bo);
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, month);
			final Date date = cal.getTime();
			bannerPostVO.setGracePeriod(date);
			bannerPostVO.setVersion(bannerPostBO.getVersion());

			this.employerDAO.updateBanner(bannerPostVO);
			bannerPostBO.setResponse(SuccessMsg.UPDATE_SUCCESS);
		} catch (final MyJobKartException e) {
			e.printStackTrace();
		}

		return bannerPostBO;
	}

	@Override
	public PaymentBO productsEnrolledList(long loginId) {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		final PaymentBO paymentBO = new PaymentBO();
		final List<PaymentBO> productsList = this.employerDAO
				.productsEnrolledList(loginId);
		if (0 != productsList.size()) {
			paymentBO.setEnrolledList(productsList);
		}
		EmployerServiceImpl.LOGGER.exit();
		return paymentBO;
	}

	@Override
	public PaymentBO deleteJobseekerEnrolledDetails(PaymentBO savejobBO) {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		try {
			final EntrolledSeviceVO saveJobVO = new EntrolledSeviceVO();
			saveJobVO.setEntrolledid(savejobBO.getId());
			saveJobVO.setIsDeleted(savejobBO.getIsDeleted());
			saveJobVO.setDeletedDate(savejobBO.getDeletedDate());
			final int result = this.employerDAO
					.deleteEmployerEnrolledDetails(saveJobVO);
			if (result != 0) {
				savejobBO.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final Exception je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}

		}

		EmployerServiceImpl.LOGGER.exit();
		return savejobBO;
	}

	@Override
	public PaymentBO employerLastMonthEnrolledList(long registerId) {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		final PaymentBO paymentBO = new PaymentBO();
		final List<PaymentBO> productsList = this.employerDAO
				.employerLastMonthEnrolledList(registerId);
		if (0 != productsList.size()) {
			paymentBO.setEnrolledList(productsList);
		}
		EmployerServiceImpl.LOGGER.exit();
		return paymentBO;
	}

	@Override
	public PaymentBO employerPaymentHistory() {
		EmployerServiceImpl.LOGGER.entry();
		final PaymentBO paymentBO = new PaymentBO();
		final List<PaymentBO> productsList = this.employerDAO
				.employerPaymentHistory();
		if (0 != productsList.size()) {
			paymentBO.setEnrolledList1(productsList);
		}
		EmployerServiceImpl.LOGGER.exit();
		return paymentBO;
	}

	@Override
	public SaveCandidateBO reteriveCandidate() {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		final SaveCandidateBO sBo = new SaveCandidateBO();
		try {
			final List<SaveCandidateBO> retriveProfileList = this.employerDAO
					.reteriveCandidate();
			if (0 != retriveProfileList.size()) {
				sBo.setJobProfileList(retriveProfileList);

			}
		} catch (final Exception je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return sBo;
	}

	@Override
	public ShortListCandidate shortListCandidatesView(
			ShortListCandidate shortlistCandidate) {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		final ShortListCandidate sBo = new ShortListCandidate();
		ShortListVO shortListVO = new ShortListVO();
		shortListVO.setCreatedBy(shortlistCandidate.getCreatedBy());
		try {
			final List<ShortListCandidate> retriveProfileList = this.employerDAO
					.shortListCandidatesView(shortListVO);
			if (0 != retriveProfileList.size()) {
				sBo.setCandidateList(retriveProfileList);

			}
		} catch (final Exception je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return sBo;
	}

	@Override
	public ShortListCandidate shortListCandidates() {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		final ShortListCandidate sBo = new ShortListCandidate();

		try {
			final List<ShortListCandidate> retriveProfileList = this.employerDAO
					.shortListCandidates();
			if (0 != retriveProfileList.size()) {
				sBo.setCandidateList(retriveProfileList);

			}
		} catch (final Exception je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return sBo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jobtrolley.service.EmployerService#retriveEmployerprofile(long)
	 */

	public boolean activeEmployer(EmployerLoginBO login) {
		EmployerServiceImpl.LOGGER.entry();
		boolean loginActivated = false;

		try {

			EmployerLoginVO loginVO = new EmployerLoginVO();
			BeanUtils.copyProperties(login, loginVO);
			loginVO.setEmailAddress(login.getEmailAddress());
			loginVO.setPassword(login.getPassword());
			loginVO.setCreatedBy(login.getId());
			loginVO.setModifiedBy(login.getId());
			loginVO.setActive(true);
			EmployerVO register = new EmployerVO();
			register.setId(login.getId());
			loginVO.setEmployerRegistration(register);

			final EmployerProductServiceVO employeerProductServiceVO = new EmployerProductServiceVO();
			employeerProductServiceVO.setProductType("employeer");
			employeerProductServiceVO.setProductCost(new BigDecimal(0));

			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 1);
			final Date date = cal.getTime();

			employeerProductServiceVO.setGracePeriod(date);

			final long employerLoginId = this.employerDAO.employerActivation(
					loginVO, employeerProductServiceVO);
			if (0 != employerLoginId) {
				loginActivated = true;
			}

		} catch (final Exception je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}

		}
		return loginActivated;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.service.EmployerService#activatedEmployers(com.jobtrolley
	 * .bo.EmployerLoginBO)
	 */
	@Override
	public EmployerLoginBO activatedEmployers(EmployerLoginBO employerLoginBO) {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		try {

			EmployerLoginVO employerLoginVO = this.employerDAO.authendicate(
					"emailAddress", employerLoginBO.getEmailAddress());

			if (null != employerLoginVO) {

				employerLoginBO.setIsActive(true);

			} else {
				employerLoginBO.setIsActive(false);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		EmployerServiceImpl.LOGGER.exit();
		return employerLoginBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#jobPostProfileStatus(com.myjobkart
	 * .bo.JobPostBO)
	 */
	@Override
	public boolean jobPostProfileStatus(JobPostBO jobPostBO) {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		boolean update = false;
		try {

			JobPostVO jobpostVO = new JobPostVO();
			BeanUtils.copyProperties(jobPostBO, jobpostVO);
			jobpostVO = employerDAO.jobPostProfileStatus(jobpostVO);
			if (0 != jobpostVO.getJobId()) {
				update = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		EmployerServiceImpl.LOGGER.exit();
		return update;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#shortListDeleteProfile(com.myjobkart
	 * .bo.ShortListCandidate)
	 */
	@Override
	public ShortListCandidate shortListDeleteProfile(
			ShortListCandidate shortListCandidateBO) {
		// TODO Auto-generated method stub
		EmployerServiceImpl.LOGGER.entry();
		try {
			ShortListVO shortListVO = new ShortListVO();
			shortListVO.setShortlistId(shortListCandidateBO.getShortlistId());
			shortListVO.setDeletedDate(shortListCandidateBO.getDeletedDate());
			shortListVO.setDeletedBy(shortListCandidateBO.getDeletedBy());
			shortListVO.setModifiedBy(shortListCandidateBO.getModifiedBy());
			shortListVO.setIsDeleted(shortListCandidateBO.getIsDeleted());

			int result = employerDAO.shortListDeleteProfile(shortListVO);
			if (0 != result) {
				shortListCandidateBO.setResponse(SuccessMsg.DELETE_SUCCESS);
			}

		} catch (Exception je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return shortListCandidateBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#payBanner(com.myjobkart.bo.BannerPostBO
	 * )
	 */
	@Override
	public BannerPostBO payBanner(BannerPostBO bannerPostBO) {
		// TODO Auto-generated method stub
		try {

			BannerPostVO bannerPostVO = new BannerPostVO();
			bannerPostVO.setBannerId(bannerPostBO.getBannerId());
			bannerPostVO = employerDAO.getBanner(bannerPostVO);

			int month = bannerPostBO.getTotalMonth();
			bannerPostVO.setBannerName(bannerPostBO.getBannerName());
			bannerPostVO.setPostPage(bannerPostBO.getPostPage());
			bannerPostVO.setBannerImage(bannerPostBO.getBannerImage());
			bannerPostVO.setTotalcost(bannerPostBO.getTotalcost());
			bannerPostVO.setTotalMonth(bannerPostBO.getTotalMonth());
			bannerPostVO.setIsDelete(bannerPostBO.getIsDelete());
			bannerPostVO.setBannerId(bannerPostBO.getBannerId());
			// bannerPostVO.setCreatedBy(bannerPostBO.getCreatedBy());
			bannerPostVO.setModifiedBy(bannerPostBO.getModifiedBy());
			EmployerLoginVO bo = new EmployerLoginVO();
			bo.setId(bannerPostBO.getEmployerRegister().getId());
			bannerPostVO.setEmployerRegister(bo);
			final Calendar cal = Calendar.getInstance();

			cal.add(Calendar.MONTH, month);
			final Date date = cal.getTime();

			bannerPostVO.setGracePeriod(date);
			bannerPostVO.setVersion(bannerPostBO.getVersion());

			this.employerDAO.updateBanner(bannerPostVO);
			bannerPostBO.setResponse(SuccessMsg.UPDATE_SUCCESS);
		} catch (final MyJobKartException e) {
			e.printStackTrace();
		}

		return bannerPostBO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.EmployerService#employerRegistration(long)
	 */

	public EmployerBO employerRegisteration(EmployerBO employerBO) {
		// TODO Auto-generated method stub

		EmployerVO employerVO = new EmployerVO();
		EmployerBO empBO = new EmployerBO();
		EmployerLoginVO employerLoginVO = new EmployerLoginVO();

		employerLoginVO.setId(employerBO.getId());
		EmployerLoginBO empLogin = new EmployerLoginBO();
		try {
			employerLoginVO = employerDAO.getEmployerLogin(employerLoginVO);

			empBO.setCompanyLogo(employerLoginVO
					.getEmployerRegistration()
					.getCompanyLogo()
					.getBytes(
							1,
							(int) employerLoginVO.getEmployerRegistration()
									.getCompanyLogo().length()));
			empBO.setConfirmEmailAddress(employerVO.getConfirmEmailAddress());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return empBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.EmployerService#retrivecount()
	 */
	@Override
	public List<EmployerBO> retrivecount() {
		return employerDAO.jobpostcount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.EmployerService#getProductList()
	 */
	@Override
	public List<ProductBO> getProductList() {
		List<ProductBO> productBOList = null;
		try {
			productBOList = employerDAO.getProductList();
		} catch (Exception e) {
			e.printStackTrace();
			productBOList = new ArrayList<ProductBO>();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}

		}
		return productBOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#feedback(com.myjobkart.bo.FeedbackBO
	 * )
	 */
	@Override
	public FeedbackBO feedbackBO(FeedbackBO feedbackBO) {
		// TODO Auto-generated method stub
		try {
			FeedbackVO feedback = new FeedbackVO();
			feedback = EmployerServiceImpl.preparableBOtoVO(feedbackBO);
			feedback = employerDAO.feedbackVO(feedback);

			if (null != feedback) {

				if (AdminServiceImpl.isApproval) {
					this.sendFeedbackEmail(feedback);
				}
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
		return feedbackBO;
	}

	private void sendFeedbackEmail(FeedbackVO feedback) {
		try {
			final SendMail sendMail = new SendMail();

			final String toaddress = feedback.getEmail();
			final String subject = "Myjobkart:FeedBack Details!";
			String bodycontent = "FeedBack";
			model.setPhoneno(feedback.getPhoneno());
			model.setSubject(feedback.getSubject());
			model.setArea(feedback.getArea());
			model.setDetails(feedback.getDetails());
			model.setFirstname(feedback.getName());
			model.setEmail(feedback.getEmail());
			emailManager.sendEmail(toaddress, subject, bodycontent, model);
		} catch (final Exception ex) {
			AdminServiceImpl.LOGGER.debug(ex,
					"Client Email to the Customer Failed!");
		}
	}

	private static FeedbackVO preparableBOtoVO(FeedbackBO feedbackBO) {
		// TODO Auto-generated method stub
		FeedbackVO feedback = new FeedbackVO();
		feedback.setName(feedbackBO.getName());
		feedback.setEmail(feedbackBO.getEmail());
		feedback.setPhoneno(feedbackBO.getPhoneno());
		feedback.setSubject(feedbackBO.getSubject());
		feedback.setArea(feedbackBO.getArea());
		feedback.setDetails(feedbackBO.getDetails());
		feedback.setIsDelete(false);
		return feedback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#empprofileStatusActive(com.myjobkart
	 * .bo.EmployerLoginBO)
	 */
	@Override
	public boolean empprofileStatusActive(EmployerLoginBO login) {

		boolean loginChanged = false;

		EmployerVO employerVO = new EmployerVO();

		if (null != login.getEmailAddress()) {
			BeanUtils.copyProperties(login, employerVO);
			employerVO.setId(login.getId());
			employerVO = this.employerDAO.empprofileStatusActive(employerVO);
			if (null != employerVO.getEmailAddress()) {
				loginChanged = true;
			}
		}
		return loginChanged;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.EmployerService#jobbyExperience()
	 */
	@Override
	public List<JobPostBO> jobbyExperience() {
		EmployerServiceImpl.LOGGER.entry();
		List<JobPostBO> jobByExp = null;
		try {
			jobByExp = this.employerDAO.jobbyExperience();

		} catch (final Exception e) {
			jobByExp = new ArrayList<JobPostBO>();
			e.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}

		}

		EmployerServiceImpl.LOGGER.exit();
		return jobByExp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.EmployerService#jobbyLocation()
	 */
	@Override
	public List<JobPostBO> jobbyLocation() {
		EmployerServiceImpl.LOGGER.entry();
		// List<JobPostBO> jobByLocation = new ArrayList<JobPostBO>();
		List<JobPostBO> jobByLocation = null;
		try {
			jobByLocation = this.employerDAO.jobbyLocation();

		} catch (final Exception e) {
			e.printStackTrace();
			jobByLocation = new ArrayList<JobPostBO>();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}

		EmployerServiceImpl.LOGGER.exit();
		return jobByLocation;
	}

	public boolean emailVerification(String email) {
		return employerDAO.emailVerification(email);
	}

	public boolean Mobile_verifications(String mobileNumber) {
		return employerDAO.Mobile_verifications(mobileNumber);
	}

	@Override
	public List<EmployerActivityBO> retriveEmpactivity(
			EmployerActivityBO employerActivityBO) {
		return employerDAO.retriveEmpactivity(employerActivityBO);
	}
	
	

	@Override
	public boolean findbyEmailSubuser(String emailAdress)
			throws MyJobKartException {
		boolean result = false;
		try {
			result = employerDAO.findbyEmailSubuser(emailAdress);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public boolean createSubuserProfile(EmployerSubuserBO profile)
			throws SQLException {
		EmployerServiceImpl.LOGGER.entry();
		boolean isProfile = false;

		try {
			EmployerSubuserVO employerSubuserVO = new EmployerSubuserVO();

			employerSubuserVO = EmployerServiceImpl.preparableBOtoVO(profile);
			EmployerLoginVO employerLogin = new EmployerLoginVO();
			EmployerVO employerVO = new EmployerVO();
			employerVO.setId(profile.getEmpRegid());
			employerLogin.setEmployerRegistration(employerVO);
			employerLogin.setId(profile.getEmployerId());
			employerSubuserVO.setEmployerLogin(employerLogin);
			long profileId = this.employerDAO
					.createSubuserprofile(employerSubuserVO);
			if (profileId > 0) {
				isProfile = true;
			}
		} catch (final BeansException jb) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}

		}
		EmployerServiceImpl.LOGGER.exit();
		return isProfile;
	}

	@Override
	public List<EmployerSubuserBO> retriveSubusers(
			EmployerSubuserBO retriveprofile) {
		EmployerServiceImpl.LOGGER.entry();

		try {
			List<EmployerSubuserBO> boList = this.employerDAO
					.retriveSubusers(retriveprofile);
			return boList;

		} catch (final Exception e) {
			e.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isInfoEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#updateSubuser(com.myjobkart.bo.
	 * EmployerSubuserBO)
	 */
	// @Override
	public EmployerSubuserBO updateSubuser(EmployerSubuserBO updatesubuser)
			throws MyJobKartException, SQLException {
		EmployerServiceImpl.LOGGER.entry();

		try {
			
			EmployerSubuserVO vo = employerDAO.getSubuser(updatesubuser);
			vo.setFirstName(updatesubuser.getFirstName());
			vo.setLastName(updatesubuser.getLastName());
			vo.setEmailAddress(updatesubuser.getEmailAddress());
			vo.setPassword(updatesubuser.getPassword());
			vo.setConfirmPassword(updatesubuser.getPassword());
			vo.setEmailAddress(updatesubuser.getEmailAddress());
			vo.setConfirmEmailAddress(updatesubuser.getConfirmEmailAddress());
			vo.setContactNumber(updatesubuser.getContactNumber());
			
			
			boolean status = employerDAO.updateSubuser(vo);
			if (status) {
				updatesubuser.setResponse(SuccessMsg.UPDATE_SUCCESS);
			} else {
				updatesubuser.setResponse(null);
			}

		} catch (final Exception e) {
			e.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isInfoEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return updatesubuser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#deleteSubuser(com.myjobkart.bo.
	 * EmployerSubuserBO)
	 */
	// @Override
	public EmployerSubuserBO subuserdelete(EmployerSubuserBO deleteSubuser)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		try {
			int result = this.employerDAO.subuserdelete(deleteSubuser);
			if (result != 0) {
				deleteSubuser.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteSubuser.setErrorCode(je.getErrorCode());
			deleteSubuser.setErrorMessage(je.getErrorMessage());
		}

		return deleteSubuser;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#retriveSubuser(com.myjobkart.bo
	 * .EmployerSubuserBO)
	 */
	@Override
	public EmployerSubuserBO retriveSubuser(EmployerSubuserBO retriveprofile)
			throws MyJobKartException {

		return employerDAO.retriveSubuser(retriveprofile);
	}

	@Override
	public long retriveCompanyId(List<String> companyName) {
		return this.employerDAO.retriveCompanyId(companyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.EmployerService#getSubuserCount(long)
	 */
	@Override
	public long getSubuserCount(long id) {
		return employerDAO.getSubuserCount(id);
	}

	private static EmployerSubuserVO preparableBOtoVO(EmployerSubuserBO profile)
			throws SQLException {
		EmployerServiceImpl.LOGGER.entry();
		EmployerSubuserVO profileVO = null;

		try {
			profileVO = new EmployerSubuserVO();
			BeanUtils.copyProperties(profile, profileVO);
			profileVO.setId(profile.getId());
			profileVO.setCreatedBy(profile.getEmployerId());
			profileVO.setModifiedBy(profile.getEmployerId());
			EmployerLoginVO loginVO = new EmployerLoginVO();
			loginVO.setId(profile.getEmployerId());
			profileVO.setEmployerLogin(loginVO);
		} catch (final NullPointerException ne) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return profileVO;

	}

	@Override
	public void updateOrder(BookingBO orderBO, Payment payment) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			BookingVO orderVO = new BookingVO();

			orderVO = employerDAO.getOrderByOrderId(orderBO.getId());
			EmployerVO employerVO = new EmployerVO();
			orderVO.setPaymentMode(orderBO.getPaymentMode());
			orderVO.setTransactionId(payment.getId());
			orderVO.setOrderAmount(payment.getTransactions().get(0).getAmount()
					.getTotal());
			orderVO.setPaymentStatus(payment.getState());
			orderVO.setTransactionDate(format.format(new Date()));
			orderVO.setProductType(orderBO.getProductType());
			orderVO.setTotalMonth(orderBO.getTotalMonth());
			employerVO.setId(orderBO.getEmployerRegistration().getId());
			orderVO.setBookingId(orderBO.getId());
			employerDAO.updateOrder(orderVO);
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Update order has failed:" + ex.getMessage());
			}
			LOGGER.info("Update order has failed:" + ex.getMessage());
		}
	}

	@Override
	public BookingBO getOrderByOrderId(String orderId) {

		BookingBO bookingBO = new BookingBO();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			BookingVO bookingVO = new BookingVO();
			bookingVO = employerDAO.getOrderByOrderId(Long.parseLong(orderId));
			// Booking Vo copy from Booking bo object
			BeanUtils.copyProperties(bookingVO, bookingBO);
			bookingBO.setTransactionId(bookingVO.getTransactionId());
			bookingBO.setId(bookingVO.getBookingId());

		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Order By OrderId has failed:"
						+ ex.getMessage());
			}
			LOGGER.info("Get Order By OrderId has failed:" + ex.getMessage());
		}
		return bookingBO;
	}

	@Override
	public void updateOrderStatus(String orderId, String status) {
		employerDAO.updateOrderStatus(orderId, status);
	}

	/**
	 * This Method used to check for product purchase
	 */
	@Override
	public boolean getemployerProduct(long empReg) {

		return employerDAO.getemployerProduct(empReg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.EmployerService#viewRequirters()
	 */
	@Override
	public List<JobPostBO> viewRecruiters(String val) {
		return employerDAO.viewRecruiters(val);
	}

	@Override
	public long createWalkinprofile(List<WalkinBO> walkinList)
			throws MyJobKartException {

		EmployerServiceImpl.LOGGER.entry();
		WalkinBO walkin = new WalkinBO();
		long jobId = 0;
		try {
			List<WalkinVO> walkinVO = new ArrayList<WalkinVO>();
			WalkinVO walkins = null;

			if (null != walkinList && walkinList.size() > 0) {
				for (WalkinBO walkinBO : walkinList) {
					walkins = new WalkinVO();
					walkins.setAddress(walkinBO.getAddress());
					walkins.setCompanyName(walkinBO.getCompanyName());
					walkins.setContactNo(walkinBO.getContactNo());
					walkins.setContactPerson(walkinBO.getContactPerson());
					walkins.setCreatedBy(walkinBO.getCreatedBy());
					walkins.setModifiedBy(walkinBO.getModifiedBy());
					walkins.setCurrencyType(walkinBO.getCurrencyType());
					walkins.setDeletedBy(walkinBO.getDeletedBy());
					walkins.setDeletedDate(walkinBO.getDeletedDate());
					walkins.setFunctionArea(walkinBO.getFunctionArea());
					walkins.setIndustryType(walkinBO.getIndustryType());
					walkins.setIsActive(walkinBO.getIsActive());
					walkins.setIsDeleted(walkinBO.getIsDeleted());
					walkins.setJobDescription(walkinBO.getJobDescription());
					walkins.setJobLocation(walkinBO.getJobLocation());
					walkins.setJobResponse(walkinBO.getJobResponse());
					walkins.setJobTitle(walkinBO.getJobTitle());
					walkins.setCompanyProfile(walkinBO.getCompanyProfile());
					walkins.setJobType(walkinBO.getJobType());
					walkins.setRole(walkinBO.getRole());
					walkins.setRoleCategory(walkinBO.getRoleCategory());
					walkins.setDoctorate(walkinBO.getDoctorate());
					walkins.setKeywords(walkinBO.getKeywords());
					walkins.setMaxSalary(walkinBO.getMaxSalary());
					walkins.setMaxExp(walkinBO.getMaxExp());
					walkins.setMinExp(walkinBO.getMinExp());
					walkins.setMinSalary(walkinBO.getMinSalary());
					walkins.setNoVacancies(walkinBO.getNoVacancies());
					walkins.setPgQualification(walkinBO.getPgQualification());
					walkins.setUgQualification(walkinBO.getUgQualification());
					walkins.setPostedBy(walkinBO.getPostedBy());
					walkins.setStartDate(walkinBO.getStartDate());
					walkins.setEndDate(walkinBO.getEndDate());
					EmployerLoginVO employerLoginVO = new EmployerLoginVO();
					employerLoginVO.setId(walkinBO.getEmployerRegistion()
							.getId());
					walkins.setEmployerLogin(employerLoginVO);

					EmployerVO employerVO = new EmployerVO();
					if (null != walkinBO.getEmployerRegistion()) {
						employerVO.setId(walkinBO.getEmployerRegistion()
								.getId());
						walkins.setEmployerRegistionVO(employerVO);
					}
					CompanyVO companyVO = new CompanyVO();
					companyVO.setCompaniesId(walkinBO.getCompanyId());
					walkins.setCompanyID(companyVO);

					walkinVO.add(walkins);
				}
			}
			jobId = employerDAO.createWalkinprofile(walkinVO);
			walkin.setResponse(SuccessMsg.JOB_POST_SUCCESS);

		} catch (final MyJobKartException je) {
			je.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.JOB_POST_FAIL + je);
			}
			walkin.setErrorCode(je.getErrorCode());
			walkin.setErrorMessage(je.getErrorMessage());
		}

		return jobId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.WalkinService#retrieveWalkinJobs(com.myjobkart.
	 * bo.WalkinBO)
	 */
	@Override
	// public WalkinBO retrieveWalkinJobs(WalkinBO walkinBO) {
	public List<WalkinBO> retrieveWalkinJobs(WalkinBO walkinBO) {

		EmployerServiceImpl.LOGGER.entry();
		List<WalkinBO> walkinBOList = null;
		try {

			walkinBOList = this.employerDAO.retriveWalkinJobs(walkinBO);

		} catch (final Exception e) {
			walkinBOList = new ArrayList<WalkinBO>();
			e.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}
		return walkinBOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#retriveWalkin(com.myjobkart.bo.
	 * WalkinBO)
	 */
	@Override
	public WalkinBO retriveWalkin(WalkinBO walkinBO) {
		return employerDAO.retriveWalkin(walkinBO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#walkinStatus(com.myjobkart.bo.WalkinBO
	 * )
	 */
	@Override
	public boolean walkinStatus(WalkinBO walkinBO) {
		EmployerServiceImpl.LOGGER.entry();
		boolean status = false;
		try {
			WalkinVO walkinVO = new WalkinVO();
			BeanUtils.copyProperties(walkinBO, walkinVO);
			walkinVO = employerDAO.walkinStatus(walkinVO);
			if (0 != walkinVO.getJobId()) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		EmployerServiceImpl.LOGGER.exit();
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#deleteWalkin(com.myjobkart.bo.WalkinBO
	 * )
	 */
	@Override
	public WalkinBO deleteWalkin(WalkinBO walkinBO) throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		try {
			final WalkinVO walkinVO = new WalkinVO();
			walkinVO.setIsDeleted(walkinBO.getIsDeleted());
			walkinVO.setJobId(walkinBO.getId());
			walkinVO.setDeletedBy(walkinBO.getDeletedBy());
			walkinVO.setDeletedDate(walkinBO.getDeletedDate());
			walkinVO.setModifiedBy(walkinBO.getModifiedBy());
			final int result = this.employerDAO.deleteWalkin(walkinVO);
			if (result != 0) {
				walkinBO.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			walkinBO.setErrorCode(je.getErrorCode());
			walkinBO.setErrorMessage(je.getErrorMessage());
		}

		return walkinBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.EmployerService#updateWalkin(com.myjobkart.bo.WalkinBO
	 * )
	 */
	@Override
	public WalkinBO updateWalkin(WalkinBO walkinBO) throws MyJobKartException {

		EmployerServiceImpl.LOGGER.entry();
		try {
			WalkinVO walkinPost = new WalkinVO();
			walkinPost.setJobId(walkinBO.getId());

			WalkinVO vo = this.employerDAO.getwalkinpost(walkinPost);
			if (vo.getJobId() == walkinBO.getId()) {
				BeanUtils.copyProperties(walkinBO, vo);
				vo.setAddress(walkinBO.getAddress());
				vo.setCompanyName(walkinBO.getCompanyName());
				vo.setContactNo(walkinBO.getContactNo());
				vo.setContactPerson(walkinBO.getContactPerson());
				vo.setCurrencyType(walkinBO.getCurrencyType());
				vo.setFunctionArea(walkinBO.getFunctionArea());
				vo.setIndustryType(walkinBO.getIndustryType());
				vo.setJobDescription(walkinBO.getJobDescription());
				vo.setJobLocation(walkinBO.getJobLocation());
				vo.setJobResponse(walkinBO.getJobResponse());
				vo.setJobTitle(walkinBO.getJobTitle());
				vo.setCompanyProfile(walkinBO.getCompanyProfile());
				vo.setJobType(walkinBO.getJobType());
				vo.setRole(walkinBO.getRole());
				vo.setRoleCategory(walkinBO.getRoleCategory());
				vo.setDoctorate(walkinBO.getDoctorate());
				vo.setKeywords(walkinBO.getKeywords());
				vo.setMaxSalary(walkinBO.getMaxSalary());
				vo.setMaxExp(walkinBO.getMaxExp());
				vo.setMinExp(walkinBO.getMinExp());
				vo.setMinSalary(walkinBO.getMinSalary());
				vo.setNoVacancies(walkinBO.getNoVacancies());
				vo.setPgQualification(walkinBO.getPgQualification());
				vo.setUgQualification(walkinBO.getUgQualification());
				vo.setPostedBy(walkinBO.getPostedBy());
				vo.setStartDate(walkinBO.getStartDate());
				vo.setEndDate(walkinBO.getEndDate());
				
				boolean status = employerDAO.updateWalkinjob(vo);
				if (status) {
					walkinBO.setResponse(SuccessMsg.UPDATE_SUCCESS);
				} else {
					walkinBO.setResponse(null);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isInfoEnabled()) {
				EmployerServiceImpl.LOGGER.debug(e.getMessage() + e);
			}
		}
		EmployerServiceImpl.LOGGER.exit();
		return walkinBO;
	}

	
	
	public List<EmployerjobAlertsVO> employerjob_alert() {
		List<EmployerjobAlertsVO> employerjobList = new ArrayList<EmployerjobAlertsVO>();
		EmployerjobAlertsVO jobalertVO = new EmployerjobAlertsVO();
		List<JobPostVO> jobPostList = new ArrayList<JobPostVO>();
		List<JobseekerProfileVO> jobseekerprofileList = new ArrayList<JobseekerProfileVO>();
		JobseekerProfileVO jobseekerVO = new JobseekerProfileVO();
		long count = 001;
		String a = "jb_A00";
		String tempToAddress = null;
		String jobTitle = null;

		JobPostVO jobpostVO = new JobPostVO();
		/*jobseekerprofileList = this.employerDAO.jobseekerList();*/
		if (null != jobseekerprofileList && jobseekerprofileList.size() > 0) {
			for (JobseekerProfileVO profilevo : jobseekerprofileList) {
				jobseekerVO.setFirstName(profilevo.getFirstName());
				jobseekerVO.setEmailId(profilevo.getEmailId());
				jobseekerVO.setprofileId(profilevo.getprofileId());
				jobseekerVO.setKeySkills(profilevo.getKeySkills());
				/*jobPostList = this.employerDAO.employerjobalert(jobseekerVO
						.getKeySkills());*/
			}

			Set<JobPostVO> postVOslist = new HashSet<JobPostVO>();

			Set<String> mailList = new HashSet<String>();
			if (null != jobPostList && jobPostList.size() > 0) {
				for (JobPostVO postVO : jobPostList) {
					postVOslist.add(postVO);
					mailList.add(postVO.getEmployerLogin().getEmailAddress());
				}
			}
			boolean status = false;

			if (null != postVOslist) {

				Set<JobPostVO> postVOList = null;
				for (String mailId : mailList) {
					postVOList = new HashSet<JobPostVO>();
					for (JobPostVO volist : postVOslist) {
						if (mailId.equalsIgnoreCase(volist.getEmployerLogin()
								.getEmailAddress())) {
							postVOList.add(volist);
							if (null == jobTitle) {
								jobTitle = volist.getJobId() + "-"
										+ volist.getJobTitle();
							} else {
								jobTitle = jobTitle + "," + volist.getJobId()
										+ "-" + volist.getJobTitle();
							}
							tempToAddress = mailId;

						}
					}
				}

				try {
					employerjobList = this.employerDAO.employerjob_alert();

					if (null != employerjobList && employerjobList.size() > 0) {

						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date date = new Date();
						String cdate = format.format(date);
						for (EmployerjobAlertsVO employerjobVO : employerjobList) {
							// jobalertVO=new EmployerjobAlertsVO();
							jobalertVO.setId(employerjobVO.getId());
							jobalertVO.setJobId(employerjobVO.getJobId());
							jobalertVO.setJobName(employerjobVO.getJobName());
							jobalertVO.setNextRun(employerjobVO.getNextRun());
							if (cdate.equals(jobalertVO.getNextRun()))

							{

								EmailModel model = new EmailModel();
								String url = JobtrolleyResourceBundle
										.getValue("EmployerAlert");
								String joburl = JobtrolleyResourceBundle
										.getValue("EmployerAlertJob");
								String toaddress = tempToAddress;
								String bodyContent = "Empoyer_Alert";
								String subject = "Myjobkart: Employer Alert!";
								model.setEmailId(jobseekerVO.getEmailId());
								model.setJobtitle(jobTitle);
								model.setKeySkills(jobseekerVO.getKeySkills());
								model.setUrl(url + jobseekerVO.getprofileId());
								model.setJobUrl(joburl);
								model.setFirstname(jobseekerVO.getFirstName());
								status = emailManager.sendMailAlert(toaddress,
										subject, bodyContent, model);

								if (status) {

									status = this.employerDAO
											.addEmployerAlert(postVOList);
									// status=this.employerDAO.addNextrun();
									DateFormat df = new SimpleDateFormat(
											"yyyy-MM-DD");
									Date dt = new Date();
									String dtt = format.format(dt);
									EmployerjobAlertsVO employerjob = new EmployerjobAlertsVO();
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(date);
									calendar.add(Calendar.MONTH, 1);
									date = calendar.getTime();
									SimpleDateFormat formats = new SimpleDateFormat(
											"yyyy-MM-dd");
									String sdate = format.format(date);
									String jobId = a + count++;
									employerjob.setJobId(jobId);
									employerjob.setJobName(jobalertVO
											.getJobName());
									employerjob.setLastRun(format.format(dt));
									employerjob.setNextRun(format.format(date));
									status = this.employerDAO
											.addNextrun(employerjob);
									// System.out.println("date"+sdate);

								}
							}
						}
					} else {
						jobseekerprofileList = null;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			EmployerServiceImpl.LOGGER.exit();
		}

		return employerjobList;
	}
	
	@Override
	public PaymentBO addPayment(PaymentBO paymentBO) {
		final Calendar cal1 = new GregorianCalendar();
		final Calendar cal2 = new GregorianCalendar();

		JobSeekerServiceImpl.LOGGER.entry();
		final int month = paymentBO.getTotalMonth();
		final EntrolledSeviceVO entrolledSevice = new EntrolledSeviceVO();
		BeanUtils.copyProperties(paymentBO, entrolledSevice);
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
		if (null == entrolledSevice.getSelectProduct()
				|| entrolledSevice.getSelectProduct().isEmpty()) {
			entrolledSevice.setSelectProduct("Trail");
		} else {
			entrolledSevice.setSelectProduct(paymentBO.getSelectProduct());
		}
		this.employerDAO.addPayments(entrolledSevice);
		paymentBO.setResponse(SuccessMsg.REG_SUCCESS);
		JobSeekerServiceImpl.LOGGER.exit();
		return paymentBO;

	}

}
