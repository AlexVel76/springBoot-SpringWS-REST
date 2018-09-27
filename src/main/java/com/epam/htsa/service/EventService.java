package com.epam.htsa.service;

import com.epam.htsa.entity.Event;

/**
 * @author Oleksandr_Velchenko
 */
public interface EventService extends ItemService<Event> {

    /**
     * Finding event by name
     *
     * @param name Name of the event
     * @return found event or <code>null</code>
     */
    public Event getByName(String name);
}
