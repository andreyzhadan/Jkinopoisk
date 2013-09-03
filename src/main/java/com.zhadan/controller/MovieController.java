package com.zhadan.controller;

import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.validation.MovieValidator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    @Autowired
    private MovieValidator movieValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(movieValidator);
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "movieAdd";
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String addingMovie(@ModelAttribute("movie") @Valid Movie movie, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/movieAdd";
        }
        movieDao.create(movie);
        return "redirect:/movies";
    }

    @RequestMapping(value = "/deleteMovie/{movieId}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("movieId") Integer movieId) {
        movieDao.delete(movieDao.find(movieId));
        return "redirect:/movies";
    }

    @RequestMapping(value = "/editMovie/{movieId}", method = RequestMethod.GET)
    public String editMovie(Model model, @PathVariable("movieId") Integer movieId) {
        Movie movie = movieDao.find(movieId);
        model.addAttribute("movie", movie);
        return "/movieEdit";
    }

    @RequestMapping(value = "/editMovie", method = RequestMethod.POST)
    public String editingMovie(@ModelAttribute("movie") @Valid Movie movie, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/movieEdit";
        }
        movieDao.update(movie);
        return "redirect:/movies";
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String listOfMovies(Model model) {
        model.addAttribute("movies", movieDao.list());
        return "movies";
    }

    @RequestMapping(value = "/api/movies", method = RequestMethod.GET)
    public
    @ResponseBody
    String listOfMoviesJson() throws JSONException {
        JSONArray userArray = new JSONArray();
        for (Movie movie : movieDao.list()) {
            JSONObject movieJSON = new JSONObject();
            movieJSON.put("id", movie.getId());
            movieJSON.put("name", movie.getName());
            movieJSON.put("rating", movie.getRating());
            movieJSON.put("russianName", movie.getRussianName());
            movieJSON.put("slogan", movie.getSlogan());
            movieJSON.put("year", movie.getYear());
            userArray.put(movieJSON);
        }
        return userArray.toString();
    }

    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }
}
