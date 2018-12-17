package com.myjobkart.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.JobAlertBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobSeekerLoginBO;
import com.myjobkart.bo.JobseekerActivityBO;
import com.myjobkart.bo.JobseekerBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.SavejobBO;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.dao.JobSeekerDAO;
import com.myjobkart.dao.JobSeekerDAOImpl;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.model.EmailModel;
import com.myjobkart.utils.ErrorCodes;
import com.myjobkart.utils.SendEmailServiceImpl;
import com.myjobkart.utils.SendMail;
import com.myjobkart.utils.SuccessMsg;
import com.myjobkart.vo.AppliedJobVO;
import com.myjobkart.vo.BODTO;
import com.myjobkart.vo.CompanyVO;
import com.myjobkart.vo.EmployerAlertVO;
import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerVO;
import com.myjobkart.vo.EntrolledSeviceVO;
import com.myjobkart.vo.JobAlertVO;
import com.myjobkart.vo.JobPostVO;
import com.myjobkart.vo.JobseekerActivityVO;
import com.myjobkart.vo.JobseekerEducationVO;
import com.myjobkart.vo.JobseekerLoginVO;
import com.myjobkart.vo.JobseekerProductServiceVO;
import com.myjobkart.vo.JobseekerProfessionalVO;
import com.myjobkart.vo.JobseekerProfileVO;
import com.myjobkart.vo.JobseekerVO;
import com.myjobkart.vo.SaveJobVO;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by :
 * Sathishkumar.s Description : JobSeekerServiceImpl Class is a Class which is
 * responsible for access the data from Controller then convert it into
 * persistent Object then sent it into DAO class. Reviewed by :
 * 
 * 
 */

