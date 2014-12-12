package web.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import web.dao.MenuMgmtDao;
import web.exception.DaoException;
import web.model.Menu;
import web.model.Role;
import web.util.Page;

/**
 * @author liuyh
 * 
 */
public class MenuMgmtDaoImpl extends BaseDaoImpl implements MenuMgmtDao {

	public List findAllMenupage(Page page) throws DaoException {
		List list = null;
		String hql = "from Menu  order by menuLevel";
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		List lists = query.list();
		int inter = lists.size();
		page.setMaxRowCount(inter);
		query.setFirstResult((page.getCurPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		list = query.list();
		this.clear(session);
		return list;
	}

	public Menu findStormenuById(String menuId) throws DaoException {
		String sql = "from Menu where menuId =?";
		Session session = getSessionFactory().openSession();
		Menu stormenu = (Menu) session.createQuery(sql).setParameter(0,
				Integer.valueOf(menuId)).uniqueResult();
		this.clear(session);
		return stormenu;
	}

	public List<Menu> findStormenusByRoleId(Integer roleId) throws DaoException {
		String hql = "select m from Menu m, Roletomenu r where m.menuId = r.menuid and r.roleid= ?  order by menuLevel";
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql).setParameter(0, roleId);
		List lists = query.list();
		this.clear(session);
		return lists;
	}
	
	public Menu findStormenuByUrl(String menuUrl) throws DaoException {
		String sql = "from Menu where menuUrl =?";
		Session session = getSessionFactory().openSession();
		Menu stormenu = (Menu) session.createQuery(sql).setParameter(0,
				menuUrl).uniqueResult();
		this.clear(session);
		return stormenu;
	}	

	public Menu findStormenuByLevel(String menuLevel) throws DaoException {
		String sql = "from Menu where menuLevel =?";
		Session session = getSessionFactory().openSession();
		Menu stormenu = (Menu) session.createQuery(sql).setParameter(0,
				menuLevel).uniqueResult();
		this.clear(session);
		return stormenu;
	}	
	
	public List findAllMenuList() throws DaoException {
		String hql = "from Menu  order by menuLevel";
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		List lists = query.list();
		this.clear(session);
		return lists;
	}
	public List findChildMenuList(String mparentId) throws DaoException {
		String hql = "from Menu where MparentId = ?  order by menuLevel";
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql).setParameter(0, mparentId);
		List lists = query.list();
		this.clear(session);
		return lists;
	}
	public List<Menu> getOutOfMenusByRole(Role role)
			throws DaoException {
		return null;
	}

	public void removeMenu(Menu menu) throws DaoException {
		this.delete(menu);
	}

	public void saveMenu(Menu menu) throws DaoException {
		this.save(menu);

	}

	public void updateMenu(Menu menu) throws DaoException {
		Session session = getSessionFactory().openSession();
		session.merge(menu);
		this.clear(session);
	}
	
	
	
}
