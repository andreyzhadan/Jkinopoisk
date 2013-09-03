package com.zhadan.controller;

import com.zhadan.bean.Actor;
import com.zhadan.dao.interfaces.ActorDao;
import com.zhadan.validation.ActorValidator;
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
 * Date: 8/30/13
 * Time: 12:13 PM
 */
@Controller
public class ActorController {
    @Autowired
    private ActorDao actorDao;
    @Autowired
    private ActorValidator actorValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(actorValidator);
    }

    @RequestMapping(value = "/addActor", method = RequestMethod.GET)
    public String addActor(Model model) {
        model.addAttribute("actor", new Actor());
        return "actorAdd";
    }

    @RequestMapping(value = "/addActor", method = RequestMethod.POST)
    public String addingActor(@ModelAttribute("actor") @Valid Actor actor, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/actorAdd";
        }
        actorDao.create(actor);
        return "redirect:/actors";
    }

    @RequestMapping(value = "/deleteActor/{actorId}", method = RequestMethod.GET)
    public String deleteActor(@PathVariable("actorId") Integer actorId) {
        actorDao.delete(actorDao.find(actorId));
        return "redirect:/actors";
    }

    @RequestMapping(value = "/editActor/{actorId}", method = RequestMethod.GET)
    public String editActor(Model model, @PathVariable("actorId") Integer actorId) {
        Actor actor = actorDao.find(actorId);
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

    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public String listOfActors(Model model) {
        model.addAttribute("actors", actorDao.list());
        return "actors";
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
            userArray.put(actorJSON);
        }
        return userArray.toString();
    }
}
