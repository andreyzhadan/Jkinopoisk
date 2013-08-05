package com.zhadan.dao;

import com.zhadan.bean.Movie;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.08.13
 * Time: 22:33
 */
public interface MovieDao {    //the same notes as in ActorDao
    public void addMovie(Movie movie);

    public List<Movie> listMovies();

    public Movie findById(Integer id);

    public void removeMovie(Integer id);
}
