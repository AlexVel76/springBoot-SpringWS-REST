package com.epam.htsa.controller;

import com.epam.htsa.entity.User;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/user")
public class RestUserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUserById(final @PathVariable Long userId) {
        User result = userFacade.getById(userId);
        return new ResponseEntity<User>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> result = userFacade.getAll();
        return new ResponseEntity<List<User>>(result, HttpStatus.OK);
    }

}
