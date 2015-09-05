package com.zavgorodniy;

import com.zavgorodniy.dao.*;

import com.zavgorodniy.dao.impl.CinemaDAOImpl;
import com.zavgorodniy.dao.impl.MovieDAOImpl;
import com.zavgorodniy.dao.impl.SheduleDAOImpl;
import com.zavgorodniy.dao.impl.TicketDAOImpl;
import com.zavgorodniy.entity.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static CinemaDAO cinemaDAO = new CinemaDAOImpl();
    static MovieDAO movieDAO = new MovieDAOImpl();
    static SheduleDAO sheduleDAO = new SheduleDAOImpl();
    static TicketDAO ticketDAO = new TicketDAOImpl();

    public static void main(String[] args) throws SQLException {

        List<Cinema> cinemaList = generateCinemas();
        for (Cinema cinema : cinemaList) {
            cinemaDAO.addElement(cinema);
        }

        List<Movie> movieList = generateMovies();
        for (Movie movie : movieList) {
            movieDAO.addElement(movie);
        }

        List<Shedule> sheduleList = generateShedules();
        for (Shedule shedule : sheduleList) {
            sheduleDAO.addElement(shedule);
        }

        toBookTicket();

        HibernateUtils.getSessionFactory().close();
    }

    public static void toBookTicket () throws SQLException {

        Movie movie;
        Cinema cinema;
        Shedule shedule;
        Long movieId;
        Long cinemaId;
        Long sheduleId;
        String movieName;
        String cinemaName;
        String personName;
        String personLastName;
        Calendar sessionStart;
        Integer movieDuration;

        SimpleDateFormat formatDateTime = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("kk:mm");

        Collection shedList = sheduleDAO.getAllElements();
        Iterator iterator = shedList.iterator();

        System.out.println("\n________________________________Available movies:________________________________");

        while (iterator.hasNext()) {

            shedule = (Shedule) iterator.next();
            movieId = shedule.getMovieId();
            movieName = movieDAO.getElementById(movieId).getName();

            System.out.println("(ID = " + movieId + ")" + " \"" + movieName + "\"");
        }

        System.out.println("\nTo book a ticket, choose movie id from available:");

        Scanner in = new Scanner(System.in);
        movieId = in.nextLong();

        movie = movieDAO.getElementById(movieId);
        movieName = movie.getName();
        movieDuration = movie.getMovieDuration();

        System.out.println("\nYou choosed \"" + movieName + "\" movie");
        System.out.println("This movie is available in cinemas:\n");

        iterator = shedList.iterator();

        while (iterator.hasNext()) {

            shedule = (Shedule) iterator.next();

            if (shedule.getMovieId().equals(movieId)) {
                cinemaId = shedule.getCinemaId();
                cinemaName = cinemaDAO.getElementById(cinemaId).getName();
                Date date = shedule.getDateOfSession().getTime();

                System.out.println("(ID = " + shedule.getId() + ")" + " \"" + cinemaName + "\" " + formatDateTime.format(date));
            }
        }

        System.out.println("\nChoose shedule id from available:");

        sheduleId = in.nextLong();
        shedule = sheduleDAO.getElementById(sheduleId);
        cinemaId = shedule.getCinemaId();
        cinema = cinemaDAO.getElementById(cinemaId);
        cinemaName = cinema.getName();
        sessionStart = shedule.getDateOfSession();

        System.out.println("\nYou choosed \"" + cinemaName + "\" cinema");


        System.out.println("\nEnter your name:");
        personName = in.next();

        System.out.println("\nand last name:");
        personLastName = in.next();

        System.out.println("\nYour ticket:");
        System.out.println("_______________________________");
        System.out.println("Movie:\t\t\"" +  movieName + "\"");
        System.out.println("Cinema:\t\t\"" +  cinemaName + "\"");
        System.out.println("Date:\t\t" + formatDate.format(sessionStart.getTime()));
        System.out.println("Time:\t\t" + formatTime.format(sessionStart.getTime()));
        System.out.println("Duration:\t" + movieDuration + " min");
        System.out.println("Name:\t\t" +  personName);
        System.out.println("Last name:\t" + personLastName);
        System.out.println("_______________________________");

        System.out.println("Book it? (Y/N)");

        String confirm = in.next().toUpperCase();

        if (confirm.equals("Y")) {
            Ticket ticket = new Ticket();
            ticket.setMovieName(movieName);
            ticket.setCinemaName(cinemaName);
            ticket.setStartOfSession(sessionStart);
            ticket.setMovieDuration(movieDuration);
            ticket.setName(personName);
            ticket.setLastname(personLastName);

            shedule = sheduleDAO.getElementById(sheduleId);
            Integer seats = shedule.getAvailableTicketsNumber();

            if (seats > 0) {
                shedule.setAvailableTicketsNumber(seats - 1);
                sheduleDAO.updateElement(shedule);
                ticketDAO.addElement(ticket);
            }
        }
    }

    private static List<Shedule> generateShedules() throws SQLException {

        List<Shedule> sheduleList = new ArrayList<Shedule>();
        Shedule shedule;
        Long cinemaId;
        Long movieId;
        Cinema cinema;
        Calendar calendar;

        //For cinema id = 1 set shedules
        shedule = new Shedule();
        calendar = Calendar.getInstance();
        cinemaId = 1L;
        movieId = 1L;
        calendar.set(2015, Calendar.JULY, 25, 19, 0);
        cinema = cinemaDAO.getElementById(cinemaId);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(calendar);
        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());

        sheduleList.add(shedule);

        shedule = new Shedule();
        calendar = Calendar.getInstance();
        movieId = 3L;
        calendar.set(2015, Calendar.JULY, 22, 20, 45);
        cinema = cinemaDAO.getElementById(cinemaId);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(calendar);
        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());

        sheduleList.add(shedule);

