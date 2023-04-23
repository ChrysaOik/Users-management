package com.agileproject.userscrud.service;

import com.agileproject.userscrud.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    public List<User> findAll();

    Optional<User> findById(int theId);

    User save(User user);

    void deleteById(int id);
}
