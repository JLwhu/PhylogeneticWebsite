package web.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.UserDao;
import web.exception.UserAlreadyExistsException;
import web.model.User;
import web.service.UserService;

public class UserServiceImpl implements UserService{
    private UserDao userDao;
	
	private Log log = LogFactory.getLog(HotspotServiceImpl.class);
	
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User authenticate(String username, String password) {
        User u = getUser(username);
        if (u != null && password.equals(u.getPassword())) {
            return u;
        }
        return null;
    }

    public User getUser(String username) {
        return userDao.getUser(username);
    }

    public void addUser(User user) throws UserAlreadyExistsException {
        User u = getUser(user.getUsername());
        if (u != null) {
            //register.user.already.exists
            throw new UserAlreadyExistsException();
        }
        userDao.addUser(user);
    }
}
