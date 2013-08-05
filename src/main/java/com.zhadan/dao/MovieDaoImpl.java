package com.zhadan.dao;

import com.zhadan.bean.Movie;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:34
 */
public class MovieDaoImpl implements MovieDao {
    private static final Logger logger = getLogger(MovieDaoImpl.class.getName());
    private static final String SELECT_ALL = "select * from movie";
    private static final String SELECT_BY_ID = "select * from movie where id=?";

    public MovieDaoImpl() {
    }

    @Override
    public void addMovie(Movie movie) {
    }

    @Override
    public List<Movie> listMovies() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            // Create initial context JNDI
            Context ctx = new InitialContext();
            // Get datasource from context
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jkinopoisk");
            connection = ds.getConnection();

            ps = connection.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setCountry(rs.getString("country"));
                movie.setName(rs.getString("name"));
                movie.setRussianName(rs.getString("russianName"));
                movie.setRating(rs.getFloat("rating"));
                movie.setSlogan(rs.getString("slogan"));
                movie.setYear(rs.getInt("year"));
                movies.add(movie);
            }
            logger.info(Arrays.asList(movies));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Something bad happens");
        } catch (NamingException e) {
            e.printStackTrace();
            logger.error("Something bad happens");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Cannot closeAllConnections RS && STMT");
            }
        }
        return movies;
    }

    @Override
    public Movie findById(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie movie = new Movie();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jkinopoisk");
            connection = ds.getConnection();

            ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                movie.setId(rs.getInt("id"));
                movie.setCountry(rs.getString("country"));
                movie.setName(rs.getString("name"));
                movie.setRussianName(rs.getString("russianName"));
                movie.setRating(rs.getFloat("rating"));
                movie.setSlogan(rs.getString("slogan"));
                movie.setYear(rs.getInt("year"));
                logger.info(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Something bad happens");
        } catch (NamingException e) {
            e.printStackTrace();
            logger.error("Something bad happens");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Cannot closeAllConnections RS && STMT");
            }
        }
        return movie;
    }

    @Override
    public void removeMovie(Integer id) {
    }
}
