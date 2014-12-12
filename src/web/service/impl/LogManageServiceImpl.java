package web.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.LogManageDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.Role;
import web.model.User;
import web.service.LogManageService;
import web.util.Page;

/**
 *
 * 
 * @version 1.0 07/7/27
 * @since JDK1.5
 * 
 */
public class LogManageServiceImpl implements LogManageService {
	private Log log = LogFactory.getLog(LogManageServiceImpl.class);

	private LogManageDao logManageDao;

	public LogManageDao getLogManageDao() {
		return logManageDao;
	}

	public void setLogManageDao(LogManageDao logManageDao) {
		this.logManageDao = logManageDao;
	}

	public void saveLogManage(Object object) throws ServiceException {
		try {
			if (object != null) {
				logManageDao.save(object);
			}

		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	public Page findLogManage(Page page) throws ServiceException {
		List list = null;
		try {
			list = logManageDao.findLogManage(page);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return new Page(list, page.getMaxRowCount(), page.getCurPage(), page
				.getPageSize());
	}

	public List findLogByLogname(String logName) throws ServiceException {
		List list = null;
		if (logName != null && logName != "") {
			try {
				list = logManageDao.findLogByLogname(logName);
			} catch (DaoException e) {
				log.error(e.getMessage());
				throw new ServiceException(e);
			}
		}
		return list;
	}

	public User findUserById(String userid) throws ServiceException {
		User user = null;
		if (userid != null && userid != "") {
			try {
				user = logManageDao.findUserById(userid);
			} catch (DaoException e) {
				log.error(e.getMessage());
				throw new ServiceException(e);
			}
		}

		return user;
	}

	public void updateLogManage(Object object) throws ServiceException {
		if (object != null) {
			try {
				logManageDao.update(object);
			} catch (DaoException e) {
				log.error(e.getMessage());
				throw new ServiceException(e);
			}
		}

	}

	public User getLogByLognamePwd(String logName, String passWord)
			throws ServiceException {
		User user = null;
		try {
			user = logManageDao.getLogByLognamePwd(logName, passWord);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return user;
	}

	public String getMaxUserID() throws ServiceException {
		String hql = "SELECT MAX(userid)+1 FROM User ";
		String maxUserId = null;
		try {
			List ll = (List) logManageDao.querySQL(hql);
			Iterator itr = ll.iterator();
			if (itr.hasNext()) {
				Object noint = itr.next();
				if (noint == null) {
					maxUserId = "1";
				} else {
					maxUserId = noint.toString();
				}
			}
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return maxUserId;
	}

	public void deleLogManage(String userid) throws ServiceException {
		User user;
		if (userid != null && userid != "") {
			try {
				user = logManageDao.findUserById(userid);
				logManageDao.delete(user);
			} catch (DaoException e) {
				log.error(e.getMessage());
				throw new ServiceException(e);
			}
		}

	}

	public Page findLogByQueryName(Page page, String queryName,
			String queryValue) throws ServiceException {
		List list = null;
		try {
			list = logManageDao.findLogByQueryName(page, queryName, queryValue);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return new Page(list, page.getMaxRowCount(), page.getCurPage(), page
				.getPageSize());

	}

	public boolean examSingle(String username) throws ServiceException {
		boolean flag = true;
		try {
			if (username != null && username != "") {
				flag = logManageDao.examSingle(username);
			}
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return flag;
	}

	public List<Role> getOutOfRolesByUser(User sysuser)
			throws ServiceException {
		List<Role> list = null;
		try {
			if(sysuser!=null){
				list = logManageDao.getOutOfRolesByUser(sysuser);
			}
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return list;
	}

	public int changePasswordByid(Integer operatorid, String password)
			throws ServiceException {
		int flag=0;
		try {
			flag=logManageDao.changePasswordByid(operatorid, password);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return flag;
	}
	

}
