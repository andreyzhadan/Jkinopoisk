package com.zhadan.controller;

import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.ownIoC.SpringInitServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:48
 */
public class MovieEditServlet extends SpringInitServlet {
    private static final long serialVersionUID = -6777834847092077725L;
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_MOVIE = "movie";
    private static final String PAGE_OK = "/movieEdit.jsp";
    private static final String PAGE_ERROR = "/error.jsp";
    private static final Logger logger = getLogger(MovieEditServlet.class.getName());
    //@ZhadanInject("movieDao")
    private MovieDao movieDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        movieDao = (MovieDao) getContext().getBean("movieDao");
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            final Integer id = Integer.valueOf(idStr);
            Movie movie = movieDao.find(id);
            if (movie != null) {
                req.setAttribute(ATTRIBUTE_MOVIE, movie);
                logger.info("set attribute " + ATTRIBUTE_MOVIE + movie);
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
