package com.myjobkart.service;

import java.util.Date;
import java.util.List;

import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.vo.BODTO;

public interface GenericService<E> {

	/**
	 * Creates a new entity and copies properties of given entity.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */
	E create(E entity) throws MyJobKartException;

	/**
	 * Create list of new entity and copies properties of given entity.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */
	void create(List<BODTO<E>> list) throws MyJobKartException;

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
	void deleteAll(List<E> entityList) throws MyJobKartException;

	/**
	 * Updates given entity to the database
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 */
	void update(E entity) throws MyJobKartException;

	/**
	 * Updates given list of entity to the database
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 */
	void update(List<BODTO<E>> list) throws MyJobKartException;

	/**
	 * Retrieve entity with given criteria.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */

	E findByCriteria(E entity) throws MyJobKartException;

	/**
	 * Retrieve entity with given name String.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */

	E findByParam(String entityParam, String entityParamValue)
			throws MyJobKartException;

	/**
	 * Retrieve entity with given date.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */

	List<E> findByDate(Date fDate, Date tDate) throws MyJobKartException;

	/**
	 * Retrieve entity with given id.
	 * 
	 * @param entity
	 * @throws MyJobKartException
	 *             ;
	 */

	E findById(Long long1) throws MyJobKartException;

}
