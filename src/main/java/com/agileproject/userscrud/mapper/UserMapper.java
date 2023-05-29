package com.agileproject.userscrud.mapper;

import com.agileproject.userscrud.dto.UserDTO;
import com.agileproject.userscrud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") //defining mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class); //creates an Instance

    User userDTOToUser(UserDTO userRequest);

    UserDTO userToUserDTO(User user);
    List<UserDTO> allUsersToUserDTO(List<User> users);
}