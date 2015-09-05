package com.zavgorodniy.dao;

import com.zavgorodniy.entity.Cinema;

import java.sql.SQLException;
import java.util.Collection;

public interface CinemaDAO {

    Collection toSearch(String req) throws SQLException;
    Long addElement(Cinema cinema) throws SQLException;
    void updateElement(Cinema cinema) throws SQLException;
    Cinema getElementById(Long id) throws SQLException;
    Collection getAllElements() throws SQLException;
    void deleteElement(Cinema cinema) throws SQLException;
    
}
