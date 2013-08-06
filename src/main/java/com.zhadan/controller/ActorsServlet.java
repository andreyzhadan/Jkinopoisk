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
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/4/13
 * Time: 1:12 PM
 */
public class ActorsServlet extends DependencyInjectionServlet {
    private static final long serialVersionUID = -5043972288953941065L;
    private static final String ATTRIBUTE_ACTOR_LIST = "actors";
    private static final String PAGE_OK = "/actors.jsp";
    private static final String PAGE_ERROR = "/error.jsp";
    private static final Logger logger = getLogger(ActorsServlet.class);
    @ZhadanInject("actorDao")
    private ActorDao actorDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Actor> actorList = actorDao.list();
            req.setAttribute(ATTRIBUTE_ACTOR_LIST, actorList);
            logger.info("set attribute " + ATTRIBUTE_ACTOR_LIST + " with " + actorList.size() + " movies");
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            logger.info("dispatched to page " + PAGE_OK);
        } catch (Exception ex) {

            //FAIL
            resp.sendRedirect(PAGE_ERROR);
            logger.info("redirect to page " + PAGE_ERROR);
        }
    }
}
