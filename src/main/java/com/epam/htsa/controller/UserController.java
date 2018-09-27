package com.epam.htsa.controller;

import com.epam.htsa.entity.User;
import com.epam.htsa.entity.UserAccount;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.facade.UserFacade;
import com.epam.htsa.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping
    public ModelAndView getUserById(final @RequestParam Long userId) {
        ModelAndView result;
        User user = userFacade.getById(userId);
        if (user == null)
            throw new HomeTaskExceptioin("User not found");
        result = new ModelAndView("user", "user", user);
        return result;
    }

    @GetMapping(value = "/users")
    public ModelAndView getUsers() {
        ModelAndView result;
        List<User> users = userFacade.getAll();
        if (CollectionUtils.isEmpty(users))
            throw new HomeTaskExceptioin("User not found");
        result = new ModelAndView("userList", "users", users);
        return result;
    }

}
