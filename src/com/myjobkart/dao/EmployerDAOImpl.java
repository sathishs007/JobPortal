package com.myjobkart.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
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
import org.springframework.transaction.annotation.Transactional;

import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.BannerPostBO;
import com.myjobkart.bo.BookingBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EmployerSubuserBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobSeekerLoginBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.SaveCandidateBO;
import com.myjobkart.bo.ShortListCandidate;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.service.EmployerServiceImpl;
import com.myjobkart.service.GenericService;
import com.myjobkart.utils.ErrorCodes;
import com.myjobkart.utils.SendMail;
import com.myjobkart.vo.AppliedJobVO;
import com.myjobkart.vo.BODTO;
import com.myjobkart.vo.BannerPostVO;
import com.myjobkart.vo.BookingVO;
import com.myjobkart.vo.CompanyVO;
import com.myjobkart.vo.EmployerActivityVO;
import com.myjobkart.vo.EmployerAlertVO;
import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerProductServiceVO;
import com.myjobkart.vo.EmployerProfileVO;
import com.myjobkart.vo.EmployerSubuserVO;
import com.myjobkart.vo.EmployerVO;
import com.myjobkart.vo.EmployerjobAlertsVO;
import com.myjobkart.vo.EntrolledSeviceVO;
import com.myjobkart.vo.FeedbackVO;
import com.myjobkart.vo.JobPostVO;
import com.myjobkart.vo.JobseekerLoginVO;
import com.myjobkart.vo.JobseekerProfessionalVO;
import com.myjobkart.vo.JobseekerProfileVO;
import com.myjobkart.vo.JobseekerVO;
import com.myjobkart.vo.ProductVO;
import com.myjobkart.vo.SaveCandidateVO;
import com.myjobkart.vo.ShortListVO;
import com.myjobkart.vo.ViewJobSeekerVO;
import com.myjobkart.vo.WalkinVO;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Aravindhan
 * Description : This is the EmployerDAOImpl layer which is used to do persist
 * the data into the system. Reviewed by :
 * 
 */
