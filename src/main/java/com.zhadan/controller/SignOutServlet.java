package com.zhadan.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/2/13
 * Time: 3:58 PM
 */
public class SignOutServlet extends HttpServlet {
    private static final long serialVersionUID = 2571540935760619201L;
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("GET in logout");
        request.getSession().invalidate();
        response.sendRedirect("/signIn.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("POST in logout");
    }
}
