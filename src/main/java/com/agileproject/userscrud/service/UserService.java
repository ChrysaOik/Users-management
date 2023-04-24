package com.agileproject.userscrud.service;

import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDTO> findAll();

    Optional<UserDTO> findById(UUID theId);

    User save(User user);

    void deleteById(UUID id);
}
