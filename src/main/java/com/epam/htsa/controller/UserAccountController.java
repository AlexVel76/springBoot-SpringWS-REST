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

import java.util.List;

@Controller
@RequestMapping(value = "/user/account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PutMapping(value = "/{userId}")
    public ModelAndView refillingAcount(final @RequestBody Double amount, final @RequestParam Long userId) {
        ModelAndView result;
        UserAccount userAcount = userAccountService.refillingAcount(userId, amount);
        result = new ModelAndView("user", "user", userAcount.getUser());
        return result;
    }
}
