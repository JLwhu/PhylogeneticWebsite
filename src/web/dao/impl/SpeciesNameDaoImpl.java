package web.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import web.dao.SpeciesNameDao;
import web.exception.DaoException;
import web.model.Hotspot;
import web.model.MongoSpeciesName;
import web.model.SpeciesName;
import web.util.Page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SpeciesNameDaoImpl extends BaseDaoImpl implements SpeciesNameDao {
	
	public SpeciesName getSpeciesNameByid(Integer id) throws DaoException {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesName where spceciesid = ?");
			query.setInteger(0, id);
			query.setMaxResults(1);
			return (SpeciesName) query.uniqueResult();
		} finally {
			this.clear(session);
		}
		
	};
	
	public String getSpeciesCommonNameByScientificName(String scientificName) throws DaoException {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesName where scientificname = ?");
			query.setString(0, scientificName);
			query.setMaxResults(1);
			SpeciesName res = (SpeciesName) query.uniqueResult();
			if (res!=null)
				return res.getCommonName();
			else 
				return "";
		} finally {
			this.clear(session);
		}
		
	};
	
	public String getSpeciesScientificNameByCommonName(String commonName) throws DaoException {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesName where commonname = ?");
			query.setString(0, commonName);
			query.setMaxResults(1);
			SpeciesName res = (SpeciesName) query.uniqueResult();
			if (res!=null)
				return res.getScientificName();
			else 
				return "";
		} finally {
			this.clear(session);
		}
		
	};
	public SpeciesName getSpeciesNameByScientificName(String scientificName) throws DaoException {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesName where scientificname = ?");
			query.setString(0, scientificName);
			query.setMaxResults(1);
			return (SpeciesName) query.uniqueResult();
		} finally {
			this.clear(session);
		}
		
	};
	public SpeciesName getSpeciesNameByCommonName(String commonName) throws DaoException{
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesName where commonName = ?");
			query.setString(0, commonName);
			query.setMaxResults(1);
			return (SpeciesName) query.uniqueResult();
		} finally {
			this.clear(session);
		}
		
	};

	public Map getAllSpeciesNames() throws DaoException{
		Session session = getSessionFactory().openSession();
		try {
			Map resMap = new HashMap();
			Query query = session
					.createQuery("from SpeciesName ");
			List speciesNameList = query.list();
			Iterator<SpeciesName> iterator = speciesNameList.iterator();
			while (iterator.hasNext()) {
				SpeciesName sn = iterator.next();
				resMap.put(sn.getScientificName(), sn.getCommonName());
			}
			return resMap;
		} finally {
			this.clear(session);
		}
	}
	
	public List getSpeciesList(Page page) throws DaoException {
		//
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(*) ");
		sqlCount.append("from SpeciesName ");

		sql.append("from SpeciesName  ");

		String newsql = sql.toString().trim();
		String newsqlcount = sqlCount.toString().trim();
		//
		Session session = getSessionFactory().openSession();
		try {
			Object object = new Object();
			object = session.createQuery(newsqlcount).uniqueResult();

			int inter = 0;
			if (object != null) {
				inter = Integer.parseInt(object.toString());
			}
			// List<?> list = new ArrayList();
			Query query = session.createQuery(newsql);
			page.setMaxRowCount(inter);
			query.setFirstResult((page.getCurPage() - 1) * page.getPageSize());
			query.setMaxResults(page.getPageSize());
			List SpeciesList = query.list();

			// for(int i=0;i<SpeciesList.size();i++){
			//
			// }
			// list = query.list();
			return SpeciesList;
		} finally {
			this.clear(session);
		}


	}
	
	public List getSpeciesSciStartingWith(String spe) throws DaoException {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesName where scientificname like ?");
			query.setString(0, spe);
			List SpeciesList = query.list();
			return SpeciesList;
		} finally {
			this.clear(session);
		}

	}
	
	public List getSpeciesComStartingWith(String spe) throws DaoException {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesName where commonName like ?");
			query.setString(0, spe);
			List SpeciesList = query.list();
			return SpeciesList;
		} finally {
			this.clear(session);
		}

	}

	public Map getAllSpeciesScientificNameMap() throws DaoException {
		Map resMap = new HashMap();
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesName");
			List speciesNameList = query.list();
			
			Iterator<SpeciesName> iterator = speciesNameList.iterator();
			while (iterator.hasNext()) {
				SpeciesName sn = iterator.next();
				resMap.put(sn.getSpeciesid(), sn.getScientificName());
			}
		} finally {
			this.clear(session);
		}
		return resMap;
	}

}
