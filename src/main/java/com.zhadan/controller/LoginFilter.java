package com.zhadan.controller;

import com.zhadan.bean.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created by azhadan on 7/31/13.
 */
public class LoginFilter implements Filter {
    private static final Logger logger = getLogger(LoginFilter.class.getName());
    private static final String LOGIN_PAGE = "login.jsp";

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        //NOP
    }

    @Override
    public void destroy() {
        //NOP
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession httpSession = req.getSession();
        User userFromSession = (User) httpSession.getAttribute("user");
        if (userFromSession == null && !req.getMethod().equals("POST")) {
            req.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}