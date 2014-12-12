package web.dao;

import java.util.List;

import web.exception.DaoException;
import web.model.Role;
import web.model.User;
import web.util.Page;

/**
 * ��־����ģ��Dao��
 * 
 * @author liuyh
 * @version 1.0
 * @since JDK1.5
 * 
 */
public interface LogManageDao extends BaseDao {
	public List findLogManage(Page pageList) throws DaoException;

	public List findLogByQueryName(Page page, String queryName,
			String queryValue) throws DaoException;

	public List findLogByLogname(String logName) throws DaoException;

	public User getLogByLognamePwd(String logName, String passWord)
			throws DaoException;

	public User findUserById(String userid) throws DaoException;

	public String getMaxUserID() throws DaoException;

	public List<Role> getOutOfRolesByUser(User sysuser)
			throws DaoException;

	public boolean examSingle(String username) throws DaoException;

	public void updateLogManage(Object object) throws DaoException;
	
	public int changePasswordByid(Integer operatorid, String password)
	throws DaoException;
}
