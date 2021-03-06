package com.zhadan.servlet;

import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:33
 */
public class MoviesServlet extends SpringInitServlet {
    private static final long serialVersionUID = -36236414742594L;
    private static final String ATTRIBUTE_MOVIE_LIST = "movies";
    private static final String PAGE_OK = "/v1servlet/movies.jsp";
    private static final Logger logger = getLogger(MoviesServlet.class.getName());
    //@ZhadanInject("movieDao")
    private MovieDao movieDao;

    @Override
    public void init() throws ServletException {
        super.init();
        movieDao = (MovieDao) getContext().getBean("movieDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Movie> movieList = movieDao.list();
            req.setAttribute(ATTRIBUTE_MOVIE_LIST, movieList);
            logger.info("set attribute " + ATTRIBUTE_MOVIE_LIST + " with " + movieList.size() + " movies");
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            logger.info("dispatched to page " + PAGE_OK);
        } catch (Exception ex) {
            //FAIL
            logger.error("error occur ");
        }
    }
}
