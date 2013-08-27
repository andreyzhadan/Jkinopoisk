package com.zhadan.dao.jdbcTemplates;

import com.zhadan.bean.User;
import com.zhadan.dao.interfaces.BasicDao;
import com.zhadan.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/27/13
 * Time: 10:16 AM
 */
//@Repository
public class UserDaoJdbcTemplateImpl implements BasicDao<User> {
    private JdbcTemplate jdbc;


    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public User find(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<User> list() throws DAOException {
        return null;
    }

    @Override
    public void create(User entity) throws IllegalArgumentException, DAOException {

    }

    @Override
    public void update(User entity) throws IllegalArgumentException, DAOException {
    }

    @Override
    public void delete(User entity) throws DAOException {
    }
}
