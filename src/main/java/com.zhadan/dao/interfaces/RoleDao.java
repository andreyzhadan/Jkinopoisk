package com.zhadan.dao.interfaces;

import com.zhadan.bean.Role;
import com.zhadan.exceptions.DAOException;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/3/13
 * Time: 10:39 AM
 */
public interface RoleDao {
    public static final String INSERT_SQL = "insert into userRole (userId,roleName) values (?,?)";

    public void create(Role role) throws IllegalArgumentException, DAOException;
}
