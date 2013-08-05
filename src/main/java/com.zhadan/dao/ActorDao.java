package com.zhadan.dao;

import com.zhadan.bean.Actor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/4/13
 * Time: 1:17 PM
 */
public interface ActorDao {
    //throws own exceptions
    public void addActor(Actor actor);

    public List<Actor> listActors();

    public Actor findById(Integer id);

    public void removeActor(Integer id);
}
