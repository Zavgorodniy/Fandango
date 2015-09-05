package com.zavgorodniy.service.impl;

import com.zavgorodniy.dao.TicketDAO;
import com.zavgorodniy.entity.Ticket;
import com.zavgorodniy.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketDAO ticketDAO;

    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Long addElement(Ticket ticket) throws SQLException {
        return ticketDAO.addElement(ticket);
    }

    @Override
    public void updateElement(Ticket ticket) throws SQLException {
        ticketDAO.updateElement(ticket);
    }

    @Override
    public Ticket getElementById(Long id) throws SQLException {
        return ticketDAO.getElementById(id);
    }

    @Override
    public Collection getAllElements() throws SQLException {
        return ticketDAO.getAllElements();
    }

    @Override
    public void deleteElement(Ticket ticket) throws SQLException {
        ticketDAO.deleteElement(ticket);
    }
}
