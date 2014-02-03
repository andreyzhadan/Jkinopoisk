package com.zhadan.dao.interfaces;

import com.zhadan.bean.Actor;


/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/6/13
 * Time: 10:06 AM
 */
public interface ActorDao extends BasicDao<Actor> {
    //    public static final String HQL_SELECT_ACTORS_LAZY = "select id, firstName, lastName, birthday, country from Actor order by firstName";
    public static final String HQL_COUNT_ACTORS = "select count(*) from Actor";
    public static final String SELECT_ALL = "select * from actor";
    public static final String SELECT_BY_ID = "select * from actor where id=?";
    public static final String INSERT_SQL = "insert into actor (firstName,lastName,birthday,country,picture) values (?,?,?,?,?)";
    public static final String UPDATE_SQL = "update actor set firstName=?,lastName=?,birthday=?,country=?,picture=? where id=?";
    public static final String DELETE_SQL = "delete from actor where id=?";
}
