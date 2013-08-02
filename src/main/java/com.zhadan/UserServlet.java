package com.zhadan;

import com.zhadan.bean.User;
import com.zhadan.dao.UserDao;
import com.zhadan.dao.UserDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by azhadan on 7/30/13.
 */
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = -6500557339421743748L;
    private static final Logger logger = Logger.getLogger(UserServlet.class.getName());
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jkinopoisk/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Session id " + req.getSession().getId());
        for (Cookie cookie : req.getCookies()) {
            logger.info("Cookie " + cookie.getName() + " / " + cookie.getValue());
        }
        RequestDispatcher rd;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userDao.validateUser(login, password);
        if (user == null) {
            rd = req.getRequestDispatcher("login.jsp");
        } else {
            req.getSession().setAttribute("user", user);
            rd = req.getRequestDispatcher("jkinopoisk/index.jsp");
        }
        rd.forward(req, resp);
    }
}
