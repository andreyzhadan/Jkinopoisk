package com.zhadan.dao.interfaces;

import com.zhadan.bean.User;
import com.zhadan.exceptions.DAOException;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/1/13
 * Time: 15:52
 */
public interface UserDao {
    public static final String SELECT_SQL = "select * from users where userName=?";
    public static final String INSERT_SQL = "insert into users (userName,password) values (?,?)";

    public void create(User user) throws IllegalArgumentException, DAOException;

    public User findByLogin(String userName);

    public User validateUser(String userName, String password);
}
