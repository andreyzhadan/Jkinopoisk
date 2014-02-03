package com.zhadan.dao.hibernate;

import com.zhadan.bean.Actor;
import com.zhadan.dao.interfaces.ActorDao;
import com.zhadan.exceptions.DAOException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
    public void insert(Actor entity) throws IllegalArgumentException, DAOException {
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
        actorToUpdate.setMovies(entity.getMovies());
        getCurrentSession().update(actorToUpdate);
    }

    @Override
    public Actor find(Integer id) throws DAOException {
        Actor actor = (Actor) getCurrentSession().get(Actor.class, id);
        Hibernate.initialize(actor.getMovies());
        return actor;
    }

    @Override
    public List<Actor> list() throws DAOException {
        return list(0, 0);
    }

    @Override
    public List<Actor> list(int offset, int limit) throws DAOException {
        return getCurrentSession().createCriteria(Actor.class).addOrder(Order.asc("firstName")).setFirstResult(offset).setMaxResults(limit).list();
    }

    @Override
    public void delete(Actor entity) throws DAOException {
        getCurrentSession().delete(entity);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int getSize() {
        return ((Long) getCurrentSession().createQuery(HQL_COUNT_ACTORS).uniqueResult()).intValue();
    }
}