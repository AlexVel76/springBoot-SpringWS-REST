package com.epam.htsa.service;

import com.epam.htsa.entity.User;

/**
 * @author Oleksandr_Velchenko
 */
public interface UserService extends ItemService<User> {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    public User getUserByEmail(String email);

    User getByLogin(String login);
}
