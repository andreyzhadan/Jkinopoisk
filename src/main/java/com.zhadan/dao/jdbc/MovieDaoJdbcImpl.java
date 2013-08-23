package com.zhadan.dao.jdbc;

import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zhadan.utils.DatabaseUtils.close;
import static com.zhadan.utils.DatabaseUtils.createDataSource;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:34
 */
public class MovieDaoJdbcImpl implements MovieDao {
    private static final Logger logger = getLogger(MovieDaoJdbcImpl.class.getName());
    private static final String SELECT_ALL = "select * from movie";
    private static final String SELECT_BY_ID = "select * from movie where id=?";
    private static final String INSERT = "insert into movie (name,russianName,rating,slogan,year,country) values (?,?,?,?,?,?)";
    private static final String UPDATE = "update movie set name=?,russianName=?,rating=?,slogan=?,year=?,country=?, where id=?";
    private static final String DELETE = "delete from movie where id=?";
    private DataSource dataSource;


    public MovieDaoJdbcImpl() {
        dataSource = createDataSource();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
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
                movie.setName(rs.getString("name"));
                movie.setRussianName(rs.getString("russianName"));
                movie.setRating(rs.getFloat("rating"));
                movie.setSlogan(rs.getString("slogan"));
                movie.setYear(rs.getInt("year"));
                movie.setCountry(rs.getString("country"));
                logger.debug("You find movie " + movie);
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
                movie.setName(rs.getString("name"));
                movie.setRussianName(rs.getString("russianName"));
                movie.setRating(rs.getFloat("rating"));
                movie.setSlogan(rs.getString("slogan"));
                movie.setYear(rs.getInt("year"));
                movie.setCountry(rs.getString("country"));
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
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getRussianName());
            ps.setFloat(3, entity.getRating());
            ps.setString(4, entity.getSlogan());
            ps.setInt(5, entity.getYear());
            ps.setString(6, entity.getCountry());
            int affectedRows = ps.executeUpdate();
            logger.debug("Inserted " + affectedRows + " entity with values " + entity);
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
    }

    @Override
    public void update(Movie entity) throws IllegalArgumentException, DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getRussianName());
            ps.setFloat(3, entity.getRating());
            ps.setString(4, entity.getSlogan());
            ps.setInt(5, entity.getYear());
            ps.setString(6, entity.getCountry());
            ps.setInt(7, entity.getId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating movie failed, no rows affected.");
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
    }

    @Override
    public void delete(Movie entity) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(DELETE);
            ps.setInt(1, entity.getId());
            int affectedRows = ps.executeUpdate();
            logger.debug("You are trying to delete movie");
            if (affectedRows == 0) {
                throw new DAOException("Updating movie failed, no rows affected.");
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
    }

}
