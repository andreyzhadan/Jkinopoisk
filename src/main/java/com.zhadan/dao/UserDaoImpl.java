package com.zhadan.dao;

import com.zhadan.bean.User;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by azhadan on 8/1/13.
 */
public class UserDaoImpl implements UserDao {
    private static final Logger logger = getLogger(UserDaoImpl.class.getName());
    private static final String SELECT_SQL = "select * from user where login=?";

    @Override
    public void insert(User user) {

    }

    @Override
    public User getUserByLogin(String login) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
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
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                logger.info(user);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Something bad happens");
        } catch (NamingException e) {
            e.printStackTrace();
            logger.error("Something bad happens");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Cannot closeAllConnections RS && STMT");
            }
        }
        return null;
    }

    @Override
    public User validateUser(String login, String password) {
        if (login == null || password == null) {
            return null;
        }
        //User user = users.get(login);
        User user = getUserByLogin(login);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password.trim())) {
            return null;
        }
        return user;
    }
}
