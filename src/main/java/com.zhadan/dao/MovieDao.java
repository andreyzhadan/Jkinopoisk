package com.zhadan.dao;

import com.zhadan.bean.Movie;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:33
 */
public interface MovieDao {
    //throws own exceptions
    public List<Movie> selectAll();

    public Movie selectById(Integer id);
}
