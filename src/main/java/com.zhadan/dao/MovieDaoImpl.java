package com.zhadan.dao;

import com.zhadan.bean.Movie;
import com.zhadan.bean.User;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public List<Movie> selectAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            Context ctx = new InitialContext();
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
                movie.setSlogan(rs.getString("slogan"));
                movie.setYear(rs.getInt("year"));
                logger.info(movie);
                movies.add(movie);
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
        return movies;
    }

    @Override
    public Movie selectById(Integer id) {
        return null;
    }
}
