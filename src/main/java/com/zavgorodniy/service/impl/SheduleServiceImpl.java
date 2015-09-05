package com.zavgorodniy.service.impl;

import com.zavgorodniy.dao.SheduleDAO;
import com.zavgorodniy.entity.Shedule;
import com.zavgorodniy.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;


@Service
public class SheduleServiceImpl implements SheduleService{

    @Autowired
    private SheduleDAO sheduleDAO;

    public SheduleDAO getSheduleDAO() {
        return sheduleDAO;
    }

    public void setSheduleDAO(SheduleDAO sheduleDAO) {
        this.sheduleDAO = sheduleDAO;
    }

    @Override
    public Collection getCinemasByMovieId(Long id) throws SQLException {
        return sheduleDAO.getCinemasByMovieId(id);
    }

    @Override
    public Collection getMoviesByCinemaId(Long id) throws SQLException {
        return sheduleDAO.getMoviesByCinemaId(id);
    }

    @Override
    public Collection getSessionsStart(Long movieId, Long cinemaId) {
        return sheduleDAO.getSessionsStart(movieId, cinemaId);
    }

    @Override
    public Long addElement(Shedule shedule) throws SQLException {
        return sheduleDAO.addElement(shedule);
    }

    @Override
    public void updateElement(Shedule shedule) throws SQLException {
        sheduleDAO.updateElement(shedule);
    }

    @Override
    public Shedule getElementById(Long id) throws SQLException {
        return sheduleDAO.getElementById(id);
    }

    @Override
    public Collection getAllElements() throws SQLException {
        return sheduleDAO.getAllElements();
    }

    @Override
    public void deleteElement(Shedule shedule) throws SQLException {
        sheduleDAO.deleteElement(shedule);
    }
}
