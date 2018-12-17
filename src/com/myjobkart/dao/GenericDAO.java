package com.myjobkart.dao;

import java.util.Date;
import java.util.List;

import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.vo.BODTO;
import com.myjobkart.vo.BasicEntity;
import com.myjobkart.vo.JobPostVO;

/**
 * This class to be form an common function for this application
 * 
 * @author Scube Technologies
 * @param <E>
 * 
 */

public interface GenericDAO<T extends BasicEntity> {

	/**
	 * Creates a new entity and copies properties of given entity.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */
	T create(T entity) throws MyJobKartException;

	/**
	 * Create list of new entity and copies properties of given entity.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */
	void create(List<BODTO<T>> list) throws MyJobKartException;

	/**
	 * Deletes given entity from database
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 */
	void delete(Long id) throws MyJobKartException;

	/**
	 * Deletes given list of entity from database
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */
	void deleteAll(List<BODTO<T>> entityList) throws MyJobKartException;

	/**
	 * Updates given entity to the database
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 */
	void update(T entity) throws MyJobKartException;

	/**
	 * Updates given list of entity to the database
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 */
	void update(List<BODTO<T>> list) throws MyJobKartException;

	/**
	 * Retrieve entity with given criteria.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */

	T findByCriteria(T entity) throws MyJobKartException;

	/**
	 * Retrieve entity with given name String.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */

	T findByParam(String entityParam, String entityParamValue)
			throws MyJobKartException;

	/**
	 * Retrieve entity with given date.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */

	List<T> findByDate(Date fDate, Date tDate) throws MyJobKartException;

	/**
	 * Retrieve entity with given id.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */

	T findById(Long id) throws MyJobKartException;

	/**
	 * Retrieve All entity from the database.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */
	List<T> retrieveAll() throws MyJobKartException;



}
