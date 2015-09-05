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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Calendar;

@Controller
public class AdminController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String toPos(Model model) throws SQLException {

        model.addAttribute("movies", movieService.getAllElements());
        model.addAttribute("cinemas", cinemaService.getAllElements());

        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String addShedule(HttpServletRequest request, Model model) throws SQLException {

        Shedule shedule = new Shedule();
        Long cinemaId = Long.valueOf(request.getParameter("cinemaId"));
        Long movieId = Long.valueOf(request.getParameter("movieId"));
        Calendar sessionStart = Calendar.getInstance();
        int ticketsNumber = cinemaService.getElementById(cinemaId).getNumberOfSeats();

        int year = Integer.valueOf(request.getParameter("year"));
        int month = Integer.valueOf(request.getParameter("month")) - 1;
        int day = Integer.valueOf(request.getParameter("day"));
        int hours = Integer.valueOf(request.getParameter("hours"));
        int minutes = Integer.valueOf(request.getParameter("minutes"));
        sessionStart.set(year, month, day, hours, minutes);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(sessionStart);
        shedule.setAvailableTicketsNumber(ticketsNumber);

        Long sheduleId = sheduleService.addElement(shedule);

        model.addAttribute("sheduleId", sheduleId);
        model.addAttribute("movies", movieService.getAllElements());
        model.addAttribute("cinemas", cinemaService.getAllElements());

        return "admin";
    }

    @RequestMapping(value = "/admin/add-cinema", method = RequestMethod.POST)
         public String addCinema(HttpServletRequest request, Model model) throws SQLException {

        Cinema cinema = new Cinema();
        cinema.setName(request.getParameter("cinemaName"));
        cinema.setLocation(request.getParameter("location"));
        cinema.setNumberOfSeats(Integer.valueOf(request.getParameter("seats")));

        Long cinemaId = cinemaService.addElement(cinema);

        model.addAttribute("cinemaId", cinemaId);
        model.addAttribute("movies", movieService.getAllElements());
        model.addAttribute("cinemas", cinemaService.getAllElements());

        return "admin";
    }

    @RequestMapping(value = "/admin/add-movie", method = RequestMethod.POST)
    public String addMovie(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) throws SQLException {

        Long movieId;
        String message;

        String imgDir = file.getOriginalFilename();
        imgDir = imgDir.replace(" ", "_");

        String imgDBDir = "../img/posters/" + imgDir;
        imgDir = "src/main/webapp/img/posters/" + imgDir;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(imgDir)));
                stream.write(bytes);
                stream.close();
                message = "File " + imgDir + " uploaded !";
            } catch (Exception e) {
                message = "Error, the file was not uploaded " + imgDir + " => " + e.getMessage();
            }
        } else {
            message = "Error, the file was not uploaded " + imgDir + " file empty.";
        }

        System.out.println();
        System.out.println(message);
        System.out.println();

        Movie movie = new Movie();
        movie.setName(request.getParameter("movieName"));
        movie.setDescription(request.getParameter("description"));
        movie.setMovieDuration(Integer.valueOf(request.getParameter("duration")));
        movie.setImgDirectory(imgDBDir);

        movieId = movieService.addElement(movie);

        model.addAttribute("movieId", movieId);
        model.addAttribute("movies", movieService.getAllElements());
        model.addAttribute("cinemas", cinemaService.getAllElements());

        return "admin";
    }
}
