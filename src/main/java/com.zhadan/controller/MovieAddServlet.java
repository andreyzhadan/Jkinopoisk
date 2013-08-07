package com.zhadan.controller;

import com.zhadan.bean.Actor;
import com.zhadan.bean.Movie;
import com.zhadan.dao.ActorDao;
import com.zhadan.dao.MovieDao;
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
 * Time: 12:46 PM
 */
public class MovieAddServlet extends DependencyInjectionServlet {
    private static final long serialVersionUID = 4787141816316166489L;
    private static final Logger logger = getLogger(MovieAddServlet.class.getSimpleName());
    private static final String MOVIE_ADD_PAGE = "/movieAdd.jsp";
    @ZhadanInject("movieDao")
    private MovieDao movieDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(MOVIE_ADD_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String russianName = req.getParameter("russianName");
            Float rating = Float.valueOf(req.getParameter("rating"));
            String country = req.getParameter("country");
            Integer year = Integer.valueOf(req.getParameter("year"));
            String slogan = req.getParameter("slogan");
            Movie movie = new Movie(name, russianName, rating, slogan, year, country);
            movieDao.create(movie);
            logger.debug("Insert new movie " + movie);
            logger.debug("Send redirect to movies page");
            resp.sendRedirect("/movies");
        } catch (Exception ex) {
            logger.error("Send redirect back to movieAdd page because of not valid data");
            resp.sendRedirect(MOVIE_ADD_PAGE);
        }
    }
}
