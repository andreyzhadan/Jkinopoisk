package com.zhadan.controller;

import com.zhadan.bean.User;
import com.zhadan.dao.UserDao;
import com.zhadan.dao.UserDaoImpl;
import com.zhadan.ownIoC.DependencyInjectionServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/2/13
 * Time: 4:53 PM
 */
public class SignUpServlet extends DependencyInjectionServlet {
    private static final long serialVersionUID = 348015408215577254L;
    private static final String INDEX_PAGE = "/home.jsp";
    private static final String REG_PAGE = "/signUp.jsp";
    private static final Logger logger = Logger.getLogger(SignUpServlet.class.getName());
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET in register");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST in register");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        if (login != null && userDao.getUserByLogin(login) == null && !password.isEmpty() && password.equals(password2)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            userDao.addUser(user);
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(INDEX_PAGE);
        } else {
            resp.sendRedirect(REG_PAGE);
        }
    }
}
