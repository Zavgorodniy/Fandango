package com.zavgorodniy.controller;

import com.zavgorodniy.entity.Shedule;
import com.zavgorodniy.entity.Ticket;
import com.zavgorodniy.service.SheduleService;
import com.zavgorodniy.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@Controller
public class CongratulationsController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SheduleService sheduleService;

    @RequestMapping(value = "/congratulations")
    public String toConfirm() throws SQLException, IOException {

        Long sheduleId = FormController.form.getSheduleId();
        Shedule shedule = sheduleService.getElementById(sheduleId);
        Integer seats = shedule.getAvailableTicketsNumber();

        Ticket ticket = new Ticket();
        ticket.setName(FormController.form.getName());
        ticket.setLastname(FormController.form.getLastName());
        ticket.setMovieName(FormController.form.getMovie().getName());
        ticket.setCinemaName(FormController.form.getCinema().getName());
        ticket.setMovieDuration(FormController.form.getMovie().getMovieDuration());
        ticket.setStartOfSession(FormController.form.getStartOfSession());

        if (seats > 0) {
            shedule.setAvailableTicketsNumber(seats - 1);
            sheduleService.updateElement(shedule);
            ticketService.addElement(ticket);

            return "congratulations";
        }

        return "login";
    }

}
