package web.service;

import java.util.List;

import web.exception.ServiceException;
import web.model.Menu;
import web.model.Role;
import web.util.Page;

/**
 * @author liuyh
 * 
 */
public interface MenuMgmtService {
	public Page findAllMenupage(Page page) throws ServiceException;

	public Menu findStormenuById(String menuId) throws ServiceException;

	public Menu findStormenuByUrl(String menuUrl) throws ServiceException;
	
	public Menu findStormenuByLevel(String menuLevel)throws ServiceException;

	public List findAllMenuList() throws ServiceException;

	public void removeMenu(Menu menu) throws ServiceException;

	public void saveMenu(Menu menu) throws ServiceException;

	public void updateMenu(Menu menu) throws ServiceException;

	public List<Menu> getOutOfMenusByRole(Role role)
			throws ServiceException;

	public List findChildMenuList(String mparentId) throws ServiceException;
	
	public List<Menu> findStormenusByRoleId(Integer roleId) throws ServiceException;

}
