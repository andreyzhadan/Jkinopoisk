package com.zhadan.dao.jdbc;

import com.zhadan.bean.Role;
import com.zhadan.dao.interfaces.RoleDao;
import com.zhadan.dao.interfaces.UserDao;
import com.zhadan.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.zhadan.utils.DatabaseUtils.close;
import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/3/13
 * Time: 10:40 AM
 */
@Repository
public class RoleDaoJdbcImpl implements RoleDao {
    private static final Logger logger = getLogger(RoleDaoJdbcImpl.class.getSimpleName());
    @Autowired
    private DataSource dataSource;

    @Override
    public void create(Role role) throws IllegalArgumentException, DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(INSERT_SQL);
            ps.setInt(1, role.getUserId());
            ps.setString(2, role.getRoleName());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            close(connection, ps, rs);
        }
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
