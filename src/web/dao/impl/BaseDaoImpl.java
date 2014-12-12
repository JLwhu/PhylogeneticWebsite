/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 15, 2013
 */
package web.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.BaseDao;
import web.exception.DaoException;
import web.model.Hotspot;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDaoImpl.
 */
public class BaseDaoImpl implements BaseDao{
	
	/** The session factory. */
	private SessionFactory sessionFactory;
	
	private static Log log = LogFactory.getLog(BaseDaoImpl.class);

	/* (non-Javadoc)
	 * @see web.dao.BaseDao#getSessionFactory()
	 */
	public SessionFactory getSessionFactory() {
	    return this.sessionFactory;
	}
	
	/* (non-Javadoc)
	 * @see web.dao.BaseDao#setSessionFactory(org.hibernate.SessionFactory)
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	}
	
	
	public void save(Object object) throws DaoException {
		if (object == null)
			throw new DaoException("Input is null, can not save");
		Session session = sessionFactory.openSession();
		try {
			session.save(object);
		} finally {
			session.close();
		}
	}

	public void saveOrUpdate(Object object) throws DaoException {
		try {
			Session session = sessionFactory.openSession();
			session.flush();
			session.clear();
			session.saveOrUpdate(object);
			// session.evict(object);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DaoException("Save or Update Error!", e);
		}
	}

	public void delete(Object object) throws DaoException {
		if (object == null)
			throw new DaoException("Input is null, can not delete");
		Session session = sessionFactory.openSession();
		try {
			session.delete(object);
		} finally {
			session.close();
		}
	}

	public void update(Object object) throws DaoException {
		if (object == null)
			throw new DaoException("Input is null, can not update");
		Session session = sessionFactory.openSession();
		try {
			session.update(object);
		} finally {
			session.close();
		}
	}

/*	public List find(String query, Object[] values) throws DaoException {
		try {
			return find(query, values);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DaoException("Query Error!", e);
		}
	}

	public List find(String query, Object object) throws DaoException {
		List list = null;
		try {
			Session session = sessionFactory.openSession();
			Query q = session.createQuery(query);
			q.
			return q.list();
			list = find(query, object);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new DaoException("Query Error!", ex);
		}

		return list;
	}*/
	
	public List query(String hql) throws DaoException {
		try {
			Session session = sessionFactory.openSession();
			Query q = session.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DaoException("Query Error!", e);
		}

	}

	public List querySQL(String sql) throws DaoException {
		try {
			Session session = sessionFactory.openSession();
			SQLQuery q = session.createSQLQuery(sql);
			return q.list();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DaoException("Query Error!", e);
		}

	}

	/*public Object find(Class arg0, Serializable arg1) throws DaoException {
		if (arg0 == null)
			throw new DaoException("Input is null, can not query!");
		Object daoClass = null;
		try {
		//	daoClass = getSession().get(arg0, arg1);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DaoException("Query Error!", e);
		}
		return daoClass;
	}*/

	public void clear(Session session) throws DaoException {
		try {
			session.flush();
			session.clear();
			session.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DaoException("Cache clear error!", e);
		}
	}

	/*public List find(final String query, final Object[] values, final int firstRow, final int maxRow) {
		return null;
		/*return (List) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(query);
				// prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i + 1, values[i]);
					}
				}
				queryObject.setFirstResult(firstRow);
				queryObject.setMaxResults(maxRow);
				return queryObject.list();
			}
		});*/
//	}
	

}
