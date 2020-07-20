package com.rest.jwtdemo.Security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;

public class JwtUser implements UserDetails {

    private  String username;
    private  String firstname;
    private String lastname;
    private  String password;
    private String email;
    private  boolean enabled;
    private  Collection<? extends GrantedAuthority> authorities;

    public JwtUser(String username, String firstname, String lastname, String password, String email, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFirstname() {
        return firstname;
    }



    public String getLastname() {
        return lastname;
    }



    public String getEmail() {
        return email;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
