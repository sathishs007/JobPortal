package com.megatech.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megatech.exception.HeloclinicException;
import com.megatech.exception.JLogger;
import com.megatech.model.AdminLoginBO;
import com.megatech.model.CustomerBO;
import com.megatech.model.InvoiceCustomerBO;
import com.megatech.model.ServiceBO;
import com.megatech.model.SupplierBO;
import com.megatech.model.SupplierInvoiceBO;
import com.megatech.utils.EncryptAndDecrypt;
import com.megatech.utils.SuccessMsg;
import com.megatech.vo.AdminLoginVO;
import com.megatech.vo.CustomerVO;
import com.megatech.vo.InvoiceCustomerVO;
import com.megatech.vo.ServiceVO;
import com.megatech.vo.SupplierAllInvoiceVO;
import com.megatech.vo.SupplierInvoiceVO;
import com.megatech.vo.SupplierVO;

@Repository("AdminDao")
public class AdminDaoImpl extends AbstractDao implements AdminDao {
	boolean isEmail;
	private final JLogger LOGGER = JLogger.getLogger(AdminDaoImpl.class);

	String salt = "this is a simple clear salt";

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	public AdminLoginBO authenticate(String email, String password)
			throws HeloclinicException {
		LOGGER.entry();
		AdminLoginBO loginBO = null;
		try {

			AdminLoginVO loginVO = adminSignIn(email);
			if (null != loginVO) {
				String passwordDec = EncryptAndDecrypt.decrypt(
						loginVO.getPassword(), salt);
				if (passwordDec.equals(password)) {
					loginBO = new AdminLoginBO();
					loginBO.setEmailAddress(loginVO.getEmailAddress());
					loginBO.setId(loginVO.getId());
					loginBO.setStatus(true);
					loginBO.setUserType(loginVO.getUserType());
					return loginBO;
				} else {
					loginBO = new AdminLoginBO();
					loginBO.setStatus(false);
				}
			} else {
				loginBO = new AdminLoginBO();
				loginBO.setStatus(false);
			}
		} catch (HeloclinicException he) {
			LOGGER.debug(he.getMessage() + he);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.exit();
		return loginBO;
	}

	@Override
	public AdminLoginVO forgotPassword(AdminLoginBO loginBO)
			throws HeloclinicException {
		LOGGER.entry();
		AdminLoginVO adminLoginVO = null;
		try {
			Criteria criteria = getSession().createCriteria(AdminLoginVO.class);
			criteria.add(Restrictions.eq("emailAddress",
					loginBO.getEmailAddress()));
			if (null != criteria.list() && criteria.list().size() > 0) {
				adminLoginVO = (AdminLoginVO) criteria.list().get(0);
			}
		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return adminLoginVO;
	}

	@Override
	public AdminLoginVO changePassword(AdminLoginVO loginVO)
			throws HeloclinicException {
		LOGGER.entry();
		int result = 0;
		String changePasswordQuery = "UPDATE AdminLoginVO S set"
				+ " S.password = :password"
				+ " WHERE S.emailAddress = :emailAddress";
		// String changePasswordQuery =
		// "UPDATE PatientRegistrationVO E set E.password = :password , E.confirmPassword = :cPassword  WHERE E.emailAddress = :emailAddress";
		try {
			Query query = getSession().createQuery(changePasswordQuery);
			query.setParameter("password", loginVO.getPassword());
			query.setParameter("emailAddress", loginVO.getEmailAddress());
			result = query.executeUpdate();
			if (result != 0) {
				return loginVO;
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}

		LOGGER.exit();
		return null;
	}

	@Override
	public List<CustomerBO> allCustomer() throws IllegalAccessException,
			InvocationTargetException {
		LOGGER.entry();
		List<CustomerVO> customerVO = new ArrayList<CustomerVO>();
		List<CustomerBO> customeBOList = new ArrayList<CustomerBO>();
		CustomerBO bo;
		try {
			Criteria criteria = getSession().createCriteria(CustomerVO.class);
			criteria.add(Restrictions.eq("isDeleted", true));
			customerVO = criteria.list();
			if (null != customerVO && 0 != customerVO.size()) {
				for (CustomerVO vo : customerVO) {
					bo = new CustomerBO();
					bo.setCustomerName(vo.getCustomerName());
					bo.setCompanyName(vo.getCompanyName());
					bo.setCustomerId(vo.getCustomerId());
					bo.setEmailAddress(vo.getEmailAddress());
					bo.setAddress(vo.getAddress());
					bo.setMobileNumber(vo.getMobileNumber());
					customeBOList.add(bo);
				}
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		LOGGER.exit();
		return customeBOList;

	}

	@Override
	public long createCustomerInvoice(InvoiceCustomerVO vo) {
		LOGGER.entry();
		long id = 0;
		try {
			Session session = getSession();
			id = (Long) session.save(vo);
			session.flush();
		} catch (HibernateException he) {

		}
		LOGGER.exit();
		return id;

	}

	@Override
	public String supplierIdAutoGenerated() {
		LOGGER.entry();
		String supplierId = null;
		List<SupplierVO> supplierAutoList = new ArrayList<SupplierVO>();
		Criteria criteria;
		try {
			criteria = getSession().createCriteria(SupplierVO.class);
			supplierAutoList = criteria.list();
			for (SupplierVO supplierVO : supplierAutoList) {
				supplierId = supplierVO.getSupplierId();
			}
			if (supplierId != null) {
				List<Integer> idList = new ArrayList<Integer>();
				idList.add(Integer.parseInt(supplierId.substring(3,
						supplierId.length())));
				supplierId = "SUP" + (Collections.max(idList) + 1);
			}

			else {
				supplierId = "SUP1001";
			}
		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return supplierId;
	}

	@Override
	public String createSupplier(SupplierVO supplierVO)
			throws HeloclinicException {
		LOGGER.entry();
		String supplierId = null;
		try {
			Session session = getSession();
			supplierId = (String) session.save(supplierVO);
			session.flush();
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.exit();
		return supplierId;
	}

	@Override
	public List<SupplierBO> retrieveSuppliers() throws HeloclinicException {
		LOGGER.entry();
		List<SupplierBO> supplierBOList = new ArrayList<SupplierBO>();
		List<SupplierVO> supplierVOList = new ArrayList<SupplierVO>();
		Criteria criteria;
		SupplierBO supplierBO;
		try {
			criteria = getSession().createCriteria(SupplierVO.class);
			criteria.add(Restrictions.eq("isDeleted", true));
			supplierVOList = criteria.list();
			for (SupplierVO supplierVO : supplierVOList) {
				supplierBO = new SupplierBO();
				BeanUtils.copyProperties(supplierVO, supplierBO);
				supplierBOList.add(supplierBO);
			}

		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return supplierBOList;
	}

	@Override
	public List<InvoiceCustomerBO> retrieveCustomerInvoice() {
		LOGGER.entry();
		List<InvoiceCustomerBO> invoiceCustomerBOList = new ArrayList<InvoiceCustomerBO>();
		List<InvoiceCustomerVO> invoiceCustomerVOList = new ArrayList<InvoiceCustomerVO>();
		Criteria criteria;
		InvoiceCustomerBO invoiceCustomerBO;
		try {
			criteria = getSession().createCriteria(InvoiceCustomerVO.class);
			// criteria.add(Restrictions.eq("isDeleted", true));
			invoiceCustomerVOList = criteria.list();
			for (InvoiceCustomerVO vo : invoiceCustomerVOList) {
				invoiceCustomerBO = new InvoiceCustomerBO();
				BeanUtils.copyProperties(vo, invoiceCustomerBO);
				invoiceCustomerBO.setId(vo.getInvoiceCustomer());
				invoiceCustomerBOList.add(invoiceCustomerBO);
			}

		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return invoiceCustomerBOList;

	}

	@Override
	public List<SupplierInvoiceBO> retrieveSupplierInvoice(
			SupplierInvoiceBO supplierInvoiceBO) {
		LOGGER.entry();
		List<SupplierInvoiceBO> supplierInvoiceBOList = new ArrayList<SupplierInvoiceBO>();
		List<SupplierInvoiceVO> supplierInvoiceVOList = new ArrayList<SupplierInvoiceVO>();
		Criteria criteria;
		SupplierInvoiceBO invoiceCustomerBO;
		try {
			criteria = getSession().createCriteria(SupplierInvoiceVO.class);
			criteria.add(Restrictions.eq("invoiceNumber",
					supplierInvoiceBO.getInvoiceNumber()));
			criteria.add(Restrictions.eq("isDeleted", true));
			supplierInvoiceVOList = criteria.list();
			for (SupplierInvoiceVO vo : supplierInvoiceVOList) {
				invoiceCustomerBO = new SupplierInvoiceBO();
				BeanUtils.copyProperties(vo, invoiceCustomerBO);
				invoiceCustomerBO.setId(vo.getId());
				invoiceCustomerBO.setInvoiceAmount(vo.getInvoiceAmount());
				invoiceCustomerBO.setSupplierName(vo.getSupplierVO()
						.getSupplierName());
				invoiceCustomerBO.setSupplierId(vo.getSupplierVO()
						.getSupplierId());
				invoiceCustomerBO
						.setEmail(vo.getSupplierVO().getEmailAddress());
				invoiceCustomerBO.setSupplierCompanyName(vo.getSupplierVO()
						.getCompanyName());
				invoiceCustomerBO.setMobileNumber(vo.getSupplierVO()
						.getMobileNumber());
				invoiceCustomerBO.setAddress(vo.getSupplierVO().getAddress());
				invoiceCustomerBO.setIsDeleted(vo.isDeleted());
				invoiceCustomerBO.setQuantity(vo.getQuantity());
				supplierInvoiceBOList.add(invoiceCustomerBO);

			}

		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return supplierInvoiceBOList;

	}

	@Override
	public SupplierBO editSupplier(SupplierVO supplierVO)
			throws HeloclinicException {
		LOGGER.entry();
		SupplierBO supplierBO = null;
		try {
			supplierBO = new SupplierBO();
			supplierVO = (SupplierVO) saveorupdate(supplierVO);
			BeanUtils.copyProperties(supplierVO, supplierBO);
			supplierBO.setResponse(SuccessMsg.UPDATE_SUCCESS);

		} catch (HeloclinicException he) {
			LOGGER.debug(he.getMessage() + he);
			supplierBO.setErrorCode(he.getErrorCode());
			supplierBO.setErrorMessage(he.getErrorMessage());
		}

		LOGGER.exit();
		return supplierBO;
	}

	@Override
	public int deleteSupplier(SupplierVO supplierVO) throws HeloclinicException {
		LOGGER.entry();
		int result = 0;
		String deleteQuery = "UPDATE SupplierVO S set"
				+ " S.isDeleted = :isDeleted," + "S.modifiedBy = :modifiedBy,"
				+ "S.modified=:modified" + " WHERE S.supplierId = :supplierId";
		try {

			Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDeleted", supplierVO.getIsDeleted());
			query.setParameter("modifiedBy", supplierVO.getModifiedBy());
			query.setParameter("modified", supplierVO.getModified());
			query.setParameter("supplierId", supplierVO.getSupplierId());
			result = query.executeUpdate();
		} catch (HibernateException he) {

			LOGGER.debug(he.getMessage() + he);
		}

		LOGGER.exit();
		return result;
	}

	@Override
	public String createCustomer(CustomerVO customerVO)
			throws HeloclinicException {
		LOGGER.entry();
		String customerId = null;
		try {
			Session session = getSession();
			customerId = (String) session.save(customerVO);
			session.flush();
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.exit();
		return customerId;
	}

	@Override
	public List<CustomerBO> retrieveCustomers() throws HeloclinicException {
		LOGGER.entry();
		List<CustomerBO> customerBOList = new ArrayList<CustomerBO>();
		List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
		Criteria criteria;
		CustomerBO customerBO;
		try {
			Session session = getSession();
			criteria = session.createCriteria(CustomerVO.class);
			criteria.add(Restrictions.eq("isDeleted", true));
			customerVOList = criteria.list();
			for (CustomerVO customerVO : customerVOList) {
				customerBO = new CustomerBO();
				BeanUtils.copyProperties(customerVO, customerBO);
				customerBOList.add(customerBO);
			}

		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return customerBOList;
	}

	@Override
	public CustomerBO editCustomer(CustomerVO customerVO)
			throws HeloclinicException {
		LOGGER.entry();
		CustomerBO customerBO = null;
		try {
			customerBO = new CustomerBO();
			customerVO = (CustomerVO) saveorupdate(customerVO);
			BeanUtils.copyProperties(customerVO, customerBO);
			customerBO.setResponse(SuccessMsg.UPDATE_SUCCESS);

		} catch (HeloclinicException he) {
			LOGGER.debug(he.getMessage() + he);
			customerBO.setErrorCode(he.getErrorCode());
			customerBO.setErrorMessage(he.getErrorMessage());
		}

		LOGGER.exit();
		return customerBO;
	}

	@Override
	public int deleteCustomer(CustomerVO customerVO) throws HeloclinicException {
		LOGGER.entry();
		int result = 0;
		String deleteQuery = "UPDATE CustomerVO C set"
				+ " C.isDeleted = :isDeleted," + "C.modifiedBy = :modifiedBy,"
				+ "C.modified=:modified" + " WHERE C.customerId = :customerId";
		try {
			System.out.println("Insid3e the dao delete....");
			Session session = getSession();
			Query query = session.createQuery(deleteQuery);
			query.setParameter("isDeleted", customerVO.getIsDeleted());
			query.setParameter("modifiedBy", customerVO.getModifiedBy());
			query.setParameter("modified", customerVO.getModified());
			query.setParameter("customerId", customerVO.getCustomerId());
			result = query.executeUpdate();
		} catch (HibernateException he) {

			LOGGER.debug(he.getMessage() + he);
		}

		LOGGER.exit();
		return result;
	}

	@Override
	public String customerIdAutoGenerated() {
		LOGGER.entry();
		String customerId = null;
		List<CustomerVO> customerAutoList = new ArrayList<CustomerVO>();
		Criteria criteria;
		try {

			Session session = getSession();
			criteria = session.createCriteria(CustomerVO.class);
			session.flush();
			customerAutoList = criteria.list();
			for (CustomerVO customerVO : customerAutoList) {
				customerId = customerVO.getCustomerId();
			}
			if (customerId != null) {
				List<Integer> idList = new ArrayList<Integer>();
				idList.add(Integer.parseInt(customerId.substring(3,
						customerId.length())));
				customerId = "CUS" + (Collections.max(idList) + 1);
			}

			else {
				customerId = "CUS1001";
			}
		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return customerId;
	}

	@Override
	public long createSupplierInvoice(SupplierInvoiceVO vo)
			throws HeloclinicException {
		LOGGER.entry();
		long id = 0;
		try {
			Session session = getSession();
			id = (Long) session.save(vo);
			session.flush();
		} catch (HibernateException he) {

		}
		LOGGER.exit();
		return id;
	}

	@Override
	public int deleteSupplierInvoiceItems(SupplierInvoiceVO supplierVO)
			throws HeloclinicException {
		LOGGER.entry();
		int result = 0;
		String deleteQuery = "UPDATE SupplierInvoiceVO S set"
				+ " S.isDeleted = :isDeleted," + "S.modifiedBy = :modifiedBy,"
				+ "S.modified=:modified" + " WHERE S.id = :id";
		try {

			Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDeleted", supplierVO.isDeleted());
			query.setParameter("modifiedBy", supplierVO.getModifiedBy());
			query.setParameter("modified", supplierVO.getModified());
			query.setParameter("id", supplierVO.getId());
			result = query.executeUpdate();
		} catch (HibernateException he) {

			LOGGER.debug(he.getMessage() + he);
		}

		LOGGER.exit();
		return result;
	}

	@Override
	public SupplierInvoiceBO editSupplierInvoiceItems(
			SupplierInvoiceVO supplierVO) throws HeloclinicException {
		LOGGER.entry();
		SupplierInvoiceBO supplierBO = null;
		try {
			supplierBO = new SupplierInvoiceBO();
			supplierVO = (SupplierInvoiceVO) saveorupdate(supplierVO);
			BeanUtils.copyProperties(supplierVO, supplierBO);
			supplierBO.setResponse(SuccessMsg.UPDATE_SUCCESS);

		} catch (HeloclinicException he) {
			LOGGER.debug(he.getMessage() + he);
			supplierBO.setErrorCode(he.getErrorCode());
			supplierBO.setErrorMessage(he.getErrorMessage());
		}

		LOGGER.exit();
		return supplierBO;
	}

	@Override
	public long createSupplierAllInvoice(SupplierAllInvoiceVO vo)
			throws HeloclinicException {
		LOGGER.entry();
		long id = 0;
		try {
			Session session = getSession();
			id = (Long) session.save(vo);
			session.flush();
		} catch (HibernateException he) {

		}
		LOGGER.exit();
		return id;
	}

	@Override
	public List<SupplierInvoiceBO> retrieveAllSupplierInvoice() {
		LOGGER.entry();
		List<SupplierInvoiceBO> supplierInvoiceBOList = new ArrayList<SupplierInvoiceBO>();
		List<SupplierAllInvoiceVO> supplierInvoiceVOList = new ArrayList<SupplierAllInvoiceVO>();
		Criteria criteria;
		SupplierInvoiceBO invoiceCustomerBO;
		try {
			criteria = getSession().createCriteria(SupplierAllInvoiceVO.class);
			criteria.add(Restrictions.eq("isDeleted", true));
			supplierInvoiceVOList = criteria.list();
			for (SupplierAllInvoiceVO vo : supplierInvoiceVOList) {
				invoiceCustomerBO = new SupplierInvoiceBO();
				BeanUtils.copyProperties(vo, invoiceCustomerBO);
				invoiceCustomerBO.setId(vo.getId());
				invoiceCustomerBO.setTotal(vo.getInvoiceAmount());
				invoiceCustomerBO.setSupplierName(vo.getSupplierVO()
						.getSupplierName());
				invoiceCustomerBO.setSupplierId(vo.getSupplierVO()
						.getSupplierId());
				invoiceCustomerBO
						.setEmail(vo.getSupplierVO().getEmailAddress());
				invoiceCustomerBO.setSupplierCompanyName(vo.getSupplierVO()
						.getCompanyName());
				invoiceCustomerBO.setMobileNumber(vo.getSupplierVO()
						.getMobileNumber());
				invoiceCustomerBO.setAddress(vo.getSupplierVO().getAddress());
				invoiceCustomerBO.setIsDeleted(vo.isDeleted());
				// invoiceCustomerBO.setQuantity(vo.get)
				supplierInvoiceBOList.add(invoiceCustomerBO);

			}

		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return supplierInvoiceBOList;
	}

	@Override
	public SupplierInvoiceBO updateAllSupplierInvoices(
			SupplierAllInvoiceVO invoiceVO) {
		LOGGER.entry();
		SupplierInvoiceBO supplierBO = null;
		try {
			supplierBO = new SupplierInvoiceBO();
			invoiceVO = (SupplierAllInvoiceVO) saveorupdate(invoiceVO);
			BeanUtils.copyProperties(invoiceVO, supplierBO);
			supplierBO.setResponse(SuccessMsg.UPDATE_SUCCESS);

		} catch (HeloclinicException he) {
			LOGGER.debug(he.getMessage() + he);
			supplierBO.setErrorCode(he.getErrorCode());
			supplierBO.setErrorMessage(he.getErrorMessage());
		}

		LOGGER.exit();
		return supplierBO;
	}

	// mobile service
	@Override
	public List<ServiceBO> retrieveService() throws HeloclinicException,
			SerialException, SQLException {
		LOGGER.entry();
		List<ServiceBO> serviceBOList = new ArrayList<ServiceBO>();
		List<ServiceVO> serviceVOList = new ArrayList<ServiceVO>();
		Criteria criteria;
		ServiceBO serviceBO;
		try {
			Session session = getSession();
			criteria = session.createCriteria(ServiceVO.class);
			serviceVOList = criteria.list();
			for (ServiceVO serviceVO : serviceVOList) {
				serviceBO = new ServiceBO();
				BeanUtils.copyProperties(serviceVO, serviceBO);
				if(serviceVO.getFileupload()!=null){
					byte imageData[] =	serviceVO.getFileupload().getBytes(1, (int)serviceVO.getFileupload().length());
					String imageDataString = encodeImage(imageData);
					serviceBO.setImag(imageDataString);
					}
				serviceBOList.add(serviceBO);
			}

		} catch (Exception he) {
he.printStackTrace();
		}

		LOGGER.exit();
		return serviceBOList;
	}

	// mobile service
	@Override
	public List<ServiceBO> searchService(String searchParam)
			throws HeloclinicException, SerialException, SQLException {
		LOGGER.entry();
		List<ServiceBO> serviceBOList = new ArrayList<ServiceBO>();
		List<ServiceVO> serviceVOList = new ArrayList<ServiceVO>();
		Criteria criteria;
		ServiceBO serviceBO;

		try {
			String search = "from ServiceVO s where s.customerName like :keyWord OR s.technician like :keyWord OR  s.customerVO.customerId =:keyWords OR s.jobNo =:keyWords OR s.serviceId =:keyWords OR  s.dateOfServics =:keyWords";

			Session session = getSession();
			Query query = session.createQuery(search);
			query.setParameter("keyWord", "%" + searchParam + "%");
			query.setParameter("keyWords", searchParam);
			serviceVOList = query.list();
			for (ServiceVO serviceVO : serviceVOList) {
				serviceBO = new ServiceBO();
				BeanUtils.copyProperties(serviceVO, serviceBO);
				if(serviceVO.getFileupload()!=null){
					byte imageData[] =	serviceVO.getFileupload().getBytes(1, (int)serviceVO.getFileupload().length());
					String imageDataString = encodeImage(imageData);
					serviceBO.setImag(imageDataString);
					}
				serviceBOList.add(serviceBO);
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		}

		LOGGER.exit();
		return serviceBOList;
	}

	// mobile service
	@Override
	public List<ServiceBO> searchReport(String searchParam)
			throws HeloclinicException, SerialException, SQLException {
		LOGGER.entry();
		List<ServiceBO> serviceBOList = new ArrayList<ServiceBO>();
		List<ServiceVO> serviceVOList = new ArrayList<ServiceVO>();
		Criteria criteria;
		ServiceBO serviceBO;
		try {

			// String
			// search="SELECT * s.customerName, s.jobNo, s.dateOfServics, s.description,s.refNo from ServiceVO as s left join InvoiceCustomerVO as i on  s.customerVO.customerId=i.invoiceCustomer where s.customerName like :keyWord or s.serviceId=:keyWords or s.jobNo=:keyWords or s.dateOfServics=:keyWords";
			String search = "SELECT s.customer_name, s.job_no, s.date_of_servics, s.description,s.ref_no ,i.inv_no,i.created_date from service as s left join invoice_customer as i on  s.customer_id=i.cust_id where s.customer_name like '"
					+ searchParam
					+ "%' or s.service_id='"
					+ searchParam
					+ "' or s.job_no='"
					+ searchParam
					+ "'or s.date_of_servics='" + searchParam + "'  ";
			Session session = getSession();
			Query query = session.createSQLQuery(search);
			// query.setParameter("keyWord","%" + searchParam + "%");
			// query.setParameter("keyWords", searchParam );
			List<ServiceBO> serviceBOLists = query.list();
			// serviceBOList = query.list();
			serviceBOList = serviceBOLists;
			// serviceBOList=result;
			/*
			 * for (ServiceVO serviceVO : serviceVOList) { serviceBO = new
			 * ServiceBO(); BeanUtils.copyProperties(serviceVO, serviceBO);
			 * serviceBOList.add(serviceBO); }
			 */

		} catch (HibernateException he) {
			he.printStackTrace();
		}

		LOGGER.exit();
		return serviceBOList;
	}
	
	@Override
	public int addService(ServiceVO serviceVO)
			throws HeloclinicException {
		LOGGER.entry();
		int customerId = 0;
		try {
			Session session = getSession();
			/*CustomerVO customerVO=new CustomerVO();
			customerVO.setCustomerId("Cus1001");
			serviceVO.setCustomerVO(customerVO);*/
			customerId = (Integer) session.save(serviceVO);
			//session.flush();
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.exit();
		return customerId;
	}
	@Override
	public List<CustomerBO> checkCustomerName(String customerName) throws HeloclinicException {
		LOGGER.entry();
		List<CustomerBO> customerBOList = new ArrayList<CustomerBO>();
		List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
		Criteria criteria;
		CustomerBO customerBO;
		try {
			Session session = getSession();
			criteria = session.createCriteria(CustomerVO.class);
			criteria.add(Restrictions.eq("isDeleted", true));
			criteria.add(Restrictions.eq("customerName", customerName));
			customerVOList = criteria.list();
			for (CustomerVO customerVO : customerVOList) {
				customerBO = new CustomerBO();
				BeanUtils.copyProperties(customerVO, customerBO);
				customerBOList.add(customerBO);
			}

		} catch (HibernateException he) {

		}

		LOGGER.exit();
		return customerBOList;
	}

	
	/**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }
}