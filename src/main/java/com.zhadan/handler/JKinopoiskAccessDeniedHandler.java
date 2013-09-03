package com.zhadan.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/3/13
 * Time: 1:22 PM
 */
public class JKinopoiskAccessDeniedHandler extends AccessDeniedHandlerImpl {
    private String accessDeniedUrl;

    public JKinopoiskAccessDeniedHandler(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }

    public JKinopoiskAccessDeniedHandler() {
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        setErrorPage("/securityAccessDenied");  // this is a standard Spring MVC Controller

        super.handle(request, response, accessDeniedException);
    }

    public String getAccessDeniedUrl() {
        return accessDeniedUrl;
    }

    public void setAccessDeniedUrl(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }
}
