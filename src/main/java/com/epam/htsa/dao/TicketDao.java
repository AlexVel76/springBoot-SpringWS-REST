package com.epam.htsa.dao;

import com.epam.htsa.entity.Event;
import com.epam.htsa.entity.Ticket;
import com.epam.htsa.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Oleksandr_Velchenko
 */
public interface TicketDao extends CrudRepository<Ticket, Long> {

    /**
     * Getting price when buying all supplied seats for particular event
     *
     * @param event    Event to get base ticket price, vip seats and other
     *                 information
     * @param dateTime Date and time of event air
     * @param user     User that buys ticket could be needed to calculate discount.
     *                 Can be <code>null</code>
     * @return total price
     */
    public List<Ticket> findByEventAndDateTimeAndUser(Event event, String dateTime, User user);


    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param event    Event to get tickets for
     * @param dateTime Date and time of airing of event
     * @return set of all purchased tickets
     */
    public List<Ticket> findByEventAndDateTime(Event event, String dateTime);

}
