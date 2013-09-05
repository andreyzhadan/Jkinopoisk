package com.zhadan.dao.hibernate;

import com.zhadan.bean.Actor;
import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.ActorDao;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.exceptions.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/5/13
 * Time: 10:32 AM
 */
@Repository
@Transactional
public class ActorDaoHibernateImpl implements ActorDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Actor entity) throws IllegalArgumentException, DAOException {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Actor entity) throws IllegalArgumentException, DAOException {
        Actor actorToUpdate = find(entity.getId());
        actorToUpdate.setFirstName(entity.getFirstName());
        actorToUpdate.setLastName(entity.getLastName());
        actorToUpdate.setBirthday(entity.getBirthday());
        actorToUpdate.setCountry(entity.getCountry());
        actorToUpdate.setPicture(entity.getPicture());
        getCurrentSession().update(actorToUpdate);
    }

    @Override
    public Actor find(Integer id) throws DAOException {
        Actor actor = (Actor) getCurrentSession().get(Actor.class, id);
        return actor;
    }

    @Override
    public List<Actor> list() throws DAOException {
        return getCurrentSession().createQuery("from Actor").list();
    }

    @Override
    public void delete(Actor entity) throws DAOException {
        getCurrentSession().delete(entity);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}