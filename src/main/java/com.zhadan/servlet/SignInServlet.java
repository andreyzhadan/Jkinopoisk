package com.zhadan.servlet;

import com.zhadan.bean.User;
import com.zhadan.dao.interfaces.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by azhadan on 7/30/13.
 */
//@Service
public class SignInServlet extends SpringInitServlet {
    private static final long serialVersionUID = -6500557339421743748L;
    private static final String INDEX_PAGE = "/v1servlet/home.jsp";
    private static final String LOGIN_PAGE = "/v1servlet/signIn.jsp";
    private static final Logger logger = Logger.getLogger(SignInServlet.class.getName());
    //@ZhadanInject("userDao")
    //@Autowired
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = (UserDao) getContext().getBean("userDao");
    }

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
            rd.forward(req, resp);
        } else {
            req.getSession().setAttribute("login", user.getUserName());
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(INDEX_PAGE);
        }
    }
}
