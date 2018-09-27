package com.epam.htsa.facade.impl;

import com.epam.htsa.entity.Event;
import com.epam.htsa.entity.Ticket;
import com.epam.htsa.entity.User;
import com.epam.htsa.entity.UserAccount;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.facade.BookingFacade;
import com.epam.htsa.service.*;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    private LocalDateTime getLocalDateTime(Date dateTime) {
        return dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public List<Ticket> bookTickets(final Long eventId, final Date dateTime, final Long userId,
                                    final String seats) {

        List<Ticket> tickets = Lists.newArrayList();
        User user = userService.getById(userId);
        Event event = eventService.getById(eventId);

        if (user == null || event == null) {
            throw new HomeTaskExceptioin("User or Event not found");
        }

        Set<Long> sets = Arrays.stream(seats.split(",")).map(String::trim).mapToLong(Long::parseLong).boxed()
                .collect(Collectors.toSet());

        for (Long seat : sets) {
            tickets.add(new Ticket(user, event, getLocalDateTime(dateTime), seat, event.getBasePrice()));
        }
        return bookingService.bookTickets(tickets);
    }

    @Override
    public Ticket bookTickets(Ticket ticket) {
        return bookTickets(ticket.getEvent().getId(), new Date(),
                ticket.getUser().getId(), new Long(ticket.getSeat()).toString()).get(0);
    }


}
