package web.service;
import java.util.List;

import web.exception.ServiceException;
import web.model.Role;
import web.model.User;
import web.util.Page;

/**
 * @author liuyh
 * 日志记录
 */
public interface LogManageService {
	public void saveLogManage(Object object) throws ServiceException;
	public void updateLogManage(Object object) throws ServiceException;
	public Page findLogManage(Page page) throws ServiceException;
	public Page findLogByQueryName(Page page,String queryName,String queryValue) throws ServiceException;
	public List findLogByLogname(String logName) throws ServiceException;
	public User getLogByLognamePwd(String logName,String password) throws ServiceException;
	public User findUserById(String userid) throws ServiceException;
	public String getMaxUserID() throws ServiceException;
	public void deleLogManage(String userid)throws ServiceException;
	public List<Role> getOutOfRolesByUser(User sysuser)throws ServiceException;
	public boolean examSingle(String username) throws ServiceException;
	public int changePasswordByid(Integer operatorid,String password) throws ServiceException ;
}
