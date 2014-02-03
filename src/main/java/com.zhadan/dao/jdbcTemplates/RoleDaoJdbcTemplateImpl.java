package com.zhadan.dao.jdbcTemplates;

import com.zhadan.bean.Role;
import com.zhadan.dao.interfaces.RoleDao;
import com.zhadan.exceptions.DAOException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/3/13
 * Time: 10:43 AM
 */
@Repository
public class RoleDaoJdbcTemplateImpl implements RoleDao {
    private static final RowMapper<Role> ROLE_ROW_MAPPER = new RowMapper<Role>() {

        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setUserId(resultSet.getInt("userId"));
            role.setRoleName(resultSet.getString("roleName"));
            return role;
        }
    };
    private JdbcTemplate jdbc;

    @Override
    public void insert(Role role) throws IllegalArgumentException, DAOException {
        jdbc.update(INSERT_SQL, role.getUserId(), role.getRoleName());
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
}
