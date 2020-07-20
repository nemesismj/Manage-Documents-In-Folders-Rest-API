package com.rest.jwtdemo.Security;

import com.rest.jwtdemo.Model.User;
import com.rest.jwtdemo.Security.jwt.JwtUser;
import com.rest.jwtdemo.Security.jwt.JwtUserFactory;
import com.rest.jwtdemo.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
       // System.out.println("juser "+jwtUser.getPassword());

        return jwtUser;
    }
}
