package web.service;

import web.dao.UserDao;
import web.exception.UserAlreadyExistsException;
import web.model.User;

public interface UserService {
    public void setUserDao(UserDao userDao);

    public User authenticate(String username, String password) ;

    public User getUser(String username);

    public void addUser(User user) throws UserAlreadyExistsException;
}
