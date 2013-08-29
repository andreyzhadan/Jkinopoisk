package com.zhadan.servlet;

import com.zhadan.bean.Actor;
import com.zhadan.dao.interfaces.ActorDao;
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
public class ActorsServlet extends SpringInitServlet {
    private static final long serialVersionUID = -5043972288953941065L;
    private static final String ATTRIBUTE_ACTOR_LIST = "actors";
    private static final String PAGE_OK = "/v1servlet/actors.jsp";
    private static final Logger logger = getLogger(ActorsServlet.class.getSimpleName());
    //@ZhadanInject("actorDao")
    private ActorDao actorDao;

    @Override
    public void init() throws ServletException {
        super.init();
        actorDao = (ActorDao) getContext().getBean("actorDao");
    }

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
            logger.error("error occur ");
        }
    }
}
