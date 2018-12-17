package com.megatech.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.megatech.exception.HeloclinicException;
import com.megatech.exception.JLogger;
import com.megatech.utils.ErrorCodes;
import com.megatech.vo.AdminLoginVO;

public abstract class AbstractDao {
	private final JLogger log = JLogger.getLogger(AbstractDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public void persist(Object entity) {
		try{
		getSession().persist(entity);
		}catch(HibernateException he){
			he.printStackTrace();
		}
	}

	public long save(Object entity) throws HeloclinicException {
		log.entry();
		long id = 0;
		try {
			id = (Long) getSession().save(entity);
			
		} catch (HibernateException he) {
			he.printStackTrace();
			log.debug(ErrorCodes.PT_REG_FAIL_MSG + he);
			throw new HeloclinicException(ErrorCodes.PT_REG_FAIL,
					ErrorCodes.PT_REG_FAIL_MSG);

		}
		log.exit();
		return id;
	}

	public Object saveorupdate(Object entity) throws HeloclinicException {
		log.entry();

		try {
			Session session=getSession();
			session.saveOrUpdate(entity);
			session.flush();
           
		} catch (Exception he) {
			he.printStackTrace();
			log.debug(ErrorCodes.PT_UP_FAIL_MSG + he);
			throw new HeloclinicException(ErrorCodes.PT_UP_FAIL,
					ErrorCodes.PT_UP_FAIL_MSG);

		}
		log.exit();
		return entity;

	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	
	public AdminLoginVO adminSignIn(String emailAddress)
			throws HeloclinicException {
		log.entry();
		AdminLoginVO adminLoginVO = null;
		try {
			Criteria cr = getSession().createCriteria(AdminLoginVO.class);
			cr.add(Restrictions.eq("isDeleted", true));
			cr.add(Restrictions.eq("emailAddress", emailAddress));
			if (null != cr.list() && cr.list().size() > 0) {
				adminLoginVO = (AdminLoginVO) cr.list().get(0);
			}

		} catch (HibernateException he) {
			he.printStackTrace();
			log.debug(ErrorCodes.PT_LOG_FAIL_MSG + he);
			throw new HeloclinicException(ErrorCodes.PT_LOG_FAIL,
					ErrorCodes.PT_LOG_FAIL_MSG);
		}
		log.exit();
		return adminLoginVO;

	}

}
