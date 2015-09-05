package com.zavgorodniy.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "SHEDULES")
public class Shedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Long id;

    @Column(name = "Movie_id", nullable = false, length = 5)
    private Long movieId;

    @Column(name = "Cinema_id", nullable = false, length = 5)
    private Long cinemaId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "Date_of_session", nullable = false)
    private Calendar dateOfSession;

    @Column(name = "Available_tickets", nullable = false, length = 5)
    private Integer availableTicketsNumber;

    public Shedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Calendar getDateOfSession() {
        return dateOfSession;
    }

    public void setDateOfSession(Calendar dateOfSession) {
        this.dateOfSession = dateOfSession;
    }

    public Integer getAvailableTicketsNumber() {
        return availableTicketsNumber;
    }

    public void setAvailableTicketsNumber(Integer availableTicketsNumber) {
        this.availableTicketsNumber = availableTicketsNumber;
    }
}
