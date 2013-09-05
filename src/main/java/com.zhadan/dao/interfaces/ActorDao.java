package com.zhadan.dao.interfaces;

import com.zhadan.bean.Actor;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/6/13
 * Time: 10:06 AM
 */
public interface ActorDao extends BasicDao<Actor> {
    public static final String SELECT_ALL = "select * from actor";
    public static final String SELECT_BY_ID = "select * from actor where id=?";
    public static final String INSERT_SQL = "insert into actor (firstName,lastName,birthday,country,picture) values (?,?,?,?,?)";
    public static final String UPDATE_SQL = "update actor set firstName=?,lastName=?,birthday=?,country=?,picture=? where id=?";
    public static final String DELETE_SQL = "delete from actor where id=?";
}
