package web.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import web.dao.UserDao;
import web.model.User;

public class UserDaoImpl  extends BaseDaoImpl implements UserDao{

    public User getUser(String username) {
		Session session = getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from User u where u.username = :username");
            query.setString("username", username);
            query.setMaxResults(1);
            return (User)query.uniqueResult();
        } finally {
            session.close();
        }
    }

    public void addUser(User user) {
		Session session = getSessionFactory().openSession();
        Transaction ts = null;
        try {
            ts = session.beginTransaction();
            session.save(user);
            ts.commit();
        } finally {
            session.close();
        }
    }
}
