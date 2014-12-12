package web.dao;

import web.model.User;

public interface UserDao  extends BaseDao{
    public User getUser(String username);

    public void addUser(User user);

}
