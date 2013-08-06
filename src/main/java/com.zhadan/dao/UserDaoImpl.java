package com.zhadan.dao;

import com.zhadan.bean.User;
import com.zhadan.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zhadan.utils.DaoUtils.close;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created by azhadan on 8/1/13.
 */
public class UserDaoImpl implements UserDao {
    private static final Logger logger = getLogger(UserDaoImpl.class.getSimpleName());
    private static final String SELECT_SQL = "select * from user where login=?";
    private static final String INSERT_SQL = "insert into user (login,password) values (?,?)";

    @Override
    public void create(User user) throws IllegalArgumentException, DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jkinopoisk");
            connection = ds.getConnection();
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
            //Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jkinopoisk", "root", "sadmin");

            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jkinopoisk");
            connection = ds.getConnection();

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
}
