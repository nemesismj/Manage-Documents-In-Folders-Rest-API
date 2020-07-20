package com.rest.jwtdemo.dto;

import com.rest.jwtdemo.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private boolean enabled;
    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setEnabled(enabled);
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setFirstname(user.getFirstname());
        adminUserDto.setLastname(user.getLastname());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setEnabled(user.isEnabled());
        return adminUserDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
