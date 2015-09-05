package com.zavgorodniy.controller;

import com.zavgorodniy.entity.Cinema;
import com.zavgorodniy.entity.Movie;
import com.zavgorodniy.entity.Shedule;
import com.zavgorodniy.service.CinemaService;
import com.zavgorodniy.service.MovieService;
import com.zavgorodniy.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.*;

@Controller
public class CinemaController {

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/cinema/{id}")
    public String toTickets(@PathVariable(value = "id") Long id, Model model) throws SQLException {

        Cinema cinema = cinemaService.getElementById(id);

        HashSet<Movie> movies = new HashSet<>();
        Collection shedules = sheduleService.getMoviesByCinemaId(id);
        Iterator iterator = shedules.iterator();

        while (iterator.hasNext()) {

            Shedule shedule = (Shedule) iterator.next();
            Long movieId = shedule.getMovieId();
            Movie movie = movieService.getElementById(movieId);

            movies.add(movie);
        }

        model.addAttribute("movies", movies);
        model.addAttribute("cinema", cinema);

        return "cinema";
    }

}
