package web.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import web.dao.GenericDAOHibernate;
import web.dao.SysLogDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.Operatelog;
import web.service.SysLogService;
import web.util.Page;

public class SysLogServiceImpl implements SysLogService {
	private SysLogDao sysLogDao;
	//protected GenericDAOHibernate genericHibernateDao;
	private Log log = LogFactory.getLog(SysLogServiceImpl.class);

	public void deleteSysLog(Long logId) throws ServiceException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 */
	/*public List findLogByCon(String hql, String[] properties, Object[] values, Page page, String orderbyStr) throws ServiceException {
		return this.findByProperty(hql, properties,values, page,orderbyStr);
	}*/ 

	/*private List findByProperty(String hql, String[] properties,
			Object[] values, Page page, String orderbyStr) {
		String qry=hql;
		if(properties!=null){
			for(String prop:properties){
				qry+=" and "+prop+"=?";
			}
		}
		if(orderbyStr!=null && !"".equals(orderbyStr)){
			qry+=" order by "+orderbyStr;
		}
		return genericHibernateDao.findByNamedParam(qry,properties,values,page);
	}*/
	
	public void delteSysLog() throws ServiceException {
		// TODO Auto-generated method stub

	}

	public Page findSysLog(Page page) throws ServiceException {
		List logList = null;
		try {
			logList = sysLogDao.findSysLog(page);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return new Page(logList, page.getMaxRowCount(), page.getCurPage(), page
				.getPageSize());

	}

	public Page findSysLogSearch(Page page, String username,
			String startDate, String endStart, String operatetype)
			throws ServiceException {
		List logList = null;
		try {
			logList = sysLogDao.findSysLogSearch(page, username, startDate,
					endStart, operatetype);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return new Page(logList, page.getMaxRowCount(), page.getCurPage(), page
				.getPageSize());

	}

	public void saveSysLog(Operatelog olog) throws ServiceException {
		try {
			sysLogDao.save(olog);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

	}
	public String getMaxID() throws ServiceException {

		String hql = "SELECT MAX(operatelogid)+1 FROM Operatelog ";
		String maxroleid = null;
		try {
			List ll = (List) sysLogDao.querySQL(hql);
			Iterator itr = ll.iterator();
			// String maxuserid = null;
			if (itr.hasNext()) {
				Object noint = itr.next();
				if (noint == null) {
					maxroleid = "1";
				} else {
					maxroleid = noint.toString();
				}
			}
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return maxroleid;

	}
	public SysLogDao getSysLogDao() {
		return sysLogDao;
	}

	public void setSysLogDao(SysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}

	/*public void setGenericHibernateDao(GenericDAOHibernate genericHibernateDao) {
		this.genericHibernateDao = genericHibernateDao;
	}*/

}
