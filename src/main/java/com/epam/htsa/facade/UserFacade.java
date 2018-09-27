package com.epam.htsa.facade;

import com.epam.htsa.entity.User;

import java.util.List;

public interface UserFacade {
    void save(User user);

    void remove(User user);

    User getById(Long id);

    List<User> getAll();

    User getUserByEmail(String email);
}
