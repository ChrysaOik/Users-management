package com.agileproject.userscrud.service;

import com.agileproject.userscrud.dao.UserRepository;
import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.dto.UserDTOMapper;
import com.agileproject.userscrud.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserServiceImpl(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findById(UUID theId) {
        return userRepository.findById(theId)
                .map(userDTOMapper);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

}
