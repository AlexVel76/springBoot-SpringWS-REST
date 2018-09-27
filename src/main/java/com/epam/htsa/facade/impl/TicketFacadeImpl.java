package com.epam.htsa.facade.impl;

import com.epam.htsa.entity.Ticket;
import com.epam.htsa.facade.TicketFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.htsa.service.TicketService;

import java.util.Date;
import java.util.List;

@Service
public class TicketFacadeImpl implements TicketFacade {

    @Autowired
    private TicketService ticketService;

    @Override
    public Ticket getTicketById(Long ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    @Override
    public double getTicketsPrice(final Long eventId, final String dateTime, final Long userId, final String seat) {
        return ticketService.getTicketPrice(eventId, dateTime, userId, seat);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @Override
    public List<Ticket> getPurchasedTicketsForEvent(Long eventId, String dateTime) {
        return ticketService.getPurchasedTicketsForEvent(eventId, dateTime);

    }
}
