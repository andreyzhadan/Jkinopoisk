package com.zhadan.dao.jdbcTemplates;

import com.zhadan.bean.Actor;
import com.zhadan.dao.interfaces.ActorDao;
import com.zhadan.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Time: 12:25 PM
 */
@Repository
public class ActorDaoJdbcTemplateImpl implements ActorDao {
    private static final RowMapper<Actor> ACTOR_ROW_MAPPER = new RowMapper<Actor>() {

        @Override
        public Actor mapRow(ResultSet resultSet, int i) throws SQLException {
            Actor actor = new Actor();
            actor.setFirstName(resultSet.getString("firstName"));
            actor.setLastName(resultSet.getString("lastName"));
            actor.setId(resultSet.getInt("id"));
            actor.setBirthday(resultSet.getInt("birthday"));
            actor.setCountry(resultSet.getString("country"));
            actor.setPicture(resultSet.getString("picture"));
            return actor;
        }
    };
    private JdbcTemplate jdbc;

    @Override
    public Actor find(Integer id) throws DAOException {
        List<Actor> actorList = jdbc.query(SELECT_BY_ID, new Object[]{id}, ACTOR_ROW_MAPPER);
        if (actorList.isEmpty()) {
            return null;
        }
        return actorList.get(0);
    }

    @Override
    public List<Actor> list() throws DAOException {
        return jdbc.query(SELECT_ALL, new BeanPropertyRowMapper(Actor.class));
    }

    @Override
    public void create(Actor entity) throws IllegalArgumentException, DAOException {
        jdbc.update(INSERT_SQL, new Object[]{entity.getFirstName(), entity.getLastName(), entity.getBirthday(), entity.getCountry(), entity.getPicture()});
    }

    @Override
    public void update(Actor entity) throws IllegalArgumentException, DAOException {
        jdbc.update(UPDATE_SQL, entity.getFirstName(), entity.getLastName(), entity.getBirthday(), entity.getCountry(), entity.getPicture(), entity.getId());
    }

    @Override
    public void delete(Actor entity) throws DAOException {
        jdbc.update(DELETE_SQL, entity.getId());
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
}
