package com.epam.htsa.service.impl;

import com.epam.htsa.dao.EventDao;
import com.epam.htsa.entity.Event;
import com.epam.htsa.exception.HomeTaskExceptioin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.epam.htsa.service.EventService;
import java.util.List;

/**
 * @author Oleksandr_Velchenko
 */
@Component
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;

	/**
	 * Finding event by name
	 * 
	 * @param name
	 *            Name of the event
	 * @return found event or <code>null</code>
	 */
	public Event getByName(final String name) {
		return eventDao.findByName(name);
	}

	@Override
	public Event save(final Event event) {

		return eventDao.save(event);
	}

	@Override
	public void remove(final Event object) {
		eventDao.delete(object);
	}

	@Override
	public Event getById(final Long id) {
		return eventDao.findById(id).orElseGet(null);
	}

	@Override
	public List<Event> getAll() {
		return (List<Event>) eventDao.findAll();
	}

}
