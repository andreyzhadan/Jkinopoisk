package com.zhadan.dao.hibernate;

import com.zhadan.bean.User;
import com.zhadan.dao.interfaces.BasicDao;
import com.zhadan.exceptions.DAOException;
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
    //@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User find(Integer id) throws DAOException {
        User user = (User) getCurrentSession().get(User.class, id);
        return user;
    }

    @Override
    public List<User> list() throws DAOException {
        return getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void create(User entity) throws IllegalArgumentException, DAOException {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(User entity) throws IllegalArgumentException, DAOException {
        User userToUpdate = find(entity.getId());
        userToUpdate.setUserName(entity.getUserName());
        userToUpdate.setPassword(entity.getPassword());
        getCurrentSession().update(userToUpdate);
    }

    @Override
    public void delete(User entity) throws DAOException {
        getCurrentSession().delete(entity);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}