package com.zavgorodniy.dao;

import com.zavgorodniy.entity.Movie;

import java.sql.SQLException;
import java.util.Collection;

public interface MovieDAO {

    Collection toSearch(String req) throws SQLException;
    Long addElement(Movie movie) throws SQLException;
    void updateElement(Movie movie) throws SQLException;
    Movie getElementById(Long id) throws SQLException;
    Collection getAllElements() throws SQLException;
    void deleteElement(Movie movie) throws SQLException;

}
