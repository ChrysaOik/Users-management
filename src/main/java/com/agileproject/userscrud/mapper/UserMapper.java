package com.agileproject.userscrud.mapper;

import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDTOToUser(UserDTO userRequest);

    UserDTO userToUserDTO(User user);
    List<UserDTO> allUsersToUserDTO(List<User> users);
}