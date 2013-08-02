package com.zhadan.controller;

import com.zhadan.bean.User;
import com.zhadan.dao.UserDao;
import com.zhadan.dao.UserDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by azhadan on 7/30/13.
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -6500557339421743748L;
    private static final String INDEX_PAGE = "jkinopoisk/index.jsp";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(INDEX_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Session id " + req.getSession().getId());
        RequestDispatcher rd;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userDao.validateUser(login, password);
        if (user == null) {
            rd = req.getRequestDispatcher(LOGIN_PAGE);
        } else {
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("user", user);
            rd = req.getRequestDispatcher(INDEX_PAGE);
        }
        rd.forward(req, resp);
    }
}
