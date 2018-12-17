package com.myjobkart.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.myjobkart.bo.ContactBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.FeedbackBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.service.GenericService;
import com.myjobkart.service.JobtrolleyResourceBundle;
import com.myjobkart.utils.ErrorCodes;
import com.myjobkart.vo.AdminLoginVO;
import com.myjobkart.vo.BODTO;
import com.myjobkart.vo.CompaniesVO;
import com.myjobkart.vo.CompanyVO;
import com.myjobkart.vo.ContactVO;
import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerProfileVO;
import com.myjobkart.vo.EmployerVO;
import com.myjobkart.vo.FeedbackVO;
import com.myjobkart.vo.IndustryVO;
import com.myjobkart.vo.JobPostVO;
import com.myjobkart.vo.JobseekerLoginVO;
import com.myjobkart.vo.JobseekerProfileVO;
import com.myjobkart.vo.JobseekerVO;
import com.myjobkart.vo.NewsLetterVO;
import com.myjobkart.vo.ProductVO;
import com.myjobkart.vo.WalkinVO;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by : Vinoth P
 * Description : JobSeekerDAOImpl is a Class which is responsible for storing
 * the data into the database Reviewed by :
 * 
 * 
 */

@Repository("adminDAOImpl")
public class AdminDAOImpl extends GenericDAOImpl<AdminLoginVO> implements
AdminDAO {

	public AdminDAOImpl() throws MyJobKartException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final JLogger LOGGER = JLogger.getLogger(AdminDAOImpl.class);

	@Override
	public AdminLoginVO authendicate(String string, String emailAddress)
			throws MyJobKartException {
		AdminDAOImpl.LOGGER.entry();
		AdminLoginVO adminLoginVO = null;
		final String loginQuery = "FROM AdminLoginVO E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			session.flush();
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				adminLoginVO = (AdminLoginVO) query.list().get(0);
			}

		} catch (final HibernateException he) {
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			AdminDAOImpl.LOGGER.exit();
		}

		return adminLoginVO;

	}

	@Override
	public void deleteAll(List<BODTO<AdminLoginVO>> entityList)
			throws MyJobKartException {
		// TODO Auto-generated method stub

	}

	@Override
	public AdminLoginVO findByCriteria(AdminLoginVO entity)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GenericService<AdminLoginVO> getBasicService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobPostBO> reteriveAllJoBpost() throws SerialException,
	SQLException {
		// TODO Auto-generated method stub
		List<JobPostVO> jobPostVOList = new ArrayList<JobPostVO>();
		final List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();

		AdminDAOImpl.LOGGER.entry();
		int count = 0;
		try {
			Session session = getSession();
			final Criteria criteria = session.createCriteria(JobPostVO.class);
			criteria.add(Restrictions.eq("isDeleted", true));
			jobPostVOList = criteria.list();
			for (final JobPostVO jobPostVO : jobPostVOList) {
				final JobPostBO jobPostBO = new JobPostBO();
				jobPostBO.setCompanyName(jobPostVO.getCompanyName());
				jobPostBO.setJobTitle(jobPostVO.getJobTitle());
				jobPostBO.setContactPerson(jobPostVO.getContactPerson());
				jobPostBO.setIsActive(jobPostVO.getIsActive());
				jobPostBO.setAddress(jobPostVO.getAddress());
				jobPostBO.setContactNo(jobPostVO.getContactNo());
				jobPostBO.setJobDescription(jobPostVO.getJobDescription());
				jobPostBO.setUgQualification(jobPostVO.getUgQualification());
				jobPostBO.setIndustryType(jobPostVO.getIndustryType());
				jobPostBO.setFunctionArea(jobPostVO.getFunctionArea());
				jobPostBO.setKeywords(jobPostVO.getKeywords());
				jobPostBO.setJobLocation(jobPostVO.getJobLocation());
				jobPostBO.setMinExp(jobPostVO.getMinExp());
				jobPostBO.setMaxSalary(jobPostVO.getMaxSalary());
				jobPostBO.setMinSalary(jobPostVO.getMinSalary());
				jobPostBO.setMaxExp(jobPostVO.getMaxExp());
				jobPostBO.setNoVacancies(jobPostVO.getNoVacancies());
				jobPostBO.setPgQualification(jobPostVO.getPgQualification());
				jobPostBO.setId(jobPostVO.getJobId());
				/*
				 * jobPostBO.setPresentation(jobPostVO.getPresentation().getBytes
				 * ( 1, (int) jobPostVO.getPresentation().length()));
				 */
				/*
				 * jobPostBO.setPhoto(jobPostVO.getPhoto().getBytes(1, (int)
				 * jobPostVO.getPhoto().length()));
				 */
				jobPostBO.setOtherSalaryDetails(jobPostVO
						.getOtherSalaryDetails());
				jobPostBO.setPostedBy(jobPostVO.getPostedBy());
				jobPostBO.setJobResponse(jobPostVO.getJobResponse());
				jobPostBO.setCurrencyType(jobPostVO.getCurrencyType());
				jobPostBO.setIsActive(jobPostVO.getIsActive());
				jobPostBO.setVersion(jobPostVO.getVersion());
				jobPostBO.setStartDate(jobPostVO.getStartDate());
				jobPostBO.setEndDate(jobPostVO.getEndDate());

				jobPostBO.setEmployerRegisterID(jobPostVO.getEmployerLogin()
						.getEmployerRegistration().getId());

				count = count + 1;
				jobPostBO.setsNo(count);
				jobPostBOList.add(jobPostBO);

			}
		} catch (final HibernateException e) {
			e.printStackTrace();

		}
		return jobPostBOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.dao.AdminDAO#createProduct(com.jobtrolley.vo.ProductVO)
	 */
	@Override
	public ProductVO createProduct(ProductVO productVO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		AdminDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.save(productVO);
			session.flush();
		} catch (HibernateException he) {
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);

		} finally {
			AdminDAOImpl.LOGGER.exit();
		}

		return productVO;

	}

	@Override
	public boolean procutAuthentication(ProductBO productBO) {
		// TODO Auto-generated method stub
		AdminDAOImpl.LOGGER.entry();
		List<ProductVO> listProductVO = new ArrayList<ProductVO>();
		boolean productStatus = false;
		try {
			Session session = getSession();
			final Criteria criteria = session.createCriteria(ProductVO.class);
			criteria.addOrder(Order.desc("created"));
			criteria.add(Restrictions.eq("productType", productBO.getProductType()));
			criteria.add(Restrictions.eq("isDeleted", true));
			listProductVO = criteria.list();
			if(null != listProductVO && 0 !=listProductVO.size()){
				productStatus = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view product failed:" + e.getMessage());
			}
			LOGGER.info("view product failed:" + e.getMessage());
		}
		AdminDAOImpl.LOGGER.exit();
		return productStatus;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jobtrolley.dao.AdminDAO#viewProduct()
	 */
	@Override
	public List<ProductBO> viewProduct() {
		// TODO Auto-generated method stub
		AdminDAOImpl.LOGGER.entry();
		List<ProductVO> listProductVO = new ArrayList<ProductVO>();
		final List<ProductBO> listProductBO = new ArrayList<ProductBO>();
		try {
			Session session = getSession();
			final Criteria criteria = session.createCriteria(ProductVO.class);
			criteria.addOrder(Order.desc("created"));
			criteria.add(Restrictions.eq("isDeleted", true));
			listProductVO = criteria.list();
			int i=1;
			SimpleDateFormat changeDateFormate = new SimpleDateFormat("dd/MM/yyyy");
			for (ProductVO productVO : listProductVO) {

				ProductBO productBO = new ProductBO();
				productBO.setSno(i++);

				productBO.setProductId(productVO.getProductId());
				productBO.setProductType(productVO.getProductType());
				productBO.setProductPrice(productVO.getProductPrice());
				productBO.setdurationDate(productVO.getDurationDate());
				productBO.setServices(productVO.getServices());
				productBO.setCreated(productVO.getCreated());
				productBO.setIsActive(productVO.getIsActive());
				productBO.setIsDeleted(productVO.getIsDeleted());
				productBO.setRegisterDate(changeDateFormate.format(productVO.getCreated()));
				if (productBO.getIsActive() == true) {
					productBO.setActive("Active");
				} else {
					productBO.setActive("De-Active");
				}
				listProductBO.add(productBO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view product failed:" + e.getMessage());
			}
			LOGGER.info("view product failed:" + e.getMessage());
		}
		AdminDAOImpl.LOGGER.exit();
		return listProductBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.dao.AdminDAO#procutActivation(com.jobtrolley.vo.ProductVO)
	 */
	@Override
	public boolean procutActivation(ProductVO productVO) {
		// TODO Auto-generated method stub
		boolean status = false;
		Session session = getSession();
		String createQuery = "update ProductVO P set P.isActive=:status where  P.productId=:Id";
		Query query = session.createQuery(createQuery);
		query.setParameter("status", productVO.getIsActive());
		query.setParameter("Id", productVO.getProductId());
		int result = query.executeUpdate();
		if (0 != result) {

			status = true;

		} else {

		}

		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.dao.AdminDAO#productUpdate(com.jobtrolley.vo.ProductVO)
	 */
	@Override
	public int productUpdate(ProductVO productVO) throws MyJobKartException {
		// TODO Auto-generated method stub
		AdminDAOImpl.LOGGER.entry();
		int result = 0;

		try {
			String createQuery = "update ProductVO P set P.productType=:productType,P.services=:services,P.productPrice=:productPrice,P.durationDate=:durationDate where  P.productId=:Id";
			Session session = getSession();
			Query query = session.createQuery(createQuery);
			query.setParameter("productType", productVO.getProductType());
			query.setParameter("services", productVO.getServices());
			query.setParameter("productPrice", productVO.getProductPrice());
			query.setParameter("durationDate", productVO.getDurationDate());
			query.setParameter("Id", productVO.getProductId());
			result = query.executeUpdate();
			if (0 != result) {

				System.out
				.println("################## The Query Updated is Sucessfully #####################"
						+ result);

			} else {
				System.out
				.println("################## The Query Updated is Not Sucessfully ##########################"
						+ result);
			}
			// session.saveOrUpdate(productVO);

		} catch (HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.UPDATE_FAIL, he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {
			AdminDAOImpl.LOGGER.exit();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobtrolley.dao.AdminDAO#deleteProduct(com.jobtrolley.vo.ProductVO)
	 */
	@Override
	public int deleteProduct(ProductVO productVO) throws MyJobKartException {
		// TODO Auto-generated method stub
		AdminDAOImpl.LOGGER.entry();
		int result = 0;
		try {
			String deleteProduct = "update ProductVO P set P.isDeleted=:delete,P.modifiedBy=:modifiedUser where P.productId=:Id";
			Session session = getSession();

			Query query = session.createQuery(deleteProduct);
			query.setParameter("modifiedUser", productVO.getModifiedBy());
			query.setParameter("delete", productVO.getIsDeleted());
			query.setParameter("Id", productVO.getProductId());
			result = query.executeUpdate();

		} catch (HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL, he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_DELETE_FAIL,
					ErrorCodes.ENTITY_DELETE_FAIL_MSG);
		} finally {
			AdminDAOImpl.LOGGER.exit();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#contactVO(com.myjobkart.vo.ContactVO)
	 */
	@Override
	public ContactVO contactVO(ContactVO contactVO) {
		// TODO Auto-generated method stub
		try {
			Session session = getSession();
			session.saveOrUpdate(contactVO);
			session.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contactVO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#retrieveUserConatctDetails()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContactBO> retrieveUserConatctDetails(ContactBO contact) {
		// TODO Auto-generated method stub
		List<ContactBO> boList = new ArrayList<ContactBO>();
		List<ContactVO> voList = new ArrayList<ContactVO>();
		ContactBO contactBO;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(ContactVO.class);
			if (null != contact.getSearchElement()) {

				criteria.add(Restrictions.ilike("name",
						"%" + contact.getSearchElement() + "%"));

			}
			criteria.addOrder(Order.desc("id"));
			criteria.add(Restrictions.eq("isDelete", false));
			voList = criteria.list();
			SimpleDateFormat dateformate = new SimpleDateFormat("dd/MM/yyyy");
			int sno=1;	
			for (ContactVO contactVO : voList) {
				contactBO = new ContactBO();
				contactBO.setSno(sno++);
				contactBO.setemail(contactVO.getemail());
				contactBO.setsubject(contactVO.getsubject());
				contactBO.setmessage(contactVO.getmessage());
				contactBO.setId(contactVO.getcontactId());
				contactBO.setname(contactVO.getName());
				contactBO.setphoneno(contactVO.getPhoneno());
				contactBO.setContactCreatedDate(dateformate.format(contactVO.getCreated())); 
				boList.add(contactBO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#deleteContact(com.myjobkart.vo.ContactVO)
	 */
	@SuppressWarnings("unused")
	@Override
	public int deleteContact(ContactVO contactVO) {
		// TODO Auto-generated method stub
		int result = 0;
		String deletequery = "UPDATE ContactVO C set C.isDelete=:isDelete where C.contactId=:id";
		try {
			Session session = getSession();
			final Query query = session.createQuery(deletequery);
			query.setParameter("isDelete", contactVO.getIsDelete());
			query.setParameter("id", contactVO.getcontactId());
			result = query.executeUpdate();
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#retrievefeedbackDetails()
	 */
	@Override
	public List<FeedbackBO> retrievefeedbackDetails(FeedbackBO feedback) {
		// TODO Auto-generated method stub
		List<FeedbackBO> boList = new ArrayList<FeedbackBO>();
		List<FeedbackVO> voList = new ArrayList<FeedbackVO>();
		FeedbackBO feedBackBO;
		try {
			int sno = 1;
			SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			Session session = getSession();
			Criteria criteria = session.createCriteria(FeedbackVO.class);

			if (null != feedback.getSearchElement()) {

				criteria.add(Restrictions.ilike("name",
						"%" + feedback.getSearchElement() + "%"));

			}
			criteria.addOrder(Order.desc("id"));
			criteria.add(Restrictions.eq("isDelete", false));
			voList = criteria.list();

			for (FeedbackVO feedBackVO : voList) {

				feedBackBO = new FeedbackBO();
				feedBackBO.setSno(sno++);
				feedBackBO.setName(feedBackVO.getName());
				feedBackBO.setEmail(feedBackVO.getEmail());
				feedBackBO.setPhoneno(feedBackVO.getPhoneno());
				feedBackBO.setArea(feedBackVO.getArea());
				feedBackBO.setDetails(feedBackVO.getDetails());
				feedBackBO.setId(feedBackVO.getId());
				feedBackBO.setSubject(feedBackVO.getSubject());
				feedBackBO.setFeedbackCreateDate(dateFormate.format(feedBackVO.getCreated()));
				boList.add(feedBackBO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boList;

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.AdminDAO#deleteFeedback(com.myjobkart.vo.FeedbackVO)
	 */
	@Override
	public int deleteFeedback(FeedbackVO feedbackVO) {
		// TODO Auto-generated method stub
		int result = 0;
		String deletequery = "UPDATE FeedbackVO f set f.isDelete=:isDelete where f.id=:id";
		try {
			Session session = getSession();
			final Query query = session.createQuery(deletequery);
			query.setParameter("isDelete", feedbackVO.getIsDelete());
			query.setParameter("id", feedbackVO.getId());
			result = query.executeUpdate();
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<String, List<EmployerProfileVO>> retrieveCompanyName() {

		Map<String, List<EmployerProfileVO>> companyByIndustryList = new HashMap<String, List<EmployerProfileVO>>();
		List<EmployerProfileVO> companyList =null;
		String employerIndustry = null;
		int recordCount=0;

		try{
			Session session = getSession();
			Criteria cr = session.createCriteria(EmployerProfileVO.class);
			cr.add(Restrictions.eq("isActive", true));
			cr.addOrder(Order.asc("companyType"));
			List<EmployerProfileVO> rows = cr.list();
			if(null!=rows && rows.size()>0){
			 employerIndustry=rows.get(0).getCompanyType();
			}

			for(EmployerProfileVO employers:rows){
				recordCount++;
				if(employerIndustry.equalsIgnoreCase(employers.getCompanyType())){
					if(null==companyList){
					companyList = new ArrayList<EmployerProfileVO>();
					}
					companyList.add(employers);	
					if(companyList.size()>0){
						companyByIndustryList.put(employerIndustry, companyList);
					}
				}else{
					if(null!=companyList && companyList.size() >0){
						companyByIndustryList.put(employerIndustry, companyList);
					}
					employerIndustry=employers.getCompanyType();
					companyList = null;	
					companyList = new ArrayList<EmployerProfileVO>();
					companyList.add(employers);
					if(rows.size()==recordCount){
						companyByIndustryList.put(employerIndustry, companyList);
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/*try {
			final List<EntityBO> industryAllList = new ArrayList<EntityBO>();
			List<IndustryVO> industrylist = new ArrayList<IndustryVO>();
			EntityBO industryEntity;

			Session session = getSession();
			Criteria cr = session.createCriteria(IndustryVO.class);
			cr.addOrder(Order.desc("industryId"));
			industrylist = cr.list();
			String companyType;
			List<String> IndustryStringList = new ArrayList<String>();
			for (final IndustryVO industryVO : industrylist) {

				IndustryStringList.add(industryVO.getIndustryName());
			}


			for(String industryType:IndustryStringList){
				companyType = industryType;
			String tempValue[] = companyType.split(",");
			String sqlQuery = "SELECT companyType, companyName,isActive FROM employer_profile";
			SQLQuery query = getSession().createSQLQuery(sqlQuery);
			List<Object[]> rows = query.list();
			List<String> companyNameList = null;
			if (null != rows && !rows.isEmpty()) {
				for (int i = 0; i < tempValue.length; i++) {
					String mapKey = null;
					companyNameList = new ArrayList<String>();
					int count = 1;
					for (Object[] row : rows) {
						if(null != row[0].toString()){
							if (row[2].equals(true)) {

								if (tempValue[i]
										.equalsIgnoreCase(row[0].toString())) {
									mapKey = tempValue[i];
									companyNameList.add(row[1].toString());
									if (count == 5) {
										break;
									}
									count++;
								}
							}
						}

					}
					if (null != mapKey) {

						// add elements to al, including duplicates
						Set<String> hs = new HashSet<>();
						hs.addAll(companyNameList);
						companyNameList.clear();
						companyNameList.addAll(hs);
						companyList.put(mapKey, companyNameList);
					}
				}
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		return companyByIndustryList;
	}


	@Override
	public Map<String, List<String>> retrieveEntityList(String Value) {

		Map<String, List<String>> companyList = new HashMap<String, List<String>>();
		try {
			List<IndustryVO> industrylist = new ArrayList<IndustryVO>();

			Session session = getSession();
			Criteria cr = session.createCriteria(IndustryVO.class);
			cr.addOrder(Order.desc("industryId"));
			industrylist = cr.list();
			String companyType;
			List<String> IndustryStringList = new ArrayList<String>();
			for (final IndustryVO industryVO : industrylist) {
				if(industryVO.getIndustryName().startsWith(Value)){
					IndustryStringList.add(industryVO.getIndustryName());
				}
			}


			for(String industryType:IndustryStringList){
				companyType = industryType;
				String tempValue[] = companyType.split(",");
				String sqlQuery = "SELECT companyType, companyName,isActive FROM employer_profile";
				SQLQuery query = getSession().createSQLQuery(sqlQuery);
				List<Object[]> rows = query.list();
				List<String> companyNameList = null;
				if (null != rows && !rows.isEmpty()) {
					for (int i = 0; i < tempValue.length; i++) {
						String mapKey = null;
						companyNameList = new ArrayList<String>();

						for (Object[] row : rows) {
							if(null != row[0].toString()){
								if (row[2].equals(true)) {

									if (tempValue[i]
											.equalsIgnoreCase(row[0].toString())) {
										mapKey = tempValue[i];
										companyNameList.add(row[1].toString());
									}
								}
							}

						}
						if (null != mapKey) {

							// add elements to al, including duplicates
							Set<String> hs = new HashSet<>();
							hs.addAll(companyNameList);
							companyNameList.clear();
							companyNameList.addAll(hs);
							companyList.put(mapKey, companyNameList);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return companyList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#retrieveExperience()
	 */
	@Override
	public Map<String, List<String>> retrieveExperience() {

		Map<String, List<String>> experienceList = new HashMap<String, List<String>>();
		try {
			String companyType = JobtrolleyResourceBundle
					.getDropdown("employer.companyType");
			String tempValue[] = companyType.split(",");
			String sqlQuery = "SELECT distinct min_exp, industry_type,is_active FROM job_posting";
			SQLQuery query = getSession().createSQLQuery(sqlQuery);
			List<Object[]> rows = query.list();

			List<String> expList = null;

			int count = 1;
			if (null != rows && !rows.isEmpty()) {
				for (int i = 0; i < tempValue.length; i++) {
					String mapKey = null;
					expList = new ArrayList<String>();
					for (Object[] row : rows) {

						if (row[2].equals(true)) {

							if (tempValue[i]
									.equalsIgnoreCase(row[1].toString())) {
								mapKey = tempValue[i];
								expList.add(row[0].toString());

								if (count == 5) {
									break;
								}
								count++;
							}

						}
					}
					if (null != mapKey) {
						experienceList.put(mapKey, expList);
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return experienceList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.AdminDAO#newsLetterCreate(com.myjobkart.vo.NewsLetterVO
	 * )
	 */
	@Override
	public NewsLetterVO newsLetterCreate(NewsLetterVO vo) {

		// TODO Auto-generated method stub
		LOGGER.entry();
		long id;
		NewsLetterVO newsVo = null;
		try {
			Session session = getSession();
			id = (Long) session.save(vo);
			if (0 != id) {
				newsVo = vo;
			}
			session.flush();
		} catch (HibernateException he) {

		}

		return newsVo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#findByParams(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public NewsLetterVO findByParams(String string, String emailId) {
		// TODO Auto-generated method stub
		LOGGER.entry();
		NewsLetterVO vo = null;
		try {
			Session session = getSession();
			Criteria cr = session.createCriteria(NewsLetterVO.class);
			cr.add(Restrictions.eq(string, emailId));
			if (null != cr.list() && cr.list().size() > 0) {
				vo = (NewsLetterVO) cr.list().get(0);
			}
		} catch (HibernateException he) {
		}

		return vo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#uploadFiles(com.myjobkart.vo.CompaniesVO)
	 */
	@Override
	public boolean createCompany(List<CompanyVO> companiesVO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		LOGGER.entry();
		Session session = getSession();
		try {
			for (CompanyVO companies : companiesVO) {
				session.save(companies);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);

		} finally {
			session.flush();
			session.clear();
			LOGGER.exit();
		}
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.AdminDAO#uploadIndustry(com.myjobkart.vo.CompaniesVO)
	 */
	@Override
	public boolean createIndustry(List<IndustryVO> industryVO)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		LOGGER.entry();
		Session session = getSession();
		long IndustryId = 0;
		try {
			for (IndustryVO industry : industryVO) {
				session.save(industry);
			}

		} catch (HibernateException he) {
			he.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {
			session.flush();
			session.clear();
			LOGGER.exit();
		}
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#reteriveIndustry()
	 */
	@Override
	public List<String> reteriveIndustry() throws MyJobKartException {
		// TODO Auto-generated method stub
		LOGGER.entry();

		List<String> uploadFileList = new ArrayList<String>();

		try {

			Session session = getSession();
			Criteria reteriveIndustry = session
					.createCriteria(IndustryVO.class);
			reteriveIndustry.add(Restrictions.eq("isDeleted", false));
			List<IndustryVO> industryList = reteriveIndustry.list();
			for (IndustryVO industry : industryList) {
				EntityBO uploadFile = new EntityBO();
				uploadFileList.add(industry.getIndustryName());
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			LOGGER.exit();
		}
		return uploadFileList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#reteriveCompany()
	 */
	@Override
	public List<String> reteriveCompany() throws MyJobKartException {
		// TODO Auto-generated method stub

		LOGGER.entry();

		List<String> existingCompanyList = new ArrayList<String>();

		try {

			Session session = getSession();
			Criteria reteriveIndustry = session.createCriteria(CompanyVO.class);
			reteriveIndustry.add(Restrictions.eq("isDeleted", false));
			List<CompanyVO> industryList = reteriveIndustry.list();
			for (CompanyVO company : industryList) {
				existingCompanyList.add(company.getCompaniesName());
				existingCompanyList.add(company.getEmail());
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			LOGGER.exit();
		}
		return existingCompanyList;
	}

	@Override 
	public long uploadEmployer(List<EmployerProfileVO> employerList) throws
	MyJobKartException { 
		long uploadEmpId = 0;
		try { 

			List<EmployerProfileVO> employerVOList = new ArrayList<EmployerProfileVO>();
			List<EmployerVO> employerRegList = new ArrayList<EmployerVO>();
			for(EmployerProfileVO employerVO : employerList){

				Session session =getSession(); 
				session.saveOrUpdate(employerVO);
			}


			return  1;


		} catch (Exception e) 
		{ 
			e.printStackTrace();
		}

		return 0;
	}




	@Override 
	public EmployerVO uploadRegesitraion(EmployerVO employerVO)  { 
		try { 
			Session session =getSession(); 
			session.saveOrUpdate(employerVO);
		} catch (Exception e) 
		{ 
			e.printStackTrace();
		}

		return employerVO;
	}




	@Override 
	public EmployerLoginVO uploadLogin(EmployerLoginVO employerloginVO)  { 
		try { 
			Session session =getSession(); 
			session.saveOrUpdate(employerloginVO);
		} catch (Exception e) 
		{ 
			e.printStackTrace();
		}

		return employerloginVO;
	}




	@Override 
	public EmployerProfileVO uploadProfile(EmployerProfileVO employerProfileVO)  { 
		try { 
			Session session =getSession(); 
			session.saveOrUpdate(employerProfileVO);
		} catch (Exception e) 
		{ 
			e.printStackTrace();
		}

		return employerProfileVO;
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#adminCompanyEntityView()
	 */
	@Override
	public List<EntityBO> adminCompanyEntityView() throws MyJobKartException {
		// TODO Auto-generated method stub

		LOGGER.entry();
		List<EntityBO> adminCompanyEntityList = new ArrayList<EntityBO>();
		try {

			Session session = getSession();

			Criteria criteriaCompanyEntity = session
					.createCriteria(CompanyVO.class);
			criteriaCompanyEntity.add(Restrictions.eq("isDeleted", false));
			List<CompanyVO> companyEntityList = criteriaCompanyEntity.list();
			if (null != companyEntityList && companyEntityList.size() != 0) {
				for (CompanyVO company : companyEntityList) {
					EntityBO entity = new EntityBO();
					entity.setCompanyName(company.getCompaniesName());
					entity.setEmail(company.getEmail());
					entity.setCompaniesId(company.getCompaniesId());
					adminCompanyEntityList.add(entity);
				}
			}

		} catch (HibernateException he) {
			he.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.COMPNAY_ENTITY_VIEW_MSG + he);
			}
			throw new MyJobKartException(ErrorCodes.COMPNAY_ENTITY_VIEW_MSG,
					ErrorCodes.COMPNAY_ENTITY_VIEW_MSG);

		}
		LOGGER.exit();
		return adminCompanyEntityList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.AdminDAO#getCompanyEntity(com.myjobkart.vo.CompanyVO)
	 */
	@Override
	public CompanyVO getCompanyEntity(CompanyVO companyVO)
			throws MyJobKartException {
		LOGGER.entry();
		try {
			Session session = getSession();
			companyVO = (CompanyVO) session.get(CompanyVO.class,
					companyVO.getCompaniesId());
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.COMPNAY_ENTITY_VIEW_MSG + e);
			}
			throw new MyJobKartException(ErrorCodes.COMPNAY_ENTITY_VIEW_MSG,
					ErrorCodes.COMPNAY_ENTITY_VIEW_MSG);
		}
		LOGGER.exit();
		return companyVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.AdminDAO#adminUpdateCompanyEntity(com.myjobkart.vo.
	 * CompanyVO)
	 */
	@Override
	public long adminUpdateCompanyEntity(CompanyVO companyVO) {
		LOGGER.entry();
		long profileId = 0;
		try {
			Session session = getSession();
			session.saveOrUpdate(companyVO);
			session.flush();
			if (companyVO.getCompaniesId() != 0) {
				profileId = companyVO.getCompaniesId();
			}
		} catch (Exception exception) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("AdminDAOImpl  Update Company Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("AdminDAOImpl  Update Company Entity Failed"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return profileId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.AdminDAO#adminDeleteCompanyEntity(com.myjobkart.vo.
	 * CompanyVO)
	 */
	@Override
	public int adminDeleteCompanyEntity(CompanyVO companyVO)
			throws MyJobKartException {
		String updateToDelete = "update CompanyVO c set c.isDeleted =:isDeleted,c.modified=:modified,modifiedBy=:modifiedBy where c.companiesId=:companiesId";
		int updateStatus = 0;
		try {

			Session session = getSession();
			Query deletedQuery = session.createQuery(updateToDelete);

			deletedQuery.setParameter("isDeleted", companyVO.getIsDeleted());
			deletedQuery.setParameter("modified", companyVO.getModified());
			deletedQuery.setParameter("modifiedBy", companyVO.getModifiedBy());
			deletedQuery
			.setParameter("companiesId", companyVO.getCompaniesId());

			updateStatus = deletedQuery.executeUpdate();
		} catch (HibernateException he) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.UPDATE_FAIL_MSG + he);
			}
			throw new MyJobKartException(ErrorCodes.UPDATE_FAIL_MSG,
					ErrorCodes.UPDATE_FAIL_MSG);

		}
		return updateStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#adminIndustryEntityView()
	 */
	@Override
	public List<EntityBO> adminIndustryEntityView() {
		LOGGER.entry();
		List<EntityBO> industryEntityList = new ArrayList<EntityBO>();
		try {
			Session session = getSession();
			Criteria industryCriteria = session
					.createCriteria(IndustryVO.class);
			industryCriteria.add(Restrictions.eq("isDeleted", false));
			List<IndustryVO> industryList = industryCriteria.list();

			for (IndustryVO industryVO : industryList) {
				EntityBO entityBO = new EntityBO();
				entityBO.setCompanyName(industryVO.getIndustryName());
				entityBO.setEmail(industryVO.getEmail());
				entityBO.setCompaniesId(industryVO.getIndustryId());
				industryEntityList.add(entityBO);

			}
		} catch (Exception exception) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("AdminDAOImpl  Update Company Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("AdminDAOImpl  Update Company Entity Failed"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return industryEntityList;
	}

	@Override
	public IndustryVO getIndustryEntity(IndustryVO industryVO) {
		LOGGER.entry();
		try {
			Session session = getSession();
			industryVO = (IndustryVO) session.get(IndustryVO.class,
					industryVO.getIndustryId());
		} catch (Exception exception) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("AdminDAOImpl  Get Record Industry Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("AdminDAOImpl  Get Record Industry Entity Failed"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return industryVO;
	}

	@Override
	public long adminUpdateIndustryEntity(IndustryVO industryVO) {

		LOGGER.entry();
		long profileId = 0;
		try {
			Session session = getSession();
			session.saveOrUpdate(industryVO);
			session.flush();
			if (industryVO.getIndustryId() != 0) {
				profileId = industryVO.getIndustryId();
			}
		} catch (Exception exception) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("AdminDAOImpl  Update Industry Entity Failed:"
						+ exception.getMessage());

			}
			LOGGER.info("AdminDAOImpl  Update Industry Entity Failed"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return profileId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.myjobkart.dao.AdminDAO#adminDeleteIndustryEntity(com.myjobkart.vo
	 * .IndustryVO)
	 */
	@Override
	public int adminDeleteIndustryEntity(IndustryVO industryVO)
			throws MyJobKartException {
		String updateToDelete = "update IndustryVO c set c.isDeleted =:isDeleted,c.modified=:modified,modifiedBy=:modifiedBy where c.industryId=:industryId";
		int updateStatus = 0;
		try {

			Session session = getSession();
			Query deletedQuery = session.createQuery(updateToDelete);

			deletedQuery.setParameter("isDeleted", industryVO.getIsDeleted());
			deletedQuery.setParameter("modified", industryVO.getModified());
			deletedQuery.setParameter("modifiedBy", industryVO.getModifiedBy());
			deletedQuery.setParameter("industryId", industryVO.getIndustryId());

			updateStatus = deletedQuery.executeUpdate();
		} catch (HibernateException he) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.UPDATE_FAIL_MSG + he);
			}
			throw new MyJobKartException(ErrorCodes.UPDATE_FAIL_MSG,
					ErrorCodes.UPDATE_FAIL_MSG);

		}
		return updateStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myjobkart.dao.AdminDAO#addNewCompany(com.myjobkart.vo.CompanyVO)
	 */
	@Override
	public long addNewCompany(CompanyVO companyVO) {
		try {
			long id = (Long) getSession().save(companyVO);
			return id;
		} catch (HibernateException he) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ErrorCodes.UPDATE_FAIL_MSG + he);
			}
		}
		return 0;
	}


	@Override
	public List<EntityBO> retrievemail() {
		List<EntityBO>entityBOList=new ArrayList<EntityBO>();
		try {
			List<CompanyVO> companiesVOList = new ArrayList<CompanyVO>();

			Criteria criteria = getSession().createCriteria(CompanyVO.class);
			criteria.add(Restrictions.eq("isActiveInvitation",false));
			EntityBO entityBO=null;

			companiesVOList = criteria.list();

			if (null != companiesVOList && !companiesVOList.isEmpty()) {
				for (CompanyVO vo : companiesVOList) {
					if (null != vo.getEmail() && !vo.getEmail().isEmpty()) {
						entityBO = new EntityBO();

						entityBO.setEmail(vo.getEmail());
						entityBO.setId(vo.getCompaniesId());
						entityBOList.add(entityBO);

					}
				}
			}


		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve  email has failed:" + ex.getMessage());
			}
			LOGGER.info("Retrieve  email has failed:" + ex.getMessage());


		}

		return entityBOList;
	}

	@Override
	public boolean setemployerinvitation(long companyId) {

		try {
			String updatequery="UPDATE CompanyVO set isActiveInvitation= :isActiveInvitation where companiesId = :id";
			Session session=getSession();
			Query query=session.createQuery(updatequery);
			query.setParameter("isActiveInvitation",true);
			query.setParameter("id", companyId);
			query.executeUpdate();


			return true ;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.myjobkart.dao.AdminDAO#retriveAllWalkin()
	 */
	@Override
	public List<WalkinBO> retriveAllWalkin(WalkinBO bo) throws MyJobKartException {

		AdminDAOImpl.LOGGER.entry();
		List<WalkinVO> walkinVOList = new ArrayList<WalkinVO>();
		final List<WalkinBO> walkinBOList = new ArrayList<WalkinBO>();
		WalkinBO walkinBO;
		int count = 0;
		try {
			Session session = getSession();
			final Criteria criteria = session.createCriteria(WalkinVO.class);
			criteria.add(Restrictions.eq("isDeleted", true));
			if(null != bo.getStatus()){
				if(bo.getStatus().equals("active")){
					criteria.add(Restrictions.eq("isActive", true));
				}else{
					criteria.add(Restrictions.eq("isActive", false));
				}
			}
			walkinVOList = criteria.list();

			if ( null != walkinVOList && 0 != walkinVOList.size()){
				for(final WalkinVO walkin : walkinVOList){
					walkinBO = new WalkinBO();

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
					walkinBO.setExperiance(walkin.getMinExp() + "-"+ walkin.getMaxExp());
					walkinBO.setPostedBy(walkin.getPostedBy());
					walkinBO.setId(walkin.getJobId());
					walkinBO.setIsActive(walkin.getIsActive());
					walkinBO.setEmployerId(walkin.getEmployerLogin().getId());
					walkinBO.setSalary(walkin.getMinSalary() + "-"+ walkin.getMaxSalary());
					count = count + 1;
					walkinBO.setSNo(count);
					walkinBOList.add(walkinBO);

				}
			}
		}catch (final NullPointerException he) {
			he.printStackTrace();
			AdminDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			AdminDAOImpl.LOGGER.exit();
		}
		return walkinBOList;

	}
	
	@Override
	public List<EntityBO> retrieveIndustryList() throws MyJobKartException,
			SerialException, SQLException {
		JobSeekerDAOImpl.LOGGER.entry();
		EntityBO industryEntity;
		final List<EntityBO> industryAllList = new ArrayList<EntityBO>();
		try {
			Session session = getSession();
			Criteria cr = session.createCriteria(IndustryVO.class);
			cr.add(Restrictions.eq("isDeleted", false));
			cr.addOrder(Order.desc("industryId"));
			List<IndustryVO> industrylist = cr.list();
			if (null != industrylist && industrylist.size() > 0) {
				for (final IndustryVO industryVO : industrylist) {
					industryEntity = new EntityBO();
					industryEntity
							.setIndustryName(industryVO.getIndustryName());
					industryEntity.setIndustryId(industryVO.getIndustryId());
					industryAllList.add(industryEntity);
				}
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			JobSeekerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			JobSeekerDAOImpl.LOGGER.exit();
		}
		return industryAllList;
	}



}
