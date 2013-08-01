package com.zhadan;

import com.zhadan.bean.User;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by azhadan on 7/30/13.
 */
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = -6500557339421743748L;
    private static final Logger LOGGER = Logger.getLogger(UserServlet.class.getName());
    private static final String SELECT_SQL = "select * from user where login=?";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jkinopoisk/index.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Session id " + req.getSession().getId());
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            LOGGER.info("Cookie " + cookie.getName() + " / " + cookie.getValue());
        }
        RequestDispatcher rd;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = validateUser(login, password);
        if (user == null) {
            rd = req.getRequestDispatcher("login.jsp");
        } else {
            req.getSession().setAttribute("user", user);
            rd = req.getRequestDispatcher("jkinopoisk/index.jsp");
        }
        rd.forward(req, resp);
    }

    private User validateUser(String login, String password) {
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

    private User getUserByLogin(String login) {
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
                LOGGER.info(user.toString());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Smth bad happens");
        } catch (NamingException e) {
            e.printStackTrace();
            LOGGER.error("Smth bad happens");
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Cannot closeAllConnections RS && STMT");
            }
        }
        return null;
    }
}
