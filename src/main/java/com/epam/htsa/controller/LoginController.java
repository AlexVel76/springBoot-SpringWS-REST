package com.epam.htsa.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    private static final Logger LOG = LogManager.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        LOG.info("Getting login page, error={}", error);
        return new ModelAndView("login", "error", error);
    }
}
