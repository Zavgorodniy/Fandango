package com.zavgorodniy.model;

import com.zavgorodniy.entity.Cinema;
import com.zavgorodniy.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * Created by ADMIN on 29.07.2015.
 */
@Component
public class Form {

    private String name;

    private String lastName;

    private Movie movie;

    private Cinema cinema;

    private Long sheduleId;

    private Calendar startOfSession;

    private String sessionDate;

    private String sessionTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public Long getSheduleId() {
        return sheduleId;
    }

    public void setSheduleId(Long sheduleId) {
        this.sheduleId = sheduleId;
    }

    public Calendar getStartOfSession() {
        return startOfSession;
    }

    public void setStartOfSession(Calendar startOfSession) {
        this.startOfSession = startOfSession;
    }
}
