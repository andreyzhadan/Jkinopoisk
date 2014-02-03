package com.zhadan.dao.hibernate;

import com.zhadan.bean.Recommendation;
import com.zhadan.dao.interfaces.RecommendationDao;
import com.zhadan.exceptions.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 20.01.14
 * Time: 1:07
 */
@Repository
@Transactional
public class RecommendationDaoHibernateImpl implements RecommendationDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Recommendation find(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Recommendation> list() throws DAOException {
        return list(0, 0);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Recommendation> list(int offset, int limit) throws DAOException {
        return getCurrentSession().createQuery(HQL_SELECT_ALL).setFirstResult(offset).setMaxResults(limit).list();
    }

    @Override
    public void insert(Recommendation entity) throws IllegalArgumentException, DAOException {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Recommendation entity) throws IllegalArgumentException, DAOException {

    }

    @Override
    public void delete(Recommendation entity) throws DAOException {

    }

    @Override
    public int getSize() {
        return ((Long) getCurrentSession().createQuery(HQL_COUNT_RECOMMENDATIONS).uniqueResult()).intValue();
    }

    @Override
    public void batchInsert(List<Recommendation> recommendations) throws IllegalArgumentException, DAOException {
        for (Recommendation recommendation : recommendations) {
            getCurrentSession().save(recommendation);
        }
    }
}
