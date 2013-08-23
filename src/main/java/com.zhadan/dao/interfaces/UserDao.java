package com.zhadan.dao.interfaces;

import com.zhadan.bean.User;
import com.zhadan.exceptions.DAOException;

/**
 * Created by azhadan on 8/1/13.
 */
public interface UserDao {

    public void create(User user) throws IllegalArgumentException, DAOException;

    public User findByLogin(String login);

    public User validateUser(String login, String password);
}
