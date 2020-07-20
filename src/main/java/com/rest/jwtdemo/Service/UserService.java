package com.rest.jwtdemo.Service;

import com.rest.jwtdemo.Model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Integer id);

    void delete(Integer id);

    void deleteByUsername(String username);
    void saveUser(User user);
    void edit(String name, User user);
    User getCurrentLoggedUser();
}
