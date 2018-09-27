package com.epam.htsa.controller;

import com.epam.htsa.exception.HomeTaskExceptioin;
import com.google.common.base.Joiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.Optional;

@ControllerAdvice
@Order(value = 0)
public class CustomExceptionHandler {
    private static final Logger LOG = LogManager.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler({UsernameNotFoundException.class, Exception.class, HomeTaskExceptioin.class, RuntimeException.class, FileNotFoundException.class})
    public ModelAndView handleException(WebRequest req, Exception e) {
        LOG.error("Requested URL=" + req.toString());
        LOG.error("Exception Raised=" + e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("message", e instanceof HomeTaskExceptioin ? ((HomeTaskExceptioin) e).getExceptionMsg()
                : Optional.ofNullable(e.getMessage()).orElse("With empty message"));
        modelAndView.addObject("request", req.toString());
        modelAndView.addObject("stacktrace", Joiner.on(System.getProperty("line.separator")).join(e.getStackTrace()));

        modelAndView.setViewName("error");
        return modelAndView;
    }
}

