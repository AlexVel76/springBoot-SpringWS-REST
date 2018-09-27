package com.epam.htsa.dao;

import com.epam.htsa.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Oleksandr_Velchenko
 */
public interface UserDao extends CrudRepository<User, Long> {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    public User findByEmail(String email);

    /**
     * Finding user by login
     *
     * @param login User Login
     * @return found user or <code>null</code>
     */
    public User findByLogin(String login);
}
