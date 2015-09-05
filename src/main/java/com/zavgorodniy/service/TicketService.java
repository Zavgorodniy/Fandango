package com.zavgorodniy.service;

import com.zavgorodniy.entity.Ticket;

import java.sql.SQLException;
import java.util.Collection;

public interface TicketService {

    Long addElement(Ticket ticket) throws SQLException;
    void updateElement(Ticket ticket) throws SQLException;
    Ticket getElementById(Long id) throws SQLException;
    Collection getAllElements() throws SQLException;
    void deleteElement(Ticket ticket) throws SQLException;

}
