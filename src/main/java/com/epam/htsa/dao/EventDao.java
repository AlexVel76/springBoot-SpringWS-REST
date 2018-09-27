package com.epam.htsa.dao;

import com.epam.htsa.entity.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yuriy_Tkach, Oleksandr_Velchenko
 */
public interface EventDao extends CrudRepository<Event, Long> {

    /**
     * Finding event by name
     *
     * @param name Name of the event
     * @return found event or <code>null</code>
     */
    public Event findByName(String name);

}
