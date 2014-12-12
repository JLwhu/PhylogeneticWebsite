package web.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.MenuMgmtDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.Menu;
import web.model.Role;
import web.service.MenuMgmtService;
import web.util.Page;

/**
 * @author liuyh
 *
 */
public class MenuMgmtServiceImpl implements MenuMgmtService {
	private Log log = LogFactory.getLog(MenuMgmtServiceImpl.class);

	private MenuMgmtDao menuMgmtDao;

	public Page findAllMenupage(Page page) throws ServiceException {
		List list = null;
		try {
			list = menuMgmtDao.findAllMenupage(page);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return new Page(list, page.getMaxRowCount(), page.getCurPage(), page
				.getPageSize());
	}

	public List findAllMenuList() throws ServiceException {
		List list = null;
		try {
			list = menuMgmtDao.findAllMenuList();
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return list;
	}

	public Menu findStormenuById(String menuId) throws ServiceException {
		Menu menu = null;
		try {
			menu = menuMgmtDao.findStormenuById(menuId);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return menu;
	}

	public MenuMgmtDao getMenuMgmtDao() {
		return menuMgmtDao;
	}

	public void setMenuMgmtDao(MenuMgmtDao menuMgmtDao) {
		this.menuMgmtDao = menuMgmtDao;
	}

	public List<Menu> getOutOfMenusByRole(Role role)
			throws ServiceException {
		List<Menu> list;
		try {
			list = menuMgmtDao.getOutOfMenusByRole(role);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return list;
	}

	public void removeMenu(Menu menu) throws ServiceException {
		try {
			menuMgmtDao.removeMenu(menu);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

	}

	public void saveMenu(Menu menu) throws ServiceException {
		try {
			menuMgmtDao.save(menu);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

	}

	public void updateMenu(Menu menu) throws ServiceException {
		try {
			menuMgmtDao.updateMenu(menu);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

	}

	public List findChildMenuList(String mparentId) throws ServiceException {
		List list = null;
		try {
			list =menuMgmtDao.findChildMenuList(mparentId);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return list;
	}

	public Menu findStormenuByUrl(String menuUrl) throws ServiceException {
		Menu menu = null;
		try {
			menu = menuMgmtDao.findStormenuByUrl(menuUrl);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return menu;
	}

	public Menu findStormenuByLevel(String menuLevel) throws ServiceException {
		Menu menu = null;
		try {
			menu = menuMgmtDao.findStormenuByLevel(menuLevel);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return menu;
	}

	public List<Menu> findStormenusByRoleId(Integer roleId)
			throws ServiceException {
		List<Menu> list=null;
		try {
			list = menuMgmtDao.findStormenusByRoleId(roleId);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return list;
	}

}
