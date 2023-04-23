package com.agileproject.userscrud.dao;

import com.agileproject.userscrud.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User findById(int id);

    User save(User user);

    void deleteById(int id);

}
