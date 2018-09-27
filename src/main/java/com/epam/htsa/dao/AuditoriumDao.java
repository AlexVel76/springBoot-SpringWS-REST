package com.epam.htsa.dao;

import com.epam.htsa.entity.Auditorium;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Oleksandr_Velchenko
 */
public interface AuditoriumDao extends CrudRepository<Auditorium, Long> {

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    public Auditorium findByName(String name);

}
