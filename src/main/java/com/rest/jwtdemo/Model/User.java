package com.rest.jwtdemo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private boolean enabled;
    @JacksonXmlProperty(localName = "authorities")
    @JacksonXmlElementWrapper(useWrapping = false)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "username")
    private Set<Authority> authorities =  new HashSet<>();
    private String firstname;
    private String lastname;
    private String email;
    @Transient
    private String repeatedPassword;

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
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

    public User() {
    }

    public User(String username, String password, boolean enabled, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
       // this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Boolean setEnabled(boolean enabled) {
        this.enabled = enabled;
        return null;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public Set<Authority> setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
        return authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", repeatedPassword='" + repeatedPassword + '\'' +
                '}';
    }
}

