package com.zavgorodniy.controller;

import com.zavgorodniy.entity.Cinema;
import com.zavgorodniy.entity.Movie;
import com.zavgorodniy.entity.Shedule;
import com.zavgorodniy.model.Form;
import com.zavgorodniy.model.Visitor;
import com.zavgorodniy.service.CinemaService;
import com.zavgorodniy.service.MovieService;
import com.zavgorodniy.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class FormController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private CinemaService cinemaService;

    public static Form form = new Form();

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String toMovies(HttpServletRequest request, Model model) throws SQLException {

        Visitor visitor = (Visitor) request.getSession().getAttribute("visitor");

        Long sheduleId = Long.valueOf(request.getParameter("id"));

        form.setSheduleId(sheduleId);

        Shedule shedule = sheduleService.getElementById(sheduleId);

        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("kk:mm");
        Movie movie = movieService.getElementById(shedule.getMovieId());

        form.setMovie(movie);

        Cinema cinema = cinemaService.getElementById(shedule.getCinemaId());

        form.setCinema(cinema);

        Calendar sessionStart = shedule.getDateOfSession();
        Date date = sessionStart.getTime();

        String sessionDate = formatDate.format(date);
        String sessionTime = formatTime.format(date);

        form.setName(visitor.getName());
        form.setLastName(visitor.getLastName());
        form.setStartOfSession(sessionStart);
        form.setSessionDate(sessionDate);
        form.setSessionTime(sessionTime);

        model.addAttribute("form", form);

        return "form";
    }

}
