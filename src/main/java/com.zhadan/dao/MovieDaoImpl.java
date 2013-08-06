package com.zhadan.dao;

import com.zhadan.bean.Movie;
import com.zhadan.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zhadan.utils.DaoUtils.close;
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
    private DataSource dataSource;


    public MovieDaoImpl() {
        try {
            dataSource = (DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/jkinopoisk");
        } catch (NamingException e) {
            logger.error("Naming exception " + e.getMessage());
        }
    }

    @Override
    public Movie find(Integer id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie movie = null;
        try {
            connection = dataSource.getConnection();

            ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setCountry(rs.getString("country"));
                movie.setName(rs.getString("name"));
                movie.setRussianName(rs.getString("russianName"));
                movie.setRating(rs.getFloat("rating"));
                movie.setSlogan(rs.getString("slogan"));
                movie.setYear(rs.getInt("year"));
                logger.info(movie);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
        return movie;
    }

    @Override
    public List<Movie> list() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL);
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
            logger.debug(Arrays.asList(movies));
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, statement, rs);
        }
        return movies;
    }

    @Override
    public void create(Movie entity) throws IllegalArgumentException, DAOException {
    }

    @Override
    public void update(Movie entity) throws IllegalArgumentException, DAOException {
    }

    @Override
    public void delete(Movie entity) throws DAOException {
    }

}
