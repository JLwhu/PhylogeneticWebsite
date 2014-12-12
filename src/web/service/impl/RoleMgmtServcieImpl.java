package web.service.impl;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import web.dao.RoleMgmtDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.Role;
import web.model.User;
import web.service.RoleMgmtServcie;
import web.util.Page;

/**
 * @author liuyh
 *
 */
public class RoleMgmtServcieImpl implements RoleMgmtServcie {
	private Log log = LogFactory.getLog(RoleMgmtServcieImpl.class);

	private RoleMgmtDao roleMgmtDao;

	public List getAllRoles() throws ServiceException {
		List list = null;
		try {
			list = roleMgmtDao.getAllRoles();
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return list;
	}

	public List<User> getOutOfUsersByRole(Role role)
			throws ServiceException {
		List<User> list = null;
//		try {
//			list = roleMgmtDao.getOutOfUsersByRole(role);
//		} catch (DaoException e) {
//			log.error(e.getMessage());
//			throw new ServiceException(e);
//		}
		return list;
	}

	public Role getRoleById(String roleid) throws ServiceException {
		Role role = null;
		try {
			role = roleMgmtDao.getRoleById(roleid);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return role;
	}

	public Page getRolesBySearchname(String searchname, String searchValue,
			Page page) throws ServiceException {

		List list = null;
		try {
			list = roleMgmtDao.getRolesBySearchname(searchname, searchValue,
					page);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

		return new Page(list, page.getMaxRowCount(), page.getCurPage(), page
				.getPageSize());
	}

	public void removeRole(Role role) throws ServiceException {
		try {
			roleMgmtDao.removeRole(role);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

	}

	public void saveRole(Role role) throws ServiceException {
		try {
			roleMgmtDao.saveRole(role);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

	}

	public void updateRole(Role role) throws ServiceException {
		try {
			roleMgmtDao.updateRole(role);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

	}

	public RoleMgmtDao getRoleMgmtDao() {
		return roleMgmtDao;
	}

	public void setRoleMgmtDao(RoleMgmtDao roleMgmtDao) {
		this.roleMgmtDao = roleMgmtDao;
	}

	public String getMaxRoleID() throws ServiceException {

		String hql = "SELECT MAX(roleid)+1 FROM Role ";
		String maxroleid = null;
		try {
			List ll = (List) roleMgmtDao.querySQL(hql);
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

	public List<Role> findRolesByUserid(Integer userid)
			throws ServiceException {
		List<Role> list = null;
		try {
			list = roleMgmtDao.findRolesByUserid(userid);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return list;
	}

}
