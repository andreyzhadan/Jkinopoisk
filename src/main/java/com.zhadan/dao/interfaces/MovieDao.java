package com.zhadan.dao.interfaces;

import com.zhadan.bean.Movie;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/6/13
 * Time: 10:07 AM
 */
public interface MovieDao extends BasicDao<Movie> {
    public static final String SELECT_ALL = "select * from movie";
    public static final String SELECT_BY_ID = "select * from movie where id=?";
    public static final String INSERT_SQL = "insert into movie (name,russianName,rating,slogan,year,country) values (?,?,?,?,?,?)";
    public static final String UPDATE_SQL = "update movie set name=?,russianName=?,rating=?,slogan=?,year=?,country=? where id=?";
    public static final String DELETE_SQL = "delete from movie where id=?";

    public void setDataSource(DataSource dataSource);
}
