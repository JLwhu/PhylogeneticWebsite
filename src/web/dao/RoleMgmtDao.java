package web.dao;

import web.exception.DaoException;
import web.model.Role;
import web.model.User;
import web.util.Page;

import java.util.List;

/**
 * @author liuyh
 * 
 */
public interface RoleMgmtDao extends BaseDao {
	public Role getRoleById(String roleid) throws DaoException;

	public List getAllRoles() throws DaoException;

	public void removeRole(Role role) throws DaoException;

	public void saveRole(Role role) throws DaoException;

	public String getMaxRoleID() throws DaoException;

	public List getRolesBySearchname(String searchname, String searchValue,
			Page page) throws DaoException;


	public void updateRole(Role role) throws DaoException;
	
	public List<Role> findRolesByUserid(Integer userid) throws DaoException;
}
