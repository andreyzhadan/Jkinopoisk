package com.zhadan.dao.hibernate;

import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
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
 * User: Andrew
 * Date: 23.08.13
 * Time: 11:37
 */
@Repository
@Transactional
public class MovieDaoHibernateImpl implements MovieDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void delete(Movie entity) throws DAOException {
        getCurrentSession().delete(entity);
    }

    @Override
    public int getSize() {
        return ((Long) getCurrentSession().createQuery(HQL_COUNT_MOVIES).uniqueResult()).intValue();
    }

    @Override
    public void update(Movie entity) throws IllegalArgumentException, DAOException {
        Movie movieToUpdate = find(entity.getId());
        movieToUpdate.setName(entity.getName());
        movieToUpdate.setRussianName(entity.getRussianName());
        movieToUpdate.setSlogan(entity.getSlogan());
        movieToUpdate.setYear(entity.getYear());
        movieToUpdate.setCountry(entity.getCountry());
        movieToUpdate.setRating(entity.getRating());
        movieToUpdate.setPicture(entity.getPicture());
        movieToUpdate.setActors(entity.getActors());
        getCurrentSession().update(movieToUpdate);
    }

    @Override
    public Movie find(Integer id) throws DAOException {
        Movie movie = (Movie) getCurrentSession().get(Movie.class, id);
        Hibernate.initialize(movie.getActors());
        return movie;
    }

    @Override
    public List<Movie> list() throws DAOException {
        return list(0, 0);
    }

    @Override
    public List<Movie> list(int offset, int limit) throws DAOException {
        return getCurrentSession().createCriteria(Movie.class).addOrder(Order.desc("russianName")).setFirstResult(offset).setMaxResults(limit).list();
    }

    @Override
    public void insert(Movie entity) throws IllegalArgumentException, DAOException {
        getCurrentSession().save(entity);
    }

    @Override
    public void batchInsert(List<Movie> movies) throws IllegalArgumentException, DAOException {
        for (Movie movie : movies) {
            getCurrentSession().save(movie);
        }
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}