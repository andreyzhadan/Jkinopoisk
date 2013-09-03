package com.zhadan.controller;

import com.zhadan.bean.User;
import com.zhadan.dao.interfaces.UserDao;
import com.zhadan.validation.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/2/13
 * Time: 2:34 PM
 */
@Controller
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthValidator authValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(authValidator);
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn() {
        return "signIn";
    }

    @RequestMapping(value = "/signInFailed", method = RequestMethod.GET)
    public String signInFailed(ModelMap model) {
        model.addAttribute("error", true);
        return "signIn";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(ModelMap model) {
        return "signIn";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp(ModelMap model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUpping(@ModelAttribute("user") @Valid User user, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/signUp";
        }
        userDao.create(user);
        return "redirect:/home";
    }
}
