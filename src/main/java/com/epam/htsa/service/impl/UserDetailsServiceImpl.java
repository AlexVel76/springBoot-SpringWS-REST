package com.epam.htsa.service.impl;

import com.epam.htsa.entity.User;
import com.epam.htsa.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOG = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getByLogin(login);
        LOG.info("User : " + user);
        if (user == null) {
            throw new UsernameNotFoundException("Username: " + login + " not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true,
                true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Arrays.stream(user.getRoles().split(",")).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        return authorities;
    }
}
