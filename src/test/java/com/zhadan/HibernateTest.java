package com.zhadan;

import com.zhadan.bean.User;
import com.zhadan.utils.HibernateUtil;
import org.hibernate.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ge;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 01.07.13
 * Time: 7:50
 */
public class HibernateTest {
    private Session session;
    private Transaction tx;

    @Before
    public void setUp() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();
        this.tx = session.beginTransaction();
    }

    @Test
    public void testSelectById() throws SQLException {
        User user = (User) session.get(User.class, 1);
        System.out.println(user);
    }

    @Test
    public void testSelectAll() throws SQLException {
        Query query = session.createQuery("from User");
        List<User> allUsers = query.list();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectAllByCriteria() throws SQLException {
        Criteria criteria = session.createCriteria(User.class);
        List<User> allUsers = criteria.list();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectWhereCriteria() throws SQLException {
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(eq("login", "andrey"))
                .add(ge("id", 2))
                .addOrder(desc("id"));
        List<User> allUsers = criteria.list();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        User newUser = new User("andrey", "1111");
        session.save(newUser);
        System.out.println(newUser);
    }

    @After
    public void tearDown() throws Exception {
        this.tx.commit();
        this.session.close();
    }
}
