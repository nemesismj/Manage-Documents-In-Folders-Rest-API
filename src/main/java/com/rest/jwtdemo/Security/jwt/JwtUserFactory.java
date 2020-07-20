package com.rest.jwtdemo.Security.jwt;

import com.rest.jwtdemo.Model.Authority;
import com.rest.jwtdemo.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getEmail(),
                true,
                mapToGrantedAuthorities(new ArrayList<>(user.getAuthorities()))
               // user.getStatus().equals(Status.ACTIVE),
                //user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getAuthority())
                ).collect(Collectors.toList());
    }
}
