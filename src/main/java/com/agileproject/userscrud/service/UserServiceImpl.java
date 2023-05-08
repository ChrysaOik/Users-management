package com.agileproject.userscrud.service;

import com.agileproject.userscrud.dao.UserRepository;
import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.dto.UserRequest;
import com.agileproject.userscrud.entity.User;
import com.agileproject.userscrud.mapper.UserMapper;
import com.agileproject.userscrud.rest.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserDTO(u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail()) )
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(UUID theId) {

        User user = userRepository.findById(theId).orElseThrow(() -> new UserNotFoundException());
        return UserMapper.INSTANCE.userToUserDTO(user);


    }

    @Override
    public UserDTO save(@Valid UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(),savedUser.getFirstName(),savedUser.getLastName(), savedUser.getEmail());
    }

    public UserDTO update(UserRequest userRequest){
        User user = userRepository.findById(userRequest.id())
                .orElseThrow(() -> new UserNotFoundException());

        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());

        User updatedUser = userRepository.save(user);

        return new UserDTO(updatedUser.getId(), updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail());
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

}
