package com.epam.htsa.facade;

import com.epam.htsa.entity.Event;

import java.util.List;

public interface EventFacade {
    void save(Event event);

    List<Event> getAll();

    Event getByName(String name);

    void remove(Event object);

    Event getById(Long id);
}