@Transactional
@Repository("employerDAOImpl")
public class EmployerDAOImpl extends GenericDAOImpl<EmployerVO> implements
		EmployerDAO {

	public EmployerDAOImpl() throws MyJobKartException {
		super();
		// TODO Auto-generated constructor stub
	}

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	long id;
	JobseekerProfileBO jobProfileBO;
	AppliedJobBO appliedjobBO;
	SaveCandidateBO saveCandidateBO;
	PaymentBO paymentBO;

	private static final JLogger LOGGER = JLogger
			.getLogger(EmployerDAOImpl.class);

	private GenericService<EmployerVO> employerService;

	@Override
	protected GenericService<EmployerVO> getBasicService() {

		return this.employerService;
	}

	/**
	 * This Approve Employer method is used to approve the Employer in the
	 * system so that he can able to login in the system
	 * 
	 * @param login
	 * @throws MyJobKartException
	 */
	@Override
	public void approveEmployer(EmployerLoginVO employerLogin)
			throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		Session session = getSession();
		final EmployerVO registration = new EmployerVO();
		try {
			registration.setId(employerLogin.getId());
			final EmployerVO registration2 = (EmployerVO) session.get(
					EmployerVO.class, registration.getId());
			registration2.setIsActive(false);
			session.update(registration2);
			session.flush();

			employerLogin.setEmployerRegistration(registration2);

			final Serializable id = session.save(employerLogin);
			if (id != null) {
				// for(EmployerLoginBO emailList:employerLogin)
				final SendMail sendMail = new SendMail();
				final String toaddress = employerLogin.getEmailAddress();
				final String subject = "Approval";
				final String bodyContent = "Dear "
						+ registration2.getFirstName()
						+ ","
						+ "\n\n\tClick here to conformation your registration in Scube Technologies:\n"
						+ "\t http://www.scubetechnologies.co.in/faces/UserConformation.jsp?emailId="
						+ employerLogin.getEmailAddress()
						+ "\n"
						+ "\n\n\n\n\nRegards,\nOperation Manager,\nScube Technologies,\nChennai-600 116.";
				sendMail.send(toaddress, subject, bodyContent);
			}

		} catch (final RuntimeException re) {
			re.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(ErrorCodes.EM_ACT_FAIL_MSG + re);
			}
			throw new MyJobKartException(ErrorCodes.EM_ACT_FAIL,
					ErrorCodes.EM_ACT_FAIL_MSG);
		} catch (final Exception e) {
			e.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(ErrorCodes.EM_ACT_FAIL_MSG + e);
			}
			throw new MyJobKartException(ErrorCodes.EM_ACT_FAIL,
					ErrorCodes.EM_ACT_FAIL_MSG);
		}
		EmployerDAOImpl.LOGGER.exit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployerVO> getunapproveEmployers(
			EmployerVO employerRegistration) throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		List<EmployerVO> ul = null;
		final Boolean isActive = false;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(EmployerVO.class);
			if (null != employerRegistration.getIsActive()
					&& isActive != employerRegistration.getIsActive()) {
				cr.add(Restrictions.eq("isActive", true));
			}
			if (isActive == employerRegistration.getIsActive()) {
				cr.add(Restrictions.eq("isActive", false));
			}
			ul = cr.list();
		} catch (final HibernateException he) {

		} catch (final Exception je) {

		}
		EmployerDAOImpl.LOGGER.exit();
		return ul;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllEmailList() throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		List<String> getAllEmailList = null;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(EmployerVO.class);
			cr.setProjection(Projections.property("emailAddress")).list();
			getAllEmailList = cr.list();
		} catch (final HibernateException he) {

			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		}
		EmployerDAOImpl.LOGGER.exit();
		return getAllEmailList;

	}

	@Override
	public void deleteAll(List<BODTO<EmployerVO>> entityList)
			throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public EmployerVO findByCriteria(EmployerVO entity)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployerLoginVO authendicate(String string, String emailAddress) {
		EmployerDAOImpl.LOGGER.entry();
		EmployerLoginVO employeeLogin = null;
		final String loginQuery = "FROM EmployerLoginVO E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				employeeLogin = (EmployerLoginVO) query.list().get(0);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return employeeLogin;
	}

	@Override
	public EmployerVO findByEmail(String string, String emailAddress) {
		EmployerDAOImpl.LOGGER.entry();
		EmployerVO employeeRegistraion = null;
		final String loginQuery = "FROM EmployerVO E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				employeeRegistraion = (EmployerVO) query.list().get(0);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return employeeRegistraion;
	}

	@Override
	public EmployerLoginVO changeAuthendication(EmployerLoginVO employerLoginVO) {
		EmployerDAOImpl.LOGGER.entry();
		final String changeLoginQuery = "UPDATE EmployerLoginVO E set E.password = :password , E.confirmPassword = :cPassword  WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(changeLoginQuery);
			query.setParameter("password", employerLoginVO.getPassword());
			query.setParameter("cPassword",
					employerLoginVO.getConfirmPassword());
			query.setParameter("emailAddress",
					employerLoginVO.getEmailAddress());
			final int result = query.executeUpdate();
			if (0 != result) {
				return employerLoginVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();

			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return null;

	}

	@Override
	public long employerActivation(EmployerLoginVO employerLoginVO,
			EmployerProductServiceVO employeerProductServiceVO) {
		EmployerDAOImpl.LOGGER.entry();
		long employerId = 0;
		try {

			final boolean isActive = true;
			final String updateQuery = "UPDATE EmployerVO S set"
					+ " S.isActive = :isActive," + "S.createdBy = :createdBy,"
					+ "S.modifiedBy = :modifiedBy," + "S.modified=:modified"
					+ " WHERE S.id = :id";

			if (null != employerLoginVO) {
				if (employerLoginVO.isActive()) {
					employerId = (Long) getSession().save(employerLoginVO);
				}
				final Query query = getSession().createQuery(updateQuery);
				query.setParameter("isActive", isActive);
				query.setParameter("createdBy", employerLoginVO.getCreatedBy());
				query.setParameter("modifiedBy",
						employerLoginVO.getModifiedBy());
				query.setParameter("modified", new Date());
				query.setParameter("id", employerLoginVO
						.getEmployerRegistration().getId());
				query.executeUpdate();

				employerId = employerLoginVO.getEmployerRegistration().getId();
				final EmployerVO obj = new EmployerVO();
				obj.setId(employerLoginVO.getEmployerRegistration().getId());
				employeerProductServiceVO.setEmployer(obj);
				getSession().saveOrUpdate(employeerProductServiceVO);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return employerId;
	}

	@Override
	public long createProfile(EmployerProfileVO employerProfileVO) {
		EmployerDAOImpl.LOGGER.entry();
		EmployerActivityVO emActivityVO = new EmployerActivityVO();
		long profileId = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Session session = getSession();
			session.saveOrUpdate(employerProfileVO);
			if (employerProfileVO.getProfileId() != 0) {
				profileId = employerProfileVO.getProfileId();
				emActivityVO.setCreated(employerProfileVO.getCreated());
				emActivityVO.setCreatedBy(employerProfileVO.getCreatedBy());
				emActivityVO.setEmployerProfileVO(employerProfileVO);
				emActivityVO.setActivityDate(format.format(employerProfileVO
						.getCreated()));
				emActivityVO.setStatus("New Profile");
				session.saveOrUpdate(emActivityVO);

			}
			session.flush();

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return profileId;
	}

	@Override
	public long saveProfile(SaveCandidateVO saveCandidateVO) {
		EmployerDAOImpl.LOGGER.entry();
		long profileId = 0;
		try {
			EmployerActivityVO emActivityVO = new EmployerActivityVO();
			EmployerVO employerVO;
			// List<EmployerVO> regList = new ArrayList<EmployerVO>();
			JobseekerProfileVO porfileVO = new JobseekerProfileVO();
			Session session = getSession();
			session.saveOrUpdate(saveCandidateVO);

			// Employer Activity Process
			if (saveCandidateVO.getSavecandidateid() != 0) {
				profileId = saveCandidateVO.getSavecandidateid();
				emActivityVO.setCreated(saveCandidateVO.getCreated());
				emActivityVO.setCreatedBy(saveCandidateVO.getCreatedBy());
				porfileVO.setprofileId(saveCandidateVO.getJobseekerProfileVO()
						.getprofileId());
				emActivityVO.setJobseekerProfileVO(porfileVO);
				emActivityVO.setActivityDate(format.format(saveCandidateVO
						.getCreated()));
				saveCandidateVO.getEmployerLoginVO().getEmailAddress();

				final Criteria cr = session.createCriteria(EmployerVO.class);
				cr.add(Restrictions.eq("emailAddress",
						saveCandidateVO.getEmailId()));
				List<EmployerVO> regList = cr.list();
				if (null != regList && regList.size() != 0) {
					for (EmployerVO regVO : regList) {
						employerVO = new EmployerVO();
						employerVO.setId(regVO.getId());
						emActivityVO.setEmployerVO(employerVO);
					}
				}

				emActivityVO.setStatus("Saved Candidate");
				session.saveOrUpdate(emActivityVO);

			}

			session.flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return profileId;
	}

	@Override
	public ShortListVO shortlistCandidate(ShortListVO shortListVO) {
		EmployerDAOImpl.LOGGER.entry();
		long profileId = 0;
		final String shortlisted = "UPDATE AppliedJobVO E set E.isShortlisted = :isShortlisted WHERE E.applicationid=:applicationid ";
		final String shortlistSaved = "UPDATE SaveCandidateVO E set E.isShortListed = :isShortListed WHERE E.savecandidateid=:savecandidateid ";
		try {
			Session session = getSession();

			session.saveOrUpdate(shortListVO);
			if (shortListVO.getShortlistId() != 0) {
				profileId = shortListVO.getShortlistId();
			}
			if (shortListVO.getAppliedJobVO() != null) {
				if (0 != shortListVO.getAppliedJobVO().getApplicationid()
						&& 0 != profileId) {
					final Query query = session.createQuery(shortlisted);
					query.setParameter("isShortlisted", true);
					query.setParameter("applicationid", shortListVO
							.getAppliedJobVO().getApplicationid());
					query.executeUpdate();
				}
			} else if (shortListVO.getCandidateVO() != null) {
				if (0 != shortListVO.getCandidateVO().getSavecandidateid()
						&& 0 != profileId) {
					final Query query = session.createQuery(shortlistSaved);
					query.setParameter("isShortListed", true);
					query.setParameter("savecandidateid", shortListVO
							.getCandidateVO().getSavecandidateid());
					query.executeUpdate();
				}
			}
			session.flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return shortListVO;
	}

	@Override
	public List<EmployerProfileBO> retriveEmployerProfile() {
		EmployerDAOImpl.LOGGER.entry();
		final List<EmployerProfileBO> profileBOList = new ArrayList<EmployerProfileBO>();
		// List<EmployerProfileVO> profileVOList = new
		// ArrayList<EmployerProfileVO>();
		EmployerProfileBO profileBO;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(EmployerProfileVO.class);
			cr.add(Restrictions.eq("isDelete", true));
			cr.add(Restrictions.eq("isActive", true));
			List<EmployerProfileVO> profileVOList = cr.list();

			for (final EmployerProfileVO jobPostVO : profileVOList) {
				profileBO = new EmployerProfileBO();
				profileBO.setId(jobPostVO.getProfileId());
				profileBO.setCompanyLogo(jobPostVO.getCompanyLogo().getBytes(1,
						(int) jobPostVO.getCompanyLogo().length()));
				profileBOList.add(profileBO);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} catch (final Exception e) {

		}
		EmployerDAOImpl.LOGGER.exit();
		return profileBOList;
	}

	@Override
	public List<JobPostBO> retrieveJobPosting() {
		EmployerDAOImpl.LOGGER.entry();
		final List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();
		// List<JobPostVO> jobPostVOList = new ArrayList<JobPostVO>();
		JobPostBO jobPostBO;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(JobPostVO.class);
			// cr.add(Restrictions.eq("isDeleted", true));
			List<JobPostVO> jobPostVOList = cr.list();

			for (final JobPostVO jobPostVO : jobPostVOList) {
				jobPostBO = new JobPostBO();
				jobPostBO.setAddress(jobPostVO.getAddress());
				jobPostBO.setCompanyName(jobPostVO.getCompanyName());
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
				jobPostBO.setOtherSalaryDetails(jobPostVO
						.getOtherSalaryDetails());
				jobPostBO.setPgQualification(jobPostVO.getPgQualification());
				jobPostBO.setUgQualification(jobPostVO.getUgQualification());

				jobPostBO.setCreated(jobPostVO.getCreated());
				jobPostBO.setModified(jobPostVO.getModified());
				jobPostBO.setExperiance(jobPostVO.getMinExp() + "-"
						+ jobPostVO.getMaxExp());
				jobPostBO.setPostedBy(jobPostVO.getPostedBy());
				jobPostBO.setId(jobPostVO.getJobId());
				final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
				employerLoginBO.setId(jobPostVO.getEmployerLogin().getId());
				jobPostBO.setEmployerLogin(employerLoginBO);
				// EmployerProfileBO employerBO = new EmployerProfileBO();
				// employerBO.setId(jobPostVO.getEmployerProfileVO().getEmployerRegistion().getId());
				// jobPostBO.setEmployerRegistion(employerBO);
				jobPostBO.setSalary(jobPostVO.getMinSalary() + "-"
						+ jobPostVO.getMaxSalary());
				jobPostBOList.add(jobPostBO);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} catch (final Exception e) {

		}
		EmployerDAOImpl.LOGGER.exit();
		return jobPostBOList;
	}

	@Override
	public long getJobseekerRegId(ShortListCandidate shortListCandidate) {
		EmployerDAOImpl.LOGGER.entry();
		// List<JobseekerProfileVO> profileList = new
		// ArrayList<JobseekerProfileVO>();
		long jobseekerProfileId = 0;
		try {
			Session session = getSession();
			final Criteria cr = session
					.createCriteria(JobseekerProfileVO.class);
			cr.add(Restrictions.eq("isActive", true));
			List<JobseekerProfileVO> profileList = cr.list();

			for (JobseekerProfileVO profileVO : profileList) {
				if (shortListCandidate.getJobseekerId() == profileVO
						.getJobSeekerLogin().getId()) {
					jobseekerProfileId = profileVO.getprofileId();
				}
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		EmployerDAOImpl.LOGGER.exit();
		return jobseekerProfileId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JobPostBO retriveEmployer(JobPostBO jobPostBO)
			throws MyJobKartException, SerialException, SQLException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		final List<JobPostBO> employerProfileList = new ArrayList<JobPostBO>();
		List<JobPostVO> employerProfile = null;
		EmployerDAOImpl.LOGGER.entry();
		// JobPostBO jobPostBO;
		JobPostBO countPost = new JobPostBO();

		try {
			List<JobPostBO> countCompany = new ArrayList<JobPostBO>();
			List<JobPostBO> countLocation = new ArrayList<JobPostBO>();
			List<JobPostBO> countTitle = new ArrayList<JobPostBO>();
			final Criteria cr = getSession().createCriteria(JobPostVO.class);
			if (0 != jobPostBO.getJobId()) {
				cr.add(Restrictions.eq("jobId", jobPostBO.getJobId()));
			}
			if (null != jobPostBO.getSearchElement()) {
				String[] locations = jobPostBO.getSearchElement().split(",");
				for (String location : locations) {
					cr.add(Restrictions.like("jobLocation", "%" + location + "%"));
				}
			}
			if (null != jobPostBO.getJobTitle()
					&& !jobPostBO.getJobTitle().isEmpty()) {
				cr.add(Restrictions.eq("jobTitle", jobPostBO.getJobTitle()));
			}
			if (0 != jobPostBO.getCompanyId()) {
				cr.add(Restrictions.eq("companyId", jobPostBO.getCompanyId()));
			}
			if (null != jobPostBO.getKeywords()) {
				String[] skills = jobPostBO.getKeywords().split(",");
				for (String skill : skills) {
					cr.add(Restrictions.like("keywords", "%" + skill + "%"));
				}
			}
			if (null != jobPostBO.getCompanyName()
					&& !jobPostBO.getCompanyName().isEmpty()) {
				cr.add(Restrictions.eq("companyName",
						jobPostBO.getCompanyName()));
			}
			if (null != jobPostBO.getAddress()) {
				cr.add(Restrictions.eq("address", jobPostBO.getAddress()));

			}
			if (null == jobPostBO.getIsActive()) {
				cr.add(Restrictions.eq("isActive", true));
			}
			cr.add(Restrictions.eq("isDeleted", true));
			cr.addOrder(Order.desc("created"));

			// cr.setMaxResults(6);
			employerProfile = cr.list();

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("companyName")).add(
					Projections.rowCount());

			Criteria c = getSession().createCriteria(JobPostVO.class)
					.add(Restrictions.eq("isDeleted", true))
					.add(Restrictions.eq("isActive", true));
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

			Criteria cir = getSession().createCriteria(JobPostVO.class)
					.add(Restrictions.eq("isDeleted", true))
					.add(Restrictions.eq("isActive", true));
			cir.setProjection(projectionList1);
			List<Object[]> payments1 = cir.list();

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

			Criteria ci = getSession().createCriteria(JobPostVO.class)
					.add(Restrictions.eq("isDeleted", true))
					.add(Restrictions.eq("isActive", true));
			// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			ci.setProjection(projectionList2);
			List<Object[]> payments2 = ci.list();

			for (Object[] payment : payments2) {
				JobPostBO companyCount = new JobPostBO();
				companyCount.setJobLocation((payment[0].toString()));
				companyCount.setCountlocation(Integer.parseInt(payment[1]
						.toString()));
				countLocation.add(companyCount);

			}

			for (final JobPostVO jobPostVO : employerProfile) {
				jobPostBO = new JobPostBO();
				EmployerProfileBO employerProfiles = new EmployerProfileBO();
				employerProfiles.setId(jobPostVO.getEmployerProfileVO()
						.getProfileId());
				jobPostBO.setEmployerRegistion(employerProfiles);
				jobPostBO.setAddress(jobPostVO.getAddress());
				jobPostBO.setCompanyName(jobPostVO.getCompanyName());
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
				jobPostBO.setOtherSalaryDetails(jobPostVO
						.getOtherSalaryDetails());
				jobPostBO.setPgQualification(jobPostVO.getPgQualification());
				jobPostBO.setUgQualification(jobPostVO.getUgQualification());
				jobPostBO.setCreated(jobPostVO.getCreated());
				jobPostBO.setModified(jobPostVO.getModified());
				jobPostBO.setExperiance(jobPostVO.getMinExp() + "-"
						+ jobPostVO.getMaxExp());
				jobPostBO.setPostedBy(jobPostVO.getPostedBy());
				jobPostBO.setId(jobPostVO.getJobId());
				jobPostBO.setMinExp(jobPostVO.getMinExp());
				jobPostBO.setsDate(format.format(jobPostVO.getStartDate()));
				jobPostBO.seteDate(format.format(jobPostVO.getEndDate()));
				jobPostBO.setMaxExp(jobPostVO.getMaxExp());
				final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
				if (null != jobPostVO.getEmployerLogin()) {
					employerLoginBO.setId(jobPostVO.getEmployerLogin().getId());
					jobPostBO.setEmployerLogin(employerLoginBO);
				}
				jobPostBO.setSalary(jobPostVO.getMinSalary() + "-"
						+ jobPostVO.getMaxSalary());
				employerProfileList.add(jobPostBO);
			}

			if (null != employerProfileList) {
				countPost.setJobPostList(employerProfileList);
				countPost.setCompanyList(countCompany);
				countPost.setCountTitleList(countTitle);
				countPost.setCountLocationList(countLocation);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {
			EmployerDAOImpl.LOGGER.exit();
		}
		return countPost;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyEntity> retriveCompanyList() throws MyJobKartException,
			SerialException, SQLException {
		final List<CompanyEntity> companyAllList = new ArrayList<CompanyEntity>();
		List<CompanyVO> companylist = null;
		EmployerDAOImpl.LOGGER.entry();
		CompanyEntity companyEntity;
		try {

			Session session = getSession();
			Criteria cr = session.createCriteria(CompanyVO.class);
			cr.add(Restrictions.eq("isDeleted", false));
			cr.addOrder(Order.desc("companiesId"));
			companylist = cr.list();

			for (final CompanyVO companyVO : companylist) {
				companyEntity = new CompanyEntity();
				companyEntity.setCompanyName(companyVO.getCompaniesName());
				companyEntity.setCompaniesId(companyVO.getCompaniesId());
				companyAllList.add(companyEntity);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {
			EmployerDAOImpl.LOGGER.exit();
		}
		return companyAllList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JobPostBO reteriveJobPost(JobPostBO appliedJobBO) {
		// List<JobPostVO> jobPostList = new ArrayList<JobPostVO>();
		EmployerDAOImpl.LOGGER.entry();
		JobPostBO job = null;
		try {

			Criteria cr = getSession().createCriteria(JobPostVO.class);
			cr.add(Restrictions.eq("employerLogin.id", appliedJobBO
					.getEmployerLogin().getId()));
			cr.add(Restrictions.like("jobTitle", appliedJobBO.getJobTitle(),
					MatchMode.ANYWHERE));
			cr.add(Restrictions.eq("isDeleted", true));
			cr.addOrder(Order.desc("jobId"));
			List<JobPostVO> jobPostList = cr.list();

			for (final JobPostVO companyVO : jobPostList) {
				job = new JobPostBO();
				job.setJobId(companyVO.getJobId());

			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {
			EmployerDAOImpl.LOGGER.exit();
		}
		return job;
	}

	@Override
	public EmployerVO updateEmployer(EmployerVO updateProfile)
			throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.saveOrUpdate(updateProfile);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return updateProfile;

	}

	@Override
	public EmployerProfileVO updateProfile(EmployerProfileVO updateProfile)
			throws MyJobKartException {
		EmployerActivityVO emActivityVO = new EmployerActivityVO();
		EmployerDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			getSession().saveOrUpdate(updateProfile);
			/*
			 * if (updateProfile.getProfileId() != 0) {
			 * emActivityVO.setCreated(updateProfile.getCreated());
			 * emActivityVO.setCreatedBy(updateProfile.getCreatedBy());
			 * emActivityVO.setModifiedBy(updateProfile.getModifiedBy());
			 * emActivityVO.setEmployerProfileVO(updateProfile);
			 * emActivityVO.setActivityDate(format.format(updateProfile
			 * .getCreated())); emActivityVO.setStatus("Updated Profile");
			 * session.saveOrUpdate(emActivityVO); }
			 */
			getSession().flush();
		} catch (final HibernateException he) {
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return updateProfile;

	}

	@Override
	public EmployerProfileBO employeerProfileCheck(
			EmployerProfileVO employerProfileVO1) {
		EmployerDAOImpl.LOGGER.entry();
		// List<EmployerProfileVO> employeeProfileList = new
		// ArrayList<EmployerProfileVO>();
		final EmployerProfileBO employerProfileBO = new EmployerProfileBO();
		try {
			// session = getSession();
			final Criteria criteria = getSession().createCriteria(
					EmployerProfileVO.class);
			criteria.createCriteria("employerLogin").add(
					Restrictions.eq("id", employerProfileVO1.getProfileId()));
			// criteria.add(Restrictions.eq("isDeleted", false));
			List<EmployerProfileVO> employeeProfileList = criteria.list();
			for (final EmployerProfileVO employerProfileVO : employeeProfileList) {
				employerProfileBO.setId(employerProfileVO.getProfileId());
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
		}

		return employerProfileBO;
	}

	@Override
	public int deleteProfile(EmployerProfileVO deleteProfile)
			throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE EmployerProfileVO S set"
				+ " S.isDelete = :isDelete," + "S.deletedDate = :deletedDate,"
				+ "S.deleteBy = :deleteBy," + "S.modifiedBy = :modifiedBy,"
				+ "S.modified=:modified" + " WHERE S.profileId = :profileId";
		try {
			// session = getSession();
			// session.saveOrUpdate(saveJobVO);
			// session.flush();
			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", deleteProfile.getIsDelete());
			query.setParameter("deletedDate", deleteProfile.getDeletedDate());
			query.setParameter("deleteBy", deleteProfile.getDeleteBy());
			query.setParameter("modifiedBy", deleteProfile.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("profileId", deleteProfile.getProfileId());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;

	}

	@Override
	public int deleteProfile(SaveCandidateVO deleteProfile)
			throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		// List<SaveCandidateVO> saveCandidateList = new
		// ArrayList<SaveCandidateVO>();
		JobseekerProfileVO jbProfileVO = new JobseekerProfileVO();
		EmployerVO employerVO = new EmployerVO();
		EmployerActivityVO emActivityVO = new EmployerActivityVO();

		int result = 0;
		final String deleteQuery = "UPDATE SaveCandidateVO S set"
				+ " S.isDelete = :isDelete," + "S.deletedDate = :deletedDate,"
				+ "S.deleteBy = :deleteBy," + "S.modifiedBy = :modifiedBy,"
				+ "S.modified=:modified"
				+ " WHERE S.savecandidateid = :savecandidateid";
		try {
			// session = getSession();
			// session.saveOrUpdate(saveJobVO);
			// session.flush();
			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", deleteProfile.getIsDelete());
			query.setParameter("deletedDate", deleteProfile.getDeletedDate());
			query.setParameter("deleteBy", deleteProfile.getDeleteBy());
			query.setParameter("modifiedBy", deleteProfile.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("savecandidateid",
					deleteProfile.getSavecandidateid());
			result = query.executeUpdate();
			if (deleteProfile.getSavecandidateid() != 0) {
				final Criteria cr = getSession().createCriteria(
						SaveCandidateVO.class);
				cr.add(Restrictions.eq("savecandidateid",
						deleteProfile.getSavecandidateid()));
				List<SaveCandidateVO> saveCandidateList = cr.list();
				if (null != saveCandidateList) {
					for (SaveCandidateVO candidateVO : saveCandidateList) {
						jbProfileVO.setprofileId(candidateVO
								.getJobseekerProfileVO().getprofileId());
						employerVO.setId(deleteProfile.getModifiedBy());
					}
				}
			}
			Session session = getSession();
			emActivityVO.setCreated(new Date());
			emActivityVO.setCreatedBy(deleteProfile.getModifiedBy());

			emActivityVO.setEmployerVO(employerVO);
			emActivityVO.setActivityDate(format.format(new Date()));
			emActivityVO.setStatus("De-SavedCandidate");
			emActivityVO.setJobseekerProfileVO(jbProfileVO);
			session.saveOrUpdate(emActivityVO);

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;

	}

	@Override
	public int deleteAppliedCandidate(AppliedJobVO deleteCanidate)
			throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE AppliedJobVO S set"
				+ " S.isDeleted = :isDeleted,"
				+ "S.deletedDate = :deletedDate," + "S.deletedBy = :deletedBy,"
				+ "S.modifiedBy = :modifiedBy," + "S.modified=:modified"
				+ " WHERE S.applicationid = :applicationid";
		try {

			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDeleted", deleteCanidate.getIsDeleted());
			query.setParameter("deletedDate", deleteCanidate.getDeletedDate());
			query.setParameter("deletedBy", deleteCanidate.getDeletedBy());
			query.setParameter("modifiedBy", deleteCanidate.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("applicationid",
					deleteCanidate.getApplicationid());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;

	}

	@Override
	public int deleteEmployer(EmployerVO deleteProfile)
			throws MyJobKartException {

		long employerLoginId = 0;
		EmployerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE EmployerVO S set"
				+ " S.isDeleted = :isDeleted,"
				+ "S.deletedDate = :deletedDate," + "S.deletedBy = :deletedBy,"
				+ "S.modifiedBy = :modifiedBy," + "S.modified=:modified"
				+ " WHERE S.id = :Id";
		try {

			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDeleted", deleteProfile.getIsDeleted());
			query.setParameter("deletedDate", deleteProfile.getDeletedDate());
			query.setParameter("deletedBy", deleteProfile.getDeletedBy());
			query.setParameter("modifiedBy", deleteProfile.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("Id", deleteProfile.getId());
			result = query.executeUpdate();
			// List<EmployerLoginVO> employerLoginList = new
			// ArrayList<EmployerLoginVO>();
			final Criteria cr = getSession().createCriteria(
					EmployerLoginVO.class);
			cr.add(Restrictions.eq("employerRegistration.id",
					deleteProfile.getId()));
			List<EmployerLoginVO> employerLoginList = cr.list();
			if (null != employerLoginList) {
				for (EmployerLoginVO loginVO : employerLoginList) {
					if (loginVO.getEmployerRegistration().getId() == deleteProfile
							.getId()) {
						employerLoginId = loginVO.getId();
					}
				}
			}
			final String logindeleteQuery = "UPDATE EmployerLoginVO L set "
					+ "L.isActive=false" + " WHERE L.id = " + employerLoginId;

			final Query loginquery = getSession().createQuery(logindeleteQuery);
			result = loginquery.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;

	}

	@Override
	public int deleteJobPost(JobPostVO deleteProfile) throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE JobPostVO S set"
				+ " S.isDeleted = :isDeleted,"
				+ "S.deletedDate = :deletedDate," + "S.deletedBy = :deletedBy,"
				+ "S.modifiedBy = :modifiedBy," + "S.modified=:modified"
				+ " WHERE S.jobId = :jobId";
		try {

			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDeleted", deleteProfile.getIsDeleted());
			query.setParameter("deletedDate", deleteProfile.getDeletedDate());
			query.setParameter("deletedBy", deleteProfile.getDeletedBy());
			query.setParameter("modifiedBy", deleteProfile.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("jobId", deleteProfile.getJobId());
			result = query.executeUpdate();

			EmployerActivityVO emActivityVO = new EmployerActivityVO();
			EmployerVO employerVO = new EmployerVO();
			JobPostVO jobpostVO = new JobPostVO();
			emActivityVO.setCreated(deleteProfile.getCreated());
			emActivityVO.setCreatedBy(deleteProfile.getModifiedBy());
			emActivityVO.setModifiedBy(deleteProfile.getModifiedBy());
			emActivityVO.setModified(deleteProfile.getModified());
			emActivityVO.setActivityDate(format.format(deleteProfile
					.getModified()));
			emActivityVO.setStatus("Deleted JobPost");
			employerVO.setId(deleteProfile.getModifiedBy());
			emActivityVO.setEmployerVO(employerVO);

			jobpostVO.setJobId(deleteProfile.getJobId());
			emActivityVO.setJobPostVO(jobpostVO);
			Session session = getSession();
			session.saveOrUpdate(emActivityVO);
			session.flush();

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	@Override
	public long jobPost(List<JobPostVO> uploadJobPostVO)
			throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		EmployerActivityVO emActivityVO = new EmployerActivityVO();
		EmployerProfileVO profileVO = new EmployerProfileVO();
		JobPostVO uploadVO;
		long jobPostId = 0;
		try {
			if (null != uploadJobPostVO && uploadJobPostVO.size() != 0) {
				for (JobPostVO jobPostVO : uploadJobPostVO) {
					uploadVO = new JobPostVO();
					uploadVO = jobPostVO;
					Session session = getSession();
					session.saveOrUpdate(uploadVO);
					jobPostId = uploadVO.getJobId();
					/*
					 * if (uploadVO.getJobId() != 0) { jobPostId =
					 * uploadVO.getJobId();
					 * emActivityVO.setCreated(uploadVO.getCreated());
					 * emActivityVO.setCreatedBy(uploadVO.getCreatedBy());
					 * 
					 * profileVO.setProfileId(uploadVO.getEmployerProfileVO()
					 * .getProfileId());
					 * emActivityVO.setEmployerProfileVO(profileVO);
					 * emActivityVO.setActivityDate(format.format(uploadVO
					 * .getCreated())); emActivityVO.setStatus("New JobPost");
					 * emActivityVO.setJobPostVO(uploadVO);
					 * session.saveOrUpdate(emActivityVO); }
					 */
				}
			}
			getSession().flush();

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return jobPostId;
	}

	@Override
	public EmployerVO getEmployerRegistraion(EmployerVO employerVO) {
		// TODO Auto-generated method stub

		Session session = getSession();
		employerVO = (EmployerVO) session.get(EmployerVO.class,
				employerVO.getId());

		return employerVO;
	}

	@Override
	public JobseekerProfileBO searchJobseeker(
			JobseekerProfileBO jobseekerProfile) throws MyJobKartException {

		final List<JobseekerProfileBO> searchJobList = new ArrayList<JobseekerProfileBO>();
		List<JobseekerProfileBO> countTitle = new ArrayList<JobseekerProfileBO>();
		JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();

		try {

			final Criteria cr = getSession().createCriteria(
					JobseekerProfileVO.class);
			if (null != jobseekerProfile.getKeySkills()) {
				String[] skills = jobseekerProfile.getKeySkills().split(",");
				for (String skill : skills) {
					cr.add(Restrictions.ilike("keySkills", "%"+skill+"%"));
				}

			}
			if (null != jobseekerProfile.getPreferredIndustry()
					&& !jobseekerProfile.getPreferredIndustry().isEmpty()) {
				String[] industries = jobseekerProfile.getPreferredIndustry()
						.split(",");
				for (String industry : industries) {
					cr.add(Restrictions.ilike("preferredIndustry", "%"+industry+"%"));
				}

			}
			if (null != jobseekerProfile.getPreferredLocation()
					&& !jobseekerProfile.getPreferredLocation().isEmpty()) {

				String[] preferredLocation = jobseekerProfile
						.getPreferredLocation().split(",");
				for (String location : preferredLocation) {
					cr.add(Restrictions.ilike("preferredLocation", "%"+location+"%"));
				}
			}

			if (0 != jobseekerProfile.getExperienceInYear()) {
				cr.add(Restrictions.eq("noOfExperience",
						jobseekerProfile.getExperienceInYear()));
			}
			if (null != jobseekerProfile.getExpectedCtc()) {
				cr.add(Restrictions.eq("lastSalary",
						jobseekerProfile.getExpectedCtc()));
			}

			if (null != jobseekerProfile.getProfiledescription()
					&& !jobseekerProfile.getProfiledescription().isEmpty()) {
				cr.add(Restrictions.eq("profiledescription",
						jobseekerProfile.getProfiledescription()));

			}

			cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("isDelete", true));
			cr.addOrder(Order.desc("created"));

			List<JobseekerProfileVO> searchList = cr.list();
			if (null != searchList) {
				for (final JobseekerProfileVO jobSearch : searchList) {
					List<JobseekerProfessionalVO> professionlist = jobSearch
							.getJobseekerProfessionalVO();

					if (null != professionlist && professionlist.size() != 0) {

						for (JobseekerProfessionalVO professionalVO : professionlist) {
							if (null != professionalVO.getCompanyVO()) {
								if (jobseekerProfile.getCompanyId() != professionalVO
										.getCompanyVO().getCompaniesId()
										&& professionalVO.getExpStatus()) {
									jobseekerProfile = new JobseekerProfileBO();
									// BeanUtils.copyProperties(jobSearch,
									// jobPostBO);
									jobseekerProfile = preparableVOtoBO(jobSearch);
									/*
									 * jobseekerProfile = EmployerDAOImpl
									 * .preparableVOtoBO(jobSearch);
									 */
									jobseekerProfile.setId(jobSearch
											.getprofileId());
									jobseekerProfile.setCreatedDate(format
											.format(jobSearch.getCreated()));
									searchJobList.add(jobseekerProfile);
								}
							}
						}
					} else {
						jobseekerProfile = EmployerDAOImpl
								.preparableVOtoBO(jobSearch);
						jobseekerProfile.setId(jobSearch.getprofileId());
						jobseekerProfile.setCreatedDate(format.format(jobSearch
								.getCreated()));
						searchJobList.add(jobseekerProfile);
					}
				}

			}

			// Get The Count For job seeker title

			ProjectionList projectionList1 = Projections.projectionList();
			projectionList1
					.add(Projections.groupProperty("profiledescription")).add(
							Projections.rowCount());
			Criteria cir = getSession()
					.createCriteria(JobseekerProfileVO.class)
					.add(Restrictions.eq("isDelete", true))
					.add(Restrictions.eq("isActive", true));
			cir.setProjection(projectionList1);
			List<Object[]> payments1 = cir.list();

			for (Object[] payment : payments1) {
				JobseekerProfileBO companyCount = new JobseekerProfileBO();
				companyCount.setProfiledescription((payment[0].toString()));
				companyCount.setTitleCount(Integer.parseInt(payment[1]
						.toString()));
				System.out.println("Title--------"
						+ companyCount.getProfiledescription());

				System.out.println("Count--------"
						+ companyCount.getTitleCount());
				countTitle.add(companyCount);

				System.out.println("countTitle---------" + countTitle.size());
			}

			if (null != searchJobList) {
				jobseekerProfileBO.setResumeList(searchJobList);
				jobseekerProfileBO.setTitleList(countTitle);

			}

			// End

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
			he.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH004,
					ErrorCodes.JOB_SEARCH004_MSG);
			// Fields Interrupt Exception
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH_FAIL,
					ErrorCodes.JOB_SEARCH_FAIL_MSG);
		}
		EmployerDAOImpl.LOGGER.exit();
		return jobseekerProfileBO;
	}

	@SuppressWarnings("unused")
	private static JobseekerProfileBO preparableVOtoBO(
			JobseekerProfileVO profileVO) throws SerialException, SQLException {
		EmployerDAOImpl.LOGGER.entry();
		JobseekerProfileBO profileBO = null;
		try {
			profileBO = new JobseekerProfileBO();
			profileBO.setCreated(profileVO.getCreated());
			profileBO.setCurrentIndustry(profileVO.getCurrentIndustry());
			profileBO.setCurrentSalary(profileVO.getCurrentSalary());
			profileBO.setDeleteBy(profileVO.getDeleteBy());
			profileBO.setDeletedDate(profileVO.getDeletedDate());
			profileBO.setDomainSkills(profileVO.getDomainSkills());
			profileBO.setEmailId(profileVO.getEmailId());
			profileBO.setExpectedCtc(profileVO.getExpectedCtc());
			profileBO.setExperienceInMonth(profileVO.getExperienceInMonth());
			profileBO.setExperienceInYear(profileVO.getExperienceInYear());
			/*
			 * if((profileVO.getExperienceInMonth())>=(12)){ int
			 * month=profileVO.getExperienceInMonth();
			 * 
			 * profileBO.setExperienceInYear(month/12);
			 * profileBO.setExperienceInMonth(month%12);
			 * 
			 * } else{ profileBO.setExperienceInYear(0);
			 * profileBO.setExperienceInMonth(profileVO.getExperienceInMonth());
			 * }
			 */
			profileBO.setProfiledescription(profileVO.getProfiledescription());
			profileBO.setFirstName(profileVO.getFirstName());
			profileBO.setFunction(profileVO.getFunction());
			profileBO.setGender(profileVO.getGender());
			profileBO.setIsActive(profileVO.getIsActive());

			profileBO.setJobType(profileVO.getJobType());
			profileBO.setKeySkills(profileVO.getKeySkills());
			profileBO.setLanguagesKnown(profileVO.getLanguagesKnown());
			profileBO.setLastName(profileVO.getLastName());
			profileBO.setLocation(profileVO.getLocation());
			profileBO.setMaritalStatus(profileVO.getMaritalStatus());
			profileBO.setModifiedBy(profileVO.getModifiedBy());
			profileBO.setNationality(profileVO.getNationality());
			profileBO.setPhone(profileVO.getPhone());
			profileBO.setPreferredIndustry(profileVO.getPreferredIndustry());
			profileBO.setPreferredLocation(profileVO.getPreferredLocation());
			profileBO.setProfileImage(profileVO.getProfileImage().getBytes(1,
					(int) profileVO.getProfileImage().length()));
			profileBO.setResumeTitle(profileVO.getResumeTitle());
			profileBO
					.setProfileDescriptions(profileVO.getProfileDescriptions());
			profileBO.setUploadResume(profileVO.getUploadResume().getBytes(1,
					(int) profileVO.getUploadResume().length()));
			profileBO.setId(profileVO.getprofileId());
		} catch (final NullPointerException ne) {
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(ne.getMessage() + ne);
			}
		}
		EmployerDAOImpl.LOGGER.exit();
		return profileBO;

	}

	@Override
	public EmployerProfileVO profileStatus(EmployerProfileVO employerProfileVO) {
		EmployerDAOImpl.LOGGER.entry();
		final String changeLoginQuery = "UPDATE EmployerProfileVO E set E.isActive = :IsActive WHERE E.profileId = :profileId";
		try {
			// session = getSession();
			final Query query = getSession().createQuery(changeLoginQuery);
			query.setParameter("IsActive", employerProfileVO.getIsActive());
			query.setParameter("profileId", employerProfileVO.getProfileId());
			final int result = query.executeUpdate();
			if (0 != result) {
				return employerProfileVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return null;

	}

	@Override
	public List<JobseekerProfileBO> refineResult(JobseekerProfileBO profileBO)
			throws MyJobKartException {
		JobseekerProfileBO profileBO2;
		List<JobseekerProfileVO> searchList = null;
		final List<JobseekerProfileBO> searchResumeList = new ArrayList<JobseekerProfileBO>();
		final org.apache.lucene.search.Query query;
		try {
			if (null != profileBO.getDegree()) {
				// session = getSession();
				final Criteria cr = getSession().createCriteria(
						JobseekerProfileVO.class);
				cr.add(Restrictions.eq("degree", profileBO.getDegree()));
				searchList = cr.list();
				if (null != searchList && searchList.size() != 0) {
					for (final JobseekerProfileVO profileVO : searchList) {

						profileBO2 = new JobseekerProfileBO();
						profileBO2 = EmployerDAOImpl
								.preparableVOtoBO(profileVO);
						searchResumeList.add(profileBO2);
					}

				}
			} else {
				final FullTextSession fullTextSession = Search
						.getFullTextSession(sessionFactory.getCurrentSession());
				fullTextSession.createIndexer().startAndWait();
				final QueryBuilder qb = fullTextSession.getSearchFactory()
						.buildQueryBuilder()
						.forEntity(JobseekerProfileVO.class).get();
				query = qb
						.keyword()
						.wildcard()
						.onField("companyName")
						.andField("experienceInYear")
						.andField("degree")
						.andField("preferredLocation")
						.andField("designation")
						.matching(
								profileBO.getSearchElement().toLowerCase()
										+ "*").createQuery();

				final Query hibQuery = fullTextSession.createFullTextQuery(
						query, JobseekerProfileVO.class);
				searchList = hibQuery.list();
				if (null != searchList && searchList.size() != 0) {
					for (final JobseekerProfileVO profileVO : searchList) {
						profileBO2 = new JobseekerProfileBO();
						profileBO2 = EmployerDAOImpl
								.preparableVOtoBO(profileVO);
						searchResumeList.add(profileBO2);
					}
				}
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

			he.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH004,
					ErrorCodes.JOB_SEARCH004_MSG);
			// Fields Interrupt Exception
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MyJobKartException(ErrorCodes.JOB_SEARCH_FAIL,
					ErrorCodes.JOB_SEARCH_FAIL_MSG);
		}
		EmployerDAOImpl.LOGGER.exit();
		return searchResumeList;
	}

	/*
	 * @Override public List<AppliedJobBO> reteriveAppliedJobs(AppliedJobBO
	 * appliedJobBO) throws MyJobKartException, SerialException, SQLException {
	 * List<AppliedJobVO> appliedjobvolist = new ArrayList<AppliedJobVO>();
	 * EmployerDAOImpl.LOGGER.entry(); final List<AppliedJobBO> appliedlist =
	 * new ArrayList<AppliedJobBO>(); final List<AppliedJobBO> appliedjoblist =
	 * new ArrayList<AppliedJobBO>(); new ArrayList<JobseekerProfileVO>();
	 * List<JobseekerProfileVO> jobseekerProfileVOList1 = new
	 * ArrayList<JobseekerProfileVO>(); new ArrayList<JobseekerProfileBO>(); try
	 * { final Criteria cr = getSession().createCriteria(AppliedJobVO.class)
	 * .createCriteria("employerLogin"); if
	 * (appliedJobBO.getEmployerLogin().getId() != 0) {
	 * cr.add(Restrictions.eq("id", appliedJobBO.getEmployerLogin() .getId()));
	 * } else { cr.addOrder(Order.desc("created")); } appliedjobvolist =
	 * cr.list(); for (final AppliedJobVO appliedJobVO : appliedjobvolist) {
	 * this.appliedjobBO = new AppliedJobBO();
	 * this.appliedjobBO.setCreated(appliedJobVO.getCreated());
	 * this.appliedjobBO.setJobTitle(appliedJobVO.getJobTitle());
	 * this.appliedjobBO.setCompanyName(appliedJobVO.getCompanyName());
	 * this.appliedjobBO.setId(appliedJobVO.getApplicationid());
	 * this.appliedjobBO.setIsShortlisted(appliedJobVO .getIsShortlisted());
	 * final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO(); this.id
	 * = appliedJobVO.getJobseekerLogin().getId(); jobSeekerLoginBO
	 * .setId(appliedJobVO.getJobseekerLogin().getId());
	 * this.appliedjobBO.setJobseekerLogin(jobSeekerLoginBO);
	 * this.appliedjobBO.setMinExp(appliedJobVO.getMinExp());
	 * this.appliedjobBO.setMaxExp(appliedJobVO.getMaxExp());
	 * this.appliedjobBO.setMinSalary(appliedJobVO.getMinSalary());
	 * this.appliedjobBO.setMaxSalary(appliedJobVO.getMaxSalary());
	 * appliedlist.add(this.appliedjobBO); }
	 * 
	 * final Criteria cr2 = getSession().createCriteria(
	 * JobseekerProfileVO.class); cr2.add(Restrictions.eq("isActive", true));
	 * 
	 * jobseekerProfileVOList1 = cr2.list();
	 * 
	 * for (final AppliedJobBO appliedBO : appliedlist) { long count = 0; for
	 * (final JobseekerProfileVO jobseekerProfileBO : jobseekerProfileVOList1) {
	 * 
	 * if (jobseekerProfileBO.getJobSeekerLogin().getId() == appliedBO
	 * .getJobseekerLogin().getId()) { if (count != 1) { this.appliedjobBO = new
	 * AppliedJobBO(); count++;
	 * this.appliedjobBO.setMinExp(appliedBO.getMinExp());
	 * this.appliedjobBO.setMaxExp(appliedBO.getMaxExp());
	 * this.appliedjobBO.setMinSalary(appliedBO.getMinSalary());
	 * this.appliedjobBO.setMaxSalary(appliedBO.getMaxSalary());
	 * this.appliedjobBO.setFirstName(jobseekerProfileBO .getFirstName());
	 * this.appliedjobBO .setProfiledescription(jobseekerProfileBO
	 * .getProfiledescription());
	 * this.appliedjobBO.setResumeTitle(jobseekerProfileBO .getResumeTitle());
	 * if (null != jobseekerProfileBO.getUploadResume()) { this.appliedjobBO
	 * .setUploadResume(jobseekerProfileBO .getUploadResume() .getBytes( 1,
	 * (int) jobseekerProfileBO .getUploadResume() .length())); }
	 * this.appliedjobBO .setProfileImage(jobseekerProfileBO
	 * .getProfileImage().getBytes( 1, (int) jobseekerProfileBO
	 * .getProfileImage() .length())); this.appliedjobBO
	 * .setPreferredLocation(jobseekerProfileBO .getPreferredLocation());
	 * this.appliedjobBO.setEmailId(jobseekerProfileBO .getEmailId());
	 * 
	 * if (null != jobseekerProfileBO.getExpectedCtc()) { this.appliedjobBO
	 * .setExpectedCtc(jobseekerProfileBO .getExpectedCtc()); }
	 * 
	 * this.appliedjobBO.setPhone(jobseekerProfileBO .getPhone());
	 * this.appliedjobBO .setExperienceInYear(jobseekerProfileBO
	 * .getExperienceInYear()); this.appliedjobBO
	 * .setExperienceInMonth(jobseekerProfileBO .getExperienceInMonth());
	 * this.appliedjobBO .setPreferredIndustry(jobseekerProfileBO
	 * .getPreferredIndustry()); this.appliedjobBO
	 * .setCurrentIndustry(jobseekerProfileBO .getCurrentIndustry());
	 * this.appliedjobBO.setKeySkills(jobseekerProfileBO .getKeySkills());
	 * 
	 * this.appliedjobBO .setCreated(appliedBO.getCreated());
	 * this.appliedjobBO.setJobTitle(appliedBO .getJobTitle());
	 * this.appliedjobBO.setCompanyName(appliedBO .getCompanyName());
	 * this.appliedjobBO.setId(appliedBO.getId());
	 * this.appliedjobBO.setIsShortlisted(appliedBO .getIsShortlisted());
	 * this.appliedjobBO.setJobseekerLogin(appliedBO .getJobseekerLogin());
	 * appliedjoblist.add(this.appliedjobBO); } }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } catch (final HibernateException he) { he.printStackTrace();
	 * EmployerDAOImpl.LOGGER.debug(he, he.getMessage()); } finally {
	 * 
	 * EmployerDAOImpl.LOGGER.exit(); } return appliedjoblist;
	 * 
	 * }
	 */
	@Override
	public List<AppliedJobBO> reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException, SerialException, SQLException {
		// List<AppliedJobVO> appliedjobvolist = new ArrayList<AppliedJobVO>();
		EmployerDAOImpl.LOGGER.entry();
		final List<AppliedJobBO> appliedlist = new ArrayList<AppliedJobBO>();
		final List<AppliedJobBO> appliedjoblist = new ArrayList<AppliedJobBO>();

		// List<JobseekerProfileVO> jobseekerProfileVOList1 = new
		// ArrayList<JobseekerProfileVO>();

		try {

			int sno = 1;
			final Criteria cr = getSession().createCriteria(AppliedJobVO.class);
			if (null != appliedJobBO.getEmployerLogin()) {
				cr.add(Restrictions.eq("employerLogin.id", appliedJobBO
						.getEmployerLogin().getId()));

			}
			if (appliedJobBO.getId() != 0) {

				cr.add(Restrictions.eq("applicationid", appliedJobBO.getId()));
			}
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isShortlisted", false));
			List<AppliedJobVO> appliedjobvolist = cr.list();
			for (final AppliedJobVO appliedJobVO : appliedjobvolist) {
				this.appliedjobBO = new AppliedJobBO();
				this.appliedjobBO.setCreated(appliedJobVO.getCreated());
				this.appliedjobBO.setJobTitle(appliedJobVO.getJobTitle());
				this.appliedjobBO.setCompanyName(appliedJobVO.getCompanyName());
				this.appliedjobBO.setId(appliedJobVO.getApplicationid());
				this.appliedjobBO.setIsShortlisted(appliedJobVO
						.getIsShortlisted());
				final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
				this.id = appliedJobVO.getJobseekerLogin().getId();
				jobSeekerLoginBO
						.setId(appliedJobVO.getJobseekerLogin().getId());
				this.appliedjobBO.setJobseekerLogin(jobSeekerLoginBO);
				this.appliedjobBO.setMinExp(appliedJobVO.getMinExp());
				this.appliedjobBO.setMaxExp(appliedJobVO.getMaxExp());
				this.appliedjobBO.setMinSalary(appliedJobVO.getMinSalary());
				this.appliedjobBO.setMaxSalary(appliedJobVO.getMaxSalary());
				appliedlist.add(this.appliedjobBO);
			}

			final Criteria cr2 = getSession().createCriteria(
					JobseekerProfileVO.class);
			cr2.add(Restrictions.eq("isActive", true));

			List<JobseekerProfileVO> jobseekerProfileVOList1 = cr2.list();

			for (final AppliedJobBO appliedBO : appliedlist) {
				for (final JobseekerProfileVO jobseekerProfileBO : jobseekerProfileVOList1) {

					if (jobseekerProfileBO.getJobSeekerLogin().getId() == appliedBO
							.getJobseekerLogin().getId()) {

						this.appliedjobBO = new AppliedJobBO();
						this.appliedjobBO.setSno(sno++);
						this.appliedjobBO.setDateandYear(format
								.format(appliedBO.getCreated()));
						this.appliedjobBO.setMinExp(appliedBO.getMinExp());
						this.appliedjobBO.setMaxExp(appliedBO.getMaxExp());
						this.appliedjobBO
								.setMinSalary(appliedBO.getMinSalary());
						this.appliedjobBO
								.setMaxSalary(appliedBO.getMaxSalary());
						this.appliedjobBO.setFirstName(jobseekerProfileBO
								.getFirstName());
						this.appliedjobBO
								.setProfiledescription(jobseekerProfileBO
										.getProfiledescription());
						this.appliedjobBO.setResumeTitle(jobseekerProfileBO
								.getResumeTitle());
						if (null != jobseekerProfileBO.getUploadResume()) {
							this.appliedjobBO
									.setUploadResume(jobseekerProfileBO
											.getUploadResume().getBytes(
													1,
													(int) jobseekerProfileBO
															.getUploadResume()
															.length()));
						}
						this.appliedjobBO.setProfileImage(jobseekerProfileBO
								.getProfileImage().getBytes(
										1,
										(int) jobseekerProfileBO
												.getProfileImage().length()));
						this.appliedjobBO
								.setPreferredLocation(jobseekerProfileBO
										.getPreferredLocation());
						this.appliedjobBO.setEmailId(jobseekerProfileBO
								.getEmailId());

						if (null != jobseekerProfileBO.getExpectedCtc()) {
							this.appliedjobBO.setExpectedCtc(jobseekerProfileBO
									.getExpectedCtc());
						}

						this.appliedjobBO.setPhone(jobseekerProfileBO
								.getPhone());
						this.appliedjobBO
								.setExperienceInYear(jobseekerProfileBO
										.getExperienceInYear());
						this.appliedjobBO
								.setExperienceInMonth(jobseekerProfileBO
										.getExperienceInMonth());
						this.appliedjobBO
								.setPreferredIndustry(jobseekerProfileBO
										.getPreferredIndustry());
						this.appliedjobBO.setCurrentIndustry(jobseekerProfileBO
								.getCurrentIndustry());
						this.appliedjobBO.setKeySkills(jobseekerProfileBO
								.getKeySkills());

						this.appliedjobBO.setCreated(appliedBO.getCreated());
						this.appliedjobBO.setJobTitle(appliedBO.getJobTitle());
						this.appliedjobBO.setCompanyName(appliedBO
								.getCompanyName());
						this.appliedjobBO.setId(appliedBO.getId());
						this.appliedjobBO.setIsShortlisted(appliedBO
								.getIsShortlisted());
						this.appliedjobBO.setJobseekerLogin(appliedBO
								.getJobseekerLogin());
						this.appliedjobBO.setLoginId(jobseekerProfileBO
								.getJobSeekerLogin().getId());
						appliedjoblist.add(this.appliedjobBO);

					}

				}

			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return appliedjoblist;

	}

	@Override
	public List<SaveCandidateBO> reteriveCandidate(
			SaveCandidateBO savecandidateBO) throws MyJobKartException {

		EmployerDAOImpl.LOGGER.entry();
		// List<SaveCandidateVO> savecandidateVolist = new
		// ArrayList<SaveCandidateVO>();
		final List<SaveCandidateBO> saveCandidate = new ArrayList<SaveCandidateBO>();

		try {
			long sno = 1;
			// session = getSession();
			final Criteria cr = getSession().createCriteria(
					SaveCandidateVO.class);
			if (null != savecandidateBO.getEmployerLoginBO()) {
				cr.add(Restrictions.eq("isDelete", true));
				cr.createCriteria("employerLoginVO").add(
						Restrictions.eq("id", savecandidateBO
								.getEmployerLoginBO().getId()));
				cr.addOrder(Order.desc("created"));
			}
			if (savecandidateBO.getId() != 0) {
				cr.add(Restrictions.eq("savecandidateid",
						savecandidateBO.getId()));
			}
			if (null != savecandidateBO.getJobseekerProfileBO()
					&& 0 != savecandidateBO.getJobseekerProfileBO().getId()) {
				cr.add(Restrictions.eq("jobseekerProfileVO.profileId",
						savecandidateBO.getJobseekerProfileBO().getId()));

			}
			cr.add(Restrictions.eq("isDelete", true));
			cr.addOrder(Order.desc("created"));
			List<SaveCandidateVO> savecandidateVolist = cr.list();
			for (final SaveCandidateVO profileBO : savecandidateVolist) {
				this.saveCandidateBO = new SaveCandidateBO();
				this.saveCandidateBO.setSno(sno++);
				this.saveCandidateBO.setDateandYear(format.format(profileBO
						.getCreated()));
				this.saveCandidateBO.setCollege(profileBO.getCollege());
				this.saveCandidateBO.setCompanyName(profileBO.getCompanyName());
				this.saveCandidateBO.setCompanyType(profileBO.getCompanyType());
				this.saveCandidateBO.setCreatedBy(profileBO.getCreatedBy());
				this.saveCandidateBO.setCurrentIndustry(profileBO
						.getCurrentIndustry());
				this.saveCandidateBO.setCurrentSalary(profileBO
						.getCurrentSalary());
				this.saveCandidateBO.setDegree(profileBO.getDegree());
				this.saveCandidateBO.setDeleteBy(profileBO.getDeleteBy());
				this.saveCandidateBO.setDeletedDate(profileBO.getDeletedDate());
				this.saveCandidateBO.setDesignation(profileBO.getDesignation());
				this.saveCandidateBO.setDomainSkills(profileBO
						.getDomainSkills());

				this.saveCandidateBO.setEmailId(profileBO
						.getJobseekerProfileVO().getEmailId());
				this.saveCandidateBO.setExpectedCtc(profileBO.getExpectedCtc());
				this.saveCandidateBO.setExpEndDate(profileBO.getExpEndDate());
				this.saveCandidateBO.setExperienceInMonth(profileBO
						.getExperienceInMonth());
				this.saveCandidateBO.setExpStartDate(profileBO
						.getExpStartDate());
				this.saveCandidateBO.setFirstName(profileBO.getFirstName());
				this.saveCandidateBO.setFunction(profileBO.getFunction());
				this.saveCandidateBO.setGender(profileBO.getGender());
				this.saveCandidateBO.setGetPrivilege(profileBO
						.getGetPrivilege());
				this.saveCandidateBO.setGrade(profileBO.getGrade());

				saveCandidateBO.setProfileId(profileBO.getJobseekerProfileVO()
						.getprofileId());
				this.saveCandidateBO.setJobType(profileBO.getJobType());
				this.saveCandidateBO.setKeySkills(profileBO.getKeySkills());
				this.saveCandidateBO.setLanguagesKnown(profileBO
						.getLanguagesKnown());
				this.saveCandidateBO.setLastName(profileBO.getLastName());
				this.saveCandidateBO.setLocation(profileBO.getLocation());
				this.saveCandidateBO.setMaritalStatus(profileBO
						.getMaritalStatus());
				this.saveCandidateBO.setModifiedBy(profileBO.getModifiedBy());
				this.saveCandidateBO.setNationality(profileBO.getNationality());
				this.saveCandidateBO.setPhone(profileBO.getPhone());
				this.saveCandidateBO.setPreferredIndustry(profileBO
						.getPreferredIndustry());
				this.saveCandidateBO.setPreferredLocation(profileBO
						.getPreferredLocation());
				if (null != profileBO.getProfileImage()) {
					this.saveCandidateBO
							.setProfileImage(profileBO.getProfileImage()
									.getBytes(
											1,
											(int) profileBO.getProfileImage()
													.length()));
				}
				this.saveCandidateBO.setResumeTitle(profileBO.getResumeTitle());
				this.saveCandidateBO.setSpecialisation(profileBO
						.getSpecialisation());
				if (null != profileBO.getUploadResume()
						&& profileBO.getUploadResume().length() > 0) {
					this.saveCandidateBO
							.setUploadResume(profileBO.getUploadResume()
									.getBytes(
											1,
											(int) profileBO.getUploadResume()
													.length()));
				}
				this.saveCandidateBO.setYearOfPassing(profileBO
						.getYearOfPassing());
				this.saveCandidateBO.setCurrentSalaryPer(profileBO
						.getCurrentSalaryPer());
				this.saveCandidateBO.setExpectedCtcPer(profileBO
						.getExpectedCtcPer());
				this.saveCandidateBO.setExpYearandMonth(profileBO
						.getExperienceInYear()
						+ "-"
						+ profileBO.getExperienceInMonth());
				this.saveCandidateBO.setExperienceInYear(profileBO
						.getExperienceInYear());
				this.saveCandidateBO.setProfiledescription(profileBO
						.getProfiledescription());
				this.saveCandidateBO.setIsDelete(profileBO.getIsDelete());
				this.saveCandidateBO.setIsActive(profileBO.getIsActive());
				this.saveCandidateBO.setId(profileBO.getSavecandidateid());
				this.saveCandidateBO.setIsShortListed(profileBO
						.getIsShortListed());
				saveCandidate.add(this.saveCandidateBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return saveCandidate;

	}

	@Override
	public List<JobPostBO> retrieveJobPosts(JobPostVO jobpostVO)
			throws SerialException, SQLException {
		EmployerDAOImpl.LOGGER.entry();
		// List<JobPostVO> jobPostVOList = new ArrayList<JobPostVO>();
		final List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();
		JobPostBO jobPostBO;
		try {
			long count = 0;
			// session = getSession();
			final Criteria cr = getSession().createCriteria(JobPostVO.class);

			if (jobpostVO.getCreatedBy() != 0) {
				cr.createCriteria("employerLogin").add(
						Restrictions.eq("id", jobpostVO.getCreatedBy()));
				cr.add(Restrictions.eq("isDeleted", true));
				cr.addOrder(Order.desc("id"));

			}

			if (0 != jobpostVO.getJobId()) {
				cr.add(Restrictions.eq("jobId", jobpostVO.getJobId()));
				cr.add(Restrictions.eq("isDeleted", true));
				cr.addOrder(Order.desc("id"));
			}
			
			
			
			if (null != jobpostVO.getJobTitle()) {
				cr.add(Restrictions.ilike("jobTitle",
						"%" + jobpostVO.getJobTitle() + "%"));
						}
			/*
			 * else{
			 * 
			 * cr.add(Restrictions.eq("isDeleted", true)); List<JobPostVO>
			 * jobPostVOList = cr.list(); for (final JobPostVO jobPostVO :
			 * jobPostVOList) { jobPostBO = new JobPostBO(); EmployerLoginBO
			 * employerLoginBO = new EmployerLoginBO();
			 * employerLoginBO.setId(jobPostVO.getEmployerLogin().getId());
			 * jobPostBO.setEmployerLogin(employerLoginBO);
			 * jobPostBO.setAddress(jobPostVO.getAddress());
			 * jobPostBO.setCompanyName(jobPostVO.getCompanyName());
			 * jobPostBO.setContactNo(jobPostVO.getContactNo()); jobPostBO
			 * .setContactPerson(jobPostVO.getContactPerson());
			 * jobPostBO.setCreatedBy(jobPostBO.getCreatedBy());
			 * jobPostBO.setCurrencyType(jobPostVO.getCurrencyType());
			 * jobPostBO.setFunctionArea(jobPostVO.getFunctionArea());
			 * jobPostBO.setIndustryType(jobPostVO.getIndustryType());
			 * jobPostBO.setJobDescription(jobPostVO .getJobDescription());
			 * jobPostBO.setJobLocation(jobPostVO.getJobLocation());
			 * jobPostBO.setJobResponse(jobPostVO.getJobResponse());
			 * jobPostBO.setJobTitle(jobPostVO.getJobTitle());
			 * jobPostBO.setKeywords(jobPostVO.getKeywords());
			 * jobPostBO.setMaxExp(jobPostVO.getMaxExp());
			 * jobPostBO.setModifiedBy(jobPostVO.getModifiedBy());
			 * jobPostBO.setMaxSalary(jobPostVO.getMaxSalary());
			 * jobPostBO.setMinExp(jobPostVO.getMinExp());
			 * jobPostBO.setMinSalary(jobPostVO.getMinSalary());
			 * jobPostBO.setNoVacancies(jobPostVO.getNoVacancies());
			 * jobPostBO.setVersion(jobPostVO.getVersion());
			 * jobPostBO.setStartDate(jobPostVO.getStartDate());
			 * jobPostBO.setEndDate(jobPostVO.getEndDate());
			 * jobPostBO.setOtherSalaryDetails(jobPostVO
			 * .getOtherSalaryDetails()); jobPostBO.setPgQualification(jobPostVO
			 * .getPgQualification()); jobPostBO.setUgQualification(jobPostVO
			 * .getUgQualification());
			 * 
			 * jobPostBO.setCreated(jobPostVO.getCreated());
			 * jobPostBO.setModified(jobPostVO.getModified());
			 * jobPostBO.setExperiance(jobPostVO.getMinExp() + "-" +
			 * jobPostVO.getMaxExp());
			 * jobPostBO.setPostedBy(jobPostVO.getPostedBy());
			 * jobPostBO.setId(jobPostVO.getJobId());
			 * jobPostBO.setIsActive(jobPostVO.getIsActive()); jobPostBO
			 * .setEmpId(jobPostVO.getEmployerLogin().getId());
			 * 
			 * jobPostBO.setSalary(jobPostVO.getMinSalary() + "-" +
			 * jobPostVO.getMaxSalary());
			 * jobPostBO.setCompanyId(jobPostVO.getCompanyId()); count = count +
			 * 1; jobPostBO.setSNo(count); EmployerProfileBO employerProfileBO =
			 * new EmployerProfileBO();
			 * employerProfileBO.setId(jobPostVO.getEmployerProfileVO
			 * ().getProfileId());
			 * jobPostBO.setEmployerRegistion(employerProfileBO);
			 * jobPostBOList.add(jobPostBO); }
			 * 
			 * }
			 */
			cr.add(Restrictions.eq("isDeleted", true));
			List<JobPostVO> jobPostVOList = cr.list();
			if (null != jobPostVOList && 0 != jobPostVOList.size()) {
				for (final JobPostVO jobPostVO : jobPostVOList) {
					jobPostBO = new JobPostBO();
					EmployerLoginBO employerLoginBO = new EmployerLoginBO();
					employerLoginBO.setId(jobPostVO.getEmployerLogin().getId());
					jobPostBO.setEmployerLogin(employerLoginBO);

					// Check the post job details by employerLoginId
					if (jobpostVO.getCreatedBy() == employerLoginBO.getId()
							|| jobPostVO.getJobId() == jobpostVO.getJobId()) {

						jobPostBO.setAddress(jobPostVO.getAddress());
						jobPostBO.setCompanyName(jobPostVO.getCompanyName());
						jobPostBO.setContactNo(jobPostVO.getContactNo());
						jobPostBO
								.setContactPerson(jobPostVO.getContactPerson());
						jobPostBO.setCreatedBy(jobPostBO.getCreatedBy());
						jobPostBO.setCurrencyType(jobPostVO.getCurrencyType());
						jobPostBO.setFunctionArea(jobPostVO.getFunctionArea());
						jobPostBO.setIndustryType(jobPostVO.getIndustryType());
						jobPostBO.setJobDescription(jobPostVO
								.getJobDescription());
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
						jobPostBO.setVersion(jobPostVO.getVersion());
						jobPostBO.setStartDate(jobPostVO.getStartDate());
						jobPostBO.setEndDate(jobPostVO.getEndDate());
						jobPostBO.setOtherSalaryDetails(jobPostVO
								.getOtherSalaryDetails());
						jobPostBO.setPgQualification(jobPostVO
								.getPgQualification());
						jobPostBO.setUgQualification(jobPostVO
								.getUgQualification());

						jobPostBO.setCreated(jobPostVO.getCreated());
						jobPostBO.setModified(jobPostVO.getModified());
						jobPostBO.setExperiance(jobPostVO.getMinExp() + "-"
								+ jobPostVO.getMaxExp());
						jobPostBO.setPostedBy(jobPostVO.getPostedBy());
						jobPostBO.setId(jobPostVO.getJobId());
						jobPostBO.setIsActive(jobPostVO.getIsActive());
						jobPostBO
								.setEmpId(jobPostVO.getEmployerLogin().getId());

						jobPostBO.setSalary(jobPostVO.getMinSalary() + "-"
								+ jobPostVO.getMaxSalary());
						jobPostBO.setCompanyId(jobPostVO.getCompanyId());
						count = count + 1;
						jobPostBO.setsNo(count);
						EmployerProfileBO employerProfileBO = new EmployerProfileBO();
						employerProfileBO.setId(jobPostVO
								.getEmployerProfileVO().getProfileId());
						jobPostBO.setEmployerRegistion(employerProfileBO);
						jobPostBOList.add(jobPostBO);
					}
				}
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}

		EmployerDAOImpl.LOGGER.exit();
		return jobPostBOList;
	}

	@Override
	public JobPostVO updateJobPost(JobPostVO postVO) throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			EmployerVO employerVO = new EmployerVO();
			EmployerActivityVO emActivityVO = new EmployerActivityVO();
			session.saveOrUpdate(postVO);

			if (postVO.getJobId() != 0) {
				emActivityVO.setCreated(postVO.getCreated());
				emActivityVO.setCreatedBy(postVO.getCreatedBy());
				emActivityVO.setModifiedBy(postVO.getModifiedBy());
				employerVO.setId(postVO.getEmployerProfileVO()
						.getEmployerRegistion().getId());
				emActivityVO.setEmployerVO(employerVO);
				emActivityVO
						.setActivityDate(format.format(postVO.getCreated()));
				emActivityVO.setStatus("Updated JobPost");
				emActivityVO.setJobPostVO(postVO);
				session.saveOrUpdate(emActivityVO);

			}

			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		EmployerDAOImpl.LOGGER.exit();
		return postVO;
	}

	@Override
	public EmployerActivityVO shortlistCandidateActivity(
			EmployerActivityVO emActivityVO) {
		EmployerDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			EmployerVO employerVO = new EmployerVO();
			session.saveOrUpdate(emActivityVO);

			if (emActivityVO.getId() != 0) {
				emActivityVO.getId();

			}

			getSession().flush();
		} catch (final HibernateException he) {
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}

		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		EmployerDAOImpl.LOGGER.exit();
		return emActivityVO;
	}

	@Override
	public List<EmployerBO> retrieveRegisteredList() throws MyJobKartException,
			SerialException, SQLException {
		final List<EmployerBO> employerRegisteredList = new ArrayList<EmployerBO>();
		// List<EmployerVO> employerVOList = new ArrayList<EmployerVO>();
		EmployerDAOImpl.LOGGER.entry();
		EmployerBO employerBO;
		int count = 0;
		try {
			// session = getSession();
			final Criteria cr = getSession().createCriteria(EmployerVO.class);
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isDeleted", true));
			List<EmployerVO> employerVOList = cr.list();
			for (final EmployerVO employerVO : employerVOList) {
				employerBO = new EmployerBO();
				employerBO.setId(employerVO.getId());
				employerBO.setEmailAddress(employerVO.getEmailAddress());
				employerBO.setCompanyName(employerVO.getCompanyName());
				employerBO.setFirstName(employerVO.getFirstName()
						.substring(0, 1).toUpperCase()
						+ employerVO.getFirstName().substring(1));
				employerBO.setIsActive(employerVO.getIsActive());
				if (employerBO.getIsActive()) {
					employerBO.setActive("Active");
				} else {
					employerBO.setActive("De-active");
				}
				employerBO.setCompanyType(employerVO.getCompanyType());
				employerBO.setCreated(employerVO.getCreated());
				employerBO.setCreatedBy(employerVO.getCreatedBy());
				employerBO.setModifiedBy(employerVO.getModifiedBy());
				employerBO.setContactNumber(employerVO.getContactNumber());
				employerBO.setIndustryType(employerVO.getIndustryType());
				employerBO.setLastName(employerVO.getLastName());
				if (null != employerVO.getCompanyLogo()) {
					employerBO
							.setCompanyLogo(employerVO.getCompanyLogo()
									.getBytes(
											1,
											(int) employerVO.getCompanyLogo()
													.length()));
				}
				employerBO.setMobileNumber(employerVO.getMobileNumber());
				employerBO.setConfirmEmailAddress(employerVO
						.getConfirmEmailAddress());
				employerBO.setTermsConditionsAgreed(employerVO
						.getTermsConditionsAgreed());
				employerBO.setWebSite(employerVO.getWebSite());
				employerBO.setPassword(employerVO.getPassword());
				employerBO.setConfirmPassword(employerVO.getConfirmPassword());
				employerBO.setIsDeleted(employerVO.getIsDeleted());
				count = count + 1;
				employerBO.setsNo(count);
				employerRegisteredList.add(employerBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return employerRegisteredList;
	}

	@Override
	public List<EmployerBO> retrieveRegisteredList(long employerRegisterId)
			throws MyJobKartException, SerialException, SQLException {
		final List<EmployerBO> employerRegisteredList = new ArrayList<EmployerBO>();
		// List<EmployerVO> employerVOList = new ArrayList<EmployerVO>();
		EmployerDAOImpl.LOGGER.entry();
		EmployerBO employerBO;
		try {
			// session = getSession();
			final Criteria cr = getSession().createCriteria(EmployerVO.class);
			cr.add(Restrictions.eq("id", employerRegisterId));
			cr.add(Restrictions.eq("isDeleted", true));
			List<EmployerVO> employerVOList = cr.list();
			for (final EmployerVO employerVO : employerVOList) {
				employerBO = new EmployerBO();
				employerBO.setId(employerVO.getId());
				employerBO.setEmailAddress(employerVO.getEmailAddress());
				employerBO.setCompanyName(employerVO.getCompanyName());
				employerBO.setFirstName(employerVO.getFirstName());
				employerBO.setIsActive(employerVO.getIsActive());
				if (employerBO.getIsActive() == true) {
					employerBO.setActive("Active");
				} else {
					employerBO.setActive("De-active");
				}
				employerBO.setCompanyType(employerVO.getCompanyType());
				employerBO.setCreated(employerVO.getCreated());
				employerBO.setCreatedBy(employerVO.getCreatedBy());
				employerBO.setModifiedBy(employerVO.getModifiedBy());
				employerBO.setContactNumber(employerVO.getContactNumber());
				employerBO.setIndustryType(employerVO.getIndustryType());
				employerBO.setLastName(employerVO.getLastName());
				if (null != employerVO.getCompanyLogo()) {
					employerBO
							.setCompanyLogo(employerVO.getCompanyLogo()
									.getBytes(
											1,
											(int) employerVO.getCompanyLogo()
													.length()));
				}
				employerBO.setMobileNumber(employerVO.getMobileNumber());
				employerBO.setConfirmEmailAddress(employerVO
						.getConfirmEmailAddress());
				employerBO.setTermsConditionsAgreed(employerVO
						.getTermsConditionsAgreed());
				employerBO.setWebSite(employerVO.getWebSite());
				employerBO.setPassword(employerVO.getPassword());
				employerBO.setConfirmPassword(employerVO.getConfirmPassword());
				employerBO.setVersion(employerVO.getVersion());
				employerBO.setIsDeleted(employerVO.getIsDeleted());
				employerRegisteredList.add(employerBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return employerRegisteredList;
	}

	@Override
	public List<SaveCandidateBO> employeerSaveResume(
			SaveCandidateBO saveCandidateBO) throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		// List<SaveCandidateVO> saveCandidateList = new
		// ArrayList<SaveCandidateVO>();
		final List<SaveCandidateBO> saveCandidateBOList = new ArrayList<SaveCandidateBO>();
		Criteria criteria;
		try {
			final long id = saveCandidateBO.getLoginId();
			// session = getSession();
			criteria = getSession().createCriteria(SaveCandidateVO.class);
			criteria.createCriteria("employerLoginVO").add(
					Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isDelete", true));
			List<SaveCandidateVO> saveCandidateList = criteria.list();

			if (null != saveCandidateList && saveCandidateList.size() > 0) {

				for (final SaveCandidateVO saveCandidateVO : saveCandidateList) {
					final SaveCandidateBO candidateBO = new SaveCandidateBO();
					candidateBO.setCollege(saveCandidateVO.getCollege());
					candidateBO
							.setCompanyName(saveCandidateVO.getCompanyName());
					candidateBO.setProfileId(saveCandidateVO
							.getJobseekerProfileVO().getprofileId());
					saveCandidateBOList.add(candidateBO);
				}
			}
		} catch (final HibernateException he) {

			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		}
		return saveCandidateBOList;
	}

	@Override
	public EmployerVO profileStatus1(EmployerVO employerLoginVO) {
		EmployerDAOImpl.LOGGER.entry();
		final String changeLoginQuery = "UPDATE EmployerVO E set E.isActive = :IsActive WHERE E.id= :Id";
		try {
			// session = getSession();
			final Query query = getSession().createQuery(changeLoginQuery);
			query.setParameter("IsActive", employerLoginVO.getIsActive());
			query.setParameter("Id", employerLoginVO.getId());
			final int result = query.executeUpdate();
			if (0 != result) {
				return employerLoginVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return null;
	}

	@Override
	public void saveProfileView(ViewJobseekerBO viewBo) {
		try {

			Criteria cr;
			Session session = getSession();

			cr = getSession().createCriteria(JobseekerProfileVO.class);
			cr.add(Restrictions.eq("profileId", viewBo.getjId()));

			final List<JobseekerProfileVO> jbId = cr.list();

			for (final JobseekerProfileVO vo : jbId) {

				final long id = vo.getJobSeekerLogin().getId();
				final ViewJobSeekerVO vJobSeeker = new ViewJobSeekerVO();

				final EmployerVO ebo = new EmployerVO();
				ebo.setId(viewBo.getEmpId());
				vJobSeeker.setEmployerRegistration(ebo);

				final JobseekerLoginVO jbo = new JobseekerLoginVO();
				jbo.setId(id);
				vJobSeeker.setJobseekerLoginVO(jbo);

				final JobseekerProfileVO jpBo = new JobseekerProfileVO();
				jpBo.setprofileId(viewBo.getjId());
				vJobSeeker.setJobseekerProfileVO(jpBo);

				vJobSeeker.setDays(1);

				Criteria cr1;

				cr1 = session.createCriteria(ViewJobSeekerVO.class);
				cr1.createCriteria("jobseekerLoginVO").add(
						Restrictions.eq("id", id));

				cr1.createCriteria("employerRegistration").add(
						Restrictions.eq("id", viewBo.getEmpId()));

				cr1.createCriteria("jobseekerProfileVO").add(
						Restrictions.eq("profileId", viewBo.getjId()));

				final List<ViewJobSeekerVO> eqList = cr1.list();
				long historyId = 0;
				int beforetotalview = 0;
				int afterTotalView;
				for (final ViewJobSeekerVO vjs : eqList) {
					historyId = vjs.getHistoryId();
					beforetotalview = vjs.getDays();

				}
				if (eqList.isEmpty()) {
					session.saveOrUpdate(vJobSeeker);
					if (vJobSeeker.getHistoryId() != 0) {

					}
					session.flush();
				} else {

					afterTotalView = beforetotalview + 1;
					final String updateQuery = "UPDATE ViewJobSeekerVO C set C.days = :afterTotalView WHERE C.historyId = :historyId";

					session = getSession();
					final Query query = session.createQuery(updateQuery);
					query.setParameter("afterTotalView", afterTotalView);
					query.setParameter("historyId", historyId);
					query.executeUpdate();
				}

			}

		} catch (final HibernateException he) {
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<EmployerBO> renewelRegisteredList() throws MyJobKartException,
			SerialException, SQLException {
		final List<EmployerBO> employerRegisteredList = new ArrayList<EmployerBO>();
		// List<EmployerVO> employerVOList = new ArrayList<EmployerVO>();
		// List<EmployerProductServiceVO> employerProductService = new
		// ArrayList<EmployerProductServiceVO>();
		EmployerDAOImpl.LOGGER.entry();
		EmployerBO employerBO;
		final Calendar cal1 = new GregorianCalendar();
		final Calendar cal2 = new GregorianCalendar();
		int count = 0;
		try {
			// session = getSession();
			final Criteria cr = getSession().createCriteria(EmployerVO.class);
			cr.addOrder(Order.desc("created"));
			cr.add(Restrictions.eq("isDeleted", true));
			List<EmployerVO> employerVOList = cr.list();

			final Criteria criteria = getSession().createCriteria(
					EmployerProductServiceVO.class);
			List<EmployerProductServiceVO> employerProductService = criteria
					.list();

			final Date date = new Date();

			new SimpleDateFormat("ddMMyyyy");

			for (final EmployerVO employerVO : employerVOList) {

				for (final EmployerProductServiceVO employeerProduct : employerProductService) {
					if (employeerProduct.getEmployer().getId() == employerVO
							.getId()) {
						employerBO = new EmployerBO();
						final Date grecePeriod = employeerProduct
								.getGracePeriod();

						cal1.setTime(grecePeriod);
						cal2.setTime(date);
						employerBO.setEdate(grecePeriod);
						final Date ed = cal1.getTime();
						final Date sd = cal2.getTime();
						final long diff = ed.getTime() - sd.getTime();
						final int totalDays = (int) (diff / (1000 * 24 * 60 * 60));

						employerBO.setTotalDate(totalDays);
						employerBO.setId(employerVO.getId());
						employerBO
								.setEmailAddress(employerVO.getEmailAddress());
						employerBO.setCompanyName(employerVO.getCompanyName());
						employerBO.setFirstName(employerVO.getFirstName());
						employerBO.setIsActive(employerVO.getIsActive());
						if (employerBO.getIsActive() == true) {
							employerBO.setActive("Active");
						} else {
							employerBO.setActive("De-active");
						}
						employerBO.setCompanyType(employerVO.getCompanyType());
						employerBO.setCreated(employerVO.getCreated());
						employerBO.setCreatedBy(employerVO.getCreatedBy());
						employerBO.setModifiedBy(employerVO.getModifiedBy());
						employerBO.setContactNumber(employerVO
								.getContactNumber());
						employerBO
								.setIndustryType(employerVO.getIndustryType());
						employerBO.setLastName(employerVO.getLastName());
						employerBO.setCompanyLogo(employerVO.getCompanyLogo()
								.getBytes(
										1,
										(int) employerVO.getCompanyLogo()
												.length()));
						employerBO
								.setMobileNumber(employerVO.getMobileNumber());
						employerBO.setConfirmEmailAddress(employerVO
								.getConfirmEmailAddress());
						employerBO.setTermsConditionsAgreed(employerVO
								.getTermsConditionsAgreed());
						employerBO.setWebSite(employerVO.getWebSite());
						employerBO.setPassword(employerVO.getPassword());
						employerBO.setConfirmPassword(employerVO
								.getConfirmPassword());
						employerBO.setVersion(employerVO.getVersion());
						employerBO.setIsDeleted(employerVO.getIsDeleted());
						count = count + 1;
						employerBO.setsNo(count);
						employerRegisteredList.add(employerBO);

					}
				}
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return employerRegisteredList;
	}

	@Override
	public long addPayments(EntrolledSeviceVO entrolledSevice) {
		long entollId = 0;
		Session session = getSession();
		entollId = (Long) session.save(entrolledSevice);

		return entollId;
	}

	@Override
	public List<SaveCandidateBO> getJobseekerId(SaveCandidateBO candidateBO) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		final List<SaveCandidateBO> list = new ArrayList<SaveCandidateBO>();
		// List<SaveCandidateVO> list1 = new ArrayList<SaveCandidateVO>();
		// session = getSession();
		final Criteria criteria = getSession().createCriteria(
				SaveCandidateVO.class);
		criteria.createCriteria("jobseekerProfileVO").add(
				Restrictions.eq("profileId", candidateBO.getProfileId()));
		List<SaveCandidateVO> jobseekerlist = criteria.list();
		for (final SaveCandidateVO candidateVO : jobseekerlist) {
			final SaveCandidateBO bo = new SaveCandidateBO();
			bo.setProfileId(candidateVO.getJobseekerProfileVO().getprofileId());
			bo.setEmpId(candidateVO.getEmployerLoginVO().getId());
			list.add(bo);
		}

		return list;
	}

	@Override
	public List<JobPostBO> getShortlistId(JobPostBO candidate) {
		// TODO Auto-generated method stub
		final List<JobPostBO> list = new ArrayList<JobPostBO>();
		// List<JobPostVO> list1 = new ArrayList<JobPostVO>();
		// session = getSession();
		final Criteria criteria = getSession().createCriteria(JobPostVO.class);
		criteria.createCriteria("employerLogin").add(
				Restrictions.eq("id", candidate.getEmpId()));
		List<JobPostVO> shortlistList = criteria.list();
		for (final JobPostVO candidateVO : shortlistList) {
			if (shortlistList.size() != 0) {
				final JobPostBO bo2 = new JobPostBO();
				bo2.setJobId(candidateVO.getJobId());
				list.add(bo2);

			}

		}

		return list;
	}

	@Override
	public List<JobPostBO> getJobposting(JobPostBO jobPostBO) {
		// TODO Auto-generated method stub
		final Criteria criteria1 = getSession().createCriteria(JobPostVO.class);
		Restrictions.eq("jobId", jobPostBO.getJobId());
		final JobPostBO bo = new JobPostBO();
		final List<JobPostBO> candidateVO = new ArrayList<JobPostBO>();
		final List<JobPostVO> candidateVO2 = criteria1.list();
		for (final JobPostVO listVO : candidateVO2) {
			bo.setJobTitle(listVO.getJobTitle());
			bo.setContactPerson(listVO.getContactPerson());
			bo.setJobDescription(listVO.getJobDescription());
			bo.setCompanyName(listVO.getCompanyName());
			bo.setEmailId(listVO.getEmployerLogin().getEmailAddress());
			candidateVO.add(bo);
		}
		return candidateVO;

	}

	@Override
	public long saveBannerPost(BannerPostVO bannerPostVO) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		long profileId = 0;
		try {
			profileId = (Long) getSession().save(bannerPostVO);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return profileId;
	}

	@Override
	public List<BannerPostBO> getBannerList(String fileName)
			throws SerialException, SQLException {
		// TODO Auto-generated method stub
		final List<BannerPostBO> bannerPost = new ArrayList<BannerPostBO>();
		// List<BannerPostVO> bannerPostVO = new ArrayList<BannerPostVO>();
		final Criteria criteria = getSession().createCriteria(
				BannerPostVO.class).add(Restrictions.eq("isDelete", true));
		criteria.add(Restrictions.eq("postPage", fileName));
		List<BannerPostVO> bannerPostVO = criteria.list();
		for (final BannerPostVO postVO : bannerPostVO) {
			final BannerPostBO postBO = new BannerPostBO();
			postBO.setBannerId(postVO.getBannerId());
			postBO.setBannerImage(postVO.getBannerImage().getBytes(1,
					(int) postVO.getBannerImage().length()));
			postBO.setPostPage(postVO.getPostPage());
			bannerPost.add(postBO);
		}
		return bannerPost;
	}

	@Override
	public List<BannerPostBO> getBannerList(long id) throws SerialException,
			SQLException {
		// TODO Auto-generated method stub
		final Calendar cal1 = new GregorianCalendar();
		final Calendar cal2 = new GregorianCalendar();

		final List<BannerPostBO> bannerPost = new ArrayList<BannerPostBO>();
		// List<BannerPostVO> bannerPostVO = new ArrayList<BannerPostVO>();
		final Criteria criteria = getSession().createCriteria(
				BannerPostVO.class).add(Restrictions.eq("isDelete", true));
		criteria.createCriteria("employerRegister").add(
				Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("isActive", true));
		criteria.addOrder(Order.desc("bannerId"));
		List<BannerPostVO> bannerPostVO = criteria.list();
		for (final BannerPostVO postVO : bannerPostVO) {
			BannerPostBO postBO = new BannerPostBO();
			postVO.getGracePeriod();
			postVO.getCreated();
			cal1.setTime(postVO.getGracePeriod());
			// cal2.setTime(postVO.getCreated());
			// cal2.setTime(new Date());
			final Date ed = cal1.getTime();
			final Date sd = cal2.getTime();
			final long diff = ed.getTime() - sd.getTime();
			final int totalDays = (int) (diff / (1000 * 24 * 60 * 60));

			if (totalDays > 0) {
				postBO.setTotalDays(totalDays);
			} else {
				postBO.setTotalDays(0);
			}

			postBO.setBannerName(postVO.getBannerName());
			postBO.setBannerId(postVO.getBannerId());
			postBO.setBannerImage(postVO.getBannerImage().getBytes(1,
					(int) postVO.getBannerImage().length()));
			postBO.setPostPage(postVO.getPostPage());
			postBO.setTotalcost(postVO.getTotalcost());
			postBO.setVersion(postVO.getVersion());
			bannerPost.add(postBO);
		}
		return bannerPost;
	}

	@Override
	public List<BannerPostBO> getBannarList() throws SerialException,
			SQLException {
		// TODO Auto-generated method stub
		final Calendar cal1 = new GregorianCalendar();
		final Calendar cal2 = new GregorianCalendar();
		final List<BannerPostBO> bannerPost = new ArrayList<BannerPostBO>();
		// List<BannerPostVO> bannerPostVO = new ArrayList<BannerPostVO>();
		final Criteria criteria = getSession().createCriteria(
				BannerPostVO.class).add(Restrictions.eq("isDelete", true));
		List<BannerPostVO> bannerPostVO = criteria.list();
		for (final BannerPostVO postVO : bannerPostVO) {
			cal1.setTime(postVO.getGracePeriod());
			cal2.setTime(postVO.getCreated());
			final Date ed = cal1.getTime();
			final Date sd = cal2.getTime();
			final long diff = ed.getTime() - sd.getTime();
			final int totalDays = (int) (diff / (1000 * 24 * 60 * 60));
			final BannerPostBO postBO = new BannerPostBO();
			postBO.setTotalDays(totalDays);
			postBO.setBannerName(postVO.getBannerName());
			postBO.setBannerId(postVO.getBannerId());
			postBO.setBannerImage(postVO.getBannerImage().getBytes(1,
					(int) postVO.getBannerImage().length()));
			postBO.setPostPage(postVO.getPostPage());
			postBO.setTotalcost(postVO.getTotalcost());
			postBO.setVersion(postVO.getVersion());
			bannerPost.add(postBO);
		}
		return bannerPost;
	}

	@Override
	public List<EmployerBO> employerRenewalAlert(String email)
			throws SerialException, SQLException {
		// TODO Auto-generated method stub
		final List<EmployerBO> employerRegisteredList = new ArrayList<EmployerBO>();
		// List<EmployerVO> employerVOList = new ArrayList<EmployerVO>();
		// List<EmployerProductServiceVO> employerProductService = new
		// ArrayList<EmployerProductServiceVO>();
		EmployerDAOImpl.LOGGER.entry();
		EmployerBO employerBO;
		final Calendar cal1 = new GregorianCalendar();
		final Calendar cal2 = new GregorianCalendar();
		try {
			final Criteria cr = getSession().createCriteria(EmployerVO.class);
			cr.add(Restrictions.eq("emailAddress", email));
			List<EmployerVO> employerVOList = cr.list();

			final Criteria criteria = getSession().createCriteria(
					EmployerProductServiceVO.class);
			List<EmployerProductServiceVO> employerProductService = criteria
					.list();

			final Date date = new Date();

			new SimpleDateFormat("ddMMyyyy");
			long count = 0;
			for (final EmployerVO employerVO : employerVOList) {

				for (final EmployerProductServiceVO employeerProduct : employerProductService) {
					if (employeerProduct.getEmployer().getId() == employerVO
							.getId()) {
						employerBO = new EmployerBO();
						final Date grecePeriod = employeerProduct
								.getGracePeriod();

						cal1.setTime(grecePeriod);
						cal2.setTime(date);
						employerBO.setEdate(grecePeriod);
						final Date ed = cal1.getTime();
						final Date sd = cal2.getTime();
						final long diff = ed.getTime() - sd.getTime();
						final int totalDays = (int) (diff / (1000 * 24 * 60 * 60));

						if (totalDays > 0) {
							employerBO.setTotalDate(totalDays);
						} else {
							employerBO.setTotalDate(0);
						}

						employerBO.setId(employerVO.getId());
						employerBO
								.setEmailAddress(employerVO.getEmailAddress());
						employerBO.setCompanyName(employerVO.getCompanyName());
						employerBO.setFirstName(employerVO.getFirstName());
						employerBO.setIsActive(employerVO.getIsActive());
						if (employerBO.getIsActive() == true) {
							employerBO.setActive("Active");
						} else {
							employerBO.setActive("De-active");
						}
						employerBO.setCompanyType(employerVO.getCompanyType());
						employerBO.setCreated(employerVO.getCreated());
						employerBO.setCreatedBy(employerVO.getCreatedBy());
						employerBO.setModifiedBy(employerVO.getModifiedBy());
						employerBO.setContactNumber(employerVO
								.getContactNumber());
						employerBO
								.setIndustryType(employerVO.getIndustryType());
						employerBO.setLastName(employerVO.getLastName());
						employerBO.setCompanyLogo(employerVO.getCompanyLogo()
								.getBytes(
										1,
										(int) employerVO.getCompanyLogo()
												.length()));
						employerBO
								.setMobileNumber(employerVO.getMobileNumber());
						employerBO.setConfirmEmailAddress(employerVO
								.getConfirmEmailAddress());
						employerBO.setTermsConditionsAgreed(employerVO
								.getTermsConditionsAgreed());
						employerBO.setWebSite(employerVO.getWebSite());
						employerBO.setPassword(employerVO.getPassword());
						employerBO.setConfirmPassword(employerVO
								.getConfirmPassword());
						employerBO.setVersion(employerVO.getVersion());
						employerBO.setIsDeleted(employerVO.getIsDeleted());
						count = count + 1;
						employerBO.setsNo(count);
						employerRegisteredList.add(employerBO);

					}
				}
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return employerRegisteredList;
	}

	@Override
	public int deleteBannerList(BannerPostVO jobPostVO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE BannerPostVO S set"
				+ " S.isDelete = :isDelete" + " WHERE S.bannerId = :bannerId";

		try {
			// session = getSession();
			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", jobPostVO.getIsDelete());
			// query.setParameter("deletedDate", jobPostVO.getDeletedDate());
			query.setParameter("bannerId", jobPostVO.getBannerId());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	@Override
	public long bannerName(BannerPostBO bannerName) {

		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		long result = 0;
		// List<BannerPostVO> bannerPostVOs = new ArrayList<BannerPostVO>();
		try {
			final Criteria criteria = getSession().createCriteria(
					BannerPostVO.class);
			criteria.add(Restrictions.eq("bannerName",
					bannerName.getBannerName()));
			criteria.add(Restrictions.eq("postPage", bannerName.getPostPage()));
			List<BannerPostVO> bannerPostVOs = criteria.list();
			if (bannerPostVOs.size() > 0) {
				result = 1;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}

		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;

	}

	@Override
	public BannerPostVO updateBanner(BannerPostVO bannerPostVO)
			throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		try {
			getSession().saveOrUpdate(bannerPostVO);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return bannerPostVO;

	}

	@Override
	public List<PaymentBO> productsEnrolledList(long loginId) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();

		// List<EntrolledSeviceVO> enrolledServiceList = new
		// ArrayList<EntrolledSeviceVO>();
		final List<PaymentBO> paymentList = new ArrayList<PaymentBO>();
		try {
			final Criteria criteria = getSession().createCriteria(
					EntrolledSeviceVO.class);
			criteria.add(Restrictions.eq("payId", loginId));
			criteria.add(Restrictions.eq("isDeleted", true));
			List<EntrolledSeviceVO> enrolledServiceList = criteria.list();
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

		}
		return paymentList;
	}

	@Override
	public int deleteEmployerEnrolledDetails(EntrolledSeviceVO saveJobVO) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		int result = 0;
		final String deleteQuery = "UPDATE EntrolledSeviceVO  set"
				+ " isDeleted =:Deleted," + "deletedDate =:deletedDate"
				+ " WHERE entrolledid =:id";
		try {
			// session = getSession();
			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("Deleted", saveJobVO.getIsDeleted());
			query.setParameter("deletedDate", saveJobVO.getDeletedDate());
			query.setParameter("id", saveJobVO.getEntrolledid());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}

		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	@Override
	public List<PaymentBO> employerLastMonthEnrolledList(long registerId) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		final PaymentBO paymentBO = new PaymentBO();
		// List<EntrolledSeviceVO> enrolledServiceList = new
		// ArrayList<EntrolledSeviceVO>();
		final List<PaymentBO> paymentList = new ArrayList<PaymentBO>();
		try {
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			final Date result = cal.getTime();
			// session = getSession();
			final Criteria criteria = getSession().createCriteria(
					EntrolledSeviceVO.class);
			criteria.add(Restrictions.eq("payId", registerId));
			criteria.add(Restrictions.eq("isDeleted", true));
			criteria.add(Restrictions.eq("producType", "employeer"));
			criteria.add(Restrictions.between("created", result, new Date()));
			List<EntrolledSeviceVO> enrolledServiceList = criteria.list();
			for (final EntrolledSeviceVO entrolledSeviceVO : enrolledServiceList) {
				paymentBO.setPayId(entrolledSeviceVO.getPayId());
				paymentBO.setId(entrolledSeviceVO.getEntrolledid());
				paymentBO.setSelectProduct(entrolledSeviceVO.getProducType());
				paymentBO.setTotalcost(entrolledSeviceVO.getTotalcost());
				paymentBO.setCreated(entrolledSeviceVO.getValidFrom());
				paymentBO.setEndDate(entrolledSeviceVO.getValidEnd());
				paymentBO
						.setSelectProduct(entrolledSeviceVO.getSelectProduct());
				paymentBO.setProductType(entrolledSeviceVO.getProducType());
				paymentBO.setTotalMonth(entrolledSeviceVO.getTotalMonth());
				paymentList.add(paymentBO);

			}

		} catch (final Exception exception) {
			exception.printStackTrace();

		}
		return paymentList;
	}

	@Override
	public List<PaymentBO> employerPaymentHistory() {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		final PaymentBO payment = new PaymentBO();
		List<EntrolledSeviceVO> enrolledServiceList = new ArrayList<EntrolledSeviceVO>();
		// List<EmployerVO> paymentList1 = new ArrayList<EmployerVO>();
		final List<PaymentBO> paymentList = new ArrayList<PaymentBO>();
		try {
			final Criteria criteria = getSession().createCriteria(
					EntrolledSeviceVO.class);
			criteria.add(Restrictions.eq("isDeleted", true));
			criteria.add(Restrictions.eq("producType", "employeer"));
			enrolledServiceList = criteria.list();
			for (final EntrolledSeviceVO entrolledSeviceVO : enrolledServiceList) {
				this.paymentBO = new PaymentBO();
				this.paymentBO.setPayId(entrolledSeviceVO.getPayId());
				this.paymentBO.setId(entrolledSeviceVO.getEntrolledid());
				this.paymentBO.setSelectProduct(entrolledSeviceVO
						.getProducType());
				this.paymentBO.setTotalcost(entrolledSeviceVO.getTotalcost());
				this.paymentBO.setValidFrom(entrolledSeviceVO.getValidFrom());
				this.paymentBO.setEndDate(entrolledSeviceVO.getValidEnd());
				this.paymentBO.setSelectProduct(entrolledSeviceVO
						.getSelectProduct());
				this.paymentBO
						.setProductType(entrolledSeviceVO.getProducType());
				this.paymentBO.setTotalMonth(entrolledSeviceVO.getTotalMonth());
				final Criteria criteria2 = getSession().createCriteria(
						EmployerVO.class);
				criteria2.add(Restrictions.eq("id",
						entrolledSeviceVO.getPayId()));
				List<EmployerVO> paymentList1 = criteria2.list();
				for (final EmployerVO jobseekerVO : paymentList1) {
					if (jobseekerVO.getId() == entrolledSeviceVO.getPayId()) {
						payment.setName(jobseekerVO.getFirstName());
					}
				}
				this.paymentBO.setName(payment.getName());
				paymentList.add(this.paymentBO);
			}
		} catch (final Exception exception) {
			exception.printStackTrace();

		}
		return paymentList;
	}

	@Override
	public List<SaveCandidateBO> reteriveCandidate() {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		// List<SaveCandidateVO> savecandidateVolist = new
		// ArrayList<SaveCandidateVO>();
		final List<SaveCandidateBO> saveCandidate = new ArrayList<SaveCandidateBO>();

		try {
			// session = getSession();
			final Criteria cr = getSession().createCriteria(
					SaveCandidateVO.class);
			List<SaveCandidateVO> savecandidateVolist = cr.list();
			for (final SaveCandidateVO profileBO : savecandidateVolist) {
				this.saveCandidateBO = new SaveCandidateBO();
				this.saveCandidateBO.setCollege(profileBO.getCollege());
				this.saveCandidateBO.setCompanyName(profileBO.getCompanyName());
				this.saveCandidateBO.setCompanyType(profileBO.getCompanyType());
				this.saveCandidateBO.setCreatedBy(profileBO.getCreatedBy());
				this.saveCandidateBO.setCurrentIndustry(profileBO
						.getCurrentIndustry());
				this.saveCandidateBO.setCurrentSalary(profileBO
						.getCurrentSalary());
				this.saveCandidateBO.setDegree(profileBO.getDegree());
				this.saveCandidateBO.setDeleteBy(profileBO.getDeleteBy());
				this.saveCandidateBO.setDeletedDate(profileBO.getDeletedDate());
				this.saveCandidateBO.setDesignation(profileBO.getDesignation());
				this.saveCandidateBO.setDomainSkills(profileBO
						.getDomainSkills());
				this.saveCandidateBO.setEmailId(profileBO
						.getJobseekerProfileVO().getEmailId());
				this.saveCandidateBO.setExpectedCtc(profileBO.getExpectedCtc());
				this.saveCandidateBO.setExpEndDate(profileBO.getExpEndDate());
				this.saveCandidateBO.setExperienceInMonth(profileBO
						.getExperienceInMonth());
				this.saveCandidateBO.setExpStartDate(profileBO
						.getExpStartDate());
				this.saveCandidateBO.setFirstName(profileBO.getFirstName());
				this.saveCandidateBO.setFunction(profileBO.getFunction());
				this.saveCandidateBO.setGender(profileBO.getGender());
				this.saveCandidateBO.setGetPrivilege(profileBO
						.getGetPrivilege());
				this.saveCandidateBO.setGrade(profileBO.getGrade());
				this.saveCandidateBO.setJobType(profileBO.getJobType());
				this.saveCandidateBO.setKeySkills(profileBO.getKeySkills());
				this.saveCandidateBO.setLanguagesKnown(profileBO
						.getLanguagesKnown());
				this.saveCandidateBO.setLastName(profileBO.getLastName());
				this.saveCandidateBO.setLocation(profileBO.getLocation());
				this.saveCandidateBO.setMaritalStatus(profileBO
						.getMaritalStatus());
				this.saveCandidateBO.setModifiedBy(profileBO.getModifiedBy());
				this.saveCandidateBO.setNationality(profileBO.getNationality());
				this.saveCandidateBO.setPhone(profileBO.getPhone());
				this.saveCandidateBO.setPreferredIndustry(profileBO
						.getPreferredIndustry());
				this.saveCandidateBO.setPreferredLocation(profileBO
						.getPreferredLocation());
				this.saveCandidateBO.setProfileImage(profileBO
						.getProfileImage().getBytes(1,
								(int) profileBO.getProfileImage().length()));
				this.saveCandidateBO.setResumeTitle(profileBO.getResumeTitle());
				this.saveCandidateBO.setSpecialisation(profileBO
						.getSpecialisation());
				this.saveCandidateBO.setUploadResume(profileBO
						.getUploadResume().getBytes(1,
								(int) profileBO.getUploadResume().length()));
				this.saveCandidateBO.setYearOfPassing(profileBO
						.getYearOfPassing());
				this.saveCandidateBO.setCurrentSalaryPer(profileBO
						.getCurrentSalaryPer());
				this.saveCandidateBO.setExpectedCtcPer(profileBO
						.getExpectedCtcPer());
				this.saveCandidateBO.setExperienceInYear(profileBO
						.getExperienceInYear());
				this.saveCandidateBO.setProfiledescription(profileBO
						.getProfiledescription());
				this.saveCandidateBO.setId(profileBO.getSavecandidateid());
				this.saveCandidateBO.setEmpId(profileBO.getEmployerLoginVO()
						.getId());
				this.saveCandidateBO.setProfileId(profileBO
						.getJobseekerProfileVO().getprofileId());
				this.saveCandidateBO.setIsShortListed(profileBO
						.getIsShortListed());
				saveCandidate.add(this.saveCandidateBO);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return saveCandidate;
	}

	public List<ShortListCandidate> shortListCandidatesView(
			ShortListVO shortListVO) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		// List<ShortListVO> shortListcandidate = new ArrayList<ShortListVO>();
		final List<ShortListCandidate> saveCandidate = new ArrayList<ShortListCandidate>();
		try {
			final Criteria criteria = getSession().createCriteria(
					ShortListVO.class);
			criteria.add(Restrictions.eq("createdBy",
					shortListVO.getCreatedBy()));
			criteria.add(Restrictions.eq("isDeleted", true));
			criteria.addOrder(Order.desc("created"));
			List<ShortListVO> shortListcandidate = criteria.list();
			for (final ShortListVO profileBO : shortListcandidate) {
				final ShortListCandidate saveCandidate1 = new ShortListCandidate();
				saveCandidate1.setCompanyName(profileBO.getCompanyName());
				saveCandidate1.setCurrentIndustry(profileBO
						.getCurrentIndustry());
				saveCandidate1.setJobTitle(profileBO.getJobTitle());
				saveCandidate1.setFirstName(profileBO.getFirstName());
				saveCandidate1.setProfiledescription(profileBO
						.getProfiledescription());
				saveCandidate1.setPreferredLocation(profileBO
						.getPreferredLocation());
				saveCandidate1.setExpectedCtc(profileBO.getExpectedCtc());
				saveCandidate1.setResumeTitle(profileBO.getResumeTitle());
				saveCandidate1.setUploadResume(profileBO.getUploadResume());
				saveCandidate1.setKeySkills(profileBO.getKeySkills());
				saveCandidate1.setDegree(profileBO.getDegree());
				saveCandidate1.setCurrentIndustry(profileBO
						.getCurrentIndustry());
				saveCandidate1.setPhone(profileBO.getPhone());
				saveCandidate1.setEmailId(profileBO.getEmailId());
				saveCandidate1.setPreferredIndustry(profileBO
						.getPreferredIndustry());
				saveCandidate1.setShortlistId(profileBO.getShortlistId());
				saveCandidate1.setJobpostTittle(profileBO.getJobpostTittle());
				saveCandidate1.setCreatedBy(profileBO.getCreatedBy());
				saveCandidate1.setShortListCompany(profileBO
						.getShortListCompany());

				if (null != profileBO.getAppliedJobVO()) {
					List<JobseekerProfileVO> jobseekerProfile = new ArrayList<JobseekerProfileVO>();
					saveCandidate1.setEmploginId(profileBO.getAppliedJobVO()
							.getJobseekerLogin().getId());

					final Criteria c = getSession()
							.createCriteria(JobseekerProfileVO.class)
							.createCriteria("jobSeekerLogin")
							.add(Restrictions.eq("id",
									saveCandidate1.getEmploginId()));
					jobseekerProfile = c.list();
					for (final JobseekerProfileVO jobseekerVO : jobseekerProfile) {
						saveCandidate1.setProfileId(jobseekerVO.getprofileId());
						saveCandidate1.setJobseekerId(jobseekerVO
								.getJobSeekerLogin().getId());
					}

				}

				if (null != profileBO.getCandidateVO()) {
					saveCandidate1.setProfileId(profileBO.getCandidateVO()
							.getJobseekerProfileVO().getprofileId());
					saveCandidate1.setJobseekerId(profileBO.getCandidateVO()
							.getJobseekerProfileVO().getJobSeekerLogin()
							.getId());

				}

				saveCandidate.add(saveCandidate1);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return saveCandidate;
	}

	@Override
	public List<ShortListCandidate> shortListCandidates() {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		// List<ShortListVO> savecandidateVolist = new ArrayList<ShortListVO>();
		final List<ShortListCandidate> saveCandidate = new ArrayList<ShortListCandidate>();
		try {
			final Criteria criteria = getSession().createCriteria(
					ShortListVO.class);

			List<ShortListVO> savecandidateVolist = criteria.list();
			for (final ShortListVO profileBO : savecandidateVolist) {
				final ShortListCandidate saveCandidate1 = new ShortListCandidate();
				saveCandidate1.setCompanyName(profileBO.getCurrentIndustry());
				saveCandidate1.setJobTitle(profileBO.getJobTitle());
				saveCandidate1.setFirstName(profileBO.getFirstName());
				saveCandidate1.setProfiledescription(profileBO
						.getProfiledescription());
				saveCandidate1.setPreferredLocation(profileBO
						.getPreferredLocation());
				saveCandidate1.setExpectedCtc(profileBO.getExpectedCtc());
				saveCandidate1.setResumeTitle(profileBO.getResumeTitle());
				saveCandidate1.setUploadResume(profileBO.getUploadResume());
				saveCandidate1.setKeySkills(profileBO.getKeySkills());
				saveCandidate1.setDegree(profileBO.getDegree());
				saveCandidate1.setCurrentIndustry(profileBO
						.getCurrentIndustry());
				saveCandidate1.setPhone(profileBO.getPhone());
				saveCandidate1.setEmailId(profileBO.getEmailId());
				saveCandidate1.setPreferredIndustry(profileBO
						.getPreferredIndustry());
				saveCandidate1.setShortlistId(profileBO.getShortlistId());
				saveCandidate1.setJobpostTittle(profileBO.getJobpostTittle());
				saveCandidate1.setCreatedBy(profileBO.getCreatedBy());

				if (null != profileBO.getAppliedJobVO()) {
					saveCandidate1.setJobseekerId(profileBO.getAppliedJobVO()
							.getJobseekerLogin().getId());

					Criteria cri = getSession()
							.createCriteria(JobseekerProfileVO.class)
							.createCriteria("jobSeekerLogin")
							.add(Restrictions.eq("id",
									saveCandidate1.getJobseekerId()));

					List<JobseekerProfileVO> jobseekerProfileVO = cri.list();

					if (null != jobseekerProfileVO
							&& !jobseekerProfileVO.isEmpty()) {

						for (JobseekerProfileVO jobseekerProfile : jobseekerProfileVO) {
							saveCandidate1.setProfileId(jobseekerProfile
									.getprofileId());
						}
					}

				}

				if (null != profileBO.getCandidateVO()) {
					saveCandidate1.setProfileId(profileBO.getCandidateVO()
							.getJobseekerProfileVO().getprofileId());
				}

				saveCandidate.add(saveCandidate1);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return saveCandidate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#jobPostProfileStatus(com.myjobkart.vo.JobPostVO
	 * )
	 */
	@Override
	public JobPostVO jobPostProfileStatus(JobPostVO jobpostVO) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		try {
			final String updateActiveQuery = "UPDATE JobPostVO J set J.isActive=:IsActive where J.jobId=:jobId";
			Query query = getSession().createQuery(updateActiveQuery);

			query.setParameter("IsActive", jobpostVO.getIsActive());
			query.setParameter("jobId", jobpostVO.getJobId());
			int update = query.executeUpdate();
			if (0 != update) {
				return jobpostVO;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		EmployerDAOImpl.LOGGER.exit();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#shortListDeleteProfile(com.myjobkart.vo
	 * .ShortListVO)
	 */
	@Override
	public int shortListDeleteProfile(ShortListVO shortListVO) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		ShortListCandidate shortCandidateBO = new ShortListCandidate();
		// List<ShortListVO> shortListcandidate = new ArrayList<ShortListVO>();
		List<JobseekerProfileVO> JobseekerProfileList = new ArrayList<JobseekerProfileVO>();
		EmployerActivityVO emActivityVO = new EmployerActivityVO();
		EmployerVO employerVO = new EmployerVO();
		JobPostVO jobpostVO = new JobPostVO();
		JobseekerProfileVO jobseekerProfileVO = new JobseekerProfileVO();
		int result = 0;
		long jobseekerLoginId = 0;
		try {

			final String deleteQuery = "UPDATE ShortListVO s  set "
					+ "s.isDeleted =:Deleted," + "s.deletedBy =:deletedby,"
					+ "s.deletedDate =:deletedDate"
					+ " WHERE s.shortlistId =:id";

			Query query = getSession().createQuery(deleteQuery);
			query.setParameter("Deleted", shortListVO.getIsDeleted());
			query.setParameter("deletedby", shortListVO.getDeletedBy());
			query.setParameter("deletedDate", shortListVO.getDeletedDate());
			query.setParameter("id", shortListVO.getShortlistId());
			result = query.executeUpdate();

			if (shortListVO.getModifiedBy() != 0) {

				final Criteria criteria = getSession().createCriteria(
						ShortListVO.class);
				criteria.add(Restrictions.eq("shortlistId",
						shortListVO.getShortlistId()));
				criteria.addOrder(Order.desc("created"));
				List<ShortListVO> shortListcandidate = criteria.list();
				Session session = getSession();
				if (null != shortListcandidate) {
					for (ShortListVO uploadVO : shortListcandidate) {
						if (null != uploadVO.getAppliedJobVO()) {

							employerVO.setId(uploadVO.getAppliedJobVO()
									.getEmployerRegistration().getId());
							jobpostVO.setJobId(uploadVO.getAppliedJobVO()
									.getJobpostVO().getJobId());
							jobseekerLoginId = uploadVO.getAppliedJobVO()
									.getJobseekerLogin().getId();
							if (jobseekerLoginId != 0) {
								final Criteria cr = getSession()
										.createCriteria(
												JobseekerProfileVO.class);
								cr.add(Restrictions.eq("jobSeekerLogin.id",
										jobseekerLoginId));
								cr.add(Restrictions.eq("isDelete", true));
								cr.add(Restrictions.eq("isActive", true));
								JobseekerProfileList = cr.list();
								if (null != JobseekerProfileList) {
									for (JobseekerProfileVO profileListVO : JobseekerProfileList) {
										jobseekerProfileVO
												.setprofileId(profileListVO
														.getprofileId());
									}
								}
							}

						} else if (null != uploadVO.getCandidateVO()) {
							jobseekerProfileVO.setprofileId(uploadVO
									.getCandidateVO().getJobseekerProfileVO()
									.getprofileId());
							employerVO.setId(uploadVO.getCandidateVO()
									.getEmployerLoginVO()
									.getEmployerRegistration().getId());
						}
					}
				}

				emActivityVO.setCreated(new Date());
				emActivityVO.setCreatedBy(shortListVO.getModifiedBy());

				emActivityVO.setEmployerVO(employerVO);
				emActivityVO.setActivityDate(format.format(new Date()));
				emActivityVO.setStatus("De-Shortlisted");
				if (jobpostVO.getJobId() != 0) {
					emActivityVO.setJobPostVO(jobpostVO);
				}
				emActivityVO.setJobseekerProfileVO(jobseekerProfileVO);
				session.saveOrUpdate(emActivityVO);
			}

		} catch (HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);

			}
		}
		EmployerDAOImpl.LOGGER.exit();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#getJoppost(com.myjobkart.vo.JobPostVO)
	 */
	@Override
	public JobPostVO getJobpost(JobPostVO jobPostVO) {
		JobPostVO jobPostVORes = null;
		try {
			Session session = getSession();
			jobPostVORes = (JobPostVO) session.get(JobPostVO.class,
					jobPostVO.getJobId());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return jobPostVORes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#getBanner(com.myjobkart.vo.BannerPostVO)
	 */
	@Override
	public BannerPostVO getBanner(BannerPostVO bannerVO) {
		// TODO Auto-generated method stub
		BannerPostVO bannerVOList = null;
		try {
			Session session = getSession();
			bannerVOList = (BannerPostVO) session.get(BannerPostVO.class,
					bannerVO.getBannerId());

		} catch (HibernateException he) {
			he.printStackTrace();
		}

		return bannerVOList;
	}

	@Override
	public EmployerVO getEmployerReg(EmployerVO employerVO) {
		EmployerVO employerReg = null;
		try {
			Session session = getSession();
			employerReg = (EmployerVO) session.get(EmployerVO.class,
					employerVO.getId());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return employerReg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#getEmployerLogin(long)
	 */
	@Override
	public EmployerLoginVO getEmployerLogin(EmployerLoginVO employerLoginVO) {
		// TODO Auto-generated method stub

		try {
			Session session = getSession();
			employerLoginVO = (EmployerLoginVO) session.get(
					EmployerLoginVO.class, employerLoginVO.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employerLoginVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#employerRegisteration(com.myjobkart.bo.
	 * EmployerBO)
	 */
	@Override
	public EmployerVO employerRegisteration(EmployerVO employerVO) {
		// TODO Auto-generated method stub
		// List<EmployerVO> employerRegisterList = new ArrayList<EmployerVO>();

		try {
			Session session = getSession();
			Criteria employerRegisteraion = session
					.createCriteria(EmployerVO.class);
			employerRegisteraion.add(Restrictions.eq("confirmEmailAddress",
					employerVO.getConfirmEmailAddress()));
			List<EmployerVO> employerRegisterList = employerRegisteraion.list();
			for (EmployerVO empVO : employerRegisterList) {
				employerVO.setId(empVO.getId());
				employerVO.setCompanyLogo(empVO.getCompanyLogo());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employerVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#jobpostcount()
	 */
	@Override
	public List<EmployerBO> jobpostcount() {
		// TODO Auto-generated method stub
		List<EmployerBO> boList = new ArrayList<EmployerBO>();
		Session session = getSession();
		String sql = "select er.id, COUNT(jp.job_id) as jobcount, jp.company_name from job_posting as jp "
				+ "left join em_login as el on jp.emp_id = el.id "
				+ "Left join em_registration as er on el.em_Id = er.id where jp.is_active = true "
				+ "GROUP BY er.id LIMIT 36";
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			EmployerBO emp = new EmployerBO();
			emp.setId(Long.parseLong(row[0].toString()));
			emp.setJobCount(Integer.parseInt(row[1].toString()));
			emp.setCompanyName(row[2].toString());
			boList.add(emp);
		}

		return boList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#contactVO(com.myjobkart.vo.ContactVO)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#getProductList()
	 */
	@Override
	public List<ProductBO> getProductList() {
		// List<ProductVO> productVOList = new ArrayList<ProductVO>();
		List<ProductBO> productBOList = new ArrayList<ProductBO>();
		ProductBO productBO;

		try {
			final Criteria cr = getSession().createCriteria(ProductVO.class);
			cr.add(Restrictions.eq("isDeleted", true));
			List<ProductVO> productVOList = cr.list();
			if (productVOList.size() != 0) {
				for (ProductVO vo : productVOList) {
					productBO = new ProductBO();
					productBO.setProductId(vo.getProductId());
					productBO.setProductType(vo.getProductType());
					productBO.setProductPrice(vo.getProductPrice());
					productBO.setServices(vo.getServices());
					productBO.setdurationDate(vo.getDurationDate());
					productBO.setIsActive(vo.getIsActive());
					productBOList.add(productBO);
				}
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return productBOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#feedbackVO(com.myjobkart.vo.FeedbackVO)
	 */
	@Override
	public FeedbackVO feedbackVO(FeedbackVO feedback) {
		// TODO Auto-generated method stub
		try {
			Session session = getSession();
			session.saveOrUpdate(feedback);
			session.flush();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return feedback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#empprofileStatusActive(com.myjobkart.vo
	 * .EmployerVO)
	 */
	@Override
	public EmployerVO empprofileStatusActive(EmployerVO employerVO) {

		final String changeLoginQuery = "UPDATE EmployerVO E set E.isActive = :IsActive WHERE E.id=:id and E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(changeLoginQuery);
			query.setParameter("IsActive", employerVO.getIsActive());
			query.setParameter("emailAddress", employerVO.getEmailAddress());
			query.setParameter("id", employerVO.getId());
			final int result = query.executeUpdate();
			if (0 != result) {
				return employerVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();

		} finally {

		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#jobbyExperience()
	 */
	@Override
	public List<JobPostBO> jobbyExperience() {
		EmployerDAOImpl.LOGGER.entry();
		final List<JobPostBO> jobbyExperienceBOList = new ArrayList<JobPostBO>();
		// List<JobPostVO> jobPostVOList = new ArrayList<JobPostVO>();
		JobPostBO jobPostBO;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(JobPostVO.class);
			List<JobPostVO> jobPostVOList = cr.list();

			for (final JobPostVO jobPostVO : jobPostVOList) {
				jobPostBO = new JobPostBO();
				jobPostBO.setId(jobPostVO.getJobId());
				jobPostBO.setMinExp(jobPostVO.getMinExp());
				jobbyExperienceBOList.add(jobPostBO);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {

		}
		EmployerDAOImpl.LOGGER.exit();
		return jobbyExperienceBOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#jobbyLocation()
	 */
	@Override
	public List<JobPostBO> jobbyLocation() {
		EmployerDAOImpl.LOGGER.entry();
		final List<JobPostBO> jobbyLocationBOList = new ArrayList<JobPostBO>();
		// List<JobPostVO> jobPostVOList = new ArrayList<JobPostVO>();
		JobPostBO jobPostBO;
		try {
			Session session = getSession();
			final Criteria cr = session.createCriteria(JobPostVO.class);
			List<JobPostVO> jobPostVOList = cr.list();

			for (final JobPostVO jobPostVO : jobPostVOList) {
				jobPostBO = new JobPostBO();
				jobPostBO.setId(jobPostVO.getJobId());
				jobPostBO.setJobLocation(jobPostVO.getJobLocation());
				jobbyLocationBOList.add(jobPostBO);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} catch (final Exception e) {

		}
		EmployerDAOImpl.LOGGER.exit();
		return jobbyLocationBOList;
	}

	public boolean emailVerification(String email) {
		try {
			Criteria criteria = getSession().createCriteria(
					EmployerLoginVO.class);

			criteria.add(Restrictions.eq("emailAddress", email));
			List<EmployerLoginVO> loginVOList = criteria.list();
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

	public boolean Mobile_verifications(String mobileNumber) {
		try {
			Criteria criteria = getSession().createCriteria(EmployerVO.class);

			criteria.add(Restrictions.eq("mobileNumber",
					Long.parseLong(mobileNumber)));
			List<EmployerVO> loginVOList = criteria.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployerActivityBO> retriveEmpactivity(
			EmployerActivityBO employerActivityBO) {
		EmployerDAOImpl.LOGGER.entry();
		List<EmployerActivityBO> employerActivityBOList = new ArrayList<EmployerActivityBO>();
		// List<EmployerActivityVO> employerActivityVOList = new
		// ArrayList<EmployerActivityVO>();

		EmployerActivityBO activityBO = new EmployerActivityBO();
		int count = 0;
		long registrationId = 0;
		try {
			Session session = getSession();
			Criteria criteria = session
					.createCriteria(EmployerActivityVO.class);
			if (null != employerActivityBO.getStartDate()
					&& null != employerActivityBO.getEndDate()) {
				criteria.add(Restrictions.between("created",
						getStartOfDay(employerActivityBO.getStartDate()),
						getEndOfDay(employerActivityBO.getEndDate())));
			}
			if (null != employerActivityBO.getActivityStatus()) {
				criteria.add(Restrictions.like("status",
						employerActivityBO.getActivityStatus(),
						MatchMode.ANYWHERE));

			}

			criteria.add(Restrictions.eq("employerVO.id",
					employerActivityBO.getEmpId()));
			criteria.addOrder(Order.desc("created"));
			List<EmployerActivityVO> employerActivityVOList = criteria.list();

			for (EmployerActivityVO activityVO : employerActivityVOList) {
				activityBO = new EmployerActivityBO();

				if (null != activityVO.getEmployerVO()) {
					activityBO.setEmpId(activityVO.getEmployerVO().getId());
					registrationId = activityVO.getEmployerVO().getId();
				}

				if (employerActivityBO.getEmpId() == registrationId)

				{
					BeanUtils.copyProperties(activityVO, activityBO);
					activityBO.setActivityId(activityVO.getId());
					activityBO.setActivityStatus(activityVO.getStatus());

					if (null != activityVO.getJobPostVO()) {
						activityBO
								.setJbId(activityVO.getJobPostVO().getJobId());
						activityBO.setJbpostName(activityVO.getJobPostVO()
								.getPostedBy());
						activityBO.setJbpostTitle(activityVO.getJobPostVO()
								.getJobTitle());
					}
					if (null != activityVO.getAdminVO()) {
						activityBO.setAdminId(activityVO.getAdminVO().getId());
					}
					if (null != activityVO.getEmployerVO()) {
						activityBO.setEmpId(activityVO.getEmployerVO().getId());
						activityBO.setEmpName(activityVO.getEmployerVO()
								.getFirstName());
					}
					if (null != activityVO.getEmployerProfileVO()) {
						activityBO.setEmpprfId(activityVO
								.getEmployerProfileVO().getProfileId());

					}
					if (null != activityVO.getJobseekerProfileVO()) {
						activityBO.setJbprfId(activityVO
								.getJobseekerProfileVO().getprofileId());
						activityBO.setJbseekerName(activityVO
								.getJobseekerProfileVO().getFirstName());

					}
					if (null != activityVO.getJobseekerVO()) {
						activityBO.setJbseekerId(activityVO.getJobseekerVO()
								.getId());

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

	@Override
	public long activity(EmployerActivityVO activityVO) {
		EmployerDAOImpl.LOGGER.entry();
		long profileId = 0;
		try {
			Session session = getSession();
			session.saveOrUpdate(activityVO);
			session.flush();
			if (activityVO.getId() != 0) {
				profileId = activityVO.getId();
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return profileId;
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

	@Override
	public boolean findbyEmailSubuser(String emailAdress) {
		EmployerDAOImpl.LOGGER.entry();
		boolean status = false;
		try {
			Criteria cr = getSession().createCriteria(EmployerSubuserVO.class);
			cr.add(Restrictions.eq("emailAddress", emailAdress));
			List list = cr.list();
			if (0 != list.size()) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#createSubuser(com.myjobkart.vo.
	 * EmployerSubuserVO)
	 */
	@Override
	public long createSubuserprofile(EmployerSubuserVO employerSubuserVO) {
		EmployerDAOImpl.LOGGER.entry();
		long id = 0;

		try {
			id = (Long) getSession().save(employerSubuserVO);
			if (id > 0) {
				EmployerLoginVO employerLogin = new EmployerLoginVO();
				employerLogin.setEmailAddress(employerSubuserVO
						.getEmailAddress());
				employerLogin.setPassword(employerSubuserVO.getPassword());
				employerLogin.setConfirmPassword(employerSubuserVO
						.getConfirmPassword());
				employerLogin.setCreatedBy(employerSubuserVO.getEmployerLogin()
						.getEmployerRegistration().getId());
				employerLogin.setModifiedBy(employerSubuserVO
						.getEmployerLogin().getEmployerRegistration().getId());
				employerLogin.setActive(employerSubuserVO.getIsActive());
				EmployerVO employerVO = new EmployerVO();
				employerVO.setId(employerSubuserVO.getEmployerLogin()
						.getEmployerRegistration().getId());
				employerLogin.setEmployerRegistration(employerVO);
				EmployerSubuserVO subuserVO = new EmployerSubuserVO();
				subuserVO.setId(id);
				employerLogin.setEmployerSubuserVO(subuserVO);
				getSession().save(employerLogin);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#retriveSubuser(com.myjobkart.vo.
	 * EmployerSubuserVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployerSubuserBO> retriveSubusers(
			EmployerSubuserBO retriveprofile) {
		EmployerDAOImpl.LOGGER.entry();
		// List<EmployerSubuserVO> employerSubuserList = new
		// ArrayList<EmployerSubuserVO>();
		List<EmployerSubuserBO> boList = new ArrayList<EmployerSubuserBO>();
		try {
			long count = 0;
			final Criteria cr = getSession().createCriteria(
					EmployerSubuserVO.class);
			cr.add(Restrictions.eq("isDeleted", false));
			if (0 != retriveprofile.getEmployerId()) {
				cr.createCriteria("employerLogin").add(
						Restrictions.eq("id", retriveprofile.getEmployerId()));
			} else if (0 != retriveprofile.getId()) {
				cr.add(Restrictions.eq("id", retriveprofile.getId()));
			}
			List<EmployerSubuserVO> employerSubuserList = cr.list();
			if (null != employerSubuserList && !employerSubuserList.isEmpty()) {
				EmployerSubuserBO bo = null;
				for (EmployerSubuserVO vo : employerSubuserList) {
					bo = new EmployerSubuserBO();
					BeanUtils.copyProperties(vo, bo);
					vo.getEmployerLogin().getEmployerRegistration()
							.getCompanyName();
					EmployerLoginBO loginBO = new EmployerLoginBO();
					loginBO.setId(vo.getEmployerLogin().getId());
					loginBO.setEmailAddress(vo.getEmployerLogin()
							.getEmailAddress());
					bo.setEmploginBO(loginBO);
					bo.setEmployerId(vo.getId());
					bo.setEmpRegid(vo.getEmployerLogin()
							.getEmployerRegistration().getId());
					bo.setIsActivedVal(vo.getIsActive());
					count = count + 1;
					bo.setsNo(count);
					boList.add(bo);
				}
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}

		return boList;
	}

	@Override
	public EmployerSubuserBO retriveSubuser(EmployerSubuserBO retriveprofile) {
		EmployerDAOImpl.LOGGER.entry();

		try {
			EmployerSubuserVO employerSubuserVO = (EmployerSubuserVO) getSession()
					.get(EmployerSubuserVO.class, retriveprofile.getId());
			EmployerSubuserBO bo = null;
			bo = new EmployerSubuserBO();
			BeanUtils.copyProperties(employerSubuserVO, bo);
			bo.setEmployerId(employerSubuserVO.getId());
			return bo;
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return null;
	}

	@Override
	public EmployerSubuserVO getSubuser(EmployerSubuserBO bo) {
		EmployerDAOImpl.LOGGER.entry();

		try {
			EmployerSubuserVO employerSubuserVO = (EmployerSubuserVO) getSession()
					.get(EmployerSubuserVO.class, bo.getId());

			return employerSubuserVO;
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return null;
	}

	@Override
	public long retriveCompanyId(List<String> companyName) {
		long companyId = 0;
		try {
			Session session = getSession();
			List<CompanyVO> companyList;
			final Criteria cr = session.createCriteria(CompanyVO.class);
			cr.addOrder(Order.desc("created"));
			companyList = cr.list();
			for (CompanyVO getList : companyList) {
				for (String getCompany : companyName) {

					if (getList.getCompaniesName().equalsIgnoreCase(getCompany)) {
						companyId = getList.getCompaniesId();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyId;
	}

	public int subuserdelete(EmployerSubuserBO deleteSubuser)
			throws MyJobKartException {

		EmployerDAOImpl.LOGGER.entry();
		int result = 0;
		long subuserId = 0;
		// long employerLoginId = 0;
		final String deleteQuery = "UPDATE EmployerSubuserVO S set"
				+ " S.isDeleted = :isDeleted," + "S.modifiedBy = :modifiedBy,"
				+ " S.modified=:modified" + " WHERE S.id = :id";
		try {
			final Query query = getSession().createQuery(deleteQuery);

			query.setParameter("isDeleted", deleteSubuser.getIsDeletedVal());
			query.setParameter("modifiedBy", deleteSubuser.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("id", deleteSubuser.getId());

			result = query.executeUpdate();

			// List<EmployerLoginVO> employerLoginList = new
			// ArrayList<EmployerLoginVO>();
			final Criteria cr = getSession().createCriteria(
					EmployerLoginVO.class);
			cr.add(Restrictions.eq("employerSubuserVO.id",
					deleteSubuser.getId()));
			List<EmployerLoginVO> employerLoginList = cr.list();
			if (null != employerLoginList) {
				for (EmployerLoginVO loginVO : employerLoginList) {
					if (loginVO.getEmployerSubuserVO().getId() == deleteSubuser
							.getId()) {
						subuserId = loginVO.getId();
					}
				}
			}
			final String logindeleteQuery = "UPDATE EmployerLoginVO L set "
					+ "L.isActive=false" + " WHERE L.id = " + subuserId;

			final Query loginquery = getSession().createQuery(logindeleteQuery);
			result = loginquery.executeUpdate();

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#updateSubuser(com.myjobkart.vo.
	 * EmployerSubuserVO)
	 */
	public boolean updateSubuser(EmployerSubuserVO vo)
			throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		try {
			getSession().saveOrUpdate(vo);
			getSession().flush();
			return true;
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#getSubuserCount(long)
	 */
	@Override
	public long getSubuserCount(long id) {
		try {
			String sqlQuery = "SELECT count(id) AS user_count FROM em_subuser_creation WHERE is_deleted = false AND em_id = :id";
			SQLQuery query = getSession().createSQLQuery(sqlQuery);
			query.setParameter("id", id);
			if (query.list().size() > 0) {
				long count = Long.parseLong(query.list().get(0).toString());
				return count;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#updateSubUserActive(com.myjobkart.vo.
	 * EmployerSubuserVO)
	 */
	@Override
	public EmployerSubuserVO updateSubUserActive(EmployerSubuserVO subUserVO) {
		JobSeekerDAOImpl.LOGGER.entry();
		final String changeLoginQuery = "UPDATE EmployerSubuserVO E set E.isActive = :IsActive WHERE E.id=:id";
		try {
			Session session = getSession();
			final Query query = session.createQuery(changeLoginQuery);
			query.setParameter("IsActive", subUserVO.getIsActive());
			query.setParameter("id", subUserVO.getId());
			final int result = query.executeUpdate();
			if (0 != result) {
				long subuserId = 0;
				boolean activeId = subUserVO.getIsActive();
				// List<EmployerLoginVO> employerLoginList = new
				// ArrayList<EmployerLoginVO>();
				final Criteria cr = getSession().createCriteria(
						EmployerLoginVO.class);
				cr.add(Restrictions.eq("employerSubuserVO.id",
						subUserVO.getId()));
				List<EmployerLoginVO> employerLoginList = cr.list();
				if (null != employerLoginList) {
					for (EmployerLoginVO loginVO : employerLoginList) {
						if (loginVO.getEmployerSubuserVO().getId() == subUserVO
								.getId()) {
							subuserId = loginVO.getId();
						}
					}
				}
				final String logindeleteQuery = "UPDATE EmployerLoginVO L set "
						+ "L.isActive= " + activeId + " WHERE L.id = "
						+ subuserId;

				final Query loginquery = getSession().createQuery(
						logindeleteQuery);
				int resultId = loginquery.executeUpdate();

				return subUserVO;
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
	public void updateOrder(BookingVO orderVO) {
		try {
			Session session = getSession();
			session.saveOrUpdate(orderVO);
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Update Order has failed:" + ex.getMessage());
			}
			LOGGER.info("Update Order has failed:" + ex.getMessage());
		}
	}

	@Override
	public BookingVO getOrderByOrderId(long orderId) {
		BookingVO bookingVO = new BookingVO();
		try {
			bookingVO = (BookingVO) getSession().get(BookingVO.class, orderId);
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Order has failed:" + ex.getMessage());
			}
			LOGGER.info("Get Order has failed:" + ex.getMessage());
		}
		return bookingVO;
	}

	@SuppressWarnings("unused")
	@Override
	public void updateOrderStatus(String orderId, String status) {
		try {
			String hql = "UPDATE BookingVO set paymentStatus = :status "
					+ "WHERE bookingId = :bookingId";
			Query query = getSession().createQuery(hql);
			query.setParameter("status", status);
			query.setParameter("bookingId", Long.parseLong(orderId));
			int result = query.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Update Order Status has failed:"
						+ ex.getMessage());
			}
			LOGGER.info("Update Order Status has failed:" + ex.getMessage());
		}

	}

	@Override
	public BookingBO createOrder(BookingVO orderVO) {

		BookingBO orderBO = new BookingBO();
		try {
			Session session = getSession();
			long id = (Long) session.save(orderVO);
			if (id > 0) {
				Criteria criteria = getSession()
						.createCriteria(BookingVO.class);
				criteria.add(Restrictions.eq("custId", orderVO.getCustId()));
				criteria.addOrder(Order.desc("bookingId"));
				criteria.setMaxResults(1);
				if (null != criteria.list() && !criteria.list().isEmpty()) {
					BookingVO vo = (BookingVO) criteria.list().get(0);
					BeanUtils.copyProperties(vo, orderBO);
					orderBO.setId(vo.getBookingId());

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Create Order has failed:" + ex.getMessage());
			}
			LOGGER.info("Create Order has failed:" + ex.getMessage());
		}
		return orderBO;
	}

	@Override
	public EmployerVO getByEmployerEmail(String employerEmailId) {
		EmployerDAOImpl.LOGGER.entry();
		EmployerVO employerRegister = null;
		try {
			final Criteria cr = getSession().createCriteria(EmployerVO.class);

			if (null != employerEmailId) {
				cr.add(Restrictions.eq("emailAddress", employerEmailId));
				cr.add(Restrictions.eq("isDeleted", true));
			}
			List<EmployerVO> employerRegisterList = cr.list();
			if (null != employerRegisterList
					&& 0 != employerRegisterList.size()) {
				for (final EmployerVO registerList : employerRegisterList) {
					employerRegister = registerList;
				}
			} else {
				employerRegister = null;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}

		return employerRegister;
	}

	@Override
	public EmployerProfileVO retriveEmployer(EmployerProfileVO employerProfileVO) {
		EmployerDAOImpl.LOGGER.entry();
		EmployerProfileVO employerProfile = null;
		try {
			final Criteria cr = getSession().createCriteria(
					EmployerProfileVO.class);
			cr.add(Restrictions.eq("isDelete", true));
			if (null != employerProfileVO.getEmailId()) {
				cr.add(Restrictions.eq("emailId",
						employerProfileVO.getEmailId()));
			} else if (0 != employerProfileVO.getProfileId()
					&& null != employerProfileVO) {
				cr.add(Restrictions.eq("profileId",
						employerProfileVO.getProfileId()));
			} else if (null != employerProfileVO.getEmployerRegistion()) {
				cr.add(Restrictions.eq("employerRegistion.id",
						employerProfileVO.getEmployerRegistion().getId()));
			} else if (null != employerProfileVO.getCompanyID()) {
				cr.add(Restrictions.eq("companyID.companiesId",
						employerProfileVO.getCompanyID().getCompaniesId()));
			}
			List<EmployerProfileVO> employerProfileList = cr.list();
			if (null != employerProfileList && 0 != employerProfileList.size()) {
				for (final EmployerProfileVO profileList : employerProfileList) {
					employerProfile = profileList;
				}
			} else {
				employerProfile = null;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}

		return employerProfile;
	}

	@Override
	public boolean getemployerProduct(long empId) {

		System.out.println(empId);
		// TODO Auto-generated method stub
		boolean isCheck = false;
		final Criteria cr = getSession().createCriteria(
				EmployerProductServiceVO.class);
		cr.createCriteria("employer").add(Restrictions.eq("id", empId));
		cr.add(Restrictions.le("gracePeriod", new Date()));

		List list = cr.list();

		if (list.size() > 0) {
			isCheck = true;
		} else {
			isCheck = false;
		}

		return isCheck;
	}

	@Override
	public List<JobPostBO> viewRecruiters(String val) {

		List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();
		EmployerDAOImpl.LOGGER.entry();

		try {
			String sqlQuery = "SELECT jp.job_id, jp.company_name, jp.companyId from job_posting AS jp "
					+ "LEFT JOIN employer_profile AS ep ON jp.profileId = ep.profileId "
					+ "WHERE ep.isActive = true AND ep.isDelete "
					+ "= true AND jp.is_active = true AND jp.is_deleted = true ";
			if (null != val) {
				sqlQuery = sqlQuery + "AND jp.company_name LIKE '" + val
						+ "%' ";
			}
			sqlQuery = sqlQuery
					+ "GROUP BY jp.profileId ORDER BY jp.company_name ASC";
			SQLQuery query = getSession().createSQLQuery(sqlQuery);

			List<Object[]> objectList = query.list();
			if (null != objectList && objectList.size() > 0) {
				JobPostBO bo = null;
				for (Object[] obj : objectList) {
					bo = new JobPostBO();
					bo.setId(Long.parseLong(obj[0].toString()));
					bo.setCompanyName(obj[1].toString());
					bo.setCompanyId(Long.parseLong(obj[2].toString()));
					jobPostBOList.add(bo);
				}
				return jobPostBOList;
			}

		} catch (final Exception ex) {
			ex.printStackTrace();
			EmployerDAOImpl.LOGGER.exit();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.WalkinDAO#createWalkinprofile(java.util.List)
	 */
	@Override
	public long createWalkinprofile(List<WalkinVO> walkinVO)
			throws MyJobKartException {

		EmployerDAOImpl.LOGGER.entry();
		EmployerVO employerVO = new EmployerVO();
		long jobId = 0;

		try {
			if (null != walkinVO && walkinVO.size() != 0) {
				for (WalkinVO VO : walkinVO) {
					Session session = getSession();
					session.saveOrUpdate(VO);
					if (VO.getJobId() != 0) {
						jobId = VO.getJobId();

					}
				}
			}
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		}
		return jobId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.WalkinDAO#retriveWalkinJobs(com.myjobkart.vo.WalkinVO)
	 */
	@Override
	public List<WalkinBO> retriveWalkinJobs(WalkinBO bo)
			throws MyJobKartException, SQLException {
		EmployerDAOImpl.LOGGER.entry();
		// List<WalkinVO> walkinVOList = new ArrayList<WalkinVO>();
		final List<WalkinBO> walkinBOList = new ArrayList<WalkinBO>();

		WalkinBO walkinBO;
		try {
			long count = 0;
			final Criteria criteria = getSession().createCriteria(
					WalkinVO.class);

			if (null != bo.getEmployerRegistion()
					&& bo.getEmployerRegistion().getId() > 0) {
				criteria.add(Restrictions.eq("employerRegistionVO.id", bo
						.getEmployerRegistion().getId()));
				criteria.add(Restrictions.eq("isDeleted", true));

			} else if (bo.getId() != 0) {
				criteria.add(Restrictions.eq("jobId", bo.getId()));
				criteria.add(Restrictions.eq("isDeleted", true));
			} else {
				criteria.add(Restrictions.eq("isActive", true));
				criteria.add(Restrictions.eq("isDeleted", true));
			}
			if (null != bo.getSearchElement()) {
				criteria.add(Restrictions.ilike("jobTitle",
						"%" + bo.getSearchElement() + "%"));

			}
			criteria.add(Restrictions.eq("isDeleted", true));
			criteria.addOrder(Order.desc("jobId"));
			List<WalkinVO> walkinVOList = criteria.list();
			if (null != walkinVOList && 0 != walkinVOList.size()) {
				for (final WalkinVO walkin : walkinVOList) {
					walkinBO = new WalkinBO();

					EmployerLoginBO employerLoginBO = new EmployerLoginBO();
					employerLoginBO.setId(walkin.getEmployerLogin().getId());
					walkinBO.setEmployerLogin(employerLoginBO);

					EmployerBO employerBO = new EmployerBO();
					employerBO.setId(walkin.getEmployerRegistionVO().getId());
					walkinBO.setEmployerRegistion(employerBO);

					walkinBO.setAddress(walkin.getAddress());
					walkinBO.setCompanyName(walkin.getCompanyName());
					walkinBO.setCompanyProfile(walkin.getCompanyProfile());
					walkinBO.setContactNo(walkin.getContactNo());
					walkinBO.setContactPerson(walkin.getContactPerson());
					walkinBO.setCreatedBy(walkin.getCreatedBy());
					walkinBO.setCurrencyType(walkin.getCurrencyType());
					walkinBO.setFunctionArea(walkin.getFunctionArea());
					walkinBO.setIndustryType(walkin.getIndustryType());
					walkinBO.setJobDescription(walkin.getJobDescription());
					walkinBO.setJobLocation(walkin.getJobLocation());
					walkinBO.setJobResponse(walkin.getJobResponse());
					walkinBO.setJobTitle(walkin.getJobTitle());
					walkinBO.setKeywords(walkin.getKeywords());
					walkinBO.setMaxExp(walkin.getMaxExp());
					walkinBO.setModifiedBy(walkin.getModifiedBy());
					walkinBO.setMaxSalary(walkin.getMaxSalary());
					walkinBO.setMinExp(walkin.getMinExp());
					walkinBO.setMinSalary(walkin.getMinSalary());
					walkinBO.setNoVacancies(walkin.getNoVacancies());
					walkinBO.setVersion(walkin.getVersion());
					walkinBO.setStartDate(walkin.getStartDate());
					walkinBO.setEndDate(walkin.getEndDate());
					walkinBO.setPgQualification(walkin.getPgQualification());
					walkinBO.setUgQualification(walkin.getUgQualification());
					walkinBO.setDoctorate(walkin.getDoctorate());
					walkinBO.setRole(walkin.getRole());
					walkinBO.setRoleCategory(walkin.getRoleCategory());
					walkinBO.setCreated(walkin.getCreated());
					walkinBO.setModified(walkin.getModified());
					walkinBO.setExperiance(walkin.getMinExp() + "-"
							+ walkin.getMaxExp());
					walkinBO.setPostedBy(walkin.getPostedBy());
					walkinBO.setId(walkin.getJobId());
					walkinBO.setIsActive(walkin.getIsActive());
					walkinBO.setEmployerId(walkin.getEmployerLogin().getId());
					walkinBO.setEmpRegId(walkin.getEmployerRegistionVO()
							.getId());
					walkinBO.setSalary(walkin.getMinSalary() + "-"
							+ walkin.getMaxSalary());
					count = count + 1;
					walkinBO.setSNo(count);
					walkinBOList.add(walkinBO);

				}
			}
		} catch (final NullPointerException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			EmployerDAOImpl.LOGGER.exit();
		}
		return walkinBOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#retriveWalkin(com.myjobkart.bo.WalkinBO)
	 */
	@Override
	public WalkinBO retriveWalkin(WalkinBO walkinBO) {

		EmployerDAOImpl.LOGGER.entry();
		try {
			WalkinVO walkinVO = (WalkinVO) getSession().get(WalkinVO.class,
					walkinBO.getId());
			WalkinBO walkin = new WalkinBO();
			BeanUtils.copyProperties(walkinVO, walkin);
			walkin.setJobId(walkinVO.getJobId());
			return walkin;
		} catch (final Exception ee) {
			ee.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(ee, ee.getMessage());
		}
		EmployerDAOImpl.LOGGER.exit();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#walkinStatus(com.myjobkart.vo.WalkinVO)
	 */
	@Override
	public WalkinVO walkinStatus(WalkinVO walkinVO) {
		// TODO Auto-generated method stub
		EmployerDAOImpl.LOGGER.entry();
		try {
			final String updateActiveQuery = "update WalkinVO  W set W.isActive=:IsActive where W.jobId=:jobId";
			Query query = getSession().createQuery(updateActiveQuery);
			query.setParameter("IsActive", walkinVO.getIsActive());
			query.setParameter("jobId", walkinVO.getJobId());
			int update = query.executeUpdate();
			if (0 != update) {
				return walkinVO;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		EmployerDAOImpl.LOGGER.exit();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#deleteWalkin(com.myjobkart.vo.WalkinVO)
	 */
	@Override
	public int deleteWalkin(WalkinVO walkinVO) throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		int result = 0;

		final String deleteQuery = "UPDATE WalkinVO S set"
				+ " S.isDeleted = :isDeleted," + "S.modifiedBy = :modifiedBy,"
				+ " S.deletedBy = :deletedBy,"
				+ "S.deletedDate = :deletedDate," + " S.modified=:modified"
				+ " WHERE S.jobId = :jobId";
		try {
			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDeleted", walkinVO.getIsDeleted());
			query.setParameter("deletedDate", walkinVO.getDeletedDate());
			query.setParameter("deletedBy", walkinVO.getDeletedBy());
			query.setParameter("modifiedBy", walkinVO.getModifiedBy());
			query.setParameter("modified", new Date());
			query.setParameter("jobId", walkinVO.getJobId());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
						.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		}
		EmployerDAOImpl.LOGGER.exit();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#retriveWalkinJobpost(com.myjobkart.bo.WalkinBO
	 * )
	 */
	@Override
	public WalkinBO retriveWalkinJobpost(WalkinBO walkinBO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#updateWalkinjob(com.myjobkart.vo.WalkinVO)
	 */
	@Override
	public boolean updateWalkinjob(WalkinVO vo) throws MyJobKartException {

		EmployerDAOImpl.LOGGER.entry();
		try {
			getSession().saveOrUpdate(vo);
			getSession().flush();
			return true;
		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.EmployerDAO#getwalkinpost(com.myjobkart.vo.WalkinVO)
	 */
	@Override
	public WalkinVO getwalkinpost(WalkinVO walkinPost) {
		WalkinVO walkinVO = null;
		try {
			Session session = getSession();
			walkinVO = (WalkinVO) session.get(WalkinVO.class,
					walkinPost.getJobId());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return walkinVO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.EmployerDAO#getJoppost(com.myjobkart.vo.JobPostVO)
	 */
	@Override
	public JobPostVO getJoppost(JobPostVO jobPostVO) {
		JobPostVO jobPostVORes = null;
		try {
			Session session = getSession();
			jobPostVORes = (JobPostVO) session.get(JobPostVO.class,
					jobPostVO.getJobId());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return jobPostVORes;
	}

	public List<JobseekerProfileVO> retrivejobseekerList() {
		List<JobseekerProfileVO> jobseekerprofileList = new ArrayList<JobseekerProfileVO>();
		final Criteria criteria = getSession().createCriteria(
				JobseekerProfileVO.class);
		criteria.addOrder(Order.desc("id"));
		jobseekerprofileList = criteria.list();

		return jobseekerprofileList;
	}

	public List<EmployerjobAlertsVO> employerjob_alert() {

		List<EmployerjobAlertsVO> employerjobList = new ArrayList<EmployerjobAlertsVO>();

		try {
			final Criteria criteria = getSession().createCriteria(
					EmployerjobAlertsVO.class);
			criteria.addOrder(Order.desc("jobId"));
			criteria.setMaxResults(1);
			employerjobList = criteria.list();

		} catch (final HibernateException he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return employerjobList;
	}

	public boolean addEmployerAlert(Set<JobPostVO> postVOList) {
		try {
			JobPostVO jobpostVO = null;
			EmployerAlertVO employerVO = null;
			if (null != postVOList) {
				for (JobPostVO empAlert : postVOList) {
					jobpostVO = new JobPostVO();
					employerVO = new EmployerAlertVO();
					employerVO.setKeySkills(empAlert.getKeywords());
					employerVO.setActive(empAlert.getIsActive());
					employerVO.setEmailAddress(empAlert.getEmployerLogin()
							.getEmailAddress());
					EmployerLoginVO loginVO = new EmployerLoginVO();
					loginVO.setId(empAlert.getEmployerLogin().getId());
					employerVO.setEmpLogin(loginVO);
					jobpostVO.setJobId(empAlert.getJobId());
					employerVO.setJobpost(jobpostVO);
					Session session = getSession();
					session.save(employerVO);
					session.flush();
					return true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;

	}

	public boolean addNextrun(EmployerjobAlertsVO employerjob) {
		try {
			Session session = getSession();

			session.saveOrUpdate(employerjob);
			return true;
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return false;
	}

}
