package com.zhadan.dao;

import com.zhadan.bean.Actor;
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
import java.util.Arrays;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/4/13
 * Time: 1:17 PM
 */
public class ActorDaoImpl implements ActorDao {
    private static final Logger logger = getLogger(ActorDaoImpl.class.getName());
    private static final String SELECT_ALL = "select * from actor";
    private static final String SELECT_BY_ID = "select * from actor where id=?";

    public ActorDaoImpl() {
    }

    @Override
    public void addActor(Actor actor) {
    }

    @Override
    public List<Actor> listActors() {
        Connection connection = null;
        PreparedStatement ps = null;  // do we need prepared statement here? Simple Statement is a little bit faster
        ResultSet rs = null;
        List<Actor> actors = new ArrayList<Actor>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jkinopoisk");
            connection = ds.getConnection();

            ps = connection.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Actor actor = new Actor();
                actor.setId(rs.getInt("id"));
                actor.setCountry(rs.getString("country"));
                actor.setFirstName(rs.getString("firstName"));
                actor.setLastName(rs.getString("lastName"));
                actor.setDate(rs.getInt("date"));
                actors.add(actor);
            }
            logger.info(Arrays.asList(actors));
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
        return actors;
    }

    @Override
    public Actor findById(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Actor actor = new Actor();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jkinopoisk");
            connection = ds.getConnection();

            ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                actor.setId(rs.getInt("id"));
                actor.setCountry(rs.getString("country"));
                actor.setFirstName(rs.getString("firstName"));
                actor.setLastName(rs.getString("lastName"));
                actor.setDate(rs.getInt("date"));
                logger.info(actor);
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
        return actor;
    }

    @Override
    public void removeActor(Integer id) {
    }
}