//        shedule = new Shedule();
//        calendar = Calendar.getInstance();
//        movieId = 5L;
//        calendar.set(2015, Calendar.JULY, 24, 13, 20);
//        cinema = cinemaDAO.getElementById(cinemaId);
//
//        shedule.setCinemaId(cinemaId);
//        shedule.setMovieId(movieId);
//        shedule.setDateOfSession(calendar);
//        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());
//
//        sheduleList.add(shedule);

        //For cinema id = 2 set shedules
        shedule = new Shedule();
        calendar = Calendar.getInstance();
        cinemaId = 2L;
        movieId = 1L;
        calendar.set(2015, Calendar.AUGUST, 5, 23, 0);
        cinema = cinemaDAO.getElementById(cinemaId);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(calendar);
        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());

        sheduleList.add(shedule);

        shedule = new Shedule();
        calendar = Calendar.getInstance();
        movieId = 2L;
        calendar.set(2015, Calendar.JULY, 29, 11, 15);
        cinema = cinemaDAO.getElementById(cinemaId);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(calendar);
        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());

        sheduleList.add(shedule);

        shedule = new Shedule();
        calendar = Calendar.getInstance();
        movieId = 4L;
        calendar.set(2015, Calendar.JULY, 27, 17, 45);
        cinema = cinemaDAO.getElementById(cinemaId);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(calendar);
        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());

        sheduleList.add(shedule);

        //For cinema id = 3 set shedules
        shedule = new Shedule();
        calendar = Calendar.getInstance();
        cinemaId = 3L;
        movieId = 2L;
        calendar.set(2015, Calendar.AUGUST, 2, 13, 30);
        cinema = cinemaDAO.getElementById(cinemaId);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(calendar);
        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());

        sheduleList.add(shedule);

        shedule = new Shedule();
        calendar = Calendar.getInstance();
        movieId = 3L;
        calendar.set(2015, Calendar.JULY, 19, 14, 40);
        cinema = cinemaDAO.getElementById(cinemaId);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(calendar);
        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());

        sheduleList.add(shedule);

        shedule = new Shedule();
        calendar = Calendar.getInstance();
        movieId = 4L;
        calendar.set(2015, Calendar.JULY, 20, 10, 55);
        cinema = cinemaDAO.getElementById(cinemaId);

        shedule.setCinemaId(cinemaId);
        shedule.setMovieId(movieId);
        shedule.setDateOfSession(calendar);
        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());

        sheduleList.add(shedule);

