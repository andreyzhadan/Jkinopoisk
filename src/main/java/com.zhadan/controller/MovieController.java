package com.zhadan.controller;

import com.zhadan.bean.Movie;
import com.zhadan.dao.MovieDao;
import com.zhadan.dao.MovieDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class MovieController extends HttpServlet {
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_MOVIE = "movie";
    private static final String PAGE_OK = "/jkinopoisk/movies.jsp";
    private static final String PAGE_ERROR = "/jkinopoisk/error.jsp";
    private static final Logger logger = getLogger(MovieAllController.class.getName());
    private MovieDao movieDao = new MovieDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            final Integer id = Integer.valueOf(idStr);
            Movie movie = movieDao.selectById(id);
            if (movie != null) {
                req.setAttribute(ATTRIBUTE_MOVIE, movie);
                logger.debug("set attribute " + ATTRIBUTE_MOVIE + movie);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                logger.debug("dispatched to page " + PAGE_OK);
                return;
            }
        }
        //ERROR
        resp.sendRedirect(PAGE_ERROR);
        logger.debug("redirect to page " + PAGE_ERROR);
    }
}
