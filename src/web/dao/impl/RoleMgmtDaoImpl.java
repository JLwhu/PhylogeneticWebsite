package web.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import web.dao.RoleMgmtDao;
import web.exception.DaoException;
import web.model.Role;
import web.model.User;
import web.util.Page;

/**
 * @author liuyh
 * 
 */
public class RoleMgmtDaoImpl extends BaseDaoImpl implements RoleMgmtDao {

	public List getAllRoles() throws DaoException {
		String hql = "from Role ";
		Session session = getSessionFactory().openSession();
		List rolelist = session.createQuery(hql).list();
		this.clear(session);
		return rolelist;
	}

	public List<Role> findRolesByUserid(Integer userid) throws DaoException {
		String hql = "select r.* from Role r,Usertorole u where r.roleid = u.roleid and u.userid = ? ";
		Session session = getSessionFactory().openSession();
		List rolelist = session.createSQLQuery(hql).addEntity(Role.class).setParameter(0, userid).list();
		this.clear(session);
		return rolelist;
	}	
	


	public Role getRoleById(String roleid) throws DaoException {
		String hql = "from Role where  roleid=?";
		Session session = getSessionFactory().openSession();
		Role role = (Role) session.createQuery(hql).setParameter(0,
				Integer.valueOf(roleid)).uniqueResult();
		this.clear(session);
		return role;
	}

	public List getRolesBySearchname(String queryName, String queryValue,
			Page page) throws DaoException {
		List list = null;
		String hql = "";
		if (queryName == null || queryName.equals("") || queryValue == null
				|| queryValue.equals("")) {
			hql = "from Role ";
		} else {
			hql = "from Role where " + queryName + " like '%" + queryValue
					+ "%'";
		}
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

	public void removeRole(Role role) throws DaoException {
		this.delete(role);

	}

	public void saveRole(Role role) throws DaoException {
		this.save(role);

	}

	public void updateRole(Role role) throws DaoException {
		Session session = getSessionFactory().openSession();
		session.merge(role);
		this.clear(session);
	}

	public String getMaxRoleID() throws DaoException {
		String hql = "SELECT MAX(roleid) FROM Role ";
		Session session = getSessionFactory().openSession();
		List ll = (List) session.createSQLQuery(hql);
		Iterator itr = ll.iterator();
		String maxroleid = null;
		if (itr.hasNext()) {
			Object noint = itr.next();
			if (noint == null) {
				maxroleid = "1";
			} else {
				maxroleid = noint.toString();
			}
		}
		this.clear(session);
		return maxroleid;
	}

}
