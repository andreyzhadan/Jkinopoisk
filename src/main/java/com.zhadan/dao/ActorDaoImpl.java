package com.zhadan.dao;

import com.zhadan.bean.Actor;
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
 * User: azhadan
 * Date: 8/4/13
 * Time: 1:17 PM
 */
public class ActorDaoImpl implements ActorDao {
    private static final Logger logger = getLogger(ActorDaoImpl.class.getSimpleName());
    private static final String SELECT_ALL = "select * from actor";
    private static final String SELECT_BY_ID = "select * from actor where id=?";
    private static final String INSERT = "insert into actor (firstName,lastName,birthday,country) values (?,?,?,?)";
    private static final String UPDATE = "update actor set firstName=?,lastName=?,birthday=?,country=? where id=?";
    private static final String DELETE = "delete from actor where id=?";
    private DataSource dataSource;


    public ActorDaoImpl() {
        try {
            // Create initial context JNDI
            // Get datasource from context
            dataSource = (DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/jkinopoisk");
        } catch (NamingException e) {
            logger.error("Naming exception " + e.getMessage());
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Actor find(Integer id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Actor actor = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                actor = new Actor();
                actor.setId(rs.getInt("id"));
                actor.setCountry(rs.getString("country"));
                actor.setFirstName(rs.getString("firstName"));
                actor.setLastName(rs.getString("lastName"));
                actor.setBirthday(rs.getInt("birthday"));
                logger.debug("You find actor " + actor);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
        return actor;
    }

    @Override
    public List<Actor> list() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Actor> actors = new ArrayList<Actor>();
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL);
            while (rs.next()) {
                Actor actor = new Actor();
                actor.setId(rs.getInt("id"));
                actor.setCountry(rs.getString("country"));
                actor.setFirstName(rs.getString("firstName"));
                actor.setLastName(rs.getString("lastName"));
                actor.setBirthday(rs.getInt("birthday"));
                actors.add(actor);
            }
            logger.debug(Arrays.asList(actors));
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, statement, rs);
        }
        return actors;
    }

    @Override
    public void create(Actor entity) throws IllegalArgumentException, DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getBirthday());
            ps.setString(4, entity.getCountry());
            int affectedRows = ps.executeUpdate();
            logger.debug("Inserted " + affectedRows + " entity with values " + entity);
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
    }

    @Override
    public void update(Actor entity) throws IllegalArgumentException, DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getBirthday());
            ps.setString(4, entity.getCountry());
            ps.setInt(5, entity.getId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating user failed, no rows affected.");
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
    }

    @Override
    public void delete(Actor entity) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(DELETE);
            ps.setInt(1, entity.getId());
            int affectedRows = ps.executeUpdate();
            logger.debug("You are trying to delete actor");
            if (affectedRows == 0) {
                throw new DAOException("Updating user failed, no rows affected.");
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
    }

}
