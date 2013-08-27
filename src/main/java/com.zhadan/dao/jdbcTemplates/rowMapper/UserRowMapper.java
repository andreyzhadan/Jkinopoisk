package com.zhadan.dao.jdbcTemplates.rowMapper;

import com.zhadan.dao.jdbcTemplates.rsExtractor.UserResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/27/13
 * Time: 10:30 AM
 */
public class UserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        UserResultSetExtractor extractor = new UserResultSetExtractor();
        return extractor.extractData(resultSet);
    }
}
