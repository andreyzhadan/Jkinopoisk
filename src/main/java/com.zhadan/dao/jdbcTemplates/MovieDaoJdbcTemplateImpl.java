package com.zhadan.dao.jdbcTemplates;

import com.zhadan.bean.Actor;
import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.exceptions.DAOException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/28/13
 * Time: 12:50 PM
 */
@Repository
public class MovieDaoJdbcTemplateImpl implements MovieDao {
    private static final RowMapper<Movie> MOVIE_ROW_MAPPER = new RowMapper<Movie>() {

        @Override
        public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
            Movie movie = new Movie();
            movie.setName(resultSet.getString("name"));
            movie.setRussianName(resultSet.getString("russianName"));
            movie.setId(resultSet.getInt("id"));
            movie.setYear(resultSet.getInt("year"));
            movie.setSlogan(resultSet.getString("slogan"));
            movie.setCountry(resultSet.getString("country"));
            movie.setPicture(resultSet.getString("picture"));
            return movie;
        }
    };
    private JdbcTemplate jdbc;

    @Override
    public Movie find(Integer id) throws DAOException {
        List<Movie> movieList = jdbc.query(SELECT_BY_ID, new Object[]{id}, MOVIE_ROW_MAPPER);
        if (movieList.isEmpty()) {
            return null;
        }
        return movieList.get(0);
    }

    @Override
    public List<Movie> list() throws DAOException {
        return jdbc.query(SELECT_ALL, new BeanPropertyRowMapper(Movie.class));
    }

    @Override
    public void create(Movie entity) throws IllegalArgumentException, DAOException {
        jdbc.update(INSERT_SQL, new Object[]{entity.getName(), entity.getRussianName(), entity.getRating(), entity.getSlogan(), entity.getYear(), entity.getCountry()});
    }

    @Override
    public void update(Movie entity) throws IllegalArgumentException, DAOException {
        jdbc.update(UPDATE_SQL, entity.getName(), entity.getRussianName(), entity.getRating(), entity.getSlogan(), entity.getYear(), entity.getCountry(), entity.getId());
    }

    @Override
    public void delete(Movie entity) throws DAOException {
        jdbc.update(DELETE_SQL, entity.getId());
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
}
