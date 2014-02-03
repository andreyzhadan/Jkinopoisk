package com.zhadan.utils;

import com.zhadan.bean.Movie;
import com.zhadan.bean.Recommendation;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.dao.interfaces.RecommendationDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 25.01.14
 * Time: 11:01
 */
public final class Converter {

    public static void convertMovies(MovieDao movieDao) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("movies.dat"));
            String line = br.readLine();
            List<Movie> movies = new ArrayList<Movie>();
            while (line != null) {
                int i1 = line.indexOf("::");
                int i2 = line.lastIndexOf(")");

                int id = Integer.parseInt(line.substring(0, i1));
                String name = line.substring(i1 + 2, i2 - 6);
                int year = Integer.parseInt(line.substring(i2 - 4, i2));

                Movie movie = new Movie(id, name, year);
                movies.add(movie);
                line = br.readLine();
            }
            movieDao.batchInsert(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertRecommendations(RecommendationDao recommendationDao) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("ratingsForMahout.dat"));
            String line = br.readLine();
//            List<Recommendation> recommendations = new ArrayList<Recommendation>();
            while (line != null) {
                int i1 = line.indexOf(",");
                int i2 = line.lastIndexOf(",");

                int user_id = Integer.parseInt(line.substring(0, i1));
                int movie_id = Integer.parseInt(line.substring(i1 + 1, i2));
                int preference = Integer.parseInt(line.substring(i2 + 1, i2 + 2));

                Recommendation recommendation = new Recommendation(user_id, movie_id, preference);
//                recommendations.add(recommendation);
                recommendationDao.insert(recommendation);
                line = br.readLine();
            }
            //recommendationDao.batchInsert(recommendations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
