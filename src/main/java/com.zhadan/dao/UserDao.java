package com.zhadan.dao;

import com.zhadan.bean.User;

/**
 * Created by azhadan on 8/1/13.
 */
public interface UserDao {
    public void insert(User user);

    public User getUserByLogin(String login);

    public User validateUser(String login, String password);
}
