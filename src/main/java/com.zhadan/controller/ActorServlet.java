package com.zhadan.controller;

import com.zhadan.bean.Actor;
import com.zhadan.dao.ActorDao;
import com.zhadan.ownIoC.DependencyInjectionServlet;
import com.zhadan.ownIoC.ZhadanInject;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/4/13
 * Time: 1:11 PM
 */
public class ActorServlet extends DependencyInjectionServlet {
    private static final long serialVersionUID = 2352072114543109917L;
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_ACTOR = "actor";
    private static final String PAGE_OK = "/actor.jsp";
    private static final String PAGE_ERROR = "/error.jsp";
    private static final Logger logger = getLogger(ActorServlet.class.getName());
    @ZhadanInject("actorDao")
    private ActorDao actorDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            final Integer id = Integer.valueOf(idStr);
            Actor actor = actorDao.find(id);
            if (actor != null) {
                req.setAttribute(ATTRIBUTE_ACTOR, actor);
                logger.info("set attribute " + ATTRIBUTE_ACTOR + actor);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                logger.info("redirected to page " + PAGE_OK);
                return;
            }
        }
        //ERROR
        resp.sendRedirect(PAGE_ERROR);
        logger.info("redirect to page " + PAGE_ERROR);
    }
}
