package net.therap.service;

import net.therap.dao.UserDao;
import net.therap.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserManager")
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserDao userDao;

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public User getUser(long id) {
        return (User) userDao.getUser(id);
    }

    public User getUserByLoginName(String userName) {
        return userDao.getUserByLoginName(userName);
    }
}
