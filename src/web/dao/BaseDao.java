/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 15, 2013
 */
package web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import web.exception.DaoException;

// TODO: Auto-generated Javadoc
/**
 * The Interface BaseDao.
 */
public interface BaseDao {
	
	/**
	 * Gets the session factory.
	 *
	 * @return the session factory
	 */
	public SessionFactory getSessionFactory();
	
	/**
	 * Sets the session factory.
	 *
	 * @param sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) ;
	
	
	
	public void save(Object object) throws DaoException;

	public void saveOrUpdate(Object object) throws DaoException;

	public void delete(Object object) throws DaoException;

	public void update(Object object) throws DaoException;


	public List query(String hql) throws DaoException;

	public List querySQL(String sql) throws DaoException ;

	public void clear(Session session) throws DaoException;

}
