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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = "/movie/{id}")
    public String toGet(@PathVariable(value = "id") Long id, Model model) throws SQLException {

        Movie movie = movieService.getElementById(id);
        Collection shedules = sheduleService.getCinemasByMovieId(id);

        Iterator iterator = shedules.iterator();
        HashMap cinemasShed = new HashMap();

//        SimpleDateFormat formatDate = new SimpleDateFormat("'Date: 'dd.MM.yyyy' Time: ' kk:yy");

        while (iterator.hasNext()) {

            Shedule shedule = (Shedule) iterator.next();
            Long cinemaId = shedule.getCinemaId();
            Cinema cinema = cinemaService.getElementById(cinemaId);

            Collection sheds = sheduleService.getSessionsStart(id, cinemaId);
//            Iterator itr = sheds.iterator();
//            List dates = new ArrayList();
//            while (itr.hasNext()) {
//                String date = formatDate.format(shedule.getDateOfSession().getTime());
//                dates.add(date);
//            }

            cinemasShed.put(cinema, sheds);
        }

        model.addAttribute("movie", movie);
        model.addAttribute("shed", cinemasShed);

        return "movie";
    }

}
