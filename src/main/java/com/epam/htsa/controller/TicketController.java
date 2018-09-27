package com.epam.htsa.controller;

import com.epam.htsa.entity.Ticket;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.facade.BookingFacade;
import com.epam.htsa.facade.TicketFacade;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {

    @Autowired
    private TicketFacade ticketFacade;

    @Autowired
    private BookingFacade bookingFacade;

    /**
     * Get ticket by id
     * <p>
     * Example: http://localhost:8080/booking/ticket?ticketId=1
     *
     * @param ticketId
     * @return
     */
    @GetMapping
    public ModelAndView getTicketByTicketId(final @RequestParam Long ticketId) {
        ModelAndView result;
        Ticket ticket = ticketFacade.getTicketById(ticketId);
        if (ticket == null)
            throw new HomeTaskExceptioin("Ticket not found");
        result = new ModelAndView("ticket", "ticket", ticket);
        return result;
    }

    /**
     * Books tickets in internal system.
     */
    @PostMapping(value = "/add")
    public String bookTickets(final Long eventId, final Long userId, final String seats, final Date dateTime) {
        bookingFacade.bookTickets(eventId, dateTime, userId, seats);
        return "redirect:add";
    }

    @GetMapping(value = "/add")
    public String bookTickets() {
        return "ticketadd";
    }

    /**
     * Booked tickets in internal system.
     */
    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")//all user password is 'password'
    @GetMapping(value = "/tickets")
    public ModelAndView getAllTickets() {
        return new ModelAndView("main", "tickets", ticketFacade.getAllTickets());
    }

    /**
     * Getting price when buying all supplied seats for particular event
     *
     * @return total price
     * @throws ParseException
     */

    @GetMapping(value = "/price")
    @PostMapping(value = "/price")
    public ModelAndView getTicketsPrice(Long eventId, String dateTime, Long userId, String seat)
            throws ParseException {

        return new ModelAndView("ticketprice", "price",
                ticketFacade.getTicketsPrice(eventId, dateTime, userId, seat));
    }

    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param eventId  Event to get tickets for
     * @param dateTime Date and time of airing of event
     * @return set of all purchased tickets
     * @throws ParseException
     */

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")//all user password is 'password'
    @GetMapping(value = "/bookedticket")
    public ModelAndView getPurchasedTicketsForEvent(Long eventId, String dateTime) throws ParseException {
        return new ModelAndView("bookedticket", "tickets",
                ticketFacade.getPurchasedTicketsForEvent(eventId, dateTime));
    }

}
