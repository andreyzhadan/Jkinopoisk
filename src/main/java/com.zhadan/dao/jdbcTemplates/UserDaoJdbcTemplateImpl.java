package com.zhadan.dao.jdbcTemplates;

import com.zhadan.bean.User;
import com.zhadan.dao.interfaces.UserDao;
import com.zhadan.exceptions.DAOException;
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
 * Date: 8/27/13
 * Time: 10:16 AM
 */
@Repository
public class UserDaoJdbcTemplateImpl implements UserDao {
    private static final RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    };
    private JdbcTemplate jdbc;

    @Override
    public void create(User user) throws IllegalArgumentException, DAOException {
        jdbc.update(INSERT_SQL, new Object[]{user.getLogin(), user.getPassword()});
    }

    @Override
    public User findByLogin(String login) {
        List<User> userList = jdbc.query(SELECT_SQL, new Object[]{login}, USER_ROW_MAPPER);
        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User validateUser(String login, String password) {
        if (login == null || password == null) {
            return null;
        }
        User user = findByLogin(login);
        if (user == null || !user.getPassword().equals(password.trim())) {
            return null;
        }
        return user;
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
}
