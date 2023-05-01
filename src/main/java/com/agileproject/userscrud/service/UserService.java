package com.agileproject.userscrud.service;

import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.dto.UserRequest;
import com.agileproject.userscrud.entity.User;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(UUID theId);

    UserDTO save(@Valid UserRequest userRequest);

    UserDTO update(UserRequest userRequest);

    void deleteById(UUID id);
}
