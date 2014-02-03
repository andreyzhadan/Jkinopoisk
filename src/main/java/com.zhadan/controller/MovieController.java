package com.zhadan.controller;

import com.zhadan.bean.Actor;
import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.ActorDao;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.editor.ActorEditor;
import com.zhadan.utils.Converter;
import com.zhadan.validation.MovieValidator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
    private ActorDao actorDao;
    @Autowired
    private MovieValidator movieValidator;
    @Autowired
    private ActorEditor actorEditor;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(movieValidator);
        binder.registerCustomEditor(Actor.class, actorEditor);
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("allActors", actorDao.list());
        return "/movieAdd";
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String addingMovie(@ModelAttribute("movie") @Valid Movie movie, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/movieAdd";
        }
        movieDao.insert(movie);
        return "redirect:/movies";
    }

    @RequestMapping(value = "/convertMoviesToMahoutData", method = RequestMethod.GET)
    public
    @ResponseBody
    String convertMoviesToMahoutData() {
        Converter.convertMovies(movieDao);
        return "All movies has been converted";
    }

    @RequestMapping(value = "/deleteMovie/{movieId}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("movieId") Integer movieId) {
        movieDao.delete(movieDao.find(movieId));
        return "redirect:/movies";
    }

    @RequestMapping(value = "/editMovie/{movieId}", method = RequestMethod.GET)
    public String editMovie(Model model, @PathVariable("movieId") int movieId) {
        Movie movie = movieDao.find(movieId);
        model.addAttribute("movie", movie);
        model.addAttribute("allActors", actorDao.list());
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
    @RequestMapping(value = "/movies/prev", method = RequestMethod.GET)
    public String listOfMoviesPrev(HttpServletRequest request) {
        Integer offset = Integer.parseInt(String.valueOf(request.getSession().getAttribute("offset")));
        Integer limit = Integer.parseInt(String.valueOf(request.getSession().getAttribute("limit")));
        int newOffset = offset - limit;
        if (newOffset <= 0) {
            newOffset = 0;
        }
        return "redirect:/movies?offset=" + newOffset + "&limit=" + limit;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String listOfMovies(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(value = "param", required = false, defaultValue = "empty") String param,
                               Model model, HttpServletRequest request) {
        request.getSession().setAttribute("offset", String.valueOf(offset));
        request.getSession().setAttribute("limit", String.valueOf(limit));
        if (offset == 0) {
            param = "prev";
        }
        request.getSession().setAttribute("param", param);
        model.addAttribute("movies", movieDao.list(offset, limit));
        return "movies";
    }

    @RequestMapping(value = "/movies/next", method = RequestMethod.GET)
    public String listOfMoviesNext(HttpServletRequest request) {
        Integer offset = Integer.parseInt(String.valueOf(request.getSession().getAttribute("offset")));
        Integer limit = Integer.parseInt(String.valueOf(request.getSession().getAttribute("limit")));
        int size = movieDao.getSize();
        int newOffset = offset + limit;
        String param = "empty";
        if (newOffset + limit > size) {
            param = "next";
        }
        if (newOffset > size) {
            newOffset = offset;
        }
        return "redirect:/movies?offset=" + newOffset + "&limit=" + limit + "&param=" + param;
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
            movieJSON.put("picture", movie.getPicture());
            userArray.put(movieJSON);
        }
        return userArray.toString();
    }

    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }
}
