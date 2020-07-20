package com.rest.jwtdemo.Service;

import com.rest.jwtdemo.Model.Authority;
import com.rest.jwtdemo.Model.User;
import com.rest.jwtdemo.Repository.AuthorityRepository;
import com.rest.jwtdemo.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Authority roleUser = authorityRepository.findByAuthority("ROLE_USER");
        Set<Authority> userRoles =  new HashSet<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(userRoles);
        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Integer id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
    @Transactional
    @Override
    public void deleteByUsername(String username) {
        authorityRepository.deleteByUsername(username);
        userRepository.deleteByUsername(username);
        log.info("IN delete - user with username: {} successfully deleted", username);
    }
    @Transactional
    @Override
    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authority authority = new Authority();
        authority.setUsername(user.getUsername());
        authority.setAuthority("ROLE_ADMIN");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setAuthorities(authorities);
       // System.out.println(user.getAuthorities());
        log.info("IN saveUser - user with username: {} successfully saved", user.getUsername());
        userRepository.save(user);
        authorityRepository.save(authority);
    }
    @Transactional
    @Override
    public void edit(String name, User user) {
        User editUser= userRepository.findByUsername(name);
        editUser.setUsername(name);
        editUser.setEmail(user.getEmail());
        editUser.setEnabled(user.isEnabled());
        editUser.setFirstname(user.getFirstname());
        editUser.setLastname(user.getLastname());
        editUser.setPassword(user.getPassword());
        editUser.setRepeatedPassword(user.getRepeatedPassword());
        editUser.setAuthorities(user.getAuthorities());
        log.info("IN edit - user with username: {} successfully edited", editUser.toString());
        saveUser(editUser);

    }
    @Override
    public User getCurrentLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        log.info("IN getCurrentLoggedUser - current logged user is: {}", auth.getName());
        return userRepository.getUserByName(username);
    }

}
