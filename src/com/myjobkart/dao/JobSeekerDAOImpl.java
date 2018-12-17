package com.myjobkart.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchException;
import org.hibernate.search.bridge.BridgeException;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerAlertBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.JobAlertBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobSeekerLoginBO;
import com.myjobkart.bo.JobseekerActivityBO;
import com.myjobkart.bo.JobseekerBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.SavejobBO;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.service.GenericService;
import com.myjobkart.service.JobSeekerService;
import com.myjobkart.utils.ErrorCodes;
import com.myjobkart.utils.SendMail;
import com.myjobkart.vo.AppliedJobVO;
import com.myjobkart.vo.BODTO;
import com.myjobkart.vo.EmployerActivityVO;
import com.myjobkart.vo.EmployerAlertVO;
import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerProfileVO;
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
import com.myjobkart.vo.ViewJobSeekerVO;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by : Vinoth P
 * Description : JobSeekerDAOImpl is a Class which is responsible for storing
 * the data into the database Reviewed by :
 * 
 * j
 */

@Repository("jobSeekerDAOImpl")
public class JobSeekerDAOImpl extends GenericDAOImpl<JobseekerVO> implements
JobSeekerDAO {

	public JobSeekerDAOImpl() throws MyJobKartException {
		super();
		// TODO Auto-generated constructor stub
	}

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	public static final JLogger LOGGER = JLogger
			.getLogger(JobSeekerDAOImpl.class);
	JobseekerProfileBO profileBO;
	JobAlertBO alertBO;
	PaymentBO paymentBO;
	@SuppressWarnings("unused")
	private JobSeekerService<JobseekerVO> jobSeekerService;
	JobPostBO jobpostBO;

	@Override
	public void deleteAll(List<BODTO<JobseekerVO>> entityList)
			throws MyJobKartException {

	}

	@Override
	public JobseekerVO findByCriteria(JobseekerVO entity)
			throws MyJobKartException {
		return null;
	}

	@Override
	protected GenericService<JobseekerVO> getBasicService() {
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void jobSeekerApproved(JobseekerLoginVO jobSeekerLogin)
			throws MyJobKartException {
		Session session = null;
		Transaction tr = null;
		try {
			session = getSession();
			tr = session.beginTransaction();
			final JobseekerVO jobseekerRegistration = new JobseekerVO();
			session = getSession();
			jobseekerRegistration.setId(jobSeekerLogin.getId());
			final JobseekerVO jobseekerReg = (JobseekerVO) session.get(
					JobseekerVO.class, jobseekerRegistration.getId());
			jobseekerReg.setIsActive(false);
			session.update(jobseekerReg);
			session.flush();
			session.clear();
			jobSeekerLogin.setJobseekerRegistration(jobseekerReg);
			final Serializable jobseekerLogin = session.save(jobSeekerLogin);
			if (jobseekerLogin != null) {
				final SendMail sendMail = new SendMail();
				final String toaddress = jobSeekerLogin.getEmailAddress();
				final String subject = "Approval";
				final String bodyContent = "Dear "
						+ jobseekerReg.getFirstName()
						+ ","
						+ "\n\n\tClick here to conformation your registration in Myjobkart:\n"
						+ "\t http://www.scubetechnologies.co.in/faces/UserConformation.jsp?emailId="
						+ jobSeekerLogin.getEmailAddress() + "\n"
						+ "\n\n\n\n\nRegards,\nOperation Manager,\n myjobkart.";
				// sendMail.send(toaddress, subject, bodyContent);
			}

		} catch (final RuntimeException re) {
			re.printStackTrace();
			tr.rollback();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.JB_ACT_FAIL_MSG + re);
			}
			throw new MyJobKartException(ErrorCodes.JB_ACT_FAIL_MSG,
					ErrorCodes.JB_ACT_FAIL_MSG);
		} catch (final Exception e) {
			e.printStackTrace();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.JB_ACT_FAIL_MSG + e);
			}
			throw new MyJobKartException(ErrorCodes.JB_ACT_FAIL_MSG,
					ErrorCodes.JB_ACT_FAIL_MSG);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobseekerVO> getjobseekers(JobseekerVO jobseekerRegistration)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		List<JobseekerVO> ul = null;
		final Boolean isActive = false;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(JobseekerVO.class);
			if (null != jobseekerRegistration.getIsActive()
					&& isActive != jobseekerRegistration.getIsActive()) {
				cr.add(Restrictions.eq("isActive", true));
			}
			if (isActive == jobseekerRegistration.getIsActive()) {
				cr.add(Restrictions.eq("isActive", false));
			}
			ul = cr.list();
		} catch (final HibernateException he) {

		} catch (final Exception je) {

		}
		JobSeekerDAOImpl.LOGGER.exit();
		return ul;
	}

	/**
	 * This Method perform for jobsee ker forget password
	 * 
	 */
	@Override
	public JobseekerLoginVO jobseekerForgetPassword(String criteriaType,
			String emailAddress) throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		JobseekerLoginVO jobseekerloginVO = null;
		List<JobseekerLoginVO> loginList = new ArrayList<JobseekerLoginVO>();
		try {
			
			final Criteria cr = getSession().createCriteria(JobseekerLoginVO.class);
			cr.add(Restrictions.eq("emailAddress",emailAddress));
			loginList = cr.list();
			if(null != loginList && loginList.size() != 0){
				for(JobseekerLoginVO loginVO : loginList){
					jobseekerloginVO = loginVO;
				}
			}
			
		} catch (final HibernateException he) {
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerloginVO;
	}
	
	

	/**
	 * This Method Used To Perform The Job seeker change password after login.
	 * 
	 */

	@Override
	public JobseekerLoginVO jobseekerChangePasswordAfterLogin(
			JobseekerLoginVO jobseekerLoginVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		String changeLoginQuery = "UPDATE JobseekerLoginVO E set E.password = :password , E.confirmPassword = :cPassword  WHERE E.emailAddress = :emailAddress";
		try {
			final Query query = getSession().createQuery(changeLoginQuery);
			query.setParameter("password", jobseekerLoginVO.getPassword());
			query.setParameter("cPassword",
					jobseekerLoginVO.getConfirmPassword());
			query.setParameter("emailAddress",
					jobseekerLoginVO.getEmailAddress());
			final int result = query.executeUpdate();
			if (0 != result) {
				return jobseekerLoginVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return null;
	}

	@Override
	public long jobseekerActivation(JobseekerLoginVO jobseekerLoginVO,
			JobseekerProductServiceVO jobseekerProductServiceVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		long jobseekerId = 0;
		final boolean isActive = true;
		final String updateQuery = "UPDATE JobseekerVO S set"
				+ " S.isActive = :isActive," + "S.createdBy = :createdBy,"
				+ "S.modifiedBy = :modifiedBy," + "S.modified=:modified"
				+ " WHERE S.id = :id";
		try {
			getSession().saveOrUpdate(jobseekerLoginVO);
			JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
			JobseekerVO jobseekerVO = new JobseekerVO();
			if (jobseekerLoginVO.getId() != 0) {
				jobseekerId = jobseekerLoginVO.getId();
				final Query query = getSession().createQuery(updateQuery);
				query.setParameter("isActive", isActive);
				query.setParameter("createdBy", jobseekerLoginVO.getCreatedBy());
				query.setParameter("modifiedBy",
						jobseekerLoginVO.getModifiedBy());
				query.setParameter("modified", new Date());
				query.setParameter("id", jobseekerLoginVO
						.getJobseekerRegistration().getId());
				query.executeUpdate();
				jobseekerVO.setId(jobseekerLoginVO.getJobseekerRegistration()
						.getId());
				jobseekerProductServiceVO.setJobseeker(jobseekerVO);
				getSession().save(jobseekerProductServiceVO);

				jbActivityVO.setActivityDate(format.format(new Date()));
				jobseekerLoginVO.getId();
				jbActivityVO.setJobseekerLoginVO(jobseekerLoginVO);
				jbActivityVO.setJobseekerVO(jobseekerVO);
				jbActivityVO.setCreated(new Date());
				jbActivityVO.setCreatedBy(jobseekerLoginVO
						.getJobseekerRegistration().getId());
				jbActivityVO.setStatus("Activated Jobseeker");
				getSession().save(jbActivityVO);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerId;
	}

	@Override
	public List<SavejobBO> reteriveSavedJob(SavejobBO savejobBO)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		final List<SavejobBO> savejoblist = new ArrayList<SavejobBO>();
		List<SaveJobVO> savedJobVO=null;
		long totalsavedjob = 0;
		long totalactivesavedjob = 0;
		long totalapplaysavedjob = 0;
		long deactive = 0;
		try {
			final Criteria cr = getSession().createCriteria(SaveJobVO.class);
			if (0 != savejobBO.getJobId()) {
				cr.createCriteria("jobId").add(
						Restrictions.eq("jobId", savejobBO.getJobId()));

			}
			cr.add(Restrictions.eq("isDeleted", true));
			cr.add(Restrictions.eq("isApply", true));

			if (null != savejobBO.getJobseekerLogin()) {
				cr.createCriteria("jobseekerLogin").add(
						Restrictions.eq("id", savejobBO.getJobseekerLogin()
								.getId()));
				cr.add(Restrictions.eq("isDeleted", true));
				cr.add(Restrictions.eq("isApply", true));
				cr.addOrder(Order.desc("id"));
			}
			savedJobVO = cr.list();
			totalsavedjob = savedJobVO.size();
			final Criteria cr1 = getSession().createCriteria(SaveJobVO.class);
			if (null != savejobBO.getJobseekerLogin()) {
				cr1.createCriteria("jobseekerLogin").add(
						Restrictions.eq("id", savejobBO.getJobseekerLogin()
								.getId()));
				cr1.add(Restrictions.eq("isDeleted", true));
				cr1.add(Restrictions.eq("isApply", true));
			} else {
				cr1.addOrder(Order.desc("created"));
				cr1.add(Restrictions.eq("isDeleted", true));
				cr1.add(Restrictions.eq("isApply", true));
			}
			savedJobVO = cr1.list();
			totalactivesavedjob = savedJobVO.size();
			final Criteria cr2 = getSession().createCriteria(SaveJobVO.class);
			if (null != savejobBO.getJobseekerLogin()) {
				cr2.createCriteria("jobseekerLogin").add(
						Restrictions.eq("id", savejobBO.getJobseekerLogin()
								.getId()));
				cr2.add(Restrictions.eq("isApply", false));
			} else {
				cr2.addOrder(Order.desc("created"));
				cr2.add(Restrictions.eq("isApply", false));
			}
			savedJobVO = cr2.list();
			totalapplaysavedjob = savedJobVO.size();

			final Criteria cr3 = getSession().createCriteria(SaveJobVO.class);
			if (null != savejobBO.getJobseekerLogin()) {
				cr3.createCriteria("jobseekerLogin").add(
						Restrictions.eq("id", savejobBO.getJobseekerLogin()
								.getId()));
				cr3.add(Restrictions.eq("isDeleted", false));
			} else {
				cr3.addOrder(Order.desc("created"));
				cr3.add(Restrictions.eq("isDeleted", false));
			}
			savedJobVO = cr3.list();
			deactive = savedJobVO.size();
			if (0 != totalsavedjob) {
				savejobBO.setTotalsavedjob(totalsavedjob);
				savejobBO.setTotalactivesavedjob(totalactivesavedjob);
				savejobBO.setTotalapplaysavedjob(totalapplaysavedjob);
				savejobBO.setDeactive(deactive);
				savejoblist.add(savejobBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return savejoblist;
	}

	/**
	 * 
	 * This Method Used to Perform The retrieve the job seeker saved job
	 * details.
	 * 
	 */
	@Override
	public List<SavejobBO> reteriveSavedJobs(SavejobBO savejobBO)
			throws MyJobKartException {
		List<SaveJobVO> savejobvolist = new ArrayList<SaveJobVO>();
		JobSeekerDAOImpl.LOGGER.entry();
		final List<SavejobBO> savejoblist = new ArrayList<SavejobBO>();
		SavejobBO saveJobBO1;
		try {
			int sno = 0;
			Session session = getSession();
			final Criteria cr = session.createCriteria(SaveJobVO.class);

			if (null!=savejobBO.getJobseekerLogin() && 0!= savejobBO.getJobseekerLogin().getId()) {
				cr.createCriteria("jobseekerLogin").add(
						Restrictions.eq("id", savejobBO.getJobseekerLogin()
								.getId()));
			} 
			if(null != savejobBO.getJobseekerRegistration() && 0 != savejobBO.getJobseekerRegistration().getId()){
				cr.createCriteria("jobseekerLogin").createCriteria("jobseekerRegistration").add(
						Restrictions.eq("id", savejobBO.getJobseekerRegistration().getId()));
				//cr.add(Restrictions.eq("jobseekerLogin.jobseekerRegistration.id", savejobBO.getJobseekerRegistration().getId()));
			}
			if (savejobBO.getJobId() != 0) {
				cr.add(Restrictions.eq("applicationid", savejobBO.getJobId()));
			}

			if (null != savejobBO.getSearchElement()) {

				cr.add(Restrictions.ilike("jobTitle",
						"%" + savejobBO.getSearchElement() + "%"));

			}

			cr.add(Restrictions.eq("isDeleted", true));
			cr.add(Restrictions.eq("isApply", true));
			cr.addOrder(Order.desc("id"));

			savejobvolist = cr.list();
			for (final SaveJobVO saveJobVO : savejobvolist) {
				sno=sno+1;
				saveJobBO1 = new SavejobBO();
				saveJobBO1.setSno(sno);
				saveJobBO1.setSaveDate(format.format(saveJobVO.getCreated()));
				saveJobBO1.setIsDeleted(saveJobVO.getIsDeleted());
				saveJobBO1.setId(saveJobVO.getApplicationid());
				saveJobBO1.setCreatedBy(saveJobVO.getCreatedBy());
				saveJobBO1.setModifiedBy(saveJobVO.getModifiedBy());
				saveJobBO1.setCompanyName(saveJobVO.getCompanyName());
				saveJobBO1.setJobLocation(saveJobVO.getJobLocation());
				saveJobBO1.setPostedBy(saveJobVO.getPostedBy());
				saveJobBO1.setJobTitle(saveJobVO.getJobTitle());
				saveJobBO1.setAddress(saveJobVO.getAddress());
				saveJobBO1.setContactNo(saveJobVO.getContactNo());
				saveJobBO1.setContactPerson(saveJobVO.getContactPerson());
				saveJobBO1.setFunctionArea(saveJobVO.getFunctionArea());
				saveJobBO1.setIndustryType(saveJobVO.getIndustryType());
				saveJobBO1.setJobDescription(saveJobVO.getJobDescription());
				saveJobBO1.setKeywords(saveJobVO.getKeywords());
				saveJobBO1.setMaxSalary(saveJobVO.getMaxSalary());
				saveJobBO1.setMinSalary(saveJobVO.getMinSalary());
				saveJobBO1.setMaxExp(saveJobVO.getMaxExp());
				saveJobBO1.setMinExp(saveJobVO.getMinExp());
				saveJobBO1.setPgQualification(saveJobVO.getPgQualification());
				saveJobBO1.setUgQualification(saveJobVO.getUgQualification());
				saveJobBO1.setOtherSalaryDetails(saveJobVO
						.getOtherSalaryDetails());
				saveJobBO1.setJobResponse(saveJobVO.getJobResponse());
				saveJobBO1.setCurrencyType(saveJobVO.getCurrencyType());
				final JobPostBO jobPostBO = new JobPostBO();
				jobPostBO.setId(saveJobVO.getJobpostVO().getJobId());
				saveJobBO1.setJobId(jobPostBO.getId());
				saveJobBO1.setJobpostBO(jobPostBO);
				final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
				employerLoginBO.setId(saveJobVO.getEmployerLogin().getId());
				saveJobBO1.setEmployerLogin(employerLoginBO);

				final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();

				jobSeekerLoginBO.setId(saveJobVO.getJobseekerLogin().getId());
				saveJobBO1.setJobseekerLogin(jobSeekerLoginBO);
				saveJobBO1.setJobpostId(saveJobVO.getJobpostVO().getJobId());
				saveJobBO1.setEmpRegId(saveJobVO.getEmployerLogin()
						.getEmployerRegistration().getId());
				EmployerBO employerBO = new EmployerBO();
				employerBO.setId(saveJobVO.getEmployerRegistration().getId());
				saveJobBO1.setEmployerRegistration(employerBO);
				//BeanUtils.copyProperties(saveJobVO, saveJobBO1);

				savejoblist.add(saveJobBO1);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return savejoblist;

	}

	@Override
	public List<AppliedJobBO> reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		try {
			int sno = 0;
			final Criteria cr = getSession().createCriteria(AppliedJobVO.class);
			if (null!=appliedJobBO.getJobseekerLogin() && 0 != appliedJobBO.getJobseekerLogin().getId()) {
				cr.createCriteria("jobseekerLogin").add(
						Restrictions.eq("id", appliedJobBO.getJobseekerLogin()
								.getId()));
			} 
			if(null != appliedJobBO.getJobseekerRegistration() && 0 != appliedJobBO.getJobseekerRegistration().getId()){
				cr.createCriteria("jobseekerLogin").createCriteria("jobseekerRegistration").add(
						Restrictions.eq("id", appliedJobBO.getJobseekerRegistration().getId()));
			}
			if ( 0 != appliedJobBO.getId() ) {
				cr.add(Restrictions.eq("applicationid", appliedJobBO.getId()));
			}
			cr.addOrder(Order.desc("id"));
			List<AppliedJobVO> appliedjobvolist = cr.list();
			if (null != appliedjobvolist && appliedjobvolist.size() > 0) {
				List<AppliedJobBO> appliedjoblist = new ArrayList<AppliedJobBO>();
				for (AppliedJobVO appliedJobVO : appliedjobvolist) {
					sno = sno + 1;
					appliedJobBO = new AppliedJobBO();
					appliedJobBO.setSno(sno);
					appliedJobBO.setAppliedDate(format.format(appliedJobVO
							.getCreated()));
					appliedJobBO.setId(appliedJobVO.getApplicationid());
					appliedJobBO.setJobId(appliedJobVO.getJobpostVO()
							.getJobId());
					appliedJobBO.setCreated(appliedJobVO.getCreated());
					appliedJobBO.setCreatedBy(appliedJobVO.getCreatedBy());
					appliedJobBO.setModifiedBy(appliedJobVO.getModifiedBy());
					appliedJobBO.setCompanyName(appliedJobVO.getCompanyName());
					appliedJobBO.setJobLocation(appliedJobVO.getJobLocation());
					appliedJobBO.setPostedBy(appliedJobVO.getPostedBy());
					appliedJobBO.setJobTitle(appliedJobVO.getJobTitle());
					appliedJobBO.setAddress(appliedJobVO.getAddress());
					appliedJobBO.setContactNo(appliedJobVO.getContactNo());
					appliedJobBO.setContactPerson(appliedJobVO
							.getContactPerson());
					appliedJobBO
					.setFunctionArea(appliedJobVO.getFunctionArea());
					appliedJobBO
					.setIndustryType(appliedJobVO.getIndustryType());
					appliedJobBO.setJobDescription(appliedJobVO
							.getJobDescription());
					appliedJobBO.setNoVacancies(appliedJobVO.getNoVacancies());
					appliedJobBO.setKeywords(appliedJobVO.getKeywords());
					appliedJobBO.setMaxSalary(appliedJobVO.getMaxSalary());
					appliedJobBO.setMinSalary(appliedJobVO.getMinSalary());
					appliedJobBO.setMaxExp(appliedJobVO.getMaxExp());
					appliedJobBO.setMinExp(appliedJobVO.getMinExp());
					appliedJobBO.setPgQualification(appliedJobVO
							.getPgQualification());
					appliedJobBO.setIsShortlisted(appliedJobVO
							.getIsShortlisted());
					appliedJobBO.setUgQualification(appliedJobVO
							.getUgQualification());
					appliedJobBO.setOtherSalaryDetails(appliedJobVO
							.getOtherSalaryDetails());
					appliedJobBO.setJobResponse(appliedJobVO.getJobResponse());
					appliedJobBO
					.setCurrencyType(appliedJobVO.getCurrencyType());
					final JobPostBO jobPostBO = new JobPostBO();
					jobPostBO.setId(appliedJobVO.getJobpostVO().getJobId());
					appliedJobBO.setJobpostBO(jobPostBO);
					final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
					employerLoginBO.setId(appliedJobVO.getEmployerLogin()
							.getId());
					appliedJobBO.setEmployerLogin(employerLoginBO);
					EmployerBO employerBO = new EmployerBO();
					if (null != appliedJobVO.getEmployerRegistration()) {
						employerBO.setId(appliedJobVO.getEmployerRegistration()
								.getId());
						appliedJobBO.setEmployerRegistration(employerBO);
					}
					appliedJobBO.setIsDeleted(appliedJobVO.getIsDeleted());
					appliedJobBO.setEmpId(appliedJobVO.getEmployerLogin()
							.getEmployerRegistration().getId());
					final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();

					jobSeekerLoginBO.setId(appliedJobVO.getJobseekerLogin()
							.getId());
					appliedJobBO.setJobseekerLogin(jobSeekerLoginBO);
					appliedjoblist.add(appliedJobBO);
				}
				return appliedjoblist;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return null;
	}

	@Override
	public JobseekerProfileVO updateProfile(
			JobseekerProfileVO jobseekerProfileVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
		JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();

		try {
			Session session = getSession();
			session.saveOrUpdate(jobseekerProfileVO);

			if (null != jobseekerProfileVO) {
				jbActivityVO.setCreated(jobseekerProfileVO.getCreated());
				jbActivityVO.setActivityDate(format.format(jobseekerProfileVO
						.getCreated()));
				jobseekerLoginVO.setId(jobseekerProfileVO.getJobSeekerLogin()
						.getId());
				jbActivityVO.setJobseekerLoginVO(jobseekerLoginVO);
				jobseekerProfileVO.setprofileId(jobseekerProfileVO
						.getprofileId());
				jbActivityVO.setJobseekerProfileVO(jobseekerProfileVO);
				jbActivityVO.setCreatedBy(jobseekerProfileVO.getCreatedBy());
				jbActivityVO.setModifiedBy(jobseekerProfileVO.getCreatedBy());
				jbActivityVO.setStatus("Updated JobseekerProfile");
				session.saveOrUpdate(jbActivityVO);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerProfileVO;
	}

	@Override
	public JobseekerEducationVO eduProfile(
			JobseekerEducationVO jobseekerProfileVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
		JobseekerProfileVO jobseekerProfleVO = new JobseekerProfileVO();
		try {
			Session session = getSession();
			session.saveOrUpdate(jobseekerProfileVO);

			if (null != jobseekerProfileVO) {
				jbActivityVO.setCreated(jobseekerProfileVO.getCreated());
				jbActivityVO.setActivityDate(format.format(jobseekerProfileVO
						.getCreated()));
				jbActivityVO.setCreatedBy(jobseekerProfileVO.getCreatedBy());
				jbActivityVO.setModifiedBy(jobseekerProfileVO.getCreatedBy());
				jbActivityVO.setModified(new Date());
				jobseekerProfleVO.setprofileId(jobseekerProfileVO
						.getCreatedBy());
				jbActivityVO.setJobseekerProfileVO(jobseekerProfleVO);
				jbActivityVO.setStatus("Updated JobseekerProfile");

				session.saveOrUpdate(jbActivityVO);
			}
			session.flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerProfileVO;
	}

	@Override
	public JobseekerProfessionalVO experienceDetails(
			JobseekerProfessionalVO jobseekerProfileVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
		JobseekerProfileVO jobseekerProfleVO = new JobseekerProfileVO();
		try {
			Session session = getSession();
			session.saveOrUpdate(jobseekerProfileVO);

			if (null != jobseekerProfileVO) {
				jbActivityVO.setCreated(jobseekerProfileVO.getCreated());
				jbActivityVO.setActivityDate(format.format(jobseekerProfileVO
						.getCreated()));
				jbActivityVO.setCreatedBy(jobseekerProfileVO.getModifiedBy());
				jbActivityVO.setModifiedBy(jobseekerProfileVO.getModifiedBy());
				jbActivityVO.setModified(new Date());
				jobseekerProfleVO.setprofileId(jobseekerProfileVO
						.getCreatedBy());
				jbActivityVO.setJobseekerProfileVO(jobseekerProfleVO);
				jbActivityVO.setStatus("Updated JobseekerProfile");
				jbActivityVO.setVersion(jobseekerProfileVO.getVersion());

				session.saveOrUpdate(jbActivityVO);
			}
			session.flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerProfileVO;
	}

	/**
	 * This Method Used To Perform The JObseeker Delete Profile.
	 * 
	 */
	@Override
	public int jobseekerDeleteProfile(JobseekerProfileVO deleteProfile)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE JobseekerProfileVO S set"
				+ " S.isDelete = :isDelete," + "S.deletedDate = :deletedDate,"
				+ "S.deleteBy = :deleteBy," + "S.modifiedBy = :modifiedBy,"
				+ "S.modified=:modified" + " WHERE S.profileId = :profileId";
		try {
			JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
			JobseekerProfileVO jobseekerProfleVO = new JobseekerProfileVO();
			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", deleteProfile.getIsDelete());
			query.setParameter("deletedDate", deleteProfile.getDeletedDate());
			query.setParameter("deleteBy", deleteProfile.getDeleteBy());
			query.setParameter("modifiedBy", deleteProfile.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("profileId", deleteProfile.getprofileId());
			result = query.executeUpdate();

			// jobseeker Activity
			jbActivityVO.setCreated(deleteProfile.getCreated());
			jbActivityVO.setActivityDate(format.format(deleteProfile
					.getCreated()));
			jbActivityVO.setCreatedBy(deleteProfile.getModifiedBy());
			jbActivityVO.setModifiedBy(deleteProfile.getModifiedBy());
			jobseekerProfleVO.setprofileId(deleteProfile.getprofileId());
			JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();
			jobseekerLoginVO.setId(deleteProfile.getDeleteBy());
			jbActivityVO.setJobseekerLoginVO(jobseekerLoginVO);
			jbActivityVO.setJobseekerProfileVO(jobseekerProfleVO);
			jbActivityVO.setStatus("JobseekerProfile Deleted");
			getSession().saveOrUpdate(jbActivityVO);

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL
						+ he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	@Override
	public int deleteJobseeker(JobseekerVO deleteProfile)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE JobseekerVO S set"
				+ " S.isDelete = :isDelete," + "S.deletedDate = :deletedDate,"
				+ "S.deleteBy = :deleteBy," + "S.modifiedBy = :modifiedBy,"
				+ "S.modified=:modified" + " WHERE S.id = :id";
		try {
			Session session = getSession();
			final Query query = session.createQuery(deleteQuery);
			query.setParameter("isDelete", deleteProfile.getIsDelete());
			query.setParameter("deletedDate", deleteProfile.getDeletedDate());
			query.setParameter("deleteBy", deleteProfile.getDeleteBy());
			query.setParameter("modifiedBy", deleteProfile.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("id", deleteProfile.getId());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL
						+ he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	/**
	 * This Method Used To Perform The Delete The save job details.
	 * 
	 */

	@Override
	public int jobseekerDeleteSavedJobs(SaveJobVO saveJobVO)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE SaveJobVO S set"
				+ " S.isDeleted = :isDeleted,"
				+ "S.deletedDate = :deletedDate," + "S.deletedBy = :deletedBy,"
				+ "S.modifiedBy = :modifiedBy," + "S.modified=:modified"
				+ " WHERE S.applicationid = :applicationid";
		try {

			Session session = getSession();
			final Query query = session.createQuery(deleteQuery);
			query.setParameter("isDeleted", saveJobVO.getIsDeleted());
			query.setParameter("deletedDate", saveJobVO.getDeletedDate());
			query.setParameter("deletedBy", saveJobVO.getDeletedBy());
			query.setParameter("modifiedBy", saveJobVO.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("applicationid", saveJobVO.getApplicationid());
			result = query.executeUpdate();

			if (null != saveJobVO) {
				JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
				JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();
				EmployerLoginVO employerLoginVO = new EmployerLoginVO();
				JobPostVO jobPostVO = new JobPostVO();

				List<SaveJobVO> saveJobList = new ArrayList<SaveJobVO>();
				Criteria c = session.createCriteria(SaveJobVO.class);
				c.add(Restrictions.eq("applicationid",
						saveJobVO.getApplicationid()));
				saveJobList = c.list();

				// jobseeker Activity
				if (null != saveJobList && saveJobList.size() != 0) {
					for (SaveJobVO savejobVO : saveJobList) {

						jbActivityVO.setCreated(savejobVO.getCreated());
						jbActivityVO.setActivityDate(format.format(saveJobVO
								.getCreated()));
						jbActivityVO.setCreatedBy(savejobVO.getCreatedBy());
						jbActivityVO.setModifiedBy(savejobVO.getModifiedBy());
						jobPostVO.setJobId(savejobVO.getJobpostVO().getJobId());
						jbActivityVO.setJobPostVO(jobPostVO);

						jobseekerLoginVO.setId(savejobVO.getCreatedBy());
						jbActivityVO.setJobseekerLoginVO(jobseekerLoginVO);

						employerLoginVO.setId(savejobVO.getEmployerLogin()
								.getId());
						jbActivityVO.setEmployerLoginVO(employerLoginVO);

						jbActivityVO.setStatus("SavedJob Deleted");
						session.saveOrUpdate(jbActivityVO);
					}
				}
			}

		} catch (final HibernateException he) {
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL
						+ he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	/**
	 * This Method used to perform the delete the applied job details.
	 * 
	 */

	@Override
	public int jobseekerDeleteAppliedJobs(AppliedJobVO appliedJobVO)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE AppliedJobVO S set"
				+ " S.isDeleted = :isDeleted,"
				+ "S.deletedDate = :deletedDate," + "S.deletedBy = :deletedBy,"
				+ "S.modifiedBy = :modifiedBy," + "S.modified=:modified"
				+ " WHERE S.applicationid = :applicationid";
		try {
			Session session = getSession();
			final Query query = session.createQuery(deleteQuery);
			query.setParameter("isDeleted", appliedJobVO.getIsDeleted());
			query.setParameter("deletedDate", appliedJobVO.getDeletedDate());
			query.setParameter("deletedBy", appliedJobVO.getDeletedBy());
			query.setParameter("modifiedBy", appliedJobVO.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("applicationid", appliedJobVO.getApplicationid());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL
						+ he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JobPostBO jobSearch(JobPostBO jobPostBO) throws MyJobKartException {
		List<JobPostBO> countCompany = new ArrayList<JobPostBO>();
		List<JobPostBO> countLocation = new ArrayList<JobPostBO>();
		List<JobPostBO> countTitle = new ArrayList<JobPostBO>();
		Set<JobPostVO> searchList = new HashSet<JobPostVO>();

		@SuppressWarnings("unused")
		List<JobPostVO> datesearchList = new ArrayList<JobPostVO>();
		List<JobPostBO> searchJobList = new ArrayList<JobPostBO>();
		org.apache.lucene.search.Query query;
		JobPostBO countPost = new JobPostBO();
		try {

			String keySkills = jobPostBO.getKeywords();
			String location = jobPostBO.getSearchElement();
			String experience = jobPostBO.getMinExp();
			Session session = getSession();
			jobPostBO.setIsActive(true);

			// key skill Spit
			String[] parts = keySkills.split(",");
			List<String> skills = Arrays.asList(parts);

			// Location split

			String[] locationCount = location.split(",");
			List<String> locations = Arrays.asList(locationCount);

			// Experience split
			String minExp = null;
			String maxExp = null;
			String[] experiencesplit = experience.split("-");
			int experienceSearch = experiencesplit.length;

			if (experienceSearch > 1) {
				for (int i = 0; i < experienceSearch; i++) {
					minExp = experiencesplit[0];
					maxExp = experiencesplit[1];

				}
			} else {
				minExp = experiencesplit[0];

			}

			FullTextSession fullTextSession = Search
					.getFullTextSession(getSession());
			fullTextSession.createIndexer().startAndWait();
			QueryBuilder qb = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(JobPostVO.class).get();

			// For Key Skills based search

			if (null != keySkills && !keySkills.isEmpty()) {
				for (String skill : skills) {
					org.apache.lucene.search.Query luceneQuery = qb.keyword()
							.onField("keywords").matching("*" + skill + "*")
							.createQuery();

					Query hibQuery = fullTextSession.createFullTextQuery(
							luceneQuery, JobPostVO.class);

					searchList.addAll(hibQuery.list());
				}

			}

			// end key skill based search.

			// Location based search.

			if (null != location && !location.isEmpty()) {

				for (String locationss : locations) {
					org.apache.lucene.search.Query luceneQuerylocation = qb
							.keyword().onField("jobLocation")
							.matching("*" + locationss + "*").createQuery();
					Query hibQuerys = fullTextSession.createFullTextQuery(
							luceneQuerylocation, JobPostVO.class);

					searchList.addAll(hibQuerys.list());
				}
			}

			// End location based search.

			// Experience based Start
			if (null != experience && !experience.isEmpty()) {
				if (experienceSearch > 1) {

					QueryBuilder qbs = fullTextSession.getSearchFactory()
							.buildQueryBuilder().forEntity(JobPostVO.class)
							.get();

					query = qbs
							.bool()
							.should(qb.range().onField("minExp")
									.ignoreAnalyzer().above(minExp)
									.createQuery())

									.should(qb.range().onField("maxExp")
											.ignoreAnalyzer().below(maxExp)
											.createQuery()).createQuery();

					Query hibQuery = fullTextSession.createFullTextQuery(query,
							JobPostVO.class);

					searchList.addAll(hibQuery.list());

				} else {

					QueryBuilder qbs = fullTextSession.getSearchFactory()
							.buildQueryBuilder().forEntity(JobPostVO.class)
							.get();

					query = qbs
							.bool()
							.should(qb.keyword().onFields("minExp", "maxExp")
									.matching(experience).createQuery())
									.createQuery();

					Query hibQuery = fullTextSession.createFullTextQuery(query,
							JobPostVO.class);

					searchList.addAll(hibQuery.list());

				}

			}
			// End Experience based Search

			if (null != searchList && !searchList.isEmpty()) {

				for (JobPostVO jobSearch : searchList) {
					if (jobSearch.getIsActive() && jobSearch.getIsDeleted()) {
						jobPostBO = new JobPostBO();
						jobPostBO = JobSeekerDAOImpl
								.preparableVOtoBO(jobSearch);
						jobPostBO.setId(jobSearch.getJobId());
						EmployerLoginBO employerLoginBO = new EmployerLoginBO();
						employerLoginBO.setId(jobSearch.getEmployerLogin()
								.getId());
						jobPostBO.setEmployerLogin(employerLoginBO);
						jobPostBO.setOtherSalaryDetails(jobSearch
								.getOtherSalaryDetails());
						EmployerProfileBO employerBO = new EmployerProfileBO();
						employerBO.setId(jobSearch.getEmployerProfileVO()
								.getEmployerRegistion().getId());
						jobPostBO.setEmployerRegistion(employerBO);
						jobPostBO.setsDate(format.format(jobSearch
								.getStartDate()));
						jobPostBO
						.seteDate(format.format(jobSearch.getEndDate()));
						searchJobList.add(jobPostBO);
					}
				}

			}

			// Search Count Based
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("companyName")).add(
					Projections.rowCount());

			Criteria c = session.createCriteria(JobPostVO.class);

			if ((keySkills != null && !keySkills.isEmpty())
					&& (location != null && !location.isEmpty())
					&& (experience != null && !experience.isEmpty())) {

				c.add(Restrictions.like("keywords", keySkills,
						MatchMode.ANYWHERE))

						.add(Restrictions.like("jobLocation", location,
								MatchMode.ANYWHERE))

								.add(Restrictions.like("minExp", experience,
										MatchMode.ANYWHERE))

										.add(Restrictions.like("maxExp", experience,
												MatchMode.ANYWHERE));
			}
			c.add(Restrictions.eq("isDeleted", true));
			c.add(Restrictions.eq("isActive", true));
			c.setProjection(projectionList);
			List<Object[]> payments = c.list();

			for (Object[] payment : payments) {
				JobPostBO companyCount = new JobPostBO();
				companyCount.setCompanyName((payment[0].toString()));
				companyCount.setCompanyCount(Integer.parseInt(payment[1]
						.toString()));

				countCompany.add(companyCount);

			}

			ProjectionList projectionList1 = Projections.projectionList();
			projectionList1.add(Projections.groupProperty("jobTitle")).add(
					Projections.rowCount());

			Criteria cr = session.createCriteria(JobPostVO.class);

			if ((keySkills != null && !keySkills.isEmpty())
					&& (location != null && !location.isEmpty())
					&& (experience != null && !experience.isEmpty())) {

				cr.add(Restrictions.like("keywords", keySkills,
						MatchMode.ANYWHERE))

						.add(Restrictions.like("jobLocation", location,
								MatchMode.ANYWHERE))

								.add(Restrictions.like("minExp", experience,
										MatchMode.ANYWHERE))

										.add(Restrictions.like("maxExp", experience,
												MatchMode.ANYWHERE));
			}
			cr.add(Restrictions.eq("isDeleted", true));
			cr.add(Restrictions.eq("isActive", true));
			cr.setProjection(projectionList1);
			List<Object[]> payments1 = cr.list();

			for (Object[] payment : payments1) {
				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobTitle((payment[0].toString()));
				companyCount.setTitleCount(Integer.parseInt(payment[1]
						.toString()));

				countTitle.add(companyCount);

			}

			ProjectionList projectionList2 = Projections.projectionList();
			projectionList2.add(Projections.groupProperty("jobLocation")).add(
					Projections.rowCount());

			Criteria ci = session.createCriteria(JobPostVO.class);

			if ((keySkills != null && !keySkills.isEmpty())
					&& (location != null && !location.isEmpty())
					&& (experience != null && !experience.isEmpty())) {

				ci.add(Restrictions.like("keywords", keySkills,
						MatchMode.ANYWHERE))

						.add(Restrictions.like("jobLocation", location,
								MatchMode.ANYWHERE))

								.add(Restrictions.like("minExp", experience,
										MatchMode.ANYWHERE))

										.add(Restrictions.like("maxExp", experience,
												MatchMode.ANYWHERE));
			}

			ci.add(Restrictions.eq("isDeleted", true));
			ci.add(Restrictions.eq("isActive", true));
			ci.setProjection(projectionList2);
			List<Object[]> payments2 = ci.list();

			for (Object[] payment : payments2) {

				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobLocation((payment[0].toString()));
				companyCount.setCountlocation(Integer.parseInt(payment[1]
						.toString()));

				countLocation.add(companyCount);

			}

			if (null != searchJobList) {
				countPost.setJobPostList(searchJobList);
				countPost.setCompanyList(countCompany);
				countPost.setCountTitleList(countTitle);
				countPost.setCountLocationList(countLocation);
			}

			// Date Exception
		} catch (final BridgeException be) {
			be.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH001,
					ErrorCodes.JOB_SEARCH001_MSG);
			// Unable to Find the Field
		} catch (final SearchException se) {
			se.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH002,
					ErrorCodes.JOB_SEARCH002_MSG);
			// HibernateException
		} catch (final HibernateException he) {
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH004,
					ErrorCodes.JOB_SEARCH004_MSG);
			// Fields Interrupt Exception
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH_FAIL,
					ErrorCodes.JOB_SEARCH_FAIL_MSG);
		}
		JobSeekerDAOImpl.LOGGER.exit();
		return countPost;
	}

	@Override
	public JobPostBO JobByTitle(JobPostBO jobPostBO) throws MyJobKartException {
		List<JobPostBO> countCompany = new ArrayList<JobPostBO>();
		List<JobPostBO> countLocation = new ArrayList<JobPostBO>();
		List<JobPostBO> countTitle = new ArrayList<JobPostBO>();
		List<JobPostBO> searchCatagoriesList = new ArrayList<JobPostBO>();
		List<JobPostVO> searchList = new ArrayList<JobPostVO>();
		Session session = getSession();
		try {

			Criteria cr = session.createCriteria(JobPostVO.class);
			if (null == jobPostBO.getJobLocation()
					&& null == jobPostBO.getJobTitle()
					&& 0 == jobPostBO.getCompanyId()
					&& null == jobPostBO.getKeywords()
					&& null == jobPostBO.getAddress()
					&& null == jobPostBO.getCompanyName()) {

				cr.add(Restrictions.eq("isDeleted", true));
				cr.add(Restrictions.eq("isActive", true));
				searchList = cr.list();

			} else {
				if (null != jobPostBO.getJobLocation()) {
					cr.add(Restrictions.eq("jobLocation",
							jobPostBO.getJobLocation()));
					cr.addOrder(Order.asc("jobLocation"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}
				if (0 != jobPostBO.getCompanyId()) {
					cr.add(Restrictions.eq("companyId",
							jobPostBO.getCompanyId()));
					cr.addOrder(Order.asc("companyName"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}
				if (null != jobPostBO.getJobTitle()) {
					cr.add(Restrictions.eq("jobTitle", jobPostBO.getJobTitle()));
					cr.addOrder(Order.asc("jobTitle"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}

				// search By Catagory
				if (null != jobPostBO.getKeywords()) {
					cr.addOrder(Order.asc("keywords"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}
				if (null != jobPostBO.getCompanyName()) {
					cr.addOrder(Order.asc("companyName"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}
				if (null != jobPostBO.getAddress()) {
					cr.addOrder(Order.asc("jobLocation"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}
				searchList = cr.list();
			}

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("companyName")).add(
					Projections.rowCount());

			Criteria c2 = session.createCriteria(JobPostVO.class);
			c2.add(Restrictions.eq("isDeleted", true));
			c2.add(Restrictions.eq("isActive", true));
			c2.setProjection(projectionList);
			List<Object[]> payments = c2.list();

			for (Object[] payment : payments) {

				JobPostBO companyCount = new JobPostBO();
				companyCount.setCompanyName((payment[0].toString()));
				companyCount.setCompanyCount(Integer.parseInt(payment[1]
						.toString()));
				countCompany.add(companyCount);
			}

			ProjectionList projectionList1 = Projections.projectionList();
			projectionList1.add(Projections.groupProperty("jobTitle")).add(
					Projections.rowCount());

			Criteria c1 = session.createCriteria(JobPostVO.class);
			c1.setProjection(projectionList1);
			c1.add(Restrictions.eq("isDeleted", true));
			c1.add(Restrictions.eq("isActive", true));
			List<Object[]> payments1 = c1.list();

			for (Object[] payment : payments1) {

				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobTitle((payment[0].toString()));
				companyCount.setTitleCount(Integer.parseInt(payment[1]
						.toString()));
				countTitle.add(companyCount);
			}

			ProjectionList projectionList2 = Projections.projectionList();
			projectionList2.add(Projections.groupProperty("jobLocation")).add(
					Projections.rowCount());

			Criteria c = session.createCriteria(JobPostVO.class);

			c.add(Restrictions.eq("isDeleted", true));
			c.add(Restrictions.eq("isActive", true));
			c.setProjection(projectionList2);

			List<Object[]> payments2 = c.list();

			for (Object[] payment : payments2) {

				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobLocation((payment[0].toString()));
				companyCount.setCountlocation(Integer.parseInt(payment[1]
						.toString()));
				countLocation.add(companyCount);
			}
			if (null != searchList && searchList.size() > 0) {
				for (JobPostVO jobSearch : searchList) {

					jobPostBO = new JobPostBO();
					jobPostBO = JobSeekerDAOImpl.preparableVOtoBO(jobSearch);
					searchCatagoriesList.add(jobPostBO);

				}
			}

			if (null != searchCatagoriesList) {
				jobPostBO.setJobPostList(searchCatagoriesList);
				jobPostBO.setCompanyList(countCompany);
				jobPostBO.setCountTitleList(countTitle);
				jobPostBO.setCountLocationList(countLocation);
			}

			// Date Exception
		} catch (BridgeException be) {
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH001,
					ErrorCodes.JOB_SEARCH001_MSG);
			// Unable to Find the Field
		} catch (SearchException se) {
			se.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH002,
					ErrorCodes.JOB_SEARCH002_MSG);
			// HibernateException
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH004,
					ErrorCodes.JOB_SEARCH004_MSG);

			// Fields Interrupt Exception
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH_FAIL,
					ErrorCodes.JOB_SEARCH_FAIL_MSG);
		}
		LOGGER.exit();
		return jobPostBO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JobPostBO jobTitleCount(JobPostBO jobPostBO) {

		List<JobPostVO> searchList = new ArrayList<JobPostVO>();
		@SuppressWarnings("unused")
		final List<JobPostVO> datesearchList = new ArrayList<JobPostVO>();
		final List<JobPostBO> searchJobList = new ArrayList<JobPostBO>();
		final org.apache.lucene.search.Query query;
		JobPostBO countPost = new JobPostBO();
		try {
			List<JobPostBO> countTitle = new ArrayList<JobPostBO>();
			String keySkills = jobPostBO.getKeywords();
			String location = jobPostBO.getSearchElement();
			String experience = jobPostBO.getMinExp();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("jobTitle")).add(
					Projections.rowCount());
			Criteria c = session.createCriteria(JobPostVO.class);
			c.add(Restrictions.like("keywords", keySkills, MatchMode.ANYWHERE))
			.add(Restrictions.like("jobLocation", location,
					MatchMode.ANYWHERE))
					.add(Restrictions.like("minExp", experience,
							MatchMode.ANYWHERE));
			c.setProjection(projectionList);
			List<Object[]> payments = c.list();

			for (Object[] payment : payments) {
				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobTitle((payment[0].toString()));
				companyCount.setTitleCount(Integer.parseInt(payment[1]
						.toString()));
				countTitle.add(companyCount);

			}
			countPost.setCountTitleList(countTitle);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		JobSeekerDAOImpl.LOGGER.exit();
		return countPost;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JobPostBO joblocationCount(JobPostBO jobPostBO) {

		List<JobPostVO> searchList = new ArrayList<JobPostVO>();
		@SuppressWarnings("unused")
		final List<JobPostVO> datesearchList = new ArrayList<JobPostVO>();
		final List<JobPostBO> searchJobList = new ArrayList<JobPostBO>();
		final org.apache.lucene.search.Query query;
		JobPostBO countPost = new JobPostBO();
		try {
			List<JobPostBO> countLocation = new ArrayList<JobPostBO>();
			String keySkills = jobPostBO.getKeywords();
			String location = jobPostBO.getSearchElement();
			String experience = jobPostBO.getMinExp();
			Session session = getSession();
			session.beginTransaction();
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("jobLocation")).add(
					Projections.rowCount());
			Criteria c = session.createCriteria(JobPostVO.class);
			c.add(Restrictions.like("keywords", keySkills, MatchMode.ANYWHERE))
			.add(Restrictions.like("jobLocation", location,
					MatchMode.ANYWHERE))
					.add(Restrictions.like("minExp", experience,
							MatchMode.ANYWHERE));
			c.setProjection(projectionList);
			List<Object[]> payments = c.list();
			for (Object[] payment : payments) {
				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobLocation((payment[0].toString()));
				companyCount.setCountlocation(Integer.parseInt(payment[1]
						.toString()));
				countLocation.add(companyCount);

			}
			countPost.setCountLocationList(countLocation);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		JobSeekerDAOImpl.LOGGER.exit();
		return countPost;
	}

	@Override
	public JobPostBO searchJob(JobPostBO jobPostBO) throws MyJobKartException {
		List<JobPostBO> countCompany = new ArrayList<JobPostBO>();
		List<JobPostBO> countLocation = new ArrayList<JobPostBO>();
		List<JobPostBO> countTitle = new ArrayList<JobPostBO>();
		List<JobPostBO> searchCatagoriesList = new ArrayList<JobPostBO>();
		List<JobPostVO> searchList = new ArrayList<JobPostVO>();
		List<JobPostVO> searchJobList = new ArrayList<JobPostVO>();
		Session session = getSession();
		try {
			Criteria cr = session.createCriteria(JobPostVO.class);
			if (null == jobPostBO.getJobLocation()
					&& null == jobPostBO.getKeywords()
					&& null == jobPostBO.getCompanyName()) {
				cr.addOrder(Order.asc("jobTitle"));
				cr.add(Restrictions.eq("isDeleted", true));
				cr.add(Restrictions.eq("isActive", true));
				searchList = cr.list();
			} else {
				if (null != jobPostBO.getKeywords()) {
					cr.addOrder(Order.asc("keywords"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}
				if (null != jobPostBO.getCompanyName()) {
					cr.addOrder(Order.asc("companyName"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}
				if (null != jobPostBO.getJobLocation()) {
					cr.addOrder(Order.asc("jobLocation"));
					cr.add(Restrictions.eq("isDeleted", true));
					cr.add(Restrictions.eq("isActive", true));
				}

				cr.addOrder(Order.asc("companyName"));
				cr.add(Restrictions.eq("isDeleted", true));
				cr.add(Restrictions.eq("isActive", true));
				searchList = cr.list();
			}

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("companyName")).add(
					Projections.rowCount());
			Criteria c = session.createCriteria(JobPostVO.class);
			c.add(Restrictions.eq("isDeleted", true));
			c.add(Restrictions.eq("isActive", true));
			c.setProjection(projectionList);
			List<Object[]> payments = c.list();
			for (Object[] payment : payments) {
				JobPostBO companyCount = new JobPostBO();
				companyCount.setCompanyName((payment[0].toString()));
				companyCount.setCompanyCount(Integer.parseInt(payment[1]
						.toString()));
				countCompany.add(companyCount);
			}

			ProjectionList projectionList1 = Projections.projectionList();
			projectionList1.add(Projections.groupProperty("jobTitle")).add(
					Projections.rowCount());
			c.add(Restrictions.eq("isDeleted", true));
			c.add(Restrictions.eq("isActive", true));
			c.setProjection(projectionList1);
			List<Object[]> payments1 = c.list();
			for (Object[] payment : payments1) {
				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobTitle((payment[0].toString()));
				companyCount.setTitleCount(Integer.parseInt(payment[1]
						.toString()));
				countTitle.add(companyCount);
			}
			ProjectionList projectionList2 = Projections.projectionList();
			projectionList2.add(Projections.groupProperty("jobLocation")).add(
					Projections.rowCount());
			c.add(Restrictions.eq("isDeleted", true));
			c.add(Restrictions.eq("isActive", true));
			c.setProjection(projectionList2);
			List<Object[]> payments2 = c.list();
			for (Object[] payment : payments2) {
				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobLocation((payment[0].toString()));
				companyCount.setCountlocation(Integer.parseInt(payment[1]
						.toString()));
				countLocation.add(companyCount);
			}
			if (null != searchList && searchList.size() > 0) {
				for (JobPostVO jobSearch : searchList) {
					jobPostBO = new JobPostBO();
					jobPostBO = JobSeekerDAOImpl.preparableVOtoBO(jobSearch);
					jobPostBO.setsDate(format.format(jobSearch.getStartDate()));
					jobPostBO.seteDate(format.format(jobSearch.getEndDate()));
					searchCatagoriesList.add(jobPostBO);
				}
			} else {
				searchJobList = null;
			}
			if (null != searchCatagoriesList) {
				jobPostBO.setCatagoriesList(searchCatagoriesList);
				jobPostBO.setCompanyList(countCompany);
				jobPostBO.setCountTitleList(countTitle);
				jobPostBO.setCountLocationList(countLocation);
			}
			// Date Exception
		} catch (BridgeException be) {
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH001,
					ErrorCodes.JOB_SEARCH001_MSG);
			// Unable to Find the Field
		} catch (SearchException se) {
			se.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH002,
					ErrorCodes.JOB_SEARCH002_MSG);
			// HibernateException
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH004,
					ErrorCodes.JOB_SEARCH004_MSG);
			// Fields Interrupt Exception
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH_FAIL,
					ErrorCodes.JOB_SEARCH_FAIL_MSG);
		}
		LOGGER.exit();
		return jobPostBO;
	}

	@Override
	public List<JobPostBO> refineResult(JobPostBO jobPostBO)
			throws MyJobKartException {

		JobSeekerDAOImpl.LOGGER.entry();
		new JobPostBO();
		List<JobPostVO> searchList = new ArrayList<JobPostVO>();

		final List<JobPostBO> searchJobList = new ArrayList<JobPostBO>();

		try {

			Session session = getSession();
			final Criteria cr = session.createCriteria(JobPostVO.class);
			cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("isDeleted", true));

			if (null != jobPostBO.getCompanyName()) {
				cr.add(Restrictions.eq("companyName",
						jobPostBO.getCompanyName()));
			}

			searchList = cr.list();

			if (null != searchList && searchList.size() != 0) {
				for (final JobPostVO jobSearch : searchList) {
					jobPostBO = new JobPostBO();
					// BeanUtils.copyProperties(jobSearch, jobPostBO);
					jobPostBO = JobSeekerDAOImpl.preparableVOtoBO(jobSearch);
					searchJobList.add(jobPostBO);
				}
			}
			// Date Exception
		} catch (final BridgeException be) {
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH001,
					ErrorCodes.JOB_SEARCH001_MSG);
			// Unable to Find the Field
		} catch (final SearchException se) {
			se.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH002,
					ErrorCodes.JOB_SEARCH002_MSG);
			// HibernateException
		} catch (final HibernateException he) {
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH004,
					ErrorCodes.JOB_SEARCH004_MSG);
			// Fields Interrupt Exception
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH_FAIL,
					ErrorCodes.JOB_SEARCH_FAIL_MSG);
		}
		JobSeekerDAOImpl.LOGGER.exit();
		return searchJobList;
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public JobPostBO jobsearchlist(JobPostBO jobPostBO)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		new ArrayList<JobPostVO>();
		final List<JobPostBO> searchlist = new ArrayList<JobPostBO>();
		final List<JobPostBO> jobPostsearchlist = new ArrayList<JobPostBO>();
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(JobPostVO.class);
			cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("isDeleted", true));

			if (null != jobPostBO.getOtherSalaryDetails()) {
				cr.add(Restrictions.eq("otherSalaryDetails",
						jobPostBO.getOtherSalaryDetails()));
			}
			if (null != jobPostBO.getMinSalary()) {
				cr.add(Restrictions.eq("minSalary", jobPostBO.getMinSalary()));
			}
			if (null != jobPostBO.getJobLocation()) {
				cr.add(Restrictions.eq("jobLocation",
						jobPostBO.getJobLocation()));
			}
			if (null != jobPostBO.getKeywords()) {
				final String keywords = jobPostBO.getKeywords();
				cr.add(Restrictions.like("keywords", keywords,
						MatchMode.ANYWHERE));
			}
			if (null != jobPostBO.getJobTitle()) {
				cr.add(Restrictions.eq("jobTitle", jobPostBO.getJobTitle()));
			}
			if (null != jobPostBO.getMinExp()) {

				cr.add(Restrictions.between("minExp", jobPostBO.getMinExp(),
						jobPostBO.getMaxExp()));
				cr.add(Restrictions.eq("industryType",
						jobPostBO.getIndustryType()));
			}

			cr.addOrder(Order.asc("companyName"));
			final List<JobPostVO> searchResult = cr.list();
			String companyName = "";
			long jobCount = 0;
			final Set<String> uniqueCompany = new HashSet<String>();
			final Map<String, Long> vacancybyLocation = new HashMap<String, Long>();
			if (searchResult.size() != 0 && null != searchResult) {
				for (final JobPostVO jb : searchResult) {
					if (jb.getCompanyName().equalsIgnoreCase(companyName)) {
						jobCount++;
					} else {
						uniqueCompany.add(jb.getCompanyName());
						if (jobCount == 0) {
							jobCount = 1;
						}
						if (jobCount >= 1 && !companyName.isEmpty()) {
							vacancybyLocation.put(companyName, jobCount);
						}
						jobCount = 1;
						companyName = jb.getCompanyName();
					}
					if (jobCount > 1 && !companyName.isEmpty()) {
						vacancybyLocation.put(companyName, jobCount);
					}
				}
			}

			if (searchResult.size() != 0 && null != searchResult) {
				for (final JobPostVO jb : searchResult) {
					if (searchResult.size() == 1) {
						final long count = 1;
						vacancybyLocation.put(jb.getCompanyName(), count);
					} else {

					}
					this.jobpostBO = new JobPostBO();
					this.jobpostBO = JobSeekerDAOImpl.preparableVOtoBO(jb);
					jobPostsearchlist.add(this.jobpostBO);
				}
			}
			if (vacancybyLocation.size() != 0 && null != vacancybyLocation) {
				for (final Map.Entry<String, Long> dd : vacancybyLocation
						.entrySet()) {
					this.jobpostBO = new JobPostBO();
					this.jobpostBO.setCompanyName(dd.getKey());
					this.jobpostBO.setNoVacancies(dd.getValue());
					this.jobpostBO.setSearchElement(jobPostBO
							.getSearchElement());
					searchlist.add(this.jobpostBO);
				}
			}
			if (jobPostsearchlist.size() != 0) {
				this.jobpostBO.setJobPostList(jobPostsearchlist);
			}
			if (null != searchlist && searchlist.size() != 0) {
				this.jobpostBO.setJobPostSearchList(searchlist);
			} else {
				this.jobpostBO.setJobPostSearchList(searchlist);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
		} catch (final Exception e) {
		}
		JobSeekerDAOImpl.LOGGER.exit();
		return this.jobpostBO;
	}

	@Override
	public EmployerProfileBO companyDetails(long employerId)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		EmployerProfileBO employerProfileBO = null;
		final String loginQuery = "FROM EmployerProfileVO E WHERE E.employerLogin.id = :employerId";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			session.flush();
			query.setParameter("employerId", employerId);
			if (null != query.list() && query.list().size() > 0) {
				final EmployerProfileVO employerProfileVO = (EmployerProfileVO) query
						.list().get(0);
				employerProfileBO = JobSeekerDAOImpl
						.employerProfileVOtoBO(employerProfileVO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return employerProfileBO;
	}

	private static EmployerProfileBO employerProfileVOtoBO(
			EmployerProfileVO employerProfileVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		EmployerProfileBO employerProfileBO = null;
		try {
			employerProfileBO = new EmployerProfileBO();
			employerProfileBO
			.setCompanyName(employerProfileVO.getCompanyName());
			employerProfileBO
			.setCompanyType(employerProfileVO.getCompanyType());
			employerProfileBO.setCompanyWebsite(employerProfileVO
					.getCompanyWebsite());
			employerProfileBO.setCompanyProfile(employerProfileVO
					.getCompanyProfile());
			employerProfileBO.setEmailId(employerProfileVO.getEmailId());

		} catch (final NullPointerException ne) {
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		JobSeekerDAOImpl.LOGGER.exit();
		return employerProfileBO;
	}

	private static JobPostBO preparableVOtoBO(JobPostVO jobPostVO)
			throws SerialException, SQLException {
		JobSeekerDAOImpl.LOGGER.entry();
		JobPostBO jobPostBO = null;
		try {
			jobPostBO = new JobPostBO();
			jobPostBO.setAddress(jobPostVO.getAddress());
			jobPostBO.setCompanyName(jobPostVO.getCompanyName());
			jobPostBO.setId(jobPostVO.getJobId());
			jobPostBO.setContactNo(jobPostVO.getContactNo());
			jobPostBO.setContactPerson(jobPostVO.getContactPerson());
			jobPostBO.setCreatedBy(jobPostBO.getCreatedBy());
			jobPostBO.setCurrencyType(jobPostVO.getCurrencyType());
			jobPostBO.setFunctionArea(jobPostVO.getFunctionArea());
			jobPostBO.setIndustryType(jobPostVO.getIndustryType());
			jobPostBO.setJobDescription(jobPostVO.getJobDescription());
			jobPostBO.setJobLocation(jobPostVO.getJobLocation());
			jobPostBO.setJobResponse(jobPostVO.getJobResponse());
			jobPostBO.setJobTitle(jobPostVO.getJobTitle());
			jobPostBO.setKeywords(jobPostVO.getKeywords());
			jobPostBO.setMaxExp(jobPostVO.getMaxExp());
			jobPostBO.setModifiedBy(jobPostVO.getModifiedBy());
			jobPostBO.setMaxSalary(jobPostVO.getMaxSalary());
			jobPostBO.setMinExp(jobPostVO.getMinExp());
			jobPostBO.setMinSalary(jobPostVO.getMinSalary());
			jobPostBO.setNoVacancies(jobPostVO.getNoVacancies());
			jobPostBO.setOtherSalaryDetails(jobPostVO.getOtherSalaryDetails());
			jobPostBO.setPgQualification(jobPostVO.getPgQualification());
			jobPostBO.setUgQualification(jobPostVO.getUgQualification());

			jobPostBO.setCreated(jobPostVO.getCreated());
			jobPostBO.setModified(jobPostVO.getModified());
			jobPostBO.setExperiance(jobPostVO.getMinExp() + "-"
					+ jobPostVO.getMaxExp());
			jobPostBO.setPostedBy(jobPostVO.getPostedBy());
			final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
			employerLoginBO.setId(jobPostVO.getEmployerLogin().getId());
			jobPostBO.setEmployerLogin(employerLoginBO);
			jobPostBO.setSalary(jobPostVO.getMinSalary() + "-"
					+ jobPostVO.getMaxSalary());
		} catch (final NullPointerException ne) {
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		JobSeekerDAOImpl.LOGGER.exit();
		return jobPostBO;
	}

	@Override
	public JobseekerProfileVO profileStatus(
			JobseekerProfileVO jobseekerProfileVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		final String changeLoginQuery = "UPDATE JobseekerProfileVO E set E.isActive = :IsActive WHERE E.profileId=:id and E.emailId = :emailAddress";
		try {
			final Query query = getSession().createQuery(changeLoginQuery);
			query.setParameter("IsActive", jobseekerProfileVO.getIsActive());
			query.setParameter("emailAddress", jobseekerProfileVO.getEmailId());
			query.setParameter("id", jobseekerProfileVO.getprofileId());
			final int result = query.executeUpdate();
			if (0 != result) {
				return jobseekerProfileVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return null;

	}

	@Override
	public long jobseekerAppliedJob(AppliedJobVO appliedJobVO, long saveJobId) {
		JobSeekerDAOImpl.LOGGER.entry();
		long profileId = 0;
		final String laterApplyJob = "UPDATE SaveJobVO E set E.isApply = :isApply WHERE E.applicationid=:id ";
		try {
			Session session = getSession();

			session.saveOrUpdate(appliedJobVO);

			if (null != appliedJobVO) {
				profileId = appliedJobVO.getApplicationid();
				if (0 != saveJobId && 0 != profileId) {
					final Query query = session.createQuery(laterApplyJob);
					query.setParameter("isApply", false);
					query.setParameter("id", saveJobId);
					query.executeUpdate();
				}

				// Job seeker Activity
				JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
				JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();
				EmployerLoginVO employerLoginVO = new EmployerLoginVO();
				JobPostVO jobPostVO = new JobPostVO();

				jbActivityVO.setActivityDate(format.format(new Date()));
				jobseekerLoginVO
				.setId(appliedJobVO.getJobseekerLogin().getId());
				jbActivityVO.setJobseekerLoginVO(jobseekerLoginVO);
				jobPostVO.setJobId(appliedJobVO.getJobpostVO().getJobId());
				jbActivityVO.setJobPostVO(jobPostVO);
				employerLoginVO.setId(appliedJobVO.getEmployerLogin().getId());
				jbActivityVO.setEmployerLoginVO(employerLoginVO);
				jbActivityVO.setCreated(new Date());
				jbActivityVO.setCreatedBy(appliedJobVO.getCreatedBy());
				jbActivityVO.setStatus("Applied Jobs");
				session.save(jbActivityVO);

			}

			session.flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return profileId;
	}

	@Override
	public List<JobseekerProfileBO> checkProfileId(long jobseekerId) {
		LOGGER.entry();
		List<JobseekerProfileBO> jobseekerProfileBOList = new ArrayList<JobseekerProfileBO>();
		List<JobseekerProfileVO> jobseekerProfileVOList = new ArrayList<JobseekerProfileVO>();
		Criteria criteria;
		JobseekerProfileBO jobseekerProfileBO;
		try {
			Session session = getSession();
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(JobseekerProfileVO.class);

			criteria.add(Restrictions.eq("isActive", true));
			criteria.add(Restrictions.eq("isDelete", true));

			jobseekerProfileVOList = criteria.list();

			for (JobseekerProfileVO ProfileVO : jobseekerProfileVOList) {
				jobseekerProfileBO = new JobseekerProfileBO();
				BeanUtils.copyProperties(ProfileVO, jobseekerProfileBO);
				jobseekerProfileBOList.add(jobseekerProfileBO);
			}

		} catch (HibernateException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker check  profile Id failed:"
						+ e.getMessage());
			}
			LOGGER.info("Jobseeker check profile Id failed:" + e.getMessage());

		}
		LOGGER.exit();
		return jobseekerProfileBOList;
	}

	@Override
	public long savedJob(SaveJobVO saveJobVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		long profileId = 0;
		try {
			JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
			JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();
			EmployerLoginVO employerLoginVO = new EmployerLoginVO();
			JobPostVO jobPostVO = new JobPostVO();
			Session session = getSession();

			session.saveOrUpdate(saveJobVO);

			if (saveJobVO.getApplicationid() != 0) {
				profileId = saveJobVO.getApplicationid();
				jbActivityVO.setActivityDate(format.format(new Date()));
				jobseekerLoginVO.setId(saveJobVO.getJobseekerLogin().getId());
				jbActivityVO.setJobseekerLoginVO(jobseekerLoginVO);
				jobPostVO.setJobId(saveJobVO.getJobpostVO().getJobId());
				jbActivityVO.setJobPostVO(jobPostVO);
				employerLoginVO.setId(saveJobVO.getEmployerLogin().getId());
				jbActivityVO.setEmployerLoginVO(employerLoginVO);
				jbActivityVO.setCreated(new Date());
				jbActivityVO.setCreatedBy(saveJobVO.getCreatedBy());
				jbActivityVO.setStatus("Saved Jobs");
				session.save(jbActivityVO);

			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return profileId;
	}

	@Override
	public AppliedJobVO applied(long id) {
		JobSeekerDAOImpl.LOGGER.entry();

		AppliedJobVO appliedJobVO = null;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(AppliedJobVO.class)
					.createCriteria("jobpostVO")
					.add(Restrictions.eq("jobId", id));

			appliedJobVO = (AppliedJobVO) cr.list().get(0);

			session.flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return appliedJobVO;
	}

	@Override
	public SaveJobVO savejob(long id) {
		JobSeekerDAOImpl.LOGGER.entry();

		SaveJobVO saveJobVO = null;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(SaveJobVO.class)
					.createCriteria("jobpostVO")
					.add(Restrictions.eq("jobId", id));
			saveJobVO = (SaveJobVO) cr.list().get(0);

			session.flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return saveJobVO;
	}

	@Override
	public List<SavejobBO> jobseekerSavedJob(AppliedJobBO appliedJobBO) {
		Criteria criteria;
		final List<SavejobBO> jobseekerSaveJobList = new ArrayList<SavejobBO>();
		List<SaveJobVO> savedJobList = new ArrayList<SaveJobVO>();
		Session session = getSession();
		criteria = session.createCriteria(SaveJobVO.class)
				.add(Restrictions.eq("isDeleted", true))
				.createCriteria("jobseekerLogin")
				.add(Restrictions.eq("id", appliedJobBO.getLoginId()));
		savedJobList = criteria.list();
		for (final SaveJobVO saveJob : savedJobList) {
			final SavejobBO savejobBO = new SavejobBO();

			savejobBO.setJobId(saveJob.getJobpostVO().getJobId());
			jobseekerSaveJobList.add(savejobBO);

		}
		return jobseekerSaveJobList;
	}

	@Override
	public List<JobseekerBO> retriveRegisteredJobseeker()
			throws MyJobKartException {
		List<JobseekerVO> jobseekerRegisteredList = new ArrayList<JobseekerVO>();
		final List<JobseekerBO> jobseekerRegisteredBOList = new ArrayList<JobseekerBO>();
		JobseekerBO jobseekerBO;
		int count = 0;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(JobseekerVO.class);
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isDelete", true));
			jobseekerRegisteredList = cr.list();
			for (final JobseekerVO jobseekerVO : jobseekerRegisteredList) {
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
				if (jobseekerBO.getIsActive() == true) {
					jobseekerBO.setActive("Active");
				} else {
					jobseekerBO.setActive("De-active");
				}
				jobseekerBO.setVersion(jobseekerVO.getVersion());
				jobseekerBO.setMobileNo(jobseekerVO.getMobileNo());
				//Capitalize the first letter of word 
				jobseekerBO.setFirstName(jobseekerVO.getFirstName().substring(0, 1).toUpperCase() + jobseekerVO.getFirstName().substring(1));
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
				count = count + 1;
				jobseekerBO.setSNo(count);
				jobseekerBO.setCreateDate(format.format(jobseekerVO.getCreated()));
				jobseekerRegisteredBOList.add(jobseekerBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrive register jobseeker without ID failed:"
						+ e.getMessage());
			}
			LOGGER.info("Retrive register jobseeker without ID failed:"
					+ e.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerRegisteredBOList;
	}

	@Override
	public List<JobseekerBO> retriveRegisteredJobseeker(long registerId)
			throws MyJobKartException {
		List<JobseekerVO> jobseekerRegisteredList = new ArrayList<JobseekerVO>();
		final List<JobseekerBO> jobseekerRegisteredBOList = new ArrayList<JobseekerBO>();
		JobseekerBO jobseekerBO;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(JobseekerVO.class);
			cr.add(Restrictions.eq("id", registerId));
			jobseekerRegisteredList = cr.list();
			for (final JobseekerVO jobseekerVO : jobseekerRegisteredList) {
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
				if (jobseekerBO.getIsActive() == true) {
					jobseekerBO.setActive("Active");
				} else {
					jobseekerBO.setActive("De-active");
				}
				jobseekerBO.setVersion(jobseekerVO.getVersion());
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
				jobseekerRegisteredBOList.add(jobseekerBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrive register jobseeker with ID failed:"
						+ e.getMessage());
			}
			LOGGER.info("Retrive register jobseeker with ID failed:"
					+ e.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerRegisteredBOList;
	}

	@Override
	public JobseekerVO updateJobseeker(JobseekerVO jobseekerVO)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.saveOrUpdate(jobseekerVO);
			session.flush();

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_UPDATE_FAIL
						+ he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		}

		finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}

		return jobseekerVO;
	}

	@Override
	public List<JobseekerProfileBO> retrieveJobseekerProfile()
			throws MyJobKartException {
		List<JobseekerProfileVO> jobseekerProfileList = new ArrayList<JobseekerProfileVO>();
		JobSeekerDAOImpl.LOGGER.entry();
		List<JobseekerProfileBO> jobseekerprofileList = new ArrayList<JobseekerProfileBO>();
		// JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();
		List<JobseekerProfileBO> jobseekereduList = new ArrayList<JobseekerProfileBO>();
		List<JobseekerProfileBO> professionalList = new ArrayList<JobseekerProfileBO>();
		int count = 0;
		try {
			Session session = getSession();
			final Criteria cr = session
					.createCriteria(JobseekerProfileVO.class);
			cr.addOrder(Order.asc("created"));
			cr.add(Restrictions.eq("isDelete", true));
			cr.add(Restrictions.eq("isActive", true));
			jobseekerProfileList = cr.list();
			for (final JobseekerProfileVO profileVO : jobseekerProfileList) {

				this.profileBO = new JobseekerProfileBO();
				if (null != profileVO.getJobseekerEducationVO()) {
					List<JobseekerEducationVO> eductionalList = profileVO
							.getJobseekerEducationVO();
					for (final JobseekerEducationVO educationVO : eductionalList) {
						this.profileBO.setEducationId(educationVO
								.getEducationId());
						this.profileBO.setDegree(educationVO.getDegree());
						if (null != educationVO.getCollege()) {
							this.profileBO.setCollege(educationVO.getCollege());
						}
						this.profileBO.setYearOfPassing(educationVO
								.getYearOfPassing());
						if (null != educationVO.getGrade()) {
							this.profileBO.setGrade(educationVO.getGrade());
						}
						if (null != educationVO.getPercentage()) {
							this.profileBO.setPercentage(educationVO
									.getPercentage());
						}
						if (null != educationVO.getDepartment()) {
							this.profileBO.setDepartment(educationVO
									.getDepartment());
						}
						jobseekereduList.add(this.profileBO);
					}
				}

				if (null != profileVO.getJobseekerProfessionalVO()) {
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
					List<JobseekerProfessionalVO> experienceList = profileVO
							.getJobseekerProfessionalVO();
					for (JobseekerProfessionalVO professionalVO : experienceList) {
						this.profileBO.setProfesId(professionalVO
								.getProfessionalId());
						this.profileBO.setCompanyName(professionalVO
								.getCompanyName());
						this.profileBO.setCompanyType(professionalVO
								.getCompanyType());
						this.profileBO.setDesignation(professionalVO
								.getDesignation());
						this.profileBO.setLastSalary(professionalVO
								.getLastSalary());
						if (null != professionalVO.getExpStartDate()) {
							this.profileBO.setStartDate(df
									.format(professionalVO.getExpStartDate()));
						}
						if (null != professionalVO.getExpEndDate()) {
							this.profileBO.setEndDate(df.format(professionalVO
									.getExpEndDate()));
						}

						professionalList.add(this.profileBO);

					}
				}
				this.profileBO.setVersion(profileVO.getVersion());
				this.profileBO.setCreated(profileVO.getCreated());
				this.profileBO.setCurrentIndustry(profileVO
						.getCurrentIndustry());
				this.profileBO.setCurrentSalary(profileVO.getCurrentSalary());
				this.profileBO.setDeleteBy(profileVO.getDeleteBy());
				this.profileBO.setDeletedDate(profileVO.getDeletedDate());
				this.profileBO.setDomainSkills(profileVO.getDomainSkills());
				this.profileBO.setEmailId(profileVO.getEmailId());
				this.profileBO.setExpectedCtc(profileVO.getExpectedCtc());
				this.profileBO.setExperienceInMonth(profileVO
						.getExperienceInMonth());
				this.profileBO.setExperienceInYear(profileVO
						.getExperienceInYear());
				this.profileBO.setIsActive(profileVO.getIsActive());

				if (this.profileBO.getIsActive()) {
					this.profileBO.setStatus("Active");

				} else {
					this.profileBO.setStatus("De-Active");
				}
				final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
				if (null != profileVO.getJobSeekerLogin()) {
					jobSeekerLoginBO.setId(profileVO.getJobSeekerLogin()
							.getId());
					this.profileBO.setJobSeekerLogin(jobSeekerLoginBO);
				}
				this.profileBO.setCreatedBy(profileVO.getCreatedBy());
				this.profileBO.setFirstName(profileVO.getFirstName());
				this.profileBO.setFunction(profileVO.getFunction());
				this.profileBO.setGender(profileVO.getGender());
				this.profileBO.setProfiledescription(profileVO
						.getProfiledescription());
				this.profileBO.setJobType(profileVO.getJobType());
				this.profileBO.setKeySkills(profileVO.getKeySkills());
				this.profileBO.setLanguagesKnown(profileVO.getLanguagesKnown());
				this.profileBO.setLastName(profileVO.getLastName());
				this.profileBO.setLocation(profileVO.getLocation());
				this.profileBO.setMaritalStatus(profileVO.getMaritalStatus());
				this.profileBO.setModifiedBy(profileVO.getModifiedBy());
				this.profileBO.setNationality(profileVO.getNationality());
				this.profileBO.setPhone(profileVO.getPhone());
				this.profileBO.setPreferredIndustry(profileVO
						.getPreferredIndustry());
				this.profileBO.setPreferredLocation(profileVO
						.getPreferredLocation());
				this.profileBO
				.setProfileImage(profileVO.getProfileImage().getBytes(
						1, (int) profileVO.getProfileImage().length()));
				this.profileBO.setResumeTitle(profileVO.getResumeTitle());

				if (null != profileVO.getUploadResume()) {
					this.profileBO
					.setUploadResume(profileVO.getUploadResume()
							.getBytes(
									1,
									(int) profileVO.getUploadResume()
									.length()));
				}

				this.profileBO.setId(profileVO.getprofileId());
				this.profileBO.setIsDelete(profileVO.getIsDelete());
				this.profileBO.setJobEductionProfileList(jobseekereduList);
				this.profileBO.setJobprofessionalList(professionalList);
				count = count + 1;
				this.profileBO.setSNo(count);
				jobseekerprofileList.add(this.profileBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve Jobseeker Profile failed:"
						+ e.getMessage());
			}
			LOGGER.info("Retrieve Jobseeker Profile  failed:" + e.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerprofileList;
	}

	@Override
	public List<JobPostBO> appliedJobSearch(JobPostBO jobPostBO) {

		List<AppliedJobVO> appliedcompany1 = new ArrayList<AppliedJobVO>();
		JobPostBO jobpost;
		final List<JobPostBO> appliedcompany = new ArrayList<JobPostBO>();

		Session session = getSession();
		final Criteria cr = session.createCriteria(AppliedJobVO.class)
				.createCriteria("jobseekerLogin")
				.add(Restrictions.eq("id", jobPostBO.getId()));
		cr.addOrder(Order.desc("id"));
		appliedcompany1 = cr.list();

		for (final AppliedJobVO appliedJobVO : appliedcompany1) {
			jobpost = new JobPostBO();
			jobpost.setId(appliedJobVO.getJobpostVO().getJobId());
			jobpost.setAddress(appliedJobVO.getAddress());
			jobpost.seteDate(format.format(appliedJobVO.getCreated()));
			jobpost.setCompanyName(appliedJobVO.getCompanyName());
			jobpost.setContactNo(appliedJobVO.getContactNo());
			jobpost.setJobTitle(appliedJobVO.getJobTitle());
			jobpost.setContactPerson(appliedJobVO.getContactPerson());
			jobpost.setCreated(appliedJobVO.getCreated());
			jobpost.setJobLocation(appliedJobVO.getJobLocation());
			jobpost.setJobDescription(appliedJobVO.getJobDescription());
			jobpost.setFunctionArea(appliedJobVO.getFunctionArea());
			jobpost.setKeywords(appliedJobVO.getKeywords());

			final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
			employerLoginBO.setId(appliedJobVO.getEmployerLogin().getId());
			jobpost.setEmployerLogin(employerLoginBO);
			appliedcompany.add(jobpost);
		}

		return appliedcompany;
	}

	@Override
	public List<JobPostBO> appliedJobSearchDate(JobPostBO jobPostBO) {

		List<AppliedJobVO> appliedcompanydate = new ArrayList<AppliedJobVO>();
		JobPostBO jobpost;
		final List<JobPostBO> appliedcompanydates = new ArrayList<JobPostBO>();
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		final Date result = cal.getTime();
		Session session = getSession();
		final Criteria cr = session.createCriteria(AppliedJobVO.class);
		cr.createCriteria("jobseekerLogin").add(
				Restrictions.eq("id", jobPostBO.getId()));
		cr.addOrder(Order.desc("id"));
		cr.add(Restrictions.between("created", result, new Date()));
		appliedcompanydate = cr.list();

		for (final AppliedJobVO appliedJobVO : appliedcompanydate) {
			jobpost = new JobPostBO();
			jobpost.seteDate(format.format(appliedJobVO.getCreated()));
			jobpost.setId(appliedJobVO.getJobpostVO().getJobId());
			jobpost.setAddress(appliedJobVO.getAddress());
			jobpost.setCompanyName(appliedJobVO.getCompanyName());
			jobpost.setContactNo(appliedJobVO.getContactNo());
			jobpost.setJobTitle(appliedJobVO.getJobTitle());
			jobpost.setContactPerson(appliedJobVO.getContactPerson());
			jobpost.setCreated(appliedJobVO.getCreated());
			jobpost.setJobLocation(appliedJobVO.getJobLocation());
			jobpost.setJobDescription(appliedJobVO.getJobDescription());
			jobpost.setFunctionArea(appliedJobVO.getFunctionArea());
			jobpost.setKeywords(appliedJobVO.getKeywords());

			jobpost.setPostedBy(appliedJobVO.getPostedBy());
			jobpost.setIndustryType(appliedJobVO.getIndustryType());
			jobpost.setMaxSalary(appliedJobVO.getMaxSalary());
			jobpost.setMinSalary(appliedJobVO.getMinSalary());
			jobpost.setMinExp(appliedJobVO.getMaxExp());
			jobpost.setMaxExp(appliedJobVO.getMinExp());
			jobpost.setNoVacancies(appliedJobVO.getJobpostVO().getNoVacancies());
			jobpost.setUgQualification(appliedJobVO.getUgQualification());
			jobpost.setPgQualification(appliedJobVO.getPgQualification());

			final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
			employerLoginBO.setId(appliedJobVO.getEmployerLogin().getId());
			jobpost.setEmployerLogin(employerLoginBO);

			appliedcompanydates.add(jobpost);
		}

		return appliedcompanydates;
	}

	@Override
	public List<JobseekerBO> renewalRegisteredJobseeker()
			throws MyJobKartException {

		final Calendar cal1 = new GregorianCalendar();
		final Calendar cal2 = new GregorianCalendar();

		List<JobseekerVO> jobseekerRegisteredList = new ArrayList<JobseekerVO>();
		final List<JobseekerBO> jobseekerRegisteredBOList = new ArrayList<JobseekerBO>();
		List<JobseekerProductServiceVO> jobseekerProductService = new ArrayList<JobseekerProductServiceVO>();

		JobseekerBO jobseekerBO;
		try {
			Session session = getSession();
			final Criteria criteria = session
					.createCriteria(JobseekerProductServiceVO.class);
			jobseekerProductService = criteria.list();
			final Criteria cr = session.createCriteria(JobseekerVO.class);
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isDelete", true));
			// cr.setMaxResults(6);
			jobseekerRegisteredList = cr.list();
			final Date date = new Date();

			new SimpleDateFormat("ddMMyyyy");

			for (final JobseekerVO jobseekerVO : jobseekerRegisteredList) {

				for (final JobseekerProductServiceVO jobseekerProduct : jobseekerProductService) {
					if (jobseekerProduct.getJobseeker().getId() == jobseekerVO
							.getId()) {
						jobseekerBO = new JobseekerBO();
						final Date grecePeriod = jobseekerProduct
								.getGracePeriod();
						cal1.setTime(grecePeriod);
						cal2.setTime(date);
						final Date ed = cal1.getTime();
						final Date sd = cal2.getTime();
						final long diff = ed.getTime() - sd.getTime();
						final int totalDays = (int) (diff / (1000 * 24 * 60 * 60));
						jobseekerBO.setTotalDays(totalDays);
						jobseekerBO.setId(jobseekerVO.getId());
						jobseekerBO.setCreated(jobseekerVO.getCreated());
						jobseekerBO.setPassword(jobseekerVO.getPassword());
						jobseekerBO.setConfirmPassword(jobseekerVO
								.getConfirmPassword());
						jobseekerBO.setEmailAddress(jobseekerVO
								.getEmailAddress());
						jobseekerBO.setConfirmEmailAddress(jobseekerVO
								.getConfirmEmailAddress());
						jobseekerBO.setIsActive(jobseekerVO.getIsActive());
						if (jobseekerBO.getIsActive() == true) {
							jobseekerBO.setActive("Active");
						} else {
							jobseekerBO.setActive("De-active");
						}
						jobseekerBO.setVersion(jobseekerVO.getVersion());
						jobseekerBO.setMobileNo(jobseekerVO.getMobileNo());
						jobseekerBO.setFirstName(jobseekerVO.getFirstName());
						jobseekerBO.setLastName(jobseekerVO.getLastName());
						jobseekerBO.setTermsConditionsAgreed(jobseekerVO
								.getTermsConditionsAgreed());
						jobseekerBO.setProfileImage(jobseekerVO
								.getProfileImage().getBytes(
										1,
										(int) jobseekerVO.getProfileImage()
										.length()));
						jobseekerBO.setIsDeleted(jobseekerVO.getIsDelete());
						jobseekerRegisteredBOList.add(jobseekerBO);

					}

				}

			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Renewal Registered Jobseeker failed:"
						+ e.getMessage());
			}
			LOGGER.info("Renewal Registered Jobseeker failed:" + e.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerRegisteredBOList;

	}

	/**
	 * 
	 * This Method used to perform the job seeker create profile.
	 * 
	 */

	@Override
	public JobseekerProfileVO jobseekerCreateProfile(
			JobseekerProfileVO jobseekerProfileVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		try {
			JobseekerActivityVO jbActivityVO = new JobseekerActivityVO();
			JobseekerLoginVO jobseekerLoginVO = new JobseekerLoginVO();
			Session session = getSession();
			session.saveOrUpdate(jobseekerProfileVO);

			// Job seeker Activity
			if (null != jobseekerProfileVO) {
				jbActivityVO.setActivityDate(format.format(new Date()));
				jbActivityVO.setJobseekerProfileVO(jobseekerProfileVO);
				jobseekerLoginVO.setId(jobseekerProfileVO.getJobSeekerLogin()
						.getId());
				jbActivityVO.setJobseekerLoginVO(jobseekerLoginVO);
				jbActivityVO.setCreated(new Date());
				jbActivityVO.setCreatedBy(jobseekerProfileVO.getCreatedBy());
				jbActivityVO.setStatus("New jobseeker Profile");
				session.saveOrUpdate(jbActivityVO);

			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerProfileVO;
	}

	@Override
	public JobseekerLoginVO activeJobseeker(JobseekerLoginVO loginVO,
			JobseekerProductServiceVO jobseekerProductServiceVO)
					throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		try {
			long jobseekerId = 0;
			final boolean isActive = true;
			final String updateQuery = "UPDATE JobseekerVO S set"
					+ " S.isActive = :isActive," + "S.createdBy = :createdBy,"
					+ "S.modifiedBy = :modifiedBy," + "S.modified=:modified"
					+ " WHERE S.id = :id";

			Session session = getSession();
			jobseekerId = (Long) session.save(loginVO);

			if (0 != jobseekerId) {
				final Query query = session.createQuery(updateQuery);
				query.setParameter("isActive", isActive);
				query.setParameter("createdBy", loginVO.getCreatedBy());
				query.setParameter("modifiedBy", loginVO.getModifiedBy());
				query.setParameter("modified", new Date());
				query.setParameter("id", loginVO.getJobseekerRegistration()
						.getId());
				query.executeUpdate();

				final JobseekerVO obj = new JobseekerVO();
				obj.setId(loginVO.getJobseekerRegistration().getId());
				jobseekerProductServiceVO.setJobseeker(obj);
				session.save(jobseekerProductServiceVO);
				session.flush();
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_UPDATE_FAIL
						+ he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return loginVO;
	}

	@Override
	public JobseekerVO profileStatus1(JobseekerVO jobseekerProfileVO) {
		// TODO Auto-generated method stub
		JobSeekerDAOImpl.LOGGER.entry();
		String CreateQuery = "UPDATE JobseekerLoginVO S set S.isActive = :IsActive WHERE E.id=:Id";
		final String changeLoginQuery = "UPDATE JobseekerVO E set E.isActive = :IsActive WHERE E.id=:Id";
		try {
			Session session = getSession();
			final Query query = session.createQuery(changeLoginQuery);
			query.setParameter("IsActive", jobseekerProfileVO.getIsActive());
			query.setParameter("Id", jobseekerProfileVO.getId());
			final int result = query.executeUpdate();
			if (0 != result) {
				return jobseekerProfileVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return null;
	}



	@Override
	public EmployerProfileBO employeeDetail(ViewJobseekerBO empdetail) {
		EmployerProfileBO empBo = null;
		try {

			Session session = getSession();
			final String querys = "From EmployerProfileVO where employerRegistion.id=:eId";
			final Query query = session.createQuery(querys);
			query.setParameter("eId", empdetail.getId());
			final List<EmployerProfileVO> list = query.list();
			for (final EmployerProfileVO evo : list) {

				empBo = new EmployerProfileBO();
				empBo.setCompanyName(evo.getCompanyName());
				empBo.setAddressLine1(evo.getAddressLine1());
				empBo.setCity(evo.getCity());
				empBo.setCompanyProfile(evo.getCompanyProfile());
				empBo.setCompanyWebsite(evo.getCompanyWebsite());
				empBo.setCompanyType(evo.getCompanyType());
				empBo.setContactNo(evo.getContactNo());
				empBo.setCountry(evo.getCountry());
				empBo.setCurrentDesignation(evo.getCurrentDesignation());
				empBo.setCurrentRolesResponsibilities(evo
						.getCurrentRolesResponsibilities());
				empBo.setEmailId(evo.getEmailId());
				empBo.setFirstName(evo.getFirstName());
				empBo.setMobileNo(evo.getMobileNo());
				empBo.setHiringPurpose(evo.getHiringPurpose());
				empBo.setSecondaryEmail(evo.getSecondaryEmail());
				empBo.setId(evo.getEmployerRegistion().getId());
				if (null != evo.getCompanyLogo()) {
					empBo.setCompanyLogo(evo.getCompanyLogo().getBytes(1,
							(int) evo.getCompanyLogo().length()));
				}
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
		} catch (final SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empBo;
	}

	@Override
	public List<JobseekerBO> renerenewalJobseekerAlert(String email) {
		// TODO Auto-generated method stub
		final Calendar cal1 = new GregorianCalendar();
		final Calendar cal2 = new GregorianCalendar();

		List<JobseekerVO> jobseekerRegisteredList = new ArrayList<JobseekerVO>();
		final List<JobseekerBO> jobseekerRegisteredBOList = new ArrayList<JobseekerBO>();
		List<JobseekerProductServiceVO> jobseekerProductService = new ArrayList<JobseekerProductServiceVO>();

		JobseekerBO jobseekerBO;
		try {
			Session session = getSession();
			final Criteria criteria = session
					.createCriteria(JobseekerProductServiceVO.class);
			jobseekerProductService = criteria.list();
			final Criteria cr = session.createCriteria(JobseekerVO.class);
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isDelete", true));
			cr.add(Restrictions.eq("emailAddress", email));
			// cr.setMaxResults(6);
			jobseekerRegisteredList = cr.list();
			final Date date = new Date();

			new SimpleDateFormat("ddMMyyyy");

			for (final JobseekerVO jobseekerVO : jobseekerRegisteredList) {

				for (final JobseekerProductServiceVO jobseekerProduct : jobseekerProductService) {
					if (jobseekerProduct.getJobseeker().getId() == jobseekerVO
							.getId()) {
						jobseekerBO = new JobseekerBO();
						final Date grecePeriod = jobseekerProduct
								.getGracePeriod();
						cal1.setTime(grecePeriod);
						cal2.setTime(date);
						final Date ed = cal1.getTime();
						final Date sd = cal2.getTime();
						final long diff = ed.getTime() - sd.getTime();
						final int totalDays = (int) (diff / (1000 * 24 * 60 * 60));
						jobseekerBO.setTotalDays(totalDays);
						jobseekerBO.setId(jobseekerVO.getId());
						jobseekerBO.setCreated(jobseekerVO.getCreated());
						jobseekerBO.setPassword(jobseekerVO.getPassword());
						jobseekerBO.setConfirmPassword(jobseekerVO
								.getConfirmPassword());
						jobseekerBO.setEmailAddress(jobseekerVO
								.getEmailAddress());
						jobseekerBO.setConfirmEmailAddress(jobseekerVO
								.getConfirmEmailAddress());
						jobseekerBO.setIsActive(jobseekerVO.getIsActive());
						if (jobseekerBO.getIsActive() == true) {
							jobseekerBO.setActive("Active");
						} else {
							jobseekerBO.setActive("De-active");
						}
						jobseekerBO.setVersion(jobseekerVO.getVersion());
						jobseekerBO.setMobileNo(jobseekerVO.getMobileNo());
						jobseekerBO.setFirstName(jobseekerVO.getFirstName());
						jobseekerBO.setLastName(jobseekerVO.getLastName());
						jobseekerBO.setTermsConditionsAgreed(jobseekerVO
								.getTermsConditionsAgreed());
						jobseekerBO.setProfileImage(jobseekerVO
								.getProfileImage().getBytes(
										1,
										(int) jobseekerVO.getProfileImage()
										.length()));
						jobseekerBO.setIsDeleted(jobseekerVO.getIsDelete());
						jobseekerRegisteredBOList.add(jobseekerBO);
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Renewal Jobseeker Alert failed:" + e.getMessage());
			}
			LOGGER.info("Renewal Jobseeker Alert failed:" + e.getMessage());
		}
		return jobseekerRegisteredBOList;
	}

	@Override
	public List<PaymentBO> productsEnrolledList(long registerId) {
		// TODO Auto-generated method stub
		JobSeekerDAOImpl.LOGGER.entry();
		List<EntrolledSeviceVO> enrolledServiceList = new ArrayList<EntrolledSeviceVO>();
		final List<PaymentBO> paymentList = new ArrayList<PaymentBO>();
		try {
			Session session = getSession();
			final Criteria criteria = session
					.createCriteria(EntrolledSeviceVO.class);
			criteria.add(Restrictions.eq("payId", registerId));
			criteria.add(Restrictions.eq("isDeleted", true));
			criteria.add(Restrictions.eq("producType", "jobseeker"));
			enrolledServiceList = criteria.list();
			for (final EntrolledSeviceVO entrolledSeviceVO : enrolledServiceList) {
				this.paymentBO = new PaymentBO();
				this.paymentBO.setPayId(entrolledSeviceVO.getPayId());
				this.paymentBO.setId(entrolledSeviceVO.getEntrolledid());
				this.paymentBO.setSelectProduct(entrolledSeviceVO
						.getProducType());
				this.paymentBO.setTotalcost(entrolledSeviceVO.getTotalcost());
				this.paymentBO.setCreated(entrolledSeviceVO.getValidFrom());
				this.paymentBO.setEndDate(entrolledSeviceVO.getValidEnd());
				this.paymentBO.setSelectProduct(entrolledSeviceVO
						.getSelectProduct());
				this.paymentBO
				.setProductType(entrolledSeviceVO.getProducType());
				this.paymentBO.setTotalMonth(entrolledSeviceVO.getTotalMonth());
				paymentList.add(this.paymentBO);

			}

		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("products EnrolledList failed:"
						+ exception.getMessage());
			}
			LOGGER.info("products EnrolledList failed:"
					+ exception.getMessage());

		}
		return paymentList;
	}

	@Override
	public int deleteJobseekerEnrolledDetails(EntrolledSeviceVO saveJobVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE EntrolledSeviceVO  set"
				+ " isDeleted =:Deleted," + "deletedDate =:deletedDate"
				+ " WHERE entrolledid =:id";
		try {
			Session session = getSession();
			final Query query = session.createQuery(deleteQuery);
			query.setParameter("Deleted", saveJobVO.getIsDeleted());
			query.setParameter("deletedDate", saveJobVO.getDeletedDate());
			query.setParameter("id", saveJobVO.getEntrolledid());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL
						+ he);
			}

		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	@Override
	public List<PaymentBO> lastMonthPaymentList(long registerId) {
		// TODO Auto-generated method stub

		JobSeekerDAOImpl.LOGGER.entry();
		this.paymentBO = new PaymentBO();
		List<EntrolledSeviceVO> enrolledServiceList = new ArrayList<EntrolledSeviceVO>();
		final List<PaymentBO> paymentList = new ArrayList<PaymentBO>();
		try {
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			final Date result = cal.getTime();
			Session session = getSession();
			final Criteria criteria = session
					.createCriteria(EntrolledSeviceVO.class);
			criteria.add(Restrictions.eq("payId", registerId));
			criteria.add(Restrictions.eq("isDeleted", true));
			criteria.add(Restrictions.eq("producType", "jobseeker"));
			criteria.add(Restrictions.between("created", result, new Date()));
			enrolledServiceList = criteria.list();
			for (final EntrolledSeviceVO entrolledSeviceVO : enrolledServiceList) {
				this.paymentBO.setPayId(entrolledSeviceVO.getPayId());
				this.paymentBO.setId(entrolledSeviceVO.getEntrolledid());
				this.paymentBO.setSelectProduct(entrolledSeviceVO
						.getProducType());
				this.paymentBO.setTotalcost(entrolledSeviceVO.getTotalcost());
				this.paymentBO.setCreated(entrolledSeviceVO.getValidFrom());
				this.paymentBO.setEndDate(entrolledSeviceVO.getValidEnd());
				this.paymentBO.setSelectProduct(entrolledSeviceVO
						.getSelectProduct());
				this.paymentBO
				.setProductType(entrolledSeviceVO.getProducType());
				this.paymentBO.setTotalMonth(entrolledSeviceVO.getTotalMonth());
				paymentList.add(this.paymentBO);

			}

		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker lastMonth PaymentList failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Jobseeker lastMonth PaymentList failed:"
					+ exception.getMessage());

		}
		return paymentList;

	}

	@Override
	public List<PaymentBO> retriveJobseekerPayment() {
		List<EntrolledSeviceVO> jobseekerPaymentsList = new ArrayList<EntrolledSeviceVO>();
		JobSeekerDAOImpl.LOGGER.entry();
		final List<PaymentBO> jobseekerPaymentList = new ArrayList<PaymentBO>();
		List<JobseekerVO> paymentList = new ArrayList<JobseekerVO>();
		try {

			final PaymentBO payment = new PaymentBO();
			Session session = getSession();
			final Criteria cr = session.createCriteria(EntrolledSeviceVO.class);
			cr.add(Restrictions.eq("isDeleted", true));
			cr.add(Restrictions.eq("producType", "jobseeker"));
			jobseekerPaymentsList = cr.list();
			for (final EntrolledSeviceVO paymentVO : jobseekerPaymentsList) {
				this.paymentBO = new PaymentBO();
				this.paymentBO.setPayId(paymentVO.getPayId());
				this.paymentBO.setId(paymentVO.getEntrolledid());
				this.paymentBO.setSelectProduct(paymentVO.getProducType());
				this.paymentBO.setTotalcost(paymentVO.getTotalcost());
				this.paymentBO.setValidFrom(paymentVO.getValidFrom());
				this.paymentBO.setEndDate(paymentVO.getValidEnd());
				this.paymentBO.setSelectProduct(paymentVO.getSelectProduct());
				this.paymentBO.setProductType(paymentVO.getProducType());
				this.paymentBO.setTotalMonth(paymentVO.getTotalMonth());
				final Criteria criteria2 = session
						.createCriteria(JobseekerVO.class);
				criteria2.add(Restrictions.eq("id", paymentVO.getPayId()));
				paymentList = criteria2.list();
				for (final JobseekerVO jobseekerVO : paymentList) {
					if (jobseekerVO.getId() == paymentVO.getPayId()) {
						payment.setName(jobseekerVO.getFirstName());
					}

				}
				this.paymentBO.setName(payment.getName());
				jobseekerPaymentList.add(this.paymentBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("retrive JobseekerPayment failed:"
						+ e.getMessage());
			}
			LOGGER.info("retrive JobseekerPayment failed:" + e.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerPaymentList;

	}

	/*
	 * @Override public List<SavejobBO> retriveJobseekersSavedJobs() {
	 * List<SaveJobVO> savejobvolist = new ArrayList<SaveJobVO>();
	 * JobSeekerDAOImpl.LOGGER.entry(); final List<SavejobBO> savejoblist = new
	 * ArrayList<SavejobBO>(); SavejobBO saveJobBO1; try { Session session =
	 * getSession(); final Criteria cr =
	 * session.createCriteria(SaveJobVO.class);
	 * cr.add(Restrictions.eq("isDeleted", true));
	 * cr.add(Restrictions.eq("isApply", true)); savejobvolist = cr.list(); for
	 * (final SaveJobVO saveJobVO : savejobvolist) { saveJobBO1 = new
	 * SavejobBO();
	 * 
	 * saveJobBO1.setIsDeleted(saveJobVO.getIsDeleted());
	 * saveJobBO1.setId(saveJobVO.getApplicationid());
	 * saveJobBO1.setCreatedBy(saveJobVO.getCreatedBy());
	 * saveJobBO1.setModifiedBy(saveJobVO.getModifiedBy());
	 * saveJobBO1.setCompanyName(saveJobVO.getCompanyName());
	 * saveJobBO1.setJobLocation(saveJobVO.getJobLocation());
	 * saveJobBO1.setPostedBy(saveJobVO.getPostedBy());
	 * saveJobBO1.setJobTitle(saveJobVO.getJobTitle());
	 * saveJobBO1.setAddress(saveJobVO.getAddress());
	 * saveJobBO1.setContactNo(saveJobVO.getContactNo());
	 * saveJobBO1.setContactPerson(saveJobVO.getContactPerson());
	 * saveJobBO1.setFunctionArea(saveJobVO.getFunctionArea());
	 * saveJobBO1.setIndustryType(saveJobVO.getIndustryType());
	 * saveJobBO1.setJobDescription(saveJobVO.getJobDescription());
	 * saveJobBO1.setKeywords(saveJobVO.getKeywords());
	 * saveJobBO1.setMaxSalary(saveJobVO.getMaxSalary());
	 * saveJobBO1.setMinSalary(saveJobVO.getMinSalary());
	 * saveJobBO1.setMaxExp(saveJobVO.getMaxExp());
	 * saveJobBO1.setMinExp(saveJobVO.getMinExp());
	 * saveJobBO1.setPgQualification(saveJobVO.getPgQualification());
	 * saveJobBO1.setUgQualification(saveJobVO.getUgQualification());
	 * saveJobBO1.setOtherSalaryDetails(saveJobVO .getOtherSalaryDetails());
	 * saveJobBO1.setJobResponse(saveJobVO.getJobResponse());
	 * saveJobBO1.setCurrencyType(saveJobVO.getCurrencyType()); final JobPostBO
	 * jobPostBO = new JobPostBO();
	 * jobPostBO.setId(saveJobVO.getJobpostVO().getJobId());
	 * saveJobBO1.setJobpostBO(jobPostBO); final EmployerLoginBO employerLoginBO
	 * = new EmployerLoginBO();
	 * employerLoginBO.setId(saveJobVO.getEmployerLogin().getId());
	 * saveJobBO1.setEmployerLogin(employerLoginBO); final JobSeekerLoginBO
	 * jobSeekerLoginBO = new JobSeekerLoginBO();
	 * jobSeekerLoginBO.setId(saveJobVO.getJobseekerLogin().getId());
	 * saveJobBO1.setJobseekerLogin(jobSeekerLoginBO);
	 * saveJobVO.getJobseekerLogin().getJobseekerRegistration() .getFirstName();
	 * saveJobBO1.setName(saveJobVO.getJobseekerLogin()
	 * .getJobseekerRegistration().getFirstName());
	 * saveJobBO1.setCreated(saveJobVO.getCreated());
	 * savejoblist.add(saveJobBO1);
	 * 
	 * } } catch (final HibernateException he) { he.printStackTrace();
	 * JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage()); } finally {
	 * 
	 * JobSeekerDAOImpl.LOGGER.exit(); } return savejoblist;
	 * 
	 * }
	 */

	@Override
	public List<SavejobBO> retriveJobseekersSavedJobs() {
		List<SaveJobVO> savejobvolist = new ArrayList<SaveJobVO>();
		JobSeekerDAOImpl.LOGGER.entry();
		final List<SavejobBO> savejoblist = new ArrayList<SavejobBO>();
		SavejobBO saveJobBO1;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(SaveJobVO.class);
			cr.add(Restrictions.eq("isDeleted", true));
			cr.add(Restrictions.eq("isApply", true));
			savejobvolist = cr.list();
			for (final SaveJobVO saveJobVO : savejobvolist) {
				saveJobBO1 = new SavejobBO();

				saveJobBO1.setIsDeleted(saveJobVO.getIsDeleted());
				saveJobBO1.setId(saveJobVO.getApplicationid());
				saveJobBO1.setCreatedBy(saveJobVO.getCreatedBy());
				saveJobBO1.setModifiedBy(saveJobVO.getModifiedBy());
				saveJobBO1.setCompanyName(saveJobVO.getCompanyName());
				saveJobBO1.setJobLocation(saveJobVO.getJobLocation());
				saveJobBO1.setPostedBy(saveJobVO.getPostedBy());
				saveJobBO1.setJobTitle(saveJobVO.getJobTitle());
				saveJobBO1.setAddress(saveJobVO.getAddress());
				saveJobBO1.setContactNo(saveJobVO.getContactNo());
				saveJobBO1.setContactPerson(saveJobVO.getContactPerson());
				saveJobBO1.setFunctionArea(saveJobVO.getFunctionArea());
				saveJobBO1.setIndustryType(saveJobVO.getIndustryType());
				saveJobBO1.setJobDescription(saveJobVO.getJobDescription());
				saveJobBO1.setKeywords(saveJobVO.getKeywords());
				saveJobBO1.setMaxSalary(saveJobVO.getMaxSalary());
				saveJobBO1.setMinSalary(saveJobVO.getMinSalary());
				saveJobBO1.setMaxExp(saveJobVO.getMaxExp());
				saveJobBO1.setMinExp(saveJobVO.getMinExp());
				saveJobBO1.setPgQualification(saveJobVO.getPgQualification());
				saveJobBO1.setUgQualification(saveJobVO.getUgQualification());
				saveJobBO1.setOtherSalaryDetails(saveJobVO
						.getOtherSalaryDetails());
				saveJobBO1.setJobResponse(saveJobVO.getJobResponse());
				saveJobBO1.setCurrencyType(saveJobVO.getCurrencyType());
				final JobPostBO jobPostBO = new JobPostBO();
				jobPostBO.setId(saveJobVO.getJobpostVO().getJobId());
				saveJobBO1.setJobpostBO(jobPostBO);
				final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
				employerLoginBO.setId(saveJobVO.getEmployerLogin().getId());
				saveJobBO1.setEmployerLogin(employerLoginBO);

				final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
				jobSeekerLoginBO.setId(saveJobVO.getJobseekerLogin().getId());
				saveJobBO1.setJobseekerLogin(jobSeekerLoginBO);

				saveJobBO1.setEmailId(saveJobVO.getJobseekerLogin()
						.getEmailAddress());

				saveJobVO.getJobseekerLogin().getJobseekerRegistration()
				.getFirstName();
				saveJobBO1.setName(saveJobVO.getJobseekerLogin()
						.getJobseekerRegistration().getFirstName());

				saveJobBO1.setCreated(saveJobVO.getCreated());
				savejoblist.add(saveJobBO1);

			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return savejoblist;

	}

	/*
	 * @Override public List<AppliedJobBO> retriveJobseekersAppliedJobs() {
	 * List<AppliedJobVO> appliedjobvolist = new ArrayList<AppliedJobVO>();
	 * JobSeekerDAOImpl.LOGGER.entry(); final List<AppliedJobBO> appliedjoblist
	 * = new ArrayList<AppliedJobBO>(); AppliedJobBO appliedjobBO1; try {
	 * Session session = getSession(); final Criteria cr =
	 * session.createCriteria(AppliedJobVO.class); //
	 * cr.add(Restrictions.eq("isDeleted", true)); //
	 * cr.addOrder(Order.desc("created")); appliedjobvolist = cr.list(); for
	 * (final AppliedJobVO appliedJobVO : appliedjobvolist) { appliedjobBO1 =
	 * new AppliedJobBO(); appliedjobBO1.setId(appliedJobVO.getApplicationid());
	 * appliedjobBO1.setCreated(appliedJobVO.getCreated());
	 * appliedjobBO1.setCreatedBy(appliedJobVO.getCreatedBy());
	 * appliedjobBO1.setModifiedBy(appliedJobVO.getModifiedBy());
	 * appliedjobBO1.setCompanyName(appliedJobVO.getCompanyName());
	 * appliedjobBO1.setJobLocation(appliedJobVO.getJobLocation());
	 * appliedjobBO1.setPostedBy(appliedJobVO.getPostedBy());
	 * appliedjobBO1.setJobTitle(appliedJobVO.getJobTitle());
	 * appliedjobBO1.setAddress(appliedJobVO.getAddress());
	 * appliedjobBO1.setContactNo(appliedJobVO.getContactNo());
	 * appliedjobBO1.setContactPerson(appliedJobVO.getContactPerson());
	 * appliedjobBO1.setFunctionArea(appliedJobVO.getFunctionArea());
	 * appliedjobBO1.setIndustryType(appliedJobVO.getIndustryType());
	 * appliedjobBO1.setJobDescription(appliedJobVO .getJobDescription());
	 * appliedjobBO1.setNoVacancies(appliedJobVO.getNoVacancies());
	 * appliedjobBO1.setKeywords(appliedJobVO.getKeywords());
	 * appliedjobBO1.setMaxSalary(appliedJobVO.getMaxSalary());
	 * appliedjobBO1.setMinSalary(appliedJobVO.getMinSalary());
	 * appliedjobBO1.setMaxExp(appliedJobVO.getMaxExp());
	 * appliedjobBO1.setMinExp(appliedJobVO.getMinExp());
	 * appliedjobBO1.setPgQualification(appliedJobVO .getPgQualification());
	 * appliedjobBO1.setUgQualification(appliedJobVO .getUgQualification());
	 * appliedjobBO1.setOtherSalaryDetails(appliedJobVO
	 * .getOtherSalaryDetails());
	 * appliedjobBO1.setJobResponse(appliedJobVO.getJobResponse());
	 * appliedjobBO1.setCurrencyType(appliedJobVO.getCurrencyType()); final
	 * JobPostBO jobPostBO = new JobPostBO();
	 * jobPostBO.setId(appliedJobVO.getJobpostVO().getJobId());
	 * appliedjobBO1.setJobpostBO(jobPostBO); final EmployerLoginBO
	 * employerLoginBO = new EmployerLoginBO();
	 * employerLoginBO.setId(appliedJobVO.getEmployerLogin().getId());
	 * appliedjobBO1.setEmployerLogin(employerLoginBO);
	 * appliedjobBO1.setIsDeleted(appliedJobVO.getIsDeleted()); final
	 * JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
	 * 
	 * jobSeekerLoginBO .setId(appliedJobVO.getJobseekerLogin().getId());
	 * appliedjobBO1.setJobseekerLogin(jobSeekerLoginBO);
	 * appliedJobVO.getJobseekerLogin().getJobseekerRegistration()
	 * .getFirstName(); appliedjobBO1.setName(appliedJobVO.getJobseekerLogin()
	 * .getJobseekerRegistration().getFirstName());
	 * appliedjoblist.add(appliedjobBO1); } } catch (final HibernateException
	 * he) { he.printStackTrace(); JobSeekerDAOImpl.LOGGER.debug(he,
	 * he.getMessage()); } finally {
	 * 
	 * JobSeekerDAOImpl.LOGGER.exit(); } return appliedjoblist;
	 * 
	 * }
	 */

	@Override
	public List<AppliedJobBO> retriveJobseekersAppliedJobs() {
		List<AppliedJobVO> appliedjobvolist = new ArrayList<AppliedJobVO>();
		JobSeekerDAOImpl.LOGGER.entry();
		final List<AppliedJobBO> appliedjoblist = new ArrayList<AppliedJobBO>();
		AppliedJobBO appliedjobBO1;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(AppliedJobVO.class);
			// cr.add(Restrictions.eq("isDeleted", true));
			// cr.addOrder(Order.desc("created"));
			appliedjobvolist = cr.list();
			for (final AppliedJobVO appliedJobVO : appliedjobvolist) {
				appliedjobBO1 = new AppliedJobBO();
				appliedjobBO1.setId(appliedJobVO.getApplicationid());
				appliedjobBO1.setCreated(appliedJobVO.getCreated());
				appliedjobBO1.setCreatedBy(appliedJobVO.getCreatedBy());
				appliedjobBO1.setModifiedBy(appliedJobVO.getModifiedBy());
				appliedjobBO1.setCompanyName(appliedJobVO.getCompanyName());
				appliedjobBO1.setJobLocation(appliedJobVO.getJobLocation());
				appliedjobBO1.setPostedBy(appliedJobVO.getPostedBy());
				appliedjobBO1.setJobTitle(appliedJobVO.getJobTitle());
				appliedjobBO1.setAddress(appliedJobVO.getAddress());
				appliedjobBO1.setContactNo(appliedJobVO.getContactNo());
				appliedjobBO1.setContactPerson(appliedJobVO.getContactPerson());
				appliedjobBO1.setFunctionArea(appliedJobVO.getFunctionArea());
				appliedjobBO1.setIndustryType(appliedJobVO.getIndustryType());
				appliedjobBO1.setJobDescription(appliedJobVO
						.getJobDescription());
				appliedjobBO1.setNoVacancies(appliedJobVO.getNoVacancies());
				appliedjobBO1.setKeywords(appliedJobVO.getKeywords());
				appliedjobBO1.setMaxSalary(appliedJobVO.getMaxSalary());
				appliedjobBO1.setMinSalary(appliedJobVO.getMinSalary());
				appliedjobBO1.setMaxExp(appliedJobVO.getMaxExp());
				appliedjobBO1.setMinExp(appliedJobVO.getMinExp());
				appliedjobBO1.setPgQualification(appliedJobVO
						.getPgQualification());
				appliedjobBO1.setUgQualification(appliedJobVO
						.getUgQualification());
				appliedjobBO1.setOtherSalaryDetails(appliedJobVO
						.getOtherSalaryDetails());
				appliedjobBO1.setJobResponse(appliedJobVO.getJobResponse());
				appliedjobBO1.setCurrencyType(appliedJobVO.getCurrencyType());
				final JobPostBO jobPostBO = new JobPostBO();
				jobPostBO.setId(appliedJobVO.getJobpostVO().getJobId());
				appliedjobBO1.setJobpostBO(jobPostBO);
				final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
				employerLoginBO.setId(appliedJobVO.getEmployerLogin().getId());
				appliedjobBO1.setEmployerLogin(employerLoginBO);
				appliedjobBO1.setIsDeleted(appliedJobVO.getIsDeleted());
				final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();

				jobSeekerLoginBO
				.setId(appliedJobVO.getJobseekerLogin().getId());
				appliedjobBO1.setJobseekerLogin(jobSeekerLoginBO);

				appliedjobBO1.setEmailId(appliedJobVO.getJobseekerLogin()
						.getEmailAddress());

				appliedJobVO.getJobseekerLogin().getJobseekerRegistration()
				.getFirstName();
				appliedjobBO1.setName(appliedJobVO.getJobseekerLogin()
						.getJobseekerRegistration().getFirstName());
				appliedjoblist.add(appliedjobBO1);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return appliedjoblist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.JobSeekerDAO#getEducation(com.myjobkart.vo.
	 * JobseekerEducationVO)
	 */
	@Override
	public JobseekerEducationVO getEducation(JobseekerEducationVO educationVO) {
		return (JobseekerEducationVO) getSession().get(
				JobseekerEducationVO.class, educationVO.getEducationId());
	}

	@Override
	public JobseekerProfessionalVO getExperience(
			JobseekerProfessionalVO professionalVO) {

		return (JobseekerProfessionalVO) getSession().get(
				JobseekerProfessionalVO.class,
				professionalVO.getProfessionalId());
	}

	@Override
	public JobseekerProfileVO getprofileId(JobseekerProfileVO profileVO) {

		return (JobseekerProfileVO) getSession().get(JobseekerProfileVO.class,
				profileVO.getprofileId());
	}

	/*
	 * EXPLATION OF THIS METHOD :
	 * 
	 * EMPLOYER VIEWS JOBSEEKER PROFILE HISTORY IS SORT BY JOBSEEKER PROFILE ID,
	 * 10 DAYS, 20 DAYS, 30 DAYS BASED ON CURRENT DATE
	 * 
	 * Employer History views job Seeker Profile
	 * 
	 * Created Date : 30July2016 Created By : VinothRaj (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.JobSeekerDAO#viewJobSeekerHistory(com.myjobkart.bo.
	 * ViewJobseekerBO)
	 */
	/*
	 * @Override public List<ViewJobseekerBO> viewJobSeekerHistory(
	 * ViewJobseekerBO viewJobseekerBO) { // TODO Auto-generated method stub
	 * 
	 * Session session = getSession(); final List<ViewJobseekerBO> boList = new
	 * ArrayList<ViewJobseekerBO>(); Calendar cal = Calendar.getInstance();
	 * 
	 * Criteria cr1 = null;
	 * 
	 * if (0 != viewJobseekerBO.getId() && 0 != viewJobseekerBO.getDays() &&
	 * viewJobseekerBO.getDays() <= 10) { cal.add(Calendar.DATE,
	 * -viewJobseekerBO.getDays()); cr1 =
	 * session.createCriteria(ViewJobSeekerVO.class);
	 * cr1.add(Restrictions.eq("jobseekerLoginVO.id", viewJobseekerBO.getId()));
	 * cr1.addOrder(Order.desc("historyId"));
	 * 
	 * 
	 * } else if (0 != viewJobseekerBO.getId() && 0 != viewJobseekerBO.getDays()
	 * && (viewJobseekerBO.getDays() > 10 && viewJobseekerBO.getDays() <= 20)) {
	 * cal.add(Calendar.DATE, -viewJobseekerBO.getDays()); cr1 =
	 * session.createCriteria(ViewJobSeekerVO.class);
	 * cr1.add(Restrictions.eq("jobseekerLoginVO.id", viewJobseekerBO.getId()));
	 * cr1.addOrder(Order.desc("historyId"));
	 * 
	 * 
	 * } else if (0 != viewJobseekerBO.getId() && 0 != viewJobseekerBO.getDays()
	 * && (viewJobseekerBO.getDays() > 20 && viewJobseekerBO.getDays() <= 30)) {
	 * 
	 * cal.add(Calendar.DATE, -viewJobseekerBO.getDays());
	 * 
	 * cr1 = session.createCriteria(ViewJobSeekerVO.class);
	 * cr1.add(Restrictions.eq("jobseekerLoginVO.id", viewJobseekerBO.getId()));
	 * cr1.addOrder(Order.desc("historyId"));
	 * 
	 * 
	 * }
	 * 
	 * else if (0 != viewJobseekerBO.getId()) { cr1 =
	 * session.createCriteria(ViewJobSeekerVO.class);
	 * cr1.add(Restrictions.eq("jobseekerLoginVO.id", viewJobseekerBO.getId()));
	 * 
	 * }
	 * 
	 * if (null != cr1.list()) { final List<ViewJobSeekerVO> list1 = cr1.list();
	 * for (final ViewJobSeekerVO vo : list1) { final ViewJobseekerBO vbo = new
	 * ViewJobseekerBO(); vbo.setDays(vo.getDays());
	 * 
	 * List<EmployerProfileVO> employerProfileList = new
	 * ArrayList<EmployerProfileVO>(); if(null != vo.getEmployerRegistration()){
	 * final Criteria cr = getSession().createCriteria(
	 * EmployerProfileVO.class); cr.add(Restrictions.eq("isDelete", true));
	 * 
	 * cr.add(Restrictions.eq("employerRegistion.id",
	 * vo.getEmployerRegistration().getId()));
	 * 
	 * employerProfileList = cr.list(); for(EmployerProfileVO profileVO:
	 * employerProfileList){ if(profileVO.getEmployerRegistion().getId() ==
	 * vo.getEmployerRegistration().getId()){
	 * vbo.setName(profileVO.getCompanyName());
	 * vbo.setEmpName(profileVO.getFirstName());
	 * vbo.setId(profileVO.getEmployerRegistion().getId());
	 * vbo.setCompanyType(profileVO.getCompanyType());
	 * vbo.setEmailAddress(profileVO.getEmailId());
	 * vbo.setConfirmEmailAddress(profileVO.getEmailId());
	 * vbo.setMobileNumber(profileVO.getMobileNo());
	 * vbo.setContactNumber(profileVO.getContactNo());
	 * vbo.setCreated(profileVO.getCreated()); if(null !=
	 * profileVO.getCompanyLogo()){
	 * vbo.setCompanyLogo(profileVO.getCompanyLogo()); }
	 * 
	 * } } } vbo.setCreated(vo.getCreated());
	 * vbo.setJobseekerRegId(vo.getJobseekerLoginVO
	 * ().getJobseekerRegistration().getId()); boList.add(vbo); }
	 * 
	 * } return boList;
	 * 
	 * }
	 */

	@Override
	public List<ViewJobseekerBO> viewJobSeekerHistory(
			ViewJobseekerBO viewJobseekerBO) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		long sno = 1;
		Criteria cr1 = null;
		if (0 != viewJobseekerBO.getId() && 0 != viewJobseekerBO.getDays()
				&& viewJobseekerBO.getDays() <= 10) {
			cal.add(Calendar.DATE, -viewJobseekerBO.getDays());
			cr1 = getSession().createCriteria(ViewJobSeekerVO.class);
			cr1.add(Restrictions.eq("jobseekerLoginVO.id",
					viewJobseekerBO.getId()));
			cr1.add(Restrictions.between("created", cal.getTime(), new Date()));
			/*
			 * cr1.add(Restrictions.eq("created", cal.getTime()));
			 * cr1.add(Restrictions.ge("created", new Date()));
			 */
			cr1.addOrder(Order.desc("historyId"));
		} else if (0 != viewJobseekerBO.getId()
				&& 0 != viewJobseekerBO.getDays()
				&& (viewJobseekerBO.getDays() > 10 && viewJobseekerBO.getDays() <= 20)) {
			cal.add(Calendar.DATE, -viewJobseekerBO.getDays());
			cr1 = getSession().createCriteria(ViewJobSeekerVO.class);
			cr1.add(Restrictions.eq("jobseekerLoginVO.id",
					viewJobseekerBO.getId()));
			cr1.add(Restrictions.between("created", cal.getTime(), new Date()));
			// cr1.add(Restrictions.ge("created", cal.getTime()));
			cr1.addOrder(Order.desc("historyId"));
		} else if (0 != viewJobseekerBO.getId()
				&& 0 != viewJobseekerBO.getDays()
				&& (viewJobseekerBO.getDays() > 20 && viewJobseekerBO.getDays() <= 30)) {
			cal.add(Calendar.DATE, -viewJobseekerBO.getDays());
			cr1 = getSession().createCriteria(ViewJobSeekerVO.class);
			cr1.add(Restrictions.eq("jobseekerLoginVO.id",
					viewJobseekerBO.getId()));
			cr1.add(Restrictions.between("created", cal.getTime(), new Date()));
			// cr1.add(Restrictions.ge("created", cal.getTime()));
			cr1.addOrder(Order.desc("historyId"));
		}

		else if (0 != viewJobseekerBO.getId()) {
			cr1 = getSession().createCriteria(ViewJobSeekerVO.class);
			cr1.add(Restrictions.eq("jobseekerLoginVO.id",
					viewJobseekerBO.getId()));
		}
		if (null != cr1.list() && cr1.list().size() > 0) {
			final List<ViewJobseekerBO> boList = new ArrayList<ViewJobseekerBO>();
			final List<ViewJobSeekerVO> list1 = cr1.list();
			for (final ViewJobSeekerVO vo : list1) {
				final ViewJobseekerBO vbo = new ViewJobseekerBO();
				vbo.setDays(vo.getDays());
				if (null != vo.getEmployerRegistration()) {
					final Criteria cr = getSession().createCriteria(
							EmployerProfileVO.class);
					cr.add(Restrictions.eq("isDelete", true));
					cr.add(Restrictions.eq("employerRegistion.id", vo
							.getEmployerRegistration().getId()));
					List<EmployerProfileVO> employerProfileList = cr.list();
					for (EmployerProfileVO profileVO : employerProfileList) {
						if (profileVO.getEmployerRegistion().getId() == vo
								.getEmployerRegistration().getId()) {
							vbo.setSno(sno++);
							vbo.setViewDate(format.format(vo.getCreated()));
							vbo.setName(profileVO.getCompanyName());
							vbo.setEmpName(profileVO.getFirstName());
							vbo.setId(profileVO.getEmployerRegistion().getId());
							vbo.setCompanyType(profileVO.getCompanyType());
							vbo.setEmailAddress(profileVO.getEmailId());
							vbo.setConfirmEmailAddress(profileVO.getEmailId());
							vbo.setMobileNumber(profileVO.getMobileNo());
							vbo.setContactNumber(profileVO.getContactNo());
							vbo.setCreated(profileVO.getCreated());
							if (null != profileVO.getCompanyLogo()) {
								vbo.setCompanyLogo(profileVO.getCompanyLogo());
							}

						}
					}
				}
				vbo.setCreated(vo.getCreated());
				vbo.setJobseekerRegId(vo.getJobseekerLoginVO()
						.getJobseekerRegistration().getId());
				boList.add(vbo);
			}
			return boList;
		}
		return null;
	}

	@Override
	public JobseekerVO getJobseekerRegistration(JobseekerVO jobseekerVO) {
		// TODO Auto-generated method stub

		jobseekerVO = (JobseekerVO) getSession().get(JobseekerVO.class,
				jobseekerVO.getId());
		return jobseekerVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.JobSeekerDAO#activeJobseekerStatus(com.myjobkart.vo
	 * .JobseekerVO)
	 */
	@Override
	public JobSeekerLoginBO activeJobseekerStatus(JobSeekerLoginBO jobseekerLoginBO) {
		JobSeekerDAOImpl.LOGGER.entry();
		final String changeRegQuery = "UPDATE JobseekerVO E set E.isActive = :IsActive WHERE E.id=:id and E.emailAddress = :emailAddress";
	   // final String changeStatusLoginQuery = "UPDATE JobseekerLoginVO E set E.isActive = :isActive WHERE E.id=:id and E.emailAddress = :emailAddress";
		
		try {
			Session session = getSession();
			final Query query = session.createQuery(changeRegQuery);
			query.setParameter("IsActive", jobseekerLoginBO.getIsActive());
			query.setParameter("emailAddress", jobseekerLoginBO.getEmailAddress());
			query.setParameter("id", jobseekerLoginBO.getJobseekerBO().getId());
			final int result = query.executeUpdate();
			if (0 != result) {
				/*final Query loginquery = session.createQuery(changeStatusLoginQuery);
				query.setParameter("isActive", jobseekerLoginBO.getIsActive());
				query.setParameter("emailAddress", jobseekerLoginBO.getEmailAddress());
				query.setParameter("id", jobseekerLoginBO.getId());
				final int loginResult = loginquery.executeUpdate();
				
						if(0!= loginResult){*/
				return jobseekerLoginBO;
						}
			
			
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.JobSeekerDAO#retrieveJobseekerResume(com.myjobkart.
	 * bo.JobseekerProfileBO)
	 */
	@Override
	public List<JobseekerProfileBO> retrieveJobseekerResume(
			JobseekerProfileBO profile) {
		List<JobseekerProfileVO> jobseekerProfileList = new ArrayList<JobseekerProfileVO>();
		JobSeekerDAOImpl.LOGGER.entry();
		final List<JobseekerProfileBO> jobseekerprofileList = new ArrayList<JobseekerProfileBO>();
		int count = 0;
		try {

			Session session = getSession();
			final Criteria cr = session
					.createCriteria(JobseekerProfileVO.class);
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isDelete", true));

			if (null != profile.getSearchElement()
					&& !profile.getSearchElement().isEmpty()) {
				cr.add(Restrictions.eq("firstName", profile.getSearchElement()));
			}

			if (null != profile.getSearchSkills()
					&& !profile.getSearchSkills().isEmpty()) {
				cr.add(Restrictions.eq("keySkills", profile.getSearchSkills()));
			}

			if (null != profile.getSearchLocation()
					&& !profile.getSearchLocation().isEmpty()) {
				cr.add(Restrictions.like("preferredLocation",
						profile.getSearchLocation()));
			}

			jobseekerProfileList = cr.list();

			for (final JobseekerProfileVO profileVO : jobseekerProfileList) {
				this.profileBO = new JobseekerProfileBO();
				this.profileBO.setVersion(profileVO.getVersion());
				this.profileBO.setCreated(profileVO.getCreated());
				this.profileBO.setCurrentIndustry(profileVO
						.getCurrentIndustry());
				this.profileBO.setCurrentSalary(profileVO.getCurrentSalary());
				this.profileBO.setDeleteBy(profileVO.getDeleteBy());
				this.profileBO.setDeletedDate(profileVO.getDeletedDate());
				this.profileBO.setDomainSkills(profileVO.getDomainSkills());
				this.profileBO.setEmailId(profileVO.getEmailId());
				this.profileBO.setExpectedCtc(profileVO.getExpectedCtc());
				this.profileBO.setExperienceInMonth(profileVO
						.getExperienceInMonth());
				this.profileBO.setExperienceInYear(profileVO
						.getExperienceInYear());
				this.profileBO.setIsActive(profileVO.getIsActive());

				if (this.profileBO.getIsActive()) {
					this.profileBO.setStatus("Active");

				} else {
					this.profileBO.setStatus("De-Active");
				}
				final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
				jobSeekerLoginBO.setId(profileVO.getJobSeekerLogin().getId());
				this.profileBO.setJobSeekerLogin(jobSeekerLoginBO);
				this.profileBO.setCreatedBy(profileVO.getCreatedBy());
				this.profileBO.setFirstName(profileVO.getFirstName());
				this.profileBO.setFunction(profileVO.getFunction());
				this.profileBO.setGender(profileVO.getGender());
				this.profileBO.setProfiledescription(profileVO
						.getProfiledescription());
				this.profileBO.setJobType(profileVO.getJobType());
				this.profileBO.setKeySkills(profileVO.getKeySkills());
				this.profileBO.setLanguagesKnown(profileVO.getLanguagesKnown());
				this.profileBO.setLastName(profileVO.getLastName());
				this.profileBO.setLocation(profileVO.getLocation());
				this.profileBO.setMaritalStatus(profileVO.getMaritalStatus());
				this.profileBO.setModifiedBy(profileVO.getModifiedBy());
				this.profileBO.setNationality(profileVO.getNationality());
				this.profileBO.setPhone(profileVO.getPhone());
				this.profileBO.setPreferredIndustry(profileVO
						.getPreferredIndustry());
				this.profileBO.setPreferredLocation(profileVO
						.getPreferredLocation());
				this.profileBO
				.setProfileImage(profileVO.getProfileImage().getBytes(
						1, (int) profileVO.getProfileImage().length()));
				this.profileBO.setResumeTitle(profileVO.getResumeTitle());

				if (null != profileVO.getUploadResume()) {
					this.profileBO
					.setUploadResume(profileVO.getUploadResume()
							.getBytes(
									1,
									(int) profileVO.getUploadResume()
									.length()));
				}

				this.profileBO.setId(profileVO.getprofileId());
				this.profileBO.setIsDelete(profileVO.getIsDelete());

				// BeanUtils.copyProperties(profileList, jobseekerProfileBO);
				count = count + 1;
				this.profileBO.setSNo(count);
				jobseekerprofileList.add(this.profileBO);

			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve Jobseeker Profile failed:"
						+ e.getMessage());
			}
			LOGGER.info("Retrieve Jobseeker Profile  failed:" + e.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerprofileList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.JobSeekerDAO#createJobAlert(com.myjobkart.vo.JobAlertVO
	 * )
	 */
	@Override
	public long createJobAlert(JobAlertVO jobalertVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		long id = 0;
		try {
			id = (Long) getSession().save(jobalertVO);
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return id;
	}

	@Override
	public JobseekerVO findByJobseekerEmail(String employerEmailId) {
		JobseekerVO employerRegister = null;
		try {
			final Criteria cr = getSession().createCriteria(
					JobseekerVO.class);

			if (null != employerEmailId) {
				cr.add(Restrictions.eq("emailAddress",employerEmailId));
				cr.add(Restrictions.eq("isDelete", true));
			}
			List<JobseekerVO> jobseekerRegisterList = cr.list();
			if (null != jobseekerRegisterList && 0 != jobseekerRegisterList.size()) {
				for (final JobseekerVO registerList : jobseekerRegisterList) {
					employerRegister = registerList;
				}
			} else {
				employerRegister = null;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
		} finally {

		}

		return employerRegister;
	}



	@SuppressWarnings("unchecked")
	public List<JobAlertBO> retriveJobAlert(JobAlertBO alert) {
		JobSeekerDAOImpl.LOGGER.entry();
		final List<JobAlertBO> jobAlertList = new ArrayList<JobAlertBO>();
		try {
			int sno = 1;
			Criteria cr = getSession().createCriteria(JobAlertVO.class);
			cr.add(Restrictions.eq("isDeleted", false));
			cr.createCriteria("jobSeekerLogin").add(
					Restrictions.eq("id", alert.getJobSeekerId()));
			cr.addOrder(Order.desc("created"));
			List<JobAlertVO> jobalertList = cr.list();
			if (null != jobalertList && jobalertList.size() > 0) {
				JobAlertBO alertBO = null;
				for (JobAlertVO alertVO : jobalertList) {
					alertBO = new JobAlertBO();
					BeanUtils.copyProperties(alertVO, alertBO);
					alertBO.setSno(sno++);
					alertBO.setAlertDate(format.format(alertVO.getCreated()));
					alertBO.setId(alertVO.getId());
					alertBO.setExperienceInYear(alertVO.getExperienceInYear());
					alertBO.setIsActive(alertVO.getIsActived());
					if (alertVO.getIsActived()) {
						alertBO.setStatus("Active");
					} else {
						alertBO.setStatus("De-Active");
					}
					jobAlertList.add(alertBO);
				}
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve Jobseeker Profile failed:"
						+ e.getMessage());
			}
			LOGGER.info("Retrieve Jobseeker Profile  failed:" + e.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}

		return jobAlertList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.JobSeekerDAO#deleteAlert(com.myjobkart.vo.JobAlertVO)
	 */
	@Override
	public int deleteAlert(JobAlertBO jobAlertBO) throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE JobAlertVO S set"
				+ " S.isDeleted = :isDeleted," + "S.modifiedBy = :modifiedBy,"
				+ "S.modified=:modified" + " WHERE S.id = :Id";
		try {
			Session session = getSession();
			final Query query = session.createQuery(deleteQuery);
			query.setParameter("isDeleted", jobAlertBO.getIsDeleted());
			query.setParameter("modifiedBy", jobAlertBO.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("Id", jobAlertBO.getId());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (JobSeekerDAOImpl.LOGGER.isDebugEnabled()) {
				JobSeekerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL
						+ he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	@Override
	public JobAlertVO jobalertStatus(JobAlertVO jobalertVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		final String changeLoginQuery = "UPDATE JobAlertVO E set E.isActived = :IsActive WHERE E.id=:id";
		try {
			Session session = getSession();
			final Query query = session.createQuery(changeLoginQuery);
			query.setParameter("IsActive", jobalertVO.getIsActived());
			query.setParameter("id", jobalertVO.getId());
			final int result = query.executeUpdate();
			if (0 != result) {
				return jobalertVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.JobSeekerDAO#alertDetails(com.myjobkart.vo.JobAlertVO)
	 */
	@Override
	public boolean updateAlert(JobAlertVO alertVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		try {
			getSession().saveOrUpdate(alertVO);
			getSession().flush();
			return true;
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return false;

	}

	@Override
	public JobAlertVO updateAlertid(JobAlertVO alertVO) {
		return (JobAlertVO) getSession().get(JobAlertVO.class, alertVO.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.JobSeekerDAO#retriveJobalert(com.myjobkart.bo.JobAlertBO
	 * )
	 */
	@Override
	public JobAlertBO retriveAlert(JobAlertBO alertBO)
			throws MyJobKartException {
		JobSeekerDAOImpl.LOGGER.entry();
		try {
			JobAlertVO jobalertVO = (JobAlertVO) getSession().get(
					JobAlertVO.class, alertBO.getId());
			BeanUtils.copyProperties(jobalertVO, alertBO);
			alertBO.setId(jobalertVO.getId());
			alertBO.setExperienceInYear(jobalertVO.getExperienceInYear());
			alertBO.setIsActive(jobalertVO.getIsActived());
			return alertBO;
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.JobSeekerDAO#emailVerifications(java.lang.String)
	 */
	@Override
	public boolean emailVerifications(String email) {
		try {
			Criteria criteria = getSession().createCriteria(
					JobseekerLoginVO.class);

			criteria.add(Restrictions.eq("emailAddress", email));
			List<JobseekerLoginVO> loginVOList = criteria.list();
			if (null != loginVOList && !loginVOList.isEmpty()) {
				return true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Email verification has failed" + e.getMessage());
			}
			LOGGER.info("Email verification has failed:" + e.getMessage());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.JobSeekerDAO#Mobile_verification(java.lang.String)
	 */
	@Override
	public boolean Mobile_verification(String mobileNo) {
		try {
			Criteria criteria = getSession().createCriteria(JobseekerVO.class);

			criteria.add(Restrictions.eq("mobileNo", Long.parseLong(mobileNo)));
			List<JobseekerVO> loginVOList = criteria.list();
			if (null != loginVOList && !loginVOList.isEmpty()) {
				return true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("mobile no has failed" + e.getMessage());
			}
			LOGGER.info("mobile no has failed:" + e.getMessage());
		}

		return false;
	}

	@Override
	public long activity(JobseekerActivityVO activityVO) {
		try {
			getSession().saveOrUpdate(activityVO);
			if (activityVO.getId() != 0) {
				return activityVO.getId();
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
		}
		return 0;
	}

	private static JobseekerProfileBO preparableVOtoBO(
			JobseekerProfileVO profileVO) throws SerialException, SQLException {
		JobseekerProfileBO profileBO = new JobseekerProfileBO();

		profileBO.setCreated(profileVO.getCreated());
		profileBO.setCurrentIndustry(profileVO.getCurrentIndustry());
		profileBO.setCurrentSalary(profileVO.getCurrentSalary());
		profileBO.setProfesId(profileVO.getprofileId());
		profileBO.setDeleteBy(profileVO.getDeleteBy());
		profileBO.setDeletedDate(profileVO.getDeletedDate());
		profileBO.setDomainSkills(profileVO.getDomainSkills());
		profileBO.setEmailId(profileVO.getEmailId());
		profileBO.setExpectedCtc(profileVO.getExpectedCtc());
		profileBO.setExperienceInMonth(profileVO.getExperienceInMonth());
		profileBO.setExperienceInYear(profileVO.getExperienceInYear());
		profileBO.setIsActive(profileVO.getIsActive());

		if (profileBO.getIsActive()) {
			profileBO.setStatus("Active");

		} else {
			profileBO.setStatus("De-Active");
		}
		final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
		jobSeekerLoginBO.setId(profileVO.getJobSeekerLogin().getId());
		profileBO.setJobSeekerLogin(jobSeekerLoginBO);
		profileBO.setFirstName(profileVO.getFirstName());
		profileBO.setFunction(profileVO.getFunction());
		profileBO.setGender(profileVO.getGender());
		profileBO.setProfiledescription(profileVO.getProfiledescription());
		profileBO.setJobType(profileVO.getJobType());
		profileBO.setKeySkills(profileVO.getKeySkills());
		profileBO.setLanguagesKnown(profileVO.getLanguagesKnown());
		profileBO.setLastName(profileVO.getLastName());
		profileBO.setLocation(profileVO.getLocation());
		profileBO.setPhone(profileVO.getPhone());
		profileBO.setPreferredIndustry(profileVO.getPreferredIndustry());
		profileBO.setPreferredLocation(profileVO.getPreferredLocation());
		profileBO.setAddress(profileVO.getAddress());
		profileBO.setProfileImage(profileVO.getProfileImage().getBytes(1,
				(int) profileVO.getProfileImage().length()));
		profileBO.setResumeTitle(profileVO.getResumeTitle());
		profileBO.setProfileDescriptions(profileVO.getProfileDescriptions());
		if (null != profileVO.getUploadResume()) {
			profileBO.setUploadResume(profileVO.getUploadResume().getBytes(1,
					(int) profileVO.getUploadResume().length()));
		}
		profileBO.setId(profileVO.getprofileId());
		return profileBO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobseekerProfileBO> retriveJobseekerBO(long companyId) {

		JobSeekerDAOImpl.LOGGER.entry();

		final List<JobseekerProfileBO> jobseekerprofileList = new ArrayList<JobseekerProfileBO>();
		try {
			Session session = getSession();
			final Criteria cr = session
					.createCriteria(JobseekerProfileVO.class);
			// cr.addOrder(Order.desc("created"));
			cr.addOrder(Order.asc("firstName"));
			cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("isDelete", true));

			List<JobseekerProfileVO> jobseekerProfileData = cr.list();

			// Retrive Jobseeker Profile List
			for (final JobseekerProfileVO profileVO : jobseekerProfileData) {

				List<JobseekerProfessionalVO> professionlist = profileVO
						.getJobseekerProfessionalVO();

				// The below codition to check professional list is not equal to
				// zero Exprience employee logged in.
				if (profileVO.getJobseekerProfessionalVO().size() != 0) {
					// Retrive Jobseeker Professional List
					for (JobseekerProfessionalVO professionalVO : professionlist) {
						profileBO = new JobseekerProfileBO();

						if (null != professionalVO.getCompanyVO()) {
							// The below check to verify jobseeker current
							// company is not same as the employer logged in.
							if (companyId != professionalVO.getCompanyVO()
									.getCompaniesId()
									&& professionalVO.getExpStatus()) {

								profileBO = preparableVOtoBO(profileVO);
								profileBO.setCreatedDate(format
										.format(profileVO.getCreated()));
								jobseekerprofileList.add(this.profileBO);
							}

						}
					}
				}
				// Fresher Candidate added to the to list
				else {
					profileBO = preparableVOtoBO(profileVO);
					jobseekerprofileList.add(profileBO);

				}
			}
			// jobseekerprofile.addAll(jobseekerprofileList);

		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerprofileList;

	}

	@SuppressWarnings("unused")
	public EmployerAlertVO addEmployerAlert(
			List<EmployerAlertVO> employerAlertList) {
		EmployerAlertVO employeralert = null;
		if (null != employerAlertList) {
			for (EmployerAlertVO empAlert : employerAlertList) {
				JobPostVO jobpostVO = new JobPostVO();
				employeralert = new EmployerAlertVO();
				employeralert.setKeySkills(empAlert.getKeySkills());
				employeralert.setActive(empAlert.isActive());
				employeralert.setEmailAddress(empAlert.getEmailAddress());
				EmployerLoginVO loginVO = new EmployerLoginVO();
				loginVO.setId(empAlert.getEmpLogin().getId());
				employeralert.setEmpLogin(loginVO);
				jobpostVO.setJobId(empAlert.getJobpost().getJobId());
				employeralert.setJobpost(jobpostVO);
				Session session = getSession();
				session.save(employeralert);
				session.flush();
			}
		}
		return employeralert;
	}

	@SuppressWarnings("unused")
	public List<JobPostVO> employerAlert(String keySkills) {
		List<EmployerAlertBO> employeralertBO = new ArrayList<EmployerAlertBO>();
		List<EmployerAlertVO> employeralertVO = new ArrayList<EmployerAlertVO>();
		List<JobPostVO> jobpostVOList = new ArrayList<JobPostVO>();

		EmployerAlertVO employeralert = null;
		try {

			FullTextSession fullTextSession = Search
					.getFullTextSession(getSession());

			QueryBuilder qb = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(JobPostVO.class).get();
			if (null != keySkills) {
				org.apache.lucene.search.Query luceneQuery = qb.keyword()
						.onField("keywords").matching("*" + keySkills + "*")
						.createQuery();
				Query hibQuery = fullTextSession.createFullTextQuery(
						luceneQuery, JobPostVO.class);
				jobpostVOList.addAll(hibQuery.list());
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve  email has failed:" + ex.getMessage());
			}
			LOGGER.info("Retrieve  email has failed:" + ex.getMessage());
		}
		return jobpostVOList;
	}



	@Override
	public List<AppliedJobBO> jobSeekerApplied(AppliedJobBO appliedJobBO) {
		Criteria criteria;
		// List<AppliedJobVO> appliedJobList = new ArrayList<AppliedJobVO>();
		final List<AppliedJobBO> appliedJobListBO = new ArrayList<AppliedJobBO>();
		AppliedJobBO bo = null;
		Session session = getSession();
		criteria = session.createCriteria(AppliedJobVO.class);
		criteria.createCriteria("jobseekerLogin")
				.add(Restrictions.eq("id", appliedJobBO.getLoginId()));
		List<AppliedJobVO> appliedJobList = criteria.list();
		for (final AppliedJobVO appliedJobVO : appliedJobList) {
			bo = new AppliedJobBO();
			bo.setIsDeleted(appliedJobVO.getIsDeleted());
			bo.setJobId(appliedJobVO.getJobpostVO().getJobId());
			appliedJobListBO.add(bo);
		}
		return appliedJobListBO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobseekerProfileBO> retriveJobseeker()
			throws MyJobKartException {
		// List<JobseekerProfileVO> jobseekerProfileList = new
		// ArrayList<JobseekerProfileVO>();
		JobSeekerDAOImpl.LOGGER.entry();
		final List<JobseekerProfileBO> jobseekerprofileList = new ArrayList<JobseekerProfileBO>();
		try {
			Session session = getSession();
			final Criteria cr = session
					.createCriteria(JobseekerProfileVO.class);
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("isDelete", true));

			List<JobseekerProfileVO> jobseekerProfileList = cr.list();

			for (final JobseekerProfileVO profileVO : jobseekerProfileList) {
				this.profileBO = new JobseekerProfileBO();
				this.profileBO.setCreated(profileVO.getCreated());
				this.profileBO.setCreatedDate(format.format(profileVO
						.getCreated()));
				this.profileBO.setCurrentIndustry(profileVO
						.getCurrentIndustry());
				this.profileBO.setCurrentSalary(profileVO.getCurrentSalary());
				this.profileBO.setDeleteBy(profileVO.getDeleteBy());
				this.profileBO.setDeletedDate(profileVO.getDeletedDate());
				this.profileBO.setDomainSkills(profileVO.getDomainSkills());
				this.profileBO.setEmailId(profileVO.getEmailId());
				this.profileBO.setExpectedCtc(profileVO.getExpectedCtc());

				this.profileBO.setNoOfExperience(profileVO.getNoOfExperience());
				this.profileBO.setIsActive(profileVO.getIsActive());

				if (this.profileBO.getIsActive()) {
					this.profileBO.setStatus("Active");

				} else {
					this.profileBO.setStatus("De-Active");
				}
				final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
				jobSeekerLoginBO.setId(profileVO.getJobSeekerLogin().getId());
				this.profileBO.setJobSeekerLogin(jobSeekerLoginBO);
				this.profileBO.setCreatedBy(profileVO.getCreatedBy());
				this.profileBO.setFirstName(profileVO.getFirstName());
				this.profileBO.setFunction(profileVO.getFunction());
				this.profileBO.setGender(profileVO.getGender());
				this.profileBO.setProfiledescription(profileVO
						.getProfiledescription());
				this.profileBO.setJobType(profileVO.getJobType());
				this.profileBO.setKeySkills(profileVO.getKeySkills());
				this.profileBO.setLanguagesKnown(profileVO.getLanguagesKnown());
				this.profileBO.setLastName(profileVO.getLastName());
				this.profileBO.setLocation(profileVO.getLocation());
				this.profileBO.setMaritalStatus(profileVO.getMaritalStatus());
				this.profileBO.setModifiedBy(profileVO.getModifiedBy());
				this.profileBO.setNationality(profileVO.getNationality());
				this.profileBO.setPhone(profileVO.getPhone());
				this.profileBO.setPreferredIndustry(profileVO
						.getPreferredIndustry());
				this.profileBO.setPreferredLocation(profileVO
						.getPreferredLocation());
				profileBO.setAddress(profileVO.getAddress());
				this.profileBO
				.setProfileImage(profileVO.getProfileImage().getBytes(
						1, (int) profileVO.getProfileImage().length()));
				this.profileBO.setResumeTitle(profileVO.getResumeTitle());

				this.profileBO.setProfileDescriptions(profileVO
						.getProfileDescriptions());

				if (null != profileVO.getUploadResume()) {
					this.profileBO
					.setUploadResume(profileVO.getUploadResume()
							.getBytes(
									1,
									(int) profileVO.getUploadResume()
									.length()));
				}
				this.profileBO.setId(profileVO.getprofileId());
				this.profileBO.setNoOfExperience(profileVO.getNoOfExperience());
				jobseekerprofileList.add(this.profileBO);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobseekerprofileList;

	}

	public JobseekerProfileBO retriveJobseeker(
			JobseekerProfileBO jobseekerProfileBO) throws MyJobKartException,
			SerialException, SQLException {
		// List<JobseekerProfileVO> jobseekerProfileList = new
		// ArrayList<JobseekerProfileVO>();
		JobSeekerDAOImpl.LOGGER.entry();
		List<JobseekerProfileBO> jobseekerprofileList = new ArrayList<JobseekerProfileBO>();
		List<JobseekerProfileBO> jobseekereduList = new ArrayList<JobseekerProfileBO>();
		List<JobseekerProfileBO> professionalList = new ArrayList<JobseekerProfileBO>();
		JobseekerProfileBO profileBO;
		JobseekerProfileBO jobProfile = new JobseekerProfileBO();
		JobseekerProfileBO experienceProfile;
		try {
			long sno = 1;
			Session session = getSession();
			final Criteria cr = session
					.createCriteria(JobseekerProfileVO.class);
			if (jobseekerProfileBO != null
					&& jobseekerProfileBO.getEmailId() != null
					|| 0 != jobseekerProfileBO.getId()
					|| 0 != jobseekerProfileBO.getJobseekerRegId()) {
				if (null != jobseekerProfileBO.getEmailId()) {
					cr.add(Restrictions.eq("emailId",
							jobseekerProfileBO.getEmailId()));

				}
				if (jobseekerProfileBO.getId() != 0) {
					cr.add(Restrictions.eq("profileId",
							jobseekerProfileBO.getId()));

				}
				if (jobseekerProfileBO.getJobseekerRegId() != 0) {
					cr.add(Restrictions.eq("jobSeekerRegistration.id",
							jobseekerProfileBO.getJobseekerRegId()));

				}
			}
			
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isDelete", true));
			List<JobseekerProfileVO> jobseekerProfileList = cr.list();
			if (0 != jobseekerProfileList.size()
					&& null != jobseekerProfileList) {
				for (JobseekerProfileVO profileVO : jobseekerProfileList) {
					profileBO = new JobseekerProfileBO();
					profileBO.setSNo(sno++);
					profileBO.setCreatedDate(format.format(profileVO
							.getCreated()));
					profileBO.setCreated(profileVO.getCreated());
					if (null != profileVO.getJobseekerEducationVO()) {
						List<JobseekerEducationVO> eductionalList = profileVO
								.getJobseekerEducationVO();
						for (JobseekerEducationVO eductionVO : eductionalList) {
							JobseekerProfileBO educationalProfile = new JobseekerProfileBO();
							educationalProfile.setEducationId(eductionVO
									.getEducationId());
							educationalProfile
							.setDegree(eductionVO.getDegree());

							if (null != eductionVO.getCollege()
									&& !eductionVO.getCollege().isEmpty()) {
								educationalProfile.setCollege(eductionVO
										.getCollege());
							} else {
								educationalProfile.setCollege("NA");
							}

							educationalProfile.setYearOfPassing(eductionVO
									.getYearOfPassing());
							if (null != eductionVO.getGrade()
									&& !eductionVO.getGrade().isEmpty()) {
								educationalProfile.setGrade(eductionVO
										.getGrade());
							} else {
								educationalProfile.setGrade("NA");
							}
							if (null != eductionVO.getPercentage()
									&& !eductionVO.getPercentage().isEmpty()) {
								educationalProfile.setPercentage(eductionVO
										.getPercentage());
							} else {
								educationalProfile.setPercentage("NA");
							}
							if (null != eductionVO.getDepartment()
									&& !eductionVO.getDepartment().isEmpty()) {
								educationalProfile.setDepartment(eductionVO
										.getDepartment());
							} else {
								educationalProfile.setDepartment("NA");
							}
							jobseekereduList.add(educationalProfile);
						}
					}
					if (null != profileVO.getJobseekerProfessionalVO()) {
						DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
						List<JobseekerProfessionalVO> experienceList = profileVO
								.getJobseekerProfessionalVO();
						for (JobseekerProfessionalVO professionalVO : experienceList) {
							experienceProfile = new JobseekerProfileBO();
							experienceProfile.setProfesId(professionalVO
									.getProfessionalId());
							experienceProfile.setCompanyName(professionalVO
									.getCompanyName());
							if (null != professionalVO.getCompanyType()
									&& !professionalVO.getCompanyType()
									.isEmpty()) {
								experienceProfile.setCompanyType(professionalVO
										.getCompanyType());
							} else {

								experienceProfile.setCompanyType("NA");

							}
							if (null != professionalVO.getDesignation()
									&& !professionalVO.getDesignation()
									.isEmpty()) {
								experienceProfile.setDesignation(professionalVO
										.getDesignation());
							} else {
								experienceProfile.setDesignation("NA");
							}
							experienceProfile.setLastSalary(professionalVO
									.getLastSalary());

							experienceProfile.setExpStatus(professionalVO
									.getExpStatus());
							experienceProfile.setStartDate(df
									.format(professionalVO.getExpStartDate()));
							experienceProfile.setEndDate(df
									.format(professionalVO.getExpEndDate()));

							professionalList.add(experienceProfile);

						}
					}
					profileBO
					.setCurrentIndustry(profileVO.getCurrentIndustry());
					profileBO.setCurrentSalary(profileVO.getCurrentSalary());
					profileBO.setDeleteBy(profileVO.getDeleteBy());
					profileBO.setDeletedDate(profileVO.getDeletedDate());
					profileBO.setDomainSkills(profileVO.getDomainSkills());
					profileBO.setEmailId(profileVO.getEmailId());
					profileBO.setExpectedCtc(profileVO.getExpectedCtc());
					profileBO.setIsDelete(profileVO.getIsDelete());
					profileBO.setExperienceInMonth(profileVO
							.getExperienceInMonth());
					profileBO.setExperienceInYear(profileVO
							.getExperienceInYear());
					profileBO.setAddress(profileVO.getAddress());
					profileBO.setIsActive(profileVO.getIsActive());
					profileBO.setProfiledescription(profileVO
							.getProfiledescription());
					if (null != profileBO.getIsActive()) {
						profileBO.setStatus("Active");
					} else {
						profileBO.setStatus("De-Active");
					}
					profileBO.setVersion(profileVO.getVersion());
					profileBO.setCreated(profileVO.getCreated());
					JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
					jobSeekerLoginBO.setId(profileVO.getJobSeekerLogin()
							.getId());
					profileBO.setJobSeekerLogin(jobSeekerLoginBO);
					profileBO.setJobseekerRegId(profileVO
							.getJobSeekerRegistration().getId());
					profileBO.setCreatedBy(profileVO.getCreatedBy());
					profileBO.setFirstName(profileVO.getFirstName());
					profileBO.setFunction(profileVO.getFunction());
					profileBO.setGender(profileVO.getGender());
					profileBO.setIsActive(profileVO.getIsActive());
					profileBO.setModified(profileVO.getModified());
					profileBO.setJobType(profileVO.getJobType());
					profileBO.setKeySkills(profileVO.getKeySkills());
					profileBO.setLanguagesKnown(profileVO.getLanguagesKnown());
					profileBO.setLastName(profileVO.getLastName());
					profileBO.setLocation(profileVO.getLocation());
					profileBO.setMaritalStatus(profileVO.getMaritalStatus());
					profileBO.setModifiedBy(profileVO.getModifiedBy());
					profileBO.setNationality(profileVO.getNationality());
					profileBO.setPhone(profileVO.getPhone());
					profileBO.setPreferredIndustry(profileVO
							.getPreferredIndustry());
					profileBO.setPreferredLocation(profileVO
							.getPreferredLocation());
					profileBO.setNoOfExperience(profileVO.getNoOfExperience());
					if (null != profileVO.getProfileImage()) {
						profileBO.setProfileImage(profileVO.getProfileImage()
								.getBytes(
										1,
										(int) profileVO.getProfileImage()
										.length()));

						profileBO.setProfilePicture(profileVO.getProfileImage()
								.getBinaryStream());

					}
					if (null != profileVO.getResumeTitle()) {
						profileBO.setResumeTitle(profileVO.getResumeTitle());
					}
					if (null != profileVO.getUploadResume()) {
						profileBO.setUploadResume(profileVO.getUploadResume()
								.getBytes(
										1,
										(int) profileVO.getUploadResume()
										.length()));
					}

					profileBO.setProfileDescriptions(profileVO
							.getProfileDescriptions());
					profileBO.setId(profileVO.getprofileId());
					profileBO.setPincode(profileVO.getPincode());
					profileBO.setState(profileVO.getState());
					jobseekerprofileList.add(profileBO);

				}
				jobProfile.setJobseekerProfileList(jobseekerprofileList);
				jobProfile.setJobEductionProfileList(jobseekereduList);
				jobProfile.setJobprofessionalList(professionalList);
			}

		} catch (Exception he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			JobSeekerDAOImpl.LOGGER.exit();
		}
		return jobProfile;
	}

	@Override
	public JobseekerVO findByEmail(String string, String emailAddress) {
		JobseekerVO employeeRegistraion = null;
		final String loginQuery = "FROM JobseekerVO E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				employeeRegistraion = (JobseekerVO) query.list().get(0);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();

		} 
		return employeeRegistraion;
	}


	@Override
	public List<JobseekerActivityBO> retriveJobseekerActivity(JobseekerActivityBO jobseekerActivityBO) {
		JobSeekerDAOImpl.LOGGER.entry();
		List<JobseekerActivityBO> employerActivityBOList = new ArrayList<JobseekerActivityBO>();
		//List<EmployerActivityVO> employerActivityVOList = new ArrayList<EmployerActivityVO>();

		JobseekerActivityBO activityBO = null;
		JobSeekerLoginBO jobseekerLoginBO  = null;
		JobseekerBO jobseekerBO = null;
		JobseekerProfileBO jobseekerProfileBO = null;
		EmployerLoginBO employerLoginBO = null;
		JobPostBO jobPostBO = null;
		int count = 0;
		try {
			Session session = getSession();
			Criteria criteria = session
					.createCriteria(JobseekerActivityVO.class);
			if (null != jobseekerActivityBO.getStartDate()
					&& null != jobseekerActivityBO.getEndDate()) {
				criteria.add(Restrictions.between("created",
						getStartOfDay(jobseekerActivityBO.getStartDate()),
						getEndOfDay(jobseekerActivityBO.getEndDate())));
			}
			if (null != jobseekerActivityBO.getActivityStatus()) {
				criteria.add(Restrictions.like("status",
						jobseekerActivityBO.getActivityStatus(),
						MatchMode.ANYWHERE));

			}
			if (null != jobseekerActivityBO.getJobseekerLoginBO()) {
				
				criteria.createCriteria("jobseekerLoginVO")
				.add(Restrictions.eq("id", jobseekerActivityBO.getJobseekerLoginBO().getId()));

			}
			
			


			criteria.addOrder(Order.desc("created"));
			List<JobseekerActivityVO> jobseekerActivityVOList = criteria.list();
			if(null != jobseekerActivityVOList && jobseekerActivityVOList.size() != 0){
				for (JobseekerActivityVO activityVO : jobseekerActivityVOList) {
					activityBO = new JobseekerActivityBO();
					jobseekerLoginBO  = new JobSeekerLoginBO();
					 jobseekerBO = new JobseekerBO();
					jobseekerProfileBO = new JobseekerProfileBO();
					employerLoginBO = new EmployerLoginBO();
					jobPostBO = new JobPostBO();
					
					if(null != activityVO.getJobseekerLoginVO()){
						jobseekerLoginBO.setId(activityVO.getJobseekerLoginVO().getId());
						activityBO.setJobseekerName(activityVO.getJobseekerLoginVO().getJobseekerRegistration().getFirstName());
						activityBO.setActivityStatus(activityVO.getStatus());
						activityBO.setActivityDate(activityVO.getActivityDate());
						activityBO.setJobseekerLoginBO(jobseekerLoginBO);
						
						if(null != activityVO.getJobseekerProfileVO()){
							activityBO.setJobseekerName(activityVO.getJobseekerProfileVO().getFirstName());
							activityBO.setActivityStatus(activityVO.getStatus());
							activityBO.setActivityDate(activityVO.getActivityDate());
							jobseekerProfileBO.setId(activityVO.getJobseekerProfileVO().getprofileId());
							activityBO.setJobseekerProfileBO(jobseekerProfileBO);
							}
						
						if(null != activityVO.getEmployerLoginVO() && null != activityVO.getJobPostVO()){
							
							activityBO.setEmployerName(activityVO.getEmployerLoginVO().getEmployerRegistration().getFirstName());
							activityBO.setJobPostTitle(activityVO.getJobPostVO().getJobTitle());
							activityBO.setActivityStatus(activityVO.getStatus());
							activityBO.setActivityDate(activityVO.getActivityDate());
							employerLoginBO.setId(activityVO.getEmployerLoginVO().getId());
							activityBO.setEmployerLoginBO(employerLoginBO);
							jobPostBO.setId(activityVO.getJobPostVO().getJobId());
							activityBO.setJobPostBO(jobPostBO);
						}
						
					}
					
					if(null != activityVO.getJobseekerVO()){
						activityBO.setJobseekerName(activityVO.getJobseekerVO().getFirstName());
						activityBO.setActivityStatus(activityVO.getStatus());
						activityBO.setActivityDate(activityVO.getActivityDate());
						jobseekerBO.setId(activityVO.getJobseekerVO().getId());
						activityBO.setJobseekerBO(jobseekerBO);
					}
					count = count + 1;
					activityBO.setSNo(count);
					employerActivityBOList.add(activityBO);
				}
			}
		} catch (final Exception ee) {
			ee.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Records Can't be Found" + ee.getMessage());
			}
			LOGGER.info("Records Can't be Found" + ee.getMessage());

		}

		return employerActivityBOList;
	}

	public static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

}
