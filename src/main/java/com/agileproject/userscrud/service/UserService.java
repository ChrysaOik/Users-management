package com.agileproject.userscrud.service;

import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.dto.UserRequest;
import com.agileproject.userscrud.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(UUID theId);

    UserDTO save(UserRequest userRequest);

    UserDTO update(UserRequest userRequest);

    void deleteById(UUID id);
}
