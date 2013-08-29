package com.zhadan.dao.jdbc;

import com.zhadan.bean.User;
import com.zhadan.dao.interfaces.UserDao;
import com.zhadan.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.zhadan.utils.DatabaseUtils.close;
import static com.zhadan.utils.DatabaseUtils.createDataSource;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created by azhadan on 8/1/13.
 */
@Repository
public class UserDaoJdbcImpl implements UserDao {
    private static final Logger logger = getLogger(UserDaoJdbcImpl.class.getSimpleName());
    private DataSource dataSource;

    public UserDaoJdbcImpl() {
        //dataSource = createDataSource();
    }

    @Override
    public void create(User user) throws IllegalArgumentException, DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(INSERT_SQL);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
    }

    @Override
    public User findByLogin(String login) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(SELECT_SQL);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                logger.info(user);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
        return user;
    }

    @Override
    public User validateUser(String login, String password) {
        if (login == null || password == null) {
            return null;
        }
        User user = findByLogin(login);
        if (user == null || !user.getPassword().equals(password.trim())) {
            return null;
        }
        return user;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
