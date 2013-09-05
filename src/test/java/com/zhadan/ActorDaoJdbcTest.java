package com.zhadan;

import com.zhadan.bean.Actor;
import com.zhadan.dao.interfaces.ActorDao;
import com.zhadan.dao.jdbc.ActorDaoJdbcImpl;
import com.zhadan.exceptions.DAOException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.zhadan.utils.DatabaseUtils.insertData;
import static com.zhadan.utils.DatabaseUtils.recreateTable;
import static org.apache.log4j.Logger.getLogger;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/6/13
 * Time: 10:27 AM
 */
public class ActorDaoJdbcTest {

    private static final String url = "jdbc:h2:jkinopoisk";
    private static final Logger logger = getLogger(ActorDaoJdbcTest.class.getSimpleName());
    private static ActorDaoJdbcImpl actorDao;
    private static BasicDataSource dataSource;

    @BeforeClass
    public static void beforeSetUp() {
        logger.debug("Before setUp method in actorDaoTest");
        actorDao = new ActorDaoJdbcImpl();
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        actorDao.setDataSource(dataSource);
    }

    @Before
    public void setUp() throws Exception {
        logger.debug("SetUp method in actorDaoTest");
        String actorCreateScript = "CREATE TABLE actor(id INT PRIMARY KEY AUTO_INCREMENT,firstName VARCHAR(45),lastName VARCHAR(45),birthday INT,country VARCHAR(45))";
        recreateTable(dataSource, actorCreateScript, "actor");
        String actorInsertScript = "insert into actor (firstName,lastName,birthday,country) values ('Brad','Pitt',1963,'USA'),('Jason','Statham',1967,'England'),('John','Travolta',1954,'USA'),('Scarlett','Johansson',1984,'USA'),('Bruce','Willis',1955,'Germany'),('Morgan','Freeman',1937,'USA'),('Vinnie','Jones',1965,'England')";
        insertData(dataSource, actorInsertScript);
    }

    @Test
    public void testFind() throws Exception {
        //find actor
        Actor actor = actorDao.find(1);
        assertThat("find actor is not null", actor, notNullValue());
        assertThat("find actor with id = 1", actor.getCountry(), equalTo("USA"));

        actor = actorDao.find(4);
        assertThat("find actor is not null", actor, notNullValue());
        assertThat("find actor with id = 1", actor.getFirstName(), equalTo("Scarlett"));

        actor = actorDao.find(50);
        assertThat("find non existing actor", actor, nullValue());
    }

    @Test
    public void testList() throws Exception {
        List<Actor> actors = actorDao.list();
        assertThat("number of actors", actors.size(), is(7));
    }

    @Test
    public void testCreateAndList() throws Exception {
        //insert 3 diff actors
        Actor actor = new Actor();
        actor.setFirstName("Andrey");
        actor.setLastName("Zhadan");
        actor.setBirthday(1991);
        actor.setCountry("Ukraine");
        actorDao.create(actor);

        actor.setCountry("Russia");
        actorDao.create(actor);

        actor.setCountry("USA");
        actorDao.create(actor);

        List<Actor> actors = actorDao.list();
        assertThat("number of actors", actors.size(), is(10));
    }

    @Test(expected = DAOException.class)
    public void testDeleteNonExisting() throws Exception {
        //delete not existing actor
        Actor actor = new Actor();
        actor.setId(50);
        actorDao.delete(actor);
    }

    @Test
    public void testDeleteAndList() throws Exception {
        actorDao.delete(actorDao.find(1));
        List<Actor> actors = actorDao.list();
        assertThat("number of actors", actors.size(), is(6));
    }

    @Test
    public void testDeleteAllAndList() throws Exception {
        for (int i = 1; i <= 7; i++) {
            actorDao.delete(actorDao.find(i));
        }
        List<Actor> actors = actorDao.list();
        assertThat("number of actors", actors.size(), is(0));
    }

    @Test
    public void testUpdate() throws Exception {
        Actor actor = new Actor();
        actor.setId(2);
        actor.setCountry("Canada");
        actor.setBirthday(1970);
        actorDao.update(actor);

        actor = actorDao.find(2);
        assertThat("update actor", actor.getCountry(), equalTo("Canada"));
        assertThat("update actor", actor.getBirthday(), equalTo(1970));
    }

    @Test(expected = DAOException.class)
    public void testUpdateNonExisting() throws Exception {
        Actor actor = new Actor();
        actor.setId(50);
        actor.setCountry("Madagascar");
        actorDao.update(actor);
    }
}