@Service("jobSeekerService")
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService<JobseekerBO> {

	static final JLogger LOGGER = JLogger
			.getLogger(JobSeekerServiceImpl.class);

	// DAo layer annotations
	@Autowired
	private JobSeekerDAO jobSeekerDAO;

	@Autowired
	private SendEmailServiceImpl emailManager;

	/**
	 * This method used to perform the job seeker registration function
	 * 
	 * @param jobseekerRegistrationBO
	 * @return jobSeekerRegistrationBO
	 */

	@Transactional
	@Override
	public JobseekerBO create(JobseekerBO jobseekerBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		JobseekerVO jobseekerRegistration = new JobseekerVO();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			BeanUtils.copyProperties(jobseekerBO, jobseekerRegistration);
			jobseekerRegistration.setIsActive(false);
			jobseekerRegistration.setIsDelete(true);
			jobseekerRegistration = this.jobSeekerDAO
					.create(jobseekerRegistration);
			if (null != jobseekerRegistration) {
				JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
				jbActivityVO.setActivityDate(format.format(new Date()));
				jbActivityVO.setJobseekerVO(jobseekerRegistration);
				jbActivityVO.setCreated(new Date());
				jbActivityVO.setCreatedBy(jobseekerRegistration.getId());
				jbActivityVO.setStatus("New Jobseeker");
				long activityId = jobSeekerDAO.activity(jbActivityVO);
				if (activityId != 0) {
					this.sendActivationEmail(jobseekerBO);
				}
			}
			jobseekerBO.setResponse(SuccessMsg.REG_SUCCESS);
		} catch (final MyJobKartException jbe) {
			jobseekerBO.setErrorCode(ErrorCodes.JB_REG_FAIL);
			jobseekerBO.setErrorMessage(ErrorCodes.JB_REG_FAIL_MSG);
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.JB_REG_FAIL_MSG
						+ jbe);
			}
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobseekerBO;
	}

	/**
	 * This method perform the job seeker registration approved
	 * 
	 * @param jobseekerLoginBO
	 * @return jobSeekerRegistrationBO
	 * 
	 */
	@Transactional
	@Override
	public JobSeekerLoginBO jobseekerApproved(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final JobseekerLoginVO jobseekerLogin = new JobseekerLoginVO();
		JobSeekerLoginBO loginBO = new JobSeekerLoginBO();
		try {
			BeanUtils.copyProperties(jobSeekerLoginBO, jobseekerLogin);
			this.jobSeekerDAO.jobSeekerApproved(jobseekerLogin);
			loginBO.setResponse(SuccessMsg.JB_ACC_ACTIVATION);
		} catch (final MyJobKartException jte) {
			jte.printStackTrace();
			loginBO.setErrorCode(jte.getErrorCode());
			loginBO.setErrorMessage(jte.getErrorMessage());
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(jte.getErrorMessage() + jte);
			}
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return loginBO;

	}

	/**
	 * This method perform the search and find the email id
	 * 
	 * @param emailAddress
	 * @return
	 * @throws MyJobKartException
	 */
	@Override
	public boolean findByEmail(String emailAddress) throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		JobseekerVO jobseekerRegistration = new JobseekerVO();
		try {
			jobseekerRegistration = this.jobSeekerDAO.findByEmail(
					"emailAddress", emailAddress);
			if (jobseekerRegistration != null) {
				return true;
			}
		} catch (final Exception jb) {

		}
		JobSeekerServiceImpl.LOGGER.exit();
		return false;

	}

	@Override
	public List<JobseekerBO> getAllJobseekers() {
		JobSeekerServiceImpl.LOGGER.entry();
		JobseekerBO registrationBO = null;
		List<JobseekerVO> jobList = null;
		final List<JobseekerBO> jobBOList = new ArrayList<JobseekerBO>();
		try {
			jobList = this.jobSeekerDAO.retrieveAll();
		} catch (final MyJobKartException e) {

			e.printStackTrace();
		}
		for (final JobseekerVO jobseekerRegistration : jobList) {
			registrationBO = new JobseekerBO();
			BeanUtils.copyProperties(jobseekerRegistration, registrationBO);
			jobBOList.add(registrationBO);
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobBOList;
	}

	@Override
	public List<JobseekerBO> getjobseeker(JobseekerBO jobSeekerRegistrationBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		JobseekerBO registrationBO = null;
		List<JobseekerVO> jobList = null;
		final JobseekerVO jobSeekerRegistrationVO = new JobseekerVO();
		final List<JobseekerBO> jobBOList = new ArrayList<JobseekerBO>();

		try {
			BeanUtils.copyProperties(jobSeekerRegistrationBO,
					jobSeekerRegistrationVO);

			jobList = this.jobSeekerDAO.getjobseekers(jobSeekerRegistrationVO);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		for (final JobseekerVO jobseekerRegistration : jobList) {
			registrationBO = new JobseekerBO();
			BeanUtils.copyProperties(jobseekerRegistration, registrationBO);
			jobBOList.add(registrationBO);
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobBOList;
	}

	/**
	 * This Method Used to Perform The Job seeker Login.
	 * 
	 */
	@Override
	public JobSeekerLoginBO jobseekerSignin(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			JobseekerLoginVO jobseekerLoginVO = this.jobSeekerDAO
					.jobseekerForgetPassword("emailAddress",
							jobSeekerLoginBO.getEmailAddress());
			if (null != jobseekerLoginVO
					&& jobseekerLoginVO.getPassword().equals(
							jobSeekerLoginBO.getPassword())) {
				BeanUtils.copyProperties(jobseekerLoginVO, jobSeekerLoginBO);
				jobSeekerLoginBO.setIsActive(true);
				jobSeekerLoginBO.setRegisterId(jobseekerLoginVO
						.getJobseekerRegistration().getId());
				jobSeekerLoginBO.setName(jobseekerLoginVO
						.getJobseekerRegistration().getFirstName());
				jobSeekerLoginBO.setPassword(jobseekerLoginVO.getPassword());
			} else {
				
				if (null != jobseekerLoginVO){
				BeanUtils.copyProperties(jobseekerLoginVO, jobSeekerLoginBO);
				jobSeekerLoginBO.setRegisterId(jobseekerLoginVO
						.getJobseekerRegistration().getId());
				}
				jobSeekerLoginBO.setIsActive(false);
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker authendicate method has failed in JobseekerServiceImpl ::"
						+ jb.getMessage());
			}
			LOGGER.info("Jobseeker authendicate method has failed in JobseekerServiceImpl ::"
					+ jb.getMessage());
		}
		return jobSeekerLoginBO;
	}

	@Override
	public boolean jobseekerForgetPassword(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException, FileNotFoundException {
		JobSeekerServiceImpl.LOGGER.entry();
		boolean loginValidated = false;
		try {
			EmailModel model = new EmailModel();
			JobseekerLoginVO jobseekerLoginVO = this.jobSeekerDAO
					.jobseekerForgetPassword("emailAddress",
							jobSeekerLoginBO.getEmailAddress());
			if (null != jobseekerLoginVO) {
				String str = JobtrolleyResourceBundle
						.getValue("JobSeekerChangePassword");
				String toaddress = jobSeekerLoginBO.getEmailAddress();
				String subject = "Myjobkart:Change Your Password for Myjobkart";
				String bodycontent = "changePassword";
				model.setUrl(str);
				model.setEmail(jobSeekerLoginBO.getEmailAddress());
				emailManager.sendEmail(toaddress, subject, bodycontent, model);
				loginValidated = true;
			}
		} catch (final MyJobKartException | MessagingException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker authendicate method has failed in JobseekerServiceImpl ::"
						+ jb.getMessage());
			}
			LOGGER.info("Jobseeker authendicate method has failed in JobseekerServiceImpl ::"
					+ jb.getMessage());
		}
		return loginValidated;
	}

	@Override
	public boolean jobseekerChangePasswordAfterLogin(
			JobSeekerLoginBO changePassword) {
		JobSeekerServiceImpl.LOGGER.entry();
		boolean loginChanged = false;
		if (null != changePassword.getEmailAddress()
				&& null != changePassword.getPassword()
				&& null != changePassword.getConfirmPassword()) {
			JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();
			BeanUtils.copyProperties(changePassword, jobseekerLoginVO);
			jobseekerLoginVO = this.jobSeekerDAO
					.jobseekerChangePasswordAfterLogin(jobseekerLoginVO);
			if (null != jobseekerLoginVO.getPassword()
					&& null != changePassword.getConfirmPassword()) {
				loginChanged = true;
			}
		}
		return loginChanged;
	}

	@Override
	public boolean jobseekerActivation(String inputParam)
			throws MyJobKartException {
		boolean isActivation = false;
		try {
			JobseekerVO jobseekerVO = this.jobSeekerDAO.findByJobseekerEmail(inputParam);
			if (null != jobseekerVO) {
				JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();
				jobseekerLoginVO.setActive(true);
				jobseekerLoginVO.setConfirmPassword(jobseekerVO
						.getConfirmPassword());
				jobseekerLoginVO.setPassword(jobseekerVO.getPassword());
				jobseekerLoginVO.setEmailAddress(jobseekerVO.getEmailAddress());
				jobseekerLoginVO.setCreatedBy(jobseekerVO.getId());
				jobseekerLoginVO.setModifiedBy(jobseekerVO.getId());
				jobseekerLoginVO.setJobseekerRegistration(jobseekerVO);
				JobseekerProductServiceVO jobseekerProductServiceVO = new JobseekerProductServiceVO();
				jobseekerProductServiceVO.setProductType("jobseeker");
				jobseekerProductServiceVO.setProductCost(new BigDecimal(0));
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, 1);
				Date date = cal.getTime();
				jobseekerProductServiceVO.setGracePeriod(date);
				long jobseekerLoginId = this.jobSeekerDAO.jobseekerActivation(
						jobseekerLoginVO, jobseekerProductServiceVO);
				if (0 != jobseekerLoginId) {
					isActivation = true;
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return isActivation;

	}

	/*
	 * This Method Used To Send Mail.
	 */
	private void sendActivationEmail(JobseekerBO jobseekerBO) {
		try {
			final SendMail sendMail = new SendMail();
			EmailModel model = new EmailModel();

			final String toaddress = jobseekerBO.getEmailAddress();
			final String subject = "Myjobkart:Registration Confirmation!";
			// final String website=JobtrolleyResourceBundle.bundle("website");
			String url = JobtrolleyResourceBundle
					.getValue("JobSeekerActivation");
			String bodycontent = "accountActivationSuccess";
			// model.setWebSite(website);
			model.setUrl(url);
			model.setFirstname(jobseekerBO.getFirstName());
			model.setEmail(jobseekerBO.getEmailAddress());
			emailManager.sendEmail(toaddress, subject, bodycontent, model);
		} catch (final Exception ex) {
			JobSeekerServiceImpl.LOGGER.debug(ex,
					"Registeration Email to the Customer Failed!");
		}
	}

	/**
	 * This Method used to perform the job seeker create profile.
	 * 
	 */
	@Override
	public JobseekerProfileBO jobseekerCreateProfile(JobseekerProfileBO profile) {
		JobSeekerServiceImpl.LOGGER.entry();
		boolean isProfile = false;

		try {

			List<JobseekerEducationVO> JobseekerEducationVOList = new ArrayList<JobseekerEducationVO>();
			List<JobseekerProfessionalVO> JobseekerProfessionalVOList = new ArrayList<JobseekerProfessionalVO>();
			JobseekerEducationVO vo;
			JobseekerProfessionalVO professionalVo;

			for (JobseekerProfileBO jobSeekerBo : profile.getJobProfileList()) {
				vo = new JobseekerEducationVO();
				vo.setCollege(jobSeekerBo.getCollege());
				vo.setDepartment(jobSeekerBo.getDepartment());
				vo.setDegree(jobSeekerBo.getDegree());
				vo.setYearOfPassing(jobSeekerBo.getYearOfPassing());
				vo.setGrade(jobSeekerBo.getGrade());
				vo.setPercentage(jobSeekerBo.getPercentage());
				JobseekerEducationVOList.add(vo);
			}
			if (null != profile.getJobseekerProfileList()
					&& !profile.getJobseekerProfileList().isEmpty()) {

				for (JobseekerProfileBO profilebo : profile
						.getJobseekerProfileList()) {
					DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					professionalVo = new JobseekerProfessionalVO();
					professionalVo.setCompanyName(profilebo.getCompanyName());
					professionalVo.setDesignation(profilebo.getDesignation());
					professionalVo.setCompanyType(profilebo.getCompanyType());
					professionalVo.setExpEndDate(format.parse(profilebo
							.getEndDate()));
					professionalVo.setExpStartDate(format.parse(profilebo
							.getStartDate()));
					professionalVo.setLastSalary(profilebo.getLastSalary());
					professionalVo.setExpStatus(profilebo.getExpStatus());
					CompanyVO companyVO = new CompanyVO();
					companyVO.setCompaniesId(profilebo.getCompanyId());
					professionalVo.setCompanyVO(companyVO);
					JobseekerProfessionalVOList.add(professionalVo);
				}
			}
			JobseekerProfileVO jobseekerProfileVO = new JobseekerProfileVO();

			jobseekerProfileVO = JobSeekerServiceImpl.preparableBOtoVO(profile);
			jobseekerProfileVO
					.setJobseekerEducationVO(JobseekerEducationVOList);

			if (null != JobseekerProfessionalVOList
					&& !JobseekerProfessionalVOList.isEmpty()) {
				jobseekerProfileVO
						.setJobseekerProfessionalVO(JobseekerProfessionalVOList);
			}
			jobseekerProfileVO.setCreatedBy(profile.getCreatedBy());
			jobseekerProfileVO.setNoOfExperience(profile.getNoOfExperience());
			JobseekerVO jobseekerVO = new JobseekerVO();
			jobseekerVO.setId(profile.getModifiedBy());
			jobseekerProfileVO.setJobSeekerRegistration(jobseekerVO);
			jobseekerProfileVO = jobSeekerDAO
					.jobseekerCreateProfile(jobseekerProfileVO);

			/*if (null != jobseekerProfileVO) {
				List<JobPostVO> jobPostList = new ArrayList<JobPostVO>();
				jobPostList = this.jobSeekerDAO
						.employerAlert(jobseekerProfileVO.getKeySkills());
				boolean status = false;
				EmployerAlertVO employerVO = null;
				List<EmployerAlertVO> employerAlertList = new ArrayList<EmployerAlertVO>();
				if (null != jobPostList) {
					for (JobPostVO volist : jobPostList)

					{
						JobPostBO jobpostBO = new JobPostBO();
						JobPostVO jobpostVO = new JobPostVO();
						employerVO = new EmployerAlertVO();
						employerVO.setKeySkills(volist.getKeywords());
						employerVO.setEmailAddress(volist.getEmployerLogin().getEmailAddress());
								
						employerVO.setActive(volist.getIsActive());
						jobpostVO.setJobId(volist.getJobId());
						employerVO.setJobpost(jobpostVO);
						EmployerLoginVO loginVO = new EmployerLoginVO();
						loginVO.setId(volist.getEmployerLogin().getId());
						employerVO.setEmpLogin(loginVO);

						jobpostBO.setEmailId(volist.getEmployerLogin()
								.getEmailAddress());
						String toaddress = jobpostBO.getEmailId();
						String bodyContent = "Empoyer_Alert";
						String subject = "Myjobkart: Employer Alert!";

						status = emailManager.sendMailAlert(toaddress, subject,
								bodyContent, jobpostBO);
						if (status) {
							employerAlertList.add(employerVO);
							employerVO = this.jobSeekerDAO
									.addEmployerAlert(employerAlertList);
						}
					}
				}
				
			}*/
		profile.setResponse(SuccessMsg.UPDATE_SUCCESS);
		} catch (final Exception jb) {
			jb.printStackTrace();
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}

		}
		JobSeekerServiceImpl.LOGGER.exit();
		return profile;
	}

	@Override
	public JobseekerProfileBO retriveJobseeker() throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		JobseekerProfileBO reteriveprofile = new JobseekerProfileBO();
		try {

			final List<JobseekerProfileBO> retriveProfileList = this.jobSeekerDAO
					.retriveJobseeker();

			if (null != retriveProfileList && !retriveProfileList.isEmpty()) {

				reteriveprofile.setJobseekerProfileList(retriveProfileList);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			reteriveprofile.setErrorCode(je.getErrorCode());
			reteriveprofile.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return reteriveprofile;

	}
	/**
	 * 
	 * This Method Used to perform the job seeker retrieve the saved job
	 * details.
	 * 
	 */

	@Override
	public SavejobBO reteriveSavedJobs(SavejobBO savejobBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final SavejobBO saveJobBO = new SavejobBO();
		try {
			final List<SavejobBO> retriveProfileList = this.jobSeekerDAO
					.reteriveSavedJobs(savejobBO);
			if (null != retriveProfileList && !retriveProfileList.isEmpty()) {
				saveJobBO.setJobPostList(retriveProfileList);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			saveJobBO.setErrorCode(je.getErrorCode());
			saveJobBO.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return saveJobBO;

	}

	@Override
	public SavejobBO reteriveSavedJob(SavejobBO savejobBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			final List<SavejobBO> retriveProfileList = this.jobSeekerDAO
					.reteriveSavedJob(savejobBO);
			if (null != retriveProfileList && !retriveProfileList.isEmpty()) {
				savejobBO.setJobPostList(retriveProfileList);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			savejobBO.setErrorCode(je.getErrorCode());
			savejobBO.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return savejobBO;
	}

	@Override
	public AppliedJobBO reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			List<AppliedJobBO> retriveProfileList = this.jobSeekerDAO
					.reteriveAppliedJobs(appliedJobBO);
			if (null != retriveProfileList && !retriveProfileList.isEmpty()) {
				appliedJobBO.setJobPostList(retriveProfileList);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			appliedJobBO.setErrorCode(je.getErrorCode());
			appliedJobBO.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return appliedJobBO;
	}

	@Override
	public JobseekerProfileBO updateProfile(JobseekerProfileBO profile)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			JobseekerProfileVO profileVO = new JobseekerProfileVO();
			profileVO.setprofileId(profile.getId());
			profileVO = jobSeekerDAO.getprofileId(profileVO);
			if (null != profileVO && profileVO.getprofileId() != 0
					&& profileVO.getprofileId() == profile.getId()) {
				profileVO.setprofileId(profile.getId());
				profileVO.setFirstName(profile.getFirstName());
				profileVO.setLastName(profile.getLastName());
				profileVO.setEmailId(profile.getEmailId());
				profileVO.setPhone(profile.getPhone());
				profileVO.setMaritalStatus(profile.getMaritalStatus());
				profileVO.setGender(profile.getGender());
				profileVO.setAddress(profile.getAddress());
				profileVO.setPincode(profile.getPincode());
				profileVO.setLocation(profile.getLocation());
				profileVO.setState(profile.getState());
				profileVO.setNationality(profile.getNationality());
				profileVO.setJobType(profile.getJobType());
				profileVO.setKeySkills(profile.getKeySkills());
				profileVO.setDomainSkills(profile.getDomainSkills());
				profileVO.setFunction(profile.getFunction());
				profileVO.setCurrentSalary(profile.getCurrentSalary());
				profileVO.setExpectedCtc(profile.getExpectedCtc());
				profileVO
						.setProfiledescription(profile.getProfiledescription());
				profileVO.setLanguagesKnown(profile.getLanguagesKnown());
				profileVO.setPreferredIndustry(profile.getPreferredIndustry());
				profileVO.setPreferredLocation(profile.getPreferredLocation());
				profileVO.setProfileDescriptions(profile
						.getProfileDescriptions());
				profileVO.setUploadResume(profile.getUploadResume());
				profileVO.setProfileImage(profile.getProfileImage());
				profileVO.setNoOfExperience(profile.getNoOfExperience());
			}
			this.jobSeekerDAO.updateProfile(profileVO);
			profile.setResponse(SuccessMsg.UPDATE_SUCCESS);
		} catch (final Exception jb) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}
		}
		return profile;
	}

	@Override
	public JobseekerProfileBO eduProfile(JobseekerProfileBO profile)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			JobseekerEducationVO educationVO = new JobseekerEducationVO();
			educationVO.setEducationId(profile.getEducationId());
			educationVO = jobSeekerDAO.getEducation(educationVO);
			if (profile.getEducationId() != 0
					&& educationVO.getEducationId() == profile.getEducationId()) {
				educationVO.setCreatedBy(profile.getProfesId());
				educationVO.setEducationId(profile.getEducationId());
				educationVO.setCollege(profile.getCollege());
				educationVO.setDepartment(profile.getDepartment());
				educationVO.setDegree(profile.getDegree());
				educationVO.setYearOfPassing(profile.getYearOfPassing());
				educationVO.setGrade(profile.getGrade());
				educationVO.setPercentage(profile.getPercentage());
			}
			this.jobSeekerDAO.eduProfile(educationVO);
			profile.setResponse(SuccessMsg.UPDATE_SUCCESS);
		} catch (final Exception jb) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}
		}
		return profile;
	}

	@Override
	public JobseekerProfileBO expDetails(JobseekerProfileBO profile)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			JobseekerProfessionalVO professionalVO = new JobseekerProfessionalVO();
			professionalVO.setProfessionalId(profile.getProfesId());
			professionalVO = jobSeekerDAO.getExperience(professionalVO);
			if (profile.getProfesId() != 0
					&& professionalVO.getProfessionalId() == profile
							.getProfesId()) {
				professionalVO.setProfessionalId(profile.getProfesId());
				professionalVO.setCompanyName(profile.getCompanyName());
				CompanyVO companyVO = new CompanyVO();
				companyVO.setCompaniesId(profile.getCompanyId());
				professionalVO.setCompanyVO(companyVO);
				professionalVO.setDesignation(profile.getDesignation());
				professionalVO.setCompanyType(profile.getCompanyType());
				professionalVO.setLastSalary(profile.getLastSalary());
				professionalVO.setExpStartDate(profile.getExpStartDate());
				professionalVO.setExpEndDate(profile.getExpEndDate());
				professionalVO.setCreatedBy(profile.getCreatedBy());
				professionalVO.setCreated(profile.getCreated());
				professionalVO.setVersion(profile.getVersion());
				professionalVO.setExpStatus(profile.getExpStatus());
				professionalVO.setModified(new Date());
				professionalVO.setModifiedBy(profile.getModifiedBy());
				professionalVO.setExpStatus(profile.getExpStatus());
			}
			this.jobSeekerDAO.experienceDetails(professionalVO);
			profile.setResponse(SuccessMsg.UPDATE_SUCCESS);
		} catch (final Exception jb) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}
		}
		return profile;
	}

	@Override
	public JobseekerBO updateJobseeker(JobseekerBO jobseekerBO)
			throws MyJobKartException {
		try {
			final JobseekerVO updateJobseeker = new JobseekerVO();
			updateJobseeker.setId(jobseekerBO.getId());
			updateJobseeker.setCreated(jobseekerBO.getCreated());
			updateJobseeker.setCreatedBy(jobseekerBO.getCreatedBy());
			updateJobseeker.setModifiedBy(jobseekerBO.getModifiedBy());
			updateJobseeker.setFirstName(jobseekerBO.getFirstName());
			updateJobseeker.setLastName(jobseekerBO.getLastName());
			updateJobseeker.setEmailAddress(jobseekerBO.getEmailAddress());
			updateJobseeker.setConfirmEmailAddress(jobseekerBO
					.getConfirmEmailAddress());
			updateJobseeker.setVersion(jobseekerBO.getVersion());
			updateJobseeker.setPassword(jobseekerBO.getPassword());
			updateJobseeker
					.setConfirmPassword(jobseekerBO.getConfirmPassword());
			updateJobseeker.setMobileNo(jobseekerBO.getMobileNo());
			updateJobseeker.setIsActive(jobseekerBO.getIsActive());
			updateJobseeker.setTermsConditionsAgreed(jobseekerBO
					.getTermsConditionsAgreed());
			updateJobseeker.setProfileImage(jobseekerBO.getProfileImage());
			updateJobseeker.setIsDelete(jobseekerBO.getIsDeleted());
			this.jobSeekerDAO.updateJobseeker(updateJobseeker);
			jobseekerBO.setResponse(SuccessMsg.UPDATE_SUCCESS);
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.UPDATE_FAIL + je);
			}
			jobseekerBO.setErrorCode(je.getErrorCode());
			jobseekerBO.setErrorMessage(je.getErrorMessage());
		}

		return jobseekerBO;
	}

	/**
	 * 
	 * This Method Used To Perform The Job seeker Delete Profile.
	 * 
	 */

	@Override
	public JobseekerProfileBO jobseekerDeleteProfile(
			JobseekerProfileBO deleteProfile) throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();

		try {
			final JobseekerProfileVO jobseekerProfileVO = new JobseekerProfileVO();
			jobseekerProfileVO.setIsDelete(deleteProfile.getIsDelete());
			jobseekerProfileVO.setprofileId(deleteProfile.getId());
			jobseekerProfileVO.setModifiedBy(deleteProfile.getModifiedBy());
			jobseekerProfileVO.setDeleteBy(deleteProfile.getDeleteBy());
			jobseekerProfileVO.setDeletedDate(deleteProfile.getDeletedDate());
			final int result = this.jobSeekerDAO
					.jobseekerDeleteProfile(jobseekerProfileVO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteProfile.setErrorCode(je.getErrorCode());
			deleteProfile.setErrorMessage(je.getErrorMessage());
		}
		return deleteProfile;
	}

	@Override
	public JobseekerBO deleteJobseeker(JobseekerBO deleteProfile)
			throws MyJobKartException {
		try {
			final JobseekerVO jobseekerVO = new JobseekerVO();
			jobseekerVO.setIsDelete(deleteProfile.getIsDeleted());
			jobseekerVO.setId(deleteProfile.getId());
			jobseekerVO.setModifiedBy(deleteProfile.getModifiedBy());
			jobseekerVO.setDeleteBy(deleteProfile.getDeletedBy());
			jobseekerVO.setDeletedDate(deleteProfile.getDeletedDate());
			final int result = this.jobSeekerDAO.deleteJobseeker(jobseekerVO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			deleteProfile.setErrorCode(je.getErrorCode());
			deleteProfile.setErrorMessage(je.getErrorMessage());
		}

		return deleteProfile;
	}

	/**
	 * This Method Used To Perform The Delete the save job details.
	 * 
	 */
	@Override
	public SavejobBO jobseekerDeleteSavedJobs(SavejobBO savejobBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			final SaveJobVO saveJobVO = new SaveJobVO();
			saveJobVO.setIsDeleted(savejobBO.getIsDeleted());
			saveJobVO.setApplicationid(savejobBO.getId());
			saveJobVO.setDeletedBy(savejobBO.getDeletedBy());
			saveJobVO.setDeletedDate(savejobBO.getDeletedDate());
			saveJobVO.setModifiedBy(savejobBO.getModifiedBy());
			final int result = this.jobSeekerDAO
					.jobseekerDeleteSavedJobs(saveJobVO);
			if (result != 0) {
				savejobBO.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			savejobBO.setErrorCode(je.getErrorCode());
			savejobBO.setErrorMessage(je.getErrorMessage());
		}

		JobSeekerServiceImpl.LOGGER.exit();
		return savejobBO;
	}

	/**
	 * This Method used to perform the delete the applied job details.
	 * 
	 */

	@Override
	public AppliedJobBO jobseekerDeleteAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			final AppliedJobVO appliedJobVO = new AppliedJobVO();
			appliedJobVO.setIsDeleted(appliedJobBO.getIsDeleted());
			appliedJobVO.setApplicationid(appliedJobBO.getId());
			appliedJobVO.setDeletedBy(appliedJobBO.getDeletedBy());
			appliedJobVO.setDeletedDate(appliedJobBO.getDeletedDate());
			appliedJobVO.setModifiedBy(appliedJobBO.getModifiedBy());
			final int result = this.jobSeekerDAO
					.jobseekerDeleteAppliedJobs(appliedJobVO);
			if (result != 0) {
				appliedJobBO.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			appliedJobBO.setErrorCode(je.getErrorCode());
			appliedJobBO.setErrorMessage(je.getErrorMessage());
		}

		JobSeekerServiceImpl.LOGGER.exit();
		return appliedJobBO;
	}

	@Override
	public JobPostBO JobByTitle(JobPostBO jobPostBO) throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		JobPostBO jobPostList = new JobPostBO();
		try {
			jobPostList = this.jobSeekerDAO.JobByTitle(jobPostBO);

		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			jobPostBO.setErrorCode(je.getErrorCode());
			jobPostBO.setErrorMessage(je.getErrorMessage());
		}
		return jobPostList;

	}

	@Override
	public JobPostBO jobTitleCount(JobPostBO jobPostBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		JobPostBO jobPostList = new JobPostBO();
		try {
			jobPostList = this.jobSeekerDAO.jobTitleCount(jobPostBO);

		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			jobPostBO.setErrorCode(je.getErrorCode());
			jobPostBO.setErrorMessage(je.getErrorMessage());
		}
		return jobPostList;

	}

	@Override
	public JobPostBO joblocationCount(JobPostBO jobPostBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		JobPostBO jobPostList = new JobPostBO();
		try {
			jobPostList = this.jobSeekerDAO.joblocationCount(jobPostBO);

		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			jobPostBO.setErrorCode(je.getErrorCode());
			jobPostBO.setErrorMessage(je.getErrorMessage());
		}
		return jobPostList;

	}



	@Override
	public JobPostBO searchJob(JobPostBO jobPostBO) throws MyJobKartException {
		LOGGER.entry();
		JobPostBO jobPostList = new JobPostBO();
		try {
			jobPostList = jobSeekerDAO.searchJob(jobPostBO);

		} catch (MyJobKartException je) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.JOB_SEARCH001_MSG + je);
			}
		}
		return jobPostList;

	}

	@SuppressWarnings("null")
	@Override
	public EmployerProfileBO companyDetails(long employerId)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		EmployerProfileBO employerProfileBO = null;
		try {
			employerProfileBO = this.jobSeekerDAO.companyDetails(employerId);

		} catch (final MyJobKartException je) {
			je.printStackTrace();
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			employerProfileBO.setErrorCode(je.getErrorCode());
			employerProfileBO.setErrorMessage(je.getErrorMessage());
		}
		return employerProfileBO;

	}

	/**
	 * This method used to perform the job seeker applied jobs.
	 * 
	 */
	
	@Override
	public boolean jobseekerAppliedJob(AppliedJobBO appliedJobBO, long saveJobId) {
		JobSeekerServiceImpl.LOGGER.entry();
		boolean isProfile = false;

		try {
			AppliedJobVO appliedJobVO = new AppliedJobVO();

			appliedJobVO = JobSeekerServiceImpl.appliedBOtoVO(appliedJobBO);
			EmployerVO employerVO = new EmployerVO();
			employerVO.setId(appliedJobBO.getEmployerRegistration().getId());
			appliedJobVO.setEmployerRegistration(employerVO);
			final long applicationId = jobSeekerDAO.jobseekerAppliedJob(appliedJobVO,
					saveJobId);
			if (0 != applicationId) {
				isProfile = true;
			}

		} catch (final Exception jb) {
			jb.printStackTrace();
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}

		}
		JobSeekerServiceImpl.LOGGER.exit();

		return isProfile;
	}

	@Override
	public boolean checkProfileId(long jobseekerId) {
		LOGGER.entry();
		boolean status = false;

		try {
			List<JobseekerProfileBO> jobseekerProfileBOList = jobSeekerDAO
					.checkProfileId(jobseekerId);

			if (null != jobseekerProfileBOList
					&& !jobseekerProfileBOList.isEmpty()) {

				status = true;
			} else {
				status = false;
			}
		} catch (Exception e) {

		}
		LOGGER.exit();
		return status;
	}

	@Override
	public boolean savedJob(SavejobBO savejobBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		boolean isProfile = false;

		try {
			SaveJobVO saveJobVO = new SaveJobVO();

			saveJobVO = JobSeekerServiceImpl.savejobBOtoVO(savejobBO);
			final long saveJobId = this.jobSeekerDAO.savedJob(saveJobVO);
			if (0 != saveJobId) {
				isProfile = true;
			}

		} catch (final Exception jb) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}

		}
		JobSeekerServiceImpl.LOGGER.exit();

		return isProfile;
	}

	private static AppliedJobVO appliedBOtoVO(AppliedJobBO appliedJobBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		AppliedJobVO appliedJobVO = null;
		try {

			appliedJobVO = new AppliedJobVO();
			appliedJobVO.setIsShortlisted(false);
			appliedJobVO.setCreatedBy(appliedJobBO.getCreatedBy());
			appliedJobVO.setModifiedBy(appliedJobBO.getModifiedBy());
			appliedJobVO.setCompanyName(appliedJobBO.getCompanyName());
			appliedJobVO.setJobLocation(appliedJobBO.getJobLocation());
			appliedJobVO.setPostedBy(appliedJobBO.getPostedBy());
			appliedJobVO.setJobTitle(appliedJobBO.getJobTitle());
			appliedJobVO.setAddress(appliedJobBO.getAddress());
			appliedJobVO.setContactNo(appliedJobBO.getContactNo());
			appliedJobVO.setContactPerson(appliedJobBO.getContactPerson());
			appliedJobVO.setFunctionArea(appliedJobBO.getFunctionArea());
			appliedJobVO.setIndustryType(appliedJobBO.getIndustryType());
			appliedJobVO.setJobDescription(appliedJobBO.getJobDescription());
			appliedJobVO.setKeywords(appliedJobBO.getKeywords());
			appliedJobVO.setMaxSalary(appliedJobBO.getMaxSalary());
			appliedJobVO.setMinSalary(appliedJobBO.getMinSalary());
			appliedJobVO.setMaxExp(appliedJobBO.getMaxExp());
			appliedJobVO.setMinExp(appliedJobBO.getMinExp());
			appliedJobVO.setPgQualification(appliedJobBO.getPgQualification());
			appliedJobVO.setUgQualification(appliedJobBO.getUgQualification());
			appliedJobVO.setOtherSalaryDetails(appliedJobBO
					.getOtherSalaryDetails());
			appliedJobVO.setJobResponse(appliedJobBO.getJobResponse());
			appliedJobVO.setCurrencyType(appliedJobBO.getCurrencyType());
			final JobPostVO jobPostVO = new JobPostVO();
			jobPostVO.setJobId(appliedJobBO.getJobpostBO().getId());
			appliedJobVO.setJobpostVO(jobPostVO);
			final EmployerLoginVO employerLoginVO = new EmployerLoginVO();
			employerLoginVO.setId(appliedJobBO.getEmployerLogin().getId());
			appliedJobVO.setEmployerLogin(employerLoginVO);

			final JobseekerLoginVO jobSeekerLoginVO = new JobseekerLoginVO();

			jobSeekerLoginVO.setId(appliedJobBO.getJobseekerLogin().getId());
			appliedJobVO.setJobseekerLogin(jobSeekerLoginVO);
			appliedJobVO.setIsDeleted(appliedJobBO.getIsDeleted());

		} catch (final NullPointerException ne) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return appliedJobVO;
	}

	private static SaveJobVO savejobBOtoVO(SavejobBO savejobBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		SaveJobVO saveJobVO = null;
		try {

			saveJobVO = new SaveJobVO();
			saveJobVO.setCreatedBy(savejobBO.getCreatedBy());
			saveJobVO.setModifiedBy(savejobBO.getModifiedBy());
			saveJobVO.setCompanyName(savejobBO.getCompanyName());
			saveJobVO.setJobLocation(savejobBO.getJobLocation());
			saveJobVO.setPostedBy(savejobBO.getPostedBy());
			saveJobVO.setJobTitle(savejobBO.getJobTitle());
			saveJobVO.setAddress(savejobBO.getAddress());
			saveJobVO.setContactNo(savejobBO.getContactNo());
			saveJobVO.setContactPerson(savejobBO.getContactPerson());
			saveJobVO.setFunctionArea(savejobBO.getFunctionArea());
			saveJobVO.setIndustryType(savejobBO.getIndustryType());
			saveJobVO.setJobDescription(savejobBO.getJobDescription());
			saveJobVO.setKeywords(savejobBO.getKeywords());
			saveJobVO.setMaxSalary(savejobBO.getMaxSalary());
			saveJobVO.setMinSalary(savejobBO.getMinSalary());
			saveJobVO.setMaxExp(savejobBO.getMaxExp());
			saveJobVO.setMinExp(savejobBO.getMinExp());
			saveJobVO.setPgQualification(savejobBO.getPgQualification());
			saveJobVO.setUgQualification(savejobBO.getUgQualification());
			saveJobVO.setOtherSalaryDetails(savejobBO.getOtherSalaryDetails());
			saveJobVO.setJobResponse(savejobBO.getJobResponse());
			saveJobVO.setCurrencyType(savejobBO.getCurrencyType());
			saveJobVO.setIsDeleted(savejobBO.getIsDeleted());
			final JobPostVO jobPostVO = new JobPostVO();
			jobPostVO.setJobId(savejobBO.getJobpostBO().getId());
			saveJobVO.setJobpostVO(jobPostVO);
			EmployerVO employerVO = new EmployerVO();
			employerVO.setId(savejobBO.getEmployerRegistration().getId());
			saveJobVO.setEmployerRegistration(employerVO);
			final EmployerLoginVO employerLoginVO = new EmployerLoginVO();
			employerLoginVO.setId(savejobBO.getEmployerLogin().getId());
			saveJobVO.setEmployerLogin(employerLoginVO);
			saveJobVO.setIsApply(true);
			final JobseekerLoginVO jobSeekerLoginVO = new JobseekerLoginVO();

			jobSeekerLoginVO.setId(savejobBO.getJobseekerLogin().getId());
			saveJobVO.setJobseekerLogin(jobSeekerLoginVO);

		} catch (final NullPointerException ne) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return saveJobVO;
	}

	private static JobseekerProfileVO preparableBOtoVO(
			JobseekerProfileBO profileBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		JobseekerProfileVO profileVO = null;
		try {
			profileVO = new JobseekerProfileVO();
			profileVO.setprofileId(profileBO.getId());
			profileVO.setState(profileBO.getState());
			profileVO.setPincode(profileBO.getPincode());
			profileVO.setVersion(profileBO.getVersion());
			profileVO.setCreatedBy(profileBO.getCreatedBy());
			profileVO.setCurrentIndustry(profileBO.getCurrentIndustry());
			profileVO.setCurrentSalary(profileBO.getCurrentSalary());
			profileVO.setDeleteBy(profileBO.getDeleteBy());
			profileVO.setDeletedDate(profileBO.getDeletedDate());
			profileVO.setDomainSkills(profileBO.getDomainSkills());
			profileVO.setAddress(profileBO.getAddress());
			profileVO.setEmailId(profileBO.getEmailId());
			profileVO.setExpectedCtc(profileBO.getExpectedCtc());
			profileVO.setExperienceInMonth(profileBO.getExperienceInMonth());
			profileVO.setFirstName(profileBO.getFirstName());
			profileVO.setFunction(profileBO.getFunction());
			profileVO.setGender(profileBO.getGender());
			profileVO.setIsActive(profileBO.getIsActive());
			profileVO.setIsDelete(profileBO.getIsDelete());
			final JobseekerLoginVO jobseekerLogin = new JobseekerLoginVO();
			jobseekerLogin.setId(profileBO.getJobSeekerLogin().getId());
			profileVO.setJobSeekerLogin(jobseekerLogin);
			profileVO.setJobType(profileBO.getJobType());
			profileVO.setKeySkills(profileBO.getKeySkills());
			profileVO.setLanguagesKnown(profileBO.getLanguagesKnown());
			profileVO.setLastName(profileBO.getLastName());
			profileVO.setLocation(profileBO.getLocation());
			profileVO.setMaritalStatus(profileBO.getMaritalStatus());
			profileVO.setModifiedBy(profileBO.getModifiedBy());
			profileVO.setNationality(profileBO.getNationality());
			profileVO.setPhone(profileBO.getPhone());
			profileVO.setPreferredIndustry(profileBO.getPreferredIndustry());
			profileVO.setPreferredLocation(profileBO.getPreferredLocation());
			profileVO.setProfileImage(profileBO.getProfileImage());
			profileVO.setResumeTitle(profileBO.getResumeTitle());
			profileVO.setUploadResume(profileBO.getUploadResume());
			profileVO.setExperienceInYear(profileBO.getExperienceInYear());
			profileVO.setProfiledescription(profileBO.getProfiledescription());
			profileVO
					.setProfileDescriptions(profileBO.getProfileDescriptions());
		} catch (final NullPointerException ne) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return profileVO;

	}

	@Override
	public boolean profileStatus(JobseekerProfileBO jobseekerProfileBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		boolean loginChanged = false;

		JobseekerProfileVO jobseekerProfileVO = new JobseekerProfileVO();

		if (null != jobseekerProfileBO.getEmailId()) {
			BeanUtils.copyProperties(jobseekerProfileBO, jobseekerProfileVO);
			jobseekerProfileVO.setprofileId(jobseekerProfileBO.getId());
			jobseekerProfileVO = this.jobSeekerDAO
					.profileStatus(jobseekerProfileVO);
			if (null != jobseekerProfileVO.getEmailId()) {
				loginChanged = true;
			}
		}
		return loginChanged;
	}

	@Override
	public JobPostBO refineResult(JobPostBO jobPostBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			final List<JobPostBO> jobPostList = this.jobSeekerDAO
					.refineResult(jobPostBO);

			jobPostBO.setJobPostList(jobPostList);
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			jobPostBO.setErrorCode(je.getErrorCode());
			jobPostBO.setErrorMessage(je.getErrorMessage());
		}
		return jobPostBO;

	}

	@Override
	public JobPostBO jobsearchlist(JobPostBO jobPostBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		JobPostBO jobpostBO = new JobPostBO();
		try {

			jobpostBO = this.jobSeekerDAO.jobsearchlist(jobPostBO);

			if (null != jobpostBO) {
				jobPostBO
						.setJobPostSearchList(jobpostBO.getJobPostSearchList());
				jobPostBO.setJobPostList(jobpostBO.getJobPostList());
			}

		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			jobPostBO.setErrorCode(je.getErrorCode());
			jobPostBO.setErrorMessage(je.getErrorMessage());
		}
		return jobPostBO;

	}

	@Override
	public void create(List<BODTO<JobseekerBO>> list) throws MyJobKartException {

	}

	@Override
	public void delete(Long id) throws MyJobKartException {

	}

	@Override
	public void deleteAll(List<JobseekerBO> entityList)
			throws MyJobKartException {

	}

	@Override
	public void update(JobseekerBO entity) throws MyJobKartException {

	}

	@Override
	public void update(List<BODTO<JobseekerBO>> list) throws MyJobKartException {

	}

	@Override
	public JobseekerBO findByCriteria(JobseekerBO entity)
			throws MyJobKartException {
		return null;
	}

	@Override
	public JobseekerBO findByParam(String entityParam, String entityParamValue)
			throws MyJobKartException {
		return null;
	}

	@Override
	public List<JobseekerBO> findByDate(Date fDate, Date tDate)
			throws MyJobKartException {
		return null;
	}

	@Override
	public JobseekerBO findById(Long long1) throws MyJobKartException {
		return null;
	}

	@Override
	public JobseekerProfileBO retriveJobseeker(
			JobseekerProfileBO reteriveprofile) throws MyJobKartException,
			SerialException, SQLException {
		JobSeekerServiceImpl.LOGGER.entry();
		JobseekerProfileBO reterive = null;
		try {
			reterive = jobSeekerDAO.retriveJobseeker(reteriveprofile);
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			reterive = new JobseekerProfileBO();
			reterive.setErrorCode(je.getErrorCode());
			reterive.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return reterive;
	}

	@SuppressWarnings("unused")
	private static SaveJobVO preparable(SavejobBO jobPostBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		SaveJobVO jobPostVO = null;

		try {
			jobPostVO = new SaveJobVO();
			jobPostVO.setApplicationid(jobPostBO.getId());
			jobPostVO.setAddress(jobPostBO.getAddress());
			jobPostVO.setCompanyName(jobPostBO.getCompanyName());

			jobPostVO.setContactNo(jobPostBO.getContactNo());
			jobPostVO.setContactPerson(jobPostBO.getContactPerson());
			jobPostVO.setCreatedBy(jobPostBO.getCreatedBy());
			jobPostVO.setCurrencyType(jobPostBO.getCurrencyType());
			jobPostVO.setDeletedBy(jobPostBO.getDeletedBy());
			jobPostVO.setDeletedDate(jobPostBO.getDeletedDate());
			final EmployerLoginVO employerLoginVO = new EmployerLoginVO();
			employerLoginVO.setId(jobPostBO.getEmployerLogin().getId());
			jobPostVO.setEmployerLogin(employerLoginVO);
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

			// jobPostVO.setPhoto(jobPostBO.getPhoto());
			// jobPostVO.setPresentation(jobPostBO.getPresentation());
			jobPostVO.setPostedBy(jobPostBO.getPostedBy());
		} catch (final NullPointerException ne) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobPostVO;

	}

	@Override
	public boolean applied(long id) throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		AppliedJobVO appliedJobVO = new AppliedJobVO();
		try {
			appliedJobVO = this.jobSeekerDAO.applied(id);
			if (appliedJobVO != null) {
				return true;
			}
		} catch (final Exception jb) {

		}
		JobSeekerServiceImpl.LOGGER.exit();
		return false;

	}

	@Override
	public boolean savejob(long id) throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		SaveJobVO appliedJobVO = new SaveJobVO();
		try {
			appliedJobVO = this.jobSeekerDAO.savejob(id);
			if (appliedJobVO != null) {
				return true;
			}
		} catch (final Exception jb) {

		}
		JobSeekerServiceImpl.LOGGER.exit();
		return false;

	}

	@Override
	public JobseekerBO retriveRegisteredJobseeker() throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final JobseekerBO jobseekerBO = new JobseekerBO();
		try {

			final List<JobseekerBO> retriveRegisterList = jobSeekerDAO
					.retriveRegisteredJobseeker();

			if (null != retriveRegisterList && !retriveRegisterList.isEmpty()) {
				jobseekerBO.setRegisteredList(retriveRegisterList);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			jobseekerBO.setErrorCode(je.getErrorCode());
			jobseekerBO.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobseekerBO;
	}

	@Override
	public JobseekerBO retriveRegisteredJobseeker(long registerId)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final JobseekerBO jobseekerBO = new JobseekerBO();
		try {

			final List<JobseekerBO> retriveRegisterList = this.jobSeekerDAO
					.retriveRegisteredJobseeker(registerId);

			if (null != retriveRegisterList && !retriveRegisterList.isEmpty()) {

				jobseekerBO.setRegisteredList(retriveRegisterList);

			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			jobseekerBO.setErrorCode(je.getErrorCode());
			jobseekerBO.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobseekerBO;
	}

	@Override
	public JobseekerProfileBO retrieveJobseekerProfile()
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final JobseekerProfileBO reteriveprofile = new JobseekerProfileBO();
		try {

			final List<JobseekerProfileBO> retriveProfileList = this.jobSeekerDAO
					.retrieveJobseekerProfile();

			if (null != retriveProfileList && !retriveProfileList.isEmpty()) {
				reteriveprofile.setJobseekerProfileList(retriveProfileList);

			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			reteriveprofile.setErrorCode(je.getErrorCode());
			reteriveprofile.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return reteriveprofile;
	}

	@Override
	public JobPostBO appliedJobSearch(JobPostBO jobPostBO)
			throws MyJobKartException {

		JobSeekerServiceImpl.LOGGER.entry();
		final List<JobPostBO> jobPoscompany = this.jobSeekerDAO
				.appliedJobSearch(jobPostBO);
		jobPostBO.setJobPostList(jobPoscompany);

		return jobPostBO;
	}

	@Override
	public JobPostBO appliedJobSearchDate(JobPostBO jobPostBO)
			throws MyJobKartException {

		final List<JobPostBO> jobPoscompany = this.jobSeekerDAO
				.appliedJobSearchDate(jobPostBO);
		jobPostBO.setJobPostList(jobPoscompany);

		return jobPostBO;
	}

	@Override
	public JobseekerBO renewalRegisteredJobseeker() throws MyJobKartException {

		JobSeekerServiceImpl.LOGGER.entry();
		final JobseekerBO jobseekerBO = new JobseekerBO();
		try {

			final List<JobseekerBO> retriveRegisterList = this.jobSeekerDAO
					.renewalRegisteredJobseeker();

			if (null != retriveRegisterList && !retriveRegisterList.isEmpty()) {

				jobseekerBO.setRegisteredList(retriveRegisterList);

			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			jobseekerBO.setErrorCode(je.getErrorCode());
			jobseekerBO.setErrorMessage(je.getErrorMessage());
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobseekerBO;

	}

	@Override
	public boolean profileStatus1(JobseekerProfileBO jobseekerProfileBO) {
		boolean loginChanged = false;

		JobseekerVO jobseekerProfileVO = new JobseekerVO();

		if (0 != jobseekerProfileBO.getId()) {
			BeanUtils.copyProperties(jobseekerProfileBO, jobseekerProfileVO);
			jobseekerProfileVO.setId(jobseekerProfileBO.getId());
			jobseekerProfileVO.setIsActive(jobseekerProfileBO.getIsActive());
			jobseekerProfileVO = this.jobSeekerDAO
					.profileStatus1(jobseekerProfileVO);
			if (0 != jobseekerProfileBO.getId()) {
				loginChanged = true;
			}
		}
		return loginChanged;

	}

	@Override
	public boolean activeJobseeker(JobSeekerLoginBO loginBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		boolean loginActivated = false;
		try {
			JobseekerLoginVO loginVO = new JobseekerLoginVO();
			BeanUtils.copyProperties(loginBO, loginVO);
			loginVO.setEmailAddress(loginBO.getEmailAddress());
			loginVO.setPassword(loginBO.getPassword());
			loginVO.setActive(true);
			Date date = new Date();
			loginVO.setCreated(date);
			loginVO.setCreatedBy(loginBO.getId());
			loginVO.setModifiedBy(loginBO.getId());
			JobseekerVO jobVO = new JobseekerVO();
			jobVO.setId(loginBO.getId());
			loginVO.setJobseekerRegistration(jobVO);
			final JobseekerProductServiceVO jobseekerProductServiceVO = new JobseekerProductServiceVO();
			jobseekerProductServiceVO.setProductType("jobseeker");
			jobseekerProductServiceVO.setProductCost(new BigDecimal(0));

			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 1);
			final Date date1 = cal.getTime();

			jobseekerProductServiceVO.setGracePeriod(date1);
			loginVO = jobSeekerDAO.activeJobseeker(loginVO,
					jobseekerProductServiceVO);

			if (null != loginVO) {
				loginActivated = true;
			}
		} catch (final Exception je) {
			je.printStackTrace();
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
		}
		return loginActivated;

	}

	

	

	@Override
	public EmployerProfileBO employeeDetail(ViewJobseekerBO empdetail) {
		return this.jobSeekerDAO.employeeDetail(empdetail);

	}

	@Override
	public JobseekerBO renewalJobseekerAlert(String email) {
		JobSeekerServiceImpl.LOGGER.entry();
		final JobseekerBO jobseekerBO = new JobseekerBO();
		final List<JobseekerBO> retriveRegisterList = this.jobSeekerDAO
				.renerenewalJobseekerAlert(email);
		if (null != retriveRegisterList && !retriveRegisterList.isEmpty()) {
			jobseekerBO.setRegisteredList(retriveRegisterList);
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobseekerBO;
	}

	@Override
	public PaymentBO productsEnrolledList(long registerId) {
		JobSeekerServiceImpl.LOGGER.entry();
		final PaymentBO paymentBO = new PaymentBO();
		final List<PaymentBO> productsList = this.jobSeekerDAO
				.productsEnrolledList(registerId);
		paymentBO.setEnrolledList(productsList);
		JobSeekerServiceImpl.LOGGER.exit();
		return paymentBO;
	}

	@Override
	public PaymentBO deleteJobseekerEnrolledDetails(PaymentBO savejobBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			final EntrolledSeviceVO saveJobVO = new EntrolledSeviceVO();
			saveJobVO.setEntrolledid(savejobBO.getId());
			saveJobVO.setIsDeleted(savejobBO.getIsDeleted());
			saveJobVO.setDeletedDate(savejobBO.getDeletedDate());
			final int result = this.jobSeekerDAO
					.deleteJobseekerEnrolledDetails(saveJobVO);
			if (result != 0) {
				savejobBO.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final Exception je) {
			je.printStackTrace();
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return savejobBO;
	}

	@Override
	public PaymentBO lastMonthPaymentList(long registerId) {
		// TODO Auto-generated method stub
		JobSeekerServiceImpl.LOGGER.entry();
		final PaymentBO paymentBO = new PaymentBO();
		final List<PaymentBO> productsList = this.jobSeekerDAO
				.lastMonthPaymentList(registerId);

		if (null != productsList && !productsList.isEmpty()) {

			paymentBO.setEnrolledList(productsList);
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return paymentBO;
	}

	/*
	 * This Method Used To Retrieve The Jobseeker Payment.
	 */

	@Override
	public PaymentBO retriveJobseekerPayment() throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final PaymentBO reterivepayment = new PaymentBO();
		final List<PaymentBO> retrivePaymentList = this.jobSeekerDAO
				.retriveJobseekerPayment();

		if (null != retrivePaymentList && !retrivePaymentList.isEmpty()) {

			reterivepayment.setJobseekerPaymentList(retrivePaymentList);
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return reterivepayment;
	}

	/*
	 * This Method Used To Retrieve The Job seeker saved Jobs.
	 */

	@Override
	public SavejobBO retriveJobseekersSavedJobs() throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final SavejobBO saveJobBO = new SavejobBO();
		final List<SavejobBO> retriveProfileList = this.jobSeekerDAO
				.retriveJobseekersSavedJobs();

		if (null != retriveProfileList && !retriveProfileList.isEmpty()) {

			saveJobBO.setAdminSaveJobList(retriveProfileList);

		}
		JobSeekerServiceImpl.LOGGER.exit();
		return saveJobBO;

	}

	/*
	 * This Method Used To Retrieve The job seeker Applied Jobs.
	 */

	@Override
	public AppliedJobBO retriveJobseekersAppliedJobs()
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final AppliedJobBO appliedjobBO = new AppliedJobBO();
		final List<AppliedJobBO> retriveAppliedList = this.jobSeekerDAO
				.retriveJobseekersAppliedJobs();

		if (null != retriveAppliedList && !retriveAppliedList.isEmpty()) {
			appliedjobBO.setAdminAppliedJobList(retriveAppliedList);

		}
		JobSeekerServiceImpl.LOGGER.exit();
		return appliedjobBO;

	}

	/*
	 * This Method Used To Jobseeker Add Payment.
	 

	@Override
	public PaymentBO jobseekerAddPayment(PaymentBO paymentBO) {
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
		entrolledSevice.setProducType("jobseeker");
		this.employerd.addPayment(entrolledSevice);
		paymentBO.setResponse(SuccessMsg.REG_SUCCESS);
		JobSeekerServiceImpl.LOGGER.exit();
		return paymentBO;

	}*/

	/*
	 * This Method Used To Job seeker Active.
	 */
	@Override
	public JobSeekerLoginBO active(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException {
		JobseekerLoginVO jobseekerLoginVO = jobSeekerDAO
				.jobseekerForgetPassword("emailAddress",
						jobSeekerLoginBO.getEmailAddress());
		if (null != jobseekerLoginVO) {
			jobSeekerLoginBO.setIsActive(true);
		} else {
			jobSeekerLoginBO.setIsActive(false);
		}
		return jobSeekerLoginBO;
	}

	/*
	 * 
	 * This Method Used To Retrieve The Job seeker Profile.
	 */

	@Override
	public JobseekerProfileBO getJobSeekerProfile(
			JobseekerProfileBO jobSeekerProfileBO) {
		JobseekerProfileVO jobSeekerProfileVO = new JobseekerProfileVO();
		try {
			jobSeekerProfileVO.setprofileId(jobSeekerProfileBO.getProfesId());
			jobSeekerProfileVO = jobSeekerDAO.getprofileId(jobSeekerProfileVO);
			if (null != jobSeekerProfileVO) {
				jobSeekerProfileBO.setProfesId(jobSeekerProfileVO
						.getprofileId());
				if (null != jobSeekerProfileVO.getUploadResume()) {
					jobSeekerProfileBO.setUploadResume(jobSeekerProfileVO
							.getUploadResume().getBytes(
									1,
									(int) jobSeekerProfileVO.getUploadResume()
											.length()));
				}
				if (null != jobSeekerProfileVO.getProfileImage()) {
					jobSeekerProfileBO.setProfileImage(jobSeekerProfileVO
							.getProfileImage().getBytes(
									1,
									(int) jobSeekerProfileVO.getProfileImage()
											.length()));
				}
				jobSeekerProfileBO.setAddress(jobSeekerProfileVO.getAddress());
				jobSeekerProfileBO.setFirstName(jobSeekerProfileVO
						.getFirstName());
				jobSeekerProfileBO
						.setLastName(jobSeekerProfileVO.getLastName());
				jobSeekerProfileBO.setLanguagesKnown(jobSeekerProfileVO
						.getLanguagesKnown());
				jobSeekerProfileBO.setEmailId(jobSeekerProfileVO.getEmailId());
				jobSeekerProfileBO.setKeySkills(jobSeekerProfileVO
						.getKeySkills());
				jobSeekerProfileBO.setPhone(jobSeekerProfileVO.getPhone());
				jobSeekerProfileBO.setPincode(jobSeekerProfileVO.getPincode());
				jobSeekerProfileBO.setPreferredLocation(jobSeekerProfileVO
						.getPreferredLocation());
				return jobSeekerProfileBO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * This Method Used To Job seeker History.
	 */
	@Override
	public List<ViewJobseekerBO> viewJobSeekerHistory(
			ViewJobseekerBO viewJobseekerBO) {
		return jobSeekerDAO.viewJobSeekerHistory(viewJobseekerBO);
	}

	/*
	 * This Method Used To Retrieve The Job Seeker Registration.
	 */

	@Override
	public JobseekerBO getJobseekerRegistration(JobseekerBO jobSeekerBO) {

		try {
			JobseekerVO jobseekerVO = new JobseekerVO();
			JobseekerBO jobseekerBO;
			jobseekerVO.setId(jobSeekerBO.getId());
			jobseekerVO = this.jobSeekerDAO
					.getJobseekerRegistration(jobseekerVO);

			if (null != jobseekerVO) {
				jobseekerBO = new JobseekerBO();
				jobseekerBO.setId(jobseekerVO.getId());
				jobseekerBO.setCreated(jobseekerVO.getCreated());
				jobseekerBO.setPassword(jobseekerVO.getPassword());
				jobseekerBO
						.setConfirmPassword(jobseekerVO.getConfirmPassword());
				jobseekerBO.setEmailAddress(jobseekerVO.getEmailAddress());
				jobseekerBO.setConfirmEmailAddress(jobseekerVO
						.getConfirmEmailAddress());
				jobseekerBO.setIsActive(jobseekerVO.getIsActive());
				jobseekerBO.setMobileNo(jobseekerVO.getMobileNo());
				jobseekerBO.setFirstName(jobseekerVO.getFirstName());
				jobseekerBO.setLastName(jobseekerVO.getLastName());
				jobseekerBO.setTermsConditionsAgreed(jobseekerVO
						.getTermsConditionsAgreed());

				if (null != jobseekerVO.getProfileImage()) {
					jobseekerBO.setProfileImage(jobseekerVO.getProfileImage()
							.getBytes(
									1,
									(int) jobseekerVO.getProfileImage()
											.length()));
					jobseekerBO.setIsDeleted(jobseekerVO.getIsDelete());
				}
				return jobseekerBO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * This Method Used To Job seeker Status Active.
	 */
	@Override
	public boolean activeJobseekerStatus(JobSeekerLoginBO login) {
		JobSeekerServiceImpl.LOGGER.entry();
		boolean loginChanged = false;

		if (null != login.getEmailAddress()) {
			
			JobSeekerLoginBO jobseekeLoginBO = this.jobSeekerDAO.activeJobseekerStatus(login);
			if (null != jobseekeLoginBO.getEmailAddress()) {
				loginChanged = true;
			}
		}
		return loginChanged;
	}

	/*
	 * This Method Used To Retrieve The Job seeker Resume.
	 */
	@Override
	public JobseekerProfileBO retrieveJobseekerResume(JobseekerProfileBO profile)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final JobseekerProfileBO reteriveprofile = new JobseekerProfileBO();
		final List<JobseekerProfileBO> retriveProfileList = this.jobSeekerDAO
				.retrieveJobseekerResume(profile);
		if (0 != retriveProfileList.size()) {
			reteriveprofile.setJobseekerResumeList(retriveProfileList);

		}
		JobSeekerServiceImpl.LOGGER.exit();
		return reteriveprofile;
	}

	@Override
	public boolean emailVerifications(String email) {
		return jobSeekerDAO.emailVerifications(email);
	}

	public boolean Mobile_verification(String mobileNo) {
		return jobSeekerDAO.Mobile_verification(mobileNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.JobSeekerService#createJobAlert(com.myjobkart.bo
	 * .JobAlertBO)
	 */
	@Override
	public JobAlertBO createJobAlert(JobAlertBO alert)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		final JobAlertVO jobalert = new JobAlertVO();
		BeanUtils.copyProperties(alert, jobalert);
		jobalert.setExperienceInYear(alert.getExperienceInYear());
		JobseekerLoginVO loginVO = new JobseekerLoginVO();
		loginVO.setId(alert.getModifiedBy());
		jobalert.setJobSeekerLogin(loginVO);
		jobalert.setIsActived(false);
		long id = jobSeekerDAO.createJobAlert(jobalert);
		if (id > 0) {
			sendJobAlertConfirmEmail(alert);
			alert.setResponse(SuccessMsg.JOB_ALERT_SUCCESS);
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return alert;

	}

	/**
	 * @param alert
	 */
	private void sendJobAlertConfirmEmail(JobAlertBO alert) {

		try {
			// final SendMail sendEmail = new SendMail();
			EmailModel model = new EmailModel();

			String toaddress = alert.getEmailId();
			String subject = "Myjobkart:Job Alert Creation Confirmation!";
			String url = JobtrolleyResourceBundle.getValue("JobAlertCreation");
			String bodycontent = "JobAlertCreationSuccess";
			model.setUrl(url);
			model.setEmail(alert.getEmailId());
			model.setKeySkills(alert.getKeySkills());
			model.setAlertName(alert.getAlertName());
			model.setJobType(alert.getJobType());
			model.setPreferedlocation(alert.getPreferredLocation());
			model.setRole(alert.getRole());
			model.setExperienceInYear(alert.getExperienceInYear());
			emailManager.sendEmail(toaddress, subject, bodycontent, model);
		} catch (final Exception ex) {
			JobSeekerServiceImpl.LOGGER.debug(ex,
					"Creation Success Email to the Customer Failed!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.service.JobSeekerService#retriveJobAlert(com.myjobkart.
	 * bo.JobAlertBO)
	 */
	@Override
	public List<JobAlertBO> retriveJobAlert(JobAlertBO retrivealert) {
		return jobSeekerDAO.retriveJobAlert(retrivealert);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.JobSeekerService#deleteAlert(com.myjobkart.bo.
	 * JobAlertBO)
	 */
	@Override
	public JobAlertBO deleteAlert(JobAlertBO jobAlertBO)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			final int result = this.jobSeekerDAO.deleteAlert(jobAlertBO);
			if (result != 0) {
				jobAlertBO.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (final MyJobKartException je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.DELETE_FAIL + je);
			}
			jobAlertBO.setErrorCode(je.getErrorCode());
			jobAlertBO.setErrorMessage(je.getErrorMessage());
		}
		return jobAlertBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.JobSeekerService#alertStatus(com.myjobkart.bo.
	 * JobAlertBO)
	 */
	@Override
	public boolean jobalertStatus(JobAlertBO jobalertBO) {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			JobAlertVO jobalertVO = new JobAlertVO();
			jobalertVO.setIsActived(jobalertBO.getIsActive());
			jobalertVO.setId(jobalertBO.getId());
			jobalertVO = this.jobSeekerDAO.jobalertStatus(jobalertVO);
			if (jobalertVO != null) {
				return true;
			} else {
				return false;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.service.JobSeekerService#alrtDetails(com.myjobkart.bo.
	 * JobAlertBO)
	 */
	@Override
	public JobAlertBO updateAlert(JobAlertBO updateJobAlert)
			throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		try {
			JobAlertVO alertVO = new JobAlertVO();
			alertVO.setId(updateJobAlert.getId());
			JobAlertVO VO = jobSeekerDAO.updateAlertid(alertVO);
			if (null != VO) {
				if (VO.getId() == updateJobAlert.getId()) {
					BeanUtils.copyProperties(updateJobAlert, VO);
					VO.setId(updateJobAlert.getId());
					VO.setModifiedBy(updateJobAlert.getJobSeekerId());
					VO.setCreatedBy(updateJobAlert.getJobSeekerId());
					VO.setExperienceInYear(updateJobAlert.getExperienceInYear());
					JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();
					jobseekerLoginVO.setId(updateJobAlert.getJobSeekerId());
					VO.setJobSeekerLogin(jobseekerLoginVO);
					boolean status = jobSeekerDAO.updateAlert(VO);
					if (status) {
						updateJobAlert.setResponse(SuccessMsg.UPDATE_SUCCESS);
					} else {
						updateJobAlert.setResponse(null);
					}
				}
			}
		} catch (final Exception jb) {

			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}
		}

		return updateJobAlert;
	}

	@Override
	public JobAlertBO retriveAlert(JobAlertBO alertBO)
			throws MyJobKartException {
		return jobSeekerDAO.retriveAlert(alertBO);
	}

	@Override
	public JobseekerProfileBO retriveJobseekerBO(long companyId) {
		JobSeekerServiceImpl.LOGGER.entry();
		JobseekerProfileBO reteriveprofiledata = new JobseekerProfileBO();
		try {

			final List<JobseekerProfileBO> retriveProfiledata = this.jobSeekerDAO
					.retriveJobseekerBO(companyId);
			if (null != retriveProfiledata && !retriveProfiledata.isEmpty()) {
				reteriveprofiledata.setJobseekerProfileList(retriveProfiledata);
			}
		} catch (Exception je) {
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}

		}
		JobSeekerServiceImpl.LOGGER.exit();
		return reteriveprofiledata;
	}

	@Override
	public JobAlertBO createAlert(JobAlertBO jobalert)
			throws MyJobKartException {
		try {

			JobseekerProfileVO jobseekerProfileVO = new JobseekerProfileVO();
			JobseekerVO jobseekerRegistration = new JobseekerVO();
			JobseekerEducationVO jobseekerEducation = new JobseekerEducationVO();
			JobseekerProfessionalVO jobseekerProfessional = new JobseekerProfessionalVO();

			// Jobseeker Registration
			jobseekerRegistration.setFirstName(jobalert.getFirstName());
			jobseekerRegistration.setPassword(jobalert.getPassword());
			jobseekerRegistration.setEmailAddress(jobalert.getEmailId());
			jobseekerRegistration.setIsActive(true);
			jobseekerRegistration.setIsDelete(true);

			if (null != jobalert.getEmailId()) {
				jobseekerRegistration.setConfirmEmailAddress(jobalert
						.getEmailId());
			}
			if (null != jobalert.getPassword()) {
				jobseekerRegistration
						.setConfirmPassword(jobalert.getPassword());
			}
			if (null != jobalert.getCreated()) {
				jobseekerRegistration.setCreated(jobalert.getCreated());
			}
			if (0 < jobalert.getCreatedBy()) {
				jobseekerRegistration.setCreatedBy(jobalert.getCreatedBy());
			}

			if (null != jobalert.getModified()) {
				jobseekerRegistration.setModified(jobalert.getModified());
			}
			if (0 < jobalert.getModifiedBy()) {
				jobseekerRegistration.setModifiedBy(jobalert.getModifiedBy());
			}
			if (null != jobalert.getProfileImage()) {
				jobseekerRegistration.setProfileImage(jobalert
						.getProfileImage());
			}
			jobseekerRegistration.setTermsConditionsAgreed(true);

			jobseekerRegistration = jobSeekerDAO.create(jobseekerRegistration);

			JobseekerBO jobseekerBO = new JobseekerBO();
			jobseekerBO.setEmailAddress(jobalert.getEmailId());
			jobseekerBO.setFirstName(jobalert.getFirstName());
			this.sendActivationEmail(jobseekerBO);

			jobalert.setResponse(SuccessMsg.REG_SUCCESS);

			// Jobseeker Profile
			if (jobseekerRegistration.getId() != 0) {

				List<JobseekerEducationVO> JobseekerEducationVOList = new ArrayList<JobseekerEducationVO>();
				List<JobseekerProfessionalVO> JobseekerProfessionalVOList = new ArrayList<JobseekerProfessionalVO>();

				// jobseekerProfileVO.setJobSeekerLogin(jobseekerLoginVO);
				jobseekerProfileVO
						.setJobSeekerRegistration(jobseekerRegistration);
				jobseekerProfileVO.setFirstName(jobalert.getFirstName());
				jobseekerProfileVO.setEmailId(jobalert.getEmailId());
				jobseekerProfileVO.setCreatedBy(jobalert.getCreatedBy());
				jobseekerProfileVO.setNoOfExperience(Long.parseLong(jobalert
						.getExperienceInYear()));
				jobseekerProfileVO.setJobType(jobalert.getJobType());
				jobseekerProfileVO.setKeySkills(jobalert.getKeySkills());
				jobseekerProfileVO.setPreferredIndustry(jobalert
						.getPreferredIndustry());
				jobseekerProfileVO.setPreferredLocation(jobalert
						.getPreferredLocation());
				jobseekerProfileVO.setExpectedCtc(Long.parseLong(jobalert
						.getSalary()));
				jobseekerProfileVO.setIsActive(true);
				jobseekerProfileVO.setIsDelete(true);
				jobseekerProfileVO.setNationality("Indian");
				jobseekerProfileVO.setProfileImage(jobalert.getProfileImage());

				jobseekerEducation.setDegree(jobalert.getDegree());
				jobseekerEducation
						.setYearOfPassing(jobalert.getYearOfPassing());
				jobseekerEducation.setCreated(jobalert.getCreated());
				jobseekerEducation.setModified(jobalert.getModified());
				JobseekerEducationVOList.add(jobseekerEducation);
				jobseekerProfileVO
						.setJobseekerEducationVO(JobseekerEducationVOList);

				jobseekerProfessional.setExperienceInMonth(Integer
						.parseInt(jobalert.getExperienceInYear()));
				jobseekerProfessional.setCreated(jobalert.getCreated());
				jobseekerProfessional.setModified(jobalert.getModified());
				JobseekerProfessionalVOList.add(jobseekerProfessional);
				jobseekerProfileVO
						.setJobseekerProfessionalVO(JobseekerProfessionalVOList);

				jobseekerProfileVO = jobSeekerDAO
						.jobseekerCreateProfile(jobseekerProfileVO);
			}

			// Jobseeker JobAlert
			if (jobseekerRegistration.getId() != 0) {
				JobAlertVO jobalertVO = new JobAlertVO();

				BeanUtils.copyProperties(jobalert, jobalertVO);
				jobalertVO.setExperienceInYear(jobalert.getExperienceInYear());

				// jobalertVO.setJobSeekerLogin(jobseekerLoginVO);
				jobalertVO.setId(jobalert.getModifiedBy());
				// jobalertVO.setProfileImage(jobalert.getProfileImage());

				jobalertVO.setJobSeeker(jobseekerRegistration);
				jobalertVO.setIsActived(false);

				long id = jobSeekerDAO.createJobAlert(jobalertVO);

				if (id > 0) {
					sendJobAlertCreationMail(jobalert);
					jobalert.setResponse(SuccessMsg.JOB_ALERT_SUCCESS);
				}
			}
		} catch (final Exception jb) {
			jb.printStackTrace();
		}
		JobSeekerServiceImpl.LOGGER.exit();
		return jobalert;
	}

	private void sendJobAlertCreationMail(JobAlertBO jobalert) {
		try {
			EmailModel model = new EmailModel();

			String toaddress = jobalert.getEmailId();
			String subject = "Myjobkart:Job Alert Creation Confirmation!";
			String bodycontent = "JobAlert";
			model.setFirstname(jobalert.getFirstName());
			model.setEmailId(jobalert.getEmailId());
			model.setKeySkills(jobalert.getKeySkills());
			model.setAlertName(jobalert.getAlertName());
			model.setJobType(jobalert.getJobType());
			model.setPreferedlocation(jobalert.getPreferredLocation());
			model.setRole(jobalert.getRole());
			model.setExperienceInYear(jobalert.getExperienceInYear());
			emailManager.sendEmail(toaddress, subject, bodycontent, model);
		} catch (final Exception ex) {
			JobSeekerServiceImpl.LOGGER.debug(ex,
					"Creation Success Email to the Customer Failed!");
		}
	}

	@Override
	public JobPostBO jobSearch(JobPostBO jobPostBO) throws MyJobKartException {
		JobSeekerServiceImpl.LOGGER.entry();
		// JobPostBO jobPostList = new JobPostBO();
		JobPostBO jobPostList = null;
		try {

			jobPostList = this.jobSeekerDAO.jobSearch(jobPostBO);

		} catch (final MyJobKartException je) {

			jobPostList = new JobPostBO();
			if (JobSeekerServiceImpl.LOGGER.isDebugEnabled()) {
				JobSeekerServiceImpl.LOGGER.debug(ErrorCodes.JOB_SEARCH_FAIL
						+ je);
			}
			jobPostBO.setErrorCode(je.getErrorCode());
			jobPostBO.setErrorMessage(je.getErrorMessage());
		}
		return jobPostList;

	}

	@Override
	public SavejobBO jobseekerSavedJob(AppliedJobBO appliedJobBO) {
		final SavejobBO savejobBO = new SavejobBO();
		// List<SavejobBO> savejobList = new ArrayList<SavejobBO>();
		List<SavejobBO> savejobList = this.jobSeekerDAO
				.jobseekerSavedJob(appliedJobBO);
		if (savejobList.size() > 0) {
			savejobBO.setSavejobList(savejobList);
		}

		return savejobBO;
	}

	public AppliedJobBO jobSeekerApplied(AppliedJobBO appliedJobBO) {
		final AppliedJobBO bo = new AppliedJobBO();
		// List<AppliedJobBO> appliedJobList = new ArrayList<AppliedJobBO>();

		List<AppliedJobBO> appliedJobList = this.jobSeekerDAO
				.jobSeekerApplied(appliedJobBO);
		if (appliedJobList.size() > 0) {
			bo.setAppliedJobList(appliedJobList);
		}
		return bo;
	}
	
	@Override
	public List<JobseekerActivityBO> retriveJobseekerActivity(JobseekerActivityBO jobseekerActivityBO) {
		return jobSeekerDAO.retriveJobseekerActivity(jobseekerActivityBO);
	}
	
}
