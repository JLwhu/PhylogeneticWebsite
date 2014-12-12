package web.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import web.dao.SysLogDao;
import web.exception.DaoException;
import web.util.Page;

public class SysLogDaoImpl extends BaseDaoImpl implements SysLogDao {

	public void deleteSysLog(Long logId) throws DaoException {
		String hql = "delete from Operatelog where operatelogid=? ";
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, logId);
		query.executeUpdate();
		this.clear(session);

	}

	/*
	 * 
	 */
	public void deleteSysLog() throws DaoException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dated = new Date();
		dated.setMonth(dated.getMonth() - 1);
		String date = df.format(dated);
		String hql = "delete from Operatelog ol where ol.operatedate <= DATE_FORMAT('"
				+ date + "','%Y-%m-%d %H:%i:%s')";
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.executeUpdate();
		this.clear(session);

	}

	public List findSysLog(Page page) throws DaoException {
		String hqlCount = "select count(*) from Operatelog  ";
		String hql = "from Operatelog  order by operatedate desc";
		Session session = getSessionFactory().openSession();
		Object object = session.createQuery(hqlCount).uniqueResult();
		int count = Integer.parseInt(object.toString());
		page.setMaxRowCount(count);
		Query query = session.createQuery(hql);
		query.setFirstResult((page.getCurPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		List logList = query.list();
		this.clear(session);
		return logList;
	}

	public List findSysLogSearch(Page page, String username, String startDate,
			String endStart, String operatetype) throws DaoException {
		StringBuffer hqlCount = new StringBuffer();
		hqlCount.append("select count(*) from Operatelog ol  where  ");
		StringBuffer hql = new StringBuffer();
		hql.append("from Operatelog ol  where  ");
		if (username != null && !username.equals("")) {
			hqlCount.append("  ol.username ='" + username + " ' and ");
			hql.append("  ol.username ='" + username + " ' and ");
		} else {
			hqlCount.append("  ");
			hql.append(" ");
		}
		if (startDate != null && !startDate.equals("")) {
			hqlCount.append("  ol.operatedate >= DATE_FORMAT('" + startDate
					+ "','%Y-%m-%d %H:%i:%s') and ");
			hql.append("  ol.operatedate >= DATE_FORMAT('" + startDate
					+ "','%Y-%m-%d %H:%i:%s') and ");
		}
		if (endStart != null && !endStart.equals("")) {
			hqlCount.append("  ol.operatedate <= DATE_FORMAT('" + endStart
					+ "','%Y-%m-%d %H:%i:%s') and ");
			hql.append("  ol.operatedate <= DATE_FORMAT('" + endStart
					+ "','%Y-%m-%d %H:%i:%s') and ");
		}
		if (operatetype != null && !operatetype.equals("0")) {
			hqlCount.append("  operatetype='" + operatetype + "'");
			hql.append("  operatetype='" + operatetype + "'");
		}
		String newhql = hql.toString().trim();
		String newhqlcount = hqlCount.toString().trim();
		if (newhql.substring(newhql.length() - 3).equals("and")) {
			newhql = newhql.substring(0, newhql.length() - 4);
			newhqlcount = newhqlcount.substring(0, newhqlcount.length() - 4);
		}
		if (newhql.substring(newhql.length() - 5, newhql.length()).equals(
				"where")) {
			newhql = newhql.substring(0, newhql.length() - 6);
			newhqlcount = newhqlcount.substring(0, newhqlcount.length() - 6);
		}
		newhql += "  order by operatedate desc";
		Session session = getSessionFactory().openSession();
		Object object = session.createQuery(newhqlcount).uniqueResult();
		int count = Integer.parseInt(object.toString());
		page.setMaxRowCount(count);
		Query query = session.createQuery(newhql);
		query.setFirstResult((page.getCurPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		List logList = query.list();
		this.clear(session);
		return logList;
	}

}
