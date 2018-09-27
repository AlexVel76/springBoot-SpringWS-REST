package com.epam.htsa.facade.impl;

import com.epam.htsa.entity.Event;
import com.epam.htsa.facade.EventFacade;
import org.springframework.beans.factory.annotation.Autowired;
import com.epam.htsa.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventFacadeImpl implements EventFacade {

    @Autowired
    private EventService eventService;

    @Override
    public void save(Event event) {
        eventService.save(event);
    }

    @Override
    public List<Event> getAll() {
        return (List<Event>) eventService.getAll();
    }

    @Override
    public Event getByName(final String name) {
        return eventService.getByName(name);
    }

    @Override
    public void remove(final Event object) {
        eventService.remove(object);
    }

    @Override
    public Event getById(final Long id) {
        return eventService.getById(id);
    }
}
