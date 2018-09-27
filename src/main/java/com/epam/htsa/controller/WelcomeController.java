package com.epam.htsa.controller;

import com.google.common.base.Strings;
import com.epam.htsa.exception.HomeTaskExceptioin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    private static final Logger LOG = LogManager.getLogger(WelcomeController.class);

    @GetMapping(value = {"/", "/welcome"})
    public String welcome() {
        LOG.info("Call welcome page");
        return "welcome";
    }
}
