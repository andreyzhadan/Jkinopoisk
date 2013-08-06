package com.zhadan;

import com.zhadan.bean.Actor;
import com.zhadan.dao.ActorDaoImpl;
import com.zhadan.exceptions.DAOException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.zhadan.utils.DaoUtils.recreateTable;
import static org.apache.log4j.Logger.getLogger;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/6/13
 * Time: 10:27 AM
 */
public class DaoTest {

    private static final String url = "jdbc:h2:jkinopoisk";
    private final Logger logger = getLogger(this.getClass().getSimpleName());
    private ActorDaoImpl actorDao;
    private BasicDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        logger.debug("SetUp method in daoTest");
        actorDao = new ActorDaoImpl();
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        actorDao.setDataSource(dataSource);
    }

    @Test
    public void testMethod() throws Exception {
        String actorCreateScript = "CREATE TABLE actor(id INT PRIMARY KEY AUTO_INCREMENT,firstName VARCHAR(45),lastName VARCHAR(45),birthday INT,country VARCHAR(45))";
        recreateTable(dataSource, actorCreateScript);
        Actor actor = new Actor();
        actor.setFirstName("Andrey");
        actor.setLastName("Zhadan");
        actor.setBirthday(1991);
        //insert 3 diff actors
        actor.setCountry("Ukraine");
        actorDao.create(actor);

        actor.setCountry("Russia");
        actorDao.create(actor);

        actor.setCountry("USA");
        actorDao.create(actor);

        List<Actor> actors = actorDao.list();
        assertThat("number of actors", actors.size(), is(3));

        //try to find no existing actor
        actor = actorDao.find(50);
        assertThat("find actor is not null", actor, nullValue());

        //find actor
        actor = actorDao.find(1);
        assertThat("find actor is not null", actor, notNullValue());
        assertThat("find actor with id = 1", actor.getCountry(), equalTo("Ukraine"));

        //delete actor
        actorDao.delete(actor);
        actors = actorDao.list();
        assertThat("number of actors after deleting", actors.size(), is(2));

        //delete not existing actor
        actor.setId(50);
        boolean flag = true;
        try {
            actorDao.delete(actor);
        } catch (DAOException ex) {
            flag = false;
        }
        assertThat("delete not existing actor", flag, is(false));

        actors = actorDao.list();
        assertThat("number of actors after deleting", actors.size(), is(2));

        //update actor
        actor.setId(2);
        actor.setCountry("Canada");
        actorDao.update(actor);

        actor = actorDao.find(2);
        assertThat("update actor", actor.getCountry(), equalTo("Canada"));

        //update non existing actor
        actor.setId(50);
        actor.setCountry("Madagascar");
        flag = true;
        try {
            actorDao.update(actor);
        } catch (DAOException ex) {
            flag = false;
        }
        assertThat("update not existing actor", flag, is(false));
    }
}
