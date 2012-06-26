package net.therap.service;

import net.therap.domain.User;

public interface UserManager {
    public void saveUser(User user);
    public User getUser(long id);
    public User getUserByLoginName(String userName);
    public void updateUser(User user);
}
