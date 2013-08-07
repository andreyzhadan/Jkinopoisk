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
 * Date: 8/7/13
 * Time: 12:25 PM
 */
public class ActorAddServlet extends DependencyInjectionServlet {
    private static final long serialVersionUID = 2137603839569251586L;
    private static final Logger logger = getLogger(ActorAddServlet.class.getSimpleName());
    private static final String ACTOR_ADD_PAGE = "/actorAdd.jsp";
    @ZhadanInject("actorDao")
    private ActorDao actorDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(ACTOR_ADD_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            Integer birthday = Integer.valueOf(req.getParameter("birthday"));
            String country = req.getParameter("country");
            Actor actor = new Actor(firstName, lastName, birthday, country);
            actorDao.create(actor);
            logger.debug("Insert new actor " + actor);
            logger.debug("Send redirect to actors page");
            resp.sendRedirect("/actors");
        } catch (Exception ex) {
            logger.error("Send redirect back to actorAdd page because of not valid data");
            resp.sendRedirect(ACTOR_ADD_PAGE);
        }
    }
}
