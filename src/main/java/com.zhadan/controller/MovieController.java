package com.zhadan.controller;

import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/28/13
 * Time: 4:05 PM
 */
@Controller
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String showMoviePage(ModelMap model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("movies", movieDao.list());
        return "movies";
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String addMovie(@ModelAttribute("movie") Movie movie, BindingResult result) {
        movieDao.create(movie);
        return "redirect:/";
    }

    @RequestMapping("/delete/{movieId}")
    public String deleteMovie(@PathVariable("movieId") Integer movieId) {
        movieDao.delete(movieDao.find(movieId));
        return "redirect:/";
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public
    @ResponseBody
    String listMoviesJson(ModelMap model) throws JSONException {
        JSONArray userArray = new JSONArray();
        for (Movie movie : movieDao.list()) {
            JSONObject movieJSON = new JSONObject();
            movieJSON.put("id", movie.getId());
            movieJSON.put("name", movie.getName());
            movieJSON.put("russianName", movie.getRussianName());
            movieJSON.put("year", movie.getYear());
            userArray.put(movieJSON);
        }
        return userArray.toString();
    }
}
