package com.zhadan.utils;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/6/13
 * Time: 11:27 AM
 */


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.apache.log4j.Logger.getLogger;

/**
 * Utility class for DAO's. This class contains commonly used DAO logic which is been refactored in
 * single static methods. As far it contains a PreparedStatement values setter and several quiet
 * close methods.
 */
public final class DatabaseUtils {
    private static final Logger logger = getLogger(DatabaseUtils.class.getSimpleName());

    public DatabaseUtils() {
    }

    /**
     * Quietly close the Connection, Statement and ResultSet. Any errors will be printed to the logger.
     *
     * @param connection The Connection to be closed quietly.
     * @param statement  The Statement to be closed quietly.
     * @param resultSet  The ResultSet to be closed quietly.
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    /**
     * Quietly close the Connection, Statement and ResultSet. Any errors will be printed to the logger.
     *
     * @param connection The Connection to be closed quietly.
     * @param statement  The Statement to be closed quietly.
     */
    public static void close(Connection connection, Statement statement) {
        close(statement);
        close(connection);
    }

    /**
     * Quietly close the Connection. Any errors will be printed to the logger.
     *
     * @param connection The Connection to be closed quietly.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Closing Connection failed: " + e.getMessage());
            }
        }
    }

    /**
     * Quietly close the Statement. Any errors will be printed to the logger.
     *
     * @param statement The Statement to be closed quietly.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Closing Connection failed: " + e.getMessage());
            }
        }
    }

    /**
     * Quietly close the ResultSet. Any errors will be printed to the logger.
     *
     * @param resultSet The ResultSet to be closed quietly.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Closing Connection failed: " + e.getMessage());
            }
        }
    }

    public static void recreateTable(BasicDataSource basicDataSource, String script, String name) {
        try {
            Connection connection = basicDataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE " + name + " if exists ");
            Statement statement2 = connection.createStatement();
            statement2.execute(script);
            statement.close();
            statement2.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(BasicDataSource basicDataSource, String script) {
        try {
            Connection connection = basicDataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(script);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource createDataSource() {
        DataSource dataSource = null;
        try {
            // Create initial context JNDI
            // Get datasource from context
            dataSource = (DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/jkinopoisk");
        } catch (NamingException e) {
            logger.error("Naming exception " + e.getMessage());
        }
        return dataSource;
    }

}