//        shedule = new Shedule();
//        calendar = Calendar.getInstance();
//        movieId = 5L;
//        calendar.set(2015, Calendar.JULY, 25, 13, 20);
//        cinema = cinemaDAO.getElementById(cinemaId);
//
//        shedule.setCinemaId(cinemaId);
//        shedule.setMovieId(movieId);
//        shedule.setDateOfSession(calendar);
//        shedule.setAvailableTicketsNumber(cinema.getNumberOfSeats());
//
//        sheduleList.add(shedule);

        return sheduleList;
    }

    public static List<Movie> generateMovies() {

        List<Movie> movieList = new ArrayList<Movie>();

        Movie movie = new Movie();
        movie.setName("Terminator Genisys");
        movie.setDescription("John Connor sends Kyle Reese back in time to protect Sarah Connor," +
                " but when he arrives in 1984, nothing is as he expected it to be.");
        movie.setMovieDuration(124);
        movie.setImgDirectory("../img/posters/terminator_genisys.jpg");
        movieList.add(movie);

        movie = new Movie();
        movie.setName("Jurassic World");
        movie.setDescription("A new theme park is built on the original site of Jurassic Park. " +
                "Everything is going well until the park's newest attraction - a genetically modified " +
                "giant stealth killing machine--escapes containment and goes on a killing spree.");
        movie.setMovieDuration(115);
        movie.setImgDirectory("../img/posters/jurassic_world.jpg");
        movieList.add(movie);

        movie = new Movie();
        movie.setName("It follows");
        movie.setDescription("A young woman is followed by an unknown supernatural force " +
                "after getting involved in a sexual encounter.");
        movie.setMovieDuration(98);
        movie.setImgDirectory("../img/posters/it_follows.jpg");
        movieList.add(movie);

        movie = new Movie();
        movie.setName("Ted 2");
        movie.setDescription("Newlywed couple Ted and Tami-Lynn want to have a baby, " +
                "but in order to qualify to be a parent, Ted will have to prove he's a person in a court of law.");
        movie.setMovieDuration(109);
        movie.setImgDirectory("../img/posters/ted_2.jpg");
        movieList.add(movie);

        movie = new Movie();
        movie.setName("Magic Mike XXL");
        movie.setDescription("Three years after Mike bowed out of the stripper life at the top of his game, " +
                "he and the remaining Kings of Tampa hit the road to Myrtle Beach to put on one last " +
                "blow-out performance.");
        movie.setMovieDuration(93);
        movie.setImgDirectory("../img/posters/magic_mike_xxl.jpg");
        movieList.add(movie);

        return movieList;
    }

    public static List<Cinema> generateCinemas() {

        List<Cinema> cinemaList = new ArrayList<Cinema>();

        Cinema cinema1 = new Cinema();
        cinema1.setName("KinoStation - Dafi");
        cinema1.setLocation("boulevard Zvezdniy 1A");
        cinema1.setNumberOfSeats(30);
        cinemaList.add(cinema1);

        Cinema cinema2 = new Cinema();
        cinema2.setName("Most-Kino");
        cinema2.setLocation("Glinki street 2");
        cinema2.setNumberOfSeats(25);
        cinemaList.add(cinema2);

        Cinema cinema3 = new Cinema();
        cinema3.setName("Materik-Kino");
        cinema3.setLocation("Marie Curie street 5");
        cinema3.setNumberOfSeats(28);
        cinemaList.add(cinema3);

        return cinemaList;
    }

}