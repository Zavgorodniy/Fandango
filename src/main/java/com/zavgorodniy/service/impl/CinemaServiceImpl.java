package com.zavgorodniy.service.impl;

import com.zavgorodniy.dao.CinemaDAO;
import com.zavgorodniy.entity.Cinema;
import com.zavgorodniy.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaDAO cinemaDAO;

    public CinemaDAO getCinemaDAO() {
        return cinemaDAO;
    }

    public void setCinemaDAO(CinemaDAO cinemaDAO) {
        this.cinemaDAO = cinemaDAO;
    }

    @Override
    public Collection toSearch(String req) throws SQLException {
        return cinemaDAO.toSearch(req);
    }

    @Override
    public Long addElement(Cinema cinema) throws SQLException {
        return cinemaDAO.addElement(cinema);
    }

    @Override
    public void updateElement(Cinema cinema) throws SQLException {
        cinemaDAO.updateElement(cinema);
    }

    @Override
    public Cinema getElementById(Long id) throws SQLException {
        return cinemaDAO.getElementById(id);
    }

    @Override
    public Collection getAllElements() throws SQLException {
        return cinemaDAO.getAllElements();
    }

    @Override
    public void deleteElement(Cinema cinema) throws SQLException {
        cinemaDAO.deleteElement(cinema);
    }
}
