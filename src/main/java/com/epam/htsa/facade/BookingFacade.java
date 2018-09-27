package com.epam.htsa.facade;

import com.epam.htsa.entity.Ticket;
import com.epam.htsa.entity.UserAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BookingFacade {

    public List<Ticket> bookTickets(Long eventId, Date dateTime, Long userId, String seats);

    Ticket bookTickets(Ticket ticket);
}
