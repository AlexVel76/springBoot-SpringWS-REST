package com.epam.htsa.controller;

import com.epam.htsa.entity.Ticket;
import com.epam.htsa.facade.BookingFacade;
import com.epam.htsa.facade.TicketFacade;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/booking")
public class RestBookingController {

    @Autowired
    private BookingFacade bookingFacade;

    @Autowired
    private TicketFacade ticketFacade;


    @GetMapping(value = "/{ticketId}")
    public @ResponseBody Ticket getTicketById(@PathVariable Long ticketId) {
        return ticketFacade.getTicketById(ticketId);
    }

    @GetMapping()
    public @ResponseBody List<Ticket> getTickets() {
        return Lists.newArrayList(ticketFacade.getAllTickets());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Ticket> bookTickets(@RequestBody Ticket ticket) {
        return new ResponseEntity<Ticket>(bookingFacade.bookTickets(ticket), HttpStatus.OK);
    }

    @GetMapping(value = "/price/{eventId}/{dateTime}/{userId}/{seat}")
    public ResponseEntity<Double> getTicketPrice(@PathVariable Long eventId, @PathVariable String dateTime,
                                                 @PathVariable Long userId, @PathVariable String seat) {

        return new ResponseEntity<Double>(ticketFacade.getTicketsPrice(eventId, dateTime, userId, seat),
                HttpStatus.OK);
    }

}
