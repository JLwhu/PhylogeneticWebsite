package web.dao;


import java.util.List;

import web.exception.DaoException;
import web.model.Menu;
import web.model.Role;
import web.util.Page;

/**
 * @author liuyh
 * 
 */
public interface MenuMgmtDao extends BaseDao {
	public List findAllMenupage(Page page) throws DaoException;
	public Menu findStormenuById(String menuId)throws DaoException;
	public List findAllMenuList()throws DaoException;
	public Menu findStormenuByUrl(String menuUrl)throws DaoException;
	public Menu findStormenuByLevel(String menuLevel)throws DaoException;
	public void removeMenu(Menu menu)throws DaoException;
	public void saveMenu(Menu menu)throws DaoException;
	public void updateMenu(Menu menu)throws DaoException;
	public List<Menu> getOutOfMenusByRole(Role role)throws DaoException;
	public List findChildMenuList(String mparentId)throws DaoException;
	public List<Menu> findStormenusByRoleId(Integer roleId) throws DaoException;
}
