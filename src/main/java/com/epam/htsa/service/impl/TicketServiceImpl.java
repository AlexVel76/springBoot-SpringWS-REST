package com.epam.htsa.service.impl;

import com.epam.htsa.dao.UserAccountDao;
import com.epam.htsa.entity.UserAccount;
import com.epam.htsa.service.EventService;
import com.epam.htsa.service.TicketService;
import com.epam.htsa.service.UserAccountService;
import com.epam.htsa.service.UserService;
import com.google.common.collect.Lists;
import com.epam.htsa.dao.TicketDao;
import com.epam.htsa.entity.Event;
import com.epam.htsa.entity.Ticket;
import com.epam.htsa.entity.User;
import com.epam.htsa.exception.HomeTaskExceptioin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Override
    public Ticket getTicketById(final Long ticketId) {
        return ticketDao.findById(ticketId).orElse(new Ticket());
    }

    @Override
    public double getTicketPrice(Long eventId, String dateTime, Long userId, String seat) {
        double result = 0;

        List<Ticket> tickets = ticketDao.findByEventAndDateTimeAndUser(eventService.getById(eventId), dateTime, userService.getById(userId));

        for (Ticket ticket : tickets) {
            result += ticket.getPrice();
        }

        return result;
    }

    @Override
    public List<Ticket> getPurchasedTicketsForEvent(final Long eventId, final String dateTime) {
        return (List<Ticket>) ticketDao.findByEventAndDateTime(eventService.getById(eventId), dateTime);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return Lists.newArrayList(ticketDao.findAll());
    }

    private List<Ticket> prepareTickets(final Long eventId, final Date dateTime, final Long userId, final String seats) {
        List<Ticket> result = Lists.newArrayList();
        User user = userService.getById(userId);
        Event event = eventService.getById(eventId);

        if (user == null || event == null) {
            throw new HomeTaskExceptioin("User or Event not found");
        }

        Set<Long> sets = Arrays.stream(seats.split(",")).map(String::trim).mapToLong(Long::parseLong).boxed()
                .collect(Collectors.toSet());

        for (Long seat : sets) {
            result.add(new Ticket(user, event, getLocalDateTime(dateTime), seat, event.getBasePrice()));
        }

        return result;
    }


    private LocalDateTime getLocalDateTime(final Date dateTime) {
        return dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
