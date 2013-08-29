package com.zhadan;

import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.dao.jdbc.MovieDaoJdbcImpl;
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
 * Date: 8/28/13
 * Time: 11:33 AM
 */
public class MovieDaoJdbcTest {
    private static final String url = "jdbc:h2:jkinopoisk";
    private static final Logger logger = getLogger(MovieDaoJdbcTest.class.getSimpleName());
    private static MovieDao movieDao;
    private static BasicDataSource dataSource;

    @BeforeClass
    public static void beforeSetUp() {
        logger.debug("Before setUp method in movieDaoTest");
        movieDao = new MovieDaoJdbcImpl();
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        movieDao.setDataSource(dataSource);
    }

    @Before
    public void setUp() throws Exception {
        logger.debug("SetUp method in movieDaoTest");
        String movieCreateScript = "CREATE TABLE movie ( id int(11) NOT NULL AUTO_INCREMENT,  name varchar(45) DEFAULT NULL,russianName varchar(45) DEFAULT NULL,  year int(11) DEFAULT NULL,  country varchar(45) DEFAULT NULL,rating float DEFAULT NULL,  slogan varchar(100) DEFAULT NULL,  picture varchar(100) DEFAULT NULL,PRIMARY KEY (id)) ENGINE=InnoDB AUTO_INCREMENT=13";
        recreateTable(dataSource, movieCreateScript, "movie");
        String movieInsertScript = "INSERT INTO movie(name,russianName,year,country,rating,slogan,picture)" +
                "VALUES(" + "'Lock, Stock and Two Smoking Barrels',\n" +
                "'Карты, деньги, два ствола',\n" +
                "1998,\n" +
                "'England',\n" +
                "8.607,\n" +
                "'They lost half a million at cards but they have still got a few tricks up their sleeve',\n" +
                "'http://st.kinopoisk.ru/images/film_big/522.jpg')," +
                "('Pulp Fiction',\n" +
                "'Криминальное чтиво',\n" +
                "1994,\n" +
                "'USA',\n" +
                "8.655,\n" +
                "'Just because you are a character does not mean you have character.',\n" +
                "'http://st.kinopoisk.ru/images/film_big/342.jpg'),\n" +
                "('Snatch.',\n" +
                "'Большой куш',\n" +
                "2000,\n" +
                "'England',\n" +
                "8.551,\n" +
                "'Stealing stones is hazardous.',\n" +
                "'http://st.kinopoisk.ru/images/film_big/526.jpg');";
        insertData(dataSource, movieInsertScript);

    }

    @Test
    public void testFind() throws Exception {
        //find movie
        Movie movie = movieDao.find(1);
        assertThat("find movie is not null", movie, notNullValue());
        assertThat("find movie with id = 1", movie.getCountry(), equalTo("England"));

        movie = movieDao.find(2);
        assertThat("find movie is not null", movie, notNullValue());
        assertThat("find movie with id = 1", movie.getName(), equalTo("Pulp Fiction"));

        movie = movieDao.find(50);
        assertThat("find non existing movie", movie, nullValue());
    }

    @Test
    public void testList() throws Exception {
        List<Movie> movies = movieDao.list();
        assertThat("number of movies", movies.size(), is(3));
    }

    @Test
    public void testCreateAndList() throws Exception {
        //insert 3 diff movies
        Movie movie = new Movie();
        movie.setName("Red");
        movie.setRussianName("РЭД");
        movie.setRating(7.277f);
        movie.setYear(2010);
        movie.setCountry("USA");
        movieDao.create(movie);

        movie.setCountry("Russia");
        movieDao.create(movie);

        movie.setCountry("Canada");
        movieDao.create(movie);

        List<Movie> movies = movieDao.list();
        assertThat("number of movies", movies.size(), is(6));
    }

    @Test(expected = DAOException.class)
    public void testDeleteNonExisting() throws Exception {
        //delete not existing movie
        Movie movie = new Movie();
        movie.setId(50);
        movieDao.delete(movie);
    }

    @Test
    public void testDeleteAndList() throws Exception {
        movieDao.delete(movieDao.find(1));
        List<Movie> movies = movieDao.list();
        assertThat("number of movies", movies.size(), is(2));
    }

    @Test
    public void testDeleteAllAndList() throws Exception {
        for (int i = 1; i <= 3; i++) {
            movieDao.delete(movieDao.find(i));
        }
        List<Movie> movies = movieDao.list();
        assertThat("number of movies", movies.size(), is(0));
    }

    @Test
    public void testUpdate() throws Exception {
        Movie movie = new Movie();
        movie.setId(2);
        movie.setName("Red");
        movie.setRussianName("РЭД");
        movie.setRating(7.277f);
        movie.setYear(2010);
        movie.setCountry("USA");
        movie.setSlogan("Бывших агентов не бывает");
        movieDao.update(movie);

        movie = movieDao.find(2);
        assertThat("update movie", movie.getCountry(), equalTo("USA"));
        assertThat("update movie", movie.getYear(), equalTo(2010));
    }

    @Test(expected = DAOException.class)
    public void testUpdateNonExisting() throws Exception {
        Movie movie = new Movie();
        movie.setId(50);
        movie.setCountry("Madagascar");
        movieDao.update(movie);
    }
}
