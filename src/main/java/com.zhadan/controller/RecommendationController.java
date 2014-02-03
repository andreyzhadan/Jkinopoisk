package com.zhadan.controller;

import com.zhadan.dao.interfaces.RecommendationDao;
import com.zhadan.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 20.01.14
 * Time: 1:01
 */
@Controller
public class RecommendationController {

    @Autowired
    private RecommendationDao recommendationDao;

    @RequestMapping(value = "/convertRecommendationsToMahoutData", method = RequestMethod.GET)
    public
    @ResponseBody
    String convertRecommendationsToMahoutData() {
        Converter.convertRecommendations(recommendationDao);
        return "All recommendations has been converted";
    }

    @RequestMapping(value = "/recommendations", method = RequestMethod.GET)
    public String listOfRecommendations(Model model) {
        model.addAttribute("recommendations", recommendationDao.list(0, 10));
        return "recommendations";
    }
}
