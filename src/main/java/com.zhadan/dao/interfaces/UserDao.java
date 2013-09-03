package com.zhadan.dao.interfaces;

import com.zhadan.bean.User;
import com.zhadan.exceptions.DAOException;

import javax.sql.DataSource;

/**
 * Created by azhadan on 8/1/13.
 */
public interface UserDao {
    public static final String SELECT_SQL = "select * from users where userName=?";
    public static final String INSERT_SQL = "insert into users (userName,password) values (?,?)";

    public void create(User user) throws IllegalArgumentException, DAOException;

    public User findByLogin(String userName);

    public User validateUser(String userName, String password);

    public void setDataSource(DataSource dataSource);
}
