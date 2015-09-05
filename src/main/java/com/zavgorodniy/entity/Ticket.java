package com.zavgorodniy.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false, length = 30)
    private String name;

    @Column(name = "Lastname", nullable = false, length = 30)
    private String lastname;

    @Column(name = "Movie_name", nullable = false, length = 30)
    private String movieName;

    @Column(name = "Cinema_name", nullable = false, length = 30)
    private String cinemaName;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "startOfSession", nullable = false)
    private Calendar startOfSession;

    @Column(name = "Movie_duration", length = 5)
    private Integer movieDuration;

    public Ticket() {
    }

    public Integer getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(Integer movieDuration) {
        this.movieDuration = movieDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public Calendar getStartOfSession() {
        return startOfSession;
    }

    public void setStartOfSession(Calendar startOfSession) {
        this.startOfSession = startOfSession;
    }
}
