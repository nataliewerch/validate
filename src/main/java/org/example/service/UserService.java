package org.example.service;

import org.example.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User addUser(User user);

    User getById(long id);
}
