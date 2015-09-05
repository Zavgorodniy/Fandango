package com.zavgorodniy.dao;

import com.zavgorodniy.entity.Shedule;

import java.sql.SQLException;
import java.util.Collection;

public interface SheduleDAO {

    Collection getCinemasByMovieId(Long id) throws SQLException;
    Collection getMoviesByCinemaId(Long id) throws SQLException;
    Collection getSessionsStart(Long movieId, Long cinemaId);

    Long addElement(Shedule shedule) throws SQLException;
    void updateElement(Shedule shedule) throws SQLException;
    Shedule getElementById(Long id) throws SQLException;
    Collection getAllElements() throws SQLException;
    void deleteElement(Shedule shedule) throws SQLException;

}
