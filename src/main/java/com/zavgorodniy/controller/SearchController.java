package com.zavgorodniy.controller;

import com.zavgorodniy.model.Search;
import com.zavgorodniy.service.CinemaService;
import com.zavgorodniy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class SearchController {

    @Autowired
    CinemaService cinemaService;

    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String toSearch(HttpServletRequest request, Model model) throws SQLException {

        String req = request.getParameter("req");
        req = "%" + req + "%";

        model.addAttribute("search", new Search());
        model.addAttribute("cinemas", cinemaService.toSearch(req));
        model.addAttribute("movies", movieService.toSearch(req));

        return "search";
    }
}
