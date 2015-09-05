package com.zavgorodniy.controller;

import com.zavgorodniy.entity.Cinema;
import com.zavgorodniy.entity.Movie;
import com.zavgorodniy.entity.Shedule;
import com.zavgorodniy.model.Visitor;
import com.zavgorodniy.service.CinemaService;
import com.zavgorodniy.service.MovieService;
import com.zavgorodniy.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = "/home")
    public String toHome(HttpServletRequest request, Model model) throws SQLException {

        Visitor visitor = new Visitor();

        if(request.getMethod().equals("POST")) {

            String admin = "admin";
            String name = request.getParameter("name").trim();
            String lastName = request.getParameter("lastName").trim();

            if (name.equals(admin) && lastName.equals(admin)) {
                visitor.setCheckAdmin(true);
            } else {
                visitor.setCheckAdmin(false);
                visitor.setName(name);
                visitor.setLastName(lastName);
            }

            request.getSession().setAttribute("visitor", visitor);
        }

        HashSet<Movie> movies = new HashSet<>();
        HashSet<Cinema> cinemas = new HashSet<>();

        Collection shedules = sheduleService.getAllElements();
        Iterator iterator = shedules.iterator();

        while (iterator.hasNext()) {

            Shedule shedule = (Shedule) iterator.next();

            Movie movie = movieService.getElementById(shedule.getMovieId());
            Cinema cinema = cinemaService.getElementById(shedule.getCinemaId());

            movies.add(movie);
            cinemas.add(cinema);
        }

        model.addAttribute("movies", movies);
        model.addAttribute("cinemas", cinemas);

        return "home";
    }
}
