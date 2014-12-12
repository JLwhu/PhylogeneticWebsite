package web.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import web.dao.LogManageDao;
import web.exception.DaoException;
import web.model.Hotspot;
import web.model.Role;
import web.model.User;
import web.util.Page;

/**
 * 
 * 
 * @author liuyh
 * @version 1.0 08/7/27
 * @since JDK1.5
 * 
 */
public class LogManageDaoImpl extends BaseDaoImpl implements LogManageDao {

	public List findLogManage(Page pl) throws DaoException {
		List list = null;
		String hql = "from User";

		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		List lists = query.list();
		int inter = lists.size();
		pl.setMaxRowCount(inter);
		query.setFirstResult((pl.getCurPage() - 1) * pl.getPageSize());
		query.setMaxResults(pl.getPageSize());
		list = query.list();
		this.clear(session);
		return list;
	}

	public List findLogByLogname(String logName) throws DaoException {
		String hql = "from User where username ='"+logName+"'";
		List list = null;
		list = this.query(hql);
		return list;
	}

	public User findUserById(String userid) throws DaoException {
		String sql = "from User where userid =?";
		Session session = getSessionFactory().openSession();
		User user = (User) session.createQuery(sql).setParameter(0,
				Integer.valueOf(userid)).uniqueResult();
		
		this.clear(session);
		return user;
	}

	public User getLogByLognamePwd(String userName, String passWord)
			throws DaoException {
		String hql = "from User where username = :username and password= :password";
		Session session = getSessionFactory().openSession();
		User user = (User) session.createQuery(hql).setParameter("username",
				userName).setParameter("password", passWord).uniqueResult();
		this.clear(session);
		return user;
	}

	public String getMaxUserID() throws DaoException {
		String hql = "SELECT MAX(userid)+1 FROM User ";
		Session session = getSessionFactory().openSession();
		List ll = (List) session.createSQLQuery(hql);
		Iterator itr = ll.iterator();
		String maxuserid = null;
		if (itr.hasNext()) {
			Object noint = itr.next();
			if (noint == null) {
				maxuserid = "1";
			} else {
				maxuserid = noint.toString();
			}
		}
		this.clear(session);
		return maxuserid;
	}

	public List findLogByQueryName(Page page, String queryName,
			String queryValue) throws DaoException {
		List list = null;
		String hql = "";
		if (queryName == null || queryName.equals("") || queryValue == null
				|| queryValue.equals("")) {
			hql = "from User ";
		} else {
			hql = "from User where " + queryName + " like '%" + queryValue
					+ "%'";
		}
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		List lists = query.list();
		int inter = lists.size();
		page.setMaxRowCount(inter);
		//(page.getCurPage() - 1) * page.getPageSize()
		query.setFirstResult((page.getCurPage() - 1) * page.getPageSize());
		list = query.list();
		this.clear(session);
		return list;
	}

	public List<Role> getOutOfRolesByUser(User sysuser)
			throws DaoException {
		String hql = " select r.roleid   from role r "
				+ "where r.roleid  not in (select r2.roleid "
				+ "from role r2 " + ",usertorole ort "
				+ "where  r2.roleid = ort.roleid and ort.userid = "
				+ sysuser.getUserid() + ")";
		Session session = getSessionFactory().openSession();
		List list = session.createSQLQuery(hql).list();
		List<Role> listrole = new ArrayList<Role>();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Integer roleid = (Integer) it.next();
			String sql = "from Role where  roleid=?";
			Role role = (Role) session.createQuery(sql).setParameter(0,
					roleid).uniqueResult();
			listrole.add(role);
		}
		this.clear(session);
		list = null;
		return listrole;
	}

	public boolean examSingle(String username) throws DaoException {
		String hql = "from User where  username = ?";
		Session session = getSessionFactory().openSession();
		List list = session.createQuery(hql).setParameter(0, username).list();
		this.clear(session);
		return (list.size() > 0) ? false : true;
	}

	public void updateLogManage(Object object) throws DaoException {
		Session session = getSessionFactory().openSession();
	session.merge(object);
	this.clear(session);
	}
	
	public int changePasswordByid(Integer operatorid, String password)
	throws DaoException {
		String hql = " update User  set password = ? where  userid =? ";
		Session session = getSessionFactory().openSession();
		int i = session.createQuery(hql).setParameter(0, password)
				.setParameter(1, operatorid).executeUpdate();
		this.clear(session);
		return i;
	}	
	
}
