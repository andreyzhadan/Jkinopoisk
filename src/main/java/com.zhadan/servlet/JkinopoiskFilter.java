package com.zhadan.servlet;

import com.zhadan.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by azhadan on 7/31/13.
 */
public class JkinopoiskFilter implements Filter {
    private static final String SIGNIN_PAGE = "/v1servlet/signIn.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //NOP
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String reqURI = req.getRequestURI();
        if (!reqURI.contains("/v1servlet/signIn") && !reqURI.contains("/v1servlet/signUp") &&
                !reqURI.contains(".css") && !reqURI.contains("favicon.ico")) {             //insert separate filter for static resources?
            HttpSession httpSession = req.getSession();
            User userFromSession = (User) httpSession.getAttribute("user");
            if (userFromSession == null) {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect(SIGNIN_PAGE);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //NOP
    }
}
