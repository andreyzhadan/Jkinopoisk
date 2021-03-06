package com.zhadan.servlet;

import com.zhadan.bean.User;
import com.zhadan.dao.interfaces.UserDao;
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
public class SignUpServlet extends SpringInitServlet {
    private static final long serialVersionUID = 348015408215577254L;
    private static final String INDEX_PAGE = "/v1servlet/home.jsp";
    private static final String REG_PAGE = "/v1servlet/signUp.jsp";
    private static final Logger logger = Logger.getLogger(SignUpServlet.class.getName());
    //@ZhadanInject("userDao")
    //@Autowired
    private UserDao userDao;

//    @Autowired
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = (UserDao) getContext().getBean("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET in register");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDao = (UserDao) getContext().getBean("userDao");
        logger.info("POST in register");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        if (login != null && userDao.findByLogin(login) == null && !password.isEmpty() && password.equals(password2)) {
            User user = new User();
            user.setUserName(login);
            user.setPassword(password);
            userDao.create(user);
            req.getSession().setAttribute("login", user.getUserName());
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(INDEX_PAGE);
        } else {
            resp.sendRedirect(REG_PAGE);
        }
    }
}
