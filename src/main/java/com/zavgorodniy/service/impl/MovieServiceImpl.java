package com.zavgorodniy.service.impl;

import com.zavgorodniy.dao.MovieDAO;
import com.zavgorodniy.entity.Movie;
import com.zavgorodniy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDAO movieDAO;

    public MovieDAO getMovieDAO() {
        return movieDAO;
    }

    public void setMovieDAO(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @Override
    public Collection toSearch(String req) throws SQLException {
        return movieDAO.toSearch(req);
    }

    @Override
    public Long addElement(Movie movie) throws SQLException {
        return movieDAO.addElement(movie);
    }

    @Override
    public void updateElement(Movie movie) throws SQLException {
        movieDAO.updateElement(movie);
    }

    @Override
    public Movie getElementById(Long id) throws SQLException {
        return movieDAO.getElementById(id);
    }

    @Override
    public Collection getAllElements() throws SQLException {
        return movieDAO.getAllElements();
    }

    @Override
    public void deleteElement(Movie movie) throws SQLException {
        movieDAO.deleteElement(movie);
    }
}
