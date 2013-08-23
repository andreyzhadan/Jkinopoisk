package com.zhadan.dao.hibernate;

import com.zhadan.bean.Actor;
import com.zhadan.dao.interfaces.ActorDao;
import com.zhadan.exceptions.DAOException;
import org.apache.log4j.Logger;

import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 23.08.13
 * Time: 11:37
 */
//@Repository
public class ActorDaoHibImpl implements ActorDao {
    private static final Logger logger = getLogger(ActorDaoHibImpl.class.getSimpleName());
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }

    @Override
    public Actor find(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Actor> list() throws DAOException {
        return null;
    }

    @Override
    public void create(Actor entity) throws IllegalArgumentException, DAOException {
    }

    @Override
    public void update(Actor entity) throws IllegalArgumentException, DAOException {
    }

    @Override
    public void delete(Actor entity) throws DAOException {
    }
}