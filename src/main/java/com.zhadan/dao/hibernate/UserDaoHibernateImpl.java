package com.zhadan.dao.hibernate;

import com.zhadan.bean.User;
import com.zhadan.dao.interfaces.BasicDao;
import com.zhadan.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 23.08.13
 * Time: 11:37
 */
@Repository
public class UserDaoHibernateImpl implements BasicDao<User> {
    private static final Logger logger = getLogger(UserDaoHibernateImpl.class.getSimpleName());
    //@Autowired
    private SessionFactory sessionFactory;

    @Override
    public User find(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<User> list() throws DAOException {
        return getCurrentSession().createQuery("from User").list();
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(User entity) throws IllegalArgumentException, DAOException {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(User entity) throws IllegalArgumentException, DAOException {
    }

    @Override
    public void delete(User entity) throws DAOException {
        getCurrentSession().delete(entity);
    }
}