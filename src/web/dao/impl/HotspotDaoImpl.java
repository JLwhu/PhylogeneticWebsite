package web.dao.impl;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;

import web.dao.HotspotDao;
import web.exception.DaoException;
import web.model.Hotspot;

public class HotspotDaoImpl extends BaseDaoImpl implements HotspotDao {  //extends BaseDaoHibernate
	public Hotspot getHotspotByLocid(String locid) throws DaoException {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from Hotspot h where h.locid = ?");
			query.setString(0, locid);
			query.setMaxResults(1);

			return (Hotspot) query.uniqueResult();
		} finally {
			session.close();
		}

	}
	
	
	/*public List getHotspotListByLocidList(String locid) throws DaoException {
		return this.getHibernateTemplate().find("from hotspotReference where locid=?", locid);
	}*/

}
