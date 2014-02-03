package com.zhadan.controller;

import com.zhadan.bean.Actor;
import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.ActorDao;
import com.zhadan.dao.interfaces.MovieDao;
import com.zhadan.editor.MovieEditor;
import com.zhadan.validation.ActorValidator;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/30/13
 * Time: 12:13 PM
 */
@Controller
public class ActorController {
    @Autowired
    private ActorDao actorDao;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private ActorValidator actorValidator;
    @Autowired
    private MovieEditor movieEditor;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(actorValidator);
        binder.registerCustomEditor(Movie.class, movieEditor);
    }

    @RequestMapping(value = "/addActor", method = RequestMethod.GET)
    public String addActor(Model model) {
        model.addAttribute("actor", new Actor());
        model.addAttribute("allMovies", movieDao.list());
        return "/actorAdd";
    }

    @RequestMapping(value = "/addActor", method = RequestMethod.POST)
    public String addingActor(@ModelAttribute("actor") @Valid Actor actor, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/actorAdd";
        }
        actorDao.insert(actor);
        return "redirect:/actors";
    }

    @RequestMapping(value = "/deleteActor/{actorId}", method = RequestMethod.GET)
    public String deleteActor(@PathVariable("actorId") Integer actorId) {
        actorDao.delete(actorDao.find(actorId));
        return "redirect:/actors";
    }

    @RequestMapping(value = "/editActor/{actorId}", method = RequestMethod.GET)
    public String editActor(Model model, @PathVariable("actorId") int actorId) {
        Actor actor = actorDao.find(actorId);
        List<Movie> movies = movieDao.list(0, 40);
        model.addAttribute("allMovies", movies);
        model.addAttribute("actor", actor);
        return "/actorEdit";
    }

    @RequestMapping(value = "/editActor", method = RequestMethod.POST)
    public String editingActor(@ModelAttribute("actor") @Valid Actor actor, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/actorEdit";
        }
        actorDao.update(actor);
        return "redirect:/actors";
    }

    @RequestMapping(value = "/actors/prev", method = RequestMethod.GET)
    public String listOfActorsPrev(HttpServletRequest request) {
        Integer offset = Integer.parseInt(String.valueOf(request.getSession().getAttribute("offset")));
        Integer limit = Integer.parseInt(String.valueOf(request.getSession().getAttribute("limit")));
        int newOffset = offset - limit;
        if (newOffset <= 0) {
            newOffset = 0;
        }
        return "redirect:/actors?offset=" + newOffset + "&limit=" + limit;
    }

    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public String listOfActors(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                               @RequestParam(value = "param", required = false, defaultValue = "empty") String param,
                               Model model, HttpServletRequest request) {
        request.getSession().setAttribute("offset", String.valueOf(offset));
        request.getSession().setAttribute("limit", String.valueOf(limit));
        if (offset == 0) {
            param = "prev";
        }
        request.getSession().setAttribute("param", param);
        model.addAttribute("actors", actorDao.list(offset, limit));
        return "actors";
    }

    @RequestMapping(value = "/actors/next", method = RequestMethod.GET)
    public String listOfActorsNext(HttpServletRequest request) {
        Integer offset = Integer.parseInt(String.valueOf(request.getSession().getAttribute("offset")));
        Integer limit = Integer.parseInt(String.valueOf(request.getSession().getAttribute("limit")));
        int size = actorDao.getSize();
        int newOffset = offset + limit;
        String param = "empty";
        if (newOffset + limit > size) {
            param = "next";
        }
        if (newOffset > size) {
            newOffset = offset;
        }
        return "redirect:/actors?offset=" + newOffset + "&limit=" + limit + "&param=" + param;
    }

    @RequestMapping(value = "/api/actors", method = RequestMethod.GET)
    public
    @ResponseBody
    String listOfActorsJson() throws JSONException {
        JSONArray userArray = new JSONArray();
        for (Actor actor : actorDao.list()) {
            JSONObject actorJSON = new JSONObject();
            actorJSON.put("id", actor.getId());
            actorJSON.put("firstName", actor.getFirstName());
            actorJSON.put("lastName", actor.getLastName());
            actorJSON.put("birthday", actor.getBirthday());
            actorJSON.put("country", actor.getCountry());
            actorJSON.put("picture", actor.getPicture());
            userArray.put(actorJSON);
        }
        return userArray.toString();
    }
}
