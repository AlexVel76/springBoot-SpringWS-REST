package com.epam.htsa.facade.impl;

import com.epam.htsa.entity.User;
import com.epam.htsa.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import com.epam.htsa.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public void save(User user) {
        userService.save(user);
    }


    @Override
    public void remove(final User user) {
        userService.remove(user);
    }

    @Override
    public User getById(final Long id) {
        return userService.getById(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userService.getAll();
    }

    @Override
    public User getUserByEmail(final String email) {
        return userService.getUserByEmail(email);
    }
}
