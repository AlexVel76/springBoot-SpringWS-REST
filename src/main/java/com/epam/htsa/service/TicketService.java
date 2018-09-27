package com.epam.htsa.service;

import com.epam.htsa.entity.Ticket;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface TicketService {
    Ticket getTicketById(Long ticketId);

    double getTicketPrice(Long eventId, String dateTime, Long userId, String seat);

    List<Ticket> getAllTickets();

    List<Ticket> getPurchasedTicketsForEvent(Long eventId, String dateTime);

}
