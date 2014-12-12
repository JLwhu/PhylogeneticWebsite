package web.service;

import java.util.List;

import web.exception.ServiceException;
import web.model.Role;
import web.model.User;
import web.util.Page;

/**
 * @author liuyh
 * 
 */
public interface RoleMgmtServcie {
	public Role getRoleById(String roleid) throws ServiceException;

	public List getAllRoles() throws ServiceException;

	public void removeRole(Role role) throws ServiceException;

	public void saveRole(Role role) throws ServiceException;

	public Page getRolesBySearchname(String searchname, String searchValue,
			Page page) throws ServiceException;

	public List<User> getOutOfUsersByRole(Role role)
			throws ServiceException;

	public void updateRole(Role role) throws ServiceException;

	public String getMaxRoleID() throws ServiceException;
	
	public List<Role> findRolesByUserid(Integer userid) throws ServiceException;
}
